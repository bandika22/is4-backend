package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.CODE_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PARAMETERS;
import static hu.econsult.is4.util.ErrorCodes.CODE_NO_PERMISSION;
import static hu.econsult.is4.util.ErrorCodes.CODE_OK;
import static hu.econsult.is4.util.ErrorCodes.CODE_UNKNOWN;
import static hu.econsult.is4.util.ErrorCodes.CODE_USER_NOT_FOUND;
import static hu.econsult.is4.util.ErrorCodes.MSG_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_AUTH_ROLE;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SP_ID_PARAM;
import static hu.econsult.is4.util.ErrorCodes.MSG_NO_PERMISSION;
import static hu.econsult.is4.util.ErrorCodes.MSG_OK;
import static hu.econsult.is4.util.ErrorCodes.MSG_UNKNOWN_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_USER_NOT_FOUND;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.NoResultException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4ResGetInstituteData;
import hu.econsult.is4.model.IS4ResGetRootSpBySession;
import hu.econsult.is4.model.IS4ResGetInstElementExt;
import hu.econsult.is4.model.IS4ResGetUserSps;
import hu.econsult.is4.model.NEUReqCheckUser;
import hu.econsult.is4.model.NEUReqLogin;
import hu.econsult.is4.model.NEUReqUpdateSession;
import hu.econsult.is4.model.NEUResLogin;
import hu.econsult.is4.model.NEUResUpdateSession;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.IS4ObjGetInstituteData;
import hu.econsult.is4.model.entity.ObjGetInstElement;
import hu.econsult.is4.model.entity.ObjServicePoint;
import hu.econsult.is4.model.entity.ObjSessionExt;
import hu.econsult.is4.repository.IS4ObjGetInstElementRepository;
import hu.econsult.is4.repository.InstituteDataRepository;
import hu.econsult.is4.repository.ServicePointRepository;
import hu.econsult.is4.repository.SessionRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	private final SpService spService;
	private final SessionRepository sessionRepository;
	private final ServicePointRepository servicePointRepository;
	private final InstituteDataRepository instituteDataRepository;
	private final IS4ObjGetInstElementRepository getInstElementRepository;
	
	@Autowired
	public AuthService(SpService spService, SessionRepository sessionRepository, ServicePointRepository servicePointRepository, InstituteDataRepository instituteDataRepository, IS4ObjGetInstElementRepository getInstElementRepository) {
		super();
		this.spService = spService;
		this.sessionRepository = sessionRepository;
		this.servicePointRepository = servicePointRepository;
		this.instituteDataRepository = instituteDataRepository;
		this.getInstElementRepository = getInstElementRepository;
	}


	public NEUResLogin loginAndReturnSessionId(NEUReqLogin request) {
		NEUResLogin result = new NEUResLogin();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if(Util.isEmptyString(request.getUserName())) {
			result.setCodeAndMessage(CODE_MISSING_PARAMETERS, MSG_MISSING_AUTH_ROLE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + MSG_MISSING_AUTH_ROLE);
		}
		
		try {
			result = spService.callLogin(request.getDeviceID(), request.getUserName(), request.getPassword(), request.getSpCode());
			if(result.getCode() < 0) {
				log.error("[" + currentMethodName + "] - " + callId + " - code:" + result.getCode() + " - message:" + result.getMessage() + " - login failed. deviceId: " + request.getDeviceID());
				return result;
			} else {
				log.info("[" + currentMethodName + "] - " + callId + " - " + request.getUserName() + " - logged in. deviceId: " + request.getDeviceID());
				return result;
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.getUserName() + " - login failed. deviceId: " + request.getDeviceID() + " Ex: " + e);
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID + callId);
			return result;
		}
	}
	
	public IS4ResGetUserSps getUserSps(String sessionId) {
		IS4ResGetUserSps response = new IS4ResGetUserSps();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(sessionId)) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		ObjSessionExt session = sessionRepository.callFnGetCurrentSession(sessionId);
		

		List<ObjServicePoint> servicePoints = servicePointRepository.callFnGetUserServicePoints(session.getUserId(), null, "SELECT", sessionId, null);
		response.setServicePoints(servicePoints);
				
		log.info("[" + currentMethodName + "]");
		return response;
	}
	
	public Response checkSession(String sessionId, String spCode ) {
		Response response = new Response();
		ObjSessionExt session = sessionRepository.callFnGetCurrentSession(sessionId);
		NEUReqUpdateSession request = new NEUReqUpdateSession();
		
		if(session != null) {
			if(isSpSame(spCode, session) && !sessionExpireInOneHour(session.getExpTime()) ) {
				response.setCode(0);
				response.setMessage(sessionId);
				return response;
			} else if(isSpSame(spCode, session) && sessionExpireInOneHour(session.getExpTime())) {
				response.setCode(2);
				response.setMessage(sessionId);
				return response;
			} else if(!isSpSame(spCode, session)) {
				request.setServicePoint(spCode);
				request.setSessionId(sessionId);
				upadteSessionId(request);
				response.setCode(1);
				response.setMessage(sessionId);
			}
		} else {
			response.setCodeAndMessage(-1, "Lejárt a session!");
		}
		
		return response;
	}
	
	public boolean isSpSame(String spCode, ObjSessionExt session ) {
		return session.getCurrRoleCode().contains(spCode);
	}
	
	
	public boolean sessionExpireInOneHour(Date sessionExpdate) {
		return TimeUnit.MILLISECONDS.toMinutes(sessionExpdate.getTime()- new Date().getTime()) <= 60;
	}
	
	
	public NEUResUpdateSession upadteSessionId(NEUReqUpdateSession request) {
		NEUResUpdateSession result = new NEUResUpdateSession();
		String contextParam = "";
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		if(!Util.isEmptyString(request.getAuthRole())) {
			contextParam += "#AUTH_ROLE=" + request.getAuthRole();
		}
		
		if(!Util.isEmptyString(request.getServicePoint())) {
			contextParam += "#SERVICE_POINT=" + request.getServicePoint();
		}
		
		if(request.getLogout() != null && request.getLogout() == true) {
			contextParam += "#LOGOUT=YES";
		}
		
		if(request.getKeepAlive() != null && request.getKeepAlive() == true) {
			contextParam += "#KEEP_ALIVE=YES";
		}
		
		try {
			result = spService.callUpdateSession(request.getSessionId(), contextParam);

			log.info("[" + currentMethodName + "] - " + callId + " - " + request.getSessionId() + " - logged in.");
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.getSessionId() + " - update failed." + "Ex: " + e);
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID + callId);
			return result;
		}
	}
	
	public Response checkUser(NEUReqCheckUser req) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Response response = new Response();
		String hash2T = null;
		String hash4T = null;
		MessageDigest md; 
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + " - " + "Hiba lépett fel a hash létrehozásakor.");
			return null;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + req.toString());
			
			if(Util.notEmptyString(req.getSsnType()) && Util.notEmptyString(req.getSsnNumber()) && Util.notEmptyString(req.getDateOfBirth())) {
				String concat = req.getSsnNumber() + req.getDateOfBirth();
				concat = concat.trim().toLowerCase();			
				md.update(concat.getBytes());
				byte[] digest = md.digest();
				hash2T = DatatypeConverter.printHexBinary(digest).toUpperCase();
			}
			
			if(Util.notEmptyString(req.getMothersName()) && Util.notEmptyString(req.getBirthName()) && Util.notEmptyString(req.getDateOfBirth()) && Util.notEmptyString(req.getPlaceOfBirth())) {
				String concat = req.getPlaceOfBirth().trim() + req.getDateOfBirth().trim() + req.getBirthName().trim() + req.getMothersName().trim();
				concat = concat.toLowerCase();	
				concat = concat.replaceAll("\\s","");
				concat = Normalizer.normalize(concat, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				md.update(concat.getBytes());
				byte[] digest = md.digest();
				hash4T = DatatypeConverter.printHexBinary(digest).toUpperCase();
			}
			
			String result = sessionRepository.callFnCheckUserWithHash(req.getId(), hash2T, hash4T);
			
			if(result == null) {
				response.setCodeAndMessage(CODE_USER_NOT_FOUND, MSG_USER_NOT_FOUND);
				log.error("[" + currentMethodName + "] - " + callId + " - " + CODE_USER_NOT_FOUND + " - " + MSG_USER_NOT_FOUND);
				return response;
			}
			
			response.setCodeAndMessage(CODE_OK, result);
			return response;
		
		} catch (NoResultException e) {
			response.setCodeAndMessage(CODE_USER_NOT_FOUND, MSG_USER_NOT_FOUND);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		} catch(Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
	}
	
	public IS4ResGetInstElementExt getUserPermission(String sessionId) {
		IS4ResGetInstElementExt response = new IS4ResGetInstElementExt();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(sessionId)) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			List<ObjGetInstElement> result = getInstElementRepository.callFnGetPermissionBySessionId(sessionId, null);
			if(result == null) {
				response.setCodeAndMessage(CODE_NO_PERMISSION, MSG_NO_PERMISSION);
				log.error("[" + currentMethodName + "] - " + callId + " - sessionId: " + sessionId + " - A felhasználónak nincs semmilyen jogosultsága");
				return response;
			} else {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setInstituteDataList(result);
				log.info("[" + currentMethodName + "] - " + callId + " - sessionId: " + sessionId + " - Sikeres jogosultság ellenőrzés.");
				return response;
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.info("[" + currentMethodName + "] - " + callId + " - sessionId: " + sessionId + " - A jogosultság ellenőrzése sikertelen volt.");
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID + callId);
			return response;
		}
	}
	
	public IS4ResGetRootSpBySession getRootSpBySession(String sessionId) {
		IS4ResGetRootSpBySession response = new IS4ResGetRootSpBySession();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(sessionId)) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		String spCode = sessionRepository.callFnGetRootSpCodeBySession(sessionId, null);
		if(spCode != null) {
			response.setCodeAndMessage(CODE_OK, MSG_OK);
			response.setSpCode(spCode);
		} else {
			response.setCodeAndMessage(-1, "Nincs jogosultsága az intézményi adminisztráció használatára!");
		}
				
		log.info("[" + currentMethodName + "]");
		return response;
	}
	
	public Response getSpBySession(String sessionId) {
		Response response = new Response();
		
		ObjSessionExt session = sessionRepository.callFnGetCurrentSession(sessionId);
		
		if(session != null && session.getCurrRoleCode() != null && session.getCurrRoleCode().contains("$")) {
			String[] split = session.getCurrRoleCode().split("\\$");
			response.setCodeAndMessage(CODE_OK, split[1]);
		}
		
		return response;
	}

}

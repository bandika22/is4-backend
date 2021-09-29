package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ReqGetInstAdminPermission;
import hu.econsult.is4.model.IS4ReqGetInstAdminRoles;
import hu.econsult.is4.model.IS4ReqGetInstAdminUserSps;
import hu.econsult.is4.model.IS4ReqGetRoles;
import hu.econsult.is4.model.IS4ReqSavePermissionToUser;
import hu.econsult.is4.model.IS4ReqSaveRoleToUser;
import hu.econsult.is4.model.IS4ReqSaveScheduleTemplates;
import hu.econsult.is4.model.IS4ResGetInstAdminPermissions;
import hu.econsult.is4.model.IS4ResGetInstAdminRoles;
import hu.econsult.is4.model.IS4ResGetInstAdminRoles2;
import hu.econsult.is4.model.IS4ResGetInstAdminUserSps;
import hu.econsult.is4.model.IS4ResGetInstUsers;
import hu.econsult.is4.model.IS4ResSaveScheduleTemplate;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.IS4ObjGetInstAdminPermissions;
import hu.econsult.is4.model.entity.IS4ObjGetInstAdminRoles;
import hu.econsult.is4.model.entity.ObjGetInstAdminRoles;
import hu.econsult.is4.model.entity.ObjGetInstAdminUserSps;
import hu.econsult.is4.model.entity.ObjGetInstAdminUsers;
import hu.econsult.is4.repository.IS4ObjGetInstAdminPermissionRepository;
import hu.econsult.is4.repository.IS4ObjGetInstAdminRolesRepository;
import hu.econsult.is4.repository.ObjGetInstAdminRolesRepository;
import hu.econsult.is4.repository.ObjGetInstAdminUserSpsReporitory;
import hu.econsult.is4.repository.ObjGetInstAdminUsersRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InstituteAdminService {
	
	private final JdbcService jdbcService;
	private final ObjGetInstAdminUsersRepository instAdminUsersRespository;
	private final ObjGetInstAdminRolesRepository instAdminRolesRespository;
	private final IS4ObjGetInstAdminRolesRepository instAdminRolesRepository;
	private final ObjGetInstAdminUserSpsReporitory instAdminUserSpsReporitory;
	private final IS4ObjGetInstAdminPermissionRepository instAdminPermissionRepository;

	@Autowired
	public InstituteAdminService(ObjGetInstAdminUsersRepository instAdminUsersRespository, ObjGetInstAdminRolesRepository instAdminRolesRespository, ObjGetInstAdminUserSpsReporitory instAdminUserSpsReporitory, JdbcService jdbcService, IS4ObjGetInstAdminPermissionRepository instAdminPermissionRepository, IS4ObjGetInstAdminRolesRepository instAdminRolesRepository) {
		super();
		this.jdbcService = jdbcService;
		this.instAdminUsersRespository = instAdminUsersRespository;
		this.instAdminRolesRespository = instAdminRolesRespository;
		this.instAdminRolesRepository = instAdminRolesRepository;
		this.instAdminUserSpsReporitory = instAdminUserSpsReporitory;
		this.instAdminPermissionRepository = instAdminPermissionRepository;
	}
	
	public IS4ResGetInstUsers getInstAdminUsers(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstUsers result = new IS4ResGetInstUsers();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<ObjGetInstAdminUsers> response = instAdminUsersRespository.getInstAdminUsers(request.getSessionId(), null);
			
			if (response != null) {
				result.setUsers(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public IS4ResGetInstAdminRoles getInstAdminRoles(IS4ReqGetRoles request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstAdminRoles result = new IS4ResGetInstAdminRoles();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<ObjGetInstAdminRoles> response = instAdminRolesRespository.getInstAdminRoles(request.getSessionId(), null, request.getUuid());
			
			if (response != null) {
				result.setRoles(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public IS4ResGetInstAdminUserSps getInstAdminUserSps(IS4ReqGetInstAdminUserSps request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstAdminUserSps result = new IS4ResGetInstAdminUserSps();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<ObjGetInstAdminUserSps> response = instAdminUserSpsReporitory.getUserSps(request.getSessionId(), null, request.getUuid());
			
			if (response != null) {
				result.setUserSps(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public Response saveRoletoUser(IS4ReqSaveRoleToUser request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getRoleList() == null) {
			response.setCodeAndMessage(CODE_MISSING_ROLE_TO_USER_LIST, MSG_MISSING_ROLE_TO_USER_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			response = jdbcService.saveRoleToUser(request.getSessionId(), request.getUuid(), request.getRoleList());
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstAdminRoles getInstAdminUserSpRoles(IS4ReqGetRoles request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstAdminRoles result = new IS4ResGetInstAdminRoles();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<ObjGetInstAdminRoles> response = instAdminRolesRespository.getInstAdminUserSpRoles(request.getSessionId(), null, request.getUuid());
			
			if (response != null) {
				result.setRoles(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public IS4ResGetInstAdminPermissions getInstAdminUserPermission(IS4ReqGetInstAdminPermission request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstAdminPermissions result = new IS4ResGetInstAdminPermissions();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<IS4ObjGetInstAdminPermissions> response = instAdminPermissionRepository.getInstAdminPermission(request.getSessionId(), null, request.getUuid(), request.getModuleRoleFk(), request.getType());
			
			if (response != null) {
				Map<String, List<IS4ObjGetInstAdminPermissions>> permission = response.stream().collect(Collectors.groupingBy(IS4ObjGetInstAdminPermissions::getPermCode));
				result.setPermissionList(permission);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public IS4ResGetInstAdminRoles2 getInstAdminUserRoles(IS4ReqGetInstAdminRoles request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		IS4ResGetInstAdminRoles2 result = new IS4ResGetInstAdminRoles2();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {
			List<IS4ObjGetInstAdminRoles> roles = instAdminRolesRepository.getInstAdminRoles(request.getSessionId(), null, request.getUuid(), request.getModuleRoleFk());
			
			if (roles != null) {
				result.setRoles(roles);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public Response savePermissionToUser(IS4ReqSavePermissionToUser request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getRoleList() == null) {
			response.setCodeAndMessage(CODE_MISSING_ROLE_TO_USER_LIST, MSG_MISSING_ROLE_TO_USER_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			response = jdbcService.saveUserPermission(request.getSessionId(), request.getUuid(), request.getModuleRoleFk(), request.getRoleList());
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
}

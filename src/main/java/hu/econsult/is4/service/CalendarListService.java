package hu.econsult.is4.service;

import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.type.ClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.spi.XMLUtil;
import hu.econsult.is4.model.GetInstCalHistory;
import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ReqAvailService;
import hu.econsult.is4.model.IS4ReqCheckUserIsAdmin;
import hu.econsult.is4.model.IS4ReqGetCalendarByAlias;
import hu.econsult.is4.model.IS4ReqGetCalendarByPatient;
import hu.econsult.is4.model.IS4ReqGetInstCal;
import hu.econsult.is4.model.IS4ReqGetSendData;
import hu.econsult.is4.model.IS4ReqModSendData;
import hu.econsult.is4.model.IS4ReqSaveSchedule;
import hu.econsult.is4.model.IS4ResAvailServices;
import hu.econsult.is4.model.IS4ResAvailServices2;
import hu.econsult.is4.model.IS4ResGetInstCal;
import hu.econsult.is4.model.IS4ResGetInstCalHistory;
import hu.econsult.is4.model.IS4ResGetSendData;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.IS4ObjAvailServices;
import hu.econsult.is4.model.entity.IS4ObjCalendarDetail;
import hu.econsult.is4.model.entity.IS4ObjGetInstCal;
import hu.econsult.is4.model.entity.IS4ObjGetInstCalHistory;
import hu.econsult.is4.model.entity.ObjGetInstElement;
import hu.econsult.is4.model.entity.ObjSendData;
import hu.econsult.is4.model.xml.CalendarDetail;
import hu.econsult.is4.model.xml.GetCalendarHistoryData;
import hu.econsult.is4.model.xml.Log;
import hu.econsult.is4.model.xml.LogData;
import hu.econsult.is4.model.xml.ModifyAppointmentData;
import hu.econsult.is4.repository.IS4ObjAppointmentRepository;
import hu.econsult.is4.repository.IS4ObjAvailServicesRepository;
import hu.econsult.is4.repository.IS4ObjCalendarDetailRepository;
import hu.econsult.is4.repository.Is4ObjGetInstCalHistoryRepository;
import hu.econsult.is4.repository.Is4ObjGetInstCalRepository;
import hu.econsult.is4.repository.ObjSendDataRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

import static hu.econsult.is4.util.ErrorCodes.*;

@Slf4j
@Service
public class CalendarListService {

	private final SpService spService;
	private final ObjSendDataRepository sendDataRepository;
	private final Is4ObjGetInstCalRepository instCalRepository;
	private final IS4ObjAppointmentRepository appointmentRepository;
	private final IS4ObjAvailServicesRepository availServicesRepository;
	private final IS4ObjCalendarDetailRepository calendarDetailRepository;
	private final Is4ObjGetInstCalHistoryRepository instCalHistoryRepository;
	
	
	@Autowired
	public CalendarListService(IS4ObjAvailServicesRepository availServicesRepository, Is4ObjGetInstCalRepository instCalRepository, SpService spService, ObjSendDataRepository sendDataRepository, Is4ObjGetInstCalHistoryRepository instCalHistoryRepository, IS4ObjCalendarDetailRepository calendarDetailRepository, IS4ObjAppointmentRepository appointmentRepository) {
		super();
		this.spService = spService;
		this.instCalRepository = instCalRepository;
		this.sendDataRepository = sendDataRepository;
		this.appointmentRepository = appointmentRepository;
		this.availServicesRepository = availServicesRepository;
		this.calendarDetailRepository = calendarDetailRepository;
		this.instCalHistoryRepository = instCalHistoryRepository;
	}

	public IS4ResAvailServices2 getAvailServicesCal(IS4ReqAvailService request) {
		
		IS4ResAvailServices2 result = new IS4ResAvailServices2();
		List<IS4ObjAvailServices> response = null;
		HashSet<ObjGetInstElement> etList = new HashSet<>();
		HashSet<ObjGetInstElement> spList = new HashSet<>();
		HashSet<ObjGetInstElement> drList = new HashSet<>();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + request.toString() + " - " + result.toString());
			return result;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - request: " + request.toString());
			response = availServicesRepository.callFnGetAvailService(request.getSessionId(), null, request.getSpId(), request.getEtId(), request.getDoctorId());	
			
			if (response != null) {
				for(IS4ObjAvailServices element: response) {
					if(element.getEtID() != null){
						etList.add(new ObjGetInstElement(element.getEtID(), element.getEtName()));
					}
					if(element.getSpID() != null){
						spList.add(new ObjGetInstElement(element.getSpID(), element.getSpName()));
					}
					if(element.getDoctorID() != null){
						drList.add(new ObjGetInstElement(element.getDoctorID(), element.getDoctorName()));
					}
				}
				result.setEtList(etList);
				result.setSpList(spList);
				result.setDrList(drList);
				
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - request: " + request.toString());
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString() + " - request: " + request.toString());
			return result;
		}		
	}
	
	public IS4ResGetInstCal getInstCalendar(IS4ReqGetInstCal request) {
		IS4ResGetInstCal result = new IS4ResGetInstCal();
		List<IS4ObjGetInstCal> response = null;
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {

			response = instCalRepository.callFnGetInstCalendar(request.getSessionId(), null, request.getSpId(), request.getEtId(), request.getDoctorId(), request.getDateFrom(), request.getDateTo(), request.getStatus());	
			
			if (response != null) {
				result.setCalendarList(response);
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
	
	public IS4ResGetInstCalHistory getInstCalendarHistory(IS4ReqGetInstCal request) {
		IS4ResGetInstCalHistory result = new IS4ResGetInstCalHistory();
		List<IS4ObjGetInstCalHistory> response = null;
		GetCalendarHistoryData calData = new GetCalendarHistoryData();
		GetInstCalHistory historyDto;
		List<GetInstCalHistory> histories = new ArrayList<GetInstCalHistory>();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {

			response = instCalHistoryRepository.callFnGetInstCalendarHistory(request.getSessionId(), null, request.getSpId(), request.getEtId(), request.getDoctorId(), request.getDateFrom(), request.getDateTo(), request.getStatus());	
			
			if (response != null) {
				for(IS4ObjGetInstCalHistory cal : response) {
					if(cal.getDetail() != null) {
						calData = Util.unmarshallXml(cal.getDetail(), GetCalendarHistoryData.class);
						if(calData.getLogData() != null) {
							for(Log log : calData.getLogData().getLog()) {
								historyDto = new GetInstCalHistory();
								
								historyDto.setContingent(cal.getContingent());
								historyDto.setDateFrom(cal.getDateFrom());
								historyDto.setDateTo(cal.getDateTo());
								historyDto.setDoctorName(cal.getDoctorName());
								historyDto.setEventTypeName(cal.getEventTypeName());
								historyDto.setId(cal.getId());
								historyDto.setServicePointName(cal.getServicePointName());
								historyDto.setStatus(cal.getStatus());
								historyDto.setPatData(calData.getPatData());
								historyDto.setChangeType(log.getChangeType());
								historyDto.setLastModTime(log.getLastModTime());
								historyDto.setLastModUser(log.getLastModUser());
								if(cal.getHidePatient() != null) {
									if(cal.getHidePatient() == 1) {
										historyDto.getPatData().setAlias("*********");
										historyDto.setHidePatient(true);
									} else {
										historyDto.setHidePatient(false);
									}
								} else {
									historyDto.setHidePatient(null);
								}
								
								histories.add(historyDto);
							}
						} else {
							historyDto = new GetInstCalHistory();
							
							historyDto.setContingent(cal.getContingent());
							historyDto.setDateFrom(cal.getDateFrom());
							historyDto.setDateTo(cal.getDateTo());
							historyDto.setDoctorName(cal.getDoctorName());
							historyDto.setEventTypeName(cal.getEventTypeName());
							historyDto.setId(cal.getId());
							historyDto.setServicePointName(cal.getServicePointName());
							historyDto.setStatus(cal.getStatus());
							historyDto.setPatData(calData.getPatData());
							historyDto.setChangeType(null);
							historyDto.setLastModTime(null);
							historyDto.setLastModUser(null);
							
							if(cal.getHidePatient() != null) {
								if(cal.getHidePatient() == 1) {
									historyDto.getPatData().setAlias("*********");
									historyDto.setHidePatient(true);
								} else {
									historyDto.setHidePatient(false);
								}
							} else {
								historyDto.setHidePatient(null);
							}
							
							histories.add(historyDto);
						}
					} else {
						historyDto = new GetInstCalHistory();
						
						historyDto.setContingent(cal.getContingent());
						historyDto.setDateFrom(cal.getDateFrom());
						historyDto.setDateTo(cal.getDateTo());
						historyDto.setDoctorName(cal.getDoctorName());
						historyDto.setEventTypeName(cal.getEventTypeName());
						historyDto.setId(cal.getId());
						historyDto.setServicePointName(cal.getServicePointName());
						historyDto.setStatus(cal.getStatus());
						historyDto.setPatData(null);
						historyDto.setChangeType(null);
						historyDto.setLastModTime(null);
						historyDto.setLastModUser(null);
						
						histories.add(historyDto);
					}
				}
				result.setCalendarList(histories);
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
	
	public Response deleteCalendar(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("DEL")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getCalIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), null, req.getMode(), req.getDetail(), req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response doneCalendar(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("DONE")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getCalIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), null, req.getMode(), req.getDetail(), req.getReason(), req.getCalModReasonCode(), req.getMedicalDate());
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response lockSchedule(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("LOCK")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getScheduleIdList()) && Util.isEmptyString(req.getCalIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), req.getScheduleIdList(), req.getMode(), req.getDetail(), req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response unlockSchedule(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("UNLOCK")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getScheduleIdList()) && Util.isEmptyString(req.getCalIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), req.getScheduleIdList(), req.getMode(), req.getDetail(), req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response freeCalendar(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("FREE")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getCalIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), null, req.getMode(), req.getDetail(), req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response modifyCalendar(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("MOD")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(Util.isEmptyString(req.getCalIdList()) && Util.isEmptyString(req.getScheduleIdList())) {
			response.setCodeAndMessage(-208, "Nincs kiválasztva előjegyzés vagy nincs megadva új időont");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			IS4ObjCalendarDetail calendarDetail = calendarDetailRepository.callFnIS4GetCalendarDetail(req.getCalId());
			Clob clob = appointmentRepository.callFnGetScheduleDetail(req.getSessionId(), null, req.getScheduleId());
			String scheduleDetail = ClobType.INSTANCE.toString((Clob) clob);

			CalendarDetail calObject = Util.unmarshallXml(calendarDetail.getDetail(), CalendarDetail.class);
			ModifyAppointmentData scheduleObject = Util.unmarshallXml(scheduleDetail, ModifyAppointmentData.class);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			String dateString = format.format(new Date());
			
			calObject.getAppData().setCreateDate(dateString);
			calObject.getAppData().setCreatorSp(scheduleObject.getCreatorSp());
			calObject.getAppData().setCreatorUser(scheduleObject.getCreatorUser());
			calObject.getAppData().setProviderSp(scheduleObject.getProviderSp());
			calObject.getAppData().setProviderUser(scheduleObject.getProviderUser());
			calObject.getAppData().setScheduleId(req.getScheduleId().toString());
			
			String stringDetail = Util.createXmlAsString(calObject);
			
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, req.getCalIdList(), req.getScheduleIdList(), req.getMode(), stringDetail, req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response checkUserHasPermission(IS4ReqCheckUserIsAdmin req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if (Util.isEmptyString(req.getPermissionType())) {
			response.setCodeAndMessage(-210, "A jogosultság típus megadása kötelező!");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(req.getSpId() == null && req.getEtId() == null) {
			response.setCodeAndMessage(-209, "Szolgáltatási pont vagy szolgáltatási típus megadása kötelező!");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		try {
			Integer result = instCalRepository.callFnCheckUserHasPermission(req.getSessionId(), null, req.getSpId(), req.getEtId(), req.getPermissionType());

			if(result == null) {
				response.setCodeAndMessage(CODE_SP_CALL_FAILED, MSG_SP_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			} else if(result == 0) {
				response.setCodeAndMessage(0, "Nincs a megadott időpont felszabadításához megfelelő jogosultság!");
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			} else {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				log.info("[" + currentMethodName + " ]" + " CODE:" + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
				return response;
			}
		} catch (Exception e) {
			response.setCode(CODE_SP_CALL_FAILED);
			response.setMessage(MSG_SP_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public IS4ResGetInstCal getCalendarByPatient(IS4ReqGetCalendarByPatient request) {
		IS4ResGetInstCal result = new IS4ResGetInstCal();
		List<IS4ObjGetInstCal> response = null;
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		try {

			response = instCalRepository.callFnGetCalendarByPatient(request.getSessionId(), null, request.getPatientId(), request.getDateFrom(), request.getDateTo());	
			
			if (response != null) {
				result.setCalendarList(response);
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
		
	public IS4ResGetInstCal getCalendarByAlias(IS4ReqGetCalendarByAlias request) {
		IS4ResGetInstCal result = new IS4ResGetInstCal();
		List<IS4ObjGetInstCal> response = null;
			
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
			
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
			
		try {

			response = instCalRepository.callFnGetCalendarByAlias(request.getSessionId(), null, request.getAlias(), request.getDateFrom(), request.getDateTo());	
				
			if (response != null) {
				result.setCalendarList(response);
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
	
	public IS4ResGetSendData getSendData(IS4ReqGetSendData request) {
		IS4ResGetSendData result = new IS4ResGetSendData();
			
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
			
		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
			
		try {

			ObjSendData response = sendDataRepository.getSendData(request.getSessionId(), null, request.getCalId());
				
			if (response != null) {
				result.setSendData(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}	
	}
	
	public Response modifyPatData(IS4ReqModSendData req) {
		IS4ResGetSendData result = new IS4ResGetSendData();
			
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
			
		if (Util.isEmptyString(req.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
			
		try {

			Response response = spService.callModifySendData(req);
				
			if (response != null) {
				result.setCodeAndMessage(response.getCode(), response.getMessage());
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
}

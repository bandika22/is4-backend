package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.CODE_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_ERROR_GET_FREEAPPS;
import static hu.econsult.is4.util.ErrorCodes.CODE_ERROR_GET_SCHEDULE_COLORS;
import static hu.econsult.is4.util.ErrorCodes.CODE_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.CODE_INVALID_HEADER;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PARAM;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PARAMETERS;
import static hu.econsult.is4.util.ErrorCodes.CODE_NO_RESULT;
import static hu.econsult.is4.util.ErrorCodes.CODE_OK;
import static hu.econsult.is4.util.ErrorCodes.CODE_UNKNOWN;
import static hu.econsult.is4.util.ErrorCodes.MSG_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_ERROR_GET_FREEAPPS;
import static hu.econsult.is4.util.ErrorCodes.MSG_ERROR_GET_SCHEDULE_COLORS;
import static hu.econsult.is4.util.ErrorCodes.MSG_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.MSG_INVALID_HEADER;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_DATE;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_PARAMETERS;
import static hu.econsult.is4.util.ErrorCodes.MSG_NO_RESULT;
import static hu.econsult.is4.util.ErrorCodes.MSG_OK;
import static hu.econsult.is4.util.ErrorCodes.MSG_UNKNOWN;
import static hu.econsult.is4.util.ErrorCodes.MSG_UNKNOWN_ID;

import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.hibernate.type.ClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ReqAppointment;
import hu.econsult.is4.model.IS4ReqAvailService;
import hu.econsult.is4.model.IS4ReqCheckContList;
import hu.econsult.is4.model.IS4ReqCheckSameCal;
import hu.econsult.is4.model.IS4ReqGetCalendarDetail;
import hu.econsult.is4.model.IS4ReqGetInstData;
import hu.econsult.is4.model.IS4ReqGetScheduleDetail;
import hu.econsult.is4.model.IS4ReqSaveSchedule;
import hu.econsult.is4.model.IS4ReqScheduleColor;
import hu.econsult.is4.model.IS4ResAppointment;
import hu.econsult.is4.model.IS4ResAvailServices;
import hu.econsult.is4.model.IS4ResAvailServices2;
import hu.econsult.is4.model.IS4ResGetCalendarDetail;
import hu.econsult.is4.model.IS4ResGetInstElement;
import hu.econsult.is4.model.IS4ResGetInstUser;
import hu.econsult.is4.model.IS4ResGetInstituteData;
import hu.econsult.is4.model.IS4ResScheduleColor;
import hu.econsult.is4.model.NEUReqGetDoctorToServicePoint;
import hu.econsult.is4.model.NEUResGetDoctorToServicePoint;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.IS4ObjAppointment;
import hu.econsult.is4.model.entity.IS4ObjAvailServices;
import hu.econsult.is4.model.entity.IS4ObjCalendarDetail;
import hu.econsult.is4.model.entity.IS4ObjGetInstituteData;
import hu.econsult.is4.model.entity.ObjGetInstElement;
import hu.econsult.is4.model.entity.ObjScheduleColor;
import hu.econsult.is4.model.xml.CalendarDetail;
import hu.econsult.is4.model.xml.ModifyAppointmentData;
import hu.econsult.is4.repository.IS4ObjAppointmentRepository;
import hu.econsult.is4.repository.IS4ObjAvailServicesRepository;
import hu.econsult.is4.repository.IS4ObjCalendarDetailRepository;
import hu.econsult.is4.repository.IS4ObjGetInstElementRepository;
import hu.econsult.is4.repository.IS4ObjScheduleColorRepository;
import hu.econsult.is4.repository.InstituteDataRepository;
import hu.econsult.is4.repository.ObjDoctorToSpRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentService {
	
	private final SpService spService;
	private final InstituteDataRepository instDataReporitory;
	private final ObjDoctorToSpRepository doctorToSpRepository;
	private final IS4ObjAppointmentRepository appointmentRepository;
	private final IS4ObjGetInstElementRepository instElementRepository;
	private final IS4ObjAvailServicesRepository availServicesRepository;
	private final IS4ObjScheduleColorRepository scheduleColorRepository;
	private final IS4ObjCalendarDetailRepository calendarDetailRepository;
	
	@Autowired
	public AppointmentService(IS4ObjGetInstElementRepository instElementRepository, IS4ObjAvailServicesRepository availServicesRepository, IS4ObjScheduleColorRepository scheduleColorRepository, IS4ObjAppointmentRepository appointmentRepository, SpService spService, IS4ObjCalendarDetailRepository calendarDetailRepository, ObjDoctorToSpRepository doctorToSpRepository, InstituteDataRepository instDataReporitory) {
		this.instDataReporitory = instDataReporitory;
		this.instElementRepository = instElementRepository;
		this.availServicesRepository = availServicesRepository;
		this.scheduleColorRepository = scheduleColorRepository;
		this.appointmentRepository = appointmentRepository;
		this.spService = spService;
		this.calendarDetailRepository = calendarDetailRepository;
		this.doctorToSpRepository = doctorToSpRepository;
	}

	public IS4ResGetInstUser getInstDoctor(IS4ReqGetInstData request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

		IS4ResGetInstUser result = new IS4ResGetInstUser();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType() + " - " + result.toString());
			return result;
		}

		try {
			log.info("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			List<ObjGetInstElement> response = instElementRepository.callFnGetInstDocor(request.getSessionId(), null, request.getPermissionType());
			
			if(response == null) {
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if(response != null && response.size() == 0){
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if (response.get(0).getId() > 0) {
				result.setUserList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString() + " - sessionID: " + request.getSessionId() + "- permissionType1: " + request.getPermissionType());
			return result;
		}

	}

	public IS4ResGetInstElement getInstEt(IS4ReqGetInstData request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

		IS4ResGetInstElement result = new IS4ResGetInstElement();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType() + " - " + result.toString());
			return result;
		}

		try {
			log.info("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			
			List<ObjGetInstElement> response = instElementRepository.callFnGetInstEt(request.getSessionId(), null, request.getPermissionType());
			
			if(response == null) {
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if(response != null && response.size() == 0){
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if (response.get(0).getId() > 0) {
				result.setElementList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			return result;
		}
	}

	public IS4ResGetInstituteData getInstSp(IS4ReqGetInstData request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

		IS4ResGetInstituteData result = new IS4ResGetInstituteData();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType() + " - " + result.toString());
			return result;
		}

		try {
			log.info("[" + currentMethodName + "] - " + callId + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			List<IS4ObjGetInstituteData> response = instDataReporitory.callFnGetInstSp(request.getSessionId(), null, request.getPermissionType());
			
			if(response == null) {
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if(response != null && response.size() == 0){
				result.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			
			if (response.get(0).getId() > 0) {
				result.setInstituteDataList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString() + " - sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			return result;
		}
	}
	
	public IS4ResAvailServices2 getAvailServices(IS4ReqAvailService request) {
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
	
	public IS4ResScheduleColor getScheduleColorList(IS4ReqScheduleColor request) {
		IS4ResScheduleColor res = new IS4ResScheduleColor();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

		if (Util.isEmptyString(request.getSessionId())) {
			res.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + request.toString() + " - " + res.toString());
			return res;
		}
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			List<ObjScheduleColor> result = scheduleColorRepository.callFnInstituteScheduleLoadIS4(request.getSessionId(), request.getServicePointId(), request.getEventTypeId(), request.getDoctorId(), request.getDateFrom(), request.getDateTo());

			if (result != null) {
				res.setObjScheduleColor(result);
				log.info("[getScheduleColorList] - servicePointId: " + request.getServicePointId()
						+ " | eventTypeId: " + request.getEventTypeId()
						+ " | doctorId: " + request.getDoctorId()
						+ " | FN_INSTITUTE_SCHEDULE_LOAD resultset size: " + res.getObjScheduleColor().size());
				res.setCode(CODE_OK);
				res.setMessage(MSG_OK);
			} else {
				res.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + res.toString());
			}
			return res;
		} catch (Exception e) {
			res.setCode(CODE_ERROR_GET_SCHEDULE_COLORS);
			res.setMessage(MSG_ERROR_GET_SCHEDULE_COLORS);
			log.error("[" + currentMethodName + "] - " + callId + " - " + res.toString() + "\n" + e.toString());
			return res;
		}
	}
	
	public IS4ResAppointment getAppointmentList(IS4ReqAppointment req) {
		IS4ResAppointment res = new IS4ResAppointment();
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

		if (Util.isEmptyString(req.getDateFrom()) || Util.isEmptyString(req.getDateTo())) {
			res.setCodeAndMessage(CODE_MISSING_PARAM, MSG_MISSING_DATE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + req.toString() + " - " + res.toString());
			return res;
		}
		
		if (Util.isEmptyString(req.getSessionId())) {
			res.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + req.toString() + res.toString());
			return res;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + req.toString());

			List<IS4ObjAppointment> resultSet = appointmentRepository.callFnGetScheduleList(req.getSessionId(), null, req.getSpId(), req.getEtId(), req.getDoctorId(), req.getDateFrom(),req.getDateTo(), req.getMode());

			if (resultSet != null) {
				res.setAppointmentList(resultSet);
				log.info("[getAppointmentList] -  dateFrom: "
						+ req.getDateFrom() + " | dateTo: " + req.getDateTo() + " | eventId: " + req.getEtId()
						+ " | servicePointId: " + req.getSpId() + " | locale: "
						+ " | FN_INSTITUTE_SCHEDULE resultset size: " + res.getAppointmentList().size());
				res.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				res.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + res.toString() + "request: " + req.toString());
			}
			return res;
		} catch (Exception e) {
			res.setCode(CODE_ERROR_GET_FREEAPPS);
			res.setMessage(MSG_ERROR_GET_FREEAPPS);
			log.error("[" + currentMethodName + "] - " + callId + " - " + res.toString() + "\n" + e.toString());
			return res;
		}
	}
	
	public Response saveSchedule(IS4ReqSaveSchedule req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + req.toString() +  " - " + response.toString());
			return response;
		}

		if(!req.getMode().equals("NEW")) {
			response.setCodeAndMessage(-206, "A mode param megadása kötelező vagy értéke nem megfelelő.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + req.toString() +  " - " + response.toString());
			return response;
		}
		if(!req.getMode().equals("NEW") && Util.isEmptyString(req.getScheduleIdList())) {
			response.setCodeAndMessage(-207, "Nincs kiválasztva időpont.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + req.toString() +  " - " + response.toString());
			return response;
		}

		try {
			Clob clob = appointmentRepository.callFnGetScheduleDetail(req.getSessionId(), null, new Long(req.getXmlDetail().getAppData().getScheduleId()));
			String scheduleDetail = ClobType.INSTANCE.toString((Clob) clob);
			
			ModifyAppointmentData scheduleObject = Util.unmarshallXml(scheduleDetail, ModifyAppointmentData.class);
			
			req.getXmlDetail().getAppData().setCreatorUser(scheduleObject.getCreatorUser());
			req.getXmlDetail().getAppData().setCreatorSp(scheduleObject.getCreatorSp());
			req.getXmlDetail().getAppData().setProviderSp(scheduleObject.getProviderSp());
			req.getXmlDetail().getAppData().setProviderUser(scheduleObject.getProviderUser());
			
			String xmlDetail = Util.createXmlAsString(req.getXmlDetail());
			Map<String, Object> result = spService.callPSaveSchedule(req.getSessionId(), null, null,req.getScheduleIdList(), req.getMode(), xmlDetail, req.getReason(), req.getCalModReasonCode(), null);
			Integer code = (Integer) result.get("p_cal_id");
			String message = (String) result.get("p_msg");

			response.setCode(code);
			response.setMessage(message);
			log.info("[saveSchedule] Mentes kod: " + code + " Message: " + message + " | SessionId: " + req.getSessionId());

			return response;
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response checkSameCal(IS4ReqCheckSameCal req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if (Util.isEmptyString(req.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		if(req.getPatHisId() == null && Util.isEmptyString(req.getPatNauId()) && Util.isEmptyString(req.getAlias())) {
			response.setCodeAndMessage(-207, "Kötelező megadni egy páciens azonosítására szolgáló paramétert!");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Integer result = appointmentRepository.callCheckSameCal(req.getSessionId(), req.getContextParam(), req.getPatHisId(), req.getPatNauId(), req.getAlias(), req.getServiceId(), req.getDate());
			if(result == null) {
				response.setCode(CODE_FN_CALL_FAILED);
				response.setMessage(MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			}
			if(result == 0) {
				response.setCode(result);
				response.setMessage(MSG_OK);
				log.info("[checkSameCal] Mentes kod: " + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
				return response;
			}
			if(result == 1) {
				response.setCode(result);
				response.setMessage("Ennek a páciensnek erre a napra már van foglalása");
				log.info("[checkSameCal] Mentes kod: " + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
				return response;
			}
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
		return response;
	}
	
	public Response getScheduleDetail(IS4ReqGetScheduleDetail req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if(req.getScheduleId() == null) {
			response.setCodeAndMessage(-207, "Nincs megadva scheduleId!");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Clob result = appointmentRepository.callFnGetScheduleDetail(req.getSessionId(), null, req.getScheduleId());
			String message = ClobType.INSTANCE.toString((Clob) result);

			if(result == null) {
				response.setCode(CODE_FN_CALL_FAILED);
				response.setMessage(MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			}
			response.setCode(CODE_OK);
			response.setMessage(message);
			log.info("[getScheduleDetail] Mentes kod: " + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
			return response;
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}
	}
	
	public Response checkContingentList(IS4ReqCheckContList req) {
		Response response = new Response();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if(Util.isEmptyString(req.getScheduleList())) {
			response.setCode(-207);
			response.setMessage("Nincs megadva scheduleId!");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		Integer result = appointmentRepository.callFnCheckContingentList(req.getSessionId(), req.getContextParam(), req.getScheduleList());
		if(result == null) {
			response.setCode(CODE_FN_CALL_FAILED);
			response.setMessage(MSG_FN_CALL_FAILED);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			if(result == 0) {
				response.setCode(0);
				response.setMessage("A kiválasztott időponthoz nincs az adott felhasználónak jogosultsága foglalni!");
				log.info("[checkContingentList] Code: " + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
				return response;
			} else if (result > 0) {
				response.setCode(CODE_OK);
				response.setMessage(MSG_OK);
				log.info("[checkContingentList] Code: " + response.getCode() + " Message: " + response.getMessage() + " | SessionId: " + req.getSessionId());
				return response;
			} else if(result < 0) {
				response.setCode(CODE_FN_CALL_FAILED);
				response.setMessage(MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			}
			return response;
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}		
	} 
	
	public IS4ResGetCalendarDetail getCalendarDetail(IS4ReqGetCalendarDetail req) {
		IS4ResGetCalendarDetail response = new IS4ResGetCalendarDetail();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		if(req.getCalId() == null) {
			response.setCode(-208);
			response.setMessage("Nincs kiválasztva előjegyzés!");
			response.setCalDetail(null);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		try {
			IS4ObjCalendarDetail result = calendarDetailRepository.callFnIS4GetCalendarDetail(req.getCalId());
			
			if(result == null) {
				response.setCode(-1);
				response.setMessage("Üres az eredmény");
				response.setCalDetail(null);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			}
			
			CalendarDetail detail = new CalendarDetail();
			
			if(result.getDetail() != null) {
				detail =  Util.unmarshallXml(result.getDetail(), CalendarDetail.class);
				response.setName(detail.getPatData().getAlias());
			}
			response.setCodeAndMessage(CODE_OK, MSG_OK);
			response.setCalDetail(result);
			return response;
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			response.setCalDetail(null);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
			return response;
		}		
	} 
	
	public IS4ResGetInstElement getAllBno() {
		IS4ResGetInstElement result = new IS4ResGetInstElement();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		try {
			List<ObjGetInstElement> response = instElementRepository.callFnAllBno();
			result.setCodeAndMessage(CODE_OK, MSG_OK);
			result.setElementList(response);
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}		
	} 
	
	public NEUResGetDoctorToServicePoint getDoctorToServicePoint(NEUReqGetDoctorToServicePoint req) {
		NEUResGetDoctorToServicePoint response = new NEUResGetDoctorToServicePoint();
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();

		try {
		
			if(Util.isEmptyString(req.getSealNumber()) && Util.isEmptyString(req.getSpCode())) {
				response.setCodeAndMessage(CODE_MISSING_PARAMETERS, MSG_MISSING_PARAMETERS);
				return response;
			}
						
			response.setDoctors(doctorToSpRepository.callFnGetDoctor2Sp(req.getSealNumber(), req.getSpCode()));
			
			if(response.getDoctors() == null) {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				return response;
			}
			
			response.setCodeAndMessage(CODE_OK, MSG_OK);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN_ID + callId);
			return response;
		}
	}
	

}

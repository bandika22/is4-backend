package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.CODE_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.CODE_INVALID_PERMISSION_TYPE;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_CONTINGENT_GROUP_NAME;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_CONTINGENT_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_DATE_FROM;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_DATE_TO;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_DELETE_SCHEDULE;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_EVENT_TYPE_FK;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_HEADER_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_NAME;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PARAM;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PERMISSION_TYPE;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SCHEDULE_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SCHEDULE_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SCHEDULE_STATUS;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SERVICE_FK;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SERVICE_POINT_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_SPECIAL_DAY_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_TEMPLATE_DETAIL_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_TEMPLATE_DETAIL_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_TEMPLATE_ID;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_TEMPLATE_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_TEMPLATE_LIST;
import static hu.econsult.is4.util.ErrorCodes.CODE_NO_RESULT;
import static hu.econsult.is4.util.ErrorCodes.CODE_OK;
import static hu.econsult.is4.util.ErrorCodes.CODE_UNKNOWN;
import static hu.econsult.is4.util.ErrorCodes.MSG_EMPTY_SESSION_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.MSG_INVALID_PERMISSION_TYPE;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_CONTINGENT_GROUP_NAME;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_CONTINGENT_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_DATE_FROM;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_DATE_TO;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_DELETE_SCHEDULE;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_EVENT_TYPE_FK;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_HEADER_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_NAME;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_PARAMETERS;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_PERMISSION_TYPE;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SCHEDULE_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SCHEDULE_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SCHEDULE_STATUS;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SERVICE_FK;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SERVICE_POINT_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_SPECIAL_DAY_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TEMPLATE_DETAIL_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TEMPLATE_DETAIL_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TEMPLATE_ID;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TEMPLATE_ID_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TEMPLATE_LIST;
import static hu.econsult.is4.util.ErrorCodes.MSG_NO_RESULT;
import static hu.econsult.is4.util.ErrorCodes.MSG_OK;
import static hu.econsult.is4.util.ErrorCodes.MSG_UNKNOWN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ReqDeleteContingentGroup;
import hu.econsult.is4.model.IS4ReqDeleteScheduleList;
import hu.econsult.is4.model.IS4ReqDeleteSpecialDay;
import hu.econsult.is4.model.IS4ReqDeleteTemplate;
import hu.econsult.is4.model.IS4ReqDeleteTemplateHeader;
import hu.econsult.is4.model.IS4ReqGenerateScheduleByTemplate;
import hu.econsult.is4.model.IS4ReqGenerateSpecialSchedule;
import hu.econsult.is4.model.IS4ReqGetContingentDetail;
import hu.econsult.is4.model.IS4ReqGetGeneratedSchedulesByTemplate;
import hu.econsult.is4.model.IS4ReqGetInstData;
import hu.econsult.is4.model.IS4ReqGetInstWorkSchedule;
import hu.econsult.is4.model.IS4ReqGetScheduleLoadByTemplate;
import hu.econsult.is4.model.IS4ReqGetScheduleTempDetailList;
import hu.econsult.is4.model.IS4ReqGetScheduleTemplateHeaders;
import hu.econsult.is4.model.IS4ReqGetScheduleTemplates;
import hu.econsult.is4.model.IS4ReqGetSpecialDays;
import hu.econsult.is4.model.IS4ReqGetTemplateCont;
import hu.econsult.is4.model.IS4ReqGetTemplateDoc;
import hu.econsult.is4.model.IS4ReqModifySchedulesDetail;
import hu.econsult.is4.model.IS4ReqSaveContingentDetail;
import hu.econsult.is4.model.IS4ReqSaveContingentGroup;
import hu.econsult.is4.model.IS4ReqSaveScheduleTemplateDetails;
import hu.econsult.is4.model.IS4ReqSaveScheduleTemplates;
import hu.econsult.is4.model.IS4ReqSaveSpecialDay;
import hu.econsult.is4.model.IS4ResGenerateScheduleByTemplate;
import hu.econsult.is4.model.IS4ResGenerateSpecialSchedule;
import hu.econsult.is4.model.IS4ResGetContingentDetail;
import hu.econsult.is4.model.IS4ResGetGeneratedSchedulesByTemplate;
import hu.econsult.is4.model.IS4ResGetInstElement;
import hu.econsult.is4.model.IS4ResGetInstElementExt;
import hu.econsult.is4.model.IS4ResGetInstUser;
import hu.econsult.is4.model.IS4ResGetInstituteData;
import hu.econsult.is4.model.IS4ResGetScheduleTempDetailList;
import hu.econsult.is4.model.IS4ResGetScheduleTemplateHeaders;
import hu.econsult.is4.model.IS4ResGetScheduleTemplates;
import hu.econsult.is4.model.IS4ResGetSpecialDays;
import hu.econsult.is4.model.IS4ResGetTemplateCont;
import hu.econsult.is4.model.IS4ResGetWorkSchedule;
import hu.econsult.is4.model.IS4ResSaveScheduleTemplate;
import hu.econsult.is4.model.IS4ResSaveSpecialDay;
import hu.econsult.is4.model.IS4ResScheduleColor;
import hu.econsult.is4.model.IS4SimpleObjScheduleTemplateDetail;
import hu.econsult.is4.model.IS4TemplateDetailContingent;
import hu.econsult.is4.model.IS4TemplateDetailDoctor;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.IS4ObjGetContingentDetails;
import hu.econsult.is4.model.entity.IS4ObjGetInstUser;
import hu.econsult.is4.model.entity.IS4ObjGetInstWorkSchedule;
import hu.econsult.is4.model.entity.IS4ObjGetInstituteData;
import hu.econsult.is4.model.entity.IS4ObjGetSpecialDay;
import hu.econsult.is4.model.entity.ObjGetGeneratedSchedulesByTemplate;
import hu.econsult.is4.model.entity.ObjGetInstElement;
import hu.econsult.is4.model.entity.ObjGetScheduleTemplateDetailList;
import hu.econsult.is4.model.entity.ObjGetScheduleTemplateHeaders;
import hu.econsult.is4.model.entity.ObjGetScheduleTemplates;
import hu.econsult.is4.model.entity.ObjGetTemplateCont;
import hu.econsult.is4.model.entity.ObjScheduleColor;
import hu.econsult.is4.repository.ContigentDetailRepository;
import hu.econsult.is4.repository.GeneratedSchedulesByTemplateRepository;
import hu.econsult.is4.repository.IS4ObjGetInstElementRepository;
import hu.econsult.is4.repository.IS4ObjGetInstUserRepository;
import hu.econsult.is4.repository.IS4ObjGetSpecialDayRepository;
import hu.econsult.is4.repository.IS4ObjScheduleColorRepository;
import hu.econsult.is4.repository.InstituteDataRepository;
import hu.econsult.is4.repository.IS4ObjGetWorkScheduleRepository;
import hu.econsult.is4.repository.ScheduleTemplateDetailListRepository;
import hu.econsult.is4.repository.ScheduleTemplatesRepository;
import hu.econsult.is4.repository.TemplateContRepository;
import hu.econsult.is4.repository.ScheduleTemplateHeadersRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {


	private final SpService spService;
	private final JdbcService jdbcService;
	private final TemplateContRepository templateContRepository;
	private final IS4ObjGetInstUserRepository instUserRepository;
	private final InstituteDataRepository instituteDataRepository;
	private final IS4ObjGetInstElementRepository elementRepository;
	private final ContigentDetailRepository contigentDetailRepository;
	private final IS4ObjGetSpecialDayRepository getSpecialDayRepository;
	private final IS4ObjScheduleColorRepository scheduleColorRepository;
	private final IS4ObjGetWorkScheduleRepository workScheduleRepository; 
	private final ScheduleTemplatesRepository scheduleTemplatesRepository;
	private final ScheduleTemplateHeadersRepository templateHeadersRepository;
	private final ScheduleTemplateDetailListRepository tempDetailListRepository;
	private final GeneratedSchedulesByTemplateRepository schedulesByTemplateRepository;

	@Autowired
	public AdminService(ScheduleTemplatesRepository scheduleTemplatesRepository, IS4ObjGetInstElementRepository elementRepository, TemplateContRepository templateContRepository, IS4ObjGetInstUserRepository instUserRepository, ScheduleTemplateHeadersRepository templateHeadersRepository, ScheduleTemplateDetailListRepository tempDetailListRepository, SpService spService, GeneratedSchedulesByTemplateRepository schedulesByTemplateRepository, InstituteDataRepository instituteDataRepository, IS4ObjScheduleColorRepository scheduleColorRepository, IS4ObjGetSpecialDayRepository getSpecialDayRepository, JdbcService jdbcService, ContigentDetailRepository contigentDetailRepository, IS4ObjGetWorkScheduleRepository workScheduleRepository) {
		super();
		this.spService = spService;
		this.jdbcService = jdbcService;
		this.instUserRepository = instUserRepository;
		this.templateContRepository = templateContRepository;
		this.instituteDataRepository = instituteDataRepository;
		this.contigentDetailRepository = contigentDetailRepository;
		this.scheduleColorRepository = scheduleColorRepository;
		this.workScheduleRepository = workScheduleRepository;
		this.templateHeadersRepository = templateHeadersRepository;
		this.scheduleTemplatesRepository = scheduleTemplatesRepository;
		this.elementRepository = elementRepository;
		this.tempDetailListRepository = tempDetailListRepository;
		this.schedulesByTemplateRepository = schedulesByTemplateRepository;
		this.getSpecialDayRepository = getSpecialDayRepository;
	}

	public IS4ResGetScheduleTemplates getScheduleTemplates(IS4ReqGetScheduleTemplates request) {		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetScheduleTemplates response = new IS4ResGetScheduleTemplates();

		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(!request.getPermissionType().contentEquals("ADMIN")) {
			response.setCodeAndMessage(CODE_INVALID_PERMISSION_TYPE, MSG_INVALID_PERMISSION_TYPE);
			log.error("[" + currentMethodName + "]]]aaaddd] - " + callId + " - " + response.toString() + " - " + request.getPermissionType() + " - " +"\n");
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " );
			List<ObjGetScheduleTemplates> result = scheduleTemplatesRepository.callFnGetScheduleTemplates(request.getSessionId(), null, request.getPermissionType());
			if(result != null) {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setScheduleTemplateList(result);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + result.size() + "\n");
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n");
			}
		} catch (Exception e) {
			response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + "\n" + e.toString());
		}
		return response;
	}
	
	public IS4ResGetInstElement getAllSp(IS4ReqGetInstData request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());

		IS4ResGetInstElement result = new IS4ResGetInstElement();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		if(!request.getPermissionType().contentEquals("ADMIN")) {
			result.setCodeAndMessage(CODE_INVALID_PERMISSION_TYPE, MSG_INVALID_PERMISSION_TYPE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - " + request.getPermissionType() + " - " +"\n");
			return result;
		}

		try {
			String contextParam = null;
			if(request.isScheduleTemplate()) {
				contextParam = "SCHEDULE_TEMPLATE=1";
			}
			log.info("[" + currentMethodName + "] - " + callId + " - "  + "sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			List<ObjGetInstElement> response = elementRepository.callFnGetAllSp(request.getSessionId(), contextParam, request.getPermissionType());
			if (response.get(0).getId() > 0) {
				result.setElementList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + response.size() + "\n");
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}
	}
	
	public IS4ResGetInstElement getAllEt(IS4ReqGetInstData request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());

		IS4ResGetInstElement result = new IS4ResGetInstElement();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		if(!request.getPermissionType().contentEquals("ADMIN")) {
			result.setCodeAndMessage(CODE_INVALID_PERMISSION_TYPE, MSG_INVALID_PERMISSION_TYPE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - " + request.getPermissionType() + " - " +"\n");
			return result;
		}

		try {
			log.info("[" + currentMethodName + "] - " + callId + " - "  + " sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			List<ObjGetInstElement> response = elementRepository.callFnGetAllEt(request.getSessionId(), null, request.getPermissionType());
			if (response.get(0).getId() > 0) {
				result.setElementList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + response.size() + "\n");
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}
	}
	
	public IS4ResGetTemplateCont getTemplateCont(IS4ReqGetTemplateCont request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());

		IS4ResGetTemplateCont result = new IS4ResGetTemplateCont();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}

		if(!request.getPermissionType().contentEquals("ADMIN")) {
			result.setCodeAndMessage(CODE_INVALID_PERMISSION_TYPE, MSG_INVALID_PERMISSION_TYPE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + " - " + request.getPermissionType() + " - " +"\n");
			return result;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - "  + " sessionID: " + request.getSessionId() + "- permissionType: " + request.getPermissionType());
			List<ObjGetTemplateCont> response = templateContRepository.callFnGetTemplateCont(request.getSessionId(), null, request.getPermissionType());
			if (response.get(0).getId() > 0) {
				result.setContingentList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + response.size() + "\n");
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}
	}
	
	public IS4ResGetInstUser getTemplateDoc(IS4ReqGetTemplateDoc request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString() + " - sessionID: " + request.getSessionId() + "- spId: " + request.getSpId());

		IS4ResGetInstUser result = new IS4ResGetInstUser();

		if (Util.isEmptyString(request.getSessionId())) {
			result.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}
		
		if(request.getSpId() == null) {
			result.setCodeAndMessage(CODE_MISSING_SERVICE_POINT_ID, MSG_MISSING_SERVICE_POINT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			return result;
		}

		try {
			log.info("[" + currentMethodName + "] - " + callId + " - ");
			List<ObjGetInstElement> response = instUserRepository.callFnGetTemplateDoc(request.getSessionId(), null, request.getSpId());
			if (response.get(0).getId() > 0) {
				result.setUserList(response);
				result.setCodeAndMessage(CODE_OK, MSG_OK);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + response.size() + "\n");
			} else if(response.get(0).getId() < 0){
				result.setCodeAndMessage(response.get(0).getId().intValue(), response.get(0).getName());
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			} else {
				result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
				return result;
			}
			return result;
		} catch (Exception e) {
			result.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED + callId);
			log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString() + "\n" + e.toString());
			return result;
		}
	}
	
	public IS4ResSaveScheduleTemplate saveScheduleTemplates(IS4ReqSaveScheduleTemplates request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResSaveScheduleTemplate response = new IS4ResSaveScheduleTemplate();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getTemplateList() == null) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_LIST, MSG_MISSING_TEMPLATE_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			response = jdbcService.saveScheduleTemplates(request.getSessionId(), request.getTemplateList());
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResSaveScheduleTemplate saveScheduleTemplateDetails(IS4ReqSaveScheduleTemplateDetails request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResSaveScheduleTemplate response = new IS4ResSaveScheduleTemplate();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getTemplateDetailList() == null) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_DETAIL_LIST, MSG_MISSING_TEMPLATE_DETAIL_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			response = jdbcService.saveScheduleTemplateDetails(request.getSessionId(), request.getTemplateDetailList());
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetScheduleTemplateHeaders getScheduleTemplateHeaders(IS4ReqGetScheduleTemplateHeaders request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetScheduleTemplateHeaders response = new IS4ResGetScheduleTemplateHeaders();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getEventTypeFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_EVENT_TYPE_FK, MSG_MISSING_EVENT_TYPE_FK);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getServicePointFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_SERVICE_POINT_ID, MSG_MISSING_SERVICE_POINT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(Util.isEmptyString(request.getName())) {
			response.setCodeAndMessage(CODE_MISSING_NAME, MSG_MISSING_NAME);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			List<ObjGetScheduleTemplateHeaders> result = templateHeadersRepository.callFnGetScheduleTemplateHeaders(request.getSessionId(), null, request.getName(), request.getEventTypeFk(), request.getServicePointFk());
			
			if(result == null) {
				response.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT);
			}
			if(result.get(0).getId() == -1) {
				response.setCodeAndMessage(-1, "Session lejárt.");
			}
			if(result != null && !(result.get(0).getId() < 1)) {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setObjGetScheduleTemplateHeadersList(result);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + result.size() + "\n");
			}
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetScheduleTempDetailList getTemplateDetailList(IS4ReqGetScheduleTempDetailList request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetScheduleTempDetailList response = new IS4ResGetScheduleTempDetailList();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getTemplateIdList())) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_ID_LIST, MSG_MISSING_TEMPLATE_ID_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			List<ObjGetScheduleTemplateDetailList> result = tempDetailListRepository.callFnGetScheduleTemplateDetailList(request.getSessionId(), null, request.getTemplateIdList());
						
			if(result == null) {
				response.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT);
				return response;
			}
			if(result.get(0).getId() == -1) {
				response.setCodeAndMessage(-1, "Session lejárt.");
				return response;
			}
			
			List<ObjGetScheduleTemplateDetailList> resultSorted = result.stream().sorted((o1, o2) -> o1.getDay().compareTo(o2.getDay())).collect(Collectors.toList());
			
			Map<String, List<IS4SimpleObjScheduleTemplateDetail>> resultMap = new HashMap<String, List<IS4SimpleObjScheduleTemplateDetail>>();
			String tempDay = "";
			List<IS4SimpleObjScheduleTemplateDetail> simpleObjList = new ArrayList<IS4SimpleObjScheduleTemplateDetail>();
			for(int i = 0; i < resultSorted.size(); i++) {
				if(!tempDay.equals(resultSorted.get(i).getDay())) {
					if(i != 0)
						resultMap.put((tempDay.substring(0, 1).toUpperCase() + tempDay.substring(1)).trim(), simpleObjList);
					tempDay = resultSorted.get(i).getDay();
					simpleObjList = new ArrayList<IS4SimpleObjScheduleTemplateDetail>();
				}

				IS4SimpleObjScheduleTemplateDetail simpleObj = new IS4SimpleObjScheduleTemplateDetail(
							resultSorted.get(i).getId(),
							resultSorted.get(i).getTimeFrom() + " - " + resultSorted.get(i).getTimeTo(),
							new IS4TemplateDetailDoctor(resultSorted.get(i).getUserFk(), resultSorted.get(i).getUserName()),
							new IS4TemplateDetailContingent(resultSorted.get(i).getContingentGroupFk(), resultSorted.get(i).getContingentGroupName()),
							resultSorted.get(i).getScheduleTemplateFk(),
							resultSorted.get(i).getColor(),
							resultSorted.get(i).getScheduleComment()
						);
				simpleObjList.add(simpleObj);
				if(i == resultSorted.size() - 1)
					resultMap.put((tempDay.substring(0, 1).toUpperCase() + tempDay.substring(1)).trim(), simpleObjList);
			}
			
			response.setResultMap(resultMap);
			response.setCodeAndMessage(CODE_OK, MSG_OK);
			log.info("[" + currentMethodName + "] - " + callId + " - result size=" + resultMap.size() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGenerateScheduleByTemplate generateScheduleByTemplate(IS4ReqGenerateScheduleByTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGenerateScheduleByTemplate response = new IS4ResGenerateScheduleByTemplate();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getDateFrom())) {
			response.setCodeAndMessage(CODE_MISSING_DATE_FROM, MSG_MISSING_DATE_FROM);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(Util.isEmptyString(request.getDateTo())) {
			response.setCodeAndMessage(CODE_MISSING_DATE_TO, MSG_MISSING_DATE_TO);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(Util.isEmptyString(request.getStatus())) {
			response.setCodeAndMessage(CODE_MISSING_SCHEDULE_STATUS, MSG_MISSING_SCHEDULE_STATUS);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(Util.isEmptyString(request.getTemplateDetailIdList())) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_DETAIL_ID_LIST, MSG_MISSING_TEMPLATE_DETAIL_ID_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Map<String, Object> result = spService.generateScheduleByTemplate(request.getSessionId(), null, request.getTemplateDetailIdList(), request.getStatus(), request.getDateFrom(), request.getDateTo());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response checkScheduleIsExist(IS4ReqGenerateScheduleByTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getDateFrom())) {
			response.setCodeAndMessage(CODE_MISSING_DATE_FROM, MSG_MISSING_DATE_FROM);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(Util.isEmptyString(request.getDateTo())) {
			response.setCodeAndMessage(CODE_MISSING_DATE_TO, MSG_MISSING_DATE_TO);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}

		if(Util.isEmptyString(request.getStatus())) {
			response.setCodeAndMessage(CODE_MISSING_SCHEDULE_STATUS, MSG_MISSING_SCHEDULE_STATUS);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(Util.isEmptyString(request.getTemplateDetailIdList())) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_DETAIL_ID_LIST, MSG_MISSING_TEMPLATE_DETAIL_ID_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {			
			response = jdbcService.checkScheduleIsExist(request);
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResScheduleColor getScheduleLoadByTemplate(IS4ReqGetScheduleLoadByTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResScheduleColor response = new IS4ResScheduleColor();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getName())) {
			response.setCodeAndMessage(CODE_MISSING_NAME, MSG_MISSING_NAME);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getEventTypeFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_EVENT_TYPE_FK, MSG_MISSING_EVENT_TYPE_FK);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getServicePointFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_SERVICE_POINT_ID, MSG_MISSING_SERVICE_POINT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			List<ObjScheduleColor> result = scheduleColorRepository.callFnScheduleLoadByTemplate(request.getSessionId(), null, request.getName(), request.getEventTypeFk(), request.getServicePointFk(), request.getDateFrom(), request.getDateTo());
			if(result != null) {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setObjScheduleColor(result);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + result.size() + "\n");
			} else {
				response.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT);
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetGeneratedSchedulesByTemplate getGeneratedSchedulesByTemplate(IS4ReqGetGeneratedSchedulesByTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetGeneratedSchedulesByTemplate response = new IS4ResGetGeneratedSchedulesByTemplate();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getName())) {
			response.setCodeAndMessage(CODE_MISSING_NAME, MSG_MISSING_NAME);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getEventTypeFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_EVENT_TYPE_FK, MSG_MISSING_EVENT_TYPE_FK);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getServicePointFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_SERVICE_POINT_ID, MSG_MISSING_SERVICE_POINT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			List<ObjGetGeneratedSchedulesByTemplate> result = schedulesByTemplateRepository.callFnGetGeneratedSchedulesByTemplate(request.getSessionId(), null, request.getName(), request.getEventTypeFk(), request.getServicePointFk(), request.getDateFrom(), request.getDateTo());
			if(result != null) {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setGeneratedSchedulesList(result);
				log.info("[" + currentMethodName + "] - " + callId + " - result size=" + result.size() + "\n");
			} else {
				response.setCodeAndMessage(CODE_NO_RESULT, MSG_NO_RESULT);
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response deleteScheduleList(IS4ReqDeleteScheduleList request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getScheduleIdList())) {
			response.setCodeAndMessage(CODE_MISSING_SCHEDULE_ID_LIST, MSG_MISSING_SCHEDULE_ID_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Map<String, Object> result = spService.deleteScheduleList(request.getSessionId(), null, request.getScheduleIdList(), request.getServiceFk());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response deleteTemplate(IS4ReqDeleteTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getName())) {
			response.setCodeAndMessage(CODE_MISSING_NAME, MSG_MISSING_NAME);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getEventTypeFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_EVENT_TYPE_FK, MSG_MISSING_EVENT_TYPE_FK);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getServicePointFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_SERVICE_POINT_ID, MSG_MISSING_SERVICE_POINT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getDeleteSchedule() == null) {
			response.setCodeAndMessage(CODE_MISSING_DELETE_SCHEDULE, MSG_MISSING_DELETE_SCHEDULE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Map<String, Object> result = spService.deleteTemplate(request.getSessionId(), null, request.getName(), request.getEventTypeFk(), request.getServicePointFk(), request.getDeleteSchedule());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response deleteTemplateHeader(IS4ReqDeleteTemplateHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getTemplateId() == null) {
			response.setCodeAndMessage(CODE_MISSING_TEMPLATE_ID, MSG_MISSING_TEMPLATE_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		if(request.getDeleteSchedule() == null) {
			response.setCodeAndMessage(CODE_MISSING_DELETE_SCHEDULE, MSG_MISSING_DELETE_SCHEDULE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Map<String, Object> result = spService.deleteTemplateHeader(request.getSessionId(), null, request.getTemplateId(), request.getDeleteSchedule());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstituteData getContingentSp(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetInstituteData response = new IS4ResGetInstituteData();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetInstituteData> result = instituteDataRepository.callFnGetContSp(request.getSessionId(), null);

			if (result != null) {
				response.setInstituteDataList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstUser getContingentUser(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetInstUser response = new IS4ResGetInstUser();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<ObjGetInstElement> result = elementRepository.callFnGetContUser(request.getSessionId(), null);

			if (result != null) {
				response.setUserList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstituteData getOtherRoles(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetInstituteData response = new IS4ResGetInstituteData();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetInstituteData> result = instituteDataRepository.callFnGetNotInstRoles(request.getSessionId(), null);

			if (result != null) {
				response.setInstituteDataList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstituteData getInstRoles(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetInstituteData response = new IS4ResGetInstituteData();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetInstituteData> result = instituteDataRepository.callFnGetInstRoles(request.getSessionId(), null);

			if (result != null) {
				response.setInstituteDataList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetInstElementExt getContingent(IS4MessageHeader request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetInstElementExt response = new IS4ResGetInstElementExt();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<ObjGetInstElement> result = elementRepository.callFnGetInstContingent(request.getSessionId(), null);

			if (result != null) {
				response.setInstituteDataList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response saveContingentGroup(IS4ReqSaveContingentGroup request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(Util.isEmptyString(request.getContingentGroupName())) {
			response.setCodeAndMessage(CODE_MISSING_CONTINGENT_GROUP_NAME, MSG_MISSING_CONTINGENT_GROUP_NAME);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}else {
			if(request.getRoleIds() == null && request.getUserIds() == null && request.getSpCodes() == null) {
				response.setCodeAndMessage(CODE_MISSING_PARAM, MSG_MISSING_PARAMETERS);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;
			}
		}
		
		try {
			response = spService.saveContingentGroup(request.getSessionId(), null, request.getContingentGroupName(), request.getUserIds(), request.getRoleIds(), request.getSpCodes());			
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response deleteContingentGroup(IS4ReqDeleteContingentGroup request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getId() == null) {
			response.setCodeAndMessage(CODE_MISSING_ID, MSG_MISSING_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			response = spService.deleteContingentGroup(request.getSessionId(), null, request.getId());
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response saveContingentDetail(IS4ReqSaveContingentDetail request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getHeaderId() == null) {
			response.setCodeAndMessage(CODE_MISSING_HEADER_ID, MSG_MISSING_HEADER_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}else {
			if(request.getRoleIds() == null && request.getUserIds() == null && request.getDelContIds() == null) {
				response.setCodeAndMessage(CODE_MISSING_PARAM, MSG_MISSING_PARAMETERS);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
				return response;	
			}
		}
		
		try {
			response = spService.saveContingentDetail(request.getSessionId(), null, request.getHeaderId(), request.getUserIds(), request.getRoleIds(), request.getDelContIds(), request.getSpCodes());			
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetContingentDetail getContingentDetails(IS4ReqGetContingentDetail request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetContingentDetail response = new IS4ResGetContingentDetail();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if (request.getContingentId() == null) {
			response.setCodeAndMessage(CODE_MISSING_CONTINGENT_ID, MSG_MISSING_CONTINGENT_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetContingentDetails> result = contigentDetailRepository.callFnGetContingentDetails(request.getSessionId(), null, request.getContingentId());

			if (result != null) {
				response.setContingentDetails(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public Response modifySchedulesDetail(IS4ReqModifySchedulesDetail request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if (Util.isEmptyString(request.getScheduleIdList())) {
			response.setCodeAndMessage(CODE_MISSING_SCHEDULE_ID_LIST, MSG_MISSING_SCHEDULE_ID_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getServiceFk() == null) {
			response.setCodeAndMessage(CODE_MISSING_SERVICE_FK, MSG_MISSING_SERVICE_FK);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			Response result = spService.modifySchedulesDetail(request.getSessionId(), null, request.getScheduleIdList(), request.getUserId(), request.getContingentGroupId(), request.getColor(), request.getComment(), request.getServiceFk());

			if (result != null) {
				response.setCodeAndMessage(result.getCode(), result.getMessage());
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + result.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetSpecialDays getSpecialDays(IS4ReqGetSpecialDays request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetSpecialDays response = new IS4ResGetSpecialDays();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if (Util.isEmptyString(request.getPermissionType())) {
			response.setCodeAndMessage(CODE_MISSING_PERMISSION_TYPE, MSG_MISSING_PERMISSION_TYPE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetSpecialDay> result = getSpecialDayRepository.callFnGetSpecialDays(request.getSessionId(), null, request.getPermissionType());
			
			if (result != null) {
				response.setSpecialDayList(result);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResSaveSpecialDay saveSpecialDay(IS4ReqSaveSpecialDay request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResSaveSpecialDay response = new IS4ResSaveSpecialDay();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		/*if(request.getSpecialDayList() == null) {
			response.setCodeAndMessage(CODE_MISSING_SPECIAL_DAY_LIST, MSG_MISSING_SPECIAL_DAY_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}*/
		
		if(request.getSessionId() == null && (Util.isEmptyString(request.getDay()) || Util.isEmptyString(request.getDayReplace()) || Util.isEmptyString(request.getYearly()) || request.getServicePointFk() == null)) {
			response.setCodeAndMessage(-2, "Hibás paraméterezés.");
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			//response = JDBCQueryUtil.saveSpecialDay(request.getSessionId(), null, request.getSpecialDayList());
			Map<String, Object> result = spService.saveSpecialDay(request.getSessionId(), null, request.getId(), request.getDay(), request.getDayReplace(), request.getYearly(), request.getServicePointFk());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGenerateSpecialSchedule generateSpecialSchedule(IS4ReqGenerateSpecialSchedule request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGenerateSpecialSchedule response = new IS4ResGenerateSpecialSchedule();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getSpecialScheduleList() == null) {
			response.setCodeAndMessage(CODE_MISSING_SCHEDULE_LIST, MSG_MISSING_SCHEDULE_LIST);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
			
			response = jdbcService.generateSpecialSchedule(request.getSessionId(), null, request.getSpecialScheduleList());
			
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");
			
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}

	public Response deleteSpecialDay(IS4ReqDeleteSpecialDay request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		if(request.getSpecialDayId() == null) {
			response.setCodeAndMessage(CODE_MISSING_SPECIAL_DAY_ID, MSG_MISSING_SPECIAL_DAY_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			return response;
		}
		
		try {
			Map<String, Object> result = spService.deleteSpecialDay(request.getSessionId(), null, request.getSpecialDayId());
			response.setCodeAndMessage((Integer) result.get("p_code"), (String) result.get("p_msg"));
			
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGetWorkSchedule getWorkSchedule(IS4ReqGetInstWorkSchedule request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		IS4ResGetWorkSchedule response = new IS4ResGetWorkSchedule();
		
		if (Util.isEmptyString(request.getSessionId())) {
			response.setCodeAndMessage(CODE_EMPTY_SESSION_ID, MSG_EMPTY_SESSION_ID);
			log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString() + " - ");
			return response;
		}
		
		try {
			List<IS4ObjGetInstWorkSchedule> workSchedule = workScheduleRepository.callFnGetInstWorkSchedule(request.getSessionId(), null, request.getSpId(), request.getEtId(), request.getDateFrom(), request.getDateTo());
			
			if (workSchedule != null) {
				response.setWorkSchedule(workSchedule);
				response.setCodeAndMessage(CODE_OK, MSG_OK);
			} else {
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
				log.error("[" + currentMethodName + "] - " + callId + " - " + response.toString());
			}
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}

}

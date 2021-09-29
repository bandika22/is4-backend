package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.CODE_SP_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.MSG_SP_CALL_FAILED;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4ReqModSendData;
import hu.econsult.is4.model.NEUResLogin;
import hu.econsult.is4.model.NEUResUpdateSession;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.util.Util;

@Service
public class SpService {

		@PersistenceContext(type = PersistenceContextType.TRANSACTION)
		private EntityManager em;

	    public Map<String, Object> callPSaveSchedule(String sessionId, String contextParams, String calId, String scheduleId, String mode, String detail, String reason, String calModReasonCode, String medicalDate) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {		
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_save_cal");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				if(Util.isEmptyString(contextParams)) {
					sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN);
				} else {
					sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParams);
				}
				if(calId != null) {
					sp.registerStoredProcedureParameter("p_cal_id_list", String.class, ParameterMode.IN).setParameter("p_cal_id_list", calId);
				} else {
					sp.registerStoredProcedureParameter("p_cal_id_list", String.class, ParameterMode.IN);
				}
				if(scheduleId != null) {
					sp.registerStoredProcedureParameter("p_sch_id_list", String.class, ParameterMode.IN).setParameter("p_sch_id_list", scheduleId);
				} else {
					sp.registerStoredProcedureParameter("p_sch_id_list", String.class, ParameterMode.IN);
				}
				sp.registerStoredProcedureParameter("p_cal_mod_reason", String.class, ParameterMode.IN).setParameter("p_cal_mod_reason", reason);
				sp.registerStoredProcedureParameter("p_cal_mod_reason_code", String.class, ParameterMode.IN).setParameter("p_cal_mod_reason_code", calModReasonCode);


				sp.registerStoredProcedureParameter("p_mode", String.class, ParameterMode.IN).setParameter("p_mode", mode);
				sp.registerStoredProcedureParameter("p_detail", String.class, ParameterMode.IN).setParameter("p_detail", detail);
				sp.registerStoredProcedureParameter("p_medical_date", String.class, ParameterMode.IN).setParameter("p_medical_date", medicalDate);
				sp.registerStoredProcedureParameter("p_code", Integer.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer code = sp.getOutputParameterValue("p_code") == null ? null : (Integer) sp.getOutputParameterValue("p_code");
				String msg = (String) sp.getOutputParameterValue("p_msg");
				
				response.put("p_cal_id", code);
				response.put("p_msg", msg);
				return response;
			} catch(Exception e) {
				e.printStackTrace();
				response.put("p_cal_id", CODE_SP_CALL_FAILED);
				response.put("p_retmsg", MSG_SP_CALL_FAILED);
				return response;
			}
		}
	    
	    public Map<String, Object> generateScheduleByTemplate(String sessionId, String contextParam, String templateDetailIdList, String status, String dateFrom, String dateTo) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_generate_sch_by_template");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_template_detail_id_list", String.class, ParameterMode.IN).setParameter("p_template_detail_id_list", templateDetailIdList);
				sp.registerStoredProcedureParameter("p_status", String.class, ParameterMode.IN).setParameter("p_status", status);
				sp.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN).setParameter("p_date_from", dateFrom);
				sp.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN).setParameter("p_date_to", dateTo);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				
				response.put("p_code", retnum);
				response.put("p_msg", retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
	    
	    public Map<String, Object> deleteScheduleList(String sessionId, String contextParam, String scheduleIdList, Long serviceFk) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_delete_schedule_list");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_sch_id_list", String.class, ParameterMode.IN).setParameter("p_sch_id_list", scheduleIdList);
				sp.registerStoredProcedureParameter("p_sv_id", Long.class, ParameterMode.IN).setParameter("p_sv_id", serviceFk);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.put("p_code", retnum);
				response.put("p_msg", retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
	    
	    public Map<String, Object> deleteTemplate(String sessionId, String contextParam, String name, Long eventTypeFk, Long servicePointFk, Long deleteSchedule) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_delete_template");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN).setParameter("p_name", name);
				sp.registerStoredProcedureParameter("p_et_id", Long.class, ParameterMode.IN).setParameter("p_et_id", eventTypeFk);
				sp.registerStoredProcedureParameter("p_sp_id", Long.class, ParameterMode.IN).setParameter("p_sp_id", servicePointFk);
				sp.registerStoredProcedureParameter("p_del_sch", Long.class, ParameterMode.IN).setParameter("p_del_sch", deleteSchedule);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
	
				response.put("p_code", retnum);
				response.put("p_msg", retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
		
	    public Map<String, Object> deleteTemplateHeader(String sessionId, String contextParam, Long templateId, Long deleteSchedule) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_delete_template_header");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_template_id", Long.class, ParameterMode.IN).setParameter("p_template_id", templateId);
				sp.registerStoredProcedureParameter("p_del_sch", Long.class, ParameterMode.IN).setParameter("p_del_sch", deleteSchedule);
				sp.registerStoredProcedureParameter("p_code", Integer.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : (Integer) sp.getOutputParameterValue("p_code");
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				
				response.put("p_code", retnum);
				response.put("p_msg", retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1L);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
	    
	    public Response saveContingentGroup(String sessionId, String contextParam, String contingentGroupName, String userIds, String roleIds, String spCodes) {
	    	
	    	Response response = new Response();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_save_contingent_group");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN).setParameter("p_name", contingentGroupName);
				sp.registerStoredProcedureParameter("p_user_id_list", String.class, ParameterMode.IN).setParameter("p_user_id_list", userIds);
				sp.registerStoredProcedureParameter("p_role_id_list", String.class, ParameterMode.IN).setParameter("p_role_id_list", roleIds);
				sp.registerStoredProcedureParameter("p_sp_code_list", String.class, ParameterMode.IN).setParameter("p_sp_code_list", spCodes);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.setCode(retnum);
				response.setMessage(retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.setCode(-1);
				response.setMessage(e.getMessage());
				return response;
			}
		}
	    
	    public Response deleteContingentGroup(String sessionId, String contextParam, Long headerId) {
	    	
	    	Response response = new Response();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_delete_contingent_group");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_header_id", Long.class, ParameterMode.IN).setParameter("p_header_id", headerId);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.setCodeAndMessage(retnum, retmsg);
				
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.setCodeAndMessage(-1, e.getMessage());
				return response;
			}
		}
	    
	    public Response saveContingentDetail(String sessionId, String contextParam, Long headerId, String userIds, String roleIds, String delContIds, String spCodes) {
	    	
	    	Response response = new Response();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_save_contingent_group_detail");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_header_id", Long.class, ParameterMode.IN).setParameter("p_header_id", headerId);
				sp.registerStoredProcedureParameter("p_user_id_list", String.class, ParameterMode.IN).setParameter("p_user_id_list", userIds);
				sp.registerStoredProcedureParameter("p_role_id_list", String.class, ParameterMode.IN).setParameter("p_role_id_list", roleIds);	
				sp.registerStoredProcedureParameter("p_sp_code_list", String.class, ParameterMode.IN).setParameter("p_sp_code_list", spCodes);
				sp.registerStoredProcedureParameter("p_del_cont_list", String.class, ParameterMode.IN).setParameter("p_del_cont_list", delContIds);	
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.setCodeAndMessage(retnum, retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.setCodeAndMessage(-1, e.getMessage());
				return response;
			}
		}
	    
	    public Map<String, Object> deleteSpecialDay(String sessionId, String contexParam, Long specDayId) {
	    	
			Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_delete_special_day");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contexParam);
				sp.registerStoredProcedureParameter("p_spec_day_id", Long.class, ParameterMode.IN).setParameter("p_spec_day_id", specDayId);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String responseDeviceId = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
		
				response.put("p_code", retnum);
				response.put("p_msg", responseDeviceId);
				
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
	    
	    public Map<String, Object> saveSpecialDay(String sessionId, String contexParam, Long specDayId, String day, String dayReplace, String yearly, Long spId) {
	    	
	    	Map<String, Object> response = new HashMap<String, Object>();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_save_special_day");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contexParam);
				sp.registerStoredProcedureParameter("p_spec_day_id", Long.class, ParameterMode.IN).setParameter("p_spec_day_id", specDayId);
				sp.registerStoredProcedureParameter("p_day", String.class, ParameterMode.IN).setParameter("p_day", day);
				sp.registerStoredProcedureParameter("p_day_replace", String.class, ParameterMode.IN).setParameter("p_day_replace", dayReplace);
				sp.registerStoredProcedureParameter("p_yearly", String.class, ParameterMode.IN).setParameter("p_yearly", yearly);
				sp.registerStoredProcedureParameter("p_sp_id", Long.class, ParameterMode.IN).setParameter("p_sp_id", spId);

				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String responseDeviceId = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.put("p_code", retnum);
				response.put("p_msg", responseDeviceId);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.put("p_code", -1);
				response.put("p_msg", e.getMessage());
				return response;
			}
		}
	    
	    public Response modifySchedulesDetail(String sessionId, String contextParam, String scheduleIdList, Long userId, Long contingentGroupId, String color, String comment, Long serviceFk) {
	    	
	    	Response response = new Response();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_modify_schedule_list");
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", sessionId);
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
				sp.registerStoredProcedureParameter("p_sch_id_list", String.class, ParameterMode.IN).setParameter("p_sch_id_list", scheduleIdList);
				sp.registerStoredProcedureParameter("p_doctor_id ", Long.class, ParameterMode.IN).setParameter("p_doctor_id ", userId);
				sp.registerStoredProcedureParameter("p_contingent_group_id", Long.class, ParameterMode.IN).setParameter("p_contingent_group_id", contingentGroupId);	
				sp.registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN).setParameter("p_color", color);
				sp.registerStoredProcedureParameter("p_comment", String.class, ParameterMode.IN).setParameter("p_comment", comment);
				sp.registerStoredProcedureParameter("p_sv_id", Long.class, ParameterMode.IN).setParameter("p_sv_id", serviceFk);
				sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
				sp.execute();
				
				Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
				String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
				
				response.setCodeAndMessage(retnum, retmsg);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response.setCodeAndMessage(-1, e.getMessage());
				return response;
			}
		}
		
	    public NEUResLogin callLogin(String deviceId, String userName, String password, String spCode) {
	    	
	    	NEUResLogin result = new NEUResLogin();
			try {
				
				StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_NAUCOMM_AUTH.P_LOGIN");
				
				if(userName != null) {
					sp.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN).setParameter("p_username", userName);
				} else {
					sp.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
				}
				
				if(password != null) {
					sp.registerStoredProcedureParameter("p_pwd", String.class, ParameterMode.IN).setParameter("p_pwd", password);
				} else {
					sp.registerStoredProcedureParameter("p_pwd", String.class, ParameterMode.IN);
				}
				
				if(spCode != null) {
					sp.registerStoredProcedureParameter("p_sp_code", String.class, ParameterMode.IN).setParameter("p_sp_code", spCode);
				} else {
					sp.registerStoredProcedureParameter("p_sp_code", String.class, ParameterMode.IN);
				}
				
				if(deviceId != null) {
					sp.registerStoredProcedureParameter("p_device_id", String.class, ParameterMode.IN).setParameter("p_device_id", deviceId);
				} else {
					sp.registerStoredProcedureParameter("p_device_id", String.class, ParameterMode.IN);
				}
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.OUT);
				sp.registerStoredProcedureParameter("p_retmsg", String.class, ParameterMode.OUT);
				sp.execute();
				
				String sessionId = sp.getOutputParameterValue("p_session_id") == null ? null : (String) sp.getOutputParameterValue("p_session_id");
				String message = sp.getOutputParameterValue("p_retmsg") == null ? null : (String) sp.getOutputParameterValue("p_retmsg");
				
				if(message != null) {
					String[] splitMessage = message.split("#");
					
					result.setSessionId(sessionId);
					if(splitMessage.length > 0) {
						result.setCode(new Integer(splitMessage[0]));
					}
					if(splitMessage.length > 1) {
						result.setMessage(splitMessage[1]);
					}
					if(splitMessage.length > 2) {
						result.setExpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(splitMessage[2]));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setCodeAndMessage(CODE_SP_CALL_FAILED, MSG_SP_CALL_FAILED);
				return result;
			}
			return result;
		}
	    
	    public NEUResUpdateSession callUpdateSession(String sessionId, String contextParam) {
	    	
	    	NEUResUpdateSession result = new NEUResUpdateSession();
			
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_NAUCOMM_AUTH.P_UPDATE_SESSION");
			if(sessionId != null) {
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.INOUT).setParameter("p_session_id", sessionId);
			} else {
				sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.INOUT);
			}
			
			if(contextParam != null) {
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN).setParameter("p_context_params", contextParam);
			} else {
				sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN);
			}
			
			sp.registerStoredProcedureParameter("p_retmsg", String.class, ParameterMode.OUT);
			sp.execute();
			
			String resSessionId = (String) sp.getOutputParameterValue("p_session_id");
			String message = (String) sp.getOutputParameterValue("p_retmsg");
			
			String[] splitMessage = message.split("#");
			
			result.setSessionId(resSessionId);
			result.setCode(new Integer(splitMessage[0]));
			result.setMessage(splitMessage[1]);
			
			return result;
	    }
	    
	    public Response callModifySendData(IS4ReqModSendData req) {
	    	
	    	Response result = new Response();
			
			StoredProcedureQuery sp = em.createStoredProcedureQuery("pkg_is4.p_mod_cal_senddata");
			sp.registerStoredProcedureParameter("p_session_id", String.class, ParameterMode.IN).setParameter("p_session_id", req.getSessionId());
			sp.registerStoredProcedureParameter("p_context_params", String.class, ParameterMode.IN);
			sp.registerStoredProcedureParameter("p_cal_id", Long.class, ParameterMode.IN).setParameter("p_cal_id", req.getCalId());
			sp.registerStoredProcedureParameter("p_send_date", String.class, ParameterMode.IN).setParameter("p_send_date", req.getSendDate());
			sp.registerStoredProcedureParameter("p_send_no", String.class, ParameterMode.IN).setParameter("p_send_no", req.getSendNo());
			sp.registerStoredProcedureParameter("p_send_sp_name", String.class, ParameterMode.IN).setParameter("p_send_sp_name", req.getSendSpName());
			sp.registerStoredProcedureParameter("p_send_sp_code", String.class, ParameterMode.IN).setParameter("p_send_sp_code", req.getSendSpCode());
			sp.registerStoredProcedureParameter("p_send_dr_name", String.class, ParameterMode.IN).setParameter("p_send_dr_name", req.getSendDrName());
			sp.registerStoredProcedureParameter("p_send_dr_seal", String.class, ParameterMode.IN).setParameter("p_send_dr_seal", req.getSendDrSeal());
			sp.registerStoredProcedureParameter("p_send_diag", String.class, ParameterMode.IN).setParameter("p_send_diag", req.getSendDiag());
			sp.registerStoredProcedureParameter("p_send_comment", String.class, ParameterMode.IN).setParameter("p_send_comment", req.getSendComment());
			
			sp.registerStoredProcedureParameter("p_code", Long.class, ParameterMode.OUT);
			sp.registerStoredProcedureParameter("p_msg", String.class, ParameterMode.OUT);
			
			Integer retnum = sp.getOutputParameterValue("p_code") == null ? null : Math.toIntExact((Long) sp.getOutputParameterValue("p_code"));
			String retmsg = sp.getOutputParameterValue("p_msg") == null ? null : (String) sp.getOutputParameterValue("p_msg");
			
			result.setCodeAndMessage(retnum, retmsg);
			
			return result;
	    }
}

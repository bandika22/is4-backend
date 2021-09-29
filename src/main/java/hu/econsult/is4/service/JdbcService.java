package hu.econsult.is4.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.IS4ReqGenerateScheduleByTemplate;
import hu.econsult.is4.model.IS4ResGenerateSpecialSchedule;
import hu.econsult.is4.model.IS4ResSaveScheduleTemplate;
import hu.econsult.is4.model.ObjSavePermissionToUser;
import hu.econsult.is4.model.ObjSaveRoleToUser;
import hu.econsult.is4.model.ObjSaveScheduleTemplate;
import hu.econsult.is4.model.ObjSaveScheduleTemplateDetail;
import hu.econsult.is4.model.ObjSpecialSchedule;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.jdbc.OracleConnection;

import static hu.econsult.is4.util.ErrorCodes.*;

@Slf4j
@Service
public class JdbcService {

	@PersistenceContext
    EntityManager em;
 
	public IS4ResSaveScheduleTemplate saveScheduleTemplates(String sessionId, List<ObjSaveScheduleTemplate> templateList) {
		IS4ResSaveScheduleTemplate response = new IS4ResSaveScheduleTemplate();
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);
			
			if (templateList != null) {
				log.info("templateList size=" + templateList.size());
			} else {
				log.info("templateList=null");
				response.setCodeAndMessage(CODE_MISSING_TEMPLATE_LIST, MSG_MISSING_TEMPLATE_LIST);
				return response;
			}
			
			Object[] objectsInTheArray = new Object[templateList.size()];

			ArrayDescriptor arrayDesc = new ArrayDescriptor("t_tbl_is4_save_sch_template".toUpperCase(), conn);

			StructDescriptor descriptor = new StructDescriptor("t_rec_is4_save_sch_template".toUpperCase(), conn);
			
			for(int i = 0; i <templateList.size(); i++) {

				log.info("Template[" + 0 + "]={ id=" + templateList.get(i).getId() 
						+ ", name=" + templateList.get(i).getName() 
						+ ", timeFrom=" + templateList.get(i).getTimeFrom() 
						+ ", timeTo=" + templateList.get(i).getTimeTo()
						+ ", eventMax=" + templateList.get(i).getEventMax()
						+ ", eventMaxSame=" + templateList.get(i).getEventMaxSame()
						+ ", userFk=" + templateList.get(i).getUserFk()
						+ ", eventTypeFk=" + templateList.get(i).getEventTypeFk()
						+ ", servicePointFk=" + templateList.get(i).getServicePointFk()
						+ ", scheduleComment=" + templateList.get(i).getScheduleComment()
						+ ", color=" + templateList.get(i).getColor()
						+ ", monday=" + templateList.get(i).getMonday()
						+ ", tuesday=" + templateList.get(i).getTuesday()
						+ ", wednesday=" + templateList.get(i).getWednesday()
						+ ", thursday=" + templateList.get(i).getThursday()
						+ ", friday=" + templateList.get(i).getFriday()
						+ ", saturday=" + templateList.get(i).getSaturday()
						+ ", sunday=" + templateList.get(i).getSunday()
						+ ", contingentGroupFk=" + templateList.get(i).getContingentGroupFk()
						+ ", week=" + templateList.get(i).getWeek()
						+ " }"
				);
				
				Object[] attributes = new Object[20];
				
				attributes[0] = templateList.get(i).getId();
				attributes[1] = templateList.get(i).getName();
				attributes[2] = templateList.get(i).getTimeFrom();
				attributes[3] = templateList.get(i).getTimeTo();
				attributes[4] = templateList.get(i).getEventMax();	
				attributes[5] = templateList.get(i).getEventMaxSame();		
				attributes[6] = templateList.get(i).getUserFk();			
				attributes[7] = templateList.get(i).getEventTypeFk();			
				attributes[8] = templateList.get(i).getServicePointFk();		
				attributes[9] = templateList.get(i).getScheduleComment();
				attributes[10] = templateList.get(i).getColor();
				attributes[11] = templateList.get(i).getMonday();
				attributes[12] = templateList.get(i).getTuesday();
				attributes[13] = templateList.get(i).getWednesday();
				attributes[14] = templateList.get(i).getThursday();
				attributes[15] = templateList.get(i).getFriday();
				attributes[16] = templateList.get(i).getSaturday();
				attributes[17] = templateList.get(i).getSunday();			
				attributes[18] = templateList.get(i).getContingentGroupFk();			
				attributes[19] = templateList.get(i).getWeek();
	
				STRUCT struct = new STRUCT(descriptor, conn, attributes);
				
				objectsInTheArray[i] = struct;
			}
			
			ARRAY array = new ARRAY(arrayDesc, conn, objectsInTheArray);

			CallableStatement callStatement = conn
					.prepareCall(" call calendar.pkg_is4.p_save_schedule_template_list( ?, ?, ?, ?, ? ) ");
			callStatement.setObject(1, sessionId, Types.VARCHAR);
			callStatement.setObject(2, null, Types.VARCHAR);
			callStatement.setObject(3, array, Types.ARRAY);
			
			log.info("call calendar.pkg_is4.p_save_schedule_template_list( " + sessionId + ", null" + ", templateList, p_code, p_msg)");

			callStatement.registerOutParameter(4, Types.DECIMAL);
			callStatement.registerOutParameter(5, Types.VARCHAR);

			callStatement.executeQuery();

			response.setCode(callStatement.getBigDecimal(4).intValue());
			response.setMessage(callStatement.getString(5));
			
			log.info("p_code=" + response.getCode() + ", p_msg=" + response.getMessage());
			
			callStatement.close();

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-999);
			response.setMessage("Ismeretlen hiba.");

			return response;
		}

	}

	public IS4ResSaveScheduleTemplate saveScheduleTemplateDetails(String sessionId, List<ObjSaveScheduleTemplateDetail> templateDetailList) {
		IS4ResSaveScheduleTemplate response = new IS4ResSaveScheduleTemplate();
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);

			
			if (templateDetailList != null) {
				log.info("templateDetailList size=" + templateDetailList.size());
			} else {
				log.info("templateDetailList=null");
				response.setCodeAndMessage(CODE_MISSING_TEMPLATE_DETAIL_LIST, MSG_MISSING_TEMPLATE_DETAIL_LIST);
				return response;
			}

			Object[] objectsInTheArray = new Object[templateDetailList.size()];

			ArrayDescriptor arrayDesc = new ArrayDescriptor("t_tbl_is4_save_temp_d".toUpperCase(), conn);

			StructDescriptor structDesc = new StructDescriptor("t_rec_is4_save_temp_d".toUpperCase(), conn);

			for (int i = 0; i < templateDetailList.size(); i++) {
				
				log.info("TemplateDetail[" + 0 + "]={ id=" + templateDetailList.get(i).getTemplateId()
						+ ", timeFrom=" + templateDetailList.get(i).getTimeFrom() 
						+ ", timeTo=" + templateDetailList.get(i).getTimeTo()
						+ ", userFk=" + templateDetailList.get(i).getUserFk()
						+ ", scheduleComment=" + templateDetailList.get(i).getScheduleComment()
						+ ", color=" + templateDetailList.get(i).getColor()
						+ ", scheduleTemplateFk=" + templateDetailList.get(i).getScheduleTemplateFk()
						+ ", contingentGroupFk=" + templateDetailList.get(i).getContingentGroupFk()
						+ ", day=" + templateDetailList.get(i).getDay()
						+ " }"
				);
							
				Object[] attributes = new Object[9];

				attributes[0] = templateDetailList.get(i).getTemplateId();
				attributes[1] = templateDetailList.get(i).getTimeFrom();
				attributes[2] = templateDetailList.get(i).getTimeTo();
				attributes[3] = templateDetailList.get(i).getUserFk();
				attributes[4] = templateDetailList.get(i).getScheduleComment();
				attributes[5] = templateDetailList.get(i).getColor();
				attributes[6] = templateDetailList.get(i).getScheduleTemplateFk();
				attributes[7] = templateDetailList.get(i).getContingentGroupFk();
				attributes[8] = templateDetailList.get(i).getDay();

				STRUCT object = new STRUCT(structDesc, conn, attributes);

				objectsInTheArray[i] = object;
			}

			ARRAY array = new ARRAY(arrayDesc, conn, objectsInTheArray);

			CallableStatement callStatement = conn
					.prepareCall(" call calendar.pkg_is4.p_save_schedule_temp_det_list( ?, ?, ?, ?, ? ) ");
			callStatement.setObject(1, sessionId, Types.VARCHAR);
			callStatement.setObject(2, null, Types.VARCHAR);
			callStatement.setObject(3, array, Types.ARRAY);
			
			log.info("call calendar.pkg_is4.p_save_schedule_temp_det_list( " + sessionId + ", null" + ", templateDetailList, p_code, p_msg)");

			callStatement.registerOutParameter(4, Types.DECIMAL);
			callStatement.registerOutParameter(5, Types.VARCHAR);

			callStatement.executeQuery();

			response.setCode(callStatement.getBigDecimal(4).intValue());
			response.setMessage(callStatement.getString(5));
			
			log.info("p_code=" + response.getCode() + ", p_msg=" + response.getMessage());
			
			callStatement.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-999);
			response.setMessage("Ismeretlen hiba.");

			return response;
		}
	}
	
	public Response checkScheduleIsExist(IS4ReqGenerateScheduleByTemplate request) {
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		log.info("[" + currentMethodName + "] - " + callId + " - " + request.toString());
		
		Response response = new Response();
		
        PreparedStatement stmtObj = null;
        
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);
			
			Clob ids = conn.createClob();
			ids.setString(1, request.getTemplateDetailIdList());
							    
            stmtObj = conn.prepareStatement("select calendar.pkg_is4.f_is_temp_already_generated(?, ?, ?, ?, ?, ?) from dual");
		    
            stmtObj.setString(1, request.getSessionId());
            stmtObj.setString(2, null);
            stmtObj.setClob(3, ids);
            stmtObj.setString(4, request.getStatus());
            stmtObj.setString(5, request.getDateFrom());
            stmtObj.setString(6, request.getDateTo());
			
            ResultSet result = stmtObj.executeQuery();
                        
            result.next();
            Integer res = result.getInt(1);
            
			log.info("[" + currentMethodName + "] - " + callId + " - code=" + response.getCode() + " message=" + response.getMessage() + "\n");

			if(res == 0) {
				response.setCodeAndMessage(1, "Ok");
			} else {
				response.setCodeAndMessage(-1, "Erre az intervallumra már van kigenerálva időpont ezen a munkahelyen.");
			}
			
            stmtObj.close();
            
			return response;
		} catch (Exception e) {
			log.error("[" + currentMethodName + "] - " + callId + " - " + e.toString());
			response.setCode(CODE_UNKNOWN);
			response.setMessage(MSG_UNKNOWN);
			
			return response;
		}
	}
	
	public IS4ResGenerateSpecialSchedule generateSpecialSchedule(String sessionId, String contextParams, List<ObjSpecialSchedule> generateSpecialSchedule) {
		IS4ResGenerateSpecialSchedule response = new IS4ResGenerateSpecialSchedule();
		
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);
			
			if (generateSpecialSchedule != null) {
				log.info("generateSpecialSchedule size=" + generateSpecialSchedule.size());
			} else {
				log.info("generateSpecialSchedule=null");
				response.setCodeAndMessage(CODE_MISSING_SCHEDULE_LIST, MSG_MISSING_SCHEDULE_LIST);
				return response;
			}

			Object[] objectsInTheArray = new Object[generateSpecialSchedule.size()];

			ArrayDescriptor arrayDesc = new ArrayDescriptor("t_tbl_is4_gen_spec_sch".toUpperCase(), conn);

			StructDescriptor structDesc = new StructDescriptor("t_rec_is4_gen_spec_sch".toUpperCase(), conn);

			for (int i = 0; i < generateSpecialSchedule.size(); i++) {
				
				log.info("TemplateDetail[" + 0 + "]={ dateFrom=" + generateSpecialSchedule.get(i).getDateFrom()
						+ ", dateTo=" + generateSpecialSchedule.get(i).getDateTo()
						+ ", userFk=" + generateSpecialSchedule.get(i).getUserFk()
						+ ", serviceFk=" + generateSpecialSchedule.get(i).getServiceFk()
						+ ", servicePointFk=" + generateSpecialSchedule.get(i).getServicePointFk()
						+ ", eventTypeFk=" + generateSpecialSchedule.get(i).getEventTypeFk()
						+ ", scheduleComment=" + generateSpecialSchedule.get(i).getScheduleComment()
						+ ", color=" + generateSpecialSchedule.get(i).getColor()
						+ ", scheduleTemplateFk=" + generateSpecialSchedule.get(i).getScheduleTemplateFk()
						+ ", contingentGroupFk=" + generateSpecialSchedule.get(i).getContingentGroupFk()
						+ ", status=" + generateSpecialSchedule.get(i).getStatus()
						+ ", scheduleTemplateName=" + generateSpecialSchedule.get(i).getScheduleTemplateName()
						+ " }"
				);
				
				Object[] attributes = new Object[11];

				attributes[0] = generateSpecialSchedule.get(i).getDateFrom();
				attributes[1] = generateSpecialSchedule.get(i).getDateTo();
				attributes[2] = generateSpecialSchedule.get(i).getUserFk();
				attributes[3] = generateSpecialSchedule.get(i).getServiceFk();
				attributes[4] = generateSpecialSchedule.get(i).getServicePointFk();
				attributes[5] = generateSpecialSchedule.get(i).getEventTypeFk();
				attributes[6] = generateSpecialSchedule.get(i).getScheduleComment();
				attributes[7] = generateSpecialSchedule.get(i).getColor();
				attributes[8] = generateSpecialSchedule.get(i).getContingentGroupFk();
				attributes[9] = generateSpecialSchedule.get(i).getStatus();
				attributes[10] = generateSpecialSchedule.get(i).getScheduleTemplateName();

				STRUCT object = new STRUCT(structDesc, conn, attributes);

				objectsInTheArray[i] = object;
			}

			ARRAY array = new ARRAY(arrayDesc, conn, objectsInTheArray);

			CallableStatement callStatement = conn
					.prepareCall(" call calendar.pkg_is4.p_generate_special_schedule( ?, ?, ?, ?, ?) ");
			callStatement.setObject(1, sessionId, Types.VARCHAR);
			callStatement.setObject(2, contextParams, Types.VARCHAR);
			callStatement.setObject(3, array, Types.ARRAY);
			
			log.info("call calendar.pkg_is4.p_generate_special_schedule( " + sessionId + ", " + contextParams + ", generateSpecialSchedule, p_code, p_msg)");

			callStatement.registerOutParameter(4, Types.DECIMAL);
			callStatement.registerOutParameter(5, Types.VARCHAR);

			callStatement.executeQuery();

			response.setCode(callStatement.getBigDecimal(4).intValue());
			response.setMessage(callStatement.getString(5));
			
			log.info("p_code=" + response.getCode() + ", p_msg=" + response.getMessage());
			
			callStatement.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-999);
			response.setMessage("Ismeretlen hiba.");

			return response;
		}
	}
	
	public Response saveRoleToUser(String sessionId, String uuid, List<ObjSaveRoleToUser> userRole) {
		Response response = new Response();
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);
			
			if (userRole != null) {
				log.info("userRole size=" + userRole.size());
			} else {
				log.info("userRole=null");
				response.setCodeAndMessage(CODE_MISSING_TEMPLATE_LIST, MSG_MISSING_TEMPLATE_LIST);
				return response;
			}
			
			Object[] objectsInTheArray = new Object[userRole.size()];

			ArrayDescriptor arrayDesc = new ArrayDescriptor("t_tbl_inst_admin_save_usr_role".toUpperCase(), conn);

			StructDescriptor descriptor = new StructDescriptor("t_rec_inst_admin_save_usr_role".toUpperCase(), conn);
			
			for(int i = 0; i <userRole.size(); i++) {

				log.info("Template[" + 0 + "]={ moduleRoleFk=" + userRole.get(i).getChildModuleRoleFk() 
						+ ", parentModuleRoleFk=" + userRole.get(i).getParentModuleRoleFk() 
						+ ", isDeleted=" + userRole.get(i).isDelete() 
						+ " }"
				);
				
				Short delete = 0;
				if(userRole.get(i).isDelete()) {
					delete = 1;
				} else {
					delete = 0;
				}
				
				Object[] attributes = new Object[3];
				
				attributes[0] = userRole.get(i).getChildModuleRoleFk();
				attributes[1] = userRole.get(i).getParentModuleRoleFk();
				attributes[2] = delete;

	
				STRUCT struct = new STRUCT(descriptor, conn, attributes);
				
				objectsInTheArray[i] = struct;
			}
			
			ARRAY array = new ARRAY(arrayDesc, conn, objectsInTheArray);

			CallableStatement callStatement = conn
					.prepareCall(" call pkg_inst_admin.p_save_inst_user_role_obj( ?, ?, ?, ?, ?, ?) ");
			callStatement.setObject(1, sessionId, Types.VARCHAR);
			callStatement.setObject(2, null, Types.CLOB);
			callStatement.setObject(3, uuid, Types.VARCHAR);
			callStatement.setObject(4, array, Types.ARRAY);
			
			log.info("call nauticom.pkg_inst_admin.p_save_inst_user_role_obj( " + sessionId + ", null" + ", templateList, p_code, p_msg)");

			callStatement.registerOutParameter(5, Types.NUMERIC);
			callStatement.registerOutParameter(6, Types.VARCHAR);

			callStatement.executeQuery();

			response.setCode(callStatement.getBigDecimal(5).intValue());
			response.setMessage(callStatement.getString(6));
			
			log.info("p_code=" + response.getCode() + ", p_msg=" + response.getMessage());
			
			callStatement.close();

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-999);
			response.setMessage("Ismeretlen hiba.");

			return response;
		}

	}
	
	public Response saveUserPermission(String sessionId, String uuid, Long moduleRoleFk, List<ObjSavePermissionToUser> permissionList) {
		Response response = new Response();
		try {
			Connection hikariConn = em.unwrap(SessionImpl.class).connection();
			OracleConnection conn = hikariConn.unwrap(OracleConnection.class);
			
			if (permissionList != null) {
				log.info("permissionList size=" + permissionList.size());
			} else {
				log.info("permissionList=null");
				response.setCodeAndMessage(CODE_MISSING_TEMPLATE_LIST, MSG_MISSING_TEMPLATE_LIST);
				return response;
			}
			
			Object[] objectsInTheArray = new Object[permissionList.size()];

			ArrayDescriptor arrayDesc = new ArrayDescriptor("t_tbl_inst_admin_save_usr_perm".toUpperCase(), conn);

			StructDescriptor descriptor = new StructDescriptor("t_rec_inst_admin_save_usr_perm".toUpperCase(), conn);
			
			for(int i = 0; i <permissionList.size(); i++) {

				/*log.info("Template[" + 0 + "]={ moduleRoleFk=" + permissionList.get(i).getChildModuleRoleFk() 
						+ ", parentModuleRoleFk=" + permissionList.get(i).getParentModuleRoleFk() 
						+ ", isDeleted=" + permissionList.get(i).isDelete() 
						+ " }"
				);*/
				
				Object[] attributes = new Object[3];
				
				attributes[0] = permissionList.get(i).getModulePermissionFk();
				attributes[1] = permissionList.get(i).getGranted();
				attributes[2] = permissionList.get(i).getDelete();

				STRUCT struct = new STRUCT(descriptor, conn, attributes);
				
				objectsInTheArray[i] = struct;
			}
			
			ARRAY array = new ARRAY(arrayDesc, conn, objectsInTheArray);

			CallableStatement callStatement = conn
					.prepareCall(" call pkg_inst_admin.P_SAVE_INST_USER_PERM_OBJ( ?, ?, ?, ?, ?, ?, ?) ");
			callStatement.setObject(1, sessionId, Types.VARCHAR);
			callStatement.setObject(2, null, Types.CLOB);
			callStatement.setObject(3, uuid, Types.VARCHAR);
			callStatement.setObject(4, moduleRoleFk, Types.DECIMAL);
			callStatement.setObject(5, array, Types.ARRAY);
			
			//log.info("call nauticom.pkg_inst_admin.p_save_inst_user_role_obj( " + sessionId + ", null" + ", templateList, p_code, p_msg)");

			callStatement.registerOutParameter(6, Types.NUMERIC);
			callStatement.registerOutParameter(7, Types.VARCHAR);

			callStatement.executeQuery();

			response.setCode(callStatement.getBigDecimal(6).intValue());
			response.setMessage(callStatement.getString(7));
			
			log.info("p_code=" + response.getCode() + ", p_msg=" + response.getMessage());
			
			callStatement.close();
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-999);
			response.setMessage("Ismeretlen hiba.");

			return response;
		}
	}

}

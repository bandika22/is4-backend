package hu.econsult.is4.service;

import static hu.econsult.is4.util.ErrorCodes.CODE_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.CODE_INVALID_HEADER;
import static hu.econsult.is4.util.ErrorCodes.CODE_MISSING_PARAM;
import static hu.econsult.is4.util.ErrorCodes.CODE_OK;
import static hu.econsult.is4.util.ErrorCodes.CODE_UNKNOWN;
import static hu.econsult.is4.util.ErrorCodes.MSG_FN_CALL_FAILED;
import static hu.econsult.is4.util.ErrorCodes.MSG_INVALID_HEADER;
import static hu.econsult.is4.util.ErrorCodes.MSG_MISSING_TYPE_CODE;
import static hu.econsult.is4.util.ErrorCodes.MSG_OK;
import static hu.econsult.is4.util.ErrorCodes.MSG_UNKNOWN;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.econsult.is4.model.NEUReqGetCodeBase;
import hu.econsult.is4.model.NEUResGetCodeBase;
import hu.econsult.is4.model.entity.ObjCodeBase;
import hu.econsult.is4.repository.CodebaseRepository;
import hu.econsult.is4.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilService {

	private final CodebaseRepository codebaseRepository;
	
	@Autowired
	public UtilService(CodebaseRepository codebaseRepository) {
		super();
		this.codebaseRepository = codebaseRepository;
	}



	public NEUResGetCodeBase getCodeBase(NEUReqGetCodeBase req ) {
		
		String callId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		NEUResGetCodeBase response = new NEUResGetCodeBase();
		
		if(req.getMessageHeader() == null || (Util.isEmptyString(req.getMessageHeader().getLocale()) || Util.isEmptyString(req.getMessageHeader().getDeviceId())) && (Util.isEmptyString(req.getMessageHeader().getSessionId()))) {
			response.setCode(CODE_INVALID_HEADER);
			response.setMessage(MSG_INVALID_HEADER);
			log.error("[" + currentMethodName + "]" + "- Invalid messageHeader. - " + req.getMessageHeader().toString());
			return response;
		}
		
		if(Util.isEmptyString(req.getTypeCode())) {
			response.setCodeAndMessage(CODE_MISSING_PARAM, MSG_MISSING_TYPE_CODE);
			log.error("[" + currentMethodName + "] - " + callId + " - " + "CODE: " + CODE_MISSING_PARAM + " MSG: " + MSG_MISSING_TYPE_CODE);
			return response;
		}
		
		try {
			log.info("[" + currentMethodName + "] - " + callId + " - " + req.toString());
			
			List<ObjCodeBase> result = codebaseRepository.callFnCodeBaseList(req.getTypeCode());
			if(result != null) {
				response.setCodeAndMessage(CODE_OK, MSG_OK);
				response.setObjCodeBase(result);
			} else {
				log.error("[" + currentMethodName + "] - " + callId + " - " + "CODE: " + CODE_FN_CALL_FAILED + " MSG: " + MSG_FN_CALL_FAILED);
				response.setCodeAndMessage(CODE_FN_CALL_FAILED, MSG_FN_CALL_FAILED);
			}
			return response;
		} catch(Exception e) {
			log.error("[" + currentMethodName + "] - " + callId+ " - " + "CODE: " + CODE_UNKNOWN + " MSG: " + MSG_UNKNOWN + "/n" + e.toString());
			response.setCodeAndMessage(CODE_UNKNOWN, MSG_UNKNOWN);
			return response;
		}
	}
}

package hu.econsult.is4.util;

public class ErrorCodes {
	
	public static final Integer CODE_UNKNOWN = -9999;
	public static final String MSG_UNKNOWN = "Ismeretlen hiba.";
	public static final String MSG_UNKNOWN_ID = "Ismeretlen hiba. Egyedi azonosító: ";
	public static final Integer CODE_TOO_MANY_TRIES = -9998;	
	public static final String MSG_TOO_MANY_TRIES = "Tul sok probalkozas";
	public static final String MSG_ERROR_DOC_TYPES = "Hiba a dokumentumtípusok lekérdezése közben.";
	public static final String MSG_ERROR_DOC_TYPES_AND_TEMPS = "Hiba a dokumentumtípusok és sablonok lekérdezése közben.";
	public static final String MSG_ERROR_TEMP_BODY = "Hiba a sablon lekérdezése közben.";
	public static final String MSG_ERROR_DOC_LIST = "Hiba a dokumentumok lekérdezése közben.";
	public static final String MSG_ERROR_DOC_DATA = "Hiba a dokumentum leíró adat lekérdezése közben.";
	public static final String MSG_ERROR_DOC_DOWNLOAD = "Hiba a dokumentum letöltése közben.";
	public static final String MSG_ERROR_CAL_LIST_DATA = "Hiba a páciens előjegyzéseinek lekérdezése közben.";
	public static final String MSG_ERROR_RES_CAL_MOD_REASON = "Hiba az időpont módosítás okainak lekérdezése közben.";
	public static final String MSG_ERROR_INSTITUE_DOCTORS = "Hiba az intézmény orvosainak lekérdezése közben.";
	public static final String MSG_ERROR_PATIENT_CARE_TYPES = "Hiba az ellátás típusok lekérdezése közben.";
	public static final String MSG_ERROR_PATIENT_GETAWAY_TYPES = "Hiba az eltávozási típusok lekérdezése közben.";
	public static final String MSG_ERROR_GET_IMPLANT_REG = "Hiba a regisztrált implantátumok lekérdezése közben.";
	public static final String MSG_ERROR_GET_CAL_DATA = "Hiba az időpontok lekérdezése közben.";
	
	
	public static final Integer CODE_SP_CALL_FAILED = -100;
	public static final String MSG_SP_CALL_FAILED = "Hiba törtent a tarolt eljaras futtatasa soran.";
	
	public static final Integer CODE_FN_CALL_FAILED = -102;
	public static final String MSG_FN_CALL_FAILED = "Hiba törtent az adatok lekerdezese soran.";
	
	public static final Integer CODE_SP_CALL_ERROR = -103;
	public static final String MSG_SP_CALL_ERROR = "Hiba törtent a feldolgozás során.";

	public static final Integer CODE_OK = 1;
	public static final String MSG_OK = "OK";
	
	public static final Integer CODE_LOGIN_FAILED = -1;
	public static final String MSG_LOGIN_FAILED = "Sikertelen bejelentkezes.";
	
	public static final Integer CODE_ERROR_DOC_TYPE = -38;
	public static final String MSG_ERROR_DOC_TYPE = "Nem letezik ilyen dokumentum tipus.";
	
	public static final Integer CODE_ERROR_DOC_ID = -39;
	public static final String MSG_ERROR_DOC_ID = "Nem letezik ilyen dokumentum azonosito.";
	
	public static final Integer CODE_SAVE_USER_ROLLBACK_ERROR = -2;
	public static final String MSG_SAVE_USER_ROLLBACK_ERROR = "p_save_user rollback error.";
	
	public static final Integer CODE_MISSING_SPCODE_OR_TAPASS = -3;
	public static final String MSG_MISSING_SPCODE_OR_TAPASS = "Az spCode vagy a tapass parameter kitoltese kotelezo.";
	
	public static final Integer CODE_GETTING_TAPASS_FAILED = -4;
	public static final String MSG_GETTING_TAPASS_FAILED = "Sikertelen tapass keres.";
	
	public static final Integer CODE_MISSING_USER_ID = -5;
	public static final String MSG_MISSING_USER_ID = "Hiányzó user id.";
	
	public static final Integer CODE_ALREADY_REGISTERED_USER = -6;
	public static final String MSG_ALREADY_REGISTERED_USER  = "A user már regisztált.";
	
	public static final Integer CODE_DATE_FORMAT_ERROR= -7;
	public static final String MSG_DATE_FORMAT_ERROR = "Dátum formátum hiba. Elvárt formátum: yyyy-MM-dd";
	
	public static final Integer CODE_DATA_SAVE_FAILED_P_SAVE_REGDATA_XML= -8;
	public static final Integer CODE_DATA_SAVE_FAILED_P_SAVE_USER = -9;
	public static final String MSG_DATA_SAVE_FAILED = "Adatok mentése sikertelen.";
	
	public static final Integer CODE_MISSING_CALMODREASON = -10;
	public static final String MSG_MISSING_CALMODREASON = "Törlésnél nem lehet üres az indoklás kódja";
	
	public static final Integer CODE_MODIFY_CALENDAR_ITEM_ERROR= -11;
	public static final String MSG_MODIFY_CALENDAR_ITEM_ERROR = "Hiba a naptár módosítás közben";
	
	public static final Integer CODE_SET_DOCTOR_FAILED = -12;
	public static final String MSG_SET_DOCTOR_FAILED = "Doktor állítása sikertelen";
	
	public static final Integer CODE_FAILED_SP_QUERY = -13;
	public static final String MSG_FAILED_SP_QUERY = "Hiba történt a szolgaltatasi pontok lekerdezese soran.";
	
	public static final Integer CODE_NO_USER_FOUND = -14;
	public static final String MSG_NO_USER_FOUND = "Nincs ilyen user.";

	public static final Integer CODE_AGREEMENT_NOT_ACCEPTED = -15;
	public static final String MSG_AGREEMENT_NOT_ACCEPTED = "Nincs elfogadva a tanusítvány.";
	
	public static final Integer CODE_USER_NOT_FOUND = -16;
	public static final String MSG_USER_NOT_FOUND = "Nincs ilyen felhasználó.";
	
	public static final Integer CODE_NOT_AUSZ_USER = -17;
	public static final String MSG_NOT_AUSZ_USER = "Nem ausz felhasználó.";
	
	public static final Integer CODE_MISSING_AGREEMENT_FALSE = -117;
	public static final String MSG_MISSING_AGREEMENT_FALSE = "A szerződési feltétel elfogadása kötelező a művelethez.";
	
	
	public static final Integer CODE_AUSZ_USER_NOT_FOUND = -18;
	public static final String MSG_AUSZ_USER_NOT_FOUND = "Nem található ausz felhasználó.";
	
	public static final Integer CODE_WRONG_AUSZCODE = -19;
	public static final Integer CODE_WRONG_PARAMETERS = -19;
	public static final String MSG_WRONG_AUSZCODE = "Hibás auszkód.";
	
	public static final Integer CODE_VALIDATE_FAILED = -20;
	public static final String MSG_VALIDATE_FAILED = "Sikertelen validáció.";
	
	public static final Integer CODE_USERNAME_TAKEN = -21;
	public static final String MSG_USERNAME_TAKEN = "A felhasznalonev mar foglalt!";
	
	public static final Integer CODE_SAVE_USER_ERROR = -22;
	public static final String MSG_SAVE_USER_ERROR = "p_save_user error.";
	
	public static final Integer CODE_SAVE_REGDATA_XML_ROLLBACK_ERROR = -23;
	public static final String MSG_SAVE_REGDATA_XML_ROLLBACK_ERROR = "p_save_regdata_xml rollback error.";
	
	public static final Integer CODE_SAVE_REGDATA_XML_ERROR = -24;
	public static final String MSG_SAVE_REGDATA_XML_ERROR = "p_save_regdata_xml error.";
	
	public static final Integer CODE_MISSING_PARAMETERS = -25;
	public static final Integer CODE_MISSING_PARAM = -25;
	public static final String MSG_MISSING_PARAMETERS = "Hibás paraméterezés.";
	public static final String MSG_MISSING_TAGCODE = "A tagCode paraméter nem lehet üres.";
	public static final String MSG_MISSING_TAGDESCRIPTION = "A tagDescription paraméter nem lehet üres.";
	public static final String MSG_MISSING_TAGNAME = "A tagName paraméter nem lehet üres.";
	public static final String MSG_MISSING_TAPASS = "A tapass paraméter nem lehet üres.";
	public static final String MSG_MISSING_BNO_GRP_PARAM = "A hianyzo bno focsoport vagy alcsoport parameter.";
	public static final String MSG_MISSING_SP_ID_PARAM = "Az spId parameter megadasa kotelezo";
	public static final String MSG_MISSING_UUID_PARAM = "Kötelező pácienst kiválasztani";
	public static final String MSG_MISSING_SV_ID_PARAM = "Az laborSvId parameter megadasa kotelezo";
	public static final String MSG_MISSING_CONTEXT_PARAM = "A contextParam parameter megadasa kotelezo";
	public static final String MSG_MISSING_SYSTEM_CODE_PARAM = "A systemCode parameter megadasa kotelezo";
	public static final String MSG_MISSING_CALID_PARAM = "A calendarId parameter megadasa kotelezo";
	public static final String MSG_MISSING_CURRENT_TAPASS = "A currentUserTapass parameter megadasa kotelezo";
	public static final String MSG_MISSING_C = "A currentUserTapass parameter megadasa kotelezo";
	public static final String MSG_MISSING_APPENDLIST_PARAM = "Az appendImplantList parameter megadasa kotelezo";
	public static final String MSG_MISSING_SP_ET_DT = "A servicePointId, eventTypeId, doctorId legalabb egyikenek megadasa kotelezo.";
	public static final String MSG_MISSING_STATUS = "A státusz megadása kötelező";
	public static final String MSG_MISSING_DATE = "A dátumok megadása kötelező";
	public static final String MSG_MISSING_DETAIL_CATEGORY = "A detailCategories nem lehet null vagy üres.";
	public static final String MSG_MISSING_USER_DETAIL = "A userDetail nem lehet null.";
	public static final String MSG_MISSING_WORKFLOW = "A workflowCode megadása kötelező";
	public static final String MSG_MISSING_USERID_TAPASS = "A userId, tapass legalabb egyikenek megadasa kotelezo.";
	public static final String MSG_MISSING_TYPE_CODE = "A typeCode megadasa kotelezo.";
	public static final String MSG_MISSING_MODE = "A mode megadása kötelező";
	public static final String MSG_MISSING_BCP_LIST = "A bcpMessageIdList megadása kötelező és nem lehet üres.";
	public static final String MSG_MISSING_STATUS_PARAM = "A status megadása kötelező.";
	public static final String MSG_MISSING_EXAM_LIST = "Hiányzó examList paraméter.";
	public static final String MSG_MISSING_SSN_NUMBER= "Hiányzó TAJ szám.";
	public static final String MSG_MISSING_SEARCH_TYPE= "Hiányzó searchType paraméter.";
	public static final String MSG_NO_UUID_PARAM = "Nincs a userhez uuid beállítva";
	public static final String MSG_MISSING_STATUS_LIST_PARAM = "A labor status lista parameter megadasa kotelezo";
	public static final String MSG_MISSING_DOCUMENT_ID = "A docId param megadasa kotelezo.";
	public static final String MSG_MISSING_AUTH_ROLE = "A szerepkör megadása kötelező!.";
	public static final String MSG_MISSING_USER_NAME = "A felhasználónév megadása!.";
	public static final String MSG_MISSING_DOC_ID = "Hianyzo dokumentum azonosito!";
	public static final String MSG_MISSING_ZIP_CODE = "Az irányítószám megadása kötelező!";
	public static final String MSG_MISSING_RELATIVE_LOCATION = "A relativeLocation paraméter magadása kötelező.";
	public static final String MSG_MISSING_RESULT_LIST = "A resultList paraméter megadása kötelező";
	public static final String MSG_MISSING_TAJ_PARAMETER = "A taj paraméter kitöltése kötelező!";
	public static final String MSG_MISSING_LABOR_SERVICE_ID = "A laborServiceId megadasa kotelezo!";
	public static final String MSG_MISSING_VALIDATOR_PARAMS = "A validatorParams paraméter kitöltése kötelező!";
	public static final String MSG_WRONG_ADMISSION_PARAMS = "A megadott behivo nem allithato vissza a kert allapotra!";

	public static final Integer CODE_SEND_EMAIL_FAILED = -41;
	public static final String MSG_SEND_EMAIL_FAILED = "Sikertelen email küldés.";
	
	public static final Integer CODE_SUCCESSFUL_REG_FAILED_PDF_SAVE = -26;
	public static final String MSG_SUCCESSFUL_REG_FAILED_PDF_SAVE = "A regisztracio sikeres, de a regnyilatkozat mentese sikertelen.";
	
	public static final Integer CODE_FAILED_REG_ROLLBACK_STEP3 = -27;
	public static final String MSG_FAILED_REG_ROLLBACK_STEP3 = "A regisztracio torlese sikertelen.";
	
	public static final Integer CODE_SUCCESSFUL_REG_ROLLBACK_STEP3 = -28;
	public static final String MSG_SUCCESSFUL_REG_ROLLBACK_STEP3 = "A regisztracio torlese sikeres.";
	
	public static final Integer CODE_ERROR_SAVE_SCHEDULE = -29;
	public static final String MSG_ERROR_SAVE_SCHEDULE = "Hiba az XML mentése közben.";
	
	public static final Integer CODE_ERROR_IN_XML_QUERY = -30;
	public static final String MSG_ERROR_IN_XML_QUERY = "Hiba történt a calendar detail lekérdezése során.";
	
	public static final Integer CODE_ERROR_GET_BNO = -31;
	public static final String MSG_ERROR_GET_BNO = "Hiba történt a BNO adatok lekérdezése közben.";

	public static final Integer CODE_SAVE_CHRDOC_ERROR = -64;
	public static final String MSG_SAVE_CHRDOC_ERROR = "p_save_chrdoc error.";
	
	public static final Integer CODE_INVALID_HEADER = -32;
	public static final String MSG_INVALID_HEADER = "Invalid header.";
	
	public static final Integer CODE_NON_UNIQUE_RESULT_EXCEPTION = -33;
	public static final String MSG_NON_UNIQUE_RESULT_EXCEPTION = "Multiple result.";
	
	public static final Integer CODE_FAILED_TO_CREATE_TEMPLATE = -34;
	public static final String MSG_FAILED_TO_CREATE_TEMPLATE = "Aktivacios e-mail osszeallitasa sikertelen. ";
	
	public static final Integer CODE_FAILED_TO_SEND_ACTIVATION = -35;
	public static final String MSG_FAILED_TO_SEND_ACTIVATION = "Aktivációs üzenet kiküldése sikertelen. ";
	
	public static final Integer CODE_FAILED_TO_SEND_ACTIVATION_SMS = -350;
	public static final String MSG_FAILED_TO_SEND_ACTIVATION_SMS = "Aktivációs SMS üzenet kiküldése sikertelen. ";
	
	public static final Integer CODE_INVALID_TAJ = -36;
	public static final String MSG_INVALID_TAJ = "Érvénytelen taj szám.";
	
	public static final Integer CODE_INVALID_BIRTH_DATE_FORMAT = -37;
	public static final String MSG_INVALID_BIRTH_DATE_FORMAT = "Érvénytelen születési dátum formátum.";
	
	public static final Integer CODE_ACT_CODE_FAILED = -38;
	public static final String MSG_ACT_CODE_FAILED = "Hiba történ a felhasználó keresése során.";
	
	public static final Integer CODE_ERROR_GET_FREEAPPS = -60;
	public static final String MSG_ERROR_GET_FREEAPPS = "Szabad időpontok lekérése sikertelen";	
	
	public static final Integer CODE_ERROR_GET_AVAIL_SERVICES = -61;
	public static final String MSG_ERROR_GET_AVAIL_SERVICES = "Hiba az elérhető szolgáltatások lekérdezése. ";
	
	public static final Integer CODE_ERROR_GET_SCHEDULE_COLORS = -62;
	public static final String MSG_ERROR_GET_SCHEDULE_COLORS = "Hiba a naptár színezés lekérdezése közben. ";
	
	public static final Integer CODE_HTML_PARSE_ERROR = -63;
	public static final String MSG_HTML_PARSE_ERROR = "Hiba tortent a html feldolgozasa soran.";
	
	public static final Integer CODE_PDF_SAVE_ERROR = -65;
	public static final String MSG_PDF_SAVE_ERROR = "Hiba tortent a pdf mentese soran.";
	
	public static final Integer CODE_NOT_PDF = -66;
	public static final String MSG_NOT_PDF = "Rossz pdf formatum.";
	
	public static final Integer CODE_SP_NOT_FOUND = -67;
	public static final String MSG_SP_NOT_FOUND = "A szolgáltatási pont nem található.";
	
	public static final Integer CODE_MULTIPLE_INSTITUTES_RESULT = -68;
	public static final String MSG_MULTIPLE_INSTITUTES_RESULT= "Több intézmény eredmény.";
	
	public static final Integer CODE_NO_WSURL_FOUND = -69;
	public static final String MSG_NO_WSURL_FOUND = "Nem található wsurl.";
	
	public static final Integer CODE_INVALID_USER_ID = -70;
	public static final String MSG_INVALID_USER_ID = "Nem megfelelő userId.";
	
	public static final Integer CODE_EMPLOYEE_WORK_TEMPLATE_LIST_EMPTY = -71;
	public static final String MSG_EMPLOYEE_WORK_TEMPLATE_LIST_EMPTY = "A mentendő templatek listája üres!";
	
	public static final Integer CODE_INVALID_WORK_TEMPLATE_ID = -72;
	public static final String MSG_INVALID_WORK_TEMPLATE_ID = "Nem megfelelő workTemplateId.";
	
	public static final Integer CODE_DATE_VALUES_NOT_PRESENT = -73;
	public static final String MSG_DATE_VALUES_NOT_PRESENT = "Az időszak kezdetének/végének megadása kötelező.";
	
	public static final Integer CODE_DURATION_IS_NOT_CORRECT = -74;
	public static final String MSG_DURATION_IS_NOT_CORRECT = "Az időszak hibásan lett megadva.";
	
	public static final Integer CODE_FAILED_TO_GET_CALENDARLIST_DATA = -75;
	public static final String MSG_FAILED_TO_GET_CALENDARLIST_DATA = "Hiba történt a calendarList meghatározásában a megadott paraméterek alapján.";
	
	public static final Integer CODE_FAILED_TO_EXTRACT_WORK_TEMPLATES = -76;
	public static final String MSG_FAILED_TO_EXTRACT_WORK_TEMPLATES = "Hiba történt a munkarend sablonok kinyerése közben.";
	
	public static final Integer CODE_NO_VALID_TEMPLATE_FOR_INTERVAL = -77;
	public static final String MSG_NO_VALID_TEMPLATE_FOR_INTERVAL = "Nem található aktív munkarend sablon a megadott inrevalumban a felhasználóhoz.";
	
	public static final Integer CODE_NO_DETAIL_FOR_TEMPLATES = -78;
	public static final String MSG_NO_DETAIL_FOR_TEMPLATES = "Nem található munkaidő szabályzat a megadott sablonokhoz.";
	
	
	public static final Integer CODE_INVALID_EK_USER_NAME = -110;
	public static final String MSG_INVALID_EK_USER_NAME = "Érvénytelen taj szám.";
	
	public static final Integer CODE_SAVE_FAILED = -300;
	public static final String MSG_SAVE_FAILED = "Sikertelen adat mentés. Egyedi azonosító: ";
	
	// LAB
	public static final Integer CODE_LAB_SAVE_ORDER_SERVICE_FAILED = -200;
	public static final String MSG_LAB_SAVE_ORDER_SERVICE_FAILED = "SAVE_ORDER_SERVICE failed.";
	public static final Integer CODE_LAB_SAVE_ORDER_SERVICE_DETAIL_FAILED = -201;
	public static final String MSG_LAB_SAVE_ORDER_SERVICE_DETAIL_FAILED = "SAVE_ORDER_SERVICE_DETAIL failed.";
	public static final Integer CODE_LAB_SEND_ORDERFAILED = -202;
	public static final String MSG_LAB_SEND_ORDER_FAILED = "SEND_ORDER failed.";
	
	public static final Integer CODE_INVALID_LAB_STATUS = -203;
	public static final String MSG_INVALID_LAB_STATUS = "Hibás lab statusz.";
	
	public static final Integer CODE_EMPTY_SESSION_ID = -204;
	public static final String MSG_EMPTY_SESSION_ID = "Üres a sessionID.";
	
	public static final Integer CODE_FAILED_UPDATE_SESSION = -205;
	public static final String FAILED_UPDATE_SESSION = "Nem sikerült frissíteni szerpkört vagy munkahelyet.";
	
	public static final Integer CODE_NO_RESULT = -206;
	public static final String MSG_NO_RESULT = "Nincs a keresésnek megfelelő eredmény";
		
	public static final Integer CODE_INVALID_PERMISSION_TYPE = -207;
	public static final String MSG_INVALID_PERMISSION_TYPE = "Érvénytelen szerepkör!";
	
	public static final Integer CODE_MISSING_SERVICE_POINT_ID = -208;
	public static final String MSG_MISSING_SERVICE_POINT_ID = "Hiányzó Ellátási pont azonosító!";
	
	public static final Integer CODE_MISSING_TEMPLATE_LIST = -209;
	public static final String MSG_MISSING_TEMPLATE_LIST = "Hiányzó sablon lista!";

	public static final Integer CODE_MISSING_TEMPLATE_DETAIL_LIST = -210;
	public static final String MSG_MISSING_TEMPLATE_DETAIL_LIST = "Hiányzó részletes sablon!";
	
	public static final Integer CODE_MISSING_EVENT_TYPE_FK = -211;
	public static final String MSG_MISSING_EVENT_TYPE_FK = "Hiányzó esemény típus azonosító!";
 	
	public static final Integer CODE_MISSING_NAME = -212;
	public static final String MSG_MISSING_NAME = "Hiányzó név!";
	
	public static final Integer CODE_MISSING_TEMPLATE_ID_LIST = -213;
	public static final String MSG_MISSING_TEMPLATE_ID_LIST = "Hiányzó sablon azonosítók!";
	
	public static final Integer CODE_MISSING_DATE_FROM = -214;
	public static final String MSG_MISSING_DATE_FROM = "Hiányzó kezdődátum!";
	
	public static final Integer CODE_MISSING_DATE_TO = -215;
	public static final String MSG_MISSING_DATE_TO = "Hiányzó végdátum!";
	
	public static final Integer CODE_MISSING_SCHEDULE_STATUS = -216;
	public static final String MSG_MISSING_SCHEDULE_STATUS = "Hiányzó időpont állapot!";
	
	public static final Integer CODE_MISSING_TEMPLATE_DETAIL_ID_LIST = -217;
	public static final String MSG_MISSING_TEMPLATE_DETAIL_ID_LIST = "Hiányzó sablon azonosító!";
	
	public static final Integer CODE_MISSING_SCHEDULE_ID_LIST = -218;
	public static final String MSG_MISSING_SCHEDULE_ID_LIST = "Hiányzó időpont azonosító!";
	
	public static final Integer CODE_MISSING_DELETE_SCHEDULE = -219;
	public static final String MSG_MISSING_DELETE_SCHEDULE = "Nincs megadva az időpont törtlése!";
	
	public static final Integer CODE_MISSING_TEMPLATE_ID = -220;
	public static final String MSG_MISSING_TEMPLATE_ID = "Hiányzó sablon azonosító!";
	
	public static final Integer CODE_MISSING_CONTINGENT_GROUP_NAME = -221;
	public static final String MSG_MISSING_CONTINGENT_GROUP_NAME = "Hiányzó kontingens csoport név!";
	
	public static final Integer CODE_MISSING_ROLE_ID = -222;
	public static final String MSG_MISSING_ROLE_ID = "Hiányzó szerepkör azanosító!";
	
	public static final Integer CODE_MISSING_ID = -223;
	public static final String MSG_MISSING_ID = "Hiányzó azonosító!";
	
	public static final Integer CODE_MISSING_HEADER_ID = -224;
	public static final String MSG_MISSING_HEADER_ID = "Hiányzó header azonosító!";
	
	public static final Integer CODE_MISSING_CONTINGENT_ID = -225;
	public static final String MSG_MISSING_CONTINGENT_ID = "Hiányzó kontingens azonosító!";
	
	public static final Integer CODE_MISSING_PERMISSION_TYPE = -226;
	public static final String MSG_MISSING_PERMISSION_TYPE = "Hiányzó engedély típus!";
	
	public static final Integer CODE_MISSING_SPECIAL_DAY_LIST = -227;
	public static final String MSG_MISSING_SPECIAL_DAY_LIST = "Hiányzó szabadnap!";
	
	public static final Integer CODE_MISSING_SCHEDULE_LIST = -228;
	public static final String MSG_MISSING_SCHEDULE_LIST = "Hiányzó időpont!";
	
	public static final Integer CODE_MISSING_SPECIAL_DAY_ID = -229;
	public static final String MSG_MISSING_SPECIAL_DAY_ID = "Hiányzó szabadnap azonosító!";
	
	public static final Integer CODE_MISSING_SERVICE_FK = -230;
	public static final String MSG_MISSING_SERVICE_FK = "Hiányzó szolgáltatás azonosító!";
	
	public static final Integer CODE_INVALID_SESSION_ID = -231;
	public static final String MSG_INVALID_SESSION_ID = "Invalid session.";
	
	public static final Integer CODE_MISSING_ROLE_TO_USER_LIST = -232;
	public static final String MSG_MISSING_ROLE_TO_USER_LIST = "Hiányzó szerepkör lista!";
	
	public static final Integer CODE_FETER_ROOT_ELEMENT_ID_NOT_FOUND_FOR_SP = -232;
	public static final String MSG_FETER_ROOT_ELEMENT_ID_NOT_FOUND_FOR_SP = "A megadott szervezeti egység nincs felvéve a fekvőbeteg ellátó modulba.";
	
	public static final Integer CODE_NO_PERMISSION = -233;
	public static final String MSG_NO_PERMISSION = "Nincs jogosultság a felhasználónak.";
	
	public static final Integer CODE_INVALID_DSC_DATE = -101;
	public static final String MSG_INVALID_DSC_DATE = "Az elbocsátás dátuma nem előzheti meg az érkeztetés dátumát!";
	
	public static final Integer CODE_ERROR_SAVE_INVOICE = -5001;
	public static final String MSG_ERROR_SAVE_INVOICE = "Hiba tortent a szamla bekuldese soran.";
	
	public static final String MSG_ERROR_TEMPLATE = "Nem talalhato ilyen sablon.";

	public static final Integer CODE_FAILED_TO_MAP_XML_DETAILS = -234;
	public static final String MSG_FAILED_TO_MAP_XML_DETAILS = "Hiba tortent az xml adatok osszeallitasa kozben!";
	
	// ECM
	public static final Integer CODE_ECM_UNREACHABLE = -5999;
	public static final String MSG_ECM_UNREACHABLE = "Az ecm nem elérhető.";
	
	public static final Integer CODE_FAILED_REQUEST = -5998;
	public static final String MSG_FAILED_REQUEST = "Hiba történt az ecm kérés során.";
	public static final String MSG_FAILED_REQUEST_ID = "Hiba történt az ecm kérés során. Egyedi azonosító: ";
	
	public static final Integer CODE_ECM_ERROR_CODE = -5997;
	
	public static final Integer CODE_NO_EESZT_DATA_TO_SP = -5996;
	public static final String MSG_NO_EESZT_DATA_TO_SP = "Nem tartozik eeszt adat a szolgáltatási ponthoz.";
	
	public static final Integer CODE_NO_EESZT_DATA_TO_USER = -5995;
	public static final String MSG_NO_EESZT_DATA_TO_USER = "Nem tartozik eeszt adat a felhasznalohoz.";
	
	
	// HÁLÓZATI ELÉRÉS ]200, 250[
	public static final Integer CODE_FAILED_TO_CREATE_STUB = -200;
	public static final String MSG_FAILED_TO_CREATE_STUB = "A kulso alkalmazas elerese sikertelen. ";
	
	public static final Integer CODE_POLIP_STUB_CREATION_FAILED = -201;
	public static final String MSG_POLIP_STUB_CREATION_FAILED = "A polip példány elérése sikertelen.";
	
	public static final Integer CODE_FAILED_TO_CALL_CALLER_SYSTEM = -205;
	public static final String MSG_FAILED_TO_CALL_CALLER_SYSTEM = "A behívó rendszer elérése sikertelen";
	
	public static final Integer CODE_FAILED_TO_CREATE_DLM_STUB = -206;
	public static final String MSG_FAILED_TO_CREATE_DLM_STUB = "Hiba történt a dlm elérése során.";
	
	public static final Integer CODE_WS_RESPONSE_NULL = -210;
	public static final String MSG_WS_RESPONSE_NULL = "A service hivas visszateresi erteke null.";
	
	// EESZT - SOA CDM 6000 - 6100
	public static final Integer CODE_OTP_FAILED = -6001;
	public static final String MSG_OTP_FAILED = "Az otp token kérése sikertelen.";
	
	public static final Integer CODE_EID_FAILED = -6002;
	public static final String MSG_EID_FAILED = "RID token kérése sikertelen.";
	
	public static final Integer CODE_ESZIG_FAILED = -6003;
	public static final String MSG_ESZIG_FAILED = "RID token kérése sikertelen.";
	
	public static final Integer CODE_X509_FAILED = -6005;
	public static final String MSG_X509_FAILED = "RID token kérése sikertelen.";
	
	public static final Integer CODE_MOBILTOKEN_FAILED = -6006;
	public static final String MSG_MOBILTOKEN_FAILED = "Mobil token kérése sikertelen.";
	
	// EESZT - EKAT 6200 - 6300
	
	public static final Integer CODE_FAILED_TO_FILTER_BCP_LIST = -6200;
	public static final String MSG_FAILED_TO_FILTER_BCP_LIST = "Hiba történt a bcp üzenetek csoportosítása során.";

	public static final Integer CODE_FAILED_TO_SEND_BCP_MESSAGES_TO_EESZT = -6201;
	public static final String MSG_FAILED_TO_SEND_BCP_MESSAGES_TO_EESZT = "Hiba történt a bcp üzenetek EESZT-be küldése során.";
	
	public static final Integer CODE_NO_TOR_DATA_FOUND = -6202;
	public static final String MSG_NO_TOR_DATA_FOUND = "Nem található a feltételnek megfelelő TOR adat.";
	
	public static final String MSG_NO_CHANGES_FOUND_FOR_EKAT = "Nincs változás az EKAT üztenetekben.";
	
	public static final Integer CODE_NO_ADD_EVENT_ENTRY_FOUND_FOR_CALID = -6203;
	public static final String MSG_NO_ADD_EVENT_ENTRY_FOUND_FOR_CALID = "Nincs a feltételeknek megfelelő EKAT üzenet a frissítéshez!";
	
	public static final Integer CODE_FAILED_TO_FIND_BCP_ELEMENT = -6204;
	public static final String MSG_FAILED_TO_FIND_BCP_ELEMENT = "Hiba történt a BCP üzenet kinyerése közben!";
	
	public static final Integer CODE_NO_PRE_SENT_EKAT_MESSAGE_FOUND = -6205;
	public static final String MSG_NO_PRE_SENT_EKAT_MESSAGE_FOUND = "Az ekat update csak az add eventEntry után küldhető be!";
	
	public static final Integer CODE_FAILED_TO_DELETE_EKAT_EVENT_ENTRY = -6206;
	public static final String MSG_FAILED_TO_DELETE_EKAT_EVENT_ENTRY = "Az ekat event entry törlése siekreteln!";
	
	public static final Integer CODE_BREAKGLASS_OK_REQUIRED = -6207;
	public static final String MSG_BREAKGLASS_OK_REQUIRED = "A sürgősségi adatlekérés indoklását kötelező megadni!";
	
	
	// POLIP 6301 - 6400
	public static final Integer CODE_NULL_POLIP_RESPONSE = -6301;
	public static final String MSG_NULL_POLIP_RESPONSE = "Null polip response - ";
	
	// EESZT - EGYEB 6500 - 6510
	public static final Integer CODE_RRS_URL_NOT_SET = -6500;
	public static final String MSG_RRS_URL_NOT_SET = "Az rss hírcsatorna nincs beállítva!";

	public static final Integer CODE_EESZT_SESSION_NOT_FOUND= -6501;
	public static final String MSG_EESZT_SESSION_NOT_FOUND = "A funkciók használatához elsőnek be kell jelentkeznie az EESZT-be!";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

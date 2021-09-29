package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import hu.econsult.is4.service.CalendarListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/rest/is4/calendar")
public class CalendarListController {

	private final CalendarListService calendarListService;

	@Autowired
	public CalendarListController(CalendarListService calendarListService) {
		super();
		this.calendarListService = calendarListService;
	}
	
	@ApiOperation(value = "Elérhető szolgáltatások visszatérítése.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"etId\": 28642942,\r\n" + 
			"  \"spId\": null,\r\n" + 
			"  \"doctorId\": null\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"serviceList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"etID\": 28642942,\r\n" + 
					"      \"etName\": \"Urológia\",\r\n" + 
					"      \"spID\": 4518743,\r\n" + 
					"      \"spName\": \"Urológia 4. szakrendelés RI(Debrecen)\",\r\n" + 
					"      \"doctorID\": 4525825,\r\n" + 
					"      \"doctorName\": \"Bene Fruzsina\",\r\n" + 
					"      \"servicesID\": 28642963\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResAvailServices.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResAvailServices.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getavailservicescal")
	public IS4ResAvailServices2 getAvailServicesCal(@RequestBody IS4ReqAvailService data) {
		return calendarListService.getAvailServicesCal(data);
	}
	
	@ApiOperation(value = "Előjegyzések, szabad időpontok visszatérítése.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5B17B1CE63491BBE05011AC0D093987\",\r\n" + 
			"  \"spId\": null,\r\n" + 
			"  \"etId\": 21951192,\r\n" + 
			"  \"doctorId\": null,\r\n" + 
			"  \"dateFrom\": \"2020-05-11 00:00\",\r\n" + 
			"  \"dateTo\": \"2020-05-15 23:59\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"calendarList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12905383,\r\n" + 
					"      \"dateFrom\": \"2020-05-11T06:00:00.000+0000\",\r\n" + 
					"      \"dateTo\": \"2020-05-11T06:30:00.000+0000\",\r\n" + 
					"      \"status\": \"F\",\r\n" + 
					"      \"doctorFk\": 4532053,\r\n" + 
					"      \"doctorName\": \"Dr. Bársony Tamás\",\r\n" + 
					"      \"eventTypeFk\": 21951192,\r\n" + 
					"      \"eventTypeName\": \"Belgyógyászat Diabetológia\",\r\n" + 
					"      \"servicePointFk\": 4510142,\r\n" + 
					"      \"servicePointName\": \"Belgyógyászat Diabetológia 1. szakambulancia\",\r\n" + 
					"      \"serviceFk\": 28419922,\r\n" + 
					"      \"detail\": \"xml file\",\r\n" + 
					"      \"calInfo\": \"lastModifiedTime=2020-05-14 08:30,lastModifiedUser=Unknown user\"\r\n" + 
					"    }\r\n" + 
					"    ...\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstCal.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstCal.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getinstitutecalendar")
	public IS4ResGetInstCal getInstCalendar(@RequestBody IS4ReqGetInstCal data) {
		return calendarListService.getInstCalendar(data);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstCalHistory.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getinstitutecalendarhistory")
	public IS4ResGetInstCalHistory getInstCalendarHistory(@RequestBody IS4ReqGetInstCal request) {
		return calendarListService.getInstCalendarHistory(request);
	}
	
	@ApiOperation(value = "Előjegyzés törlése.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5B17B1CE63491BBE05011AC0D093987\",\r\n" + 
			"  \"scheduleIdList\": \"\",\r\n" + 
			"  \"calIdList\": \"12931593\",\r\n" + 
			"  \"mode\": \"DEL\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres törlés.\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("deletecalendar")
	public Response deleteCalendar(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.deleteCalendar(data);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("donecalendar")
	public Response doneCalendar(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.doneCalendar(data);
	}
	
	@ApiOperation(value = "Időpont zárolása.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5B17B1CE63491BBE05011AC0D093987\",\r\n" + 
			"  \"scheduleIdList\": \"12905383\",\r\n" + 
			"  \"calIdList\": \"\",\r\n" + 
			"  \"mode\": \"LOCK\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres zárolás.\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("lockschedule")
	public Response lockSchedule(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.lockSchedule(data);
	}
	
	@ApiOperation(value = "Időpont feloldása.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5B17B1CE63491BBE05011AC0D093987\",\r\n" + 
			"  \"scheduleIdList\": \"12905383\",\r\n" + 
			"  \"calIdList\": \"\",\r\n" + 
			"  \"mode\": \"UNLOCK\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres feloldás.\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("unlockschedule")
	public Response unlockSchedule(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.unlockSchedule(data);
	}
	
	@ApiOperation(value = "Előjegyzés felszabadítása.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5E892E25F76F5AEE05011AC0D0960DE\",\r\n" + 
			"  \"calIdList\": \"12931785\",\r\n" + 
			"  \"mode\": \"FREE\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres felszabadítás.\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("freecalendar")
	public Response freeCalendar(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.freeCalendar(data);
	}
	
	@ApiOperation(value = "Időpont feloldása.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5E892E25F76F5AEE05011AC0D0960DE\",\r\n" + 
			"  \"calIdList\": \"12932212\",\r\n" + 
			"  \"scheduleIdList\": 12905663,\r\n" + 
			"  \"mode\": \"MOD\",\r\n" + 
			"  \"detail\": \"xml file\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 12932216,\r\n" + 
					"  \"message\": \"Sikeres módosítás.\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("modifycalendar")
	public Response modifyCalendar(@RequestBody IS4ReqSaveSchedule data) {
		return calendarListService.modifyCalendar(data);
	}
	
	@ApiOperation(value = "Megvizsgálja, hogy a felhasználónak van-e jogosultsága az adott művelethez.", notes = "<h3>Példa hívás:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A5E892E25F76F5AEE05011AC0D0960DE\",\r\n" + 
			"  \"spId\": 4510142,\r\n" + 
			"  \"etId\": 21951192,\r\n" + 
			"  \"permissionType\": \"ADMIN\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>Példa kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("checkuserhaspermission")
	public Response checkUserIsAdmin(@RequestBody IS4ReqCheckUserIsAdmin data) {
		return calendarListService.checkUserHasPermission(data);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstCal.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getcalendarbypatient")
	public IS4ResGetInstCal getCalendarByPatient(@RequestBody IS4ReqGetCalendarByPatient data) {
		return calendarListService.getCalendarByPatient(data);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstCal.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getcalendarbyalias")
	public IS4ResGetInstCal getCalendarByAlias(@RequestBody IS4ReqGetCalendarByAlias data) {
		return calendarListService.getCalendarByAlias(data);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetSendData.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getsenddata")
	public IS4ResGetSendData geSendData(@RequestBody IS4ReqGetSendData request) {
		return calendarListService.getSendData(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("modsenddata")
	public Response modifyPatData(@RequestBody IS4ReqModSendData req) {
		return calendarListService.modifyPatData(req);
	}
}

package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import hu.econsult.is4.service.AppointmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("rest/is4/appointment")
public class AppointmentController {

	private final AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}
	
	@ApiOperation(value = "Szabad id??ponttal rendelkez?? orvosok lek??r??se.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"doctorList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4526359,\r\n" + 
					"      \"name\": \"Bab??k Gy??rgyi\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4528420,\r\n" + 
					"      \"name\": \"Bagdin?? Major Julianna\"\r\n" + 
					"    },\r\n" + 
					"    ...\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstUser.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstUser.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getinstdoctor")
	public IS4ResGetInstUser getInstDoctor(@RequestBody IS4ReqGetInstData data) {
		return appointmentService.getInstDoctor(data);
	}
	
	@ApiOperation(value = "Azok a szolg??ltat??si pontok visszat??r??t??se, amelyek rendelkeznek szabad id??ponttal.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"spList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4510499,\r\n" + 
					"      \"name\": \"Belgy??gy??szat 4. szakrendel??s RI(Debrecen)\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4510800,\r\n" + 
					"      \"name\": \"B??rgy??gy??szat 5. szakrendel??s, Bajcsy Zs.(Debrecen)\"\r\n" + 
					"    },\r\n" + 
					"    ...\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElement.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstElement.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getinstsp")
	public IS4ResGetInstituteData getInstSp(@RequestBody IS4ReqGetInstData data) {
		return appointmentService.getInstSp(data);
	}
	
	@ApiOperation(value = "Azok a szolg??ltat??si t??pusok visszat??r??t??se, amelyek rendelkeznek szabad id??ponttal.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"etList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 28419833,\r\n" + 
					"      \"name\": \"Belgy??gy??szat\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 29311321,\r\n" + 
					"      \"name\": \"B??rgy??gy??szat\"\r\n" + 
					"    },\r\n" + 
					"    ...\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElement.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstElement.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getinstet")
	public IS4ResGetInstElement getInstEt(@RequestBody IS4ReqGetInstData data) {
		return appointmentService.getInstEt(data);
	}
	
	@ApiOperation(value = "El??rhet?? szolg??ltat??sok visszat??r??t??se.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
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
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"serviceList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"etID\": 28642942,\r\n" + 
					"      \"etName\": \"Urol??gia\",\r\n" + 
					"      \"spID\": 4518743,\r\n" + 
					"      \"spName\": \"Urol??gia 4. szakrendel??s RI(Debrecen)\",\r\n" + 
					"      \"doctorID\": 4525825,\r\n" + 
					"      \"doctorName\": \"Bene Fruzsina\",\r\n" + 
					"      \"servicesID\": 28642963\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResAvailServices.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResAvailServices.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getavailservices")
	public IS4ResAvailServices2 getAvailServices(@RequestBody IS4ReqAvailService data) {
		return appointmentService.getAvailServices(data);
	}
	
	@ApiOperation(value = "Foglalt??si inform??ci??k visszat??r??t??sre napora bontva.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"eventTypeId\": 28642942,\r\n" + 
			"  \"doctorId\": null,\r\n" + 
			"  \"servicePointId\": null,\r\n" + 
			"  \"dateFrom\": \"\",\r\n" + 
			"  \"dateTo\": \"\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"objScheduleColor\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 6,\r\n" + 
					"      \"dateFrom\": \"2020-04-19T22:00:00.000+0000\",\r\n" + 
					"      \"colorCode\": \"G\",\r\n" + 
					"      \"eventMax\": 18,\r\n" + 
					"      \"eventFree\": 18,\r\n" + 
					"      \"eventCont\": 0\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 7,\r\n" + 
					"      \"dateFrom\": \"2020-04-20T22:00:00.000+0000\",\r\n" + 
					"      \"colorCode\": \"G\",\r\n" + 
					"      \"eventMax\": 18,\r\n" + 
					"      \"eventFree\": 18,\r\n" + 
					"      \"eventCont\": 0\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResScheduleColor.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResScheduleColor.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getschedulecolorlist")
	public IS4ResScheduleColor getScheduleColorList(@RequestBody IS4ReqScheduleColor data) {
		return appointmentService.getScheduleColorList(data);
	}
	
	@ApiOperation(value = "Szabad id??pontok visszat??r??t??sre adott intervallumra.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"spId\": null,\r\n" + 
			"  \"etId\": 28642942,\r\n" + 
			"  \"doctorId\": null,\r\n" + 
			"  \"dateFrom\": \"2020-04-27 00:00\",\r\n" + 
			"  \"dateTo\": \"2020-04-30 23:59\",\r\n" + 
			"  \"mode\": 1\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"appointmentList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 72,\r\n" + 
					"      \"dateFrom\": \"2020-04-27T06:00:00.000+0000\",\r\n" + 
					"      \"dateTo\": \"2020-04-27T06:20:00.000+0000\",\r\n" + 
					"      \"scheduleFk\": 12874124,\r\n" + 
					"      \"scheduleStatus\": \"F\",\r\n" + 
					"      \"servicePointName\": \"Urol??gia 4. szakrendel??s RI(Debrecen)\",\r\n" + 
					"      \"eventTypeName\": \"Urol??gia\",\r\n" + 
					"      \"serviceIdFk\": 28642963,\r\n" + 
					"      \"scheduleColor\": \"COLOR_3\",\r\n" + 
					"      \"serviceDetail\": \"xml doc\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResAppointment.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResAppointment.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getappointmentlist")
	public IS4ResAppointment getAppointmentList(@RequestBody IS4ReqAppointment req) {
		return appointmentService.getAppointmentList(req);
	}
	
	@ApiOperation(value = "Kiv??lasztott id??pont ment??se.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"scheduleId\": 12873780,\r\n" + 
			"  \"mode\": \"NEW\",\r\n" + 
			"  \"detail\": \"xml doc\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 12886214,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("saveschedule")
	public Response saveSchedule(@RequestBody IS4ReqSaveSchedule req) {
		return appointmentService.saveSchedule(req);
	}
	
	@ApiOperation(value = "Ellen??rz??se, hogy a kiv??lasztott napra van-e a felhaszn??l??nak az adott szolg??ltat??sra m??r foglalt id??pontja.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"patHisId\": \"\",\r\n" + 
			"  \"patNauId\": \"86AAE5BE-389D-4CF4-93FC-2481805E8B5F\",\r\n" + 
			"  \"alias\": \"\",\r\n" + 
			"  \"serviceId\": 28419873,\r\n" + 
			"  \"date\": \"2020-04-30 16:40\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 0,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("checksamecal")
	public Response checkSameCal(@RequestBody IS4ReqCheckSameCal req) {
		return appointmentService.checkSameCal(req);
	}
	
	
	@ApiOperation(value = "Tov??bbi inform??ci??k visszat??r??t??se adott id??ponthoz.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\",\r\n" + 
			"  \"scheduleId\": 12873759\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"xml doc\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getscheduledetail")
	public Response getScheduleDetail(@RequestBody IS4ReqGetScheduleDetail req) {
		return appointmentService.getScheduleDetail(req);
	}
	
	@ApiOperation(value = "Megvizsg??lja, hogy az adott kontingens id??pontokhoz van-e jogosults??ga foglalni.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"A3F09C6A9CC3BFADE05011AC0D094D00\",\r\n" + 
			"  \"scheduleList\": \"12889861\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"code\": 1,\r\n" + 
			"  \"message\": \"OK\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("checkcontlist")
	public Response checkContingentList(@RequestBody IS4ReqCheckContList req) {
		return appointmentService.checkContingentList(req);
	}
	
	@ApiOperation(value = "Visszat??r??ti az adott el??jegyz??shez tartoz?? inform??ci??kat.", notes = "<h3>P??lda h??v??s:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"calId\": \"12931591\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"code\": 1,\r\n" + 
			"  \"message\": \"xml file\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getcalendardetail")
	public IS4ResGetCalendarDetail getCalendarDetail(@RequestBody IS4ReqGetCalendarDetail req) {
		return appointmentService.getCalendarDetail(req);
	}

	@GetMapping("getallbno")
	public IS4ResGetInstElement getAllBno() {
		return appointmentService.getAllBno();
	}
	
	@ApiOperation(value = "Pecs??tsz??m vagy ANTSZ sz??m alapj??n visszat??r??ti az orvos nev??t ??s a hozz?? kapcsolt bek??ld?? int??zm??nyeket. ", 
			notes = 
			"<h3>P??lda h??v??s:</h3>\r\n" + 
			"<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"   \"messageHeader\": {\r\n" + 
			"    \"currentTapass\": \"290082269889\",\r\n" + 
			"    \"currentRole\": \"AUTH$SZEPLESZEL_CS\",\r\n" + 
			"    \"currentServicePointId\": \"12403224\",\r\n" + 
			"    \"locale\": \"hu\",\r\n" + 
			"    \"deviceId\": \"192.123.123.123\"\r\n" + 
			"  },\r\n" + 
			"  \"sealNumber\": \"23919\",\r\n" + 
			"  \"spCode\": \"110090078\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>P??lda kimenet:</h3>\r\n" + 
			"<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"code\": 1,\r\n" + 
			"  \"message\": \"OK\",\r\n" + 
			"  \"doctors\": [\r\n" + 
			"    {\r\n" + 
			"      \"id\": 19852,\r\n" + 
			"      \"doctorSeal\": \"23919\",\r\n" + 
			"      \"spCode\": \"110090078\",\r\n" + 
			"      \"doctorName\": \"Dr. Zs??t??r Andor\",\r\n" + 
			"      \"spName\": \"Kom??rom-Esztergom-Dr. Zs??t??r  BT - Dr. Zs??t??r Andor\"\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>",
			response = NEUResGetDoctorToServicePoint.class)
	@ApiResponses(value = {
			@ApiResponse(response = NEUResGetDoctorToServicePoint.class, code = 200, message = "Sikeres lek??r??s"),
	})
	
	@PostMapping("getdoctortosp")
	public NEUResGetDoctorToServicePoint getDoctorToServicePoint(@RequestBody NEUReqGetDoctorToServicePoint req) {
		return appointmentService.getDoctorToServicePoint(req);
	}
	
	
}

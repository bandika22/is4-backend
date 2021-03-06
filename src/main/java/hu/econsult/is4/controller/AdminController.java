package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import hu.econsult.is4.model.IS4ResScheduleColor;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/rest/is4/adminfunction")
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@ApiOperation(value = "El??rhet?? sablonok visszat??r??t??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"permissionType\": \"ADMIN\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"scheduleTemplateList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"name\": \"Dr. Bed?? teszt\",\r\n" + 
					"      \"serviceFk\": 28399667,\r\n" + 
					"      \"eventTypeFk\": 28399571,\r\n" + 
					"      \"eventTypeName\": \"Kardiol??gia\",\r\n" + 
					"      \"servicePointFk\": 4513398,\r\n" + 
					"      \"servicePointName\": \"Kardiol??gia 8. szakrendel??s, J??zsa\",\r\n" + 
					"      \"lastGeneratedSchedule\": \"2020-04-15 16:40\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 2,\r\n" + 
					"      \"name\": \"JTR 8\",\r\n" + 
					"      \"serviceFk\": 29312489,\r\n" + 
					"      \"eventTypeFk\": 29311321,\r\n" + 
					"      \"eventTypeName\": \"B??rgy??gy??szat\",\r\n" + 
					"      \"servicePointFk\": 4510800,\r\n" + 
					"      \"servicePointName\": \"B??rgy??gy??szat 5. szakrendel??s, Bajcsy Zs.\",\r\n" + 
					"      \"lastGeneratedSchedule\": \"2020-04-30 08:00\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 3,\r\n" + 
					"      \"name\": \"IS_4.3\",\r\n" + 
					"      \"serviceFk\": 12904597,\r\n" + 
					"      \"eventTypeFk\": 28399571,\r\n" + 
					"      \"eventTypeName\": \"Kardiol??gia\",\r\n" + 
					"      \"servicePointFk\": 12894665,\r\n" + 
					"      \"servicePointName\": \"Feln??tt Kardiol??gia - Dr. Bed?? Zolt??n\",\r\n" + 
					"      \"lastGeneratedSchedule\": \"2020-05-29 17:30\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetScheduleTemplates.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetScheduleTemplates.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getscheduletemplates")
	public IS4ResGetScheduleTemplates getScheduleTemplates(@RequestBody IS4ReqGetScheduleTemplates data) {
		return adminService.getScheduleTemplates(data);
	}
 
	@ApiOperation(value = "El??rhet?? munkahelyek visszaad??sa amikkel sablont hozhatunk l??tre.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"permissionType\": \"ADMIN\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"spList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4509806,\r\n" + 
					"      \"name\": \"Allergol??gia 3. gyermek szakrendel??s RI(Debrecen)\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4509827,\r\n" + 
					"      \"name\": \"Allergol??gia 4. gyermek szakrendel??s RI(Debrecen)\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 31333002,\r\n" + 
					"      \"name\": \"Allergol??gia 6. szakrendel??s, Jerik?? u.(Debrecen)\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElement.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstElement.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getallsp")
	public IS4ResGetInstElement getTemplateSp(@RequestBody IS4ReqGetInstData data) {
		return adminService.getAllSp(data);
	}
 	
	@ApiOperation(value = "El??rhet?? szolg??ltat??sok visszaad??sa amikkel sablont hozhatunk l??tre.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"permissionType\": \"ADMIN\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"etList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 29338445,\r\n" + 
					"      \"name\": \"Aneszteziol??gia\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 720,\r\n" + 
					"      \"name\": \"Bel.Gasztro. 1.  Ambulancia\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 721,\r\n" + 
					"      \"name\": \"Bel.Gasztro. 1. Colonoscopia\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 722,\r\n" + 
					"      \"name\": \"Bel.Gasztro. 1. Gastroscopia\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElement.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstElement.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getallet")
	public IS4ResGetInstElement getTemplateEt(@RequestBody IS4ReqGetInstData data) {
		return adminService.getAllEt(data);
	}
 	
	@ApiOperation(value = "El??rhet?? kontingensek visszaad??sa amikkel sablont hozhatunk l??tre.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"permissionType\": \"ADMIN\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"contingentList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 11927236,\r\n" + 
					"      \"name\": \"teszt\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12887186,\r\n" + 
					"      \"name\": \"Bel3-BED\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12707660,\r\n" + 
					"      \"name\": \"Int??zm??nyi ambul??ns munkahelyek\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetTemplateCont.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetTemplateCont.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("gettemplatecont")
	public IS4ResGetTemplateCont getTemplateCont(@RequestBody IS4ReqGetTemplateCont data) {
		return adminService.getTemplateCont(data);
	}
 	
	@ApiOperation(value = "El??rhet?? orvosok visszaad??sa az adott munkahelyen akikkel sablont hozhatunk l??tre.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"spId\": 4509806\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"doctorList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4525825,\r\n" + 
					"      \"name\": \"Bene Fruzsina\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4525342,\r\n" + 
					"      \"name\": \"Bercs??nn?? Juh??sz ??va\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 11551318,\r\n" + 
					"      \"name\": \"Bihari Vivien\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstUser.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstUser.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("gettemplatedoc")
	public IS4ResGetInstUser getTemplateDoc(@RequestBody IS4ReqGetTemplateDoc data) {
		return adminService.getTemplateDoc(data);
	}
 	
	@ApiOperation(value = "Sablon fejl??c(ek) ment??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"templateList\": [\r\n" + 
					"    {\r\n" + 
					"      \"name\": \"swagger teszt\",\r\n" + 
					"      \"timeFrom\": \"08:00\",\r\n" + 
					"      \"timeTo\": \"10:00\",\r\n" + 
					"      \"eventMax\": 20,\r\n" + 
					"      \"eventMaxSame\": 1,\r\n" + 
					"      \"eventTypeFk\": 743,\r\n" + 
					"      \"servicePointFk\": 4509806,\r\n" + 
					"      \"scheduleComment\": \"SWAGGER COMMENT\",\r\n" + 
					"      \"color\": \"COLOR_1\",\r\n" + 
					"      \"monday\": \"1\",\r\n" + 
					"      \"tuesday\": \"1\",\r\n" + 
					"      \"wednesday\": \"1\",\r\n" + 
					"      \"thursday\": \"1\",\r\n" + 
					"      \"friday\": \"1\",\r\n" + 
					"      \"saturday\": \"1\",\r\n" + 
					"      \"sunday\": \"1\",\r\n" + 
					"      \"contingentGroupFk\": 12166965,\r\n" + 
					"      \"week\": \"FULL\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResSaveScheduleTemplate.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResSaveScheduleTemplate.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("savescheduletemplates")
	public IS4ResSaveScheduleTemplate saveScheduleTemplate(@RequestBody IS4ReqSaveScheduleTemplates req) {
		return adminService.saveScheduleTemplates(req);
	}
	
	@ApiOperation(value = "Sablon r??szletek ment??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"templateDetailList\": [\r\n" + 
					"    {\r\n" + 
					"      \"templateId\": null,\r\n" + 
					"      \"timeFrom\": \"08:00\",\r\n" + 
					"      \"timeTo\": \"10:00\",\r\n" + 
					"      \"userFk\": null,\r\n" + 
					"      \"scheduleComment\": \"comment\",\r\n" + 
					"      \"color\": \"COLOR_1\",\r\n" + 
					"      \"scheduleTemplateFk\": 12943280,\r\n" + 
					"      \"contingentGroupFk\": null,\r\n" + 
					"      \"day\": \"1\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResSaveScheduleTemplate.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResSaveScheduleTemplate.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("savescheduletemplatedetails")
	public IS4ResSaveScheduleTemplate saveScheduleTemplateDetails(@RequestBody IS4ReqSaveScheduleTemplateDetails req) {
		return adminService.saveScheduleTemplateDetails(req);
	}
	
	@ApiOperation(value = "Sablon fejl??cek visszat??r??t??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A79480647073813FE05011AC0D096218\",\r\n" + 
					"  \"name\": \"swagger teszt\",\r\n" + 
					"  \"eventTypeFk\": 743,\r\n" + 
					"  \"servicePointFk\": 4509806\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"objGetScheduleTemplateHeadersList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12943280,\r\n" + 
					"      \"timeFrom\": \"08:00\",\r\n" + 
					"      \"timeTo\": \"10:00\",\r\n" + 
					"      \"eventMax\": 20,\r\n" + 
					"      \"eventMaxSame\": 1,\r\n" + 
					"      \"scheduleComment\": \"SWAGGER COMMENT\",\r\n" + 
					"      \"color\": \"COLOR_1\",\r\n" + 
					"      \"monday\": \"1\",\r\n" + 
					"      \"tuesday\": \"1\",\r\n" + 
					"      \"wednesday\": \"1\",\r\n" + 
					"      \"thursday\": \"1\",\r\n" + 
					"      \"friday\": \"1\",\r\n" + 
					"      \"saturday\": \"1\",\r\n" + 
					"      \"sunday\": \"1\",\r\n" + 
					"      \"contingentGroupFk\": 12166965,\r\n" + 
					"      \"week\": \"FULL\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetScheduleTemplateHeaders.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetScheduleTemplateHeaders.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getscheduletemplateheaders")
	public IS4ResGetScheduleTemplateHeaders getScheduleTemplateHeaders(@RequestBody IS4ReqGetScheduleTemplateHeaders req) {
		return adminService.getScheduleTemplateHeaders(req);
	}
	
	@ApiOperation(value = "Sablonhoz tartoz?? r??szletek visszat??r??t??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7B5F89D2EC3706BE05011AC0D093440\",\r\n" + 
					"  \"templateIdList\": \"12942949,12942950,12942947\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"resultMap\": {\r\n" + 
					"    \"Kedd\": [\r\n" + 
					"      {\r\n" + 
					"        \"id\": 12945826,\r\n" + 
					"        \"timeInterval\": \"10:00 - 10:40\",\r\n" + 
					"        \"doctor\": {\r\n" + 
					"          \"id\": 45289382,\r\n" + 
					"          \"name\": \"Bucsk?? Andrea\"\r\n" + 
					"        },\r\n" + 
					"        \"contingent\": {},\r\n" + 
					"        \"comments\": \"andika komemntje123\"\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"        \"id\": 12945827,\r\n" + 
					"        \"timeInterval\": \"10:00 - 10:40\",\r\n" + 
					"        \"doctor\": {\r\n" + 
					"          \"id\": 45289382,\r\n" + 
					"          \"name\": \"Bucsk?? Andrea\"\r\n" + 
					"        },\r\n" + 
					"        \"contingent\": {},\r\n" + 
					"        \"comments\": \"andika komemntje123\"\r\n" + 
					"      },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetScheduleTempDetailList.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetScheduleTempDetailList.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getscheduletemplatedetaillist")
	public IS4ResGetScheduleTempDetailList getTemplateDetailList(@RequestBody IS4ReqGetScheduleTempDetailList req) {
		return adminService.getTemplateDetailList(req);
	}
	
	@ApiOperation(value = "Sablonhoz tartoz?? r??szletek visszat??r??t??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A92CB667D913D7B3E05011AC0D093B7F\",\r\n" + 
					"  \"templateDetailIdList\": \"12942949\",\r\n" + 
					"  \"status\": \"ADMIN\",\r\n" + 
					"  \"dateFrom\": \"2020-09-04\",\r\n" + 
					"  \"dateTo\": \"2020-09-05\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"resultMap\": {\r\n" + 
					"    \"Kedd\": [\r\n" + 
					"      {\r\n" + 
					"        \"id\": 12945826,\r\n" + 
					"        \"timeInterval\": \"10:00 - 10:40\",\r\n" + 
					"        \"doctor\": {\r\n" + 
					"          \"id\": 45289382,\r\n" + 
					"          \"name\": \"Bucsk?? Andrea\"\r\n" + 
					"        },\r\n" + 
					"        \"contingent\": {},\r\n" + 
					"        \"comments\": \"andika komemntje123\"\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"        \"id\": 12945827,\r\n" + 
					"        \"timeInterval\": \"10:00 - 10:40\",\r\n" + 
					"        \"doctor\": {\r\n" + 
					"          \"id\": 45289382,\r\n" + 
					"          \"name\": \"Bucsk?? Andrea\"\r\n" + 
					"        },\r\n" + 
					"        \"contingent\": {},\r\n" + 
					"        \"comments\": \"andika komemntje123\"\r\n" + 
					"      },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGenerateScheduleByTemplate.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGenerateScheduleByTemplate.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("generateschedulebytemplate")
	public IS4ResGenerateScheduleByTemplate generateScheduleByTemplate(@RequestBody IS4ReqGenerateScheduleByTemplate req) {
		return adminService.generateScheduleByTemplate(req);
	}
	
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGenerateScheduleByTemplate.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("checkscheduleisexist1")
	public Response checkScheduleIsExist(@RequestBody IS4ReqGenerateScheduleByTemplate req) {
		return adminService.checkScheduleIsExist(req);
	}
	
	@ApiOperation(value = "Sablonb??l kigener??lt id??pontok ??ltal a napt??r sz??nez??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7D4081AFBEC0FD7E05011AC0D0972F4\",\r\n" + 
					"  \"name\": \"andika\",\r\n" + 
					"  \"eventTypeFk\": 743,\r\n" + 
					"  \"servicePointFk\": 4509806\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"objScheduleColor\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"dateFrom\": \"2020-07-05T22:00:00.000+0000\",\r\n" + 
					"      \"colorCode\": \"G\",\r\n" + 
					"      \"eventMax\": 12,\r\n" + 
					"      \"eventFree\": 12,\r\n" + 
					"      \"eventCont\": 0\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 2,\r\n" + 
					"      \"dateFrom\": \"2020-07-06T22:00:00.000+0000\",\r\n" + 
					"      \"colorCode\": \"G\",\r\n" + 
					"      \"eventMax\": 12,\r\n" + 
					"      \"eventFree\": 12,\r\n" + 
					"      \"eventCont\": 0\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResScheduleColor.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResScheduleColor.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getschedulecolorbytemplate")
	public IS4ResScheduleColor getScheduleLoadByTemplate(@RequestBody IS4ReqGetScheduleLoadByTemplate req) {
		return adminService.getScheduleLoadByTemplate(req);
	}
	
	@ApiOperation(value = "Sablonb??l kigener??lt id??pontok visszat??r??t??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7D4081AFBEC0FD7E05011AC0D0972F4\",\r\n" + 
					"  \"name\": \"andika\",\r\n" + 
					"  \"eventTypeFk\": 743,\r\n" + 
					"  \"servicePointFk\": 4509806\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"generatedSchedulesList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12946765,\r\n" + 
					"      \"dateFrom\": \"2020-09-07T08:40:00.000+0000\",\r\n" + 
					"      \"dateTo\": \"2020-09-07T09:20:00.000+0000\",\r\n" + 
					"      \"status\": \"F\",\r\n" + 
					"      \"doctorName\": \"45289382\",\r\n" + 
					"      \"scheduleComment\": \"andika komemntje123\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12946766,\r\n" + 
					"      \"dateFrom\": \"2020-09-28T09:00:00.000+0000\",\r\n" + 
					"      \"dateTo\": \"2020-09-28T09:20:00.000+0000\",\r\n" + 
					"      \"status\": \"F\",\r\n" + 
					"      \"doctorName\": \"4531762\",\r\n" + 
					"      \"scheduleComment\": \"asdf\"\r\n" + 
					"    },..................." + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetGeneratedSchedulesByTemplate.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetGeneratedSchedulesByTemplate.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getgeneratedschedulesbytemplate")
	public IS4ResGetGeneratedSchedulesByTemplate getGeneratedSchedulesByTemplate(@RequestBody IS4ReqGetGeneratedSchedulesByTemplate req) {
		return adminService.getGeneratedSchedulesByTemplate(req);
	}
	
	@ApiOperation(value = "Sablonb??l kigener??lt id??pontok t??rl??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7D4081AFBEC0FD7E05011AC0D0972F4\",\r\n" + 
					"  \"scheduleIdList\": \"12946732,12946733\",\r\n" + 
					"  \"serviceFk\": 12942946,\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("deleteschedulelist")
	public Response deleteScheduleList(@RequestBody IS4ReqDeleteScheduleList req) {
		return adminService.deleteScheduleList(req);
	}
	
	@ApiOperation(value = "Sablonb??l kigener??lt id??pontok t??rl??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7D4081AFBEC0FD7E05011AC0D0972F4\",\r\n" + 
					"  \"name\": \"andika\",\r\n" +
					"  \"eventTypeFk\": 743,\r\n" + 
					"  \"servicePointFk\": 4509806,\r\n" + 
					"  \"deleteSchedule\": 0,\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("deletetemplate")
	public Response deleteTemplate(@RequestBody IS4ReqDeleteTemplate req) {
		return adminService.deleteTemplate(req);
	}
	
	@ApiOperation(value = "Sablonb??l fejl??c t??rl??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A7D4081AFBEC0FD7E05011AC0D0972F4\",\r\n" + 
					"  \"templateId\": 4509806,\r\n" + 
					"  \"deleteSchedule\": 0,\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("deletetemplateheader")
	public Response deleteTemplateHeader(@RequestBody IS4ReqDeleteTemplateHeader req) {
		return adminService.deleteTemplateHeader(req);
	}
	
	@ApiOperation(value = "Munkahelyek visszaad??sa.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"instituteDataList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4509811,\r\n" + 
					"      \"code\": \"SELECT$F56C9F9C367DD14AE04002542E234ABA\",\r\n" + 
					"      \"name\": \"Allergol??gia 3. gyermek szakrendel??s RI dolgoz??i\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4509832,\r\n" + 
					"      \"code\": \"SELECT$F56C9F9C367FD14AE04002542E234ABA\",\r\n" + 
					"      \"name\": \"Allergol??gia 4. gyermek szakrendel??s RI dolgoz??i\"\r\n" + 
					"    },............ " + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstituteData.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstituteData.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getcontingentsp")
	public IS4ResGetInstituteData getContingentSp(@RequestBody IS4MessageHeader req) {
		return adminService.getContingentSp(req);
	}
	
	@ApiOperation(value = "Kontingens felhaszn??l??inak list??ja.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"userList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4527530,\r\n" + 
					"      \"name\": \"Abuczki Istv??nn?? (N??v??r)\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4522547,\r\n" + 
					"      \"name\": \"??gostonn?? Tompos Andrea (N??v??r)\"\r\n" + 
					"    },............ " + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstUser.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstUser.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getcontingentuser")
	public IS4ResGetInstUser getContingentUser(@RequestBody IS4MessageHeader req) {
		return adminService.getContingentUser(req);
	}
	@ApiOperation(value = "Int??zm??nyen k??v??li szerepk??r??k list??ja.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"instituteDataList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 2,\r\n" + 
					"      \"code\": \"AUTH$PATIENT\",\r\n" + 
					"      \"name\": \"P??ciens\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 3,\r\n" + 
					"      \"code\": \"AUTH$NON_VALID_PATIENT\",\r\n" + 
					"      \"name\": \"Nem valid??lt p??ciens\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 4,\r\n" + 
					"      \"code\": \"AUTH$FAMILY_DOCTOR\",\r\n" + 
					"      \"name\": \"H??ziorvos\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstituteData.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstituteData.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getotherroles")
	public IS4ResGetInstituteData getOtherRoles(@RequestBody IS4MessageHeader req) {
		return adminService.getOtherRoles(req);
	}
	
	@ApiOperation(value = "Int??zm??ny szerepk??reinek lek??rdez??s.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"instituteDataList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12895358,\r\n" + 
					"      \"code\": \"AUTH$KENEZY_VDOCTOR\",\r\n" + 
					"      \"name\": \"Orvos (UDMED Virt)\"\r\n" + 
					"    }," +
					" ....... " +
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstituteData.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstituteData.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getinstroles")
	public IS4ResGetInstituteData getInstRoles(@RequestBody IS4MessageHeader req) {
		return adminService.getInstRoles(req);
	}
	
	@ApiOperation(value = "Kontingens lek??rdez??s.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"instituteDataList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12887179,\r\n" + 
					"      \"name\": \"Bel1\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12707660,\r\n" + 
					"      \"name\": \"Int??zm??nyi ambul??ns munkahelyek\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElementExt.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstElementExt.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getcontingent")
	public IS4ResGetInstElementExt getContingent(@RequestBody IS4MessageHeader req) {
		return adminService.getContingent(req);
	}
	
	@ApiOperation(value = "Kontingens csoport ment??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"contingentGroupName\": \"TestName\",\r\n" + 
					"  \"userIds\": \"4527530, 4526735\",\r\n" + 
					"  \"roleIds\": \"4509832,4516940,3\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres a kontingens csoport ment??se.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("savecontingentgroup")
	public Response saveContingentGroup(@RequestBody IS4ReqSaveContingentGroup req) {
		return adminService.saveContingentGroup(req);
	}
	
	@ApiOperation(value = "Kontingens csoport t??rl??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"id\": 12887180\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres a kontingens csoport t??rl??se.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("deletecontingentgroup")
	public Response deleteContingentGroup(@RequestBody IS4ReqDeleteContingentGroup req) {
		return adminService.deleteContingentGroup(req);
	}
	
	@ApiOperation(value = "Kontingens adatainak lek??rdez??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"contingentId\": 12887179\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"contingentDetails\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12887180,\r\n" + 
					"      \"contingentGroupFk\": 12887179,\r\n" + 
					"      \"servicePointModuleRoleFk\": 4510455,\r\n" + 
					"      \"servicePointRoleName\": \"Belgy??gy??szat 1. szakambulancia dolgoz??i\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetContingentDetail.class)
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetContingentDetail.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getcontingentdetails")
	public IS4ResGetContingentDetail getContingentDetails(@RequestBody IS4ReqGetContingentDetail req) {
		return adminService.getContingentDetails(req);
	}

	@ApiOperation(value = "Kontingens adatok ment??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"headerId\": 12887186,\r\n" + 
					"  \"userIds\": \"4527530, 4526735\",\r\n" + 
					"  \"roleIds\": \"4509832,4516940,3\",\r\n" + 
					"  \"delContIds\": \"13035734, 13035733\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres a kontingens csoport m??dos??t??sa.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("savecontingentdetail")
	public Response saveContingentDetail(@RequestBody IS4ReqSaveContingentDetail req) {
		return adminService.saveContingentDetail(req);
	}
	
	@ApiOperation(value = "Id??pontok m??dos??t??sa.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"scheduleIdList\": \"13035640,13035641,13035642\",\r\n" + 
					"  \"userId\": null,\r\n" + 
					"  \"contingentGroupId\": 6215347,\r\n" + 
					"  \"color\": \"COLOR_02\",\r\n" + 
					"  \"comment\": \"string\",\r\n" + 
					"  \"serviceFk\": 4601042\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres az id??pont le??r?? adatok m??dos??t??sa.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("modifyschedulesdetail")
	public Response modifyschedulesdetail(@RequestBody IS4ReqModifySchedulesDetail req) {
		return adminService.modifySchedulesDetail(req);
	}
	
	@ApiOperation(value = "Szabadnapok lek??rdez??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"permissionType\": \"ADMIN\"\r\n" + 
					"} " +
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"specialDayList\": [\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12349743,\r\n" + 
					"      \"day\": \"2018-08-22T22:00:00.000+0000\",\r\n" + 
					"      \"dayReplace\": \"2018-08-25T22:00:00.000+0000\",\r\n" + 
					"      \"yearly\": \"0\",\r\n" + 
					"      \"servicePointFk\": 11978896,\r\n" + 
					"      \"servicePointName\": \"Ultrahang II\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"id\": 12619045,\r\n" + 
					"      \"day\": \"2019-07-16T22:00:00.000+0000\",\r\n" + 
					"      \"dayReplace\": \"2019-07-17T22:00:00.000+0000\",\r\n" + 
					"      \"yearly\": \"0\",\r\n" + 
					"      \"servicePointFk\": 11978800,\r\n" + 
					"      \"servicePointName\": \"??rseb??szet\"\r\n" + 
					"    }" +
					"   ] " +
					" } " + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetSpecialDays.class)
 	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetSpecialDays.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getspecialdays")
	public IS4ResGetSpecialDays getSpecialDays(@RequestBody IS4ReqGetSpecialDays req) {
		return adminService.getSpecialDays(req);
	}
	
	@ApiOperation(value = "Szabadnap szab??lyok ment??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"specialDayList\": [\r\n" + 
					"    {\r\n" + 
					"      \"day\": \"2020-08-21 10:00\",\r\n" + 
					"      \"dayReplace\": \"2020-08-23 10:00\",\r\n" + 
					"      \"yearly\": \"0\",\r\n" + 
					"      \"servicePoinstFk\": 4514631\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres a szab??ly(ok) ment??se.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("savespecialday")
	public Response saveSpecialDay(@RequestBody IS4ReqSaveSpecialDay req) {
		return adminService.saveSpecialDay(req);
	}
	

	@ApiOperation(value = "Rendk??v??li id??pont l??trehoz??sa.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8E8CEEAA825B35EE05011AC0D09179B\",\r\n" + 
					"  \"contextParam\": null,\r\n" + 
					"  \"specialScheduleList\": [\r\n" + 
					"    {\r\n" + 
					"      \"dateFrom\": \"2020-08-21 08:00\",\r\n" + 
					"      \"dateTo\": \"2020-08-21 12:00\",\r\n" + 
					"      \"userFk\": null,\r\n" + 
					"      \"serviceFk\": 4601042,\r\n" + 
					"      \"servicePointFk\": 4514631,\r\n" + 
					"      \"eventTypeFk\": 961,\r\n" + 
					"      \"scheduleComment\": \"string\",\r\n" + 
					"      \"color\": \"COLOR_01\",\r\n" + 
					"      \"scheduleTemplateFk\": null,\r\n" + 
					"      \"contingentGroupFk\": null,\r\n" + 
					"      \"status\": \"C\"\r\n" + 
					"    }\r\n" + 
					"  ]\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"1 id??pont l??trehozva.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("generatespecialschedule")
	public Response generateSpecialSchedule(@RequestBody IS4ReqGenerateSpecialSchedule req) {
		return adminService.generateSpecialSchedule(req);
	}
	
	@ApiOperation(value = "Szabadnap t??rl??se.",
			notes = "<h3>P??lda h??v??s:</h3>\r\n" + 
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"sessionId\": \"A8D6067DC19A9AB7E05011AC0D0926DF\",\r\n" +
					"  \"contextParam\": \"string\",\r\n" + 
					"  \"specialDayId\": 13007065,\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>" + 
					"<h3>P??lda kimenet:</h3>\r\n" +
					"<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"Sikeres t??rl??s.\"\r\n" + 
					"}" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
 	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("deletespecialday")
	public Response deleteSpecialDay(@RequestBody IS4ReqDeleteSpecialDay req) {
		return adminService.deleteSpecialDay(req);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetWorkSchedule.class, code = 200, message = "Sikeres lek??r??s"),
	})
	@PostMapping("getworkschedule")
	public IS4ResGetWorkSchedule getWorkSchedule(@RequestBody IS4ReqGetInstWorkSchedule request) {
		return adminService.getWorkSchedule(request);
	}
}

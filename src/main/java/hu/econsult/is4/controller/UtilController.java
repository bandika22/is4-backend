package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.econsult.is4.model.NEUReqGetCodeBase;
import hu.econsult.is4.model.NEUResGetCodeBase;
import hu.econsult.is4.service.UtilService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping ("/rest/neu/util")
public class UtilController {
	
	private final UtilService utilService;
	
	@Autowired
	public UtilController(UtilService utilService) {
		super();
		this.utilService = utilService;
	}

	@ApiOperation(value = "Adott törzsadat kategóriához tartozó adatok visszatérítése.",
			notes = 
			"<h3>Példa hívás:</h3>\r\n" + 
			"<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"messageHeader\" : {\r\n" + 
			"        \"currentTapass\" : \"299700059907\",\r\n" + 
			"        \"currentRole\" : \"AUTH$SZEPLESZEL_CS\",\r\n" + 
			"        \"currentServicePointId\" : 12403224,\r\n" + 
			"        \"locale\" : \"hu\",\r\n" + 
			"        \"deviceId\" : \"192.123.123.123\"\r\n" + 
			"    },\r\n" + 
			"  \"typeCode\": \"SOCIAL_SECURITY_TYPE\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>" + 
			"<h3>Példa kimenet:</h3>\r\n" +
			"<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"code\": 1,\r\n" + 
			"  \"message\": \"OK\",\r\n" + 
			"  \"objCodeBase\": [\r\n" + 
			"    {\r\n" + 
			"      \"id\": 12,\r\n" + 
			"      \"typeCode\": \"SOCIAL_SECURITY_TYPE\",\r\n" + 
			"      \"typeDesc\": \"EÜ azonosító típus\",\r\n" + 
			"      \"code\": \"1\",\r\n" + 
			"      \"name\": \"TAJ-szám\",\r\n" + 
			"      \"validFrom\": \"2011-06-15\",\r\n" + 
			"      \"validTo\": \"2999-01-01\",\r\n" + 
			"      \"detailStr\": \"<A> <nls> <lang id=\"0\" locale=\"HU\"/> <lang id=\"3\" locale=\"RO\" code=\"1\" name=\"nr. TAJ\" descript=\"nr. TAJ\"/> </nls> <typeCodeDesc>EÜ azonosító típus</typeCodeDesc> </A> \"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"id\": 14,\r\n" + 
			"      \"typeCode\": \"SOCIAL_SECURITY_TYPE\",\r\n" + 
			"      \"typeDesc\": \"EÜ azonosító típus\",\r\n" + 
			"      \"code\": \"3\",\r\n" + 
			"      \"name\": \"útlevélszám\",\r\n" + 
			"      \"validFrom\": \"2011-06-15\",\r\n" + 
			"      \"validTo\": \"2999-01-01\",\r\n" + 
			"      \"detailStr\": \"<A> <nls> <lang id=\"0\" locale=\"HU\"/> <lang id=\"3\" locale=\"RO\" code=\"3\" name=\"numarul de pasaport\" descript=\"numarul de pasaport\"/> </nls> <typeCodeDesc>EÜ azonosító típus</typeCodeDesc> </A> \"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"id\": 6794473,\r\n" + 
			"      \"typeCode\": \"SOCIAL_SECURITY_TYPE\",\r\n" + 
			"      \"typeDesc\": \"EÜ azonosító típus\",\r\n" + 
			"      \"code\": \"7\",\r\n" + 
			"      \"name\": \"ismeretlen beteg\",\r\n" + 
			"      \"validFrom\": \"2015-09-18\",\r\n" + 
			"      \"validTo\": \"2999-01-01\",\r\n" + 
			"      \"detailStr\": \"<A> <typeCodeDesc>EÜ azonosító típus</typeCodeDesc> </A> \"\r\n" + 
			"    },\r\n" + 
			" ...\r\n" + 
			"  ]\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>",
			response = NEUResGetCodeBase.class)
	@ApiResponses(value = {
			@ApiResponse(response = NEUResGetCodeBase.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getcodebase")
	public NEUResGetCodeBase getCodeBase(@RequestBody NEUReqGetCodeBase req ) {
		return utilService.getCodeBase(req);
	}
}

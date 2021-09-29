package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ResGetInstituteData;
import hu.econsult.is4.model.IS4ResGetRootSpBySession;
import hu.econsult.is4.model.IS4ResGetInstElementExt;
import hu.econsult.is4.model.IS4ResGetUserSps;
import hu.econsult.is4.model.NEUReqCheckUser;
import hu.econsult.is4.model.NEUReqLogin;
import hu.econsult.is4.model.NEUReqUpdateSession;
import hu.econsult.is4.model.NEUResLogin;
import hu.econsult.is4.model.NEUResUpdateSession;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.model.entity.ObjSessionExt;
import hu.econsult.is4.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/rest/neu/authentication")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	@ApiOperation(value = "BejelentkezĂ©s Ă©s visszatĂ©rĂ©s sessionId -val.", 
			notes = "<h3>PĂ©lda hĂ­vĂˇs:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"userName\": \"bedo.kenezy.hu\",\r\n" + 
			"  \"password\": \"afb6cbc48b2c5f1b67bdb09cb6ba169d\",\r\n" + 
			"  \"deviceId\": \"\",\r\n" + 
			"  \"spCode\": \"F56C9F9C36FBD14AE04002542E234ABA\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + "</pre>"
			+ "<h3>PĂ©lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 0,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"sessionId\": \"A3B4657145BD16D5E05011AC0D096817\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = NEUResLogin.class)
	@ApiResponses(value = { @ApiResponse(response = NEUResLogin.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@PostMapping("loginandreturnsessionid")
	public NEUResLogin loginAndReturnSessionId(@RequestBody NEUReqLogin req) {
		return authService.loginAndReturnSessionId(req);
	}
	
	@PostMapping("getusersps")
	public IS4ResGetUserSps getUserSps(@RequestBody IS4MessageHeader req) {
		return authService.getUserSps(req.getSessionId());
	}
	
	@ApiOperation(value = "Session meghosszabbĂ­tĂˇsa, kijelentkeztetĂ©se.", notes = "<h3>PĂ©lda hĂ­vĂˇs:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"sessionId\": \"9FEFA336E6714EB4E05011AC0D094600\",\r\n" + 
			"  \"authRole\": \"\",\r\n" + 
			"  \"servicePoint\": \"\",\r\n" + 
			"  \"logout\": false,\r\n" + 
			"  \"keepAlive\": true\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>PĂ©lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 0,\r\n" + 
					"  \"message\": \"OK\",\r\n" + 
					"  \"sessionId\": \"9FEFA336E6714EB4E05011AC0D094600\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = NEUResUpdateSession.class)
	@ApiResponses(value = { @ApiResponse(response = NEUResUpdateSession.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@PostMapping("updatesessionid")
	public NEUResUpdateSession updateSessionId(@RequestBody NEUReqUpdateSession req) {
		return authService.upadteSessionId(req);
	}
	@ApiOperation(value = "FelhasznĂˇlĂł keresĂ©se nauId, 2t vagy 4t alapjĂˇn.", notes = "<h3>PĂ©lda hĂ­vĂˇs:</h3>\r\n"
			+ "<pre>\r\n" + 
			"<code>\r\n" + 
			"{\r\n" + 
			"  \"dateOfBirth\": \"19721010\",\r\n" + 
			"  \"id\": \"\",\r\n" + 
			"  \"birthName\": \"TesztGĂ©za\",\r\n" + 
			"  \"placeOfBirth\": \"Budapest\",\r\n" + 
			"  \"mothersName\": \"Kiss Irma\"\r\n" + 
			"}\r\n" + 
			"</code>\r\n" + 
			"</pre>"
			+ "<h3>PĂ©lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"  \"code\": 1,\r\n" + 
					"  \"message\": \"86AAE5BE-389D-4CF4-93FC-2481805E8B5F\"\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = Response.class)
	@ApiResponses(value = { @ApiResponse(response = Response.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@PostMapping("checkuser")
	public Response checkUser(@RequestBody NEUReqCheckUser req) {
		return authService.checkUser(req);
	}
	
	@ApiOperation(value = "JogosultsĂˇg lekĂ©rĂ©se sessionId alapjĂˇn.", 
			notes = "<h3>PĂ©lda hĂ­vĂˇs:</h3>\r\n"
					+ "<pre>\r\n" + "<code>\r\n"
					+ "	https://ekrest.nauticom.hu/rest/neu/authentication/getpermission/B51C8EC193582AEEE05011AC0D097D99"
					+ "\r\n"
					+ "</code>\r\n" + "</pre>"
			+ "<h3>PĂ©lda kimenet:</h3>" + "<pre>\r\n" + 
					"<code>\r\n" + 
					"{\r\n" + 
					"    \"code\": 1,\r\n" + 
					"    \"message\": \"OK\",\r\n" + 
					"    \"instituteDataList\": [\r\n" + 
					"        {\r\n" + 
					"            \"id\": 1,\r\n" + 
					"            \"name\": \"REQUEST\"\r\n" + 
					"        },\r\n" + 
					"        {\r\n" + 
					"            \"id\": 2,\r\n" + 
					"            \"name\": \"ADMIN\"\r\n" + 
					"        }\r\n" + 
					"    ]\r\n" + 
					"}\r\n" + 
					"</code>\r\n" + 
					"</pre>", response = IS4ResGetInstElementExt.class)
	@ApiResponses(value = { @ApiResponse(response = IS4ResGetInstElementExt.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@GetMapping("getpermission/{sessionId}")
	public IS4ResGetInstElementExt getUserPermission(@PathVariable("sessionId") String sessionId) {
		return authService.getUserPermission(sessionId);
	}
	
	@ApiResponses(value = { @ApiResponse(response = IS4ResGetRootSpBySession.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@GetMapping("getrootspbysession/{sessionId}")
	public IS4ResGetRootSpBySession getRootSpBySession(@PathVariable("sessionId") String sessionId) {
		return authService.getRootSpBySession(sessionId);
	}
	
	@ApiResponses(value = { @ApiResponse(response = Response.class, code = 200, message = "Sikeres lekĂ©rĂ©s"), })
	@GetMapping("session/valid/{sessionId}/{spcode}")
	public Response sessionIsValid(@PathVariable("sessionId") String sessionId, @PathVariable("spcode") String spCode ) {
		return authService.checkSession(sessionId, spCode);
	}
	
	@GetMapping("getsp/by/session/{session}")
	public Response getSpBySession(@PathVariable("session") String sessionId) {
		return authService.getSpBySession(sessionId);
	}

	
}

package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.econsult.is4.model.IS4MessageHeader;
import hu.econsult.is4.model.IS4ReqGetInstAdminPermission;
import hu.econsult.is4.model.IS4ReqGetInstAdminRoles;
import hu.econsult.is4.model.IS4ReqGetInstAdminUserSps;
import hu.econsult.is4.model.IS4ReqGetRoles;
import hu.econsult.is4.model.IS4ReqSavePermissionToUser;
import hu.econsult.is4.model.IS4ReqSaveRoleToUser;
import hu.econsult.is4.model.IS4ResGetInstAdminPermissions;
import hu.econsult.is4.model.IS4ResGetInstAdminRoles;
import hu.econsult.is4.model.IS4ResGetInstAdminRoles2;
import hu.econsult.is4.model.IS4ResGetInstAdminUserSps;
import hu.econsult.is4.model.IS4ResGetInstUsers;
import hu.econsult.is4.model.Response;
import hu.econsult.is4.service.InstituteAdminService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/rest/is4/instadmin")
public class InstituteAdminController {
	
	private final InstituteAdminService instAdminService;

	@Autowired
	public InstituteAdminController(InstituteAdminService instAdminService) {
		super();
		this.instAdminService = instAdminService;
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstUsers.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getusers")
	public IS4ResGetInstUsers getUsers(@RequestBody IS4MessageHeader request) {
		return instAdminService.getInstAdminUsers(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminRoles.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getroles")
	public IS4ResGetInstAdminRoles getRoles(@RequestBody IS4ReqGetRoles request) {
		return instAdminService.getInstAdminRoles(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminUserSps.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getusersps")
	public IS4ResGetInstAdminUserSps getRoles(@RequestBody IS4ReqGetInstAdminUserSps request) {
		return instAdminService.getInstAdminUserSps(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = Response.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("saveroletouser")
	public Response saveRoleToUser(@RequestBody IS4ReqSaveRoleToUser request) {
		return instAdminService.saveRoletoUser(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminRoles.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getusersproles")
	public IS4ResGetInstAdminRoles getUserSpRoles(@RequestBody IS4ReqGetRoles request) {
		return instAdminService.getInstAdminUserSpRoles(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminPermissions.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getpermissions")
	public IS4ResGetInstAdminPermissions getInstAdminUserPermission(@RequestBody IS4ReqGetInstAdminPermission request) {
		return instAdminService.getInstAdminUserPermission(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminPermissions.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("savepermissiontouser")
	public Response savePermissionToUser(@RequestBody IS4ReqSavePermissionToUser request) {
		return instAdminService.savePermissionToUser(request);
	}
	
	@ApiResponses(value = {
			@ApiResponse(response = IS4ResGetInstAdminPermissions.class, code = 200, message = "Sikeres lekérés"),
	})
	@PostMapping("getuserallroles")
	public IS4ResGetInstAdminRoles2 getInstAdminUserRoles(@RequestBody IS4ReqGetInstAdminRoles request) {
		return instAdminService.getInstAdminUserRoles(request);
	}

}

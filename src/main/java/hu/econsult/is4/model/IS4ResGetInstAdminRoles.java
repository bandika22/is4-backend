package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjGetInstAdminRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResGetInstAdminRoles extends Response {
	
	private List<ObjGetInstAdminRoles> roles;

}

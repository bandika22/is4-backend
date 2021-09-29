package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSaveRoleToUser {

	private Long childModuleRoleFk;
	private Long parentModuleRoleFk;
	private boolean delete;
}

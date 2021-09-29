package hu.econsult.is4.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ObjGetInstAdminUserSps implements Serializable{
	
	private static final long serialVersionUID = 9193687574825596878L;
	
	@Id
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "role_code")
	private String roleCode;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "child_user_role_fk")
	private Long childUserRoleFk;
	
	@Column(name = "child_module_role_fk")
	private Long childModuleRoleFk;
	
	@Column(name = "parent_user_role_fk")
	private Long parentUserRoleFk;
	
	@Column(name = "parent_module_role_fk")
	private Long parentModuleRoleFk;
	
	@Column(name = "parent_role_code")
	private String parentRoleCode;
	
	@Column(name = "parent_role_name")
	private String parentRoleName;

}

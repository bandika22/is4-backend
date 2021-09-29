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
public class ObjGetInstAdminRoles implements Serializable{
	
	private static final long serialVersionUID = -1413018527025852364L;
	
	@Id
	private Long id;
	
	@Column(name = "module_role_fk")
	private Long  moduleRoleFk;
	private String code;
	private String name;

}

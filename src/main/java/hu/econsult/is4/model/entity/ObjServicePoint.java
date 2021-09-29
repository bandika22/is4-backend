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
public class ObjServicePoint implements Serializable {

	private static final long serialVersionUID = -5501221967483621284L;
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TAPASS")
	private String tapass;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "SP_NAME")
	private String spName;
	
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Column(name = "SP_CODE")
	private String spCode;
	
	@Column(name = "SP_ID")
	private Long spId;
	
	@Column(name = "PERM_LST")
	private String permList;

}

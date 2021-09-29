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
public class IS4ObjGetInstAdminPermissions implements Serializable {
	
	private static final long serialVersionUID = -8189742594225281865L;

	@Id
	@Column(name = "permission_fk")
	private Long permFk;
	
	@Column(name = "permission_code")
	private String permCode;
	
	@Column(name = "permission_name")
	private String permName;
	
	@Column(name = "permission_type")
	private String permType;
	
	@Column(name = "module_permission_fk")
	private Long modulePermFk;
	
	@Column(name = "inherited")
	private Integer inherited;
	
	@Column(name = "is_active")
	private Integer isActive;

}

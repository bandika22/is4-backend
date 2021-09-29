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
public class IS4ObjGetInstAdminRoles implements Serializable {
	
	private static final long serialVersionUID = -8189742594225281865L;

	@Id
	private Long id;
	
	@Column(name = "moduleRoleFk")
	private Long moduleRoleFk;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "granted")
	private Integer granted;

}

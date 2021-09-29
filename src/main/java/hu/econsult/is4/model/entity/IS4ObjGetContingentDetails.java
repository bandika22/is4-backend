package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.util.Date;

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
public class IS4ObjGetContingentDetails implements Serializable {
	 
	private static final long serialVersionUID = 2382958538793198341L;

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CONTINGENT_GROUP_FK")
	private Long contingentGroupFk;

	@Column(name = "FK")
	private Long elementFk;
		
	@Column(name = "CODE")
	private String servicePointRoleName;
	
	@Column(name = "NAME")
	private String elementName;
	
	@Column(name = "TYPE")
	private String type;
}

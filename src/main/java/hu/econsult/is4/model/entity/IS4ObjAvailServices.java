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
public class IS4ObjAvailServices implements Serializable {
	
	private static final long serialVersionUID = -8189742594225281865L;

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ET_ID")
	private Long etID;
	
	@Column(name = "ET_NAME")
	private String etName;
	
	@Column(name = "SP_ID")
	private Long spID;
	
	@Column(name = "SP_NAME")
	private String spName;
	
	@Column(name = "DR_ID")
	private Long doctorID;
	
	@Column(name = "DR_NAME")
	private String doctorName;
	
	@Column(name = "SV_ID")
	private Long servicesID;

}

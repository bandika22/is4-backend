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
public class IS4ObjGetSpecialDay implements Serializable{

	private static final long serialVersionUID = 430639454801013469L;

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DAY")
	private Date day;
	
	@Column(name = "DAY_REPLACE")
	private Date dayReplace;
	
	@Column(name = "YEARLY")
	private String yearly;
	
	@Column(name = "SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name = "SERVICE_POINT_NAME")
	private String servicePointName;
	
}

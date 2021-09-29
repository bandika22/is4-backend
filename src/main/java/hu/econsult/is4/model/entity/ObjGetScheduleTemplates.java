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
public class ObjGetScheduleTemplates implements Serializable {

	private static final long serialVersionUID = 3046027313504255970L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SERVICE_FK")
	private Long serviceFk;
	
	@Column(name = "EVENT_TYPE_FK")
	private Long eventTypeFk;
	
	@Column(name = "EVENT_TYPE_NAME")
	private String eventTypeName;
	
	@Column(name = "SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name = "SERVICE_POINT_NAME")
	private String servicePointName;
	
	@Column(name = "LAST_GENERATED_SCHEDULE")
	private String lastGeneratedSchedule;

}

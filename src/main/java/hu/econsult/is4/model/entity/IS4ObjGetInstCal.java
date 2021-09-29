package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
public class IS4ObjGetInstCal implements Serializable {

	private static final long serialVersionUID = 8177508986398717930L;
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SCHEDULE_FK")
	private Long scheduleFk;
	
	@Column(name = "CALENDAR_FK")
	private Long calendarFk;
	
	@Column(name = "DATE_FROM")
	private Date dateFrom;
	
	@Column(name = "DATE_TO")
	private Date dateTo;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DOCTOR_FK")
	private Long doctorFk;
	
	@Column(name = "DOCTOR_NAME")
	private String doctorName;
	
	@Column(name = "PATIENT_ALIAS")
	private String patientAlias;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "EVENT_TYPE_FK")
	private Long eventTypeFk;
	
	@Column(name = "EVENT_TYPE_NAME")
	private String eventTypeName;
	
	@Column(name = "SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name = "SERVICE_POINT_NAME")
	private String servicePointName;
	
	@Column(name = "SERVICE_FK")
	private Long serviceFk;
	
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "SCH_COLOR")
	private String color;

	@Column(name = "CAL_INFO")
	private String calInfo;
	
	@Column(name = "CONTINGENT_GROUP_NAME")
	private String contingent;
	
	@Column(name = "CREATOR_USER_NAME")
	private String creatorUserName;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;

}

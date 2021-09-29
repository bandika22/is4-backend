package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IS4ObjAppointment implements Serializable {

	private static final long serialVersionUID = -9163796435000845436L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="DATE_FROM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTo;
	
	@Column(name="SCHEDULE_FK")
	private Long scheduleFk;
	
	@Column(name="SCHEDULE_STATUS")
	private String scheduleStatus;
	
	@Column(name="SERVICE_POINT_NAME")
	private String servicePointName;
	
	@Column(name="SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name="SERVICE_POINT_CODE")
	private String servicePointCode;
	
	@Column(name="EVENT_TYPE_NAME")
	private String eventTypeName;
	
	@Column(name="EVENT_TYPE_FK")
	private Long eventTypeFk;
	
	@Column(name="SERVICE_FK")
	private Integer serviceIdFk;
	
	@Column(name="DOCTOR_NAME")
	private String doctorName;
	
	@Column(name="DOCTOR_UUID")
	private String doctorUuid;
	
	@Column(name="SCHEDULE_COMMENT")
	private String scheduleComment;
	
	@Column(name="SCHEDULE_COLOR")
	private String scheduleColor;
	
	@Column(name="CONTINGENT_GROUP_NAME")
	private String contingentGroupName;
	
	@Column(name="SERVICE_DETAIL")
	private String serviceDetail;
	
	@Column(name="PATIENT_ALIAS")
	private String patientDetail;

}

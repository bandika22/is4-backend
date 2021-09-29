package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ObjAppointment implements Serializable {

	private static final long serialVersionUID = 4038663889123456053L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="DATE_FROM")
	@Temporal(TemporalType.TIMESTAMP)
//	@ApiModelProperty(dataType = "java.time.LocalDate")
	private Date dateFrom;
	
	@Column(name="DATE_TO")
	@Temporal(TemporalType.TIMESTAMP)
//	@ApiModelProperty(dataType = "java.time.LocalDate")
	private Date dateTo;
	
	@Column(name="TIME_FROM")
	private String timeFrom;
	
	@Column(name="CREATE_DATETIME")
	private String createDateTime;
	
	@Column(name="SCHEDULE_FK")
	private Long scheduleFk;
	
	@Column(name="CALENDAR_FK")
	private Long calendarFk;
	
	@Column(name="CALENDAR_STATUS")
	private String calendarStatus;
	
	@Column(name="SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name="SERVICE_POINT_NAME")
	private String servicePointName;
	
	@Column(name="EVENT_TYPE_FK")
	private Long eventTypeFk;
	
	private String eventTypeName;
	
	@Column(name="SERVICE_FK")
	private Long serviceFk;
	
	@Column(name="DOCTOR_FK")
	private Long doctorFk;
	
	@Column(name="DOCTOR_NAME")
	private String doctorName;
	
	@Column(name="PARTICIPANT_NAME")
	private String participantName;
	
//	@Column(name="SH_SV_DESCRIPTION")
//	private String shortDescription;
	
	@Column(name="LG_SV_DESCRIPTION")
	private String longDescription;
	
	@Column(name="SCHEDULE_COMMENT")
	private String scheduleComment;
	
	@Column(name="CALENDAR_COMMENT")
	private String calendarComment;
	
	@Column(name="SCHEDULE_COLOR")
	private String scheduleColor;
	
	@Column(name="CONTINGENT_GROUP_NAME")
	private String contingentGroupName;
	
	@Column(name="PARTICIPANT_DETAIL")
	private String participantDetail;
	
	@Column(name="CREATOR_NAME")
	private String creatorName;
	
	@Column(name="CALL_NUMBER")
	private String callNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Long getScheduleFk() {
		return scheduleFk;
	}

	public void setScheduleFk(Long scheduleFk) {
		this.scheduleFk = scheduleFk;
	}

	public Long getCalendarFk() {
		return calendarFk;
	}

	public void setCalendarFk(Long calendarFk) {
		this.calendarFk = calendarFk;
	}

	public String getCalendarStatus() {
		return calendarStatus;
	}

	public void setCalendarStatus(String calendarStatus) {
		this.calendarStatus = calendarStatus;
	}

	public Long getServicePointFk() {
		return servicePointFk;
	}

	public void setServicePointFk(Long servicePointFk) {
		this.servicePointFk = servicePointFk;
	}

	public Long getEventTypeFk() {
		return eventTypeFk;
	}

	public void setEventTypeFk(Long eventTypeFk) {
		this.eventTypeFk = eventTypeFk;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public Long getServiceFk() {
		return serviceFk;
	}

	public void setServiceFk(Long serviceFk) {
		this.serviceFk = serviceFk;
	}

	public Long getDoctorFk() {
		return doctorFk;
	}

	public void setDoctorFk(Long doctorFk) {
		this.doctorFk = doctorFk;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getScheduleComment() {
		return scheduleComment;
	}

	public void setScheduleComment(String scheduleComment) {
		this.scheduleComment = scheduleComment;
	}

	public String getCalendarComment() {
		return calendarComment;
	}

	public void setCalendarComment(String calendarComment) {
		this.calendarComment = calendarComment;
	}

	public String getScheduleColor() {
		return scheduleColor;
	}

	public void setScheduleColor(String scheduleColor) {
		this.scheduleColor = scheduleColor;
	}

	public String getContingentGroupName() {
		return contingentGroupName;
	}

	public void setContingentGroupName(String contingentGroupName) {
		this.contingentGroupName = contingentGroupName;
	}

	public String getParticipantDetail() {
		return participantDetail;
	}

	public void setParticipantDetail(String participantDetail) {
		this.participantDetail = participantDetail;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getServicePointName() {
		return servicePointName;
	}

	public void setServicePointName(String servicePointName) {
		this.servicePointName = servicePointName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public ObjAppointment() {
		super();
	}
}

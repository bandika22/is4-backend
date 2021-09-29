package hu.econsult.is4.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ObjGetScheduleTemplateDetailList implements Serializable {

	private static final long serialVersionUID = 3525968891486442675L;

	@Id
	private Long id;
	@Column(name = "DAY")
	private String day;
	@Column(name = "TIME_FROM")
	private String timeFrom;
	@Column(name = "TIME_TO")
	private String timeTo;
	@Column(name = "USER_FK")
	private Long userFk;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "CONTINGENT_GROUP_FK")
	private Long contingentGroupFk;
	@Column(name = "CONTINGENT_GROUP_NAME")
	private String contingentGroupName;
	@Column(name = "SCHEDULE_COMMENT")
	private String scheduleComment;
	@Column(name = "COLOR")
	private String color;
	@Column(name = "SCHEDULE_TEMPLATE_FK")
	private Long scheduleTemplateFk;

	public ObjGetScheduleTemplateDetailList() {
		super();
	}
	public ObjGetScheduleTemplateDetailList(Long id, String day, String timeFrom, String timeTo, Long userFk,
			String userName, Long contingentGroupFk, String contingentGroupName, String scheduleComment, String color,
			Long scheduleTemplateFk) {
		super();
		this.id = id;
		this.day = day;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.userFk = userFk;
		this.userName = userName;
		this.contingentGroupFk = contingentGroupFk;
		this.contingentGroupName = contingentGroupName;
		this.scheduleComment = scheduleComment;
		this.color = color;
		this.scheduleTemplateFk = scheduleTemplateFk;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public Long getUserFk() {
		return userFk;
	}
	public void setUserFk(Long userFk) {
		this.userFk = userFk;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getContingentGroupFk() {
		return contingentGroupFk;
	}
	public void setContingentGroupFk(Long contingentGroupFk) {
		this.contingentGroupFk = contingentGroupFk;
	}
	public String getContingentGroupName() {
		return contingentGroupName;
	}
	public void setContingentGroupName(String contingentGroupName) {
		this.contingentGroupName = contingentGroupName;
	}
	public String getScheduleComment() {
		return scheduleComment;
	}
	public void setScheduleComment(String scheduleComment) {
		this.scheduleComment = scheduleComment;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getScheduleTemplateFk() {
		return scheduleTemplateFk;
	}
	public void setScheduleTemplateFk(Long scheduleTemplateFk) {
		this.scheduleTemplateFk = scheduleTemplateFk;
	}
}

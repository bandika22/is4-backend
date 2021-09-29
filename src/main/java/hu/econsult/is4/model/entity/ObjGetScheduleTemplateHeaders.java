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
public class ObjGetScheduleTemplateHeaders implements Serializable {

	private static final long serialVersionUID = 6507692901116678130L;

	@Id
	private Long id;
	@Column(name = "TIME_FROM")
	private String timeFrom;
	@Column(name = "TIME_TO")
	private String timeTo;
	@Column(name = "EVENT_MAX")
	private Long eventMax;
	@Column(name = "EVENT_MAX_SAME")
	private Long eventMaxSame;
	@Column(name = "USER_FK")
	private Long userFk;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "SCHEDULE_COMMENT")
	private String scheduleComment;
	@Column(name = "COLOR")
	private String color;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	@Column(name = "CONTINGENT_GROUP_FK")
	private Long contingentGroupFk;
	@Column(name = "CONTINGENT_GROUP_NAME")
	private String contingentGroupName;
	@Column(name = "WEEK")
	private String week;

}

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
public class ObjGetGeneratedSchedulesByTemplate implements Serializable {

	private static final long serialVersionUID = 2440130930204812587L;

	@Id
	private Long id;
	@Column(name = "DATE_FROM")
    private Date dateFrom;
	@Column(name = "DATE_TO")
    private Date dateTo;
	@Column(name = "CALENDAR_FK")
    private Long calendarFk;
	@Column(name = "STATUS")
    private String status;
	@Column(name = "DOCTOR_NAME")
    private String doctorName;
	@Column(name = "SCHEDULE_COMMENT")
    private String scheduleComment;
	@Column(name = "SCHEDULE_COLOR")
    private String scheduleColor;
	@Column(name = "CONTINGENT_GROUP_NAME")
    private String contingentGroupName;
	@Column(name = "SERVICE_FK")
	private String serviceFk;
	
}

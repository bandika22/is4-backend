package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import hu.econsult.is4.model.xml.GetCalendarHistoryData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IS4ObjGetInstWorkSchedule implements Serializable {

	private static final long serialVersionUID = 8177508986398717930L;
	
	@Id
	@Column(name = "ROWNUMBER")
	private Long rowNumber;
	
	@Column(name = "DATE_FROM")
	private Date dateFrom;
	
	@Column(name = "DATE_TO")
	private Date dateTo;
	
	@Column(name = "DOCTOR_NAME")
	private String doctorName;
	
	@Column(name = "EVENT_TYPE_NAME")
	private String eventTypeName;
	
	@Column(name = "SERVICE_POINT_NAME")
	private String servicePointName;
	
	@Column(name = " SCHEDULE_COUNT")
	private Integer scheduleCount;
	
	@Column(name = "IS_CONT")
	private Integer contingent;
	
}

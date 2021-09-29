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
public class ObjScheduleColor implements Serializable{

	private static final long serialVersionUID = 200796590390527004L;

	@Id
	@Column(name="ID")
	private Long id;
	@Column(name="DATE_FROM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;
	@Column(name="COLOR_CODE")
	private String colorCode;
	@Column(name="EVENT_MAX")
	private Long eventMax;
	@Column(name="EVENT_FREE")
	private Long eventFree;
	@Column(name="EVENT_CONT")
	private Long eventCont;

}

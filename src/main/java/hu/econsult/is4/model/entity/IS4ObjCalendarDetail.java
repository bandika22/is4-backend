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
public class IS4ObjCalendarDetail implements Serializable {

	private static final long serialVersionUID = 5997374073447812207L;

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SERVICE_POINT_FK")
	private Long servicePointFk;
	
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "EVENT_TYPE_FK")
	private Long eventTypeFk;
	
	@Column(name = "DOCTOR_FK")
	private Long doctorFk;
	
}

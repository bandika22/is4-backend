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
public class ObjDoctorToSp implements Serializable {

	private static final long serialVersionUID = 3625904774417394726L;

	@Id
	private Long id;

	@Column(name = "DOCTOR_SEAL")
	private String doctorSeal;

	@Column(name = "SP_CODE")
	private String spCode;

	@Column(name = "DOCTOR_NAME")
	private String doctorName;

	@Column(name = "SP_NAME")
	private String spName;
	
}

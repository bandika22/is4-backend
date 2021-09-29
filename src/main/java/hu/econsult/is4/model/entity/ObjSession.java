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
public class ObjSession implements Serializable {

	private static final long serialVersionUID = 6095257753485882597L;

	@Id
	@Column(name = "SESSION_ID")
	private String sessionId;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name = "START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name = "EXP_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expTime;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "AUTH_ROLE_CODE")
	private String authRoleCode;
	
	@Column(name = "CURR_ROLE_CODE")
	private String currRoleCode;
	
	@Column(name = "CURR_LEVEL")
	private String currLevel;
	
	@Column(name = "CURR_LOCALE")
	private String currLocale;
	
	@Column(name = "TAPASS")
	private String tapass;
	
	@Column(name = "SERVICE_POINT_ID")
	private Long servicePointId;
}

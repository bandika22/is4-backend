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
public class ObjSendData implements Serializable {
	
	private static final long serialVersionUID = 3617912379061895484L;

	@Id
	@Column(name = "CAL_ID")
	private Long calId;
	
	@Column(name = "SEND_DATE")
	private String sendDate;
	
	@Column(name = "SEND_NO")
	private String sendNo;
	
	@Column(name = "SEND_SP_NAME")
	private String sendSpName;
	
	@Column(name = "SEND_SP_CODE")
	private String sendSpCode;
	
	@Column(name = "SEND_DR_NAME")
	private String sendDrName;
	
	@Column(name = "SEND_DR_SEAL")
	private String sendDrSeal;
	
	@Column(name = "SEND_DIAG")
	private String sendDiag;
	
	@Column(name = "SEND_COMMENT")
	private String sendComment;

}

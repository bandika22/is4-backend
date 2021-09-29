package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqModSendData extends IS4MessageHeader {
	
	private Long calId;
	private String sendDate;
	private String sendNo;
	private String sendSpName;
	private String sendSpCode;
	private String sendDrName;
	private String sendDrSeal;
	private String sendDiag;
	private String sendComment;
}


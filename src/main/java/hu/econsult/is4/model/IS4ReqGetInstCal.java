package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetInstCal extends IS4MessageHeader {
	
	private String contextParam;
	private Long spId;
	private Long etId;
	private String dateFrom;
	private Long doctorId;
	private String dateTo;
	private String status;
	
}

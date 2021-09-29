package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetInstWorkSchedule extends IS4MessageHeader {

	private Long spId;
	private Long etId;
	private String dateFrom;
	private String dateTo;	
}

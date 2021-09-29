package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqModifySchedulesDetail extends IS4MessageHeader{
	
	private String scheduleIdList;
	private Long userId;
	private Long contingentGroupId;
	private String color;
	private String comment;
	private Long serviceFk;
	
		
}

package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqCheckContList extends IS4MessageHeader{
	
	private String contextParam;
	private String scheduleList;

}

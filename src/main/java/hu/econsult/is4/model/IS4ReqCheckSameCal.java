package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqCheckSameCal extends IS4MessageHeader{
	
	private String contextParam;
	private Integer patHisId;
	private String patNauId;
	private String alias;
	private Integer serviceId;
	private String date;
	
}

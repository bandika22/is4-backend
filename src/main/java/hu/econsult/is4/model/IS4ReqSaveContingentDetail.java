package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveContingentDetail extends IS4MessageHeader{
	
	private Long headerId;
	private String userIds;
	private String roleIds;
	private String delContIds;
	private String spCodes;
	
}

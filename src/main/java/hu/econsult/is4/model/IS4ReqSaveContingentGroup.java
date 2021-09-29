package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveContingentGroup extends IS4MessageHeader{
	
	private String contingentGroupName;
	private String userIds;
	private String roleIds;
	private String spCodes;
	
}

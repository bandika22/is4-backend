package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetInstData extends IS4MessageHeader {
	
	private String permissionType;
	private boolean scheduleTemplate;


	@Override
	public String toString() {
		return "IS4ReqGetInstData [permissionType=" + permissionType + ", sessionId=" + getSessionId() + "]";
	}

}

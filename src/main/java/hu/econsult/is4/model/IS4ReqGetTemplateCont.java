package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetTemplateCont extends IS4MessageHeader {
	
	private String permissionType;


	@Override
	public String toString() {
		return "IS4ReqGetTemplateCont [permissionType=" + permissionType + ", sessionId=" + getSessionId() + "]";
	}

}

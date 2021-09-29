package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetTemplateDoc extends IS4MessageHeader {
	
	private Integer spId;

	@Override
	public String toString() {
		return "IS4ReqGetTemplateDoc [spId=" + spId + ", sessionId=" + getSessionId() + "]";
	}
}

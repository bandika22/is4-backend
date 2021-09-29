package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetCodeBase extends IS4MessageHeader{
	
	private String typeCode;

	@Override
	public String toString() {
		return "NEUReqGetCodeBase [messageHeader=" + getSessionId() + ", typeCode=" + typeCode + "]";
	}
}

package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUReqGetCodeBase extends Request{
	
	private String typeCode;
	
	@Override
	public String toString() {
		return "NEUReqGetCodeBase [messageHeader=" + messageHeader + ", typeCode=" + typeCode + "]";
	}

}

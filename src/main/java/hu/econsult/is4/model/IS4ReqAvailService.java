package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqAvailService extends IS4MessageHeader {
	
	private Long spId;
	private Long etId;
	private Long doctorId;
	
	@Override
	public String toString() {
		return "IS4ReqAvailService [spId=" + spId + ", etId=" + etId + ", doctorId=" + doctorId + ", getSessionId()="
				+ getSessionId() + "]";
	}
	
}

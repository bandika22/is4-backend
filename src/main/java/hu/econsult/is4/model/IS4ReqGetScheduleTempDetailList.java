package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetScheduleTempDetailList extends IS4MessageHeader {

	private String templateIdList;

	@Override
	public String toString() {
		return "IS4ReqGetScheduleTempDetailList [templateIdList=" + templateIdList + ", sessionId="
				+ getSessionId() + "]";
	}
}

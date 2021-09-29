package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqDeleteTemplateHeader extends IS4MessageHeader {

	private Long templateId;
	private Long deleteSchedule;

	@Override
	public String toString() {
		return "IS4ReqDeleteTemplateHeader [templateId=" + templateId + ", deleteSchedule=" + deleteSchedule
				+ ", sessionId=" + getSessionId() + "]";
	}
}

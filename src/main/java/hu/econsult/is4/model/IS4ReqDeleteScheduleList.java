package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqDeleteScheduleList extends IS4MessageHeader {

	private String scheduleIdList;
	private Long serviceFk;

	@Override
	public String toString() {
		return "IS4ReqDeleteScheduleList [scheduleIdList=" + scheduleIdList + ", serviceFk=" + serviceFk
				+ ", sessionId=" + getSessionId() + "]";
	}
}

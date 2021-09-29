package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGenerateScheduleByTemplate extends IS4MessageHeader {

	private String templateDetailIdList;
	private String status;
	private String dateFrom;
	private String dateTo;
	
	@Override
	public String toString() {
		return "IS4ReqGenerateScheduleByTemplate [templateDetailIdList=" + templateDetailIdList + ", status=" + status
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", sessionId=" + getSessionId() + "]";
	}
}

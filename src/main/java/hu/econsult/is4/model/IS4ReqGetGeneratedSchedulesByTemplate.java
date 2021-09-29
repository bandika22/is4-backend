package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetGeneratedSchedulesByTemplate extends IS4MessageHeader {

	private String name;
	private Long eventTypeFk;
	private Long servicePointFk;
	private String dateFrom;
	private String dateTo;
	
	@Override
	public String toString() {
		return "IS4ReqGetGeneratedSchedulesByTemplate [name=" + name + ", eventTypeFk=" + eventTypeFk
				+ ", servicePointFk=" + servicePointFk + ", sessionId=" + getSessionId() + "]";
	}
}

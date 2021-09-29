package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqScheduleColor extends IS4MessageHeader {
	
	private Long eventTypeId;
	private Long doctorId;
	private Long servicePointId;
	private String dateFrom;
	private String dateTo;

	@Override
	public String toString() {
		return "IS4ReqScheduleColor [eventTypeId=" + eventTypeId + ", doctorId=" + doctorId + ", servicePointId="
				+ servicePointId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", getSessionId()="
				+ getSessionId() + "]";
	}
}

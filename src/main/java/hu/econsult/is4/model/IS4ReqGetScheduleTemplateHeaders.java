package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetScheduleTemplateHeaders extends IS4MessageHeader {

	private String name;
	private Long eventTypeFk;
	private Long servicePointFk;
	
	@Override
	public String toString() {
		return "IS4ReqGetScheduleTemplateHeaders [name=" + name + ", eventTypeFk=" + eventTypeFk + ", servicePointFk="
				+ servicePointFk + ", sessionId=" + getSessionId() + "]";
	}
}

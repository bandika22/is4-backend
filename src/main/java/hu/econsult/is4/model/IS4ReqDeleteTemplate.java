package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqDeleteTemplate extends IS4MessageHeader {

	private String name;
	private Long eventTypeFk;
	private Long servicePointFk;
	private Long deleteSchedule;
	
	@Override
	public String toString() {
		return "IS4ReqDeleteTemplate [name=" + name + ", eventTypeFk=" + eventTypeFk + ", servicePointFk="
				+ servicePointFk + ", deleteSchedule=" + deleteSchedule + ", sessionId=" + getSessionId() + "]";
	}
}

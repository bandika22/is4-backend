package hu.econsult.is4.model;

import hu.econsult.is4.model.xml.CalendarDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveSchedule extends IS4MessageHeader {

	private String contextParam;
	private String calIdList;
	private String scheduleIdList;
	private String mode;
	private String detail;
	private String reason;
	private String calModReasonCode;
	private String medicalDate;
	private Long calId;
	private Long scheduleId;
	private CalendarDetail xmlDetail;
	

	@Override
	public String toString() {
		return "IS4ReqSaveSchedule [contextParam=" + contextParam + ", calIdList=" + calIdList + ", scheduleIdList="
				+ scheduleIdList + ", mode=" + mode + ", detail=" + detail + ", reason=" + reason
				+ ", calModReasonCode=" + calModReasonCode + ", getSessionId()=" + getSessionId() + "]";
	}
}

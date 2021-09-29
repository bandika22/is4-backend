package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqAppointment extends IS4MessageHeader {
	
	private Long spId;
	private Long etId;
	private Long doctorId;
	private String dateFrom;
	private String dateTo;
	private Integer mode;
	
	@Override
	public String toString() {
		return "IS4ReqAppointment [spId=" + spId + ", etId=" + etId + ", doctorId=" + doctorId + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", mode=" + mode + ", getSessionId()=" + getSessionId() + "]";
	}
}

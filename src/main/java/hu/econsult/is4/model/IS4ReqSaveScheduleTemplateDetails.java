package hu.econsult.is4.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveScheduleTemplateDetails extends IS4MessageHeader {

	private List<ObjSaveScheduleTemplateDetail> templateDetailList;
	

	@Override
	public String toString() {
		return "IS4ReqSaveScheduleTemplateDetails [templateDetailList=" + templateDetailList + ", sessionId="
				+ getSessionId() + "]";
	}
}

package hu.econsult.is4.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveScheduleTemplates extends IS4MessageHeader {

	private List<ObjSaveScheduleTemplate> templateList;

	@Override
	public String toString() {
		return "IS4ReqSaveScheduleTemplates [templateList=" + templateList + ", sessionId=" + getSessionId() + "]";
	}
}

package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSaveScheduleTemplateDetail {

	private Long templateId;
	private String timeFrom;
	private String timeTo;
	private Long userFk;
	private String scheduleComment;
	private String color;
	private Long scheduleTemplateFk;
	private Long contingentGroupFk;
	private String day;
	
}

package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4SimpleObjScheduleTemplateDetail {

	private Long id;
	private String timeInterval;
	private IS4TemplateDetailDoctor doctor;
	private IS4TemplateDetailContingent contingent;
	private Long scheduleTemplateFk;
	private String color;
	private String comments;
}

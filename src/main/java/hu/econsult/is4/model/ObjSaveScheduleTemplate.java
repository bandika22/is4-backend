package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSaveScheduleTemplate {

	private Long id;
	private String name;
	private String timeFrom;
	private String timeTo;
	private Long eventMax;
	private Long eventMaxSame;
	private Long userFk;
	private Long eventTypeFk;
	private Long servicePointFk;
	private String scheduleComment;
	private String color;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private Long contingentGroupFk;
	private String week;
}

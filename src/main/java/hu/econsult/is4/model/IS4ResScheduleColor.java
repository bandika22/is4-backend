package hu.econsult.is4.model;


import java.util.List;

import hu.econsult.is4.model.entity.ObjScheduleColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResScheduleColor extends Response {
	
	List<ObjScheduleColor> objScheduleColor;

	@Override
	public String toString() {
		return "IS4ResScheduleColor [objScheduleColor=" + objScheduleColor + ", getCode()=" + getCode()
				+ ", getMessage()=" + getMessage() + "]";
	}
}

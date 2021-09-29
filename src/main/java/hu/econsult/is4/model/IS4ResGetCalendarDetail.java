package hu.econsult.is4.model;

import hu.econsult.is4.model.entity.IS4ObjCalendarDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetCalendarDetail extends Response {
	
	private IS4ObjCalendarDetail calDetail;
	private String name;
	
}

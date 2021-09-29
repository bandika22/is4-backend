package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.IS4ObjGetInstCal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetInstCal extends Response{
	
	private List<IS4ObjGetInstCal> calendarList;

}

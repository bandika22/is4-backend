package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.IS4ObjGetInstWorkSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResGetWorkSchedule extends Response{
	
	private List<IS4ObjGetInstWorkSchedule> workSchedule;

}

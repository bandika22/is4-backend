package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjGetScheduleTemplateHeaders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetScheduleTemplateHeaders extends Response {

	private List<ObjGetScheduleTemplateHeaders> objGetScheduleTemplateHeadersList;

}

package hu.econsult.is4.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetScheduleTempDetailList extends Response {

	private Map<String, List<IS4SimpleObjScheduleTemplateDetail>> resultMap;

}

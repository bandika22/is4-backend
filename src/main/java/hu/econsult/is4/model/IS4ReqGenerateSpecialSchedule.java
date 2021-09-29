package hu.econsult.is4.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGenerateSpecialSchedule extends IS4MessageHeader{
	
	@ApiModelProperty(value = "Kigenerált időpontok listája")
	private List<ObjSpecialSchedule> specialScheduleList;
	
	@Override
	public String toString() {
		return "IS4ReqGenerateSpecialSchedule [getSessionId()=" + getSessionId()
				+ "]";
	}

}

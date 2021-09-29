package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4MessageHeader {
	
	@ApiModelProperty(value= "Munkamenet azonosítója")
	private String sessionId;

	@Override
	public String toString() {
		return "MessageHeader : sessionId=" + sessionId;
	}
	
}

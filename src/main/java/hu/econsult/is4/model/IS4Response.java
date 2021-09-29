package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4Response {

	@ApiModelProperty(value = "Státusz kód")
	private Integer code;
	@ApiModelProperty(value = "Státusz üzenet")
	private String message;
	@ApiModelProperty(value = "Hívás időpontja")
	private String callId;
	@ApiModelProperty(value = "Metódus neve")
	private String methodName;
	
	@Override
	public String toString() {
		return "IS4Response [code=" + code + ", message=" + message + ", callId=" + callId + ", methodName="
				+ methodName + "]";
	}
}

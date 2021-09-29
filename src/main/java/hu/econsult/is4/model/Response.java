package hu.econsult.is4.model;

import static hu.econsult.is4.util.ErrorCodes.CODE_OK;
import static hu.econsult.is4.util.ErrorCodes.MSG_OK;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	
	@ApiModelProperty(value = "Státusz kód")
	private Integer code;
	@ApiModelProperty(value = "Státusz üzenet")
	private String message;
	

	public void setCodeAndMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public void setOk() {
		this.code = CODE_OK;
		this.message = MSG_OK;
	}
	
	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + "]";
	}
	
}
package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUReqCheckUser {
	
	@ApiModelProperty(value = "Session id")
	private String sessionId;
	
	@ApiModelProperty(value = "Páciens azonosítására szolgáló érték")
	private String id;
	
	@ApiModelProperty(value = "Születési név")
	private String birthName;
	
	@ApiModelProperty(value = "Születési hely")
	private String dateOfBirth;
	
	@ApiModelProperty(value = "Születési idő")
	private String placeOfBirth;
	
	@ApiModelProperty(value = "Anyja neve")
	private String mothersName;
	
	@ApiModelProperty(value = "Okmány típus kódja")
	private String ssnType;
	
	@ApiModelProperty(value = "Igazoló okmány száma")
	private String ssnNumber;
}

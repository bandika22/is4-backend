package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUReqGetDoctorToServicePoint {
	
	@ApiModelProperty(value = "A beküldő orvos pecsétszáma")
	private String sealNumber;
	
	@ApiModelProperty(value = "A beküldő intézmény azonosító kódja.")
	private String spCode;

}

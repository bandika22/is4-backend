package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetSpecialDays extends IS4MessageHeader{

	@ApiModelProperty(value = "Engedély típusa")
	private String permissionType;
	
}

package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveSpecialDay extends IS4MessageHeader{

	
	@ApiModelProperty(value = "Szabadnap azonosítója")
	private Long id;
	@ApiModelProperty(value = "Bedolgozandó nap")
	private String day;
	@ApiModelProperty(value = "Bedolgozás időpontja")
	private String dayReplace;
	@ApiModelProperty(value = "Évente ismétlődő")
	private String yearly;
	@ApiModelProperty(value = "Szolgáltatási pont azonosítója")
	private Long servicePointFk;	
}

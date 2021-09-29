package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSpecialDay {

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

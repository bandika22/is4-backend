package hu.econsult.is4.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSpecialSchedule {

	@ApiModelProperty(value = "Rendkívüli időpont kezdete")
	private String dateFrom;
	@ApiModelProperty(value = "Rendkívüli időpont vége")
	private String dateTo;
	@ApiModelProperty(value = "A személy azonosítója")
	private Long userFk;
	@ApiModelProperty(value = "Szolgáltatás azonosítója")
	private Long serviceFk;
	@ApiModelProperty(value = "Szolgáltatási pont azonosítója")
	private Long servicePointFk;
	@ApiModelProperty(value = "Esemény típus azonosítója")
	private Long eventTypeFk;
	@ApiModelProperty(value = "Egyéb megjegyzés")
	private String scheduleComment;
	@ApiModelProperty(value = "Szín")
	private String color;
	@ApiModelProperty(value = "Időpont sablon azonosítója")
	private Long scheduleTemplateFk;
	@ApiModelProperty(value = "Kontingens csoport azonosítója")
	private Long contingentGroupFk;
	@ApiModelProperty(value = "Állapot")
	private String status;
	@ApiModelProperty(value = "Sablon neve")
	private String scheduleTemplateName;
	
}

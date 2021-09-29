package hu.econsult.is4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHeader {

	@ApiModelProperty(value= "Aktuális user tapass")
	private String currentTapass;
	@ApiModelProperty(value= "Aktuális szerepkör kódja")
	private String currentRole;
	@ApiModelProperty(value= "Aktuális szolgáltatási pont azonosítója")
	private Long currentServicePointId;
	@ApiModelProperty(value= "Aktuális nyelvterület kódja")
	private String locale;
	@ApiModelProperty(value= "Aktuális user tapass")
	private String deviceId;
	@ApiModelProperty(value= "Aktuális munkamenet azonosító. Kizáró viselkedés a többi paraméterrel (kivétel: locale, deviceId)! ")
	private String sessionId;
	
	@Override
	public String toString() {
		return "NEUMessageHeader [currentTapass=" + currentTapass + ", currentRole=" + currentRole
				+ ", currentServicePointId=" + currentServicePointId + ", locale=" + locale + ", deviceId=" + deviceId
				+ ", sessionId=" + sessionId + "]";
	}
	
}
package hu.econsult.is4.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "A")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModifyAppointmentData {
	
	@XmlElement(name = "creatorUser")
	private String creatorUser;
	
	@XmlElement(name = "creatorSp")
	private String creatorSp;
	
	@XmlElement(name = "providerUser")
	private String providerUser;
	
	@XmlElement(name = "providerSp")
	private String providerSp;
	
}

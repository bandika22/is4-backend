package hu.econsult.is4.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentData {

	@XmlAttribute
	private String scheduleId;
	
	@XmlAttribute
	private String createDate;
	
	@XmlAttribute
	private String createType;
	
	@XmlAttribute
	private String creatorUser;
	
	@XmlAttribute
	private String creatorSp;
	
	@XmlAttribute
	private String providerUser;
	
	@XmlAttribute
	private String providerSp;
	
	@XmlAttribute
    private String online;
}

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
public class CalendarDetail {
	
	@XmlElement(name = "PATDATA")
	private PatiendData patData;
	
	@XmlElement(name = "APPDATA")
	private AppointmentData appData;
	
	@XmlElement(name = "SENDDATA")
	private SendData sendData;

}

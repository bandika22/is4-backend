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
public class GetCalendarHistoryData {
	
	@XmlElement(name = "PATDATA")
	private PatiendData patData;
	
	@XmlElement(name = "LOGS")
	private LogData logData;

}

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
public class Log {
	
	@XmlAttribute
	private String changeType;
	
	@XmlAttribute
	private String lastModUser;
	
	@XmlAttribute
	private String lastModTime;

}

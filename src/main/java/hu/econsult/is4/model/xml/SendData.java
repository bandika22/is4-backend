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
public class SendData {
	
	@XmlAttribute
	private String sendDate;
	
	@XmlAttribute
	private String sendNo;
	
	@XmlAttribute
	private String sendSpName;
	
	@XmlAttribute
	private String sendSpCode;
	
	@XmlAttribute
	private String sendDrName;
	
	@XmlAttribute
	private String sendDrSeal;
	
	@XmlAttribute
	private String sendDiag;
	
	@XmlAttribute
	private String sendComment;
}

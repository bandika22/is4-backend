package hu.econsult.is4.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;

import hu.econsult.is4.model.xml.GetCalendarHistoryData;
import hu.econsult.is4.model.xml.PatiendData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInstCalHistory {
	
	private Long id;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String doctorName;
	private String eventTypeName;
	private String servicePointName;
	private PatiendData patData;
	private String contingent;
	private String changeType;
	private String lastModUser;
	private String lastModTime;
	private Boolean hidePatient;

}

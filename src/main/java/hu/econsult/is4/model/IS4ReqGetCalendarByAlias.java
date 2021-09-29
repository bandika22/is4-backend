package hu.econsult.is4.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetCalendarByAlias extends IS4MessageHeader {
	
	private String alias;
	private String dateFrom;
	private String dateTo;

}

package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	protected MessageHeader messageHeader;
	
	@Override
	public String toString() {
		return "NEURequest [messageHeader=" + messageHeader + "]";
	}
	
}

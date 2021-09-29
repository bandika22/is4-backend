package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUResUpdateSession extends Response {
	
	private String sessionId;
	private String retMsg;
	
}

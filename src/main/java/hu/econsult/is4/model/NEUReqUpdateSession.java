package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUReqUpdateSession {
	
	private String sessionId;
	private String authRole;
	private String servicePoint;
	private Boolean logout;
	private Boolean keepAlive;	
	
}

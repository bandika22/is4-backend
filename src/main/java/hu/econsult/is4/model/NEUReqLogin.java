package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUReqLogin {
	
	private String userName;
	private String password;
	private String deviceID;
	private String spCode;	
}

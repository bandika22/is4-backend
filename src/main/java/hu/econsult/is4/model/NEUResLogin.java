package hu.econsult.is4.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUResLogin extends Response{
	
	private String sessionId;
	private Date expTime;	
}

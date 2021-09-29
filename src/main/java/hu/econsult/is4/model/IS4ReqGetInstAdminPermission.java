package hu.econsult.is4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqGetInstAdminPermission extends IS4MessageHeader {
	
	private String uuid;
	private Long moduleRoleFk;
	private String type;
}

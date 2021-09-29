package hu.econsult.is4.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSavePermissionToUser extends IS4MessageHeader {

	private String uuid;
	private Long moduleRoleFk;
	private List<ObjSavePermissionToUser> roleList;
}

package hu.econsult.is4.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ReqSaveRoleToUser extends IS4MessageHeader {

	private String uuid;
	private List<ObjSaveRoleToUser> roleList;

	@Override
	public String toString() {
		return "IS4ReqSaveScheduleTemplates [roleList=" + roleList + ", sessionId=" + getSessionId() + ", uuid=" + getUuid() + "]";
	}
}

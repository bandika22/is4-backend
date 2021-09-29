package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjGetInstAdminUserSps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetInstAdminUserSps extends Response {
	
	private List<ObjGetInstAdminUserSps> userSps;
	
}

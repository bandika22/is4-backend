package hu.econsult.is4.model;

import java.util.List;
import java.util.Map;

import hu.econsult.is4.model.entity.IS4ObjGetInstAdminPermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResGetInstAdminPermissions extends Response{
	
	Map<String, List<IS4ObjGetInstAdminPermissions>> permissionList;

}

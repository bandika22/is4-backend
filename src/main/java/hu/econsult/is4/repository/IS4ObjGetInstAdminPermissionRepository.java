package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstAdminPermissions;

@Repository
public interface IS4ObjGetInstAdminPermissionRepository extends CrudRepository<IS4ObjGetInstAdminPermissions, Long>{
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_permissions(:sessionId, TO_CLOB(:contextParam), :uuid, :moduleRoleFk, :type))", 
			nativeQuery = true)
	List<IS4ObjGetInstAdminPermissions> getInstAdminPermission(
			@Param("sessionId") String sessionId, 
			@Param("contextParam") String contextParam, 
			@Param("uuid") String uuid,
			@Param("moduleRoleFk") Long moduleRoleFk, 
			@Param("type") String type);
	
}

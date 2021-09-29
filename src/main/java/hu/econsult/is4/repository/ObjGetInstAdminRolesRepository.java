package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hu.econsult.is4.model.entity.ObjGetInstAdminRoles;

public interface ObjGetInstAdminRolesRepository extends CrudRepository<ObjGetInstAdminRoles, Long>{
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_auth_roles(:sessionId, TO_CLOB(:contextParam), :uuid))", nativeQuery = true)
	List<ObjGetInstAdminRoles> getInstAdminRoles(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam,
			@Param("uuid") String uuid
			);
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_user_sp_roles(:sessionId, TO_CLOB(:contextParam), :uuid))", nativeQuery = true)
	List<ObjGetInstAdminRoles> getInstAdminUserSpRoles(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam,
			@Param("uuid") String uuid
			);

}

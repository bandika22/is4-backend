package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstAdminRoles;

@Repository
public interface IS4ObjGetInstAdminRolesRepository extends CrudRepository<IS4ObjGetInstAdminRoles, Long>{
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_all_sp_et(:sessionId, TO_CLOB(:contextParam), :uuid, :moduleRoleFk))", 
			nativeQuery = true)
	List<IS4ObjGetInstAdminRoles> getInstAdminRoles(
			@Param("sessionId") String sessionId, 
			@Param("contextParam") String contextParam, 
			@Param("uuid") String uuid,
			@Param("moduleRoleFk") Long moduleRoleFk
			);
	
}

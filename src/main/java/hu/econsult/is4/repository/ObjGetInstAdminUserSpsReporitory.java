package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetInstAdminUserSps;

@Repository
public interface ObjGetInstAdminUserSpsReporitory extends CrudRepository<ObjGetInstAdminUserSps, Long>{
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_user_sps(:sessionId, TO_CLOB(:contextParam), :uuid))", nativeQuery = true)
	List<ObjGetInstAdminUserSps> getUserSps(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam,
			@Param("uuid") String uuid
			);

}

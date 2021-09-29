package hu.econsult.is4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjSessionExt;

@Repository
public interface SessionRepository extends CrudRepository<ObjSessionExt, String>{
	
	@Query(value = "select * from table(authority.pkg_naucomm_auth.fn_get_current_session_as_tbl(:sessionId))",
			nativeQuery = true)
	ObjSessionExt callFnGetCurrentSession(@Param("sessionId") String sessionId);

	@Query(value = "select PKG_IS4.F_CHECK_USER(:p1, :p2, :p3) from dual",
			nativeQuery = true)
	String callFnCheckUserWithHash(@Param("p1") String id, 
								   @Param("p2") String hash2T, 
								   @Param("p3") String hash4T);
	
	@Query(value = "select calendar.f_get_root_sp_code_by_session(:sessionId, TO_CLOB(:contextParam)) from dual",
			nativeQuery = true)
	String callFnGetRootSpCodeBySession(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam
			);
}

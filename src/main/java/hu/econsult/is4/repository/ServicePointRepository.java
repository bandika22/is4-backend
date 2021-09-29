package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjServicePoint;

@Repository
public interface ServicePointRepository extends CrudRepository<ObjServicePoint, Long> {

	@Query(value = "select * from table(authority.pkg_naucomm_auth.fn_get_user_sps(TO_NUMBER(:userId), :roleCode, :permType, :sessionId, TO_CLOB(:contextParam)))",
			nativeQuery = true)
	List<ObjServicePoint> callFnGetUserServicePoints(@Param("userId") Long userId, 
													 @Param("roleCode") String currentRole, 
													 @Param("permType") String permType, 
													 @Param("sessionId") String sessionId, 
													 @Param("contextParam") String contextParams);
}

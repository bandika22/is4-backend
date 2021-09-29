package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetContingentDetails;
import hu.econsult.is4.model.entity.IS4ObjGetInstituteData;

@Repository
public interface InstituteDataRepository extends CrudRepository<IS4ObjGetInstituteData, Long>{

	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_cont_sp(:p1, :p2))",
			nativeQuery = true)
	List<IS4ObjGetInstituteData> callFnGetContSp(@Param("p1") String sessionId, 
												 @Param("p2") String contextParam);
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_not_inst_roles(:p1,:p2))",
			nativeQuery = true)
	List<IS4ObjGetInstituteData> callFnGetNotInstRoles(@Param("p1") String sessionId, 
			 										   @Param("p2") String contextParam);
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_inst_roles(:p1,:p2))",
			nativeQuery = true)
	List<IS4ObjGetInstituteData> callFnGetInstRoles(@Param("p1") String sessionId, 
			   										@Param("p2") String contextParam);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_get_inst_sp(:p1, :p2, :p3))", 
			nativeQuery = true)
	List<IS4ObjGetInstituteData> callFnGetInstSp(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String permissionType);

	
//	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_inst_contingent_details(:p1,:p2,:3))",
//			nativeQuery = true)
//	List<IS4ObjGetContingentDetails> callFnGetContingentDetails(@Param("p1") String sessionId,
//																@Param("p2") String contextParam,
//																@Param("3")  Long contingentId);
	
}

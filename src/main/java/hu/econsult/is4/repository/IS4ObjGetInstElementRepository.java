package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstituteData;
import hu.econsult.is4.model.entity.ObjGetInstElement;

@Repository
public interface IS4ObjGetInstElementRepository extends CrudRepository<ObjGetInstElement, Long>{
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_get_inst_doctor(:p1, :p2, :p3))", 
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetInstDocor(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String permissionType);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_get_cont_user(:p1, :p2))", 
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetContUser(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_get_inst_et(:p1, :p2, :p3))", 
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetInstEt(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String permissionType);
	
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_perm_on_sp(:p1, :p2, :p3))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetAllSp(@Param("p1") String sessionId, 
										   @Param("p2") String contextParam, 
										   @Param("p3") String permissionType);
	
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_perm_on_et(:p1, :p2, :p3))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetAllEt(@Param("p1") String sessionId, 
										   @Param("p2") String contextParam, 
										   @Param("p3") String permissionType);
	
	@Cacheable({"ObjGetInstElement"})
	@Query(value = "select * from table(calendar.pkg_is4.fn_all_bno())",
			nativeQuery = true)
	List<ObjGetInstElement> callFnAllBno();	
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_perm_type_by_session(:p1,:p2))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetPermissionBySessionId(@Param("p1") String sessionId, @Param("p2") String argp2);
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_inst_contingent(:p1,:p2))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetInstContingent(@Param("p1") String sessionId, 
														 @Param("p2") String contextParam);
	
}

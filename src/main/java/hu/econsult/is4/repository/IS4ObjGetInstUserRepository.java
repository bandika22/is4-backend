package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstUser;
import hu.econsult.is4.model.entity.ObjGetInstElement;

@Repository
public interface IS4ObjGetInstUserRepository extends CrudRepository<ObjGetInstElement, Long> {

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_template_doc(:p1, :p2, TO_NUMBER(:p3)))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetTemplateDoc(@Param("p1") String sessionId, 
												 @Param("p2") String contextParam, 
												 @Param("p3") Integer spId);
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_cont_user(:p1,:p2))",
			nativeQuery = true)
	List<ObjGetInstElement> callFnGetContUser(@Param("p1") String sessionId, 
											  @Param("p2") String contextParam);
}

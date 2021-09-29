package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetTemplateCont;

@Repository
public interface TemplateContRepository extends CrudRepository<ObjGetTemplateCont, Long>{

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_template_cont(:p1, :p2, :p3))",
			nativeQuery = true)
	List<ObjGetTemplateCont> callFnGetTemplateCont(@Param("p1") String sessionId, 
												   @Param("p2") String contextParam, 
												   @Param("p3") String permissionType);
}

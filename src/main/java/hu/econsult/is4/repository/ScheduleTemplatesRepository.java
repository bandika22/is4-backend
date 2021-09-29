package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetScheduleTemplates;

@Repository
public interface ScheduleTemplatesRepository extends CrudRepository<ObjGetScheduleTemplates, Long> {
	
	@Query(value = "select * from table(pkg_is4.fn_get_schedule_templates(:p1, :p2, :p3))",
			nativeQuery = true)
	List<ObjGetScheduleTemplates> callFnGetScheduleTemplates(@Param("p1") String sessionId, 
															 @Param("p2") String contexParam, 
															 @Param("p3") String permissionType);
}

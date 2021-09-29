package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetScheduleTemplateDetailList;

@Repository
public interface ScheduleTemplateDetailListRepository extends CrudRepository<ObjGetScheduleTemplateDetailList, Long>{

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_schedule_templates_d(:p1, :p2, :p3))",
			nativeQuery = true)
	List<ObjGetScheduleTemplateDetailList> callFnGetScheduleTemplateDetailList(@Param("p1") String sessionId, 
																			   @Param("p2") String contextParam, 
																			   @Param("p3") String templateIdList);
}

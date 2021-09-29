package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetScheduleTemplateHeaders;

@Repository
public interface ScheduleTemplateHeadersRepository extends CrudRepository<ObjGetScheduleTemplateHeaders, Long>{

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_schedule_temp_headers(:p1, :p2, :p3, TO_NUMBER(:p4), TO_NUMBER(:p5)))",
			nativeQuery = true)
	List<ObjGetScheduleTemplateHeaders> callFnGetScheduleTemplateHeaders(@Param("p1") String sessionId, 
																		 @Param("p2") String contextParam, 
																		 @Param("p3") String name, 
																		 @Param("p4") Long eventTypeFk, 
																		 @Param("p5") Long servicePointFk);
}

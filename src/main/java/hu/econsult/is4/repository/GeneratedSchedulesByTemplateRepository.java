package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjGetGeneratedSchedulesByTemplate;

@Repository
public interface GeneratedSchedulesByTemplateRepository extends CrudRepository<ObjGetGeneratedSchedulesByTemplate, Long>{

	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_generated_sch_by_temp(:p1, :p2, :p3, TO_NUMBER(:p4), TO_NUMBER(:p5), :p6, :p7))",
			nativeQuery = true)
	List<ObjGetGeneratedSchedulesByTemplate> callFnGetGeneratedSchedulesByTemplate(@Param("p1") String sessionId, 
																				   @Param("p2") String contextParam, 
																				   @Param("p3") String name, 
																				   @Param("p4") Long eventTypeId, 
																				   @Param("p5") Long servicePointId, 
																				   @Param("p6") String dateFrom, 
																				   @Param("p7") String dateTo);
}

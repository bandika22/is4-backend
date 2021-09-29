package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstWorkSchedule;

@Repository
public interface IS4ObjGetWorkScheduleRepository extends CrudRepository<IS4ObjGetInstWorkSchedule, Long> {

	@Query(value = "select * from table(calendar.pkg_is4.fn_schedule_timetable(:sessionId, TO_CLOB(:contextParam), TO_NUMBER(:spId), TO_NUMBER(:etId), :dateFrom, :dateTo)) order by doctor_name nulls first, date_from",
			nativeQuery = true)
	List<IS4ObjGetInstWorkSchedule> callFnGetInstWorkSchedule(@Param("sessionId") String sessionId, 
												 @Param("contextParam") String contextParam, 
												 @Param("spId") Long spId, 
												 @Param("etId") Long etId, 
												 @Param("dateFrom") String dateFrom, 
												 @Param("dateTo") String dateTo);
	
}

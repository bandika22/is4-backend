package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstCalHistory;

@Repository
public interface Is4ObjGetInstCalHistoryRepository extends CrudRepository<IS4ObjGetInstCalHistory, Long> {

	@Query(value = "select * from table(calendar.pkg_is4.fn_calendar_history(:p1, TO_CLOB(:p2), TO_NUMBER(:p3), TO_NUMBER(:p4), TO_NUMBER(:p5), :p6, :p7, :p8))",
			nativeQuery = true)
	List<IS4ObjGetInstCalHistory> callFnGetInstCalendarHistory(@Param("p1") String sessionId, 
												 @Param("p2") String contextParam, 
												 @Param("p3") Long spId, 
												 @Param("p4") Long etId, 
												 @Param("p5") Long doctorId, 
												 @Param("p6") String dateFrom, 
												 @Param("p7") String dateTo, 
												 @Param("p8") String status);
	
}

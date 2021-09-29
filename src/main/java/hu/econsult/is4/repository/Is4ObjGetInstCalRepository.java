package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetInstCal;

@Repository
public interface Is4ObjGetInstCalRepository extends CrudRepository<IS4ObjGetInstCal, Long> {

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_institute_calendar_new(:p1, :p2, TO_NUMBER(:p3), TO_NUMBER(:p4), TO_NUMBER(:p5), :p6, :p7, :p8))",
			nativeQuery = true)
	List<IS4ObjGetInstCal> callFnGetInstCalendar(@Param("p1") String sessionId, 
												 @Param("p2") String contextParam, 
												 @Param("p3") Long spId, 
												 @Param("p4") Long etId, 
												 @Param("p5") Long doctorId, 
												 @Param("p6") String dateFrom, 
												 @Param("p7") String dateTo, 
												 @Param("p8") String status);
	
	@Query(value = "select pkg_is4.f_check_user_has_permission(:p1, :p2, TO_NUMBER(:p3), TO_NUMBER(:p4), :p5) from dual",
			nativeQuery = true)
	Integer callFnCheckUserHasPermission(@Param("p1") String sessionId, 
										 @Param("p2") String contextParam, 
										 @Param("p3") Long spId, 
										 @Param("p4") Long etId, 
										 @Param("p5") String permissionType);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_calendar_by_patient(:p1, TO_CLOB(:p2), :p3, :p4, :p5))",
			nativeQuery = true)
	List<IS4ObjGetInstCal> callFnGetCalendarByPatient(@Param("p1") String sessionId, 
													  @Param("p2") String contextParam, 
													  @Param("p3") String patientId, 
													  @Param("p4") String dateFrom, 
													  @Param("p5") String dateTo);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_calendar_by_alias(:p1,TO_CLOB(:p2),:p3,:p4,:p5))", 
			nativeQuery = true)
	List<IS4ObjGetInstCal> callFnGetCalendarByAlias(@Param("p1") String sessionId, 
													@Param("p2") String contextParam, 
													@Param("p3") String alias, 
													@Param("p4") String dateFrom,
													@Param("p5") String dateTo);
}

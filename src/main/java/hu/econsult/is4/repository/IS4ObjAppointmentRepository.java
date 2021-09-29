package hu.econsult.is4.repository;

import java.sql.Clob;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjAppointment;
import oracle.sql.CLOB;

@Repository
public interface IS4ObjAppointmentRepository extends CrudRepository<IS4ObjAppointment, Long>{
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_institute_schedule(:p1, :p2, TO_NUMBER(:p3), TO_NUMBER(:p4), TO_NUMBER(:p5), :p6, :p7, :p8))", 
			nativeQuery = true)
	List<IS4ObjAppointment> callFnGetScheduleList(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") Long servicePointId, 
			@Param("p4") Long eventId, 
			@Param("p5") Long doctorId, 
			@Param("p6") String dateFrom, 
			@Param("p7") String dateTo, 
			@Param("p8") Integer mode);
	
	@Query(value = "select calendar.pkg_is4.f_check_same_cal(:p1, :p2, TO_NUMBER(:p3), :p4, :p5, TO_NUMBER(:p6), :p7) from dual", nativeQuery = true)
	Integer callCheckSameCal(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") Integer patHisId, 
			@Param("p4") String patNauId, 
			@Param("p5") String alias, 
			@Param("p6") Integer serviceId, 
			@Param("p7") String date);
	
	@Query(value = "select calendar.pkg_is4.f_get_schedule_detail(:p1, TO_NUMBER(:p2), :p3) from dual", nativeQuery = true)
	Clob callFnGetScheduleDetail(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") Long scheduleId);
	
	@Query(value = "select pkg_is4.f_check_cont_list(:p1, :p2, :p3) from dual", nativeQuery = true)
	Integer callFnCheckContingentList(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String scheduleList);

}

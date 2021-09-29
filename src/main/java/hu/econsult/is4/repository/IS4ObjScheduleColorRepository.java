package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjScheduleColor;

@Repository
public interface IS4ObjScheduleColorRepository extends CrudRepository<ObjScheduleColor, Long>{
	
	@Query(value = "SELECT * FROM TABLE(CALENDAR.PKG_IS4.FN_INST_SCH_LOAD(:p1,TO_NUMBER(:p2), TO_NUMBER(:p3),TO_NUMBER(:p4),:p5,:p6))", nativeQuery = true)
	List<ObjScheduleColor> callFnInstituteScheduleLoadIS4(
			@Param("p1") String sessionId, 
			@Param("p2") Long servicePointId, 
			@Param("p3") Long eventTypeId, 
			@Param("p4") Long doctorId, 
			@Param("p5") String dateTo, 
			@Param("p6") String dateFrom);
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_sch_load_by_template(:p1,:p2,:p3,TO_NUMBER(:p4),TO_NUMBER(:p5),:p6,:p7))",
			nativeQuery = true)
	List<ObjScheduleColor> callFnScheduleLoadByTemplate(@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String name, 
			@Param("p4") Long eventTypeId, 
			@Param("p5") Long servicePointId, 
			@Param("p6") String dateFrom, 
			@Param("p7") String dateTo);

}

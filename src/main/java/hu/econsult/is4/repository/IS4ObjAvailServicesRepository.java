package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjAvailServices;

@Repository
public interface IS4ObjAvailServicesRepository extends CrudRepository<IS4ObjAvailServices, Long>{

	@Query(value = "select * from table(calendar.pkg_is4.fn_get_avail_serv_cal(:p1, :p2, TO_NUMBER(:p3), TO_NUMBER(:p4), TO_NUMBER(:p5)))",
			nativeQuery = true)
	List<IS4ObjAvailServices> callFnGetAvailServiceCal(@Param("p1") String sessionId, 
													   @Param("p2") String contextParam, 
													   @Param("p3") Long spId, 
													   @Param("p4") Long etId, 
													   @Param("p5") Long doctorId);
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_get_avail_serv(:p1, :p2, TO_NUMBER(:p3), TO_NUMBER(:p4), TO_NUMBER(:p5)))",
			nativeQuery = true)
	List<IS4ObjAvailServices> callFnGetAvailService(@Param("p1") String sessionId, 
													@Param("p2") String contextParam, 
													@Param("p3") Long spId, 
													@Param("p4") Long etId, 
													@Param("p5") Long doctorId);
}

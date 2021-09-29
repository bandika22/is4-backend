package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetSpecialDay;

@Repository
public interface IS4ObjGetSpecialDayRepository extends CrudRepository<IS4ObjGetSpecialDay, Long>{
	
	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_special_days(:p1,:p2,:p3))", nativeQuery = true)
	List<IS4ObjGetSpecialDay> callFnGetSpecialDays(
			@Param("p1") String sessionId, 
			@Param("p2") String contextParam, 
			@Param("p3") String permissionType);

}

package hu.econsult.is4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjCalendarDetail;

@Repository
public interface IS4ObjCalendarDetailRepository extends CrudRepository<IS4ObjCalendarDetail, Long> {
	
	@Query(value = "select * from table(calendar.pkg_is4.f_get_calendar_detail(TO_NUMBER(:p1)))", nativeQuery = true)
	IS4ObjCalendarDetail callFnIS4GetCalendarDetail(
			@Param("p1") Long calId);

}

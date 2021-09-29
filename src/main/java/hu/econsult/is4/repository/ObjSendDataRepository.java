package hu.econsult.is4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjSendData;

@Repository
public interface ObjSendDataRepository extends CrudRepository<ObjSendData, Long>{
	
	@Query(value = "select * from table(calendar.pkg_is4.fn_cal_senddata(:sessionId, TO_CLOB(:contextParam), :calId))", nativeQuery = true)
	ObjSendData getSendData(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam,
			@Param("calId") Long calId
			);
}

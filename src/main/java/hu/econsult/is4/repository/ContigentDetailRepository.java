package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.IS4ObjGetContingentDetails;

@Repository
public interface ContigentDetailRepository extends CrudRepository<IS4ObjGetContingentDetails, Long>{

	@Query(value = "SELECT * FROM TABLE(calendar.pkg_is4.fn_get_inst_contingent_details(:p1,:p2, TO_NUMBER(:p3)))",
			nativeQuery = true)
	List<IS4ObjGetContingentDetails> callFnGetContingentDetails(@Param("p1") String sessionId,
																@Param("p2") String contextParam,
																@Param("p3") Long contingentId);
}

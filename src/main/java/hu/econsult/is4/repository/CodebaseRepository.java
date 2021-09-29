package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjCodeBase;

@Repository
public interface CodebaseRepository extends CrudRepository<ObjCodeBase, Long>{

	@Cacheable({"ObjCodeBase"})
	@Query(value = "SELECT ID, TYPE_CODE, TYPE_DESC, CODE, NAME, DESCRIPTION, VALID_FROM, VALID_TO, DETAIL_STR FROM TABLE(PKG_NAUCOMM.FN_GET_CODEBASE_LIST(:p1))",
			nativeQuery = true)
	List<ObjCodeBase> callFnCodeBaseList(@Param("p1") String typeCode);
}

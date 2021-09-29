package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.econsult.is4.model.entity.ObjDoctorToSp;

@Repository
public interface ObjDoctorToSpRepository extends CrudRepository<ObjDoctorToSp, Long> {

	@Query(value = "select * from table(pkg_naucomm.fn_get_doc2sp(:p1, :p2))", nativeQuery = true)
	List<ObjDoctorToSp> callFnGetDoctor2Sp(
			@Param("p1") String sealNumber, 
			@Param("p2") String spCode);
	
}

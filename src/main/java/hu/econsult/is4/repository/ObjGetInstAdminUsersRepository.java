package hu.econsult.is4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hu.econsult.is4.model.entity.ObjGetInstAdminUsers;

public interface ObjGetInstAdminUsersRepository extends CrudRepository<ObjGetInstAdminUsers, Long>{
	
	@Query(value = "select * from table(nauticom.pkg_inst_admin.fn_get_inst_users(:sessionId, TO_CLOB(:contextParam)))", nativeQuery = true)
	List<ObjGetInstAdminUsers> getInstAdminUsers(
			@Param("sessionId") String sessionId,
			@Param("contextParam") String contextParam
			);

}

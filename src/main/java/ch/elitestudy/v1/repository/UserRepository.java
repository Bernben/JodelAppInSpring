package ch.elitestudy.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.elitestudy.v1.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
//	User findByEmail(String email);
	User findByUserName(String username);

	@Query(value = "From User user where user.userName = :user")
	List<User> findByUserNameList(@Param("user") String user);

}

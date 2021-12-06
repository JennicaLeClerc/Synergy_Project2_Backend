package com.revature.shms.repositories;
import com.revature.shms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllByOrderByUserIDDesc();
  
    Optional<User> findByUsername(String username);
	Optional<User>  findByUserID(int userID);

	boolean existsByUsernameAndPassword(String username, String password);
    boolean updatePassword(String username,String password);
    boolean updatePassword(String password);
}

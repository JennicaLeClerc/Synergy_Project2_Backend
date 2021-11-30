package com.revature.shms.repositories;


import com.revature.shms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllOrderByUserIDDesc();

    User findByUsername(String username);

    User findByUserID(int userID);
}

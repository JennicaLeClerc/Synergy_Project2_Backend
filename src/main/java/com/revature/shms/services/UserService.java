package com.revature.shms.services;

import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.Employee;
import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Service // this annotation is to denote that this is a service class for user
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    //this method create a new user and add it to the repository
    public User createNewUser(User user ){
        return userRepository.save(user);
    }

    /*
    * if the userName and password exists in the repository return true and log in the user
    * otherwise return false
     */
    public User login(String userName,String password) throws AccessDeniedException {
		try {
			User user = getUserByUserName(userName);
			if (user.getPassword().equals(password)) return user;
		} catch (EntityNotFound e) { throw new AccessDeniedException("Incorrect username/password");}
		throw new AccessDeniedException("Incorrect username/password");
	}

    /*
    when the user click via link or button via a get request to logout
    this method redirect the user to a the log out page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:logoutPage";
    }

    public List<User> getAllUsers(){
        return userRepository.findAllByOrderByUserIDDesc();
    }

    public User getUserByUserName(String userName) throws EntityNotFound {
        return userRepository.findByUsername(userName).orElseThrow(EntityNotFound::new);
    }

    public User getUserByUserId(int userId) throws EntityNotFound {
        return userRepository.findByUserID(userId).orElseThrow(EntityNotFound::new);
    }
}

























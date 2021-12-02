package com.revature.shms.services;

import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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

    /**
     * Saves the user to the database.
     * @param user the user to be saved.
     * @return the User, but not saved to the database.
     */
    public User createNewUser(User user ){
        return userRepository.save(user);
    }

    /**
     * Logs in the user with the given username and password, then returns that User.
     * @param userName the username to match.
     * @param password the password to match.
     * @return User of the given username AND password.
     * @throws AccessDeniedException if the username AND password aren't in the database together this will be thrown.
     */
    public User login(String userName,String password) throws AccessDeniedException {
		try {
			User user = getUserByUsername(userName);
			if (user.getPassword().equals(password)) return user;
		} catch (NotFound e) { throw new AccessDeniedException("Incorrect username/password");}
		throw new AccessDeniedException("Incorrect username/password");
	}

    /**
     * Logs out the user and redirect them to the logout page.
     * @return String that redirects the user to the logout page.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:logoutPage";
    }

    /**
     * Gets a list of all users.
     * @return List<User> for all users in the database ordered by UserID in descending order.
     */
    public List<User> getAllUsers(){
        return userRepository.findAllByOrderByUserIDDesc();
    }

    /**
     * Get the user with the given username.
     * @param username the username to match.
     * @return User with the given username.
     * @throws NotFound if the username is not in the database this will be thrown.
     */
    public User getUserByUsername(String username) throws NotFound {
        return userRepository.findByUsername(username).orElseThrow(NotFound::new);
    }

    /**
     * Get the user with the given user ID.
     * @param userID the user ID to match.
     * @return User with the given userID.
     * @throws NotFound if the userID is not in the database this will be thrown.
     */
    public User getUserByUserID(int userID) throws NotFound {
        return userRepository.findByUserID(userID).orElseThrow(NotFound::new);
    }
}

























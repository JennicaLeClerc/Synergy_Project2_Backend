package com.revature.shms.services;

import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Service // this annotation is to denote that this is a service class for user
public class UserService{
    //the following code uses constructor injection
    //to supply the userRepository class instead of instantiated it
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //this method create a new user and add it to the repository
    public User createNewUser(User user ){
        return userRepository.save(user);
    }

    /*
    * if the userName and password exists in the repository return true and log in the user
    * otherwise return false
     */
    public boolean login(String userName,String password){
if(userRepository.existsbyUserNameAndPassword(userName, password)){
    return true;
}
return false;
    }

    /*
    when the user click via link or button via a get request to logout
    this method redirect the user to a the log out page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:logoutPage";
    }

}

























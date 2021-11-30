package com.revature.shms.services;

import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public boolean login(){

        return false;
    }



























}
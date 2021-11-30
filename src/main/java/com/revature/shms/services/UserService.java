package com.revature.shms.services;

import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service // this annotation is to denote that this is a service class for user
public class UserService{
    //the following code uses constructor injection
    //to supply the userRepository class instead of instantiated it
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User createNewUser(User user ){
        return userRepository.save(user);
    }
























}
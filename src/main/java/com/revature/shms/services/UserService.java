package com.revature.shms.services;

import com.revature.shms.models.User;
import com.revature.shms.repositories.RoomRepository;
import com.revature.shms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAllByOrderByUserIdDesc();
    }

    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public User getUserByUserId(int userId){
        return userRepository.findByUserId(userId);
    }
}

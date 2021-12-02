package com.revature.shms.controllers;
import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import com.revature.shms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable String userId){
        return userRepository.findByUserID(Integer.parseInt(userId));
    }

    @PostMapping
    public User createNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }
}

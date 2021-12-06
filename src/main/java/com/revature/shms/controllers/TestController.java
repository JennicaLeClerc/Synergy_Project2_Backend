package com.revature.shms.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/u")
    public String testURLUser(){
        return "You are a user";
    }

    @RequestMapping("/m")
    public String testURLManager(){
        return "You are a manager";
    }

    @RequestMapping("/e")
    public String testURLEmployee(){
        return "You are an employee";
    }

}

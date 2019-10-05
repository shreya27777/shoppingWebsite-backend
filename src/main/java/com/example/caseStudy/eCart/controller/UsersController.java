package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersServiceImp usersServiceImp;

    @Autowired
    public UsersController(UsersServiceImp usersServiceImp) {
        this.usersServiceImp = usersServiceImp;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Users users) {
        return usersServiceImp.signUpUser(users);
    }

    @PostMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public String getUser(@RequestBody String email) {
        return usersServiceImp.getUser(email);
    }
}

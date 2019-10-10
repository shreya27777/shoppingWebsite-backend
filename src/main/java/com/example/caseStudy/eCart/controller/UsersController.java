package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersServiceImp usersServiceImp;

    @Autowired
    public UsersController(UsersServiceImp usersServiceImp) {
        this.usersServiceImp = usersServiceImp;
    }

    @PostMapping(path = "/signup")
    public Boolean addUser(@RequestBody Users users) {
        users.setRole("user");
        users.setActive(1);
        return usersServiceImp.signUpUser(users);
    }

//    @GetMapping(path = "/get/{category}", produces = "application/json")
//    @ResponseBody
//    public List<Items> getItemByCategory(@PathVariable("category") String category) {
//        return itemsService.getByCategory(category);
//    }

    @GetMapping(path = "/login", produces = "application/json")
    @ResponseBody
    public Boolean getUser() {
        return true;
    }

    @GetMapping(path = "/logout")
    public Boolean logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logout servlet : "+authentication);

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            request.getSession().invalidate();
        }
        return true;
    }
}



package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.service.ItemsService;
import com.example.caseStudy.eCart.service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private UsersServiceImp usersServiceImp;
    private ItemsService itemsService;

    @Autowired
    public AdminController(UsersServiceImp usersServiceImp, ItemsService itemsService) {
        this.usersServiceImp = usersServiceImp;
        this.itemsService = itemsService;
    }

    @PostMapping(path = "/addUser")
    public Users addUser(@RequestBody Users users) {
        users.setRole("user");
        users.setActive(1);
        return usersServiceImp.signUpUser(users);
    }

    @PostMapping(path = "/removeUser/{id}")
    public List<Users> removeUser(@PathVariable("id") Long id) {
        return usersServiceImp.removeUser(id);
    }

    @PostMapping(path = "/updateUser/{id}")
    @ResponseBody
    public List<Users> updateUser(@RequestBody Users users, @PathVariable("id") Long id) {
        return usersServiceImp.updateById(users, id);
    }

    @GetMapping(path = "/getAllUsers")
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersServiceImp.getAllUser();
    }


    @PostMapping("/addItem")
    public List<Items> addItems(@RequestBody Items items) {
        items.setActive(1);
        return itemsService.addItems(items);
    }


    @PostMapping(path = "/removeItem/{id}", produces = "application/json")
    public List<Items> removeItem(@PathVariable("id") Long id) {
        return itemsService.removeItem(id);
    }

    @PostMapping(path = "/updateItems/{id}")
    @ResponseBody
    public List<Items> updateUser(@RequestBody Items items, @PathVariable("id") Long id) {
        return itemsService.updateById(items, id);
    }

    @GetMapping(path = "/get", produces = "application/json")
    @ResponseBody
    public List<Items> getItem() {
        return itemsService.getItems();
    }
}

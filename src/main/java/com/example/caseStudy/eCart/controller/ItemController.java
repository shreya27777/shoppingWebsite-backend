package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import com.example.caseStudy.eCart.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/items")
public class ItemController {
    ItemsService itemsService;

    @Autowired
    public ItemController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping("/add")
    public Boolean addItem(@RequestBody Items items) {
        return itemsService.addItem(items);
    }

    //  @PMapping(path = "/remove",produces = "application/json")
//  @ResponseBody
//     public Boolean removeItem(Items items){
//        return itemsService.removeItem(items);
//  }
    @GetMapping(path = "/removeAll", produces = "application/json")
    @ResponseBody
    public Boolean removeAllItem() {
        return itemsService.removeAll();
    }

    @GetMapping(path = "/get", produces = "application/json")
    @ResponseBody
    public List<Items> getItem() {
        return itemsService.getItems();}

    @GetMapping(path = "/get/{category}", produces = "application/json")
    @ResponseBody
    public List<Items> getItemByCategory(@PathVariable("category") String category) {
        return itemsService.getByCategory(category);
    }
}

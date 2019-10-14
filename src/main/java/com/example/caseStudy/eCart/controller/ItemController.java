package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import com.example.caseStudy.eCart.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/get-popular/{category}", produces = "application/json")
    @ResponseBody
    public List<Items> getPopularItems(@PathVariable("category") String category) {
        return itemsService.getPopularItems(category);
    }


    @GetMapping(path = "/getByid/{id}")
    public Optional<Items> getFields(@PathVariable("id") Long id) {
        return itemsService.getById(id);
    }

//    @GetMapping(path = "/FilterByPrice/price/{price}/{price2}")
//    public ArrayList<Items> getByPrice(@PathVariable("price") Double price,
//                                       @PathVariable("price2") Double price2){
//        return (ArrayList<Items>) itemsService.getByPriceBetween(price,price2);
//    }

//    @GetMapping(path = "/FilterByPrice")
//    public ArrayList<Items> getByPrice(@RequestParam(value = "lower", required = false) Double price,
//                                       @RequestParam("upper") Double price2) {
//        return (ArrayList<Items>) itemsService.getByPriceBetween(price, price2);
//
//    }

    @GetMapping(path = "/get/{category}", produces = "application/json")
    public List<Items> getByCategoryAndPrice(@PathVariable("category") String category,
                                             @RequestParam(value = "price1", required = false) Double price1,
                                             @RequestParam(value = "price2", required = false) Double price2) {
        if (price1 == null && price2 == null) {
            return itemsService.getByCategory(category);
        }
        return itemsService.getByCategoryAndPrice(category, price1, price2);
    }

    @GetMapping(path = "search/{keyword}", produces = "application/json")
    public List<Items> getSearchResult(@PathVariable("keyword") String keyword) {
        return itemsService.searchResult(keyword);
    }

//    @GetMapping(path = "/get/{category}", produces = "application/json")
//    @ResponseBody
//    public List<Items> getItemByCategory(@PathVariable("category") String category) {
//        return itemsService.getByCategory(category);
//    }
}

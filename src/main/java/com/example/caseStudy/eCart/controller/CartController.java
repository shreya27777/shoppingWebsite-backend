package com.example.caseStudy.eCart.controller;

import com.example.caseStudy.eCart.model.Cart;
import com.example.caseStudy.eCart.model.Orders;
import com.example.caseStudy.eCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {
    public CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/addProduct/{productId}", produces = "application/json")
    @ResponseBody
    public List<Cart> addProduct(@PathVariable("productId") Long productId, Principal principal) {
        return cartService.addProduct(productId,principal);
    }
    @GetMapping(path = "/removeProduct/{productId}", produces = "application/json")
    @ResponseBody
    public List<Cart> removeProduct(@PathVariable("productId") Long productId, Principal principal) {
        return cartService.removeProduct(productId,principal);
    }

    @GetMapping(path = "/decreaseQuantity/{productId}", produces = "application/json")
    @ResponseBody
    public String decreaseQuantity(@PathVariable("productId") Long productId, Principal principal) {
        return cartService.decreaseQuantity(productId,principal);
    }

    @GetMapping(path = "/checkout", produces = "application/json")
    @ResponseBody
    public List<Orders> checkoutFromCart(Principal principal){
        return cartService.checkOut(principal);
    }
}

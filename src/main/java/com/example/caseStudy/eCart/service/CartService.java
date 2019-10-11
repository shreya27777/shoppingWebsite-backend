package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Cart;
import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.model.Orders;
import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.repository.CartRepository;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import com.example.caseStudy.eCart.repository.OrdersRepository;
import com.example.caseStudy.eCart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartRepository cartRepository;
    private UsersRepository usersRepository;
    private ItemsRepository itemsRepository;
    private OrdersRepository ordersRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UsersRepository usersRepository, ItemsRepository itemsRepository,
                       OrdersRepository ordersRepository) {
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
        this.itemsRepository = itemsRepository;
        this.ordersRepository = ordersRepository;
    }


    public List<Cart> addProduct(Long productId, Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Items items = itemsRepository.findById(productId).get();
        if (cartRepository.findByUsersAndItems(users, items).isPresent()) {
            Cart cart = cartRepository.findByUsersAndItems(users, items).get();
            cart.setQuantity(cart.getQuantity() + 1);

            cartRepository.saveAndFlush(cart);
        } else {
            Cart cart = new Cart();
            cart.setQuantity(1);
            cart.setUsers(users);
            cart.setItems(items);

            cartRepository.saveAndFlush(cart);
        }
        return cartRepository.findAllByUsers(users);
    }

    public List<Cart> removeProduct(Long productId, Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Items items = itemsRepository.findById(productId).get();
        Cart cart = cartRepository.findByUsersAndItems(users, items).get();
        cartRepository.delete(cart);
        return cartRepository.findAllByUsers(users);
    }

    public List<Cart> decreaseQuantity(Long productId, Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Items items = itemsRepository.findById(productId).get();
        Cart cart = cartRepository.findByUsersAndItems(users, items).get();
        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.saveAndFlush(cart);
        }
        return cartRepository.findAllByUsers(users);
    }

    public List<Cart> getItemsFromCart(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return cartRepository.findAllByUsers(users);
    }

    public Double getTotal(Principal principal){
        Users users = usersRepository.findByEmail(principal.getName()).get();
       List<Cart> cart = cartRepository.findAllByUsers(users);
      double sum=0;
        for (int i = 0; i < cart.size(); i++) {
            sum += (cart.get(i).getQuantity() * cart.get(i).getItems().getPrice());
        }
        return sum;
    }

    public List<Orders> checkOut(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        List<Cart> cartList = cartRepository.findAllByUsers(users);

        for (Cart cart : cartList) {
            Orders orders = new Orders();
            orders.setUserId(cart.getUsers().getUserId());
            orders.setQuantity(cart.getQuantity());
            orders.setPrice(cart.getItems().getPrice());
            orders.setItemName(cart.getItems().getName());
            orders.setDate(new Date());
            cartRepository.delete(cart);
            ordersRepository.saveAndFlush(orders);
        }
        return ordersRepository.findAllByUserId(users.getUserId());
    }

    public List<Orders> orderHistory(Principal principal){
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return ordersRepository.findAllByUserId(users.getUserId());
    }
}


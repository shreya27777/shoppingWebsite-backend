package com.example.caseStudy.eCart.repository;

import com.example.caseStudy.eCart.model.Cart;
import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUsers(Users users);

    Optional<Cart> findByUsersAndItems(Users users, Items items);
}

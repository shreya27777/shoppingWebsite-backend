package com.example.caseStudy.eCart.repository;

import com.example.caseStudy.eCart.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders>  findAllByUserIdOrderByDateDesc(Long userId);
}

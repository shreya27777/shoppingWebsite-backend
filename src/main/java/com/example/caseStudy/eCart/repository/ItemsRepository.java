package com.example.caseStudy.eCart.repository;

import com.example.caseStudy.eCart.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findAllByCategory(String category);

    List<Items> findAllByPriceBetween(Double startPrice, Double endPrice);

    List<Items> findAllByCategoryAndPriceBetween(String category, Double startPrice, Double endPrice);

}

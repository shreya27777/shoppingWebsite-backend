package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {
    public ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public boolean addItem(Items items) {
        itemsRepository.saveAndFlush(items);
        return true;
    }

    public boolean removeItem(Items items) {
        itemsRepository.delete(items);
        return true;
    }

    public boolean removeAll() {
        itemsRepository.deleteAll();
        return true;
    }

    public List<Items> getItems() {
        return itemsRepository.findAll();
    }

    public List<Items> getByCategory(String category) {
        return itemsRepository.findAllByCategory(category);
    }

    public Optional<Items> getById(Long id) {
        return itemsRepository.findById(id);
    }

    public List<Items> getByPriceBetween(Double startPrice, Double endPrice) {
        return itemsRepository.findAllByPriceBetween(startPrice, endPrice);
    }
    public List<Items> getByCategoryAndPrice(String category,Double startPrice, Double endPrice) {
        return itemsRepository.findAllByCategoryAndPriceBetween(category,startPrice, endPrice);
    }
}

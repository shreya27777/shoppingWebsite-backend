package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {
    private ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public boolean addItem(Items items) {
        itemsRepository.saveAndFlush(items);
        return true;
    }

    public List<Items> addItems(Items items) {
        itemsRepository.saveAndFlush(items);
        return itemsRepository.findAll();
    }

    public List<Items> removeItem(Long id) {
        Items items = itemsRepository.findById(id).get();
        itemsRepository.delete(items);
        return itemsRepository.findAll();
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

    public List<Items> getByCategoryAndPrice(String category, Double startPrice, Double endPrice) {
        return itemsRepository.findAllByCategoryAndPriceBetween(category, startPrice, endPrice);
    }

    public List<Items> updateById(Items items, Long id) {
        Items updatedItem = itemsRepository.findById(id).get();
        updatedItem.setCategory(items.getCategory());
        updatedItem.setDescription(items.getDescription());
        updatedItem.setName(items.getName());
        updatedItem.setImage(items.getImage());
        updatedItem.setDetails(items.getDetails());
        updatedItem.setPrice(items.getPrice());
        itemsRepository.saveAndFlush(updatedItem);
        return itemsRepository.findAll();
    }

    public List<Items> getPopularItems(String category) {
        return itemsRepository.findAllByCategoryAndPopular(category, true);
    }

    public List<Items> searchResult(String keyword) {
        List<Items> itemsList = itemsRepository.findAll();
        List<Items> foundList = new ArrayList<>();

        for (Items items : itemsList) {
            if (items.getName() != null && items.getDescription() != null && (items.getName().
                    toLowerCase().contains(keyword.toLowerCase())
                    || items.getDescription().toLowerCase().contains(keyword.toLowerCase())
                    || items.getCategory().toLowerCase().contains(keyword.toLowerCase()))) {
                foundList.add(items);
            }
        }
        return foundList;
    }
}

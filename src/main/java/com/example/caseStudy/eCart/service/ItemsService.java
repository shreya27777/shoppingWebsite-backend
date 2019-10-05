package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Items;
import com.example.caseStudy.eCart.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    public ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    public boolean addItem(Items items){
        itemsRepository.saveAndFlush(items);
        return true;
    }
    public boolean removeItem(Items items){
        itemsRepository.delete(items);
        return true;
    }
    public boolean removeAll(){
        itemsRepository.deleteAll();
        return true;
    }
     public List<Items> getItems(){
        return itemsRepository.findAll();
     }
    public List<Items> getByCategory(String category){
        return itemsRepository.findAllByCategory( category);
    }
}

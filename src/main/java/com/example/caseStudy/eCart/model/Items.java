package com.example.caseStudy.eCart.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Items implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Double price ;
    private String details ;
    private String image ;
    private String category ;
    private String description;
    @Column(nullable = false, columnDefinition = "int default '1'")
    private Integer active;

//    @Embedded
//    private ItemsDetails ItemsDetails;

    public Items(){

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

//    public ItemsDetails getItemsDetails() {
//        return ItemsDetails;
//    }
//
//    public void setItemsDetails(ItemsDetails itemsDetails) {
//        ItemsDetails = itemsDetails;
//    }
}

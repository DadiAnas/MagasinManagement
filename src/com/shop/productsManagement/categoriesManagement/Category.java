package com.shop.productsManagement.categoriesManagement;

import java.util.List;
import com.shop.productsManagement.Product;


public class Category {
    private long categoryCode;
    private String categoryName;
    private List<Product> products;


    public Category(long categoryCode, String categoryName) {
        super();
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }


    public long getCategoryCode() {
        return categoryCode;
    }


    public void setCategoryCode(long categoryCode) {
        this.categoryCode = categoryCode;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return categoryName;
    }

}

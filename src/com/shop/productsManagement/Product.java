package com.shop.productsManagement;

import com.shop.productsManagement.categoriesManagement.Category;

public class Product {
    private long productCode;
    private String designation;
    private double purchasePrice;
    private double salePrice;
    Category category;
    private int stockquantity;




    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", designation='" + designation + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", category=" + category +
                ", stockquantity=" + stockquantity +
                '}';
    }

    //Constructors
    public Product(long productCode, String designation, double purchasePrice, double salePrice,Category category) {
        this.productCode = productCode;
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.category = category;
    }

    public Product(long productCode, String designation, double purchasePrice, double salePrice, int stockquantity) {
        this.productCode = productCode;
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.stockquantity = stockquantity;
    }
    public Product(long productCode, String designation, double purchasePrice, double salePrice,Category category,int stockquantity) {
        this.productCode = productCode;
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.stockquantity = stockquantity;
    }

    public Product(String designation, double purchasePrice, double salePrice,Category category,int stockquantity) {
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.stockquantity = stockquantity;
    }

    public Product(String designation, double purchasePrice, double salePrice,Category category) {
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.category = category;
    }
    public Product(long productCode, String designation, double purchasePrice, double salePrice) {
        this.productCode = productCode;
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
    }
    public Product(String designation, double purchasePrice, double salePrice) {
        this.designation = designation;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
    }
    //Getters and setters


    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(int stockquantity) {
        this.stockquantity = stockquantity;
    }
}

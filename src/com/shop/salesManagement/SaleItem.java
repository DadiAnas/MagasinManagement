package com.shop.salesManagement;

import com.shop.productsManagement.Product;

public class SaleItem {
    private long saleItemCode;
    private Product product;
    private long productCode=0;
    private int quantite;
    private double subTotal;
    private double salePrice;
    private int number;
    private Sale sale = null;
    private int qteret;

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    private String designation;

    public SaleItem(long saleItemCode, int number, Product product, int quantite, double salePrice, int qteret) {
        this.saleItemCode = saleItemCode;
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.salePrice = salePrice;
        this.qteret = qteret;

        setSubTotal();
    }

    public SaleItem(int number, Product product, int quantite, double salePrice, int qteret) {
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.salePrice = salePrice;
        this.qteret = qteret;

        setSubTotal();
    }

    public SaleItem(int number, Product product,String designation, int quantite, double salePrice, int qteret) {
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.salePrice = salePrice;
        this.qteret = qteret;
        this.designation = designation;

        setSubTotal();
    }
    public SaleItem(int number,long productCode,String designation, int quantite, double salePrice, int qteret) {
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.salePrice = salePrice;
        this.qteret = qteret;
        this.designation = designation;
        this.productCode = productCode;

        setSubTotal();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private void setSubTotal() {
        this.subTotal = salePrice * quantite;
    }


    public long getSaleItemCode() {
        return saleItemCode;
    }


    public void setSaleItemCode(long saleItemCode) {
        this.saleItemCode = saleItemCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }


    public int transactionItemsDao() {
        return number;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQteret() {
        return qteret;
    }

    public void setQteret(int qteret) {
        this.qteret = qteret;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        setSubTotal();

    }

    @Override
    public String toString() {
        return getProduct().getDesignation() + " " + getQuantite();
    }


}

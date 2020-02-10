package com.shop.purchasesManagement;

import com.shop.productsManagement.Product;

public class PurchaseItem {
    private long purchaseItemCode;
    private Product product;
    private int quantite;
    private double subTotal;
    private double purchasePrice;
    private int number;
    private String designation;
    private Purchase purchase = null;
    private int qteret;

    public PurchaseItem(long purchaseItemCode, int number, Product product, int quantite, double subTotal, double purchasePrice, int qteret) {
        this.purchaseItemCode = purchaseItemCode;
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.qteret = qteret;
        this.subTotal = subTotal;
    }

    public PurchaseItem( int number, Product product, int quantite, double subTotal, double purchasePrice, int qteret) {
        this.number = number;
        this.quantite = quantite;
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.qteret = qteret;
        this.subTotal = subTotal;
    }
    public PurchaseItem(long purchaseItemCode, int number,Product product,String designation, int quantite, double subTotal, double purchasePrice, int qteret) {
        this.number = number;
        this.quantite = quantite;
        this.designation = designation;
        this.purchasePrice = purchasePrice;
        this.qteret = qteret;
        this.subTotal = subTotal;
        this.product = product;
        this.purchaseItemCode = purchaseItemCode;
    }

    private void setSubTotal() {
        this.subTotal = purchasePrice * quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String desination) {
        this.designation = desination;
    }

    public long getPurchaseItemCode() {
        return purchaseItemCode;
    }

    public void setPurchaseItemCode(long purchaseItemCode) {
        this.purchaseItemCode = purchaseItemCode;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
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
        return super.toString();
    }


}

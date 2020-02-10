package com.shop.transactionsManagement;

import java.util.List;

import com.shop.productsManagement.Product;
import com.shop.salesManagement.paymentsManagement.Payment;

public class TransactionItem {
private long transactionItemCode;
private Product product;
private int quantite;
private double subTotal;
private double transactionPrice;
private int number;
private Transaction transaction=null;
private int qteret;

private List<TransactionItem> saleItems=null;
private List<Payment> payments=null;

private String totalPayments="---";
private String restPayments="---";


public TransactionItem(long transactionItemCode,int number,Product product, int quantite, double subTotal,double transactionPrice,int qteret) {
	this.transactionItemCode=transactionItemCode;
	this.number = number;
	this.quantite = quantite;
	this.product=product;
	this.transactionPrice=transactionPrice;
	this.qteret=qteret;
	setSubTotal();
}





public List<TransactionItem> getSaleItems() {
	return saleItems;
}


public void setSaleItems(List<TransactionItem> saleItems) {
	this.saleItems = saleItems;
}


public List<Payment> getPayments() {
	return payments;
}


public void setPayments(List<Payment> payments) {
	this.payments = payments;
}


public String getTotalPayments() {
	return totalPayments;
}


public void setTotalPayments(String totalPayments) {
	this.totalPayments = totalPayments;
}


public String getRestPayments() {
	return restPayments;
}


public void setRestPayments(String restPayments) {
	this.restPayments = restPayments;
}


public long getTransactionItemCode() {
	return transactionItemCode;
}


public void setTransactionItemCode(long transactionItemCode) {
	this.transactionItemCode = transactionItemCode;
}


public void setQuantite(int quantite) {
	this.quantite = quantite;
	setSubTotal();
	
}
private void setSubTotal() {
	this.subTotal = transactionPrice*quantite;
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





public int getNumber() {
	return number;
}



public void setNumber(int number) {
	this.number = number;
}










public double getTransactionPrice() {
	return transactionPrice;
}


public void setTransactionPrice(double transactionPrice) {
	this.transactionPrice = transactionPrice;
}



public Transaction getTransaction() {
	return transaction;
}


public void setTransaction(Transaction transaction) {
	this.transaction = transaction;
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





}

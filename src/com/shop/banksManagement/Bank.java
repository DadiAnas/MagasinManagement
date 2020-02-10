package com.shop.banksManagement;

import java.util.List;

import com.shop.productsManagement.Product;




public class Bank {
	private long bankCode;
	private String bankName;


public Bank(long bankCode, String bankName) {
	super();
	this.bankCode = bankCode;
	this.bankName = bankName;
}


public long getBankCode() {
	return bankCode;
}


public void setBankCode(long bankCode) {
	this.bankCode = bankCode;
}


public String getBankName() {
	return bankName;
}


public void setBankName(String bankName) {
	this.bankName = bankName;
}




@Override
public String toString() {
	return bankName;
}

}

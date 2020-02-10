package com.shop.salesManagement;

import java.time.LocalDate;
import java.util.List;


import com.shop.personsManagement.Person;
import com.shop.salesManagement.paymentsManagement.Payment;
import com.shop.transactionsManagement.Transaction;
import com.shop.statesManagement.State;

public class Sale extends Transaction {
	
	private List<SaleItem> saleItems=null;
	private List<Payment> payments=null;
	
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}
	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Sale(long transactionCode, State state, double total, LocalDate date) {
		super(transactionCode, state, total, date);
	}

public Sale(Person person) {
		super(person);
		// TODO Auto-generated constructor stub
	}

public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}

@Override
public String toString() {
	return "Vente N� "+getTransactionCode()+ ", du "+getDateFr()+ " - "+getPerson().getFirstName()+ " "+getPerson().getLastName();
}


}
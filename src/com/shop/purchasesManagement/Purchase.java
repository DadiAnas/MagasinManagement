package com.shop.purchasesManagement;

import java.time.LocalDate;
import java.util.List;


import com.shop.personsManagement.Person;
import com.shop.salesManagement.paymentsManagement.Payment;
import com.shop.transactionsManagement.Transaction;
import com.shop.statesManagement.State;

public class Purchase extends Transaction {
	
	private List<PurchaseItem> purchaseItems=null;
	private List<Payment> payments=null;
	
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Purchase(long transactionCode, State state, double total, LocalDate date) {
		super(transactionCode, state, total, date);
	}

public Purchase(Person person) {
		super(person);
		// TODO Auto-generated constructor stub
	}

public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

@Override
public String toString() {
	return "Achat N� "+getTransactionCode()+ ", du "+getDateFr()+ " - "+getPerson().getFirstName()+ " "+getPerson().getLastName();
}


}
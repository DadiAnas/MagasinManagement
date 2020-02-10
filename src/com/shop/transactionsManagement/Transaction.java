package com.shop.transactionsManagement;

import java.time.LocalDate;

import com.shop.personsManagement.Person;
import com.shop.statesManagement.State;

public abstract class Transaction {
	private long transactionCode=0;
	private double total=0;
	private LocalDate date;
	private State state;
	
	private Person person;
	

	private String totalPayments="---";
	private String restPayments="---";

	public String getDateFr(){
		return date.getDayOfMonth()+ "/"+date.getMonthValue()+"/"+date.getYear();
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
		public Transaction(long transactionCode,State state, double total, LocalDate date) {

		this.transactionCode = transactionCode;
		this.total = total;
		this.date = date;
		this.state=state;
	}

	public Transaction(Person person) {

		this.person = person;
		this.date = LocalDate.now();
		this.state=new State(1, "");
		this.transactionCode=0;
	}
	public long getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(long transactionCode) {
		this.transactionCode = transactionCode;
	}
	public Transaction() {
		this.date = LocalDate.now();
	}


}

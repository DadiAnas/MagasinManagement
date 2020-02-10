package com.shop.salesManagement.paymentsManagement;

import java.time.LocalDate;

import com.shop.banksManagement.Bank;
import com.shop.transactionsManagement.Transaction;

public class Payment {
	private long paymentCode;
	private Transaction transaction;
	private int number;
	private double amount;
	private LocalDate date;
    private String bankName;
    private String stateName;
	private String type;
	private String state;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private String chequeNumber;
	private String firstNameCheque;
	private String lastNameCheque;
	String daate;

	public String getDaate() {
		return daate;
	}

	public void setDaate(String daate) {
		this.daate = daate;
	}

	private Bank bank;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public long getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(long paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDateFr() {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getDueDateFr() {
		return dueDate.getDayOfMonth() + "/" + dueDate.getMonthValue() + "/" + dueDate.getYear();
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getDetail() {
		if (type.equals("CHEQUE"))
			return chequeNumber + " - " + getDueDateFr();
		return "";
	}

	public Payment(long paymentCode, Transaction transaction, int number, double amount, LocalDate date, String type,
			String state) {
		this.paymentCode = paymentCode;
		this.amount = amount;
		this.number = number;
		this.transaction = transaction;
		this.type = type;
		this.date = date;
		this.state = state;
		this.stateName = state;
		this.daate = date.toString();
		this.bankName = "BANKPOP";

	}
    public Payment(long paymentCode, int number, double amount, LocalDate date, String type,String state) {
        this.paymentCode = paymentCode;
        this.amount = amount;
        this.number = number;
        this.type = type;
        this.date = date;
        this.state = state;
        this.stateName = state;
		this.daate = date.toString();
		this.bankName = "BANKPOP";

    }

	public String getChequeName(){
		if(lastNameCheque==null && firstNameCheque==null) return "";
		
		return firstNameCheque+ "  "+lastNameCheque;
	}
	

	public String getFirstNameCheque() {
		return firstNameCheque;
	}

	public void setFirstNameCheque(String firstNameCheque) {
		this.firstNameCheque = firstNameCheque;
	}

	public String getLastNameCheque() {
		return lastNameCheque;
	}

	public void setLastNameCheque(String lastNameCheque) {
		this.lastNameCheque = lastNameCheque;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	

}

package com.shop.purchasesManagement;

import java.util.List;
import java.time.LocalDate;
import java.util.Collection;

import com.shop.personsManagement.Person;

public interface PurchaseDao<T> {
	public List<T> getAll(LocalDate date1,LocalDate date2, Person customer);
	public long add(T purchase);
	public T getOne(long code);
	public void savePurchase(T purchase);
	public void saveStatePurchase(T purchase);
	public void savePaymentsPurchase(T purchase);
	public Collection<T> getAll(Person person);
	public double getTotalPayments(long purchaseCode);
	public int delete(long purchaseCode);
	
	

}

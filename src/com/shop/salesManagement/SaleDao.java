package com.shop.salesManagement;

import java.util.List;
import java.time.LocalDate;
import java.util.Collection;

import com.shop.personsManagement.Person;

public interface SaleDao<T> {
	public List<T> getAll(LocalDate date1,LocalDate date2, Person customer);
	public long add(T sale);
	public T getOne(long code);
	public void saveSale(T purchase);
	public void saveStateSale(T purchase);
	public void savePaymentsSale(T purchase);
	public Collection<T> getAll(Person person);
	public double getTotalPayments(long purchaseCode);
	public int delete(long purchaseCode);
	
	

}

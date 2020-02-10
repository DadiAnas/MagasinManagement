package com.shop.salesManagement.paymentsManagement;

import java.util.List;


import com.shop.salesManagement.Sale;



public interface PaymentDao<T> {
	public List<T> getAll();
	public List<T> getAll(Sale sale);
	public long add(T payment);
	public T getOne(long code);
	public T save(T payment);
	public int delete(Payment payment);
	public void updateNumber(Payment payment);
	

}

package com.shop.salesManagement;

import java.util.List;

public interface SaleItemDao<T> {
	public List<T> getAll(long transactionCode);
	public void add(T lc);
	public T getOne(long code);
	public void updateSaleItem(SaleItem lc);
	public List<SaleItem> getSaleItemsProduct(long codeProduit);
	
	
}

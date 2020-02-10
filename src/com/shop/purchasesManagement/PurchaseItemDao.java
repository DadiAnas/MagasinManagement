package com.shop.purchasesManagement;

import java.util.List;
import java.util.Set;

import com.shop.productsManagement.Product;

public interface PurchaseItemDao<T> {
	public List<T> getAll(long transactionCode);
	public List<T> getByDesignation(long transactionCode,String designation);
	public void add(T lc);
	public T getOne(long code);
	public void updatePurchaseItem(PurchaseItem lc);
	public List<PurchaseItem> getPurchaseItemsProduct(long codeProduit);
	public Set<Product> getProductsByPurchasePrice();
	
	
	
}

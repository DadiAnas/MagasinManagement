package com.shop.productsManagement;

import java.util.List;

public interface ProductDao <T>{

    public List<T> getAll();
    public List<Product> getAll(String key);
    public List<T> getAllWithQuantity();
    public T add(T product);
    public T save(T product);
    public boolean delete(long code);
    public T getOne(long code);
    public List<T> getByCategory(com.shop.productsManagement.categoriesManagement.Category category);
    public int getQuantity(long code);

}
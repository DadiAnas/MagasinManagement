package com.shop.productsManagement.categoriesManagement;

import java.util.List;

public interface CategoryDao<T> {
    public Category getOne(long categoryCode);
    public List<Category> getAll(String key);
    public List<Category> getAll();
    public void add(Category category);
    public Category save(Category category);
    public boolean delete(long categoryCode);
    public Category getByName(String name);
}

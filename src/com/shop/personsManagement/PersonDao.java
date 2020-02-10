package com.shop.personsManagement;

import java.util.Collection;
public interface PersonDao <T>{
public Collection<T> getByType(String type);
public Collection<T> getByCriteria(String criteria);
public Collection<T> getAll();
public T getOne(long code);
public T add(T obj);
public T save(T obj);
public int delete(Person person);
public Collection<String> getTypes();

}

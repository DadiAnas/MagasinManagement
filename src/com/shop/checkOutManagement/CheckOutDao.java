package com.shop.checkOutManagement;

import java.util.Collection;
import java.util.Date;

import com.shop.personsManagement.Person;

public interface CheckOutDao<T> {
public void add(T obj);
public CheckOut addCheckOut(CheckOut checkOut);

public Collection<T> getAll(CheckOut checkOut,String type);
public Collection<T> getAll(CheckOut checkOut,int year);

public double getSolde(CheckOut checkOut,int year);

public Collection<T> getAll(CheckOut checkOut,String type,Date date);
public Collection<T> getAll(CheckOut checkOut,String type,Date date1,Date date2);
public CheckOut getCheckOut(Person owner);

}

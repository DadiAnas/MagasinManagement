package com.shop.usersManagement;

import java.util.List;

public interface UserDao<T> {

    public T getOne(long code);
    public List<T> getAll();
    public List<T> getAllWithQuantity();
    public T add(T user);
    public T save(T user);
    public boolean delete(long code);

}

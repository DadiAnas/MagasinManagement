package com.shop.statesManagement;

import java.util.Collection;

public interface StateDao<T> {
public T getOne(int code);

public Collection<T> getAll();
}

package com.shop.banksManagement;

import java.util.Collection;
public interface BankDao { public Collection<Bank> getAll(String name);
public Collection<Bank> getAll();
public void add(Bank bank);
public Bank save(Bank bank);
public boolean delete(long bankCode);

public Bank getByName(String name);
public Bank getByCode(long code) ;

}

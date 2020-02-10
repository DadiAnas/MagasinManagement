package com.shop.banksManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.shop.Connexion;



public class BankDaoImpl implements BankDao {
	Connection connection = null;

	public BankDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();

	}

	@Override
	public Collection<Bank> getAll() {
		List<Bank> banks = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select * from bank");
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					Bank bank = new Bank(rs.getLong("bankCode"), rs.getString("name"));
					banks.add(bank);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return banks;
	}

	@Override
	public Collection<Bank> getAll(String name) {
		List<Bank> banks = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select * from bank where Name like ?");
			prst.setString(1,"%"+name+"%");
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					Bank bank = new Bank(rs.getLong("bankCode"), rs.getString("name"));
					banks.add(bank);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return banks;
	}

	@Override
	public void add(Bank bank) {
		String sql="insert into bank(name) values(?)";
		try {
			PreparedStatement prst=connection.prepareStatement(sql);
			prst.setString(1, bank.getBankName());
			prst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bank getByName(String name) {
		Bank bank=null;
		PreparedStatement prst;
		String sql=" select * from bank where name=?";
		ResultSet rs=null;
		try {
			
			prst = connection.prepareStatement(sql);
			prst.setString(1, name);
			
			rs=prst.executeQuery();
			if(rs!=null){
				if(rs.next()){
			 bank=new Bank(rs.getLong("bankCode"), rs.getString("name"));
			System.out.println("ok");
				}
			
}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bank;
		
	
	}

	
	@Override
	public Bank getByCode(long code) {
		Bank bank=null;
		PreparedStatement prst;
		String sql=" select * from bank where bankCode=?";
		ResultSet rs=null;
		try {
			
			prst = connection.prepareStatement(sql);
			prst.setLong(1, code);
			rs=prst.executeQuery();
			if(rs!=null){
				if(rs.next()){
			 bank=new Bank(rs.getLong("bankCode"), rs.getString("name"));
				}
			
}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bank;
		
	
	}

	@Override
	public Bank save(Bank bank) {
			String sql="update bank set name=? where bankCode=?";
			try {
				PreparedStatement prst=connection.prepareStatement(sql);
				prst.setString(1, bank.getBankName());
				prst.setLong(2, bank.getBankCode());
				prst.executeUpdate();
									
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return bank;
	
	}

	@Override
	public boolean delete(long bankCode) {
		String sql="delete from bank where  bankCode=?";
		try {
			PreparedStatement prst=connection.prepareStatement(sql);
			
			prst.setLong(1, bankCode);
				prst.execute();
				return true;
								
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

}

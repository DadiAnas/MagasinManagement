package com.shop.salesManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.shop.Connexion;

import com.shop.personsManagement.Person;
import com.shop.personsManagement.PersonDao;
import com.shop.personsManagement.PersonDaoImpl;
import com.shop.salesManagement.paymentsManagement.Payment;
import com.shop.salesManagement.paymentsManagement.PaymentDao;
import com.shop.salesManagement.paymentsManagement.PaymentDaoImpl;
import com.shop.statesManagement.State;
import com.shop.statesManagement.StateDao;
import com.shop.statesManagement.StateDaoImpl;

public class SaleDaoImpl implements SaleDao<Sale> {
	Connection connection = null;
	PersonDao<Person> customerDao = new PersonDaoImpl();
	StateDao<State> stateDao = new StateDaoImpl();

	public SaleDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();
	}

	@Override
	public List<Sale> getAll(LocalDate date1, LocalDate date2, Person customer) {
		List<Sale> sales = new ArrayList();
		PreparedStatement prst;
		ResultSet rs = null;
		Date sqlDate1 = null;
		Date sqlDate2 = null;
		if (date1 != null)
			sqlDate1 = Date.valueOf(date1);
		if (date2 != null)
			sqlDate2 = Date.valueOf(date2);
		;
		try {
			String sql = "";
			// sql="select * from sale order by saleCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from sale where 1";
			if (date1 != null) {
				if (date2 == null) {
					sql = sql + " and date =?";
				} else {
					sql = sql + " and date BETWEEN ? and ?";
				}

			}

			if(customer!=null){
				sql = sql + " and customerCode =?";
			}
			prst = connection.prepareStatement(sql);

			if (date1 != null) {
				if (date2 == null) {
					prst.setDate(++index, sqlDate1);
				} else {
					prst.setDate(++index, sqlDate1);
					prst.setDate(++index, sqlDate2);
				}

			}

			if(customer!=null){
				prst.setLong(++index, customer.getPersonCode());
			}
			rs = prst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
					Sale sale = new Sale(rs.getLong("saleCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
					Person person = customerDao.getOne(rs.getLong("customerCode"));

					sale.setPerson(person);
					sales.add(sale);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sales;
	}



	@Override
	public long add(Sale sale) {
		List<SaleItem> saleItems= new ArrayList<>();
		SaleItemDao<SaleItem> saleItemDao = new SaleItemDaoImpl();
		PaymentDao<Payment> regdao = new PaymentDaoImpl();

		String sql = "insert into sale(customerCode,stateCode,total,date) values(?,?,?,?)";
		long key = 0;
		try {
			PreparedStatement prst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			prst.setDouble(1, sale.getPerson().getPersonCode());

			prst.setInt(2, sale.getState().getStateCode());
			prst.setDouble(3, sale.getTotal());
			prst.setDate(4, Date.valueOf(sale.getDate()));
			prst.executeUpdate();
			ResultSet rs = prst.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				sale.setTransactionCode(key);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return key;
	}

	@Override
	public Sale getOne(long code) {
		Sale sale = null;
		PreparedStatement prst;
		ResultSet rs = null;
		try {

			int index = 0;
			String sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from sale where 1";
					sql = sql + " and saleCode =?";
		
			prst = connection.prepareStatement(sql);
			prst.setLong(1, code);
			
			rs = prst.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
			
					sale = new Sale(rs.getLong("saleCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
			
				//	sale.setDatefr(rs.getString("datefr"));
					Person person = customerDao.getOne(rs.getLong("customerCode"));
					sale.setPerson(person);
					
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sale;

	}

	@Override
	public void saveSale(Sale sale) {
		// TODO Auto-generated method stub
		SaleItemDao<SaleItem> saleItemDao = new SaleItemDaoImpl();
		PaymentDao<Payment> regdao = new PaymentDaoImpl();

		String sql = "delete from saleItem where saleCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, sale.getTransactionCode());
			prst.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		
//		 sql = "delete from reglement where saleCode=?";
//		try {
//			PreparedStatement prst = connection.prepareStatement(sql);
//
//			prst.setLong(1, sale.getSaleCode());
//			prst.executeUpdate();
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}

		 sql = "update sale set stateCode=?,total=?,date=? where saleCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, sale.getState().getStateCode());
			prst.setDouble(2, sale.getTotal());
			prst.setDate(3, Date.valueOf(sale.getDate()));
			prst.setLong(4, sale.getTransactionCode());
			
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		List<SaleItem> saleItems = sale.getSaleItems();
		for (SaleItem saleItem : saleItems) {
			saleItem.setSale(sale);
			saleItemDao.add(saleItem);
		}
		System.out.println("save termin�!!!");

	}

	@Override
	public void saveStateSale(Sale sale) {
		// TODO Auto-generated method stub
		// Products.put(produit.getCode(), produit);
	//	String sql = "insert into sale(codePerson,stateCode,total,date) values(?,?,?,?)";

		String sql = "update sale set stateCode=? where saleCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, sale.getState().getStateCode());
			
			prst.setLong(2, sale.getTransactionCode());
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("save termin�!!!");

		
	}

	@Override
	public void savePaymentsSale(Sale sale) {
		System.out.println("save termin�!!!");

	}

	@Override
	public Collection<Sale> getAll(Person person) {
		List<Sale> sales = new ArrayList();
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			String sql = "";
			// sql="select * from sale order by saleCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from sale where 1";
					sql = sql + " and customerCode =?";
		
			prst = connection.prepareStatement(sql);
			prst.setLong(1, person.getPersonCode());
			
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
			
					Sale sale = new Sale(rs.getLong("saleCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
			
				//	sale.setDatefr(rs.getString("datefr"));

					sale.setPerson(person);
					sales.add(sale);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sales;

	}

	@Override
	public double getTotalPayments(long saleCode) {

		PreparedStatement prst;
		String sql="select SUM(amount) as total  from payment"
				+ " where payment.saleCode=?";
		ResultSet rs=null;
		try {
			
			prst = connection.prepareStatement(sql);
			prst.setLong(1, saleCode);
			rs=prst.executeQuery();
		//	 rs=prst.getResultSet();
			if(rs!=null){
				if(rs.next()){
						return rs.getDouble("total");
				}
			
}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int delete(long saleCode) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (saleCode == 0)
			return 0;
		String sql="";
		PreparedStatement prst;
		try {
			sql = "update  sale set stateCode=? where saleCode=?";
			prst = connection.prepareStatement(sql);
			prst.setLong(1, 4);
			prst.setLong(2, saleCode);
			prst.execute();
			return 1;
			
		}catch(Exception exp){
			System.out.println(exp);
			
		}

		return 0;

	}

	
}

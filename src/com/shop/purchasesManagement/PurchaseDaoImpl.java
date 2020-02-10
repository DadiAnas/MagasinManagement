package com.shop.purchasesManagement;

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

import com.shop.salesManagement.paymentsManagement.Payment;
import com.shop.salesManagement.paymentsManagement.PaymentDao;
import com.shop.salesManagement.paymentsManagement.PaymentDaoImpl;
import com.shop.personsManagement.Person;
import com.shop.personsManagement.PersonDao;
import com.shop.personsManagement.PersonDaoImpl;
import com.shop.statesManagement.State;
import com.shop.statesManagement.StateDao;
import com.shop.statesManagement.StateDaoImpl;

public class PurchaseDaoImpl implements PurchaseDao<Purchase> {
	Connection connection = null;
	PersonDao<Person> supplierDao = new PersonDaoImpl();
	StateDao<State> stateDao = new StateDaoImpl();

	public PurchaseDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();
	}

	@Override
	public List<Purchase> getAll(LocalDate date1, LocalDate date2, Person supplier) {
		List<Purchase> purchases = new ArrayList();
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
			// sql="select * from purchase order by purchaseCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from purchase where 1";
			if (date1 != null) {
				if (date2 == null) {
					sql = sql + " and date =?";
				} else {
					sql = sql + " and date BETWEEN ? and ?";
				}

			}

			if(supplier!=null){
				sql = sql + " and supplierCode =?";
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

			if(supplier!=null){
				prst.setLong(++index, supplier.getPersonCode());
			}
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
					// System.out.println("date
					// frrrrrrrrrrrrr:"+rs.getString("datefr"));

					Purchase purchase = new Purchase(rs.getLong("purchaseCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
					Person person = supplierDao.getOne(rs.getLong("supplierCode"));
					//purchase.setDatefr(rs.getString("datefr"));

					purchase.setPerson(person);
					purchases.add(purchase);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchases;
	}

	@Override
	public long add(Purchase purchase) {

		PurchaseItemDao<PurchaseItem> purchaseItemDao = new PurchaseItemDaoImpl();
		PaymentDao<Payment> regdao = new PaymentDaoImpl();

		String sql = "insert into purchase(supplierCode,stateCode,total,date) values(?,?,?,?)";
		long key = 0;
		try {
			PreparedStatement prst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			prst.setDouble(1, purchase.getPerson().getPersonCode());

			prst.setInt(2, purchase.getState().getStateCode());
			prst.setDouble(3, purchase.getTotal());
			//LocalDate datel = LocalDate.now();
			
			
		
			//Date datesql = Date.valueOf(purchase.getDate().toString());
			prst.setDate(4, Date.valueOf(purchase.getDate()));
			prst.executeUpdate();
			ResultSet rs = prst.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				purchase.setTransactionCode(key);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 0) {
			System.out.println("probl�me d'insertion");
		} else {

			List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();
			for (PurchaseItem purchaseItem : purchaseItems) {
				purchaseItem.setPurchase(purchase);
				purchaseItemDao.add(purchaseItem);
			}

			// List<Payment> regs=purchase.getPayments();
			// for(Payment rg:regs){
			// rg.setPurchase(purchase);
			// regdao.add(rg);
			// }

			System.out.println("ajout termin�!!!");

		}
		return key;
	}

	@Override
	public Purchase getOne(long code) {
		Purchase purchase = null;
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			String sql = "";
			// sql="select * from purchase order by purchaseCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from purchase where 1";
					sql = sql + " and purchaseCode =?";
		
			prst = connection.prepareStatement(sql);
			prst.setLong(1, code);
			
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				if (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
			
					purchase = new Purchase(rs.getLong("purchaseCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
			
				//	purchase.setDatefr(rs.getString("datefr"));
					Person person = supplierDao.getOne(rs.getLong("supplierCode"));
					purchase.setPerson(person);
					
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchase;

	}

	@Override
	public void savePurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		// Products.put(produit.getCode(), produit);
	//	String sql = "insert into purchase(codePerson,stateCode,total,date) values(?,?,?,?)";
		PurchaseItemDao<PurchaseItem> purchaseItemDao = new PurchaseItemDaoImpl();
		PaymentDao<Payment> regdao = new PaymentDaoImpl();

		String sql = "delete from purchaseItem where purchaseCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, purchase.getTransactionCode());
			prst.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}



		 sql = "update purchase set stateCode=?,total=?,date=? where purchaseCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, purchase.getState().getStateCode());
			prst.setDouble(2, purchase.getTotal());
			prst.setDate(3, Date.valueOf(purchase.getDate()));
			//Date.valueOf(purchase.getDate())
			
			prst.setLong(4, purchase.getTransactionCode());
			
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();
		for (PurchaseItem purchaseItem : purchaseItems) {
			purchaseItem.setPurchase(purchase);
			purchaseItemDao.add(purchaseItem);
		}
		System.out.println("save termin�!!!");

	}

	@Override
	public void saveStatePurchase(Purchase purchase) {
		// TODO Auto-generated method stub
		// Products.put(produit.getCode(), produit);
	//	String sql = "insert into purchase(codePerson,stateCode,total,date) values(?,?,?,?)";

		String sql = "update purchase set stateCode=? where purchaseCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, purchase.getState().getStateCode());
			
			prst.setLong(2, purchase.getTransactionCode());
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("save termin�!!!");

		
	}

	@Override
	public void savePaymentsPurchase(Purchase purchase) {
		System.out.println("save termin�!!!");

	}

	@Override
	public Collection<Purchase> getAll(Person person) {
		List<Purchase> purchases = new ArrayList();
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			String sql = "";
			// sql="select * from purchase order by purchaseCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from purchase where 1";
					sql = sql + " and supplierCode =?";
		
			prst = connection.prepareStatement(sql);
			prst.setLong(1, person.getPersonCode());
			
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					State state = stateDao.getOne(rs.getInt("stateCode"));
			
					Purchase purchase = new Purchase(rs.getLong("purchaseCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
			
				//	purchase.setDatefr(rs.getString("datefr"));

					purchase.setPerson(person);
					purchases.add(purchase);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchases;

	}

	@Override
	public double getTotalPayments(long purchaseCode) {

		PreparedStatement prst;
		String sql="select SUM(amount) as total  from payment"
				+ " where payment.purchaseCode=?";
		ResultSet rs=null;
		try {
			
			prst = connection.prepareStatement(sql);
			prst.setLong(1, purchaseCode);
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
	public int delete(long purchaseCode) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (purchaseCode == 0)
			return 0;
		String sql="";
		PreparedStatement prst;
		try {
			sql = "update  purchase set stateCode=? where purchaseCode=?";
			prst = connection.prepareStatement(sql);
			prst.setLong(1, 4);
			prst.setLong(2, purchaseCode);
			prst.execute();
			return 1;
			
		}catch(Exception exp){
			System.out.println(exp);
			
		}

		return 0;

	}

	
}

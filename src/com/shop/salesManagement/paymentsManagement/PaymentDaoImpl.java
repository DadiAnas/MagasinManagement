package com.shop.salesManagement.paymentsManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.shop.Connexion;
import com.shop.banksManagement.BankDao;
import com.shop.banksManagement.BankDaoImpl;
import com.shop.salesManagement.Sale;
import com.shop.salesManagement.SaleDao;
import com.shop.salesManagement.SaleDaoImpl;

public class PaymentDaoImpl implements PaymentDao<Payment> {
	Connection connection = null;
	SaleDao<Sale> salesDao = new SaleDaoImpl();
	BankDao bankDao=new BankDaoImpl();

	public PaymentDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();
	}

	@Override
	public List<Payment> getAll(Sale sale) {
		List<Payment> payments = new ArrayList();
		// Sale sale=salesDao.getOne(saleCode);
		PreparedStatement prst;
		ResultSet rs = null;

		try {
			String sql = "";
			// sql="select * from sale order by saleCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr,"
					+ "dueDate  from payment where saleCode=?";

			prst = connection.prepareStatement(sql);

			prst.setLong(1, sale.getTransactionCode());

			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {

					Payment payment = new Payment(rs.getLong("paymentCode"), sale, rs.getInt("number"),
							rs.getDouble("amount"), rs.getDate("date").toLocalDate(), rs.getString("type"), rs.getString("state"));
					payment.setDueDate(rs.getDate("dueDate").toLocalDate());
					payment.setChequeNumber(rs.getString("chequeNumber"));
					payment.setFirstNameCheque(rs.getString("nom"));
					payment.setLastNameCheque(rs.getString("prenom"));
					
					payment.setBank(bankDao.getByCode(rs.getLong("bankCode")));
					
					

					//payments.add(payment);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return payments;

	}

	@Override
	public List<Payment> getAll() {
		List<Payment> payments = new ArrayList();
		// Sale sale=salesDao.getOne(saleCode);
		PreparedStatement prst;
		ResultSet rs = null;

		try {
			String sql = "";
			// sql="select * from sale order by saleCode desc;";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr,"
					+ "dueDate  from payment";

			prst = connection.prepareStatement(sql);


			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {

					Payment payment = new Payment(rs.getLong("paymentCode"),salesDao.getOne(rs.getLong("paymentCode")), rs.getInt("number"),
							rs.getDouble("amount"), rs.getDate("date").toLocalDate(), rs.getString("type"), rs.getString("stateCode"));
					payment.setDueDate(rs.getDate("dueDate").toLocalDate());
					payment.setChequeNumber(rs.getString("chequeNumber"));
					payment.setFirstNameCheque(rs.getString("firstNameCheque"));
					payment.setLastNameCheque(rs.getString("lastNameCheque"));
					
					payment.setBank(bankDao.getByCode(rs.getLong("bankCode")));
					
					

					payments.add(payment);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return payments;

	}

	@Override
	public long add(Payment payment) {
		if (payment.getPaymentCode() > 0)
			return -1;
		Random random= new Random();
		long key = 0;
		String sql = "insert into payment(number,date,amount,type,chequeNumber,dueDate,paymentDate,stateCode,firstNameCheque,lastNameCheque,bankCode) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);



			prst.setInt(1, payment.getNumber());

			LocalDate datel = LocalDate.now();
		
			prst.setDate(2, Date.valueOf(datel));
		//	prst.setDate(3, datesql1);
			prst.setDouble(3, payment.getAmount());
			prst.setString(4, payment.getType());
			
			prst.setString(5, payment.getChequeNumber());
			prst.setDate(6, Date.valueOf(datel));
			
		//	prst.setDate(7, payment.getDueDate().);
			prst.setDate(7, Date.valueOf(datel));
			prst.setLong(8, 1);
			
			prst.setString(9, payment.getFirstNameCheque());
			prst.setString(10, payment.getLastNameCheque());
			prst.setLong(11, payment.getBank().getBankCode());

			
			prst.executeUpdate();

			ResultSet rs = prst.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				payment.setPaymentCode(key);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return key;
	}

	@Override
	public Payment getOne(long paymentCode) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub

		if (payment.getPaymentCode() == 0)
			return null;

		String sql = "update payment set number=?,amount=?,type=?,dueDate=?,paymentDate=?,state=?,chequeNumber=? where paymentCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setDouble(8, payment.getPaymentCode());

			prst.setInt(1, payment.getNumber());
			prst.setDouble(2, payment.getAmount());
			prst.setString(3, payment.getType());
			LocalDate dueDate = LocalDate.now();
			LocalDate datePayment =payment.getDueDate();
			Date dueDateSql = Date.valueOf(dueDate);
			Date datePaymentSql = Date.valueOf(datePayment);

			prst.setDate(4, dueDateSql);
			prst.setDate(5, datePaymentSql);
			
			prst.setString(6, payment.getState());
			
			prst.setString(7, payment.getChequeNumber());

			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return payment;
	}
	@Override
	public int delete(Payment payment) {
		// TODO Auto-generated method stub
		if (payment.getPaymentCode() == 0)
			return 0;

		String sql = "delete from payment  where paymentCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setLong(1, payment.getPaymentCode());
			prst.execute();
			return 1;
		}catch(Exception exp){
			System.out.println(exp);
			
		}

		return 0;
	}

	@Override
	public void updateNumber(Payment payment) {


		// TODO Auto-generated method stub

		if (payment==null || payment.getPaymentCode() == 0)
			return;

		String sql = "update payment set number=? where paymentCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setDouble(2, payment.getPaymentCode());

			prst.setInt(1, payment.getNumber());
		
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		

	}

}

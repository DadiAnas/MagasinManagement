package com.shop.checkOutManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.shop.Connexion;
import com.shop.personsManagement.Person;

public class CheckOutDaoImpl implements CheckOutDao<Operation> {
	Connection connection = null;

	public CheckOutDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();
	}

	@Override
	public void add(Operation obj) {
		// System.out.println("vszz");
		if (obj.getCodeOperation() > 0)
			return;

		String sql = "insert into operation(code,amount,type,date,descriptionAuto,checkOutCode) values(?,?,?,?,?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("probl:" + obj.getType() + " " + obj.getCheckOut());
			prst.setLong(1, obj.getCode());
			prst.setDouble(2, obj.getMontant());
			prst.setString(3, obj.getType());
			LocalDate datel = LocalDate.now();
			java.sql.Date datesql = java.sql.Date.valueOf(datel);
			prst.setDate(4, datesql);
			prst.setString(5, obj.getDescriptionAuto());
			prst.setLong(6, obj.getCheckOut().getCodeCheckOut());

			prst.executeUpdate();

			ResultSet rs = prst.getGeneratedKeys();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Collection<Operation> getAll(CheckOut checkOut, String type) {

		Collection<Operation> operations = new ArrayList();
		// Sale sale=saleDao.getOne(codeSale);
		PreparedStatement prst;
		ResultSet rs = null;

		try {
			String sql = "";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from operation where type=? and checkOutCode=?";

			prst = connection.prepareStatement(sql);

			prst.setString(1, type);
			prst.setLong(2, checkOut.getCodeCheckOut());

			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {

					Operation operation = new Operation(rs.getLong("operationCode"), rs.getDouble("amount"),
							rs.getString("type"), rs.getDate("date"), rs.getString("descriptionAuto"), checkOut);

					operations.add(operation);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return operations;

	}

	@Override
	public Collection<Operation> getAll(CheckOut checkOut, int year) {

		Collection<Operation> operations = new ArrayList();
		// Sale sale=saleDao.getOne(codeSale);
		PreparedStatement prst;
		ResultSet rs = null;

		try {
			String sql = "";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from operation where checkOutCode=?";

			prst = connection.prepareStatement(sql);

			// prst.setString(1,type);
			prst.setLong(1, checkOut.getCodeCheckOut());

			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {

					Operation operation = new Operation(rs.getLong("operationCode"), rs.getDouble("amount"),
							rs.getString("type"), rs.getDate("date"), rs.getString("descriptionAuto"), checkOut);

					operations.add(operation);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return operations;

	}

	@Override
	public double getSolde(CheckOut checkOut, int year) {

		// Sale sale=saleDao.getOne(codeSale);
		PreparedStatement prst;
		ResultSet rs = null;
		double solde = 0;
		double soldeIn = 0;
		double soldeOut = 0;
		try {
			String sql = "";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from operation where checkOutCode=?";

			prst = connection.prepareStatement(sql);

			// prst.setString(1,type);
			prst.setLong(1, checkOut.getCodeCheckOut());

			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					if (rs.getString("type").equals("RETR")) {
						soldeOut+=rs.getDouble("amount");
					}
					if (rs.getString("type").equals("VERS")) {
						soldeIn+=rs.getDouble("amount");
					}
				//	System.out.println(rs.getDouble("amount"));
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return soldeIn-soldeOut;

	}

	@Override
	public Collection<Operation> getAll(CheckOut checkOut, String type, Date date) {

		Collection<Operation> operations = new ArrayList();
		// Sale sale=saleDao.getOne(codeSale);
		PreparedStatement prst;
		ResultSet rs = null;

		try {
			String sql = "";
			int index = 0;
			sql = "select *,DATE_FORMAT(date,'%d/%m/%Y') AS datefr  from operation";

			prst = connection.prepareStatement(sql);

			// prst.setLong(1,sale.getCodeSale());

			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				while (rs.next()) {

					Operation operation = new Operation(rs.getLong("operationCode"), rs.getDouble("amount"),
							rs.getString("type"), rs.getDate("datefr"), rs.getString("descriptionAuto"), checkOut);

					operations.add(operation);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return operations;

	}

	@Override
	public Collection<Operation> getAll(CheckOut checkOut, String type, Date date1, Date date2) {

		return null;
	}

	@Override
	public CheckOut getCheckOut(Person owner) {
		// TODO Auto-generated method stub
		CheckOut checkOut = null;
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			
			prst = connection.prepareStatement("select * from checkOut where ownerCode=?");

			prst.setLong(1, owner.getPersonCode());
			rs = prst.executeQuery();
			// rs=prst.getResultSet();
			if (rs != null) {
				if (rs.next()) {
					System.out.println("iciciciic-----------------------------------");
					checkOut = new CheckOut(rs.getLong("checkOutCode"), rs.getString("description"), owner);

				}
				else
					checkOut=	addCheckOut(new CheckOut(0, "Compte local", owner));
				

			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return checkOut;

	}

	@Override
	public CheckOut addCheckOut(CheckOut checkOut) {
		if (checkOut.getCodeCheckOut() > 0)
			return null;

		String sql = "insert into checkout(ownerCode,description) values(?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			prst.setLong(1, checkOut.getPerson().getPersonCode());
			prst.setString(2, checkOut.getDescription());
			prst.executeUpdate();

			ResultSet rs = prst.getGeneratedKeys();
			if (rs.next()) {
				checkOut.setCodeCheckOut(rs.getLong(1));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return checkOut;

	}

}

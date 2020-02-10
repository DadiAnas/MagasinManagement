package com.shop.salesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.shop.Connexion;
import com.shop.personsManagement.Person;
import com.shop.productsManagement.Product;
import com.shop.productsManagement.ProductDao;
import com.shop.productsManagement.ProductDaoImpl;
import com.shop.statesManagement.State;
//import com.shop.productsManagement.Tail;
//import com.shop.productsManagement.TailDao;
//import com.shop.productsManagement.TailDaoImpl;

public class SaleItemDaoImpl implements SaleItemDao<SaleItem> {
	Connection connection = null;
	ProductDao<Product> productDao = new ProductDaoImpl();
//	TailDao<Tail> taildao = new TailDaoImpl();

	public SaleItemDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();

	}

	@Override
	public void add(SaleItem saleItem) {
		String sql = "insert into saleItem(number,saleCode,productCode,quantite,subTotal,salePrice,qteret) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setInt(1, saleItem.getNumber());
			prst.setLong(2, saleItem.getSale().getTransactionCode());

			prst.setLong(3, saleItem.getProductCode());
			prst.setInt(4, saleItem.getQuantite());
			prst.setDouble(5, saleItem.getSubTotal());
			prst.setDouble(6, saleItem.getSalePrice());
			prst.setInt(7, 0);

			prst.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public SaleItem getOne(long code) {
		return null;
		}

	@Override
	public List<SaleItem> getAll(long saleCode) {
		List<SaleItem> saleItems = new ArrayList();
System.out.println("message du 5/11");
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select * from saleItem where saleCode=?");
			prst.setLong(1, saleCode);
			rs = prst.executeQuery();
			// rs=prst.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					System.out.println("code diagnos:"+rs.getLong("productCode"));
					Product p = productDao.getOne(rs.getLong("productCode"));
					// Product
					// produit=produicDao.getOne(rs.getLong("productCode"));
					// tail.setProduct(produit);
					
					SaleItem saleItem = new SaleItem(rs.getLong("saleItemCode"), rs.getInt("number"), p, rs.getInt("quantite")
							, rs.getDouble("salePrice"), rs.getInt("qteret"));
					saleItems.add(saleItem);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saleItems;

	}

	@Override
	public void updateSaleItem(SaleItem saleItem) {
		String sql = "update saleItem set qteret=? where saleItemCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setInt(1, saleItem.getQteret());
			prst.setLong(2, saleItem.getSaleItemCode());
	
			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<SaleItem> getSaleItemsProduct(long productCode) {
		// TODO Auto-generated method stub

		List<SaleItem> saleItems = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select person.firstName,person.lastName"
					+",state.stateCode,state.stateName"
					+",sale.date,sale.saleCode,sale.total"
					
					+",product.productCode,product.designation,product.salePrice as salePrice1"
					+",saleItem.saleItemCode,saleItem.number,saleItem.quantite,saleItem.salePrice,saleItem.subTotal,saleItem.qteret"
					
					+ "	from person,sale,saleItem,product,state" + " where person.personCode=sale.customerCode"
					+ " and sale.saleCode=saleItem.saleCode" + " and saleItem.productCode=product.productCode"
					+ " and state.stateCode=sale.stateCode"
					+ " and sale.stateCode!=4"
					+ " and product.productCode=?"

			);
			prst.setLong(1, productCode);
			rs = prst.executeQuery();
			// rs=prst.getResultSet();

			if (rs != null) {
				while (rs.next()) {
			
					Person person=new Person(rs.getString("firstName"), rs.getString("lastName"), null, null, "CL");
					SaleItem saleItem = null;
					State state=new State(rs.getInt("stateCode"), rs.getString("stateName"));
					Sale sale=new Sale(rs.getLong("saleCode"), state, rs.getDouble("total"), rs.getDate("date").toLocalDate());
					sale.setPerson(person);
					
					Product product=new Product(rs.getLong("productCode"), rs.getString("designation"), 0,
							0);
					//Tail tail=new Tail(rs.getLong("tailCode"), product, rs.getString("tail"));
					saleItem=new SaleItem(rs.getLong("saleItemCode"), rs.getInt("number"), product, rs.getInt("quantite"), 
							 rs.getDouble("salePrice"),  rs.getInt("qteret"));
					saleItem.setSale(sale);
							//new SaleItem(rs.getDate("date"), rs.getLong("saleCode"), name, rs.getString("tail"),
							//rs.getDouble("salePrice"), rs.getInt("quantite"), rs.getInt("qteret"));

					saleItems.add(saleItem);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return saleItems;

	}

}

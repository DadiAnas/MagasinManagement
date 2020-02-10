package com.shop.purchasesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.shop.Connexion;
import com.shop.personsManagement.Person;
import com.shop.productsManagement.Product;
import com.shop.productsManagement.ProductDao;
import com.shop.productsManagement.ProductDaoImpl;
import com.shop.statesManagement.State;


public class PurchaseItemDaoImpl implements PurchaseItemDao<PurchaseItem> {
	Connection connection = null;
	ProductDao<Product> productDao = new ProductDaoImpl();

	public PurchaseItemDaoImpl() {
		Connexion connexion = Connexion.getConnexion();
		connection = connexion.getConnection();

	}
	public List<PurchaseItem> getByDesignation(long purchaseCode,String designation){
		List<PurchaseItem> purchaseItems = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select * from purchaseItem as pi,product as pd where purchaseCode=? and designation like ? and pd.productCode = pi.productCode  ");
			prst.setLong(1, purchaseCode);
			prst.setString(2, "%"+designation+"%");
			rs = prst.executeQuery();
			// rs=prst.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					Product p = productDao.getOne(rs.getLong("productCode"));
					// Product
					// produit=produicDao.getOne(rs.getLong("productCode"));
					// tail.setProduct(produit);

					PurchaseItem purchaseItem = new PurchaseItem(rs.getLong("purchaseItemCode"), rs.getInt("number"), p,
							rs.getInt("quantite"), rs.getDouble("subTotal"), rs.getDouble("purchasePrice"),
							rs.getInt("qteret"));
					purchaseItems.add(purchaseItem);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchaseItems;
	}
	@Override
	public void add(PurchaseItem purchaseItem) {
		String sql = "insert into purchaseItem(number,purchaseCode,productCode,quantite,subTotal,purchasePrice,qteret) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setInt(1, purchaseItem.getNumber());
			prst.setLong(2, purchaseItem.getPurchase().getTransactionCode());

			prst.setLong(3, purchaseItem.getProduct().getProductCode());
			prst.setInt(4, purchaseItem.getQuantite());
			prst.setDouble(5, purchaseItem.getSubTotal());
			prst.setDouble(6, purchaseItem.getPurchasePrice());
			prst.setInt(7, 0);

			prst.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public PurchaseItem getOne(long code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PurchaseItem> getAll(long purchaseCode) {
		List<PurchaseItem> purchaseItems = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select * from purchaseItem where purchaseCode=?");
			prst.setLong(1, purchaseCode);
			rs = prst.executeQuery();
			// rs=prst.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					Product p = productDao.getOne(rs.getLong("productCode"));
					// Product
					// produit=produicDao.getOne(rs.getLong("productCode"));
					// tail.setProduct(produit);

					PurchaseItem purchaseItem = new PurchaseItem(rs.getLong("purchaseItemCode"), rs.getInt("number"), p,
							rs.getInt("quantite"), rs.getDouble("subTotal"), rs.getDouble("purchasePrice"),
							rs.getInt("qteret"));
					purchaseItems.add(purchaseItem);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchaseItems;

	}

	@Override
	public void updatePurchaseItem(PurchaseItem purchaseItem) {
		String sql = "update purchaseItem set qteret=? where purchaseCode=?";
		try {
			PreparedStatement prst = connection.prepareStatement(sql);

			prst.setInt(1, purchaseItem.getQteret());
			prst.setLong(2, purchaseItem.getPurchaseItemCode());

			prst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<PurchaseItem> getPurchaseItemsProduct(long productCode) {
		// TODO Auto-generated method stub

		List<PurchaseItem> purchaseItems = new ArrayList();

		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement("select person.firstName,person.lastName"
					+ ",state.stateCode,state.stateName" + ",purchase.date,purchase.purchaseCode,purchase.total"

					+ ",product.productCode,product.designation,product.purchasePrice as purchasePrice1"
					+ ",purchaseItem.purchaseCode,purchaseItem.number,purchaseItem.quantite,purchaseItem.purchasePrice,purchaseItem.subTotal,purchaseItem.qteret"

					+ "	from person,purchase,purchaseItem,product,state"
					+ " where person.personCode=purchase.supplierCode"
					+ " and purchase.purchaseCode=purchaseItem.purchaseCode"
					+ " and purchaseItem.productCode=product.productCode" + " and state.stateCode=purchase.stateCode"

					+ " and product.productCode=?"

			);
			prst.setLong(1, productCode);
			rs = prst.executeQuery();
			// rs=prst.getResultSet();

			if (rs != null) {
				while (rs.next()) {

					Person person = new Person(rs.getString("firstName"), rs.getString("lastName"), null, null, "FR");
					PurchaseItem purchaseItem = null;
					State state = new State(rs.getInt("stateCode"), rs.getString("stateName"));
					Purchase purchase = new Purchase(rs.getLong("purchaseCode"), state, rs.getDouble("total"),
							rs.getDate("date").toLocalDate());
					purchase.setPerson(person);

					Product product = new Product(rs.getLong("productCode"), rs.getString("designation"),
							rs.getDouble("purchasePrice"), 0);
					// Tail tail=new Tail(rs.getLong("tailCode"), product,
					// rs.getString("tail"));
					purchaseItem = new PurchaseItem(rs.getLong("purchaseItemCode"), rs.getInt("number"), product,
							rs.getInt("quantite"), rs.getDouble("subTotal"), rs.getDouble("purchasePrice"),
							rs.getInt("qteret"));
					purchaseItem.setPurchase(purchase);
					// new PurchaseItem(rs.getDate("date"),
					// rs.getLong("purchaseCode"), name, rs.getString("tail"),
					// rs.getDouble("purchasePrice"), rs.getInt("quantite"),
					// rs.getInt("qteret"));

					purchaseItems.add(purchaseItem);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return purchaseItems;

	}

	@Override
	public Set<Product> getProductsByPurchasePrice() {
		HashSet<Product> products = new LinkedHashSet<>();
System.out.println("debut sql..............");
		PreparedStatement prst;
		ResultSet rs = null;
		try {
			prst = connection.prepareStatement(
					"select purchaseItem.purchaseCode,product.salePrice,product.barreCode,product.productCode,product.designation"
							+ ",purchaseItem.purchasePrice" + "	from purchaseItem,product" + " where 1"
							+ " and purchaseItem.productCode=product.productCode order by product.designation,purchaseItem.purchaseCode desc;");
			rs = prst.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					//
				//	int n = productDao.getQuantity(rs.getLong("productCode"));
					int n=0;
					Product product = new Product(rs.getLong("productCode"),
							rs.getString("designation"), rs.getDouble("purchasePrice"),
							rs.getDouble("salePrice"));
					product.setStockquantity(n);
					products.add(product);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("fin sql..............");

		return products;
	}

}

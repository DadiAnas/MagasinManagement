package com.shop.productsManagement;

import com.shop.productsManagement.categoriesManagement.Category;
import com.shop.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao<Product>{
    Connection connection;
    public ProductDaoImpl(){
        Connexion connexion = Connexion.getConnexion();
        connection = connexion.getConnection();
    }
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "Select * from product";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            rs = prst.executeQuery();
            if (rs != null){
                while (rs.next()){
                    Product product= new Product(rs.getLong("productCode"),rs.getString("designation"),rs.getDouble("purchasePrice"),rs.getDouble("salePrice"),rs.getInt("stockquantity"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    @Override
    public List<Product> getAll(String key) {
        List<Product> products = new ArrayList<>();
        String sql = "Select * from product where designation like ?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            prst.setString(1,"%"+key+"%");
            rs = prst.executeQuery();
            if (rs != null){
                while (rs.next()){
                    Product product= new Product(rs.getLong("productCode"),rs.getString("designation"),rs.getDouble("purchasePrice"),rs.getDouble("salePrice"),rs.getInt("stockquantity"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getAllWithQuantity() {
        return null;
    }

    @Override
    public Product add(Product product) {
        String sql="insert into product(productCode,designation,purchasePrice,salePrice,categoryCode,stockquantity) values(?,?,?,?,?,?)";
        long key = 0;
        try {
            PreparedStatement prst=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            prst.setString(1, String.valueOf(product.getProductCode()));
            prst.setString(2, product.getDesignation());
            prst.setDouble(3, product.getPurchasePrice());
            prst.setDouble(4, product.getSalePrice());
            prst.setLong(5, product.getCategory().getCategoryCode());
            prst.setLong(6, product.getStockquantity());

            prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getLong(1);
                product.setProductCode(key);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product save(Product product) {
        String sql="update product set designation=?,purchasePrice=?,salePrice=?,categoryCode=?,stockquantity=? where productCode=?";
        try {
            PreparedStatement prst=connection.prepareStatement(sql);

            prst.setString(1, product.getDesignation());
            prst.setDouble(2, product.getPurchasePrice());
            prst.setDouble(3, product.getSalePrice());
            prst.setLong(4, product.getCategory().getCategoryCode());
            prst.setLong(5, product.getStockquantity());
            prst.setLong(6, product.getProductCode());

            prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public boolean delete(long code) {
        String sql="delete from product where productCode=?";
        try {
            PreparedStatement prst=connection.prepareStatement(sql);
            prst.setLong(1, code);
            prst.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Product getOne(long code) {
        Product product=null;
        PreparedStatement prst;
        String sql="select product.productCode,product.designation,product.purchasePrice,product.salePrice,product.categoryCode,category.categoryName from product,category"
                + " where product.categoryCode=category.categoryCode and product.productCode=?";
        ResultSet rs=null;
        try {
            prst = connection.prepareStatement(sql);
            prst.setLong(1, code);
            rs = prst.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    Category category = new Category(rs.getLong("categoryCode"), rs.getString("categoryName"));
                    product= new Product(rs.getLong("productCode"),rs.getString("designation"),rs.getDouble("purchasePrice"),rs.getDouble("salePrice"),category,rs.getInt("stockquantity"));
                    System.out.println(product.getProductCode()+"dsfffffffds");
                } else {
                    System.out.println("Cannot select product");
                }
            }
        }catch (SQLException e) {
                System.out.println(e.getSQLState());
            }
            return product;
        }

    @Override
    public List<Product> getByCategory(Category category) {
        List<Product> products=new ArrayList();
        PreparedStatement prst;
        ResultSet rs=null;
        try {
            prst = connection.prepareStatement("select product.productCode,product.designation,product.purchasePrice,product.salePrice,product.categoryCode,category.categoryName,product.stockquantity from product,category where product.categoryCode=category.categoryCode and category.categoryCode=?");
            prst.setLong(1, category.getCategoryCode());
            rs=prst.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Product product=new Product(rs.getLong("productCode"), rs.getString("designation"), rs.getDouble("purchasePrice"), rs.getDouble("salePrice"),rs.getInt("stockquantity"));
                    product.setCategory(category);
                    products.add(product);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int getQuantity(long code) {
        String sql1="select  (sum(saleitem.quantite) - sum(saleitem.qteret)) nbr from saleitem,sale where sale.saleCode=saleitem.saleCode and sale.stateCode!=4 and saleitem.productCode=?";
        String sql2="select  (sum(purchaseitem.quantite) - sum(purchaseitem.qteret)) nbr from purchaseitem,purchase where purchase.purchaseCode=purchaseitem.purchaseCode and purchase.stateCode!=4 and  purchaseitem.productCode=?";
        int n1=0,n2=0;
        PreparedStatement prst;
        ResultSet rs=null;
        try {
            prst = connection.prepareStatement(sql1);
            prst.setLong(1, code);
            rs=prst.executeQuery();
            if(rs!=null){
                if(rs.next()){
                    n2=rs.getInt("nbr");
                }
            }

            prst = connection.prepareStatement(sql2);
            prst.setLong(1, code);
            rs=prst.executeQuery();
            if(rs!=null){
                if(rs.next()){
                    n1=rs.getInt("nbr");
                }
            }


        }catch(Exception exp){

        }
        return n1-n2;
    }
}

package com.shop.productsManagement.categoriesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import com.shop.Connexion;

public class CategoryDaoImpl implements CategoryDao<Category> {
    Connection connection = null;

    public CategoryDaoImpl() {
        Connexion connexion = Connexion.getConnexion();
        connection = connexion.getConnection();
    }
    @Override
    public Category getOne(long categoryCode){
        String sql = "select * from category where categoryCode = ? ";
        PreparedStatement prst= null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            prst.setString(1,String.valueOf(categoryCode));
            rs = prst.executeQuery();
            if(rs != null){
                while (rs.next()){
                    return new Category(rs.getLong("categoryCode"),rs.getString("categoryName"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "Select * from Category";
        PreparedStatement prst;
        ResultSet rs;
        try {
            prst = connection.prepareStatement(sql);
            rs = prst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Category category = new Category(rs.getLong("categoryCode"), rs.getString("categoryName"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> getAll(String key) {
        List<Category> categories = new ArrayList<>();
        String sql = "Select * from category where categoryName like ?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            prst.setString(1,"%"+key+"%");
            rs = prst.executeQuery();
            if (rs != null){
                while (rs.next()){
                    Category category= new Category(rs.getLong("categoryCode"),rs.getString("categoryName"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public void add(Category category) {
        String sql = "insert into category(categoryName) values(?)";
        try {
            PreparedStatement prst = connection.prepareStatement(sql);

            prst.setString(1, category.getCategoryName());
            prst.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Category save(Category category) {
        String sql="update category set categoryName= ? where categoryCode = ?";
        try {
            PreparedStatement prst=connection.prepareStatement(sql);
            prst.setString(1, category.getCategoryName());
            prst.setLong(2, category.getCategoryCode());
            prst.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean delete(long categoryCode) {
        String sql="delete from category where  categoryCode=?";
        try {
            PreparedStatement prst=connection.prepareStatement(sql);
            prst.setLong(1, categoryCode);
            prst.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category getByName(String name) {
        Category category = null;
        PreparedStatement prst;
        String sql = " select * from category where categoryName = ?";
        ResultSet rs = null;
        try {

            prst = connection.prepareStatement(sql);
            prst.setString(1, name);

            rs = prst.executeQuery();
            //	 rs=prst.getResultSet();
            if (rs != null) {
                if (rs.next()) {
                    category = new Category(rs.getLong("categoryCode"), rs.getString("categoryName"));
                    System.out.println("ok");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}

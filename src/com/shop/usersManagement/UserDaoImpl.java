package com.shop.usersManagement;

import com.shop.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao<User>{
    Connection connection;
    public UserDaoImpl(){
        Connexion connexion= Connexion.getConnexion();
        connection=connexion.getConnection();
    }

    public User exist(String login,String password){
        String sql="select * from user where login = ? and password = ? ";
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            prst = connection.prepareStatement(sql);
            prst.setString(1,login);
            prst.setString(2,password);
            rs = prst.executeQuery();
            if(rs != null){
                while (rs.next()) {
                    return new User(rs.getLong("userCode"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
                }
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    @Override
    public User getOne(long code) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllWithQuantity() {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(long code) {
        return false;
    }
}

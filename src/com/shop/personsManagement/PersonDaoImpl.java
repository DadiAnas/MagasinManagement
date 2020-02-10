package com.shop.personsManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.shop.Connexion;
import com.shop.checkOutManagement.CheckOut;
import com.shop.checkOutManagement.CheckOutDao;
import com.shop.checkOutManagement.CheckOutDaoImpl;
import com.shop.checkOutManagement.Operation;

public class PersonDaoImpl implements PersonDao<Person>{

	Connection connection=null;
//	SaleDao<Sale> salesDao=new SaleDaoImpl();
	
public PersonDaoImpl(){
	Connexion connexion=Connexion.getConnexion();
	connection=connexion.getConnection();
}

@Override
public Collection<Person> getByType(String type) {
	List<Person> persons=new ArrayList();
	PreparedStatement prst;
	ResultSet rs=null;
	try {
		prst = connection.prepareStatement("select * from person where type=?");
		prst.setString(1, type);
		
		rs=prst.executeQuery();
		if(rs!=null){
			while(rs.next()){
				Person cl=new Person(rs.getLong("personCode"), 
				rs.getString("firstName"), rs.getString("lastName"),
				rs.getString("telephone"),rs.getString("email"),rs.getString("type"));
		persons.add(cl);
			}
		
}
	} catch (SQLException e) {

		e.printStackTrace();
	}
	return persons;
}

	@Override
	public Collection<Person> getByCriteria(String criteria) {
		List<Person> persons=new ArrayList();
		PreparedStatement prst;
		ResultSet rs=null;
		try {
			prst = connection.prepareStatement("select * from person where firstName like ? or lastName like ? or email like ? or telephone like ?");
			prst.setString(1, "%"+criteria+"%");
			prst.setString(2, "%"+criteria+"%");
			prst.setString(3, "%"+criteria+"%");
			prst.setString(4, "%"+criteria+"%");

			rs=prst.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Person cl=new Person(rs.getLong("personCode"),
							rs.getString("firstName"), rs.getString("lastName"),
							rs.getString("telephone"),rs.getString("email"),rs.getString("type"));
					persons.add(cl);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public Collection<Person> getAll() {
		List<Person> persons=new ArrayList();
		PreparedStatement prst;
		ResultSet rs=null;
		try {
			prst = connection.prepareStatement("select * from person");

			rs=prst.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Person cl=new Person(rs.getLong("personCode"),
							rs.getString("firstName"), rs.getString("lastName"),
							rs.getString("telephone"),rs.getString("email"),rs.getString("type"));
					persons.add(cl);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return persons;
	}
@Override
public Person add(Person person) {
	// TODO Auto-generated method stub
	String sql="insert into person(firstName,lastName,telephone,email,type) values(?,?,?,?,?)";
	long key = 0;
	try {
		PreparedStatement prst=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		
		prst.setString(1, person.getFirstName());
		prst.setString(2, person.getLastName());
		prst.setString(3, person.getTelephone());
		prst.setString(4, person.getEmail());
		prst.setString(5, person.getType());

		prst.executeUpdate();
	
		ResultSet rs = prst.getGeneratedKeys();
		if (rs.next()) {
			CheckOutDao<Operation> checkOutDao=new CheckOutDaoImpl();
			key = rs.getLong(1);
			person.setPersonCode(key);
			CheckOut checkOut=new CheckOut( "", person);
			
			checkOutDao.addCheckOut(checkOut);
		}
		
	} catch (SQLException e) {

		e.printStackTrace();
	}

	return person;
}

@Override
public Person getOne(long personCode) {
	// TODO Auto-generated method stub
	Person person=null;
	PreparedStatement prst;
	ResultSet rs=null;
	try {
		prst = connection.prepareStatement("select * from person where personCode=?");
		
		prst.setLong(1, personCode);
		rs=prst.executeQuery();
	//	 rs=prst.getResultSet();
		if(rs!=null){
			if(rs.next()){
		Person pers=new Person(rs.getLong("personCode"), 
				rs.getString("firstName"), rs.getString("lastName"),
				rs.getString("telephone"),rs.getString("email"),rs.getString("type"));
		person=pers;
			}
		
}
	} catch (SQLException e) {

		e.printStackTrace();
	}
	return person;

}

@Override
public Person save(Person person) {
	String sql="update person set firstName=?,lastName=?,email=?,telephone=?,type=? where personCode=?";
	
	try {
		PreparedStatement prst=connection.prepareStatement(sql);
		
		prst.setString(1, person.getFirstName());
		prst.setString(2, person.getLastName());
		prst.setString(3, person.getEmail());
		prst.setString(4, person.getTelephone());
		prst.setString(5, person.getType());
		prst.setLong(6, person.getPersonCode());
		
		prst.executeUpdate();
		
	} catch (SQLException e) {

		e.printStackTrace();
	}

	return person;
}

@Override
public int delete(Person person) {
		
	
	String sql="delete from checkout where ownerCode=?";
	try {
		PreparedStatement prst=connection.prepareStatement(sql);
		
		prst.setLong(1, person.getPersonCode());
			prst.execute();
							
	} catch (SQLException e) {

		e.printStackTrace();
	}

	
	sql="delete from person where personCode=?";
	try {
		PreparedStatement prst=connection.prepareStatement(sql);
		
		prst.setLong(1, person.getPersonCode());
			prst.execute();
			return 1;
							
	} catch (SQLException e) {

		e.printStackTrace();
	}

	return 0;
}
	public Collection<String> getTypes(){
		List<String> types=new ArrayList();
		PreparedStatement prst;
		ResultSet rs=null;
		try {
			prst = connection.prepareStatement("select distinct type from person");

			rs=prst.executeQuery();
			if(rs!=null){
				while(rs.next()){
					types.add(rs.getString("type"));
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return types;
	}

}





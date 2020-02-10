package com.shop.statesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.shop.Connexion;

public class StateDaoImpl implements StateDao<State>{
	Connection connection=null;
	
public StateDaoImpl(){
	Connexion connexion=Connexion.getConnexion();
	connection=connexion.getConnection();
	
}


	@Override
	public State getOne(int code) {
		State state=null;
		PreparedStatement prst;
		ResultSet rs=null;
		try {
			prst = connection.prepareStatement("select * from state where stateCode=?");
			prst.setInt(1, code);
			rs=prst.executeQuery();
		//	 rs=prst.getResultSet();
			if(rs!=null){
				if(rs.next()){
					
			state=new State(rs.getInt("stateCode"),rs.getString("stateName"));
			
				}
			
}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return state;
	}

	@Override
	public Collection<State> getAll() {
		List<State> states=new ArrayList();
		PreparedStatement prst;
		ResultSet rs=null;
		try {
			prst = connection.prepareStatement("select * from state");
			rs=prst.executeQuery();
		//	 rs=prst.getResultSet();
			if(rs!=null){
				while(rs.next()){
					
			State state=new State(rs.getInt("stateCode"),rs.getString("stateName"));
			states.add(state);
				}
			
}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return states;

	}

}

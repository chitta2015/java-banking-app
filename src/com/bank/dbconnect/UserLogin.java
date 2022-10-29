package com.bank.dbconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
	
	
	public static boolean login(String loginId,String password) throws SQLException
	{
		
		Connection conn=DBConnection.connect();
		
	
			PreparedStatement statement=conn.prepareStatement("select * from customer where login_id=?");
			statement.setString(1, loginId);
			ResultSet result=statement.executeQuery();
			result.next();
			
			if(result.getString(6).equals(password))
			{
				return true;
			}
			else
			{
				return false;
			}
				
		
	}
	
}

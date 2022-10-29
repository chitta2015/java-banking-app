package com.bank.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	 static final String DB_URL="jdbc:mysql://localhost:3306/";
	 static final String DB_USER_NAME="root";
	 static final String DB_USER_PASSWORD="root";
	 static final String DB_NAME="bank_db";
	 
	 
	public static Connection connect()
	{
		Connection conn=null;
		
		try
		{
			conn=DriverManager.getConnection(DB_URL+DB_NAME, DB_USER_NAME, DB_USER_PASSWORD);
		
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return conn;
		
	}	
	

}

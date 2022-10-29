package com.bank.dbconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.exceptions.AccountNotFoundException;


public class CustomerService {
	
	static Connection conn=DBConnection.connect();
	
	public static double deposit(double amount,long accountID) throws SQLException
	{
		if(amount>0)
		{
			PreparedStatement statement=conn.prepareStatement("select * from accounts where account_ID=?");
			statement.setLong(1, accountID);
			ResultSet result=statement.executeQuery();
			result.next();
			
			double availableBalance=result.getDouble(4);
			double newBalance=availableBalance+amount;
			
			statement=conn.prepareStatement("update accounts set current_balance=? where account_ID=?");
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountID);
			
			statement.executeUpdate();	
			return newBalance;
		}
		else
		{
			return 0.0;
		}
	
	}
	
	public static double withdraw(double amount,long accountID) throws SQLException
	{
		if(amount>0)
		{
			PreparedStatement statement=conn.prepareStatement("select * from accounts where account_ID=?");
			statement.setLong(1, accountID);
			ResultSet result=statement.executeQuery();
			result.next();
			
			double availableBalance=result.getDouble(4);
			if(availableBalance>amount)
			{
				double newBalance=availableBalance - amount;
				statement=conn.prepareStatement("update accounts set current_balance=? where account_ID=?");
				statement.setDouble(1, newBalance);
				statement.setLong(2, accountID);
				
				statement.executeUpdate();	
				return newBalance;
			}
			else
			{
				return 0.0;
			}
		}
		return 0.0;
	}
	
	
	public static double balanceCheck(long accountId) throws SQLException
	{
		PreparedStatement statement=conn.prepareStatement("select * from accounts where account_ID=?");
		statement.setLong(1, accountId);
		ResultSet result=statement.executeQuery();
		
		if(result.next())
		{
			return result.getDouble(4); 
			
		}
		else
		{
			 new AccountNotFoundException("Invalid account Id!!");
		}
		return 0.0;
		
		
	}
	
	
	public static ResultSet checkAccountInfo(long accountId) throws SQLException
	{
		PreparedStatement statement=conn.prepareStatement("select * from accounts where account_ID=?");
		statement.setLong(1, accountId);
		ResultSet result=statement.executeQuery();
		
		return result;
	}

}

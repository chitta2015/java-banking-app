package com.bank.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dbconnect.CustomerService;
import com.bank.dbconnect.DBConnection;
import com.bank.dbconnect.UserLogin;
import com.bank.utility.UserInputValidation;

public class BankOperations {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		
		Connection conn=DBConnection.connect();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in)); 
		
		
		System.out.println("======================== LOGIN ====================================");
		boolean status = false;
		try {
			System.out.print("\t\t Enter login id:");
			String loginId=bufferedReader.readLine();
			
			System.out.println();
			
			System.out.print("\t\t Enter Password:");
			String password=bufferedReader.readLine();
			
		    status=UserLogin.login(loginId, password);
		    //System.out.println(status);
			
		} 
		catch (Exception e) 
		{
			
			System.out.println("Enter correct ID/PASSWORD ");
		} 
		
	if(status==true)
	{	
		do
		{
			System.out.println("=========================================================================");
			System.out.println("=============================  WELCOME USER  ============================");
			System.out.println("=========================================================================");
			System.out.println("\t\t 1 -> Deposit");  
			System.out.println("\t\t 2 -> Withdraw");
			System.out.println("\t\t 3 -> Balance Check");
			System.out.println("\t\t 4 -> Check account Info.");
			System.out.println("\t\t 5 -> Logout");
			System.out.println("=========================================================================");
			System.out.println("Enter a choice :");
			System.out.println("=========================================================================");
			int choice=Integer.parseInt(bufferedReader.readLine());
			
			switch(choice)
			{
			 case 1:System.out.println("Enter the account id:");
		 			long accountId=Integer.parseInt(bufferedReader.readLine());
				    System.out.println("Enter the deposit amount:");
			 		double depositAmount=Double.parseDouble(bufferedReader.readLine());
			 		double result=CustomerService.deposit(depositAmount, accountId);
			 		
			 		if(result>0.0)
			 		{
			 			System.out.println("Transaction is successfull!!");
			 			System.out.println("New Balance is :"+result);
			 		}
			 		else
			 		{
			 			System.out.println("Transaction failed!!");
			 			System.out.println("Enter valid deposit amount!!");
			 		}
			 		break;
			 case 2:
					 System.out.println("Enter the account id:");
		 			 accountId=Integer.parseInt(bufferedReader.readLine());
				     System.out.println("Enter the withdraw amount:");
				     double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());		
			 		 result=CustomerService.withdraw(withdrawalAmount, accountId);
			 		 
			 		 if(result>0.0)
			 		 {
			 			System.out.println("Transaction is successfull!!");
			 			System.out.println("New Balance is :"+result);
			 		 }
			 		 else
			 		 {
			 			System.out.println("Transaction failed!!");
			 			System.out.println("Insufficient balance!!");
			 		 }
			 		 break;
			 case 3: 
				 System.out.println("Enter the account id:");
				 accountId=Integer.parseInt(bufferedReader.readLine());
				 double availableBalance=CustomerService.balanceCheck(accountId);
				 
				 if(availableBalance>0)
				 {
					 System.out.println("Available Balance is :"+availableBalance);
				 }
				 else
				 {
					 System.out.println("Enter valid account Id!!");
				 }
			 	 break;
			 	 
			 	 
			 case 4: 	 
				 System.out.println("Enter the account id:");
				 accountId=Integer.parseInt(bufferedReader.readLine());
				 ResultSet row=CustomerService.checkAccountInfo(accountId);
				 
				 if(row.next())
				 {
					 System.out.println("=========================================================================");
					 System.out.println("Account Information Details:");
					 System.out.println("=========================================================================");
					 System.out.println("Account Id: " +row.getLong(1));
					 System.out.println("Account Opening date: " +row.getTimestamp(2));
					 System.out.println("Account Type: " +row.getString(3));
					 System.out.println("Bank Name: " +row.getString(5));
					 System.out.println("IFSC code: " +row.getLong(6));
					 System.out.println("Branch name: " +row.getString(7));
					 System.out.println("=========================================================================");

				 }
				 else
				 {
					 System.out.println("Invalid account Id!!");
				 }
				 break;
			 case 5: System.out.println("Are you sure?");	
			 		 System.out.println("Y -> Yes / N -> No");
			 		 String response=bufferedReader.readLine();
			 		 if(UserInputValidation.checkString(response))
			 		 {
				 		 if(response.equals("y") || response.equals("Y"))
				 		 {
				 			 status=false;
				 		 }
			 		 }
			 		 break;
			 	
			 default:System.out.println("Wrong choice!!"); 		
			
			
			}
		
			
		}
		while(status);
		 System.out.println("=========================================================================");
		System.out.println("Logged out successfully!!");
		System.out.println("Good Bye!!");
		 System.out.println("=========================================================================");

	}
	
	}

}

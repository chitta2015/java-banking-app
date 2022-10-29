package com.bank.utility;

public class UserInputValidation {
	
	
	public static boolean checkString(Object obj )
	{
		if(obj.getClass().equals("String"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}

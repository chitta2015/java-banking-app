package com.bank.testcases;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.bank.dbconnect.CustomerService;

public class DBOpearationTestCase {
	
	static CustomerService custService;
	
	
	@Test
	public void testDepositMethod1() throws SQLException
	{
		double newBalance=CustomerService.deposit(5000, 1234);
		assertEquals(30000, newBalance);
	}
	
	@Test
	public void testDepositMethod2() throws SQLException
	{
		double newBalance=CustomerService.deposit(0, 1234);
		assertEquals(0, newBalance);
	}

}

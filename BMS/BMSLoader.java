package com.bmsloader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.bms.Helper;

 
public class BMSLoader {
	int account_no;
	double balance;
	long pin;
	String account_holder;
	Scanner sc=new Scanner(System.in);
	//insert data
	public void insertData() throws SQLException{
		System.out.println("enter account no: ");
		 account_no=sc.nextInt();
		System.out.println("enter account holder name: ");
	    account_holder=sc.next();
		System.out.println("enter ifsc_code:");
		String ifsc_code=sc.next();
		System.out.println("enter branch name:");
		String branch=sc.next();
		System.out.println("enter account_type");
		String account_type=sc.next();
		System.out.println("enter balance");
	    balance=sc.nextDouble();
	    while(balance<1000) {
	    	System.out.println("Minimum 1000 is required for create an account........\n try again");
	    	System.out.println("enter balance");
	    	balance=sc.nextDouble();
	    }
		System.out.println("enter a 4 digit pin");
		 pin=sc.nextLong();
		Connection con1=Helper.con();
		PreparedStatement stmt=con1.prepareStatement("insert into Bank values(?,?,?,?,?,?,?)");
		stmt.setInt(1, account_no);
		stmt.setString(2, account_holder);
		stmt.setString(3,ifsc_code);
		stmt.setString(4,branch);
		stmt.setString(5, account_type);
		stmt.setDouble(6, balance);
		stmt.setLong(7, pin);
		stmt.executeUpdate();
		ResultSet rs=stmt.executeQuery("select * from bank");
		System.err.println("your account created");
	}
	//fetching
	 public void fetchData() throws SQLException{
	    	Connection con1=Helper.con();
	    	Statement stmt=con1.createStatement();
	    	 System.out.println("enter account number");
			 account_no=sc.nextInt();
			 System.out.println("enter pin");
			 pin=sc.nextLong();
	    	ResultSet rs=stmt.executeQuery("select * from Bank where account_no="+account_no+" and pin="+pin);
	    	//iteration
	    	while(rs.next()) {
	    		System.err.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getString(5)+" "+  rs.getDouble(6)+" "+ rs.getLong(7));
			}
		}
	 //deposit
	 public void deposit() throws SQLException{
		 Connection con1=Helper.con();
		 Statement stmt=con1.createStatement();
		 System.out.println("enter account number & pin");
		 account_no=sc.nextInt();
		 pin=sc.nextLong();
		 ResultSet rs=stmt.executeQuery("select balance from Bank where account_no="+account_no+" and pin="+pin);
		 while(rs.next()) {
		 balance=rs.getDouble(1);
		 System.out.println(+balance);}
		  System.out.println("enter amount to deposit");
		 double amount=sc.nextDouble();
		 System.out.println("new balance: ");
		 stmt.executeUpdate("update Bank set balance ="+(balance+amount)+"where account_no="+account_no+" and pin="+pin);
		 }
	  public void display() throws SQLException{
		 Connection con1=Helper.con();
		 Statement stmt=con1.createStatement();
		 ResultSet rs1=stmt.executeQuery("select balance from Bank where account_no="+account_no);
		 while(rs1.next()) {
			 balance=rs1.getDouble(1);
			 System.out.println(+balance);
		 }
	 }
	 //withdraw
	  public void withdraw() throws SQLException{
			 Connection con1=Helper.con();
			 Statement stmt=con1.createStatement();
			 System.out.println("enter account number & pin");
			 account_no=sc.nextInt();
			 pin=sc.nextLong();
			 ResultSet rs=stmt.executeQuery("select balance from Bank where account_no="+account_no+" and pin="+pin);
			 while(rs.next()) {
			 balance=rs.getDouble(1);
			 System.out.println(+balance);}
			  System.out.println("enter amount to deposit");
			 double amount=sc.nextDouble();
			 System.out.println("new balance: ");
			 stmt.executeUpdate("update Bank set balance ="+(balance-amount)+"where account_no="+account_no+" and pin="+pin);
			 }
		  public void show() throws SQLException{
			 Connection con1=Helper.con();
			 Statement stmt=con1.createStatement();
			 ResultSet rs1=stmt.executeQuery("select balance from Bank where account_no="+account_no);
			 while(rs1.next()) {
				 balance=rs1.getDouble(1);
				 System.out.println();
				 System.out.println(+balance);
			 }
		 }
	 //showing balance
	 public void showingBalance() throws SQLException{
		 Connection con1=Helper.con();
		 Statement stmt=con1.createStatement();
		 System.out.println("enter your name:");
		  account_holder=sc.next();
		  System.out.println("Hello " +account_holder);
		 System.out.println("enter your account number & pin");
		 account_no=sc.nextInt();
		 pin=sc.nextLong();
		 ResultSet rs=stmt.executeQuery("select balance from Bank where account_no="+account_no+" and pin="+pin);
		 while(rs.next()) {
		 balance=rs.getDouble(1);
		 System.out.println(+balance);
	 }
}
	 //closing account
	 public void close() throws SQLException{
		 Connection con1=Helper.con();
		 Statement stmt=con1.createStatement();
		 System.out.println("enter account number & pin");
		 account_no=sc.nextInt();
		 pin=sc.nextLong();
		 stmt.executeUpdate("delete from Bank where account_no="+account_no+" and pin="+pin);
		 System.err.println("your account deleted");
}
}

	 

package com.bms.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bms.helper.Helper;

class DuplicateAccountNoException extends Exception{
	DuplicateAccountNoException(String str){
		super(str);
	}
}
class DuplicatePinException extends Exception{
	DuplicatePinException(String str){
		super(str);
	}
}
class AccountNotFoundException extends Exception{
	AccountNotFoundException(String str){
		super(str);
	}
}
class WrongpinException extends Exception{
	WrongpinException(String str){
		super(str);
	}
}
class InsufficientBalance extends Exception{
	InsufficientBalance(String str){
		super(str);
	}
}
class InvalidPasswordException extends Exception{
	InvalidPasswordException(String str){
		super(str);
	}
}

public class BMSLoader {
	Scanner sc= new Scanner(System.in);
	int pin;
	long acc_no;
	double balance;
	String acc_holder,ifsc_code,branch,acc_type;
	
	public void storeData() throws Exception {
		System.out.println("Enter account number : ");
		acc_no=sc.nextLong();
		System.out.println("Enter account holder name : ");
		acc_holder=sc.next();
		System.out.println("Enter ifsc code :");
		ifsc_code=sc.next();
		System.out.println("Enter bank branch : ");
		branch=sc.next();
		System.out.println("choose from account type :");
		String acc_type=sc.next();
		System.out.println("Enter balance : ");
		balance=sc.nextDouble();
		while(balance<500) {
			System.err.println("Minimum balance 500 required to create an account"
					+ "\nTry again : ");
			balance=sc.nextDouble();
		}
		System.out.println("Create pin :");
		pin=sc.nextInt();
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs1=stmt.executeQuery("select acc_no from bank where acc_no="+acc_no);
		if(rs1.next()) {
			throw new DuplicateAccountNoException("Duplicate Account no. not valid");
		}else {
			rs1=stmt.executeQuery("select pin from bank where pin="+pin);
			if(rs1.next()) {
				throw new DuplicatePinException("Duplicate pin not valid");
			}else {
				PreparedStatement pstmt=conn.prepareStatement("insert into bank values(?,?,?,?,?,?,?)");
				pstmt.setLong(1,acc_no);
				pstmt.setString(2, acc_holder);
				pstmt.setString(3, ifsc_code);
				pstmt.setString(4, branch);
				pstmt.setString(5, acc_type);
				pstmt.setDouble(6, balance);
				pstmt.setInt(7,pin);
				pstmt.executeUpdate();
				System.err.println(" Your Account created ");
			}
		}	
	}
	public void displayData() throws SQLException, AccountNotFoundException, WrongpinException {
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		System.out.println("Enter account no. : ");
		acc_no=sc.nextLong();
		ResultSet rs=stmt.executeQuery("select * from bank where acc_no="+acc_no);
		if(!rs.next()) {
			throw new AccountNotFoundException("Account no. not found"+
		"\nEnter correct account no. or create a new account");
		}else {
			System.out.println("Enter pin : ");
			pin=sc.nextInt();
			if(rs.getInt(7)!=pin) {
					throw new WrongpinException("Wrong pin");
			}else {
				System.out.println("[Account No] : "+rs.getLong(1)+
						"\n[Account Holder] : "+rs.getString(2)+
						"\n[IFSC Code] : "+rs.getString(3)+
						"\n[Branch] : "+rs.getString(4)+
						"\n[Account Type] : "+rs.getString(5)+
						"\n[Balance] : "+rs.getDouble(6)+
						"\n[Pin] : "+rs.getInt(7));
			}
		}
	}
	public void deposit() throws SQLException, AccountNotFoundException,WrongpinException {
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		System.out.println("Enter account no. : ");
		acc_no=sc.nextLong();
		ResultSet rs=stmt.executeQuery("select * from bank where acc_no="+acc_no);
		if(!rs.next()) {
			throw new AccountNotFoundException("Account no. not found" + "\nEnter correct account no. or create a new account.");
		}else {
			System.out.println("Enter pin : ");
			pin=sc.nextInt();
			if(rs.getInt(7)!=pin) {
					throw new WrongpinException("Wrong pin");
			}else {
		System.out.println("Enter deposit amount : ");
		balance=sc.nextDouble();
		stmt.executeUpdate("update bank set balance=balance+"+balance+" where acc_no="+acc_no);
		System.out.println("Amount deposited successfully...");
		ResultSet rs1=stmt.executeQuery("select balance from bank where acc_no="+acc_no);
		while(rs1.next()) {
			System.err.println("Current balance : "+rs1.getDouble(1));
		}
	}}}
	public void withdraw() throws SQLException, InsufficientBalance, AccountNotFoundException, WrongpinException {
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		System.out.println("Enter account no. : ");
		acc_no=sc.nextLong();
		ResultSet rs=stmt.executeQuery("select * from bank where acc_no="+acc_no);
		if(!rs.next()) {
			throw new AccountNotFoundException("Account no. not found" + "\nEnter correct account no. or create a new account.");
		}else {
			System.out.println("Enter pin : ");
			pin=sc.nextInt();
			if(rs.getInt(7)!=pin) {
					throw new WrongpinException("Wrong pin");
			}else {
		System.out.println("Enter withdrawal amount : ");
		balance=sc.nextDouble();
		ResultSet rs1=stmt.executeQuery("select balance from bank where acc_no="+acc_no);
		while(rs1.next()) {
			if(balance>rs1.getDouble(1)) {
				throw new InsufficientBalance("!!You don't have sufficient balance in your account!! "
						+ "\nCurrent Balance : "+rs1.getDouble(1));
			}else {
				stmt.executeUpdate("update bank set balance=balance-"+balance+" where acc_no="+acc_no);
				System.out.println("Amount withdrawed successfully...");
				rs1=stmt.executeQuery("select balance from bank where acc_no="+acc_no);
				while(rs1.next()) {
					System.err.println("Remaining balance : "+rs1.getDouble(1));
				}
			}
		}}}
	}
	public void changePin() throws SQLException, AccountNotFoundException,WrongpinException {
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		System.out.println("Enter account no. : ");
		acc_no=sc.nextLong();
		ResultSet rs=stmt.executeQuery("select * from bank where acc_no="+acc_no);
		if(!rs.next()) {
			throw new AccountNotFoundException("Account no. not found" + "\nEnter correct account no. or create a new account.");
		}else {
			System.out.println("Enter pin : ");
			pin=sc.nextInt();
			if(rs.getInt(7)!=pin) {
					throw new WrongpinException("Wrong pin");
			}else {
		System.out.println("Enter new pin : ");
		pin=sc.nextInt();
		stmt.executeUpdate("update bank set pin="+pin+" where acc_no="+acc_no);
		System.out.println("Pin updated successfully...");
	}
}}}

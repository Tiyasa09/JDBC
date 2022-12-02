package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Scanner_JDBC {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("enter sname:");
		String sname=s.next();
	System.out.println("enter sadd:");
		String sadd=s.next();
		System.out.println("enter sid:");
	    int sid=s.nextInt();
	    System.out.println("enter phone:");
	    int phno=s.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	//connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/c2268","root","root");
	//create statement
			PreparedStatement stmt;
				//inserting data in database table
				String sql="insert into Student_manage values(?,?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setInt(3, phno);
			stmt.setInt(1, sid);
			stmt.setString(2, sname);
			stmt.setString(4,sadd);
			stmt.execute();
			System.out.println("inserted successfully");
			//closing
			con.close();
			}catch(Exception e) {
				System.out.println(e);
			}
}
}
package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Update_JDBC {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	//connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/c2268","root","root");
	//create statement
			Statement stmt=con.createStatement();
			//update an existing data
			stmt.executeUpdate("update student_manage set sadd='west bengal' where sid=2");
			//delete record
			stmt.executeUpdate("delete from student_manage where sid=3");
			//execute quary fetch data from database
			ResultSet rs=stmt.executeQuery("select *from student_manage");
			//iteration 
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
}}

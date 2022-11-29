package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert_JDBC {
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/c2268","root","root");
Statement stmt=con.createStatement();
stmt.executeUpdate("insert into student_manage values(4,'anu',123466789,'howrah')");
stmt.executeUpdate("insert into student_manage values(2,'riya',1234523489,'champahati')");
stmt.executeUpdate("insert into student_manage values(3,'sanju',123444789,'sonarpur')");
	System.out.println("inserted successfully");
	con.close();
	}catch(Exception e) {
		System.out.println(e);
	}
}
}

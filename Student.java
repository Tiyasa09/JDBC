package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;

public class Student {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static Connection con() throws SQLException{
		return DriverManager.getConnection(⦁	"jdbc:mysql://localhost:3306/pallabidb","System","Password"
)
	}

}

package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentHelper {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static Connection con() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/c2268","root","root");

	}
public static void main(String[] args) throws SQLException{
	MenuDrivenex md=new MenuDrivenex();
	md.saveMenyDriven();
	md.fetchMenyDriven();
	md.updateMenyDriven();
	md.deleteMenyDriven();
}
}

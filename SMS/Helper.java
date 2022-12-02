package com.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.sms.Fetch.TableFetch;
import com.sms.Student.TableInsert;
import com.sms.Test.TableDelete;

public class Helper {
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
	 public static void main(String...args) throws Exception {
		TableFetch it = new TableFetch();
		TableInsert ft = new TableInsert();
		TableDelete ut = new TableDelete();
		Scanner scan =new Scanner(System.in); 
		System.out.println("""
				Press 1 for Insert : 
				Press 2 for Display :
				Press 3 for Update :
				Press 4 for Delete :
				Press 5 for Exit : 
				""");
		int variable = scan.nextInt();
		System.out.println("-------------------------------");
		switch(variable) {
		case 2 : 
		  it.fetchMenyDriven();
		  break;
		case 1 :  
			ft.saveMenyDriven();
	 break;
		case 3 :
			ut.updateMenyDriven();
			break;
		case 4 :
			ut.deleteMenyDriven();
			break;
		case 5 :
			System.exit(0);
			break;
		}
	}
		
	}



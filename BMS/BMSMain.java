package com.bmsmain;

import java.sql.SQLException;
import java.util.Scanner;


import com.bmsloader.BMSLoader;


public class BMSMain {
	public static void main(String[] args) throws SQLException {
		BMSLoader bl=new BMSLoader();
		Scanner scan =new Scanner(System.in); //scanner class
		System.out.println("""
				Press 1 for create account : 
				Press 2 for showing account details :
				Press 3 for deposit money :
				Press 4 for withdraw money :
				Press 5 for showing balance :
				Press 6 for closing account : 
				""");
		int variable = scan.nextInt();
		System.out.println("-------------------------------");
		//cases
		switch(variable) {
		case 1 : 
			System.err.println("WELCOME");
		  bl.insertData();
		  break;
		case 2:
			bl.fetchData();
		  break;
		case 3:
			bl.deposit();
			bl.display();
		   break;
		case 4:
			bl.withdraw();
			bl.show();
			break;
		case 5:
			bl.showingBalance();
			break;
		case 6:
			bl.close();
	}

}}

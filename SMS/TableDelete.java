package com.sms.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.sms.Helper;

public class TableDelete {
	Scanner sc = new Scanner(System.in);
	//updating employee details in database
    public void updateMenyDriven()throws SQLException{
    	Connection conn=Helper.con();
		Statement stmt=conn.createStatement(); 
		System.out.println("enter employee city : ");
		String e_city=sc.nextLine();
		System.out.println("enter employee id : ");
		int e_id=sc.nextInt();
		stmt.executeUpdate("update menydriven set ecity='"+e_city+"' where e_id="+e_id);
		ResultSet rs=stmt.executeQuery("select * from MenyDriven");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));			
		}	
    }
	//deleting employee details in database
	public void deleteMenyDriven() throws SQLException{
		Connection conn = Helper.con();
		Statement stmt = conn.createStatement();
		System.out.println("Enter employee id : ");
		int e_id = sc.nextInt();
		stmt.executeUpdate("delete from menydriven where e_id="+e_id);	
		ResultSet rs=stmt.executeQuery("select * from MenuDriven");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));			
		}	
	}

    }
	



package employee_details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
	public static void main(String[] args) throws SQLException {//main method
		EmployeeUploader eu= new  EmployeeUploader();//creating object of main class
		Scanner scan =new Scanner(System.in); //scanner class
System.out.println("""
		Press 1 for Insert Department Records : 
		Press 2 for Insert Employee Records :
		Press 3 for Display Records :
		Press 4 for checking pf :
		Press 5 for Exit : 
		""");
int variable = scan.nextInt();
System.out.println("-------------------------------");
//cases
switch(variable) {
case 1 : 
  eu.storeDepartmentDetails();
  break;
case 2 :  
	try {
		eu.storeEmployeeDetails();
	} catch (SQLException | DuplicateDataException | LessSalaryAmountException
			| DepartmentNotFoundException e) {
		System.out.println(e);;
	}
	break;
case 3 :
	eu.retrieveDeptdetails();
	break;
case 4 :
	//System.out.println("Enter employee id");
	//int id= scan.nextInt();
	eu.calculatePF();
	break;
case 5 :
	System.out.println("thank you");
	System.exit(0);
	break;
}
}}




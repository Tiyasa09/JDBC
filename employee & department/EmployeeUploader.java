package employee_details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//validation
class DuplicateDataException extends Exception{
	public DuplicateDataException(String str) {
		super(str);
	}
}
class LessSalaryAmountException extends Exception{
	public LessSalaryAmountException(String str) {
		super(str);
	}
}
class DepartmentNotFoundException extends Exception{
	public DepartmentNotFoundException(String str) {
		super(str);
	}
}


 class EmployeeUploader {
	Scanner sc=new Scanner(System.in);//taking scanner class
	int d_id;
	String dname,d_head,d_description;
	//store
	public void storeDepartmentDetails() throws SQLException{//1st method
		//taking user input to store data
	System.out.println("enter department id");
	d_id=sc.nextInt();
	System.out.println("enter department name ");
	dname=sc.next();
	System.out.println("enter department head ");
	d_head=sc.next();
	System.out.println("enter department description ");
	d_description=sc.next();
	Connection conn=Helper.con();//connection with helper class
	PreparedStatement stmt=conn.prepareStatement("insert into Department values(?,?,?,?)");
	stmt.setInt(1, d_id);
	stmt.setString(2, dname);
	stmt.setString(3, d_head);
	stmt.setString(4, d_description);
	stmt.executeUpdate();
	ResultSet rs=stmt.executeQuery("select * from Department");
	//iteration
	while(rs.next()) {
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	}
	
	}
	int e_id,esalary,econ_no;
	String ename,eadd;
	//2nd method
	public void storeEmployeeDetails() throws SQLException,DuplicateDataException, LessSalaryAmountException, DepartmentNotFoundException{
		//storing data from user
		System.out.println("enter Employee id: ");
		e_id=sc.nextInt();
		System.out.println("enter Employee name: ");
		ename=sc.next();
		System.out.println("enter Employee address: ");
		eadd=sc.next();
		System.out.println("enter Employee salary: ");
		esalary=sc.nextInt();
		System.out.println("enter Employee contact no: ");
		econ_no=sc.nextInt();
		System.out.println("enter Employee d_id");
		d_id=sc.nextInt();
		Connection conn=Helper.con();//connection
		PreparedStatement stmt=conn.prepareStatement("insert into Employee values(?,?,?,?,?,?)");
		stmt.setInt(1, e_id);
		stmt.setString(2, ename);
		stmt.setString(3, eadd);
		stmt.setInt(4, esalary);
		stmt.setInt(5, econ_no);
		stmt.setInt(6, d_id);
		stmt.executeUpdate();
		//conditions
		if((d_id==e_id) || (dname==ename)){
			throw new DuplicateDataException("employee already exists");
			}
			if(esalary<1000) {
				throw new LessSalaryAmountException("salary cannot be less than 1000");
			}
			if(d_id==0) {
				throw new DepartmentNotFoundException("department does not exists");
			}
			
			System.out.println("successfully inserted");
			
			}
			
			public void retrieveDeptdetails() throws SQLException{//3rd method
				System.out.println("Enter the dept id you want to retreive  details from employee and department");
				int d_id=sc.nextInt();
                Connection conn=Helper.con();//connection
				Statement stmt=conn.createStatement(); 
		        System.out.println("employee details");
				ResultSet rs=stmt.executeQuery("select * from Employee");
		//iteration for employee details
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getInt(6));
		}
		System.out.println("department details");
		ResultSet rs1=stmt.executeQuery("select * from Department");
		//iteration for department details
		while(rs1.next()) {
			System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4));
		}
	}
	
	public void calculatePF() throws SQLException{
		Connection conn =Helper.con();
		Statement stmt =conn.createStatement();
		System.out.println("enter employee id");
		e_id=sc.nextInt();
		//System.out.println(+e_id);
		
		ResultSet rs = stmt.executeQuery("select esalary from employeee where e_id="+e_id);
		while(rs.next()) {
			esalary=rs.getInt(1);
			System.out.println(esalary);
			//conditions
			if(esalary>=100.00 && esalary<1500) {
				float pf =(esalary*5)/100;
				System.out.println("your pf is :" +pf);
			}
			if(esalary>=1500.00 && esalary<2000) {
				float pf =(esalary*6)/100;
				System.out.println("your pf is :" +pf);
			}
			if(esalary>=2000.00) {
				float pf =(esalary*7)/100;
				System.out.println("your pf is :" +pf);
			}
			
		}
	}

}

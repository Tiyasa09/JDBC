package com.bms.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bms.loader.BMSLoader;

public class BMSMain {
	public static void main(String[] args) {
		BMSLoader bms=new BMSLoader();
		Scanner sc=new Scanner(System.in);
		System.err.println("Welcome to Bank website");
		while(true) {
			System.out.println("1 for Create Account"
					+ "\n 2 for Display Details"
					+ "\n 3 for Deposit"
					+ "\n 4 for Withdraw"
					+"\n 5 for Change Pin"
					+ "\n 0 for Exit");
			int choice=sc.nextInt();
			while(!(choice>=0 && choice<6)) {
				System.err.println("Invalid choice \nTry Again : ");
				choice=sc.nextInt();
			}
			if(choice==0) {
				System.err.println("Thank you for choosing our bank");
				break;
			}else {
				try {
					if(choice==1) {
						bms.storeData();
					}else if(choice==2) {
						bms.displayData();
					}else if(choice==3) {
						bms.deposit();
					}else if(choice==4) {
						bms.withdraw();
					}else if(choice==5) {
						bms.changePin();
					}
				}catch(InputMismatchException e) {
					System.out.println("Invalid entry");
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}finally {
					System.err.println("1==Main Menu,\n0==Exit");
					int choice1=sc.nextInt();
					while(!(choice1==0||choice1==1)) {
						System.out.println("Invalid choice \nTry Again : ");
						choice1=sc.nextInt();
					}
					if(choice1==1) {
						continue;
					}else if(choice1==0) {
						System.err.println("Thank you for choosing our bank");
						break;
					}
				}
			}
		}
	}
}



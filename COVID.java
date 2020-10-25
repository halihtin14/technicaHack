package Location;

import java.util.Scanner;
import java.sql.*;

public class COVID {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/COVID";

	static final String USER = "root";
	static final String PASS = "Ulyt4020$";

	public static String MontgomeryCounty(int num) {

		System.out.println("Centers available for testing in " + num + " (Montgomery County):");
		System.out.println(" "); 

		Connection conn = null;
		Statement stmt = null;  

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "SELECT Center_Name, Antibody_Testing, PCR_Testing FROM COVID";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.printf("%-50s %-20s %-20s\n", "Center Name: ", "Antibody Testing: ", "PCR Testing: ");
			System.out.println("------------------------------------------------------------------------------------");
			while (rs.next()) {
				String Center_Name = rs.getString("Center_Name");
				String Antibody_Testing = rs.getString("Antibody_Testing");
				String PCR_Testing = rs.getString("PCR_Testing");

				System.out.printf("%-50s %-20s %-20s\n", Center_Name, Antibody_Testing, PCR_Testing);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt!=null)
					stmt.close();
			}
			catch (SQLException se2) {
			}//nothing we can do
			try {
				if (conn!=null) {
					conn.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}

		System.out.println(" ");
		return "Hope this helped, and stay safe!";
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Need to know where you can take a specific COVID-19 test in MD? This program will help you find all the centers \nthat test COVID-19 in your area, and tell you which type of test is available in each center.");

		System.out.println("");
		System.out.println("To start, enter your zip code --> ");
		int zip = sc.nextInt();

		System.out.println(" ");

		if (zip <= 20918 && zip >= 20812) {
			MontgomeryCounty(zip);
		}
		else {
			System.out.println("That zip code does not exist! Please enter a valid zip code.");
		}
	}
}
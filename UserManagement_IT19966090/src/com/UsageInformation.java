package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsageInformation {
	
	
	//method to connect to the DB
	private Connection connect(){ 
		
					Connection con = null; 
					
					try{ 
							Class.forName("com.mysql.jdbc.Driver"); 

							//Provide the correct details: DBServer/DBName, username, password 
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid","root","oop@12");
							
					} 
					catch (Exception e) {
						e.printStackTrace();
						} 
					
					return con; 
		} 
	
	//insert usage information
	
	public String insertUsageInformation( String userName, String address, String noOfUnit, String month){ 
		
				String output = ""; 
				
				try
				{ 
					Connection con = connect(); 
					
					if (con == null) 
					{
						return "Error while connecting to the database for inserting."; 
						
					} 
					// create a prepared statement
					
					String query = " insert into userpowerusageinformation (`userName`,`address`,`noOfUnit`,`month`)"+" values (?, ?, ?, ?)"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
				
					preparedStmt.setString(1, userName); 
					preparedStmt.setString(2, address); 
					preparedStmt.setString(3, noOfUnit); 
					preparedStmt.setString(4, month); 
					
					
					// execute the statement

					preparedStmt.execute(); 
					con.close(); 
					
					String newUsage = readUsageInformation(); 
					output = "{\"status\":\"success\",\"data\":\""+newUsage+"\"}"; 
				} 
				
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the usage information.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		} 
	
	
	
	
	
	
	

}

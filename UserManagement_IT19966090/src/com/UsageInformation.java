package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsageInformation {
	//A common method to connect to the DB
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
	
	
	

}

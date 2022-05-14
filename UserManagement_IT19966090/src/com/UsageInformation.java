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
	
	//read user usage information
	public String readUsageInformation() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\" class=\"table\"> <tr>"
			+ "<th>User Name</th> <th>User Address</th>"
	 		+ "<th>Number of Units</th>"
	 		+ "<th>Month</th>"
	 		+ "<th>Update</th>  <th>Remove</th></tr>";
	   
	
	 String query = "select * from userpowerusageinformation"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String usageID = Integer.toString(rs.getInt("usageID")); 
	 String userName = rs.getString("userName"); 
	 String address = rs.getString("address"); 
	 String noOfUnit = Integer.toString(rs.getInt("noOfUnit")); 
	 String month = rs.getString("month"); 
	  
	 
	 
	 
	 // Add into the html table
	 output += "<tr><td><input id='hideUsageInformationIDUpdate' name='hideUsageInformationIDUpdate' type='hidden' value='"+usageID+"'>"+userName+"</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + noOfUnit + "</td>"; 
	 output += "<td>" + month + "</td>"; 
	
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-usageid='" + usageID + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-usageid='" + usageID + "'></td></tr>"; 
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	catch (Exception e) 
	 { 
	 output = "Error while reading the usage informations."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
	//update usage information
	
	public String updateUsageInformation(String usageID,String userName, String address, String noOfUnit, String month){ 
		
			String output = ""; 
			
			try{ 
					Connection con = connect(); 
					if (con == null){
						return "Error while connecting to the database for updating.";
						} 
					// create a prepared statement
					String query = "UPDATE userpowerusageinformation SET userName=?,address=?,noOfUnit=?,month=? WHERE usageID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setString(1, userName); 
					preparedStmt.setString(2, address); 
					preparedStmt.setInt(3, Integer.parseInt(noOfUnit)); 
					preparedStmt.setString(4, month); 
				
					preparedStmt.setInt(5, Integer.parseInt(usageID)); 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					String newUsage = readUsageInformation(); 
					output = "{\"status\":\"success\",\"data\":\""+newUsage+"\"}"; 

			} 
			
			catch (Exception e){ 
				
				output = "{\"status\":\"error\",\"data\":\"Error while updating the usage information.\"}"; 

				System.err.println(e.getMessage()); 
				
			} 
			
			return output; 
	} 
	
	
	
	public String deleteUsageInformation(String usageID){ 
		
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			
			if (con == null){
				return "Error while connecting to the database for deleting."; 
				} 
			// create a prepared statement
			String query = "delete from userpowerusageinformation where usageID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(usageID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newUsage = readUsageInformation(); 
			 output = "{\"status\":\"success\",\"data\":\""+newUsage+"\"}"; 

		} 
		
		catch (Exception e){ 
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the usage information.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
} 
	
	
	
	
	
	
	

}

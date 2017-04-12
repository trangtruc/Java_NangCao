package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ktra {
	
	public static void main(String args[]) {
		Connection conn = null;
		String hostname = "localhost";
		int dbport = 3306;
		String databasename = "ebookshop";
		String dbuser = "root";
		String dbpassword = "";
		Statement st = null;
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/ebookshop", "root", "");
	    	  if (conn != null) {
	    	  System.out.println("Connected database success");
	    	  }
	    	  st = conn.createStatement();
	    	  String sql = "select * from books";
	    	  
	    	  ResultSet rs = st.executeQuery(sql);
	    	  
	    	  while(rs.next()){

	    	  System.out.println(rs.getString(2));
	    	  
	    	  }
	    	  conn.close();
	    	 
		}catch(Exception e){
			System.out.println(e.getMessage());
		} 

	}

}

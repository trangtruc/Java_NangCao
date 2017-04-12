package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

public class JDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JDBC() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Connection conn = null;
	      Statement stmt = null;
	      
	      PrintWriter out = response.getWriter();
	      
	       String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost:3306/e-book";

	      //  Database credentials
	       String USER = "root";
	       String PASS = "";
	      
	      try {
	         // Step 1: Create a database "Connection" object
	         // For MySQL
	    	 
	    	  
	    	  // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          conn = DriverManager.getConnection(DB_URL, USER, PASS);

	       
	         System.out.println("Loi2");
	         // Step 2: Create a "Statement" object inside the "Connection"
	         stmt = conn.createStatement();
	 
	         // Step 3: Execute a sample SQL SELECT query
	         String sqlStr = "SELECT * FROM books WHERE title = "
	               + "'" + request.getParameter("timkiem") + "'"
	               + " AND quantity > 0 ORDER BY author ASC, title ASC";
	 
	         // Print an HTML page as output of query
	         out.println("<html><head><title>Ket qua Tim Duoc</title></head><body>");
	         //out.println("<h2>Thank you for your query.</h2>");
	         out.println("<p>You query is: " + sqlStr + "</p>"); //save to debug
	         ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server
	 
	         // Step 4: Process the query result
	         int count = 0;
	         while(rset.next()) {
	            // Print a paragraph <p>...</p> for each row
	            out.println("<p>" + rset.getString("author")
	                  + ", " + rset.getString("title")
	                  + ", $" + rset.getDouble("price") + "</p>");
	            ++count;
	         }
	         out.println("<p>==== " + count + " records found ====</p>");
	         out.println("</body></html>");
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				out.close();
				try {
					// Step 5: Close the Statement and Connection
					if (stmt != null) stmt.close();
					if (conn != null) conn.close();
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
	      }
	}

}

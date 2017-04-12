package book;

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


public class servlet_book extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet_book() {
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost:3306/ebookshop";

	      //  Database credentials
	      String USER = "root";
	      String PASS = "";
	      Connection conn = null;
	      Statement stmt = null;
	      
	      String t = request.getParameter("ra");
	      String h2= "Information Customer";
	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "INFORMATION BOOKS";
	      String docType =
	        "<!doctype html public \"-//w3c//dtd html 4.0 " +
	         "transitional//en\">\n";
	         out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor=\"#f0f0f0\">\n" +
	         "<h1 align=\"center\">" + title + "</h1>\n");
	      try{
	         // Register JDBC driver
	         Class.forName("com.mysql.jdbc.Driver").newInstance();;

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
	         String sql;
	         
	         out.println("<form method=\"POST\" action=\"URLThongTinOrder\">\n"
		      	       +"<table border=2>\n"
		      	       +"<tr>\n"
		      		        +	"<td>\n"
		      				
							+ "<input type=\"checkbox\" id=\"checkboxAll\"/>\n"
		      				
							+	"</td><td>ID\n"
		      				
		      				+	"</td><td>TITLE\n"
		      				
		      				+	"</td><td>AUTHOR\n"
		      				
		      				+	"</td><td>PRICE\n"
		      				
		      				+	"</td><td>QUANTITY\n"
		      				
		      				+	"</td><td>QUANTITY PURCHASED\n"
		      				+   "</td>\n"
		      	       +"</tr>\n");
	         
	         if(t.equals("title")){
	        	 sql = "SELECT * FROM books WHERE title = "
	                     + "'" + request.getParameter("timkiem") + "'"
	                     + " AND qty > 0 ORDER BY author ASC, title ASC";
	         ResultSet rs = stmt.executeQuery(sql);
	         
	         while(rs.next()){
	         
	            //Display values
			        	
			       out.println("<tr>\n"
						+	"<td>\n"
						+"<input type=\"checkbox\" class=\"checkboxId\" name=\"id\"/>\n"
						+	"</td><td>\n"
						+ rs.getInt("id") 
						+	"</td><td>\n"
						+ rs.getString("title") 
						+	"</td><td>\n"
						+ rs.getString("author") 
						+	"</td><td>\n"
						+ rs.getDouble("price") 
						+	"</td><td>\n"
						+ rs.getInt("qty") 
						+	"</td><td>\n"
						+ "<input type=\"number\" name=\"quty\"/></td>\n"
				+	"</tr>\n");
	            
	         }
	         out.println("</table>\n");
	         }
	         else{
	        	 sql = "SELECT * FROM books WHERE author = "
	                     + "'" + request.getParameter("timkiem") + "'"
	                     + " AND qty > 0 ORDER BY author ASC, title ASC";
		         ResultSet rs = stmt.executeQuery(sql);

		         // Extract data from result set
		         while(rs.next()){
		        	
			            //Display values
		        	 out.println("<tr>\n"
		        					+	"<td>\n"
		        					+"<input type=\"checkbox\" name=\"ck\"/>\n"
		        					+	"</td><td>\n"
		        					+ rs.getInt("id") 
		        					+	"</td><td>\n"
		        					+ rs.getString("title") 
		        					+	"</td><td>\n"
		        					+ rs.getString("author") 
		        					+	"</td><td>\n"
		        					+ rs.getDouble("price") 
		        					+	"</td><td>\n"
		        					+ rs.getInt("qty") 
		        					+	"</td><td>\n"
		        					+ "<input type=\"number\" name=\"quty\"/></td>\n"
		        			+	"</tr>\n");
		         }
		         out.println("</table>\n");
	         }
	         
	         out.println("<h2>" +h2+ "</h2></td>\n"
		        				+ "\nName: <input type=\"text\" name=\"name\"/> \n"
		        				+ "\nEmail: <input type=\"email\" name=\"email\"/>\n"
		        				+ "\nPhone: <input type=\"number\" name=\"phone\"/>\n"
		        				+	"<input type=\"submit\" value=\"Order\"/>\n"
		        	+	"</form>");
	         out.println("</body></html>");
	         
	         // Clean-up environment
//	         rs.close();
	         stmt.close();
	         conn.close();
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      }finally{
	         //finally block used to close resources
	         try{
	            if(stmt!=null)
	               stmt.close();
	         }catch(SQLException se2){
	         }// nothing we can do
	         try{
	            if(conn!=null)
	            conn.close();
	         }catch(SQLException se){
	            se.printStackTrace();
	         }//end finally try
	      } //end try



	}

}

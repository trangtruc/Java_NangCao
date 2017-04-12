<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information Order</title>
</head>
<body>
	<h2>Information Order</h2>
	<%
	 String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    String DB_URL="jdbc:mysql://localhost:3306/ebookshop";

    //  Database credentials
    String USER = "root";
    String PASS = "";
    Connection conn = null;
    Statement stmt = null;
    
    String tr = request.getParameter("ck");
    String t = request.getParameter("ra");   
    String ti = request.getParameter("timkiem");  
    
    String h2= "Information Customer";
    // Set response content type
    response.setContentType("text/html");
 
    try{
       // Register JDBC driver
       Class.forName("com.mysql.jdbc.Driver").newInstance();;

       // Open a connection
       conn = DriverManager.getConnection(DB_URL, USER, PASS);

       // Execute SQL query
       stmt = conn.createStatement();
       String sql; %>
       
      <table border=2>
	     <tr>
	    	<td>NAME</td>
	      	<td>EMAIL</td>
	      	<td>PHONE</td>
	      	<td>TITLE</td>
	      	<td>AUTHOR</td>
	      	<td>PRICE</td>
	      	<td>QUANTITY PURCHASED</td> 
	      </tr> 
      
      <% if(tr != null ){
      	 sql = "SELECT title,author,price FROM books;";

       ResultSet rs = stmt.executeQuery(sql);
       
       while(rs.next()){ %> 
 
 			<tr> 
				<td> 
					<%= request.getParameter("name") %>
				</td> 
				<td> 
					<%= request.getParameter("email") %>
				</td> 
				<td> 
					<%= request.getParameter("phone") %>
				</td> 
				<td> 
					<%= rs.getString("title")  %> 
				</td>
				<td> 
					<%= rs.getString("author") %>   
				</td>
				<td> 
					<%= rs.getDouble("price")  %> 
				</td>
				<td> 
					<%= request.getParameter("quty") %>
				</td> 
				
			</tr> 
          
       <% } %>
        </table>
       <% }
       else{ %>
      	 
      	 <p>You didn't buy book!! </p>
          
      
        </table>
       <% } %>
  
       <%
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
		%>
</body>
</html>
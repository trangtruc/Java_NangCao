package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JDBC_book extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JDBC_book() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		String url = "jdbc:mysql://localhost:8080/e-book";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "root";
		Statement st;
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,user,password);
			out.println("Success Connected!");
			String search = request.getParameter("search");
			
			ArrayList al = null;
			ArrayList search_list = new ArrayList();
			
			String query = "select * from books where title = '" + search + "' ";
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				al = new ArrayList();
				al.add(rs.getString(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				
				out.println("al: " + al);
				search_list.add(al);
			}
			request.setAttribute("booklist", search_list);
			RequestDispatcher view = request.getRequestDispatcher("/searchview.jsp");
			view.forward(request, response);
			
			conn.close();
			out.println("Disconnected");
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String getServletInfo(){
		return "Just Show description"; 
	}

}

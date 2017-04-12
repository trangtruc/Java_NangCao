package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Loginform extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Loginform() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(user.equals("truc") && pass.equals("truc")){
			out.println("Hello user " + user);
			RequestDispatcher re = request.getRequestDispatcher("servlet2");
			re.forward(request, response);
			
		}
		else{
			
		//	response.sendRedirect("/dangnhap.html");
			RequestDispatcher re = request.getRequestDispatcher("/dangnhap.html");
			re.forward(request, response);
			
		}
	}

}
package bai1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

/**
 * Servlet implementation class greeting
 */
@WebServlet("/greeting")
public class greeting extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public greeting() {
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
		String ten = request.getParameter("ten");
		String tuoi = request.getParameter("tuoi");
		String gioitinh = request.getParameter("gioitinh");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
//		RequestDispatcher re = request.getRequestDispatcher("url_welcome");
//		re.forward(request, response);
		
		String a,b;
//		if (tuoi.equals("6")) {  
//		      // Get the URL of this Servlet  			  
//		   a = "1";
//	   }else{ 
//		   if (tuoi.equals("7")) {  
//			      // Get the URL of this Servlet  			  
//			   a = "2";
//		   }else{ 
//		   
//			   if (tuoi.equals("8")) {  
//				      // Get the URL of this Servlet  			  
//				   a = "3";
//			   }else{ 
//			   
//				   if (tuoi.equals("9")) {  
//					      // Get the URL of this Servlet  			  
//					   a = "4";
//				   }else{ 
//				   
//					   if (tuoi.equals("10")) {  
//						      // Get the URL of this Servlet  			  
//						   a = "5";
//					   }else{ 
//					   
//						   if (tuoi.equals("22")) {  
//							      // Get the URL of this Servlet  			  
//							   a = "Sinh Vien";
//						   }else{ 
//						   
//							   if (tuoi.equals("20")) {  
//								      // Get the URL of this Servlet  			  
//								   a = "Sinh Vien";
//							   }else{ 
//							   
//								   a = "Tuoi chua cap nhat :D ";  
//							   }  
//						   }  
//					   } 
//				   }  
//			   }
//		   }
//		   
//	   }
		
		switch(tuoi){
		 case "6": a = "1"; break;
		 case "7": a = "2"; break;
		 case "8": a = "3"; break;
		 case "9": a = "4"; break;
		 case "10": a = "5"; break;
		 case "11": a = "6"; break;
		 case "12": a = "7"; break;
		 case "13": a = "8"; break;
		 case "14": a = "9"; break;
		 case "15": a = "10"; break;
		 case "16": a = "11"; break;
		 case "17": a = "12"; break;
		 case "18": a = "Sinh vien nam nhat"; break;
		 case "19": a = "Sinh vien nam hai"; break;
		 case "20": a = "Sinh vien nam ba"; break;
		 case "21": a = "Sinh vien nam tu"; break;
		 case "22": a = "Sinh vien nam cuoi "; break;
		 default:
			 a = "No Class :D"; 
		 }
		
		if (gioitinh.equals("nu")) { 
				b = "Hello Miss " + ten + "!";
		} else b = "Hello Mrs. " + ten + "!";
		
		 String title = "Xin Chào";
		 out.println("<html>");
		 out.println("");
		 out.println("<head><title> Greeting </title></head>");
		 out.println("<body>");
		 out.println("<p>Hello " + ten + " ,</p>");
		//out.println("Class:    <input value=\"" + a + "\"/>");
		 out.println("<p>Class: " + a + "</p>");
		 out.println("<p>Greeting: " + b + "</p>");
		 out.println("</body></html>");

		
	}
}
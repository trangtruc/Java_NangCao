package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.DbActions;
import servlet.Giohang;
import servlet.Monhang;
import servlet.Sanpham;

import java.sql.*;

public class DatabaseAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DatabaseAccess() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            HttpSession session = request.getSession(false);
	            DbActions db = new DbActions();
	            Giohang giohang = null;
	            Object obj = session.getAttribute("giohang");
	            if (obj != null) {
	                giohang = (Giohang) obj;
	            }
	            String action = request.getParameter("action");
	            String ra = request.getParameter("ra");
	            
	            if (action.equals("muahang") && ra.equals("title")) {
	            	
	                String tenSP = request.getParameter("monhang");//lấy ms sản phẩm được chọn
	                int soluong = Integer.parseInt(request.getParameter("soluong"));
	                
	                Sanpham sp = db.getSanPhamTitle(tenSP);//Lấy sản phẩm từ cơ sở dữ liệu
	               
	                Monhang mh = new Monhang(sp, soluong);
	               
	                if (giohang == null) {//mua lần đầu
	                    giohang = new Giohang();
	                }
	                giohang.ThemMonHang(mh);
	            }
	            else if(action.equals("muahang") && ra.equals("author")){
	            	String tenSP = request.getParameter("monhang");//lấy ms sản phẩm được chọn
	                int soluong = Integer.parseInt(request.getParameter("soluong"));
	                
	                Sanpham sp = db.getSanPhamAuthor(tenSP);//Lấy sản phẩm từ cơ sở dữ liệu
	               
	                Monhang mh = new Monhang(sp, soluong);
	                System.out.print(mh);
	                if (giohang == null) {//mua lần đầu
	                    giohang = new Giohang();
	                }
	                giohang.ThemMonHang(mh);
	            }
	            else if (action.equals("trahang")) {
	                String msSP = request.getParameter("mssp");//lấy ms sản phẩm cần xóa
	                Monhang mh=new Monhang(new Sanpham(msSP, "", "", 0f,0), 0);
	                giohang.XoaMonhang(mh);
	            } else if (action.equals("checkout")) {
	                response.sendRedirect("Checkout.jsp");
	            }
	            
	            session.setAttribute("giohang", giohang);
	 
	            //chuyển lại trang mua hàng
	            response.sendRedirect("Myeshop.jsp");
	            
	        } catch (Exception e) {
	            //e.printStackTrace();
	            response.getWriter().print(e.getMessage());
	        }
	}

}

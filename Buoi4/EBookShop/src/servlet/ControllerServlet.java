package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControllerServlet() {
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
		try{
			HttpSession session = request.getSession(false);
			DbConnection db = new DbConnection();
			Giohang giohang = null;
			Object obj = session.getAttribute("giohang");
			if(obj != null){
				giohang = (Giohang) obj;
			}
			
			String action = request.getParameter("action");
			//String action1 = request.getParameter("action1");
			String radio = request.getParameter("ra");
			int soluong = Integer.parseInt(request.getParameter("soluong"));
			
			if(action.equals("muahang") && radio.equals("title")){
				String tenSP = request.getParameter("timkiem");
				ArrayList<Sanpham> sp = db.getSanPhamTitle(tenSP);
				System.out.println(sp);
				Monhang mh = new Monhang(sp, soluong);
				System.out.println(soluong);
				if (giohang == null) {//mua lần đầu
                    giohang = new Giohang();
                }
                giohang.ThemMonHangSP(sp);
               
                
			}
			else if(action.equals("muahang") && radio.equals("author")){
				String tenSP = request.getParameter("timkiem");
				ArrayList<Sanpham> sp = db.getSanPhamAuthor(tenSP);
				Monhang mh = new Monhang(sp, soluong);
				if (giohang == null) {//mua lần đầu
                    giohang = new Giohang();
                }
                giohang.ThemMonHangSP(sp);
                
			} else if (action.equals("trahang")) {
                String msSP = request.getParameter("mssp");//lấy ms sản phẩm cần xóa
                Sanpham sp=new Sanpham(msSP, "", "", 0f,0);
                Monhang mh=new Monhang(sp, 0);
                giohang.XoaMonhang(mh);
                
                
            } else if (action.equals("checkout")) {
                response.sendRedirect("Checkout.jsp");
                return;
            }
            
            session.setAttribute("giohang", giohang);
 
            //chuyển lại trang mua hàng
            response.sendRedirect("myeshop.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

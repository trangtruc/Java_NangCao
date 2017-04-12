package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DbActions {
 
    private Connection con;
 
    public DbActions() throws Exception {
    	
    	String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost:3306/ProductDB";
	      String USER = "root";
	      String PASS = "";
	      
	      Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(DB_URL, USER, PASS);
    }
 
    /**
     * Lấy sản phẩm khi biết mã số sản phẩm
     * @param ms mã số sản phẩm
     * @return sản phẩm cần lấy hoặc null truong trường hợp sp không tồn tại
     * @throws Exception
     */
    public Sanpham getSanPham(String ms) throws Exception {
        Sanpham sp = null;
        Statement stmt = con.createStatement();
        String sql = "select * from sanpham where mssp ='" + ms + "'";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            String mssp = rs.getString("mssp");
            String ten = rs.getString("tenSP");
            float dg = rs.getFloat("dongia");
            sp = new Sanpham(mssp, ten, dg);
        }
        return sp;
    }
 
    /**
     * Lấy tât cả sản phẩm
     * @return danh sách sản phẩm
     * @throws Exception
     */
    public ArrayList<Sanpham> getAllProducts() throws Exception {
        ArrayList<Sanpham> ds = new ArrayList<Sanpham>();
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String sql = "select * from sanpham";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            String mssp = rs.getString("mssp");
            String ten = rs.getString("tenSP");
            float dg = rs.getFloat("dongia");
            ds.add(new Sanpham(mssp, ten, dg));
        }
        return ds;
    }
}


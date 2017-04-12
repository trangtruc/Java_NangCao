package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import servlet.Sanpham;

public class DbActions {
	private Connection conn;
	
	public DbActions() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop","root","");
		
	}
	
	
	
    
    
	public Sanpham getSanPhamId(String id) throws Exception {
		Sanpham sp=null;
		Statement stmt = conn.createStatement();
		String sql = "select * from books where id="+id+"";
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			String i = rs.getString("id");
			String t = rs.getString("title");
            String author = rs.getString("author");
            Float price = rs.getFloat("price");
            int quantity = rs.getInt("qty");
            sp = new Sanpham(i, t, author, price, quantity);
		}
		
		return sp;
		
	}
	
	public Sanpham getSanPhamTitle(String title) throws Exception {
		Sanpham sp=null;
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM books WHERE title = "
                + "'" + title + "'"
                + " AND qty > 0 ORDER BY author ASC, title ASC";
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			String id = rs.getString("id");
			String t = rs.getString("title");
            String au = rs.getString("author");
            Float price = rs.getFloat("price");
            int quantity = rs.getInt("qty");
            sp = new Sanpham(id, t, au, price, quantity);
		}
		
		return sp;
	}
	
	public Sanpham getSanPhamAuthor(String author) throws Exception {
		Sanpham sp=null;
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM books WHERE author = "
                + "'" + author + "'"
                + " AND qty > 0 ORDER BY author ASC, title ASC";
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()){
			String id = rs.getString("id");
			String t = rs.getString("title");
            String au = rs.getString("author");
            Float price = rs.getFloat("price");
            int quantity = rs.getInt("qty");
            sp = new Sanpham(id, t, au, price, quantity);
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
        Statement stmt = conn.createStatement();
        String sql = "select * from books";
        rs = stmt.executeQuery(sql);
        while(rs.next()){
        	String id = rs.getString("id");
        	String t = rs.getString("title");
            String author = rs.getString("author");
            Float price = rs.getFloat("price");
            int quantity = rs.getInt("qty");
            ds.add(new Sanpham(id, t, author, price, quantity));
        }
        return ds;
    }
	
	
}

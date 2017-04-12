<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="servlet.*" %>
<%
	Object obj=session.getAttribute("giohang");
	if(obj!=null){
	    Giohang giohang=(Giohang)obj;
	    ArrayList<Sanpham> dssp=giohang.getSPGiohang();
	    if(dssp.size()>0){
%>
<br>
<h2 align="center">Thông tin sách tìm thấy</h2>
<table align="center" border="1" width="95%">
    <tr>
    	<th>Chọn</th>
        <th>STT</th>
        <th>Tên sách</th>
        <th>Tên tác giả</th>
        <th>Số lượng mua</th>
        <th>Đơn giá</th>
        <th>Số lượng hiện còn trong kho</th>
        <th>Thành tiền</th>
        <th></th>
    </tr>
    
    <%int i=1;
    for(Sanpham mh:dssp){
    	
    	 String ms=mh.getId();
    	 System.out.println("3");
    	 System.out.println(ms);
    	 String tua=mh.getTitle();
         String tgia=mh.getAuthor();
         //int sl = mh.getSoluong();
       
         double dg=mh.getPrice();
         int qty =mh.getQty();
         
        %>
      
        <tr>
        	<td>
        	<input type="checkbox" name="check" value="<%=mh.getId()%>">
        	</td>
            <td><%=ms%></td>
            <td><%=tua%></td>
            <td><%=tgia%></td>
            <td></td>
            <td><%=dg%></td>
            <td></td>
            <td></td>
            
           
            <td>
                <form action="Controller" method="post">
                    <input type="hidden" name="action" value="trahang"/>
                    <input type="hidden" name="mssp" value="<%=ms%>"/>
                    <input type="submit" value="Trả hàng"/>
                </form>
            </td>
        </tr>
    <%}%>
    </table>
    <br/>
        <center>
            <form method="post" action="Controller">
                <input type="hidden" name="action" value="checkout"/>
                <input type="submit" value="Tính tiền"/>
            </form>
        </center>
   <%}
   }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="servlet.*" %>
<%
   Object obj=session.getAttribute("giohang");
   if(obj!=null){
       Giohang giohang=(Giohang)obj;
       ArrayList<Monhang>dssp=giohang.getGiohang();
%>
<h1 align="center">TÍNH TIỀN</h1>
<table align="center" border="1" width="95%">
    <tr>
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
    for(Monhang mhang:dssp){
    	
    	String ms=mhang.getSanpham().getId();
   	    String tua=mhang.getSanpham().getTitle();
        String tgia=mhang.getSanpham().getAuthor();
        int sl=mhang.getSoluong();
        double dg=mhang.getSanpham().getPrice();
        int qty =mhang.getSanpham().getQty();
       
        %>
        <tr>
            <td><%=ms%></td>
            <td><%=tua%></td>
            <td><%=tgia%></td>
            <td><%=sl%></td>
            <td><%=dg%></td>
            <td><%=qty-sl%></td>
            <td><%=dg*sl%></td>
        </tr>
    <%}%>
    <tr>
        <td align="right" colspan="5" ><b>Tổng tiền: <%= giohang.Tongtien() %> VNĐ</b></td>
    </tr>
    </table>
    <br/>
    <a href="Myeshop.jsp">Nhấn vào đây để mua thêm hàng</a>
   <%}
%>

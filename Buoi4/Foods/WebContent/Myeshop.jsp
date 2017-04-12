<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopping.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>My E-Shop</title>
        <script type="text/javascript">
            function kt(){
                var s=document.getElementById("sl").value;
                if(isNaN(s) || s<=0)
                    return false;
                return true;
            }
        </script>
    </head>
    <body>
        <h1>GIỎ HÀNG ONLINE - MVC</h1>
        <%
            DbActions db = new DbActions();
            ArrayList<Sanpham> dssp = db.getAllProducts();
        %>
        <form action="UrlFoodOnline" method="post">
            Hãy chọn món hàng cần mua:
            <select name="monhang">
                <%
            for (Sanpham sp : dssp) {%>
                <option value="<%=sp.getMsSP()%>"><%=sp.getTenSP()%></option>
                <%}%>
            </select>
            Số lượng<input name="soluong" value="0" id="sl"/>
            <input type="hidden" name="action" value="muahang"/>
            <input type="submit" value="Mua hàng" onclick="return kt()"/>
        </form>
        <br/>
        <jsp:include page="Myecart.jsp" flush="true"/>
    </body>
</html>

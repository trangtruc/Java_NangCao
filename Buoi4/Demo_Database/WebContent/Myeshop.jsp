<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlet.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>My E-Shop</title>
        <script type="text/javascript">
            function kt(){
                var s=document.getElementById("sl").value;
                if(isNaN(s) || s<=0 )
                    return false;
                return true;
            }
        </script>
    </head>
    <body>
        <h1>GIỎ HÀNG ONLINE</h1>
        <%
            DbActions db = new DbActions();
            ArrayList<Sanpham> dssp = db.getAllProducts();
        %>
        <form action="Access" method="post">
            <h3><i>Hãy chọn loại sách cần mua theo chủ đề:
            	<input type="radio" name="ra" value="title"/>Title
				<input type="radio" name="ra" value="author"/>Author
            </i></h3>
            
           <input type="text" name="monhang" placeholder="Nhập tên cần tìm kiếm" size="70" />
			
			</br>
			
            Số lượng: <input name="soluong" value="0" id="sl"/>
            <input type="hidden" name="action" value="muahang"/>
            <input type="submit" value="Mua hàng" onclick="return kt()"/>
           
           
        </form>
        <br/>
        <jsp:include page="Myecart.jsp" flush="true"/>
    </body>
</html>

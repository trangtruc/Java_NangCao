<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<h2 align="center">Tìm Kiếm Thông Tin Sách Theo Chủ Đề</h2>
	<form method="POST" action="Controller">
	<table align="center" border="1" >
		<tr> 
			<td> 
				<input type="radio" name="ra" value="title"/>Tựa sách
				<input type="radio" name="ra" value="author"/>Tác giả
			</td>
		</tr>
		<tr> 
			<td> 
				<input type="text" name="timkiem" placeholder="..."/>
			</td>
		</tr>
		<tr> 
			<td> 
				Số lượng: <input name="soluong" value="0" id="sl"/>
			    <input type="hidden" name="action" value="muahang"/>
                <input type="submit" value="Mua hàng" onclick="return kt()"/>
			</td>
		</tr>
		
	</table>
	</form>
	<jsp:include page="search.jsp" flush="true"/>
	
</body>
</html>
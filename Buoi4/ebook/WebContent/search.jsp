<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h2>Tim Kiem Thong Tin Sach</h2>
	<form method="POST" action="Url_Sach">
	<table>
		<tr> 
			<td> 
				<input type="radio" name="ra" value="title"/>Title
				<input type="radio" name="ra" value="author"/>Author
			</td>
		</tr>
		<tr> 
			<td> 
				<input type="text" name="timkiem" placeholder="..."/>
			</td>
		</tr>
		<tr> 
			<td> 
				<input type="submit" value="Search!" />
				
			</td>
		</tr>
		
	</table>
	</form>
</body>
</html>
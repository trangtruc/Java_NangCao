<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="include.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<div id="resultScript">
	
	</div>
	<script>
		var h = "<%= session.getAttribute("html") %>";
		var script = "<select>"
					+"<option>0</option>"
					+ h
					+"</select>";
		$("#resultScript").html(script);
	</script>  
</body>
</html>
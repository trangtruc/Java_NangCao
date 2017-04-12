<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TRỢ GIÚP</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div class="container ">
		<div class="row bg-content">
			<div  class="col-md-3">
				<jsp:include page="MenuLeft.jsp"></jsp:include>
			</div>
			<div class="col-md-6" style="border-radius: 5px !important;background: white;min-height:600px">
				<div class="row">
					<label class="title">Các câu hỏi thường gặp</label>
				</div>
				<div class="row faqs" id="faqs">
					<div class="toggle" id="f1" onclick="faqsToggle(this.id)">
						<div class="togglet">
							<i class="fa fa-question-circle"></i>
							Đăng ký CCCD bị sai thông tin khai sinh ?
						</div>
						<div class="togglec" style="font-size: 15px; display: none;width:100%">
							<p> 
							Nếu thông tin khai sinh không chính xác, bạn phải đăng ký sửa đổi giấy khai sinh. <br>
							Sau đó đăng ký căn cước công dân lại.	
							</p>
						</div>
					</div>
					<div class="toggle" id="f2" onclick="faqsToggle(this.id)">
						<div class="togglet">
							<i class="fa fa-question-circle"></i>
							Đã gửi đơn đăng ký CCCCD, nhưng phát hiện thông tin sai ?
						</div>
						<div class="togglec" style="font-size: 15px; display: none;width:100%">
							<p> 
							Có 2 trường hợp <br>
							- Trường hợp 1: Nếu sai thông tin khai sinh, bạn phải đăng ký sửa đổi thông tin giấy khai sinh và đăng ký căn cước công dân lại <BR>
							- Trường hợp 2: Nếu sai thông tin bổ sung, thông tin này có thể sửa khi đến cơ quan làm việc, vì vậy cứ đến cơ quan làm việc đúng ngày hẹn.
							</p>
						</div>
					</div>
					<div class="toggle" id="f3" onclick="faqsToggle(this.id)">
						<div class="togglet">
							<i class="fa fa-question-circle"></i>
							Làm thế nào để lưu thành file PDF ?
						</div>
						<div class="togglec" style="font-size: 15px; display: none;">
							<p> 
							- Đầu tiên bạn nhấn tổ hợp phím Ctrl + P <br>
							- Sau đó làm theo bức ảnh này. <br>
							<img alt="Lưu file PDF bước 1" src="<c:url value="/resources/image/luu-file-pdf-1.png"></c:url> " width=98%><br>
							<img alt="Lưu file PDF bước 1" src="<c:url value="/resources/image/luu-file-pdf-2.png"></c:url> " width=98%>
							</p>
						</div>
					</div>
				</div>
			</div >
			<div class="col-md-3 MenuRight" style="min-height: 400px;" >
				<jsp:include page="MenuRight.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
<script>
function faqsToggle(id){
	$("#"+id).children(".togglec").toggle("slow");
}
</script>
</html>
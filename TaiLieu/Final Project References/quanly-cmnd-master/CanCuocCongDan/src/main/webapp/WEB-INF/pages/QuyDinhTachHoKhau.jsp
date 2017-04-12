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
<title>Thủ tục tách sổ hộ khẩu</title>
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
				<div class="row ml10">
					<h4><b>Thủ tục tách sổ hộ khẩu</b></h4>
				</div>
				<div class="row">
					<ul> 
						<li id="cap-moi"><b>1. Điều kiện tách sổ hộ khẩu:</b>
							<ul>
								<li>Người có cùng chỗ ở hợp pháp có năng lực hành vi dân sự đầy đủ và có nhu cầu tách sổ hộ khẩu.</li>
								<li>Người đã nhập vào sổ hộ khẩu của người khác mà được chủ hộ đồng ý cho tách sổ hộ khẩu.</li>
							</ul>
						</li>
						<li id="cap-doi"><b>2. Hồ sơ tách sổ hộ khẩu:</b>
							<ul>
								<li>Sổ hộ khẩu;</li>								
							</ul>
						</li>
						<li id="cap-doi"><b>3. Nơi nộp hồ sơ tách sổ hộ khẩu:</b>
							<ul>
								<li>Đối với thành phố trực thuộc Trung ương thì nộp hồ sơ tại Công an huyện, quận, thị xã.</li>		
								<li>Đối với tỉnh thì nộp hồ sơ tại Công an xã, thị trấn thuộc huyện; Công an thị xã, thành phố thuộc tỉnh.</li>						
							</ul>
						</li>
						<li id="cap-doi"><b>4. Thời hạn giải quyết:</b>
							<ul>
								<li>Trong thời hạn bảy ngày làm việc, kể từ ngày nhận đủ hồ sơ, cơ quan có thẩm quyền phải trả kết quả giải quyết việc tách sổ hộ khẩu; trường hợp không giải quyết việc tách sổ hộ khẩu thì phải trả lời bằng văn bản và nêu rõ lý do.</li>						
							</ul>
						</li>
						<li id="cap-doi"><b>5. Lệ phí:</b>
							<ul>
								<li>Miễn thu lệ phí khi tách sổ hộ khẩu.</li>				
							</ul>
						</li>
					</ul>
				</div>
				<hr>
				<div class="row" id="quy-dinh">
					<jsp:include page="MenuQuyDinh.jsp"></jsp:include>
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
</html>
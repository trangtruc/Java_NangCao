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
<title>QUY ĐỊNH CẤP CĂN CƯỚC CÔNG DÂN</title>
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
					<h4><b>Quy định thủ tục cấp mới, đổi và cấp lại thẻ căn cước công dân.</b></h4>
				</div>
				<div class="row">
					<ul> 
						<li id="cap-moi"><b>Điều 21. Người được cấp mới thẻ Căn cước công dân</b>
							<ul>
								<li>Công dân Việt Nam hiện đang thường trú trên lãnh thổ Việt Nam được cấp thẻ Căn cước công dân.</li>
							</ul>
						</li>
						<li id="cap-doi"><b>Điều 22. Các trường hợp cấp đổi, cấp lại thẻ Căn cước công dân</b>
							<ul>
								<li>1. Người thuộc một trong các trường hợp sau đây thì được làm thủ tục đổi thẻ Căn cước công dân:
									<ul>
										<li>
											Thẻ Căn cước công dân hết hạn sử dụng theo quy định tại khoản 2 Điều 19 Luật này hoặc bị hư hỏng không sử dụng được;
										</li>
										<li>
											Thay đổi họ, tên, chữ đệm, ngày, tháng, năm sinh, nơi đăng ký thường trú;
										</li>
										<li>
											Thay đổi đặc điểm nhân dạng;
										</li>
										<li>
											Được xác định lại dân tộc, giới tính;
										</li>
										<li>
											Có sai sót về thông tin trên thẻ Căn cước công dân;
										</li>
										<li>
											Công dân có yêu cầu đổi thẻ Căn cước công dân.
										</li>
									</ul>
								</li>
								<li>Người thuộc một trong các trường hợp sau đây thì được làm thủ tục cấp lại thẻ Căn cước công dân:
									<ul>
										<li>
											Bị mất thẻ Căn cước công dân;
										</li>
										<li>
											Người Việt Nam đã được cấp thẻ Căn cước công dân sau đó ra nước ngoài định cư, nay trở về Việt Nam sinh sống và được đăng ký thường trú.
										</li>
									</ul>
								</li>
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
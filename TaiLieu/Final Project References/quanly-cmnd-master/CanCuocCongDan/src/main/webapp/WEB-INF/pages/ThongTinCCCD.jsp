<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<style>
table tr:hover {
    background-color: white;
}
table tr:nth-child(even):hover {
	background-color: white;
}
table td{
	padding:5px;
}
.select-control{
	width:160px;height: 34px;padding: 6px 12px;border-radius: 4px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
</div>
<div class="container">
	<div class="row bg-content">
		<div class="row text-center">
			<label class=title>thông tin số CMND/CCCD</label>
		</div>
		<div class="row" style="min-height:360px">
			<div class="col-md-2"></div>
			<div class="row">
				<div class="col-md-2 pull-left">
					<div class="radius-01 div-center" style="background:#fff;">
						<img src="hinh-the-cccd?id=${cccd.soCC}" alt="image" width="160" height="180"/>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row">
	                            <div class="radius-01 grid_45 div-center" style="background:#fff;">
	                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                    <tbody>
	                                    <tr>
	                                        <td width="24%" class="bordersv">
	                                            <div class="bg" align="left">
	                                               Số CCCD                                         </div>
	                                        </td>
	                                        <td width="76%" class="bordersv"><div class="bold" align="left">${cccd.soCC }</div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Họ tên</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${cccd.hoTen } </div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Ngày sinh</div></td>
	                                        <td class="bordersv">
	                                            <div class="bold" align="left">
	                                               ${cccd.ngaySinh }                                       </div>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Giới tính</div></td>
	                                        <td class="bordersv">
	                                            <div class="bold" align="left">
	                                                ${cccd.gioiTinh }                                            </div>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Dân tộc</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${danToc.tenDT}</div></td>
	                                    </tr>
	                                     <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Tôn giáo</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">
	                                        	<c:if test="${cccd.tonGiao != ''}">
	                                        		${cccd.tonGiao}
	                                        	</c:if>	
	                                        	<c:if test="${cccd.tonGiao == ''}">
	                                        		Không
	                                        	</c:if>	
	                                        </div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Quê quán</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${queQuan }</div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Hôn nhân</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${honNhan }</div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Ngày cấp</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${cccd.ngayCap }</div></td>
	                                    </tr>
	                                     <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Nơi cấp</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${noiCap.tenTinh }</div></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="bordersv"><div class="bg" align="left">Người cấp</div></td>
	                                        <td class="bordersv"><div class="bold" align="left">${cccd.nguoiDuyet }</div></td>
	                                    </tr>
	                                  
	                                </tbody></table>
	                            </div>
					</div>
					<div class="row mb10">
						<span class="pull-right">
							<button type=button onclick="window.close();" class="btn btn-primary mr80">Đóng</button>
						</span>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>
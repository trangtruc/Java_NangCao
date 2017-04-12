<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<title>DANH SÁCH ĐĂNG KÝ</title>
</head>
<body>
<div>
	<jsp:include page="header.jsp"></jsp:include>
</div>
<div class="container mt20" style="min-height:420px">
		<div class="row mt80">
			<div class="text-center col-md-12">
				<div class="text-orange title">
					XÁC MINH ĐƠN ĐĂNG KÝ
				</div>
			</div>
		</div>
		<div class="row mt40">
			<label class='col-md-1 mt5'>
				Tìm kiếm:
			</label>
			 
			<div class='col-md-3'>
				<input type=text name='inputTimKiem' id='inputTimKiem' class='form-control ml35' style="border-right:none;" onkeyup='timKiem();'/>
			</div>
			<div class='col-md-1'>
				<select name='optionTimKiem' id='optionTimKiem' class='form-control' style='border-left:none;width:140%;'>
					<option value="1">Mã số</option>
					<option value="2">Họ tên</option>
				</select>
			</div>
		</div>
		<div class="row mt20">
		    <div class="col-md-12">
			    <table border="1" class="thongtin">
		           
			            <tr>
			            	<th>Mã số</th>
			                <th style="width:10%;">Họ tên</th>
			                <th style="width:4%;">Giới tính</th>
			                <th>Ngày sinh</th>
			                <th>CMND/CCCD</th>
			                <th style="width:15%;">Quê quán</th>
			                <th>Dân Tộc</th>
			                <th style="width:6%;">Yêu cầu</th>
			                <th>Cập nhật</th>
			                <th style="width:5%;">Chi tiết</th>
			            </tr>
	            	<tbody id='ketQuaTimKiem'>
	                        ${dsLamCCCD}
	                    
	                </tbody>
	            </table>
			<!-- 			End table -->
		</div>
		</div>
</div>
<div class='container'>
			<!-- 			Page footer -->
			<jsp:include page="footer.jsp"></jsp:include>
			
			<!-- 			End page footer -->
		</div>
</body>
</html>
<style>
	td a{
		color:blue;
	}
</style>
<script>
	function timKiem(){
		var str = "str="+$("#inputTimKiem").val()+"_"+$("#optionTimKiem").val();
		$.ajax({url: 'tim-kiem-don-dang-ky',
			type: 'GET',
			data: str,
		  	success: function(result){
		  		$("#ketQuaTimKiem").html(result);
	        }});
	}
</script>
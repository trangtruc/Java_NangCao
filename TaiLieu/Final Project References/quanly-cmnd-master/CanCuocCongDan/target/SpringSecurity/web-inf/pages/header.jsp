<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
    String html = "";
 	String classIcon = "icon mr10 mt-5";
    html = "<img id='open-menu' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/menu-icon.png onclick=moMenu(); >"
           +"<img id='open-setting' class='icon mr10' height=30 width=30 aria-hidden='true' src=resources/image/user-icon.svg onclick=moCaiDat(); >"
           +"";
   String iconMenu1 = "<img id='open-menu-1' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/menu-icon.png >";
   String iconMenu2 = "<img id='open-menu-2' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/menu-icon.png >";
   String iconMenu3 = "<img id='open-menu-3' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/menu-icon.png >";
   String iconSetting1 = "<img id='open-setting-1' class='"+classIcon+"' height=30 width=30 src=resources/image/user-icon.svg >";
   String iconSetting2 = "<img id='open-setting-2' class='"+classIcon+"' height=30 width=30 src=resources/image/user-icon.svg >";
   String iconSetting3 = "<img id='open-setting-3' class='"+classIcon+"' height=30 width=30 src=resources/image/user-icon.svg >";
   String menu = "";
   String viTri = session.getAttribute("ssViTri")+ " - ";
   String setting = "";
   setting =  " <div id=menu class=mt20><ul>"
			+ "<li><a href=?action=service>"+viTri+session.getAttribute("ssHoTen")+"</a></li>"
			+ "<li><a href='' >Cài đặt tài khoản</a></li>"
			+ "<li><a href='' >Thông tin tài khoản</a></li>"
			+ "<li><a href=logout >Đăng xuất</a></li></ul>"
			+ "</div>";
%>
<sec:authorize access="hasRole('QUAN_TRI')">
	<%
  		menu =  " <div id=menu class=mt20><ul>"
			 + ""
			 + "<li><a href=danh-sach-khai-sinh >Danh sách khai sinh</a></li>"
			 + "<li><a href=danh-sach-dang-ky >Danh sách đăng ký làm CMT</a></li>"
			 + "<li><a href=duyet-danh-sach-lam-can-cuoc-cong-dan >Duyệt danh sách làm CMT</a></li>"
			 + "<li><a href=trang-nhap-giay-khai-sinh >Nhập khai sinh</a></li>"
			 + "<li><a href=nhap-can-cuoc-cong-dan >Nhập căn cước</a></li>"
			 + "<li><a href=nhap-ho-khau >Nhập hộ khẩu</a></li>"
			 + "</ul>"
			 + "</div>";
	%>
</sec:authorize>
<sec:authorize access="hasRole('CAN_BO')">
	<%
		menu =  " <div id=menu class=mt20><ul>"
			 + "<li><a href=?action=service>Trang chủ</a></li>"
			 + "<li><a href='danh-sach-dang-ky' >Duyệt đơn đăng ký</a></li>"
			 + "<li><a href=nhap-can-cuoc-cong-dan >Nhập căn cước</a></li>"
			 + "<li><a href='nhap-ho-khau' >Nhập hộ khẩu</a></li>"
			 + "<li><a href='trang-nhap-giay-khai-sinh'>Nhập khai sinh</a></li>"
			 + "<li><a href=?action=service&amp;6c5356e0582ecbb8976dee>2</a></li></ul>"
			 + "</div>";
	%>
</sec:authorize>
<sec:authorize access="hasRole('CONG_DAN')">
	<%
		iconMenu1 = iconMenu2 = iconMenu3 = "";
  		menu =  "";
	%>
</sec:authorize>
	<div class="header-user mb20">
        <div class="container">
        	<div class="row main-menu">
                <div class="col-md-5 left menu">
	                    <ul>
	                        <li><a href="http://localhost:8080/CanCuocCongDan/">Trang chủ</a></li>
	                        <li><a href="" >Trợ giúp</a></li>
	                        <li><a href="" >Tích hợp</a></li>
	                        <li><a href="" >Tin tức</a></li>
	                        <li><a href="" >Liên hệ</a></li>
	                    </ul>
                </div>
                <div class="col-md-7 right menu-user">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
         
                	<%
                				out.print(iconMenu1);
	                   			out.print(iconSetting1);
                				out.print("<div id='menu-user-1' style='display:none;'>"+menu+"</div>");
                				out.print("<div id='setting-user-1' style='display:none;'>"+setting+"</div>");
                    %>
                </c:if>
                 <c:if test="${pageContext.request.userPrincipal.name == null}">
                	<a href='login' >Đăng nhập</a>
                </c:if>
                 </div>
            </div>
            <div class="row icon-menu-user">
        		<div class="col-md-5 left">
        				<span class=pull-left>
		                    <ul>
		                        <li><a href="http://localhost:8080/CanCuocCongDan/">Trang chủ</a></li>
		                        <li><a href="" >Trợ giúp</a></li>
		                        <li><a href="" >Tích hợp</a></li>
		                        <li><a href="" >Tin tức</a></li>
		                        <li><a href="" >Liên hệ</a></li>
		                    </ul>
	                    </span>
	                    <span class=pull-right>
	                    	 	<c:if test="${pageContext.request.userPrincipal.name != null}">
				                	<%
				                				out.print(iconMenu2);
					                   			out.print(iconSetting2);
				                				out.print("<div id=menu-user-2 class='display-none'>"+menu+"</div>");
				                				out.print("<div id='setting-user-2' style='display:none;'>"+setting+"</div>");
				                    %>
				                   
                				</c:if>
                 				<c:if test="${pageContext.request.userPrincipal.name == null}">
                					<a href='login' >Đăng nhập</a>
                				</c:if>
	                    </span>
                </div>
        	</div>
        	<div class="row icon-menu">
        		<div class="col-sm-1 left">
						<img height=30 width=30 class="<%=classIcon %>" aria-hidden='true' src=resources/image/menu-icon.png onclick=moMenu(); >
               		<span class=pull-right>
	                    		<c:if test="${pageContext.request.userPrincipal.name != null}">
				                	<%
				                				out.print(iconMenu3);
					                   			out.print(iconSetting3);
				                				out.print("<div id=menu-user-3 class='display-none'>"+menu+"</div>");
				                				out.print("<div id='setting-user-3' style='display:none;'>"+setting+"</div>");
				                    %>
                				</c:if>
                 				<c:if test="${pageContext.request.userPrincipal.name == null}">
                					<a href='login' >Đăng nhập</a>
                				</c:if>
                	</span>
                </div>
        	</div>
        </div>
    </div>
    
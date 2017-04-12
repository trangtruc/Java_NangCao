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
//   String iconMenu1 = "<img id='open-menu-1' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/search-icon.png >";
//   String iconMenu2 = "<img id='open-menu-2' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/search-icon.png >";
//   String iconMenu3 = "<img id='open-menu-3' class='"+classIcon+"' height=30 width=30 aria-hidden='true' src=resources/image/search-icon.png >";
   String iconSetting1 = "<span class='mr10' style='color:#fff'><a href='thong-tin-tai-khoan'>"+session.getAttribute("ssHoTen")+"</a></span><span id='open-setting-1' style='cursor: pointer; color:#337ab7;' class='fa fa-chevron-down'></span>";
   String iconSetting2 = "<span class='mr10' style='color:#fff'><a href='thong-tin-tai-khoan'>"+session.getAttribute("ssHoTen")+"</a></span><span id='open-setting-2' style='cursor: pointer; color:#337ab7;' class='fa fa-chevron-down'></span>";
   String iconSetting3 = "<span class='mr10' style='color:#fff'><a href='thong-tin-tai-khoan'>"+session.getAttribute("ssHoTen")+"</a></span><span id='open-setting-3' style='cursor: pointer; color:#337ab7;' class='fa fa-chevron-down'></span>";
   String menu = "";
   String setting = "";
   setting =  " <div id=menu class=mt20><ul>";
   if(session.getAttribute("ssSoQuyen") != null){
	   if(Integer.parseInt(session.getAttribute("ssSoQuyen").toString()) > 1){
		   setting += "<li><a href=quan-tri >Quản lý <span class='fa fa-super-user'></span>";
		   if(((session.getAttribute("ssSoDonTTDKCCCDChoXacNhan") != null) && (Integer.parseInt(session.getAttribute("ssSoDonTTDKCCCDChoXacNhan").toString()) != 0)) || 
				   ((session.getAttribute("ssSoDonTTDKCCCDChoDuyet") != null) && (Integer.parseInt(session.getAttribute("ssSoDonTTDKCCCDChoDuyet").toString()) != 0)) ||
				   ((session.getAttribute("soDonDKKSChoXacNhan") != null) && (Integer.parseInt(session.getAttribute("soDonDKKSChoXacNhan").toString()) != 0)) ||
				   ((session.getAttribute("soDonDKKSChoDuyet") != null) && (Integer.parseInt(session.getAttribute("soDonDKKSChoDuyet").toString()) != 0)) ||
				   ((session.getAttribute("soDonThemNhanKhau") != null) && (Integer.parseInt(session.getAttribute("soDonThemNhanKhau").toString()) != 0)) ||
				   ((session.getAttribute("soDonLamHoKhau") != null) && (Integer.parseInt(session.getAttribute("soDonLamHoKhau").toString()) != 0))){
			   setting += "<sup><span class='message-menu fa fa-exclamation'></span></sup>";
		   }
		   setting+="</a></li>";
	   }
   }
	setting	+= "<li><a href='logout' >Đăng xuất <span class='fa fa-sign-out'></span></a></li></ul>"
			+ "</div>";
%>
	<div class="row" style="width:100%;">
		<img src=<c:url value="/resources/image/banner.png"></c:url> width=102.8% />
	</div>
	<div class="row" style="margin-bottom: -5px; ">
	    <marquee style="border-radius: 5px 5px 5px 5px !important;border-bottom: 1px solid;color:#e30000;background: #ffedb4;" scrollamount="4" direction="left" loop="50" scrolldelay="0" behavior="scroll" > 
	    	<label>Hệ Thống Đăng Ký Và Quản Lý Căn Cước Online </label>
		</marquee> 
	</div>
	<div class="row header-user mb5" style="border-radius: 5px 5px 5px 5px !important;">
        	<div class="row main-menu">
                <div class="col-md-5 left menu ml40">
	                    <ul>
	                        <li><a href="<c:url value="/"></c:url>"><i class="fa fa-home"></i></a></li>
	                        <li><a href="quy-dinh" >Quy định</a></li> 
	                        <li><a href="tro-giup" >Trợ giúp</a></li>   
	                        <li><a href="" >Tin tức</a></li>
	                    </ul>
                </div>
                <div class="col-md-6 right menu-user">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                	<%
                				//out.print(iconMenu1);
	                   			out.print(iconSetting1);
                				//out.print("<div id='menu-user-1' style='display:none;'>"+menu+"</div>");
                				out.print("<div id='setting-user-1' style='display:none;'>"+setting+"</div>");
                    %>
                </c:if>
                 <c:if test="${pageContext.request.userPrincipal.name == null}">
                	<a href='<c:url value="/login"></c:url>' >Đăng nhập <span class="fa fa-sign-in"></span></a>
                </c:if>
                 </div>
            </div>
            <div class="row icon-menu-user">
        		<div class="col-md-5 left ml40">
        				<span class=pull-left>
		                    <ul>
		                        <li><a href="<c:url value="/"></c:url>"><i class="fa fa-home"></i></a></li>
		                        <li><a href="quy-dinh" >Quy định</a></li> 
		                        <li><a href="tro-giup" >Trợ giúp</a></li>
		                        <li><a href="" >Tin tức</a></li>
		                    </ul>
	                    </span>
	                    <span class='pull-right mr15'>
	                    	 	<c:if test="${pageContext.request.userPrincipal.name != null}">
				                	<%
				                				//out.print(iconMenu2);
					                   			out.print(iconSetting2);
				                				//out.print("<div id=menu-user-2 class='display-none'>"+menu+"</div>");
				                				out.print("<div id='setting-user-2' style='display:none;'>"+setting+"</div>");
				                    %>
				                   
                				</c:if>
                 				<c:if test="${pageContext.request.userPrincipal.name == null}">
                					<a href='<c:url value="/login"></c:url>' >Đăng nhập <span class="fa fa-sign-in"></span></a>
                				</c:if>
	                    </span>
                </div>
        	</div>
        	<div class="row icon-menu">
        		<div class="col-sm-1 left ml5">
						<a onclick="moMenu()"><span class="ml10 fa fa-align-justify fa-2x"></span></a>
               		<span class=pull-right>
	                    		<c:if test="${pageContext.request.userPrincipal.name != null}">
				                	<%
				                				//out.print(iconMenu3);
					                   			out.print(iconSetting3);
				                				//out.print("<div id=menu-user-3 class='display-none'>"+menu+"</div>");
				                				out.print("<div id='setting-user-3' style='display:none;'>"+setting+"</div>");
				                    %>
                				</c:if>
                 				<c:if test="${pageContext.request.userPrincipal.name == null}">
                					<a href='<c:url value="/login"></c:url>' >Đăng nhập <span class="fa fa-sign-in"></span></a>
                				</c:if>
                	</span>
                </div>
        	</div>
      
    </div>
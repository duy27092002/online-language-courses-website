<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<%@ page import="com.javaproject.util.SecurityUtil" %>
<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<ul class="navbar-nav ml-auto">

		<div class="topbar-divider d-none d-sm-block"></div>

		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <span
				class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:authentication
						property="principal.username"></sec:authentication></span> <img
				class="img-profile rounded-circle"
				src="<%=SecurityUtil.getPrincipal().getAvatar()%>">
		</a>
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<a class="dropdown-item"
					href="/quan-tri/ho-so-cua-toi?id=<%=SecurityUtil.getPrincipal().getUserId()%>">
					<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Hồ sơ
					của tôi
				</a> <a class="dropdown-item" href="/quan-tri/doi-mat-khau"> <i
					class="fas fa-key fa-sm fa-fw mr-2 text-gray-400"></i> Đổi mật khẩu
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="/dang-xuat" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
					Đăng xuất
				</a>
			</div></li>

	</ul>

</nav>
<!-- End of Topbar -->
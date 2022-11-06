<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<%@ page import="com.javaproject.util.SecurityUtil"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>${viewTitle}</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Edukate" name="keywords">
<meta content="Edukate - online education website" name="description">

<!-- Favicon -->
<link href="/image-file/${aboutDetails.favicon}" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css?family=Trirong"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/web/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="/web/css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Topbar Start -->
	<div class="container-fluid bg-dark">
		<div class="row py-2 px-lg-5">
			<div class="col-lg-6 text-center text-lg-left mb-2 mb-lg-0">
				<div class="d-inline-flex align-items-center text-white">
					<small><i class="fa fa-phone-alt mr-2"></i>${aboutDetails.phoneNumber}</small>
					<small class="px-3">|</small> <small><i
						class="fa fa-envelope mr-2"></i>${aboutDetails.email}</small>
				</div>
			</div>
			<div class="col-lg-6 text-center text-lg-right">
				<div class="d-inline-flex align-items-center">
					<a class="text-white px-2" href="${aboutDetails.facebookLink}">
						<i class="fab fa-facebook-f"></i>
					</a> <a class="text-white px-2" href="${aboutDetails.twitterLink}">
						<i class="fab fa-twitter"></i>
					</a> <a class="text-white px-2" href="${aboutDetails.inLink}"> <i
						class="fab fa-linkedin-in"></i>
					</a> <a class="text-white px-2" href="${aboutDetails.instagramLink}">
						<i class="fab fa-instagram"></i>
					</a> <a class="text-white pl-2" href="${aboutDetails.youtubeLink}">
						<i class="fab fa-youtube"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Topbar End -->


	<!-- Navbar Start -->
	<div class="container-fluid p-0">
		<nav
			class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0 px-lg-5">
			<a href="/trang-chu" class="navbar-brand ml-lg-3">
				<h1 class="m-0 text-uppercase text-primary">
					<img src="/image-file/${aboutDetails.logo}" class="mr-3"
						style="width: 45px; border-radius: 50%; margin-bottom: 0.25em;" />${aboutDetails.name}
				</h1>
			</a>
			<button type="button" class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between px-lg-3"
				id="navbarCollapse">
				<div class="navbar-nav mx-auto py-0">
					<a href="/trang-chu" class="nav-item nav-link ${activePage}">Trang
						chủ</a> <a href="/ve-chung-toi"
						class="nav-item nav-link ${activePage}">Chúng tôi</a> <a
						href="/danh-sach-khoa-hoc" class="nav-item nav-link ${activePage}">Khóa
						học</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-link ${activePage} dropdown-toggle"
							data-toggle="dropdown">Khác</a>
						<div class="dropdown-menu m-0">
							<a href="/danh-sach-giang-vien" class="dropdown-item">Giảng
								viên</a> <a href="/danh-gia-cua-hoc-vien" class="dropdown-item">Đánh
								giá của học viên</a>
						</div>
					</div>
					<a href="/lien-he" class="nav-item nav-link ${activePage}">Liên
						hệ</a>
				</div>
				<sec:authorize access="!isAuthenticated()">
					<a href="/dang-nhap"
						class="btn btn-primary py-2 px-4 d-none d-lg-block">Tham gia
						với chúng tôi</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:authentication
										property="principal.username"></sec:authentication></span> <img
								class="img-profile rounded-circle"
								src="<%=SecurityUtil.getPrincipal().getAvatar()%>"
								style="width: 30px;">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item"
									href="/ho-so-cua-toi?id=<%=SecurityUtil.getPrincipal().getUserId()%>">
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Hồ
									sơ của tôi
								</a> <a class="dropdown-item" href="/doi-mat-khau"> <i
									class="fas fa-key fa-sm fa-fw mr-2 text-gray-400"></i> Đổi mật
									khẩu
								</a> <a class="dropdown-item"
									href="/khoa-hoc-cua-toi?id=<%=SecurityUtil.getPrincipal().getUserId()%>">
									<i class="fas fa-book-open fa-sm fa-fw mr-2 text-gray-400"></i>
									Khóa học của tôi
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/dang-xuat"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Đăng xuất
								</a>
							</div></li>
					</ul>
				</sec:authorize>
			</div>
		</nav>
	</div>
	<!-- Navbar End -->


	<!-- Header Start -->
	<div class="jumbotron jumbotron-fluid position-relative overlay-bottom"
		style="margin-bottom: 90px;">
		<div class="container text-center my-5 py-5">
			<h1 class="text-white mt-4 mb-4">Learn From Home</h1>
			<h1 class="text-white display-1 mb-5">Language Courses</h1>
			<div class="mx-auto mb-5" style="width: 100%; max-width: 600px;">
				<form action="/danh-sach-khoa-hoc" method="get">
					<div class="input-group">
						<input type="text" maxlength="50" name="keyword"
							class="form-control border-light" style="padding: 30px 25px;"
							placeholder="Tên khóa học">
						<div class="input-group-append">
							<button type="submit" class="btn btn-secondary px-4 px-lg-5">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Header End -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Tổng quan</title>

<!-- Custom fonts for this template-->
<link href="/admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/admin/css/sb-admin-2.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/quan-tri">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fa fa-book-reader"></i>
				</div>
				<div class="sidebar-brand-text mr-3">Quản lý Website</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="/quan-tri">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>Tổng quan</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Danh mục</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="/quan-tri/thong-tin-website"> <i
					class="fas fa-fw fa-table"></i> <span>Thông tin website</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="/quan-tri/vai-tro"> <i class="fas fa-users-cog"></i> <span>Vai
						trò</span>
			</a></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-users"></i> <span>Thành viên</span>
			</a>
				<div id="collapseUtilities" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/quan-tri/danh-sach-admin">Admin</a>
						<a class="collapse-item" href="/quan-tri/danh-sach-giang-vien">Giảng
							viên</a> <a class="collapse-item" href="/quan-tri/danh-sach-hoc-vien">Học
							viên</a>
					</div>
				</div></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-book-open"></i> <span>Khóa học</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/quan-tri/danh-sach-ngon-ngu">Ngôn
							ngữ</a> <a class="collapse-item" href="/quan-tri/danh-sach-khoa-hoc">Khóa
							học</a> <a class="collapse-item" href="/quan-tri/binh-luan">Bình
							luận</a> <a class="collapse-item"
							href="/quan-tri/danh-gia-cua-hoc-vien">Đánh giá</a>
					</div>
				</div></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="/quan-tri/cau-hoi-thuong-gap"> <i
					class="fas fa-question-circle"></i> <span>Câu hỏi thường gặp</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lỗi</title>
<link href="/web/img/edukate_logo.ico" rel="icon">
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
		<div class="container-fluid">

			<!-- 404 Error Text -->
			<div class="text-center">
				<div class="error mx-auto" data-text="404">404</div>
				<p class="lead text-gray-800 mb-5">Không tìm thấy dữ liệu</p>
				<a href="${returnPageUrl}">&larr; Quay lại trang ${returnPage}</a>
			</div>

		</div>
		<%@include file="/WEB-INF/views/common/pagination.jsp"%>
	</div>
	<!-- End of Main Content -->

	<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
</body>
</html>
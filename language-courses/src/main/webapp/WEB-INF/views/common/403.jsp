<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lỗi</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
		<div class="container-fluid">

			<div class="text-center">
				<div class="error mx-auto" data-text="403">403</div>
				<p class="lead text-gray-800 mb-5">Bạn không được phép truy cập
					vào trang này!</p>
			</div>

		</div>
	</div>
	<!-- End of Main Content -->

	<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
</body>
</html>
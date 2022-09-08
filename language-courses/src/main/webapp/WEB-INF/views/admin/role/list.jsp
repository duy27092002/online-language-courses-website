<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách vai trò</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<a class="btn btn-sm btn-primary mb-4"
				href="/quan-tri/vai-tro/them-moi"><i class="fas fa-plus mr-2"></i>Thêm
				mới</a>

			<div class="table-responsive">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tên vai trò</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Admin</td>
							<td><button disabled class="btn btn-sm btn-outline-success">Hoạt
									động</button></td>
							<td><a href="/quan-tri/chinh-sua-vai-tro?id="><i
									class="fas fa-edit" data-toggle="tooltip" title="Chỉnh sửa"></i></a></td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Giảng viên</td>
							<td><button disabled class="btn btn-sm btn-outline-success">Hoạt
									động</button></td>
							<td><i class="fas fa-edit edit-role" data-toggle="modal"
								data-target="#editRole_${test2}" style="cursor: pointer;"
								data-roleId="${test2}" id="edit_${test2}"></i></td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td>Học viên</td>
							<td><button disabled class="btn btn-sm btn-outline-danger">Đã
									hủy</button></td>
							<td><i class="fas fa-edit"></i></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- /.container-fluid -->
		<%@include file="/WEB-INF/views/common/pagination.jsp"%>
	</div>
	<!-- End of Main Content -->

	<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
</body>
</html>
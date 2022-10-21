<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Admin - Giảng viên - Học viên</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<button type="button" class="btn btn-sm btn-primary mb-4"
				data-toggle="modal" data-target="#createNewUser">
				<i class="fas fa-plus mr-3"></i>Thêm mới
			</button>

			<!-- start create modal -->
			<div class="modal fade" id="createNewUser" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Thêm loại
								ngôn ngữ</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label class="col-form-label">Tên:</label> <input type="text"
										class="form-control" id="name" name="name"> <input
										type="hidden" value="1" id="status" name="status">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-sm btn-secondary"
								data-dismiss="modal">Đóng</button>
							<button type="button" class="btn btn-sm btn-primary">Tạo</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end create modal -->

			<div class="table-responsive">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Avatar</th>
							<th scope="col">Tên</th>
							<th scope="col">Giới tính</th>
							<th scope="col">SĐT</th>
							<th scope="col">Email</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Avatar</td>
							<td>Nguyễn Văn A</td>
							<td>Name</td>
							<td>0123456789</td>
							<td>nguyenvana@gmail.com</td>
							<td><button disabled class="btn btn-sm btn-outline-success">Hoạt
									động</button></td>
							<td><i class="fas fa-edit edit-user" data-toggle="modal"
								data-target="#editUser-userId" style="cursor: pointer;"
								data-userId="${test}" data-toggle="tooltip" title="Chỉnh sửa"></i>
								<i class="fas fa-eye user-details ml-3" data-toggle="modal"
								data-target="#userDetails-userId" style="cursor: pointer;"
								data-userId="${test}" data-toggle="tooltip" title="Chi tiết"></i></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="modal fade" id="editLanguage-languageId" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Chỉnh sửa</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label class="col-form-label">Tên:</label> <input type="text"
									class="form-control" id="name" name="name">
							</div>
							<div class="form-group">
								<label class="col-form-label">Trạng thái:</label> <select
									id="status" name="status" class="form-control">
									<option selected value="1">Hoạt động</option>
									<option value="0">Hủy</option>
								</select>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-secondary"
							data-dismiss="modal">Đóng</button>
						<button type="button" class="btn btn-sm btn-primary">Cập
							nhật</button>
					</div>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
		<%@include file="/WEB-INF/views/common/pagination.jsp"%>
	</div>
	<!-- End of Main Content -->

	<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
	<script src="/admin/js/edit-role.js"></script>
</body>
</html>
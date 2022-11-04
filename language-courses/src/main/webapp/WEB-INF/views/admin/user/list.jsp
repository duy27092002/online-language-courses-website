<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<div class="container-fluid">
		<h1 class="text-center">Danh sách người dùng</h1>
		<c:if test="${mess != null}">
			<div class="alert alert-${typeAlert} alert-dismissible fade show"
				role="alert">
				<strong>${mess}</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<div class="row">
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<a class="btn btn-sm btn-primary mb-4"
					href="/quan-tri/nguoi-dung/them-moi"><i
					class="fas fa-plus mr-2"></i>Thêm mới</a>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<form action="/quan-tri/nguoi-dung/danh-sach" method="get"
					class="float-sm-left float-md-right float-lg-right form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
					<div class="input-group">
						<input name="keyword" type="text"
							class="form-control bg-light small" placeholder="Nhập từ khóa"
							value="${keyword}">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<small class="text-muted">(Ấn vào biểu tượng để thay đổi trạng
			thái)</small>
		<div class="card o-hidden border-0 shadow-lg mb-4 mt-3">
			<div class="card-body p-5">
				<div class="table-responsive">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<c:if test="${resultList.keyword == null}">
									<c:set var="pagingParam"
										value="orderBy=name&orderType=${orderType}"></c:set>
								</c:if>
								<c:if test="${resultList.keyword != null}">
									<c:set var="pagingParam"
										value="keyword=${resultList.keyword}&orderBy=name&orderType=${orderType}"></c:set>
								</c:if>
								<th scope="col">Avatar</th>
								<th scope="col"><a
									href="?page=${resultList.page}&${pagingParam}"
									class="text-body" style="text-decoration: none;">Tên <i
										class="fas fa-arrows-alt-v ml-1"></i>
								</a></th>
								<th scope="col">Giới tính</th>
								<th scope="col">SĐT</th>
								<th scope="col">Email</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList.data}" var="user">
								<tr>
									<td><img class="border rounded-circle img-fluid"
										style="width: 60px;" src="${user.avatar}" alt="avatar" /></td>
									<td>${user.name}</td>
									<td><c:if test="${user.gender == 1}">Nam</c:if> <c:if
											test="${user.gender == 0}">Nữ</c:if> <c:if
											test="${user.gender == 2}">Khác</c:if></td>
									<td>${user.phoneNumber}</td>
									<td>${user.email}</td>
									<td><c:if test="${user.status == 1 }">
											<c:set var="textColor" value="success"></c:set>
											<c:set var="tooltipTitle" value="Đang hoạt động"></c:set>
											<c:set var="toggleStatus" value="on"></c:set>
										</c:if> <c:if test="${user.status == 0 }">
											<c:set var="textColor" value="danger"></c:set>
											<c:set var="tooltipTitle" value="Đã hủy"></c:set>
											<c:set var="toggleStatus" value="off"></c:set>
										</c:if>
										<form action="/quan-tri/nguoi-dung/cap-nhat-trang-thai"
											method="get">
											<input name="id" type="hidden" value="${user.id}" />
											<button type="submit" class="btn btn-sm border-0">
												<i class="fas fa-toggle-${toggleStatus} text-${textColor}"
													data-toggle="tooltip" title="${tooltipTitle}"></i>
											</button>
										</form></td>
									<td><button class="btn btn-sm border-primary">
											<a href="/quan-tri/nguoi-dung/chi-tiet-ho-so?id=${user.id}"><i
												class="fas fa-eye" data-toggle="tooltip" title="Xem hồ sơ"></i></a>
											| <a
												href="/quan-tri/nguoi-dung/chinh-sua-ho-so?id=${user.id}"><i
												class="fas fa-edit" data-toggle="tooltip" title="Chỉnh sửa"></i></a>
										</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/common/pagination.jsp"%>
</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
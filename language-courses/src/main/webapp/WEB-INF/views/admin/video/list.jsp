<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<div class="container-fluid">
		<h1 class="text-center">Danh sách video bài giảng</h1>
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
				<c:if test="${!role.contains('giang-vien')}">
					<c:set var="url" value="/quan-tri/khoa-hoc/danh-sach"></c:set>
				</c:if>
				<c:if test="${role.contains('giang-vien')}">
					<c:set var="url"
						value="/quan-tri/khoa-hoc-cua-toi?id=${instructorId}"></c:set>
				</c:if>
				<a class="btn btn-sm btn-primary mb-4"
					href="/quan-tri/video/them-moi?id=${courseId}"><i
					class="fas fa-plus mr-2"></i>Thêm mới</a> <a
					class="btn btn-sm btn-secondary mb-4" href="${url}">Quay lại</a>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<form action="/quan-tri/khoa-hoc/danh-sach-video" method="get"
					class="float-sm-left float-md-right float-lg-right form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
					<div class="input-group">
						<input name="id" type="hidden" value="${courseId}" /> <input
							name="keyword" type="text" class="form-control bg-light small"
							placeholder="Nhập từ khóa" maxlength="50" value="${keyword}">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="card o-hidden border-0 shadow-lg mb-4 mt-3">
			<div class="card-body p-5">
				<div class="table-responsive">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<th scope="col">Thumbnail</th>
								<th scope="col">Thứ tự</th>
								<th scope="col">Tên video bài giảng</th>
								<th scope="col">Giảng viên</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList.data}" var="vid">
								<tr>
									<td><img class="border img-fluid" style="width: 60px;"
										src="${vid.thumbnail}" alt="Thumbnail" /></td>
									<td>${vid.index_}</td>
									<td class="text-truncate" style="max-width: 150px;">${vid.name}</td>
									<td>${vid.user.email}</td>
									<td><c:if test="${vid.status == 2 }">
											<c:set var="textColor" value="success"></c:set>
											<c:set var="tooltipTitle" value="Đạt yêu cầu"></c:set>
											<c:set var="toggleStatus" value="on"></c:set>
										</c:if> <c:if test="${vid.status == 1 }">
											<c:set var="textColor" value="warning"></c:set>
											<c:set var="tooltipTitle" value="Đang kiểm duyệt"></c:set>
											<c:set var="toggleStatus" value="off"></c:set>
										</c:if> <c:if test="${vid.status == 0 }">
											<c:set var="textColor" value="danger"></c:set>
											<c:set var="tooltipTitle" value="Không đạt yêu cầu"></c:set>
											<c:set var="toggleStatus" value="off"></c:set>
										</c:if> <i class="fas fa-toggle-${toggleStatus} text-${textColor}"
										data-toggle="tooltip" title="${tooltipTitle}"></i></td>
									<td><button class="btn btn-sm border-primary">
											<a href="/quan-tri/video/chi-tiet?id=${vid.id}"><i
												class="fas fa-eye" data-toggle="tooltip"
												title="Xem chi tiết"></i></a> | <a
												href="/quan-tri/video/chinh-sua?id=${vid.id}"><i
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
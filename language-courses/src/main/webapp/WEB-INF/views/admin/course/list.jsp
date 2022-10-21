<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<div class="container-fluid">
		<h1 class="text-center">Danh sách khóa học</h1>
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
								<th scope="col">Ảnh đại diện</th>
								<th scope="col"><a
									href="?page=${resultList.page}&${pagingParam}"
									class="text-body" style="text-decoration: none;">Tên khóa
										học <i class="fas fa-arrows-alt-v ml-1"></i>
								</a></th>
								<th scope="col">Ngôn ngữ</th>
								<th scope="col">Giá</th>
								<th scope="col">Thời gian phát hành</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList.data}" var="course">
								<tr>
									<td><img class="border img-fluid" style="width: 70px;"
										src="${course.thumbnail}" alt="thumbnail" /></td>

									<td>${course.name}</td>
									<td>Ngôn ngữ</td>
									<td>${course.price}</td>
									<td><c:if test="${course.releaseTime == null}">
											<c:out value="Chưa có" />
										</c:if></td>
									<td><c:if test="${course.status == 1 }">
											<c:set var="textColor" value="warning"></c:set>
											<c:set var="tooltipTitle" value="Đang kiểm duyệt"></c:set>
											<c:set var="toggleStatus" value="off"></c:set>
										</c:if> <c:if test="${course.status == 0 }">
											<c:set var="textColor" value="danger"></c:set>
											<c:set var="tooltipTitle" value="Đã hủy"></c:set>
											<c:set var="toggleStatus" value="off"></c:set>
										</c:if> <c:if test="${course.status == 2 }">
											<c:set var="textColor" value="success"></c:set>
											<c:set var="tooltipTitle" value="Đang hoạt động"></c:set>
											<c:set var="toggleStatus" value="on"></c:set>
										</c:if> <i class="fas fa-toggle-${toggleStatus} text-${textColor}"
										data-toggle="tooltip" title="${tooltipTitle}"></i></td>
									<td><button class="btn btn-sm border-primary">
											<a href="/quan-tri/khoa-hoc/chi-tiet?id=${course.id}"><i
												class="fas fa-eye" data-toggle="tooltip"
												title="Xem chi tiết"></i></a> | <a
												href="/quan-tri/khoa-hoc/chinh-sua?id=${course.id}"><i
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
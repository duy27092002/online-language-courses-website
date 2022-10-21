<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Danh sách câu hỏi</h1>
		<c:if test="${mess != null}">
			<div class="alert alert-${typeAlert} alert-dismissible fade show"
				language="alert">
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
					href="/quan-tri/cau-hoi-thuong-gap/them-moi"><i
					class="fas fa-plus mr-2"></i>Thêm mới</a>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<form action="/quan-tri/cau-hoi-thuong-gap/danh-sach" method="get"
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
										value="orderBy=question&orderType=${orderType}"></c:set>
								</c:if>
								<c:if test="${resultList.keyword != null}">
									<c:set var="pagingParam"
										value="keyword=${resultList.keyword}&orderBy=question&orderType=${orderType}"></c:set>
								</c:if>
								<th scope="col"><a
									href="?page=${resultList.page}&${pagingParam}"
									class="text-body" style="text-decoration: none;">Câu hỏi <i
										class="fas fa-arrows-alt-v ml-1"></i>
								</a></th>
								<th scope="col">Câu trả lời</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList.data}" var="faq">
								<tr>
									<td>${faq.question}</td>
									<td>${faq.answer}</td>
									<td><c:if test="${faq.status == 1 }">
											<i class="fas fa-toggle-on text-success"
												data-toggle="tooltip" title="Đang hiển thị"></i>
										</c:if> <c:if test="${faq.status == 0 }">
											<i class="fas fa-toggle-off text-danger"
												data-toggle="tooltip" title="Đã ẩn"></i>
										</c:if>
									<td>
										<button class="btn btn-sm border-primary">
											<a href="/quan-tri/cau-hoi-thuong-gap/chi-tiet?id=${faq.id}"><i
												class="fas fa-eye" data-toggle="tooltip"
												title="Xem chi tiết"></i></a> | <a
												href="/quan-tri/cau-hoi-thuong-gap/chinh-sua?id=${faq.id}"><i
												class="fas fa-edit" data-toggle="tooltip" title="Chỉnh sửa"></i></a>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
	<%@include file="/WEB-INF/views/common/pagination.jsp"%>
</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
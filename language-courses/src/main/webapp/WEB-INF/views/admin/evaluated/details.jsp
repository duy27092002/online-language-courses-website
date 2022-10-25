<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Chi tiết đánh giá của học viên</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Email học viên:</label> <input
											class="form-control form-control-user bg-white"
											value="${evaluatedDetails.user.email}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Tên khóa học:</label> <input
											class="form-control form-control-user bg-white"
											value="${evaluatedDetails.course.name}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Nội dung đánh giá:</label>
										<div class="element-scroll">${evaluatedDetails.content}
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Điểm đánh giá:</label> <input
											class="form-control form-control-user bg-white"
											value="${evaluatedDetails.point}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group">
									<c:if test="${evaluatedDetails.status == 1 }">
										<c:set var="status" value="Đang hiển thị"></c:set>
									</c:if>
									<c:if test="${evaluatedDetails.status == 1 }">
										<c:set var="status" value="Đang kiểm duyệt"></c:set>
									</c:if>
									<c:if test="${evaluatedDetails.status == 0 }">
										<c:set var="status" value="Đã ẩn"></c:set>
									</c:if>
									<label>Trạng thái:</label> <input
										class="form-control form-control-user bg-white"
										value="${status}" disabled="disabled" />
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${evaluatedDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${evaluatedDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a
											href="/quan-tri/danh-gia-cua-hoc-vien/chinh-sua?id=${evaluatedDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/danh-gia-cua-hoc-vien/danh-sach"
											class="btn btn-secondary btn-user btn-block mt-4">Quay
											lại danh sách</a>
									</div>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
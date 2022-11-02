<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết khóa học</h1>
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
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group mb-3">
									<p class="text-center">
										<img class="border img-fluid" style="width: 200px;"
											src="${courseDetails.thumbnail}" alt="thumbnail" />
									</p>
								</div>
								<div class="form-group">
									<label>Tên khóa học</label> <input
										class="form-control form-control-user bg-white"
										value="${courseDetails.name}" disabled="disabled" />
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Ngôn ngữ</label> <input
											class="form-control form-control-user bg-white"
											value="${courseDetails.language.name}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Giá gốc (VND)</label> <input
											class="form-control form-control-user bg-white"
											value="${courseDetails.price}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<textarea id="description" disabled="disabled" rows="5">${courseDetails.description}</textarea>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
										<label>Giảm giá (%)</label> <input
											class="form-control form-control-user bg-white"
											value="${courseDetails.discount}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<fmt:formatDate value="${courseDetails.startDiscountTime}"
											pattern="HH:mm:ss dd/MM/yyyy" var="startDiscountTime" />
										<label>Ngày bắt đầu giảm giá</label> <input
											class="form-control form-control-user bg-white"
											value="${startDiscountTime}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<fmt:formatDate value="${courseDetails.endDiscountTime}"
											pattern="HH:mm:ss dd/MM/yyyy" var="endDiscountTime" />
										<label>Ngày kết thúc giảm giá</label> <input
											class="form-control form-control-user bg-white"
											value="${endDiscountTime}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Danh sách kỹ năng đạt được sau khóa học</label> <select
											multiple class="form-control bg-white"
											style="border-radius: 20px;" disabled="disabled">
											<c:forEach var="skl" items="${courseDetails.skillLevelList}">
												<option>${skl.name}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Danh sách giảng viên phụ trách</label> <select
											multiple class="form-control bg-white"
											style="border-radius: 20px;" disabled="disabled">
											<c:forEach var="instructor"
												items="${courseDetails.instructors}">
												<option>${instructor.email}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${courseDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${courseDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/khoa-hoc/chinh-sua?id=${courseDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/khoa-hoc/danh-sach"
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
<script src="/lib/ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('description');
	});
</script>
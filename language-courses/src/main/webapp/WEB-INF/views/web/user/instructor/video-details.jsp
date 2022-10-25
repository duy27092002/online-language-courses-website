<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết video</h1>
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
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Thumbnail:</label><br> <img
											class="border img-fluid" style="width: 200px;"
											src="${videoDetails.thumbnail}" alt="avatar" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Video:</label><br>
										<video controls style="height: 200px;">
											<source src="${videoDetails.videoFile}" type="video/mp4">
										</video>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Thứ tự:</label> <input
											class="form-control form-control-user bg-white"
											value="${videoDetails.index_}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Tên video bài giảng:</label> <input
											class="form-control form-control-user bg-white"
											value="${videoDetails.name}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Tên khóa học:</label> <input
											class="form-control form-control-user bg-white"
											value="${videoDetails.course.name}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giảng viên đăng video:</label> <input
											class="form-control form-control-user bg-white"
											value="${videoDetails.user.email}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group">
									<label>Mô tả:</label>
									<textarea id="description" disabled="disabled" rows="5">${videoDetails.description}</textarea>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Nhận xét của admin:</label>
										<textarea class="form-control bg-white"
											style="border-radius: 20px;" disabled="disabled" rows="3">${videoDetails.cmt}</textarea>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Trạng thái:</label> <input
											class="form-control form-control-user bg-white"
											value="${videoDetails.status == 1 ? 'Đang kiểm duyệt' : videoDetails.status == 2 ? 'Đạt yêu cầu' : 'Không đạt yêu cầu'}"
											disabled="disabled" />
									</div>
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${videoDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${videoDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/video/chinh-sua?id=${videoDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a
											href="/quan-tri/khoa-hoc/danh-sach-video?id=${videoDetails.course.id}"
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
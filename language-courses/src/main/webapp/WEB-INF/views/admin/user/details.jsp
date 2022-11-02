<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết hồ sơ</h1>
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
										<img class="border rounded-circle img-fluid"
											style="width: 200px;" src="${userDetails.avatar}"
											alt="avatar" />
									</p>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Tên</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.name}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Tên tài khoản</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.userName}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Ngày sinh</label> <input
											class="form-control form-control-user bg-white"
											value="<fmt:formatDate
										value="${userDetails.dobDate}"
										pattern="dd/MM/yyyy" />"
											disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giới tính</label>
										<c:if test="${userDetails.gender == 0}">
											<c:set var="gender" value="Nữ"></c:set>
										</c:if>
										<c:if test="${userDetails.gender == 1}">
											<c:set var="gender" value="Nam"></c:set>
										</c:if>
										<c:if test="${userDetails.gender == 2}">
											<c:set var="gender" value="Khác"></c:set>
										</c:if>
										<input class="form-control form-control-user bg-white"
											value="${gender}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Email</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.email}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Số điện thoại</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.phoneNumber}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Youtube</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.youtubeLink}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Facebook</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.facebookLink}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
										<label>Instagram</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.instagramLink}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>Twitter</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.twitterLink}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>LinkedIn</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.inLink}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Vai trò</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.roleName}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Trạng thái</label> <input
											class="form-control form-control-user bg-white"
											value="${userDetails.status == 1 ? 'Đang hoạt động' : 'Đã hủy'}"
											disabled="disabled" />
									</div>
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${userDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${userDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<c:if test="${enableEditAction == true}">
									<div class="form-group">
										<a href="/quan-tri/chinh-sua-ho-so?id=${userDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
									</div>
								</c:if>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->>
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
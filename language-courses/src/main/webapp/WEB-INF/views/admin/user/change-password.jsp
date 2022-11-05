<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Đổi mật khẩu</h1>
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<strong>${message}</strong>
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
							<f:form action="/quan-tri/doi-mat-khau" cssClass="user"
								modelAttribute="changePasswordDTO" method="post">
								<div class="form-group row">
									<label>Mật khẩu cũ <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control form-control-user bg-white"
										path="oldPass" type="password" />
									<small><f:errors path="oldPass" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<label>Mật khẩu mới <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control form-control-user bg-white"
										path="newPass" type="password" />
									<small><f:errors path="newPass" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<label>Nhập lại mật khẩu mới <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control form-control-user bg-white"
										path="reNewPass" type="password" />
								</div>
								<div class="form-group">
									<button class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
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
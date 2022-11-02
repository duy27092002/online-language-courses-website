<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<div class="container-fluid">
	<div class="container">
		<h1 class="text-center text-uppercase">Đổi mật khẩu</h1>
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
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form action="/doi-mat-khau" cssClass="user"
								modelAttribute="changePasswordDTO" method="post">
								<div class="form-group row">
									<label>Mật khẩu cũ <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control rounded-pill bg-white"
										path="oldPass" type="password" />
									<small><f:errors path="oldPass" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<label>Mật khẩu mới <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control rounded-pill bg-white"
										path="newPass" type="password" />
									<small><f:errors path="newPass" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<label>Nhập lại mật khẩu mới <span class="text-danger">*</span>
									</label>
									<f:input cssClass="form-control rounded-pill bg-white"
										path="reNewPass" type="password" />
								</div>
								<div class="form-group row">
									<button class="btn btn-primary rounded-pill btn-block mt-4">Lưu
										thay đổi</button>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
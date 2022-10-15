<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Đăng ký</title>
</head>

<body>

	<%@include file="/WEB-INF/views/common/form/header-of-form.jsp"%>

	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="row">
				<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
				<div class="col-lg-7">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Tạo tài khoản!</h1>
							<c:if test="${errorMess != null}">
								<div class="alert alert-danger rounded-pill">
									<c:out value="${errorMess}" />
								</div>
							</c:if>
						</div>
						<f:form action="/dang-ky" method="post" cssClass="user"
							modelAttribute="userDTO">
							<div class="form-group">
								<f:input path="name" type="text"
									cssClass="form-control form-control-user"
									placeholder="Họ và tên" />
								<small><f:errors path="name" style="color:red;"></f:errors></small>
								<f:input path="status" value="1" type="hidden" />
								<f:input path="roleId" value="3" type="hidden" />
								<f:input path="avatar" type="hidden" />
								<f:input path="twitterLink" type="hidden" />
								<f:input path="facebookLink" type="hidden" />
								<f:input path="instagramLink" type="hidden" />
								<f:input path="youtubeLink" type="hidden" />
								<f:input path="inLink" type="hidden" />
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<label>Ngày sinh:</label>
									<f:input path="dob" type="date"
										cssClass="form-control form-control-user" />
									<small><f:errors path="dob" style="color:red;"></f:errors></small>
								</div>
								<div class="col-sm-6">
									<label>Giới tính:</label><br>
									<div class="form-check form-check-inline">
										<f:radiobutton cssClass="form-check-input" path="gender"
											value="0" />
										<label class="form-check-label">Nữ</label>
									</div>
									<div class="form-check form-check-inline">
										<f:radiobutton cssClass="form-check-input" path="gender"
											value="1" />
										<label class="form-check-label">Nam</label>
									</div>
									<div class="form-check form-check-inline">
										<f:radiobutton cssClass="form-check-input" path="gender"
											value="2" />
										<label class="form-check-label">Khác</label>
									</div>
									<small><f:errors path="gender" style="color:red;"></f:errors></small>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<f:input path="email" type="text"
										cssClass="form-control form-control-user" placeholder="Email" />
									<small><f:errors path="email" style="color:red;"></f:errors></small>
									<c:if test="${emailErrorMess != null}">
										<small style="color: red;"> <c:out
												value="${emailErrorMess}" />
										</small>
									</c:if>
								</div>
								<div class="col-sm-6">
									<f:input path="phoneNumber" type="text"
										cssClass="form-control form-control-user"
										placeholder="Số điện thoại" />
									<small><f:errors path="phoneNumber" style="color:red;"></f:errors></small>
									<c:if test="${phoneErrorMess != null}">
										<small style="color: red;"> <c:out
												value="${phoneErrorMess}" />
										</small>
									</c:if>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<f:input path="userName" type="text"
										cssClass="form-control form-control-user"
										placeholder="Tên tài khoản" />
									<small><f:errors path="userName" style="color:red;"></f:errors></small>
								</div>
								<div class="col-sm-6">
									<f:input path="password" type="password"
										cssClass="form-control form-control-user"
										placeholder="Mật khẩu" />
									<small><f:errors path="password" style="color:red;"></f:errors></small>
								</div>
							</div>
							<button type="submit" class="btn btn-primary btn-user btn-block">Đăng
								ký</button>
						</f:form>
						<hr>
						<div class="text-center">
							<a class="small" href="">Quên mật khẩu?</a>
						</div>
						<div class="text-center">
							<a class="small" href="/dang-nhap">Bạn đã có tài khoản? Đăng
								nhập!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>
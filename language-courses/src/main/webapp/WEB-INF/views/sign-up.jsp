<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						</div>
						<form class="user">
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="text" class="form-control form-control-user"
										id="exampleFirstName" placeholder="First Name">
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control form-control-user"
										id="exampleLastName" placeholder="Last Name">
								</div>
							</div>
							<div class="form-group">
								<input type="email" class="form-control form-control-user"
									id="exampleInputEmail" placeholder="Email">
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="password" class="form-control form-control-user"
										id="exampleInputPassword" placeholder="Mật khẩu">
								</div>
								<div class="col-sm-6">
									<input type="password" class="form-control form-control-user"
										id="exampleRepeatPassword" placeholder="Nhập lại mật khẩu">
								</div>
							</div>
							<a href="login.html" class="btn btn-primary btn-user btn-block">
								Đăng ký </a>
						</form>
						<hr>
						<div class="text-center">
							<a class="small" href="">Quên mật khẩu?</a>
						</div>
						<div class="text-center">
							<a class="small" href="/dang-nhap">Bạn đã có tài khoản?
								Đăng nhập!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>
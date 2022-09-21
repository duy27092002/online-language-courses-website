<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/form/header-of-form.jsp"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!-- Outer Row -->
<div class="row justify-content-center">

	<div class="col-xl-10 col-lg-12 col-md-9">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
					<div class="col-lg-6">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Chào mừng bạn!</h1>
								<c:if test="${param.loi != null}">
									<div class="alert alert-danger rounded-pill">Sai thông
										tin đăng nhập</div>
								</c:if>
							</div>
							<form class="user" id="loginForm" role="form">
								<div class="form-group">
									<input type="email" class="form-control form-control-user"
										name="email" aria-describedby="emailHelp" placeholder="Email" />
								</div>
								<div class="form-group">
									<input type="password" name="password"
										class="form-control form-control-user" placeholder="Mật khẩu" />
								</div>
								<button type="submit" id="btnLogin"
									class="btn btn-primary btn-user btn-block">Đăng nhập</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="">Quên mật khẩu?</a>
							</div>
							<div class="text-center">
								<a class="small" href="/dang-ky">Tạo tài khoản!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<!-- <script>
	$('#btnLogin').click(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $('#loginForm').serializeArray();
		$.each(formData, function(i, v) {
			data["" + v.name + ""] = v.value;
		});
		checkLogin(data);
	});

	function checkLogin(data) {
		$.ajax({
			url : '/api/auth/login',
			type : 'POST',
			contentType : 'application/x-www-form-urlencoded',
			data : {email: data["email"], password: data["password"]},
			dataType : 'json',
			success : function(result) {
				console.log(result);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script> -->
<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>
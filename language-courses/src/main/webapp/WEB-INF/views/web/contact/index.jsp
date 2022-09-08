<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/web/header.jsp"%>
	<!-- Contact Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="row align-items-center">
				<div class="col-lg-5 mb-5 mb-lg-0">
					<div
						class="bg-light d-flex flex-column justify-content-center px-5"
						style="height: 450px;">
						<div class="d-flex align-items-center mb-5">
							<div class="btn-icon bg-primary mr-4">
								<i class="fa fa-2x fa-map-marker-alt text-white"></i>
							</div>
							<div class="mt-n1">
								<h4>Địa chỉ</h4>
								<p class="m-0">Đường Hồ Tùng Mậu, Mai Dịch, Cầu Giấy, Hà Nội</p>
							</div>
						</div>
						<div class="d-flex align-items-center mb-5">
							<div class="btn-icon bg-secondary mr-4">
								<i class="fa fa-2x fa-phone-alt text-white"></i>
							</div>
							<div class="mt-n1">
								<h4>Số điện thoại</h4>
								<p class="m-0">+012 345 6789</p>
							</div>
						</div>
						<div class="d-flex align-items-center">
							<div class="btn-icon bg-warning mr-4">
								<i class="fa fa-2x fa-envelope text-white"></i>
							</div>
							<div class="mt-n1">
								<h4>Email</h4>
								<p class="m-0">edukate@gmail.com</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-7">
					<div class="section-title position-relative mb-4">
						<h6
							class="d-inline-block position-relative text-secondary text-uppercase pb-2">Bạn
							cần được hỗ trợ?</h6>

					</div>
					<div class="contact-form">
						<form>
							<div class="row">
								<div class="col-6 form-group">
									<input type="text"
										class="form-control border-top-0 border-right-0 border-left-0 p-0"
										placeholder="Họ tên" required="required">
								</div>
								<div class="col-6 form-group">
									<input type="email"
										class="form-control border-top-0 border-right-0 border-left-0 p-0"
										placeholder="Email" required="required">
								</div>
							</div>
							<div class="form-group">
								<input type="text"
									class="form-control border-top-0 border-right-0 border-left-0 p-0"
									placeholder="Khóa học" required="required">
							</div>
							<div class="form-group">
								<textarea
									class="form-control border-top-0 border-right-0 border-left-0 p-0"
									rows="5" placeholder="Lời nhắn" required="required"></textarea>
							</div>
							<div>
								<button class="btn btn-primary py-3 px-5" type="submit">Gửi</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact End -->
	<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
</body>
</html>
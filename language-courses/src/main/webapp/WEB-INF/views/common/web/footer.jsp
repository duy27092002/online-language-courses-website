<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!-- Footer Start -->
<div
	class="container-fluid position-relative overlay-top bg-dark text-white-50 py-5"
	style="margin-top: 90px;">
	<div class="container mt-5 pt-5">
		<div class="row">
			<div class="col-md-6 mb-5">
				<a href="/trang-chu" class="navbar-brand">
					<h1 class="mt-n2 text-uppercase text-white">${aboutDetails.name}</h1>
				</a>
				<p class="m-0">${aboutDetails.name}lànơi cung cấp các khóa học
					ngoại ngữ online chất lượng được biên soạn bởi rất nhiều giảng viên
					tài giỏi trong và ngoài nước.</p>
			</div>
			<div class="col-md-6 mb-5 text-center"></div>
		</div>
		<div class="row">
			<div class="col-md-4 mb-5">
				<h3 class="text-white mb-4">Liên hệ</h3>
				<p>
					<i class="fa fa-map-marker-alt mr-2"></i>${aboutDetails.location}
				</p>
				<p>
					<i class="fa fa-phone-alt mr-2"></i>${aboutDetails.phoneNumber}
				</p>
				<p>
					<i class="fa fa-envelope mr-2"></i>${aboutDetails.email}
				</p>
				<div class="d-flex justify-content-start mt-4">
					<a class="text-white mr-4" href="${aboutDetails.twitterLink}"><i
						class="fab fa-2x fa-twitter"></i></a> <a class="text-white mr-4"
						href="${aboutDetails.facebookLink}"><i
						class="fab fa-2x fa-facebook-f"></i></a> <a class="text-white mr-4"
						href="${aboutDetails.inLink}"><i
						class="fab fa-2x fa-linkedin-in"></i></a> <a class="text-white"
						href="${aboutDetails.instagramLink}"><i
						class="fab fa-2x fa-instagram"></i></a>
				</div>
			</div>
			<div class="col-md-4 mb-5">
				<h3 class="text-white mb-4">Khóa học</h3>
				<div class="d-flex flex-column justify-content-start">
					<c:forEach items="${activeLanguageList}" var="language">
						<a class="text-white-50 mb-2"
							href="/danh-sach-khoa-hoc?id=${language.id}"><i
							class="fa fa-angle-right mr-2"></i>${language.name}</a>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-4 mb-5">
				<h3 class="text-white mb-4">Khác</h3>
				<div class="d-flex flex-column justify-content-start">
					<a class="text-white-50 mb-2" href="#"><i
						class="fa fa-angle-right mr-2"></i>Chính sách bảo mật</a> <a
						class="text-white-50 mb-2" href="#"><i
						class="fa fa-angle-right mr-2"></i>Điều khoản dịch vụ</a> <a
						class="text-white-50 mb-2" href="/cau-hoi-thuong-gap"><i
						class="fa fa-angle-right mr-2"></i>Câu hỏi thường gặp</a> <a
						class="text-white-50" href="/lien-he"><i
						class="fa fa-angle-right mr-2"></i>Liên hệ ngay</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid bg-dark text-white-50 border-top py-4"
	style="border-color: rgba(256, 256, 256, .1) !important;">
	<div class="container text-center">
		<p class="m-0">
			Bản quyền thuộc về &copy; <strong class="text-white">${aboutDetails.name}</strong>
		</p>
	</div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#"
	class="btn btn-lg btn-primary rounded-0 btn-lg-square back-to-top"><i
	class="fa fa-angle-double-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="/web/lib/easing/easing.min.js"></script>
<script src="/web/lib/waypoints/waypoints.min.js"></script>
<script src="/web/lib/counterup/counterup.min.js"></script>
<script src="/web/lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="/web/js/main.js"></script>
</body>

</html>
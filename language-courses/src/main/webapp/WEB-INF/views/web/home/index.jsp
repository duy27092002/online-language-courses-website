<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- About Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-5 mb-5 mb-lg-0" style="min-height: 500px;">
				<div class="position-relative h-100">
					<img class="position-absolute w-100 h-100" src="/web/img/about.jpg"
						style="object-fit: cover;">
				</div>
			</div>
			<div class="col-lg-7">
				<div class="section-title position-relative mb-4">
					<h6
						class="d-inline-block position-relative text-secondary text-uppercase pb-2">Về
						chúng tôi</h6>
					<h1 class="display-4">Học tập trực tuyến ở bất kỳ đâu</h1>
				</div>
				<p>Tempor erat elitr at rebum at at clita aliquyam consetetur.
					Diam dolor diam ipsum et, tempor voluptua sit consetetur sit.
					Aliquyam diam amet diam et eos sadipscing labore. Clita erat ipsum
					et lorem et sit, sed stet no labore lorem sit. Sanctus clita duo
					justo et tempor consetetur takimata eirmod, dolores takimata
					consetetur invidunt magna dolores aliquyam dolores dolore. Amet
					erat amet et magna</p>
				<div class="row pt-3 mx-0">
					<div class="col-4 px-0">
						<div class="bg-primary text-center p-4">
							<h1 class="text-white" data-toggle="counter-up">${releasedCourse}</h1>
							<h6 class="text-uppercase text-white">
								Khóa học<span class="d-block">trực tuyến</span>
							</h6>
						</div>
					</div>
					<div class="col-4 px-0">
						<div class="bg-secondary text-center p-4">
							<h1 class="text-white" data-toggle="counter-up">${activeInstructor}</h1>
							<h6 class="text-uppercase text-white">
								Giảng viên<span class="d-block">giỏi</span>
							</h6>
						</div>
					</div>
					<div class="col-4 px-0">
						<div class="bg-warning text-center p-4">
							<h1 class="text-white" data-toggle="counter-up">${activeStudent}</h1>
							<h6 class="text-uppercase text-white">
								Học viên<span class="d-block">thân thiện</span>
							</h6>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- About End -->


<!-- Feature Start -->
<div class="container-fluid bg-image" style="margin: 90px 0;">
	<div class="container">
		<div class="row">
			<div class="col-lg-7 my-5 pt-5 pb-lg-5">
				<div class="section-title position-relative mb-4">
					<h6
						class="d-inline-block position-relative text-secondary text-uppercase pb-2">Tại
						sao chọn Edukate?</h6>
					<h1 class="display-4">Tại sao bạn nên bắt đầu học với chúng
						tôi?</h1>
				</div>
				<p class="mb-4 pb-2">Aliquyam accusam clita nonumy ipsum sit sea
					clita ipsum clita, ipsum dolores amet voluptua duo dolores et sit
					ipsum rebum, sadipscing et erat eirmod diam kasd labore clita est.
					Diam sanctus gubergren sit rebum clita amet.</p>
				<div class="d-flex mb-3">
					<div class="btn-icon bg-primary mr-4">
						<i class="fa fa-2x fa-graduation-cap text-white"></i>
					</div>
					<div class="mt-n1">
						<h4>Giảng viên giỏi</h4>
						<p>Labore rebum duo est Sit dolore eos sit tempor eos stet,
							vero vero clita magna kasd no nonumy et eos dolor magna ipsum.</p>
					</div>
				</div>
				<div class="d-flex mb-3">
					<div class="btn-icon bg-secondary mr-4">
						<i class="fa fa-2x fa-certificate text-white"></i>
					</div>
					<div class="mt-n1">
						<h4>Chứng chỉ quốc tế</h4>
						<p>Labore rebum duo est Sit dolore eos sit tempor eos stet,
							vero vero clita magna kasd no nonumy et eos dolor magna ipsum.</p>
					</div>
				</div>
				<div class="d-flex">
					<div class="btn-icon bg-warning mr-4">
						<i class="fa fa-2x fa-book-reader text-white"></i>
					</div>
					<div class="mt-n1">
						<h4>Lớp học online</h4>
						<p class="m-0">Labore rebum duo est Sit dolore eos sit tempor
							eos stet, vero vero clita magna kasd no nonumy et eos dolor magna
							ipsum.</p>
					</div>
				</div>
			</div>
			<div class="col-lg-5" style="min-height: 500px;">
				<div class="position-relative h-100">
					<img class="position-absolute w-100 h-100"
						src="/web/img/feature.jpg" style="object-fit: cover;">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Feature Start -->


<!-- Courses Start -->
<div class="container-fluid px-0 py-5">
	<div class="row mx-0 justify-content-center pt-5">
		<div class="col-lg-7">
			<div class="section-title text-center position-relative mb-4">
				<h6
					class="d-inline-block position-relative text-secondary text-uppercase pb-2">Khóa
					học</h6>
				<h1 class="display-4">Chất lượng và luôn được cập nhật liên tục</h1>
			</div>
		</div>
		<div class="col-lg-5 my-5">
			<div class="card o-hidden border-0 shadow-lg py-3 px-5">
				<ul class="list-group list-group-flush">
					<c:forEach items="${activeLanguageList}" var="languageInfo">
						<li
							class="list-group-item d-flex justify-content-between align-items-center px-0">
							<a href="/danh-sach-khoa-hoc?id=${languageInfo.id}"
							class="text-decoration-none h6 m-0">${languageInfo.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Courses End -->


<!-- Team Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="section-title text-center position-relative mb-5">
			<h6
				class="d-inline-block position-relative text-secondary text-uppercase pb-2">Giảng
				viên</h6>
			<h1 class="display-4">Cùng gặp gỡ các giảng viên thôi nào!</h1>
		</div>
		<div class="owl-carousel team-carousel position-relative"
			style="padding: 0 30px;">
			<c:forEach items="${instructorList}" var="instructorInfo">
				<div class="team-item">
					<img class="img-fluid w-100" src="${instructorInfo.avatar}" alt="">
					<div class="bg-light text-center p-4">
						<h5 class="mb-3">${instructorInfo.name}</h5>
						<div class="d-flex justify-content-center">
							<a class="mx-1 p-1" href="${instructorInfo.twitterLink}"><i
								class="fab fa-twitter"></i></a> <a class="mx-1 p-1"
								href="${instructorInfo.facebookLink}"><i
								class="fab fa-facebook-f"></i></a> <a class="mx-1 p-1"
								href="${instructorInfo.inLink}"><i
								class="fab fa-linkedin-in"></i></a> <a class="mx-1 p-1"
								href="${instructorInfo.instagramLink}"><i
								class="fab fa-instagram"></i></a> <a class="mx-1 p-1"
								href="${instructorInfo.youtubeLink}"><i
								class="fab fa-youtube"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<!-- Team End -->


<!-- Testimonial Start -->
<div class="container-fluid bg-image py-5" style="margin: 90px 0;">
	<div class="container py-5">
		<div class="row align-items-center">
			<div class="col-lg-5 mb-5 mb-lg-0">
				<div class="section-title position-relative mb-4">
					<h6
						class="d-inline-block position-relative text-secondary text-uppercase pb-2">Đánh
						giá</h6>
					<h1 class="display-4">Học viên của chúng tôi nói gì?</h1>
				</div>
				<p class="m-0">Dolor est dolores et nonumy sit labore dolores
					est sed rebum amet, justo duo ipsum sanctus dolore magna rebum sit
					et. Diam lorem ea sea at. Nonumy et at at sed justo est nonumy
					tempor. Vero sea ea eirmod, elitr ea amet diam ipsum at amet. Erat
					sed stet eos ipsum diam</p>
			</div>
			<div class="col-lg-7">
				<div class="owl-carousel testimonial-carousel">
					<c:forEach items="${activeEvaluatedList}" var="evaluated">
						<div class="bg-white p-5">
							<i class="fa fa-3x fa-quote-left text-primary mb-4"></i>
							<p>${evaluated.content}</p>
							<div class="d-flex flex-shrink-0 align-items-center mt-4">
								<img class="img-fluid mr-4" src="${evaluated.user.avatar}"
									alt="">
								<div>
									<h5>${evaluated.user.name}</h5>
									<span>Khóa học: ${evaluated.course.name}</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Testimonial Start -->


<!-- Contact Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="row align-items-center">
			<div class="col-lg-12 mb-5 mb-lg-0">
				<div class="bg-light d-flex flex-column justify-content-center px-5"
					style="height: 450px;">
					<div class="d-flex align-items-center mb-5">
						<div class="btn-icon bg-primary mr-4">
							<i class="fa fa-2x fa-map-marker-alt text-white"></i>
						</div>
						<div class="mt-n1">
							<h4>Địa chỉ</h4>
							<p class="m-0">${aboutDetails.location}</p>
						</div>
					</div>
					<div class="d-flex align-items-center mb-5">
						<div class="btn-icon bg-secondary mr-4">
							<i class="fa fa-2x fa-phone-alt text-white"></i>
						</div>
						<div class="mt-n1">
							<h4>Số điện thoại</h4>
							<p class="m-0">${aboutDetails.phoneNumber}</p>
						</div>
					</div>
					<div class="d-flex align-items-center">
						<div class="btn-icon bg-warning mr-4">
							<i class="fa fa-2x fa-envelope text-white"></i>
						</div>
						<div class="mt-n1">
							<h4>Email</h4>
							<p class="m-0">${aboutDetails.email}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Contact End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
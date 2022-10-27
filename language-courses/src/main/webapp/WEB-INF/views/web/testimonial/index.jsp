<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Testimonial Start -->
<div class="container-fluid py-5">
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
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
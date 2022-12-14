<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
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
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
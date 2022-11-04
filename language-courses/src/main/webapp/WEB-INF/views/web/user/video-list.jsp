<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<link rel="stylesheet" href="/web/video-playlist/css/style.css">
<!-- Courses Start -->
<div class="container-fluid py-5">
	<div class="container">
		<h1 class="text-center">${courseName}</h1>
		<div class="row mt-5">
			<div class="main-video-container col-12 col-sm-12 col-md-8 col-lg-8">
				<video src="${videoListOfCourse[0].videoFile}" loop controls
					class="main-video"></video>
				<h3 class="main-vid-title">${videoListOfCourse[0].name}</h3>
				<div class="main-vid-description">${videoListOfCourse[0].description}</div>
			</div>

			<div class="video-list-container col-12 col-sm-12 col-md-4 col-lg-4">
				<c:forEach items="${videoListOfCourse}" var="video">
					<div class="list">
						<video src="${video.videoFile}" class="list-video"></video>
						<h3 class="list-title">${video.name}</h3>
						<div class="list-description" style="display: none;">${video.description}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<!-- Courses End -->
<!-- custom js file link  -->
<script src="/web/video-playlist/js/script.js"></script>
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
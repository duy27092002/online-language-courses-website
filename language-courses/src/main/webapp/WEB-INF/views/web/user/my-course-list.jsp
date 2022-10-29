<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Courses Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="row mx-0 justify-content-center">
			<div class="col-lg-8">
				<div class="section-title text-center position-relative mb-5">
					<h1 class="text-uppercase">Khóa học của tôi</h1>
				</div>
			</div>
		</div>
		<c:if test="${mess != null}">
			<div class="alert alert-${typeAlert} alert-dismissible fade show"
				role="alert">
				<strong>${mess}</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<div class="row">
			<c:forEach items="${myCourseList}" var="courseInfo">
				<div class="col-lg-4 col-md-6 pb-4">
					<a
						class="courses-list-item position-relative d-block overflow-hidden mb-2"
						href="/chi-tiet-khoa-hoc?id=${courseInfo.id}"> <img
						class="img-fluid" src="${courseInfo.thumbnail}" alt="">
						<div class="courses-text">
							<h4 class="text-center text-white px-3">${courseInfo.name}</h4>
							<div class="border-top w-100 mt-3">
								<div class="d-flex justify-content-between p-4">
									<c:forEach items="${courseInfo.instructors}"
										var="instructorInfo">
										<span class="text-white"><i class="fa fa-user mr-2"></i>${instructorInfo.name}</span>
									</c:forEach>
								</div>
							</div>
						</div>
					</a>
					<div class="text-center">
						<a href="/vao-hoc?id=${courseInfo.id}"
							class="btn btn-sm btn-primary">Vào học</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<!-- Courses End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
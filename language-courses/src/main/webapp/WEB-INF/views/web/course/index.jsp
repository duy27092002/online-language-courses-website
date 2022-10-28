<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Courses Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<c:if test="${searchList != null}">
			<c:set var="title" value='Kết quả tìm kiếm khóa học: "${keyword}"'></c:set>
			<c:set var="courseList" value="${searchList}"></c:set>
		</c:if>
		<c:if test="${courseListByLanguage != null}">
			<c:set var="title" value="Khóa học ${languageName}"></c:set>
			<c:set var="courseList" value="${courseListByLanguage}"></c:set>
		</c:if>
		<c:if test="${activeCourseList != null}">
			<c:set var="title" value="Tất cả khóa học"></c:set>
			<c:set var="courseList" value="${activeCourseList}"></c:set>
		</c:if>
		<div class="row mx-0 justify-content-center">
			<div class="col-lg-8">
				<div class="section-title text-center position-relative mb-5">
					<h1 class="text-uppercase">${title}</h1>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${courseList}" var="courseInfo">
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
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<!-- Courses End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
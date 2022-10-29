<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Detail Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-12">
				<div class="mb-5">
					<div class="section-title position-relative mb-5">
						<h6
							class="d-inline-block position-relative text-secondary text-uppercase pb-2">Chi
							tiết khóa học</h6>
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
						<h1 class="display-4">${courseDetails.name}</h1>
					</div>
					<img class="img-fluid rounded w-100 mb-4"
						src="${courseDetails.thumbnail}" alt="Image">
					<p>${courseDetails.description}</p>
				</div>

				<!-- <h2 class="mb-3">Related Courses</h2>
				<div class="owl-carousel related-carousel position-relative"
					style="padding: 0 30px;">
					<a
						class="courses-list-item position-relative d-block overflow-hidden mb-2"
						href="detail.html"> <img class="img-fluid"
						src="/web/img/courses-1.jpg" alt="">
						<div class="courses-text">
							<h4 class="text-center text-white px-3">Web design &
								development courses for beginners</h4>
							<div class="border-top w-100 mt-3">
								<div class="d-flex justify-content-between p-4">
									<span class="text-white"><i class="fa fa-user mr-2"></i>Jhon
										Doe</span> <span class="text-white"><i class="fa fa-star mr-2"></i>4.5
										<small>(250)</small></span>
								</div>
							</div>
						</div>
					</a> <a
						class="courses-list-item position-relative d-block overflow-hidden mb-2"
						href="detail.html"> <img class="img-fluid"
						src="/web/img/courses-2.jpg" alt="">
						<div class="courses-text">
							<h4 class="text-center text-white px-3">Web design &
								development courses for beginners</h4>
							<div class="border-top w-100 mt-3">
								<div class="d-flex justify-content-between p-4">
									<span class="text-white"><i class="fa fa-user mr-2"></i>Jhon
										Doe</span> <span class="text-white"><i class="fa fa-star mr-2"></i>4.5
										<small>(250)</small></span>
								</div>
							</div>
						</div>
					</a> <a
						class="courses-list-item position-relative d-block overflow-hidden mb-2"
						href="detail.html"> <img class="img-fluid"
						src="/web/img/courses-3.jpg" alt="">
						<div class="courses-text">
							<h4 class="text-center text-white px-3">Web design &
								development courses for beginners</h4>
							<div class="border-top w-100 mt-3">
								<div class="d-flex justify-content-between p-4">
									<span class="text-white"><i class="fa fa-user mr-2"></i>Jhon
										Doe</span> <span class="text-white"><i class="fa fa-star mr-2"></i>4.5
										<small>(250)</small></span>
								</div>
							</div>
						</div>
					</a>
				</div> -->
			</div>

			<div class="col-lg-12 mt-5 mt-lg-0">
				<div class="bg-primary mb-5 py-3">
					<h3 class="text-white py-3 px-4 m-0">Thông tin cơ bản</h3>
					<div class="d-flex justify-content-between border-bottom px-4">
						<h6 class="text-white my-3">Giảng viên</h6>
						<c:forEach items="${courseDetails.instructors}" var="instructor">
							<h6 class="text-white my-3">
								<i class="fa fa-user mr-2"></i>${instructor.name}</h6>
						</c:forEach>
					</div>
					<div class="d-flex justify-content-between border-bottom px-4">
						<h6 class="text-white my-3">Chất lượng</h6>
						<h6 class="text-white my-3">
							${rating} <i class="fas fa-star mr-2"></i> <small>(${totalOfEvaluated})</small>
						</h6>
					</div>
					<div class="d-flex justify-content-between border-bottom px-4">
						<h6 class="text-white my-3">Số lượng video</h6>
						<h6 class="text-white my-3">${totalOfVideo}</h6>
					</div>
					<div class="d-flex justify-content-between border-bottom px-4">
						<h6 class="text-white my-3">Kỹ năng đạt được</h6>
						<c:forEach items="${courseDetails.skillLevelList}" var="skl">
							<h6 class="text-white my-3">
								<i class="fas fa-bookmark mr-2"></i>${skl.name}</h6>
						</c:forEach>
					</div>
					<div class="d-flex justify-content-between px-4">
						<h6 class="text-white my-3">Ngôn ngữ</h6>
						<h6 class="text-white my-3">${courseDetails.language.name}</h6>
					</div>
					<h5 class="text-white py-3 px-4 m-0">Giá:
						${courseDetails.price} VND</h5>
					<div class="py-3 px-4">
						<sec:authorize access="!isAuthenticated()">
							<a class="btn btn-block btn-secondary py-3 px-5"
								href="/dang-nhap">Đăng nhập để mua</a>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<c:if
								test="${listOfStudentIdByCourseId.contains(SecurityUtil.getPrincipal().getUserId())}">
								<a href="/vao-hoc?id=${courseDetails.id}"
									class="btn btn-block btn-secondary py-3 px-5">Vào học</a>
							</c:if>
							<c:if
								test="${!listOfStudentIdByCourseId.contains(SecurityUtil.getPrincipal().getUserId())}">
								<f:form modelAttribute="courseStudentDTO" action="/mua-khoa-hoc"
									method="post">
									<f:input path="id" type="hidden" />
									<f:input path="status" type="hidden" value="1" />
									<f:input path="courseId" type="hidden"
										value="${courseDetails.id}" />
									<f:input path="studentId" type="hidden"
										value="<%=SecurityUtil.getPrincipal().getUserId()%>" />
									<button type="submit"
										class="btn btn-block btn-secondary py-3 px-5">Mua</button>
								</f:form>
							</c:if>
						</sec:authorize>
					</div>
				</div>

				<!-- <div class="mb-5">
					<h2 class="mb-4">Recent Courses</h2>
					<a class="d-flex align-items-center text-decoration-none mb-4"
						href=""> <img class="img-fluid rounded"
						src="/web/img/courses-80x80.jpg" alt="">
						<div class="pl-3">
							<h6>Web design & development courses for beginners</h6>
							<div class="d-flex">
								<small class="text-body mr-3"><i
									class="fa fa-user text-primary mr-2"></i>Jhon Doe</small> <small
									class="text-body"><i
									class="fa fa-star text-primary mr-2"></i>4.5 (250)</small>
							</div>
						</div>
					</a> <a class="d-flex align-items-center text-decoration-none mb-4"
						href=""> <img class="img-fluid rounded"
						src="/web/img/courses-80x80.jpg" alt="">
						<div class="pl-3">
							<h6>Web design & development courses for beginners</h6>
							<div class="d-flex">
								<small class="text-body mr-3"><i
									class="fa fa-user text-primary mr-2"></i>Jhon Doe</small> <small
									class="text-body"><i
									class="fa fa-star text-primary mr-2"></i>4.5 (250)</small>
							</div>
						</div>
					</a> <a class="d-flex align-items-center text-decoration-none mb-4"
						href=""> <img class="img-fluid rounded"
						src="/web/img/courses-80x80.jpg" alt="">
						<div class="pl-3">
							<h6>Web design & development courses for beginners</h6>
							<div class="d-flex">
								<small class="text-body mr-3"><i
									class="fa fa-user text-primary mr-2"></i>Jhon Doe</small> <small
									class="text-body"><i
									class="fa fa-star text-primary mr-2"></i>4.5 (250)</small>
							</div>
						</div>
					</a> <a class="d-flex align-items-center text-decoration-none" href="">
						<img class="img-fluid rounded" src="/web/img/courses-80x80.jpg"
						alt="">
						<div class="pl-3">
							<h6>Web design & development courses for beginners</h6>
							<div class="d-flex">
								<small class="text-body mr-3"><i
									class="fa fa-user text-primary mr-2"></i>Jhon Doe</small> <small
									class="text-body"><i
									class="fa fa-star text-primary mr-2"></i>4.5 (250)</small>
							</div>
						</div>
					</a>
				</div> -->
			</div>
		</div>
	</div>
</div>
<!-- Detail End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Contact Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<div class="row align-items-center">
			<div class="col-lg-5 mb-5 mb-lg-0">
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
							<p class="m-0">${aboutDetails.phoneNumber }</p>
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
			<div class="col-lg-7 mt-5">
				<div class="section-title position-relative mb-4">
					<h6
						class="d-inline-block position-relative text-secondary text-uppercase pb-2">Bạn
						cần được hỗ trợ?</h6>
					<c:if test="${mess != null}">
						<div class="alert alert-${typeAlert} alert-dismissible fade show"
							language="alert">
							<strong>${mess}</strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>
				</div>
				<div class="contact-form">
					<f:form action="/gui-phan-hoi" method="post"
						modelAttribute="feedbackDTO">
						<div class="row">
							<div class="col-6 form-group">
								<f:input path="name" type="text"
									cssClass="form-control border-top-0 border-right-0 border-left-0 p-0"
									placeholder="Họ tên *" />
								<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
							</div>
							<div class="col-6 form-group">
								<f:input path="email" type="text"
									cssClass="form-control border-top-0 border-right-0 border-left-0 p-0"
									placeholder="Email *" />
								<small><f:errors path="email" cssClass="text-danger"></f:errors></small>
							</div>
						</div>
						<div class="form-group">
							<f:textarea path="question"
								cssClass="form-control border-top-0 border-right-0 border-left-0 p-0"
								rows="5" placeholder="Lời nhắn *" />
							<small><f:errors path="question" cssClass="text-danger"></f:errors></small>
						</div>
						<div>
							<f:input type="hidden" path="status" value="1" />
							<button class="btn btn-primary py-3 px-5" type="submit">Gửi</button>
						</div>
					</f:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Contact End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
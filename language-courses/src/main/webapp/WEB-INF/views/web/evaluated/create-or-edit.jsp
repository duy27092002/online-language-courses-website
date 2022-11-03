<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<div class="container-fluid">
	<div class="container">
		<c:set var="studentId"
			value="<%=SecurityUtil.getPrincipal().getUserId()%>"></c:set>
		<c:if test="${evaluatedDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa đánh giá"></c:set>
			<c:set var="formAction" value="/danh-gia/chinh-sua"></c:set>
			<c:set var="modelAttribute" value="evaluatedDetails"></c:set>
			<c:set var="courseId" value="${evaluatedDetails.course.id}"></c:set>
		</c:if>
		<c:if test="${evaluatedDetails == null}">
			<c:set var="actionTitle" value="Tạo đánh giá"></c:set>
			<c:set var="formAction" value="/danh-gia/tao-danh-gia"></c:set>
			<c:set var="modelAttribute" value="evaluatedDTO"></c:set>
			<c:set var="courseId" value="${courseId}"></c:set>
		</c:if>
		<h1 class="text-center text-uppercase">
			<c:out value="${actionTitle}" />
		</h1>
		<c:if test="${message != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<strong>${message}</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form action="${formAction}" method="post"
								modelAttribute="${modelAttribute}">
								<div class="form-group mb-3">
									<f:input path="id" type="hidden" value="${evaluatedDetails.id}" />
									<f:input path="courseId" type="hidden" value="${courseId}" />
									<f:input path="userId" type="hidden" value="${studentId}" />
									<f:input path="status" type="hidden" value="1" />
									<label>Nội dung <span class="text-danger">*</span></label>
									<f:textarea path="content" rows="3" cssClass="form-control"
										value="${evaluatedDetails.content}"
										style="border-radius: 20px;" />
									<small><f:errors path="content" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group mb-3">
									<label>Điểm</label><br>
									<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" name="point"
											value="1" ${evaluatedDetails.point == 1 ? "checked" : "" } />
										<label class="form-check-label">1</label>
									</div>
									<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" name="point"
											value="2" ${evaluatedDetails.point == 2 ? "checked" : "" } />
										<label class="form-check-label">2</label>
									</div>
									<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" name="point"
											value="3" ${evaluatedDetails.point == 3 ? "checked" : "" } />
										<label class="form-check-label">3</label>
									</div>
									<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" name="point"
											value="4" ${evaluatedDetails.point == 4 ? "checked" : "" } />
										<label class="form-check-label">4</label>
									</div>
									<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" name="point"
											value="5" ${evaluatedDetails.point == 5 ? "checked" : "" }
											${evaluatedDetails == null ? "checked" : "" } /> <label
											class="form-check-label">5</label>
									</div>
								</div>
								<div class="form-group mb-3 text-center">
									<button type="submit" class="btn btn-primary"
										style="border-radius: 10px;">Lưu</button>
									<a href="/khoa-hoc-cua-toi?id=${studentId}"
										class="btn btn-secondary" style="border-radius: 10px;">Quay
										lại</a>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Danh sách phản hồi</h1>
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
		<small class="text-muted">(Ấn vào biểu tượng để thay đổi trạng thái)</small>
		<div class="card o-hidden border-0 shadow-lg mb-4 mt-3">
			<div class="card-body p-5">
				<div class="table-responsive">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<th scope="col">Họ tên</th>
								<th scope="col">Email</th>
								<th scope="col">Phản hồi</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultList.data}" var="feedback">
								<c:if test="${feedback.status == 1 }">
									<c:set var="textColor" value="warning"></c:set>
									<c:set var="tooltipTitle" value="Chờ kiểm duyệt"></c:set>
									<c:set var="toggleStatus" value="off"></c:set>
								</c:if>
								<c:if test="${feedback.status == 0 }">
									<c:set var="textColor" value="danger"></c:set>
									<c:set var="tooltipTitle" value="Đã hủy"></c:set>
									<c:set var="toggleStatus" value="off"></c:set>
								</c:if>
								<c:if test="${feedback.status == 2 }">
									<c:set var="textColor" value="success"></c:set>
									<c:set var="tooltipTitle" value="Đã trả lời"></c:set>
									<c:set var="toggleStatus" value="on"></c:set>
								</c:if>
								<tr>
									<td>${feedback.name}</td>
									<td>${feedback.email}</td>
									<td class="text-truncate" style="max-width: 150px;">${feedback.question}</td>
									<td><f:form modelAttribute="feedbackDTO"
											action="/quan-tri/phan-hoi/cap-nhat-trang-thai" method="post">
											<f:input path="id" type="hidden" value="${feedback.id}" />
											<f:input path="name" type="hidden" value="${feedback.name}" />
											<f:input path="email" type="hidden" value="${feedback.email}" />
											<f:input path="question" type="hidden"
												value="${feedback.question}" />
											<f:input path="status" type="hidden"
												value="${feedback.status}" />
											<button type="submit" class="btn btn-sm border-0">
												<i class="fas fa-toggle-${toggleStatus} text-${textColor}"
													data-toggle="tooltip" title="${tooltipTitle}"></i>
											</button>
										</f:form>
									<td>
										<button class="btn btn-sm border-primary">
											<a href="/quan-tri/phan-hoi/chi-tiet?id=${feedback.id}"><i
												class="fas fa-eye" data-toggle="tooltip"
												title="Xem chi tiết"></i></a>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
	<%@include file="/WEB-INF/views/common/pagination.jsp"%>
</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
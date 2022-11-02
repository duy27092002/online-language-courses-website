<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết phản hồi</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Người phản hồi</label> <input
											class="form-control form-control-user bg-white"
											value="${feedbackDetails.name}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Email</label> <input
											class="form-control form-control-user bg-white"
											value="${feedbackDetails.email}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group">
									<label>Phản hồi</label>
									<textarea class="form-control bg-white" disabled="disabled"
										rows="5" style="border-radius: 20px;">${feedbackDetails.question}</textarea>
								</div>
								<div class="form-group mb-3">
									<c:if test="${feedbackDetails.status == 0 }">
										<c:set var="status" value="Đã hủy"></c:set>
									</c:if>
									<c:if test="${feedbackDetails.status == 1 }">
										<c:set var="status" value="Chờ kiểm duyệt"></c:set>
									</c:if>
									<c:if test="${feedbackDetails.status == 2 }">
										<c:set var="status" value="Đã trả lời"></c:set>
									</c:if>
									<label>Trạng thái</label> <input
										class="form-control form-control-user bg-white"
										value="<c:out value="${status}" />" disabled="disabled" />
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${feedbackDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${feedbackDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group">
									<a href="/quan-tri/phan-hoi/danh-sach"
										class="btn btn-secondary btn-user btn-block mt-4">Quay lại
										danh sách</a>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
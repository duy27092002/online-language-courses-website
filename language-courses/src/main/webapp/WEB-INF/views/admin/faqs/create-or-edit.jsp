<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<c:if test="${faqsDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa câu hỏi"></c:set>
			<c:set var="formAction"
				value="/quan-tri/cau-hoi-thuong-gap/chinh-sua"></c:set>
			<c:set var="modelAttribute" value="faqsDetails"></c:set>
		</c:if>
		<c:if test="${faqsDetails == null}">
			<c:set var="actionTitle" value="Thêm mới câu hỏi"></c:set>
			<c:set var="formAction" value="/quan-tri/cau-hoi-thuong-gap/them-moi"></c:set>
			<c:set var="modelAttribute" value="faqsDTO"></c:set>
		</c:if>
		<h1 class="text-center">
			<c:out value="${actionTitle}" />
		</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user" action="${formAction}" method="post"
								modelAttribute="${modelAttribute}">
								<f:input type="hidden" path="id" value="${faqsDetails.id}" />
								<c:if test="${faqsDetails == null}">
									<f:input type="hidden" path="status" value="1" />
								</c:if>
								<div class="form-group mb-3">
									<label>Câu hỏi:</label>
									<f:textarea rows="3" cssClass="form-control bg-white"
										value="${faqsDetails.question}" path="question"
										style="border-radius: 20px;" />
									<small><f:errors path="question" cssClass="text-danger"></f:errors></small>
									<c:if test="${isExitName != null}">
										<small class="text-danger">${isExitName}</small>
									</c:if>
								</div>
								<div class="form-group mb-3">
									<label>Câu trả lời:</label>
									<f:input cssClass="form-control form-control-user bg-white"
										value="${faqsDetails.answer}" path="answer" />
								</div>
								<div class="form-group mb-3">
									<c:if test="${faqsDetails != null}">
										<label>Trạng thái:</label>
										<f:select path="status" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<option value="1"
												<c:if test="${faqsDetails.status == 1}"><c:out value="selected" /></c:if>>Hiển
												thị</option>
											<option value="0"
												<c:if test="${faqsDetails.status == 0}"><c:out value="selected" /></c:if>>Ẩn</option>
										</f:select>
										<small><f:errors path="status" cssClass="text-danger"></f:errors></small>
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/cau-hoi-thuong-gap/danh-sach"
											class="btn btn-secondary btn-user btn-block mt-4">Quay
											lại danh sách</a>
									</div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết câu hỏi</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group">
									<label>Câu hỏi</label>
									<textarea class="form-control bg-white" disabled="disabled"
										rows="3" style="border-radius: 20px;">${faqsDetails.question}</textarea>
								</div>
								<div class="form-group">
									<label>Câu trả lời</label>
									<textarea id="answer-details" class="form-control bg-white" disabled="disabled"
										rows="5" style="border-radius: 20px;">${faqsDetails.answer}</textarea>
								</div>
								<div class="form-group mb-3">
									<c:if test="${faqsDetails.status == 1 }">
										<c:set var="status" value="Đang hiển thị"></c:set>
									</c:if>
									<c:if test="${faqsDetails.status == 0 }">
										<c:set var="status" value="Đã ẩn"></c:set>
									</c:if>
									<label>Trạng thái</label> <input
										class="form-control form-control-user bg-white"
										value="<c:out value="${status}" />" disabled="disabled" />
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${faqsDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${faqsDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/cau-hoi-thuong-gap/chinh-sua?id=${faqsDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
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

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
<script src="/lib/ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function(){
		CKEDITOR.replace('answer-details');
	});
</script>
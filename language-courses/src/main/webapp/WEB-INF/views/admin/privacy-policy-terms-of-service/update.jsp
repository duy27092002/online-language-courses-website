<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Chỉnh sửa chính sách & điều khoản</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form action="/quan-tri/chinh-sach-va-dieu-khoan/chinh-sua"
								method="post" cssClass="user" modelAttribute="pptos">
								<f:input type="hidden" path="id" value="${pptos.id}" />
								<f:input type="hidden" path="status" value="${pptos.status}" />
								<div class="form-group">
									<label>Chính sách bảo mật <span class="text-danger">*</span>
									</label>
									<f:textarea rows="8" cssClass="form-control bg-white"
										value="${pptos.privacyPolicy}" path="privacyPolicy" />
									<small><f:errors path="privacyPolicy"
											cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group">
									<label>Điều khoản dịch vụ <span class="text-danger">*</span>
									</label>
									<f:textarea rows="8" cssClass="form-control bg-white"
										value="${pptos.termsOfService}" path="termsOfService" />
									<small><f:errors path="termsOfService"
											cssClass="text-danger"></f:errors></small>
								</div>
								<button type="submit"
									class="btn btn-primary btn-user btn-block mt-5">Lưu</button>
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
	$(document).ready(function() {
		CKEDITOR.replace('privacyPolicy');
		CKEDITOR.replace('termsOfService');
	});
</script>
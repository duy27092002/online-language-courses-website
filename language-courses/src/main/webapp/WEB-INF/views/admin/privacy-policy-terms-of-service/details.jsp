<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">
	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Chính sách bảo mật & Điều khoản dịch vụ</h1>
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
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group">
									<label>Chính sách bảo mật</label>
									<textarea id="privacy-policy" disabled="disabled">${pptos.privacyPolicy}</textarea>
								</div>
								<div class="form-group">
									<label>Điều khoản dịch vụ</label>
									<textarea id="terms-of-service" disabled="disabled">${pptos.termsOfService}</textarea>
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${pptos.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${pptos.modifiedDate}" pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<a href="/quan-tri/chinh-sach-va-dieu-khoan/chinh-sua"
									class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
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
		CKEDITOR.replace('privacy-policy');
		CKEDITOR.replace('terms-of-service');
	});
</script>
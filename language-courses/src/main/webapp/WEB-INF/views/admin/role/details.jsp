<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Thông tin chi tiết vai trò</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user">
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Tên vai trò:</label> <input
											class="form-control form-control-user bg-white"
											value="${roleDetails.name}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<c:if test="${roleDetails.status == 1 }">
											<c:set var="status" value="Đang hoạt động"></c:set>
										</c:if>
										<c:if test="${roleDetails.status == 0 }">
											<c:set var="status" value="Đã hủy"></c:set>
										</c:if>
										<label>Trạng thái:</label> <input
											class="form-control form-control-user bg-white"
											value="<c:out value="${status}" />" disabled="disabled" />
									</div>
								</div>
								<p class="mb-0">
									<small>Cập nhật bởi: ${roleDetails.modifiedBy}</small>
								</p>
								<small>Vào lúc: <fmt:formatDate
										value="${roleDetails.modifiedDate}"
										pattern="HH:mm:ss dd/MM/yyyy" /></small>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/vai-tro/chinh-sua?id=${roleDetails.id}"
											class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/vai-tro"
											class="btn btn-secondary btn-user btn-block mt-4">Quay lại</a>
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
	<%@include file="/WEB-INF/views/common/pagination.jsp"%>
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
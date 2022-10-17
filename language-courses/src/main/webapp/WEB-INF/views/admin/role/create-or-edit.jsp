<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<c:if test="${roleDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa vai trò"></c:set>
			<c:set var="col" value="col-12 col-sm-12 col-md-6 col-lg-6"></c:set>
			<c:set var="formAction" value="/quan-tri/vai-tro/chinh-sua"></c:set>
		</c:if>
		<c:if test="${roleDetails == null}">
			<c:set var="actionTitle" value="Thêm mới vai trò"></c:set>
			<c:set var="col" value="col-12"></c:set>
			<c:set var="formAction" value="/quan-tri/vai-tro/them-moi"></c:set>
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
								modelAttribute="roleDTO">
								<f:input type="hidden" path="id" value="${roleDetails.id}" />
								<c:if test="${roleDetails == null}">
									<f:input type="hidden" path="status" value="1" />
								</c:if>
								<f:input type="hidden" path="code" value="${roleDetails.code}" />
								<div class="form-group row">
									<div class="<c:out value="${col}" /> mb-sm-0">
										<label>Tên vai trò:</label>
										<f:input cssClass="form-control form-control-user basic-usage"
											value="${roleDetails.name}" path="name" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
										<c:if test="${isExitName != null}">
											<small class="text-danger">${isExitName}</small>
										</c:if>
									</div>
									<c:if test="${roleDetails != null}">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Trạng thái:</label>
											<f:select path="status" cssClass="form-control"
												style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
												<option value="1"
													<c:if test="${roleDetails.status == 1}"><c:out value="selected" /></c:if>>Đang
													hoạt động</option>
												<option value="0"
													<c:if test="${roleDetails.status == 0}"><c:out value="selected" /></c:if>>Đã
													hủy</option>
											</f:select>
											<small><f:errors path="status" cssClass="text-danger"></f:errors></small>
										</div>
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/vai-tro"
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/speakingurl/14.0.1/speakingurl.min.js"></script>

<script src="/admin/js/input-string-to-slug.js"></script>

<script>
	$(document).ready(function() {
		$(".basic-usage").stringToSlug();
	});
</script>
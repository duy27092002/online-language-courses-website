<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">Chỉnh sửa đánh giá</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user"
								action="/quan-tri/danh-gia-cua-hoc-vien/chinh-sua" method="post"
								modelAttribute="evaluatedDetails">
								<f:input type="hidden" path="id" value="${evaluatedDetails.id}" />
								<f:input type="hidden" path="userId"
									value="${evaluatedDetails.user.id}" />
								<f:input type="hidden" path="courseId"
									value="${evaluatedDetails.course.id}" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Email học viên:</label> <input
											class="form-control form-control-user bg-white"
											value="${evaluatedDetails.user.email}" disabled="disabled" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Tên khóa học:</label> <input
											class="form-control form-control-user bg-white"
											value="${evaluatedDetails.course.name}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Nội dung đánh giá:</label>
										<f:input type="hidden" path="content"
											value="${evaluatedDetails.content}" />
										<div class="element-scroll">${evaluatedDetails.content}
										</div>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Điểm đánh giá:</label>
										<f:input type="hidden" path="point"
											value="${evaluatedDetails.point}" />
										<input class="form-control form-control-user bg-white"
											value="${evaluatedDetails.point}" disabled="disabled" />
									</div>
								</div>
								<div class="form-group row">
									<label>Trạng thái:</label>
									<f:select path="status" cssClass="form-control"
										style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
										<option value="2"
											<c:if test="${evaluatedDetails.status == 2}"><c:out value="selected" /></c:if>>Hiển
											thị</option>
										<option value="1"
											<c:if test="${evaluatedDetails.status == 1}"><c:out value="selected" /></c:if>>Đang
											kiểm duyệt</option>
										<option value="0"
											<c:if test="${evaluatedDetails.status == 0}"><c:out value="selected" /></c:if>>Ẩn</option>
									</f:select>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a href="/quan-tri/danh-gia-cua-hoc-vien/danh-sach"
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
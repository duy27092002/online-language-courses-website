<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<c:if test="${courseDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa khóa học"></c:set>
			<c:set var="formAction" value="/quan-tri/khoa-hoc/chinh-sua"></c:set>
			<c:set var="modelAttribute" value="courseDetails"></c:set>
		</c:if>
		<c:if test="${courseDetails == null}">
			<c:set var="actionTitle" value="Thêm mới khóa học"></c:set>
			<c:set var="formAction" value="/quan-tri/khoa-hoc/them-moi"></c:set>
			<c:set var="modelAttribute" value="courseDTO"></c:set>
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
								modelAttribute="${modelAttribute}" enctype="multipart/form-data">
								<f:input type="hidden" path="id" value="${courseDetails.id}" />
								<f:input type="hidden" path="releaseTimeStr" />
								<c:if test="${courseDetails == null}">
									<f:input type="hidden" path="status" value="1" />
								</c:if>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Tên khóa học <span class="text-danger">*</span>
										</label>
										<f:input path="name" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${courseDetails.name}" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
										<c:if test="${isExitName != null}">
											<small class="text-danger">${isExitName}</small>
										</c:if>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Ảnh bìa</label>
										<f:input type="file" id="thumbnailInput"
											onchange="readURL(this);" cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="thumbnailFile" />
										<p>
											<c:if test="${courseDetails.thumbnail.contains('https://')}">
												<img style="width: 100px;" id="thumbnail"
													src="${courseDetails.thumbnail}" alt="thumbnail" />
											</c:if>
											<c:if
												test="${courseDetails != null && !courseDetails.thumbnail.contains('https://')}">
												<img style="width: 100px;" id="thumbnail"
													src="/image-file/thumbnail_default.png" alt="thumbnail" />
											</c:if>
											<c:if test="${courseDetails == null}">
												<img style="width: 100px;" id="thumbnail"
													src="/image-file/thumbnail_default.png" alt="thumbnail" />
											</c:if>
										</p>
										<label for="thumbnailInput" class="border p-2">Chọn
											ảnh</label>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Ngôn ngữ</label>
										<f:select path="languageId" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<c:forEach items="${languageList}" var="language">
												<option value="${language.id}"
													${courseDetails.languageId == language.id ? "selected" : ""}>${language.name}</option>
											</c:forEach>
										</f:select>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giá (VND) <span class="text-danger">*</span>
										</label>
										<f:input path="price" type="text"
											cssClass="form-control form-control-user bg-white number-separator"
											value="${courseDetails.price}" />
										<small><f:errors path="price" cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group mb-3">
									<label>Mô tả <span class="text-danger">*</span>
									</label>
									<f:textarea cssClass="form-control bg-white"
										value="${courseDetails.description}" path="description" />
									<small><f:errors path="description"
											cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giảng viên phụ trách <span class="text-danger">*</span>
										</label>
										<div class="element-scroll">
											<c:forEach var="instructor" items="${activeInstructorList}">
												<c:if
													test="${instructorIdListByCourse != null && instructorIdListByCourse.contains(instructor.id)}">
													<c:set var="checked" value="checked"></c:set>
												</c:if>
												<c:if
													test="${instructorIdListByCourse != null && !instructorIdListByCourse.contains(instructor.id)}">
													<c:set var="checked" value=""></c:set>
												</c:if>
												<div class="p-2 border">
													<div class="form-check mr-sm-2">
														<f:checkbox cssClass="form-check-input"
															name="instructorIds" path="" value="${instructor.id}"
															checked="${checked}" />
														<label class="form-check-label">
															${instructor.email} </label>
													</div>
												</div>
											</c:forEach>
										</div>
										<small><f:errors path="instructorIds"
												cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Kỹ năng đạt được sau khóa học <span
											class="text-danger">*</span>
										</label>
										<div class="element-scroll">
											<c:forEach var="skl" items="${activeSklList}">
												<c:if
													test="${sklIdListByCourse != null && sklIdListByCourse.contains(skl.id)}">
													<c:set var="checked" value="checked"></c:set>
												</c:if>
												<c:if
													test="${sklIdListByCourse != null && !sklIdListByCourse.contains(skl.id)}">
													<c:set var="checked" value=""></c:set>
												</c:if>
												<div class="p-2 border">
													<div class="form-check mr-sm-2">
														<f:checkbox cssClass="form-check-input"
															path="skillLevelIds" value="${skl.id}"
															checked="${checked}" />
														<label class="form-check-label"> ${skl.name} </label>
													</div>
												</div>
											</c:forEach>
										</div>
										<small><f:errors path="skillLevelIds"
												cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group row mb-3">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
										<label>Giảm giá (%)</label>
										<f:input path="discount"
											cssClass="form-control form-control-user bg-white"
											value="${courseDetails.discount}" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>Ngày bắt đầu giảm giá</label>
										<c:if test="${courseDetails == null}">
											<f:input path="startDisTimeStr" type="date"
												cssClass="form-control form-control-user bg-white" />
										</c:if>
										<c:if test="${courseDetails != null}">
											<fmt:formatDate value="${courseDetails.startDiscountTime}"
												pattern="yyyy-MM-dd" var="startDiscountTime" />
											<f:input path="startDisTimeStr"
												class="form-control form-control-user bg-white"
												value="${startDiscountTime}" />
										</c:if>
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>Ngày kết thúc giảm giá</label>
										<c:if test="${courseDetails == null}">
											<f:input path="endDisTimeStr" type="date"
												cssClass="form-control form-control-user bg-white" />
										</c:if>
										<c:if test="${courseDetails != null}">
											<fmt:formatDate value="${courseDetails.endDiscountTime}"
												pattern="yyyy-MM-dd" var="endDiscountTime" />
											<f:input path="endDisTimeStr"
												class="form-control form-control-user bg-white"
												value="${endDiscountTime}" />
										</c:if>
									</div>
								</div>
								<div class="form-group mb-3">
									<c:if test="${courseDetails != null}">
										<label>Trạng thái</label>
										<f:select path="status" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<option value="1"
												<c:if test="${courseDetails.status == 1}"><c:out value="selected" /></c:if>>Đang
												kiểm duyệt</option>
											<option value="0"
												<c:if test="${courseDetails.status == 0}"><c:out value="selected" /></c:if>>Hủy</option>
											<option value="2"
												<c:if test="${courseDetails.status == 2}"><c:out value="selected" /></c:if>>Phát
												hành</option>
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
										<a href="/quan-tri/khoa-hoc/danh-sach"
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
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#thumbnail').attr('src', e.target.result).width(100);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
<script src="/admin/js/number-separator.js"></script>
<script src="/lib/ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('description');
	});
</script>
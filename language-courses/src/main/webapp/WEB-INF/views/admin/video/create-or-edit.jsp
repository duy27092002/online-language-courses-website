<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<c:if test="${videoDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa video"></c:set>
			<c:set var="formAction" value="/quan-tri/video/chinh-sua"></c:set>
			<c:set var="modelAttribute" value="videoDetails"></c:set>
		</c:if>
		<c:if test="${videoDetails == null}">
			<c:set var="actionTitle" value="Thêm mới video"></c:set>
			<c:set var="formAction" value="/quan-tri/video/them-moi"></c:set>
			<c:set var="modelAttribute" value="videoDTO"></c:set>
			<c:set var="courseId" value="${param.id}"></c:set>
		</c:if>
		<c:if test="${courseIdAfterErr != null}">
			<c:set var="courseId" value="${courseIdAfterErr}"></c:set>
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
								<f:input type="hidden" path="id" value="${videoDetails.id}" />
								<c:if test="${videoDetails != null}">
									<f:input type="hidden" path="courseId"
										value="${videoDetails.course.id}" />
									<f:input type="hidden" path="userId"
										value="${videoDetails.user.id}" />
								</c:if>
								<c:if test="${videoDetails == null}">
									<f:input type="hidden" path="courseId" value="${courseId}" />
									<f:input type="hidden" path="userId" value="${instructorId}" />
									<f:input type="hidden" path="status" value="1" />
								</c:if>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Video:</label>
										<f:input type="file" id="addVideoInput"
											cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="videoFileName" accept=" video/*" />
										<p class="video">
											<c:if test="${videoDetails != null && oldVideo == null}">
												<video controls style="height: 200px;" id="video">
													<source src="${videoDetails.videoFile}" type="video/mp4">
												</video>
											</c:if>
											<c:if test="${videoDetails != null && oldVideo != null}">
												<video controls style="height: 200px;" id="video">
													<source src="${oldVideo}" type="video/mp4">
												</video>
											</c:if>
										</p>
										<label for="addVideoInput" class="border p-2"
											id="addVideosBtn">Chọn video</label><br>
										<c:if test="${doesNotVideo != null}">
											<small class="text-danger">${doesNotVideo}</small>
										</c:if>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Thumbnail:</label>
										<f:input type="file" id="thumbnailInput"
											onchange="readURL(this);" cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="thumbnailImg" />
										<p>
											<c:if test="${videoDetails != null && oldThumbnail == null}">
												<img style="width: 100px;" id="thumbnail"
													src="${videoDetails.thumbnail}" alt="thumbnail" />
											</c:if>
											<c:if test="${videoDetails != null && oldThumbnail != null}">
												<img style="width: 100px;" id="thumbnail"
													src="${oldThumbnail}" alt="thumbnail" />
											</c:if>
											<c:if test="${videoDetails == null}">
												<img style="width: 100px;" id="thumbnail"
													src="/image-file/video_thumbnail_default.png"
													alt="thumbnail" />
											</c:if>
										</p>
										<label for="thumbnailInput" class="border p-2">Chọn
											thumbnail</label>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Thứ tự video:</label>
										<f:input type="number" min="1" path="index_"
											cssClass="form-control form-control-user bg-white"
											value="${videoDetails.index_ }" />
										<small><f:errors path="index_" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Tên video bài giảng:</label>
										<f:input path="name" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${videoDetails.name}" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
										<c:if test="${isExitName != null}">
											<small class="text-danger">${isExitName}</small>
										</c:if>
									</div>
								</div>
								<div class="form-group mb-3">
									<label>Mô tả:</label>
									<f:textarea cssClass="form-control bg-white"
										value="${videoDetails.description}" path="description" />
									<small><f:errors path="description"
											cssClass="text-danger"></f:errors></small>
								</div>
								<c:if test="${role != null && role.contains('admin')}">
									<div class="form-group mb-3">
										<label>Nhận xét:</label>
										<f:textarea cssClass="form-control bg-white"
											value="${videoDetails.cmt}" path="cmt"
											style="border-radius: 20px;" />
										<small><f:errors path="cmt" cssClass="text-danger"></f:errors></small>
									</div>
								</c:if>
								<c:if test="${role.contains('giang-vien')}">
									<f:textarea cssStyle="display: none"
										cssClass="form-control bg-white" path="cmt" />
								</c:if>
								<div class="form-group mb-3">
									<c:if test="${videoDetails != null && role.contains('admin')}">
										<label>Trạng thái:</label>
										<f:select path="status" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<option value="1"
												<c:if test="${videoDetails.status == 1}"><c:out value="selected" /></c:if>>Đang
												kiểm duyệt</option>
											<option value="0"
												<c:if test="${videoDetails.status == 0}"><c:out value="selected" /></c:if>>Không
												đạt yêu cầu</option>
											<option value="2"
												<c:if test="${videoDetails.status == 2}"><c:out value="selected" /></c:if>>Đạt
												yêu cầu</option>
										</f:select>
										<small><f:errors path="status" cssClass="text-danger"></f:errors></small>
									</c:if>
									<c:if
										test="${videoDetails != null && role.contains('giang-vien')}">
										<f:input path="status" type="hidden"
											value="${videoDetails.status}" />
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<a
											href="/quan-tri/khoa-hoc/danh-sach-video?id=${videoDetails.course.id == null ? courseId : videoDetails.course.id}"
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
	function readURL(input, typeFile) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#thumbnail').attr('src', e.target.result).width(100);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
	
	$('#addVideoBtn').click(function () {
	    $(this).parents().find('#addVideoInput').click();
	});

	document.getElementById('addVideoInput').onchange = e => {
	    const file = e.target.files[0];
	    const url = URL.createObjectURL(file);
	    const li = '<video id="video" controls="controls" src="' + url + '" type="video/mp4" height="200px"></video>'
	    $('#video').remove();
	    $('.video').append(li);
	};
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
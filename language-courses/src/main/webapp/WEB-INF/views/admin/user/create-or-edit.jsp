<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<div class="container-fluid">
		<c:if test="${userDetails != null}">
			<c:set var="actionTitle" value="Chỉnh sửa thông tin hồ sơ"></c:set>
			<c:set var="col" value="col-12"></c:set>
			<c:set var="formAction" value="/quan-tri/chinh-sua-ho-so"></c:set>
		</c:if>
		<c:if test="${userDetails == null}">
			<c:set var="actionTitle" value="Thêm mới người dùng"></c:set>
			<c:set var="col" value="col-12 col-sm-12 col-md-6 col-lg-6"></c:set>
			<c:set var="formAction" value="/quan-tri/nguoi-dung/them-moi"></c:set>
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
							<f:form action="${formAction}" method="post" cssClass="user"
								modelAttribute="userDTO" enctype="multipart/form-data">
								<c:if test="${userDetails == null}">
									<f:input path="status" value="1" type="hidden" />
								</c:if>
								<f:input path="id" value="${userDetails.id}" type="hidden" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Họ và tên:</label>
										<f:input path="name" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.name}" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Avatar:</label>
										<f:input type="file" id="avatarInput"
											onchange="readURL(this);" cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="fileImage" />
										<p>
											<c:if test="${userDetails.avatar.contains('https://')}">
												<img style="width: 100px;" id="avatar"
													src="${userDetails.avatar}" alt="avatar" />
											</c:if>
											<c:if
												test="${userDetails != null && !userDetails.avatar.contains('https://')}">
												<img style="width: 100px;" id="avatar"
													src="/image-file/avatar_default.png" alt="avatar" />
											</c:if>
											<c:if test="${userDetails == null}">
												<img style="width: 100px;" id="avatar"
													src="/image-file/avatar_default.png" alt="avatar" />
											</c:if>
										</p>
										<label for="avatarInput" class="border p-2">Chọn
											Avatar</label>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Ngày sinh:</label>
										<c:if test="${userDetails == null}">
											<f:input path="dob" type="date"
												cssClass="form-control form-control-user bg-white" />
										</c:if>
										<c:if test="${userDetails != null}">
											<fmt:formatDate value="${userDetails.dobDate}"
												var="dobString" pattern="yyyy-MM-dd" />
											<f:input type="text" path="dob"
												cssClass="form-control form-control-user bg-white"
												value="${dobString}" />
										</c:if>
										<small><f:errors path="dob" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giới tính:</label><br>
										<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="gender"
												value="0" ${userDetails.gender == 0 ? "checked" : "" }
												${userDetails == null ? "checked" : "" } /> <label
												class="form-check-label">Nữ</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="gender"
												value="1" ${userDetails.gender == 1 ? "checked" : "" } /> <label
												class="form-check-label">Nam</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="gender"
												value="2" ${userDetails.gender == 2 ? "checked" : "" } /> <label
												class="form-check-label">Khác</label>
										</div>
										<small><f:errors path="gender" cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<c:if test="${userDetails != null}">
											<f:input path="email" type="hidden"
												cssClass="form-control form-control-user bg-white"
												value="${userDetails.email}" />
											<label>Tên tài khoản:</label>
											<f:input path="userName" type="text"
												cssClass="form-control form-control-user bg-white"
												value="${userDetails.userName}" />
											<small><f:errors path="userName"
													cssClass="text-danger"></f:errors></small>
										</c:if>
										<c:if test="${userDetails == null}">
											<label>Email:</label>
											<f:input path="email" type="text"
												cssClass="form-control form-control-user bg-white"
												value="${userDetails.email}" />
											<small><f:errors path="email" cssClass="text-danger"></f:errors></small>
											<c:if test="${duplicateEmailErr != null}">
												<small class="text-danger"> <c:out
														value="${duplicateEmailErr}" />
												</small>
											</c:if>
										</c:if>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Số điện thoại:</label>
										<f:input path="phoneNumber" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.phoneNumber}" />
										<small><f:errors path="phoneNumber"
												cssClass="text-danger"></f:errors></small>
										<c:if test="${duplicatePhoneNumberErr != null}">
											<small class="text-danger"> <c:out
													value="${duplicatePhoneNumberErr}" />
											</small>
										</c:if>
									</div>
								</div>
								<c:if test="${userDetails == null}">
									<div class="form-group row">
										<div class="${col} mb-3 mb-sm-0">
											<label>Tên tài khoản:</label>
											<f:input path="userName" type="text"
												cssClass="form-control form-control-user bg-white"
												value="${userDetails.userName}" />
											<small><f:errors path="userName"
													cssClass="text-danger"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Mật khẩu:</label>
											<f:input path="password" type="password"
												cssClass="form-control form-control-user bg-white" />
											<small><f:errors path="password"
													cssClass="text-danger"></f:errors></small>
										</div>
									</div>
								</c:if>
								<c:if test="${userDetails != null}">
									<f:input path="password" value="${userDetails.password}"
										type="hidden" />
								</c:if>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Youtube:</label>
										<f:input path="youtubeLink" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.youtubeLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Facebook:</label>
										<f:input path="facebookLink" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.facebookLink}" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
										<label>Instagram:</label>
										<f:input path="instagramLink" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.instagramLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>Twitter:</label>
										<f:input path="twitterLink" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.twitterLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>LinkedIn:</label>
										<f:input path="inLink" type="text"
											cssClass="form-control form-control-user bg-white"
											value="${userDetails.inLink}" />
									</div>
								</div>
								<c:if test="${role != null && !role.contains('admin')}">
									<f:input path="roleId" type="hidden"
										value="${userDetails.roleId}" />
									<f:input path="status" type="hidden"
										value="${userDetails.status}" />
								</c:if>
								<c:if
									test="${role.contains('admin') || enableSelectElement == true}">
									<div class="form-group">
										<label>Vai trò:</label>
										<f:select path="roleId" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<c:forEach items="${roleList}" var="role">
												<option value="${role.id}"
													${userDetails.roleId == role.id ? "selected" : ""}>${role.name}</option>
											</c:forEach>
										</f:select>
									</div>
									<c:if test="${userDetails != null}">
										<div class="form-group">
											<label>Trạng thái:</label>
											<f:select path="status" cssClass="form-control"
												style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
												<option value="1"
													<c:if test="${userDetails.status == 1}"><c:out value="selected" /></c:if>>Đang
													hoạt động</option>
												<option value="0"
													<c:if test="${userDetails.status == 0}"><c:out value="selected" /></c:if>>Đã
													hủy</option>
											</f:select>
										</div>
									</c:if>
								</c:if>
								<div class="form-group row">
									<button type="submit"
										class="btn btn-primary btn-user btn-block mt-4">Lưu</button>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#avatar').attr('src', e.target.result).width(100);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
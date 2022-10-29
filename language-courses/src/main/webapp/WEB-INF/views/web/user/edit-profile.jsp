<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>

<div class="container-fluid">
	<div class="container">
		<h1 class="text-center text-uppercase">Chỉnh sửa hồ sơ</h1>
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form action="/chinh-sua-ho-so" method="post" cssClass="user"
								modelAttribute="userDTO" enctype="multipart/form-data">
								<f:input path="id" value="${userDetails.id}" type="hidden" />
								<f:input path="email" type="hidden"
									cssClass="form-control rounded-pill bg-white"
									value="${userDetails.email}" />
								<c:if test="${userDetails != null}">
									<f:input path="password" value="${userDetails.password}"
										type="hidden" />
								</c:if>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Họ và tên:</label>
										<f:input path="name" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.name}" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Avatar:</label>
										<f:input type="file" id="avatarInput"
											onchange="readURL(this);" cssStyle="display: none"
											class="form-control rounded-pill bg-white" path="fileImage" />
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
										</p>
										<label for="avatarInput" class="border p-2">Chọn
											Avatar</label>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Ngày sinh:</label>
										<c:if test="${userDetails != null}">
											<fmt:formatDate value="${userDetails.dobDate}"
												var="dobString" pattern="yyyy-MM-dd" />
											<f:input type="text" path="dob"
												cssClass="form-control rounded-pill bg-white"
												value="${dobString}" />
										</c:if>
										<small><f:errors path="dob" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Giới tính:</label><br>
										<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="gender"
												value="0" ${userDetails.gender == 0 ? "checked" : "" } /> <label
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
										<label>Tên tài khoản:</label>
										<f:input path="userName" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.userName}" />
										<small><f:errors path="userName"
												cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Số điện thoại:</label>
										<f:input path="phoneNumber" type="text"
											cssClass="form-control rounded-pill bg-white"
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
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Youtube:</label>
										<f:input path="youtubeLink" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.youtubeLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Facebook:</label>
										<f:input path="facebookLink" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.facebookLink}" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
										<label>Instagram:</label>
										<f:input path="instagramLink" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.instagramLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>Twitter:</label>
										<f:input path="twitterLink" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.twitterLink}" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4">
										<label>LinkedIn:</label>
										<f:input path="inLink" type="text"
											cssClass="form-control rounded-pill bg-white"
											value="${userDetails.inLink}" />
									</div>
								</div>
								<c:if test="${role != null && !role.contains('admin')}">
									<f:input path="roleId" type="hidden"
										value="${userDetails.roleId}" />
									<f:input path="status" type="hidden"
										value="${userDetails.status}" />
								</c:if>
								<div class="form-group row">
									<button type="submit"
										class="btn btn-primary rounded-pill btn-block mt-4">Lưu</button>
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
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
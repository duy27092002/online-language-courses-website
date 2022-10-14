<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa</title>
<link href="/web/img/edukate_logo.ico" rel="icon">
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<h1 class="text-center">Chỉnh sửa thông tin website</h1>
			<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-12">
							<div class="p-5">
								<f:form action="/quan-tri/thong-tin-website/chinh-sua" method="post"
									cssClass="user" modelAttribute="aboutDTO">
									<f:input type="hidden" path="id" value="${about.id}" />
									<f:input type="hidden" path="status" value="${about.status}" />
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Tên website:</label>
											<f:input path="name" type="text"
												class="form-control form-control-user bg-white"
												value="${about.name}" />
											<small><f:errors path="name" style="color:red;"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Email:</label>
											<f:input path="email" type="text"
												class="form-control form-control-user bg-white"
												value="${about.email}" />
											<small><f:errors path="email" style="color:red;"></f:errors></small>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Số điện thoại:</label>
											<f:input path="phoneNumber" type="text"
												class="form-control form-control-user bg-white"
												value="${about.phoneNumber}" />
											<small><f:errors path="phoneNumber"
													style="color:red;"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Địa chỉ:</label>
											<f:input path="location" type="text"
												class="form-control form-control-user bg-white"
												value="${about.location}" />
											<small><f:errors path="location" style="color:red;"></f:errors></small>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Youtube:</label>
											<f:input path="youtubeLink" type="text"
												class="form-control form-control-user bg-white"
												value="${about.youtubeLink}" />
											<small><f:errors path="youtubeLink"
													style="color:red;"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Facebook:</label>
											<f:input path="facebookLink" type="text"
												class="form-control form-control-user bg-white"
												value="${about.facebookLink}" />
											<small><f:errors path="facebookLink"
													style="color:red;"></f:errors></small>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
											<label>Instagram:</label>
											<f:input path="instagramLink" type="text"
												class="form-control form-control-user bg-white"
												value="${about.instagramLink}" />
											<small><f:errors path="instagramLink"
													style="color:red;"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-4 col-lg-4">
											<label>Twitter:</label>
											<f:input path="twitterLink" type="text"
												class="form-control form-control-user bg-white"
												value="${about.twitterLink}" />
											<small><f:errors path="twitterLink"
													style="color:red;"></f:errors></small>
										</div>
										<div class="col-12 col-sm-12 col-md-4 col-lg-4">
											<label>LinkedIn:</label>
											<f:input path="inLink" type="text"
												class="form-control form-control-user bg-white"
												value="${about.inLink}" />
											<small><f:errors path="inLink" style="color:red;"></f:errors></small>
										</div>
									</div>
									<button type="submit" class="btn btn-primary btn-user btn-block mt-5">Lưu</button>
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
</body>
</html>
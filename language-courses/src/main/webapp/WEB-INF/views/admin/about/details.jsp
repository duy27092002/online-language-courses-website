<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin Website</title>
<link href="/web/img/edukate_logo.ico" rel="icon">
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<h1 class="text-center">Thông tin website</h1>
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
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Tên website:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.name}" disabled="disabled" />
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Email:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.email}" disabled="disabled" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Số điện thoại:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.phoneNumber}" disabled="disabled" />
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Địa chỉ:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.location}" disabled="disabled" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
											<label>Youtube:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.youtubeLink}" disabled="disabled" />
										</div>
										<div class="col-12 col-sm-12 col-md-6 col-lg-6">
											<label>Facebook:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.facebookLink}" disabled="disabled" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3 mb-sm-0">
											<label>Instagram:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.instagramLink}" disabled="disabled" />
										</div>
										<div class="col-12 col-sm-12 col-md-4 col-lg-4">
											<label>Twitter:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.twitterLink}" disabled="disabled" />
										</div>
										<div class="col-12 col-sm-12 col-md-4 col-lg-4">
											<label>LinkedIn:</label> <input
												class="form-control form-control-user bg-white"
												value="${about.inLink}" disabled="disabled" />
										</div>
									</div>
									<p class="mb-0">
										<small>Cập nhật bởi: ${about.modifiedBy}</small>
									</p>
									<small>Vào lúc: <fmt:formatDate
											value="${about.modifiedDate}" pattern="HH:mm:ss dd/MM/yyyy" /></small>
									<a href="/quan-tri/thong-tin-website/chinh-sua?id=${about.id}"
										class="btn btn-primary btn-user btn-block mt-4">Sửa</a>
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
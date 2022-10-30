<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<h1 class="text-center mb-3">Tổng quan</h1>

		<div class="row">

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-primary text-uppercase mb-1">
									Nhân viên</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Tổng:</strong>
											${activeEmployee + inactiveEmployee}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Còn
												làm việc:</strong> ${activeEmployee}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
												nghỉ:</strong> ${inactiveEmployee}</small>
									</p>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-users fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-success text-uppercase mb-1">
									Giảng viên</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Tổng:</strong>
											${activeInstructor + inactiveInstructor}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Còn
												làm việc:</strong> ${activeInstructor}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
												nghỉ:</strong> ${inactiveInstructor}</small>
									</p>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-users fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-info shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-info text-uppercase mb-1">Học
									viên</div>
								<div class="row no-gutters align-items-center">
									<div class="col-auto">
										<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Tổng:</strong>
													${activeStudent + inactiveStudent}</small>
											</p>
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Hoạt
														động:</strong> ${activeStudent}</small>
											</p>
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
														hủy:</strong> ${inactiveStudent}</small>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-user-graduate fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Pending Requests Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-warning shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-warning text-uppercase mb-1">
									Loại ngôn ngữ</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Tổng:</strong>
											${activeLanguage + inactiveLanguage}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Hoạt
												động:</strong> ${activeLanguage}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
												hủy:</strong> ${inactiveLanguage}</small>
									</p>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-globe fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-primary shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-primary text-uppercase mb-1">
									Khóa học</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Tổng:</strong>
											${releasedCourse + censorshipCourse + inactiveCourse}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đang
												phát hành:</strong> ${releasedCourse}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Chờ
												kiểm duyệt:</strong> ${censorshipCourse}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
												hủy:</strong> ${inactiveCourse}</small>
									</p>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-book-open fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-success shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-success text-uppercase mb-1">
									Đánh giá khóa học</div>
								<div class="h5 mb-0 font-weight-bold text-gray-800">
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><Strong>Tổng:</Strong>
											${activeEvaluated + censorshipEvaluated + inactiveEvaluated}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Hiển
												thị:</strong> ${activeEvaluated}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Chờ
												kiểm duyệt:</strong> ${censorshipEvaluated}</small>
									</p>
									<p class="mb-0">
										<small><i
											class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Ẩn:</strong>
											${inactiveEvaluated}</small>
									</p>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-star fa-2x text-gray-300"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Earnings (Monthly) Card Example -->
			<div class="col-xl-4 col-md-6 mb-4">
				<div class="card border-left-info shadow h-100 py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div
									class="text-xs font-weight-bold text-info text-uppercase mb-1">Phản
									hồi</div>
								<div class="row no-gutters align-items-center">
									<div class="col-auto">
										<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><Strong>Tổng:</Strong>
													${activeFeedback + censorshipFeedback + inactiveFeedback}</small>
											</p>
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
														trả lời:</strong> ${activeFeedback}</small>
											</p>
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Chờ
														kiểm duyệt:</strong> ${censorshipFeedback}</small>
											</p>
											<p class="mb-0">
												<small><i
													class="fas fa-caret-right fa-1x text-body-300 mr-2"></i><strong>Đã
														hủy:</strong> ${inactiveFeedback}</small>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="col-auto">
								<i class="fas fa-comment fa-2x text-gray-300"></i>
							</div>
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
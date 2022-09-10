<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<c:if test="${param['trang'] == null}">
	<c:url var="roleAPI" value="/api/admin/role" />
</c:if>
<c:if test="${param['trang'] != null}">
	<c:url var="roleAPI" value="/api/admin/role?page=${param['trang']}" />
</c:if>
<c:if
	test="${param['trang'] != null && param['sap-xep-theo'] != null && param['chieu'] != null}">
	<c:url var="roleAPI"
		value="/api/admin/role?page=${param['trang']}&order-by=${param['sap-xep-theo']}&order-type=${param['chieu']}" />
</c:if>
<c:url var="roleURL" value="/quan-tri/vai-tro" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách vai trò</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
	<!-- Main Content -->
	<div id="content">

		<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
		<!-- Begin Page Content -->
		<div class="container-fluid">
			<a class="btn btn-sm btn-primary mb-4"
				href="/quan-tri/vai-tro/them-moi"><i class="fas fa-plus mr-2"></i>Thêm
				mới</a>

			<div class="table-responsive">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">Tên vai trò</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>
					<tbody id="role-table"></tbody>
				</table>
			</div>
		</div>
		<!-- /.container-fluid -->
		<%@include file="/WEB-INF/views/common/pagination.jsp"%>
	</div>
	<!-- End of Main Content -->
	<script>
		var url = "${roleAPI}";

		$
				.get(
						url,
						function(data) {
							var content = "";
							var list = data.resultList;
							$
									.each(
											list,
											function(i, e) {
												var getStatus = "";
												var formatStatus = "";

												if (e.status == 1) {
													formatStatus = "success";
													getStatus = "Hoạt động";
												} else {
													formatStatus = "danger";
													getStatus = "Đã hủy";
												}

												content = '<tr><td>'
														+ e.name
														+ '</td><td>'
														+ '<button disabled class="btn btn-sm btn-outline-' + formatStatus + '">'
														+ getStatus
														+ '</button>'
														+ '</td>'
														+ '<td><a href="/quan-tri/chinh-sua-vai-tro?id='
														+ e.id
														+ '">'
														+ '<i class="fas fa-edit" data-toggle="tooltip" title="Chỉnh sửa"></i></a></td>'
														+ '</tr>';
												$("#role-table").first()
														.before(content);
											});
						}).fail(function(error) {
					alert(error.responseJSON.message);
				});
	</script>
	<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
</body>
</html>
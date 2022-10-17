<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<c:if test="${resultList.keyword == null}">
	<c:set var="paginationParam"
		value="orderBy=${resultList.orderBy}&orderType=${resultList.orderType}"></c:set>
</c:if>
<c:if test="${resultList.keyword != null}">
	<c:set var="paginationParam"
		value="keyword=${resultList.keyword}&orderBy=${resultList.orderBy}&orderType=${resultList.orderType}"></c:set>
</c:if>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<c:if test="${resultList.page > 3 }">
			<li class="page-item"><a class="page-link"
				href="?page=1&${paginationParam}">Trang đầu</a></li>
		</c:if>
		<c:if test="${resultList.page > 1 }">
			<li class="page-item"><a class="page-link"
				href="?page=${resultList.page - 1}&${paginationParam}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach varStatus="i" begin="1" end="${resultList.totalOfPage}"
			step="1">
			<c:if test="${i.index != resultList.page }">
				<c:if
					test="${i.index < resultList.page + 3 && i.index > resultList.page - 3}">
					<li class="page-item"><a class="page-link"
						href="?page=${i.index}&${paginationParam}">${i.index}</a></li>
				</c:if>
			</c:if>

			<c:if test="${i.index == resultList.page }">
				<li class="page-item active"><a class="page-link"
					href="?page=${i.index}&${paginationParam}">${i.index}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${resultList.page < resultList.totalOfPage }">
			<li class="page-item"><a class="page-link"
				href="?page=${resultList.page + 1}&${paginationParam}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		<c:if test="${resultList.page + 3 <= resultList.totalOfPage }">
			<li class="page-item"><a class="page-link"
				href="?page=${resultList.totalOfPage}&${paginationParam}">Trang
					cuối</a></li>
		</c:if>
	</ul>
</nav>
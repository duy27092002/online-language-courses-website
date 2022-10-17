<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<c:if test="${roleList.keyword == null}">
	<c:set var="paginationParam"
		value="orderBy=${roleList.orderBy}&orderType=${roleList.orderType}"></c:set>
</c:if>
<c:if test="${roleList.keyword != null}">
	<c:set var="paginationParam"
		value="keyword=${roleList.keyword}&orderBy=${roleList.orderBy}&orderType=${roleList.orderType}"></c:set>
</c:if>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<c:if test="${roleList.page > 3 }">
			<li class="page-item"><a class="page-link"
				href="?page=1&${paginationParam}">Trang đầu</a></li>
		</c:if>
		<c:if test="${roleList.page > 1 }">
			<li class="page-item"><a class="page-link"
				href="?page=${roleList.page - 1}&${paginationParam}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach varStatus="i" begin="1" end="${roleList.totalOfPage}"
			step="1">
			<c:if test="${i.index != roleList.page }">
				<c:if
					test="${i.index < roleList.page + 3 && i.index > roleList.page - 3}">
					<li class="page-item"><a class="page-link"
						href="?page=${i.index}&${paginationParam}">${i.index}</a></li>
				</c:if>
			</c:if>

			<c:if test="${i.index == roleList.page }">
				<li class="page-item active"><a class="page-link"
					href="?page=${i.index}&${paginationParam}">${i.index}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${roleList.page < roleList.totalOfPage }">
			<li class="page-item"><a class="page-link"
				href="?page=${roleList.page + 1}&${paginationParam}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		<c:if test="${roleList.page + 3 <= roleList.totalOfPage }">
			<li class="page-item"><a class="page-link"
				href="?page=${roleList.totalOfPage}&${paginationParam}">Trang
					cuối</a></li>
		</c:if>
	</ul>
</nav>
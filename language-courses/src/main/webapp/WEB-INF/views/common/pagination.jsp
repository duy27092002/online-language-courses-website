<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<c:if test="${currentPage > 3 }">
			<li class="page-item"><a class="page-link"
				href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=1">Trang
					đầu</a></li>
		</c:if>
		<c:if test="${currentPage > 1 }">
			<li class="page-item"><a class="page-link"
				href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=${currentPage - 1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach varStatus="i" begin="1" end="${pageTotal}" step="1">
			<c:if test="${i.index != currentPage }">
				<c:if
					test="${i.index < currentPage + 3 && i.index > currentPage - 3}">
					<li class="page-item"><a class="page-link"
						href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=${i.index }">${i.index}</a></li>
				</c:if>
			</c:if>

			<c:if test="${i.index == currentPage }">
				<li class="page-item active"><a class="page-link"
					href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=${i.index }">${i.index}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${currentPage < pageTotal }">
			<li class="page-item"><a class="page-link"
				href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=${currentPage + 1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		<c:if test="${currentPage + 3 <= pageTotal }">
			<li class="page-item"><a class="page-link"
				href="?<c:out value="${parameter}"/>sort-param=${currentSort}&page=${pageTotal }">Trang
					cuối</a></li>
		</c:if>
	</ul>
</nav>
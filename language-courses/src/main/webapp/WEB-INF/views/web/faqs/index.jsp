<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/web/header.jsp"%>
<!-- Contact Start -->
<div class="container-fluid">
	<h1 class="text-center">Câu hỏi thường gặp</h1>
	<div class="container py-5">
		<div class="accordion" id="accordionExample">
			<c:forEach items="${activeFAQsList}" var="faq">
				<div class="card">
					<div class="card-header" id="heading-${faq.id}">
						<h2 class="mb-0">
							<button
								class="text-decoration-none btn btn-link btn-block text-left"
								type="button" data-toggle="collapse"
								data-target="#collapse-${faq.id}" aria-expanded="true"
								aria-controls="collapse-${faq.id}">${faq.question}</button>
						</h2>
					</div>

					<div id="collapse-${faq.id}" class="collapse show"
						aria-labelledby="heading-${faq.id}"
						data-parent="#accordionExample">
						<div class="card-body">${faq.answer}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<!-- Contact End -->
<%@include file="/WEB-INF/views/common/web/footer.jsp"%>
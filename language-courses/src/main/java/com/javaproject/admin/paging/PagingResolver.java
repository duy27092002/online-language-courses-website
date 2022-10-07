package com.javaproject.admin.paging;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.exception.BadRequestException;

public class PagingResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(PagingParam.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		try {
			PagingParam pagingParam = parameter.getParameterAnnotation(PagingParam.class);
			String getPath = pagingParam.path();

			String pageParam = webRequest.getParameter("page");
			String pageStr = pageParam == null ? "" : pageParam.trim();
			Integer getPage = pageStr.matches("^\\d+$") ? Integer.valueOf(pageStr) : pagingParam.page();

			String pageSizeParam = webRequest.getParameter("pageSize");
			String pageSizeStr = pageSizeParam == null ? "" : pageSizeParam.trim();
			Integer getPageSize = pageSizeStr.matches("^\\d+$") ? Integer.valueOf(pageSizeStr) : pagingParam.pageSize();

			String orderByParam = webRequest.getParameter("orderBy");
			String orderByStr = orderByParam == null ? "" : orderByParam.trim();
			String getOrderBy = orderByStr.isEmpty() ? pagingParam.orderBy() : orderByStr;

			String orderTypeParam = webRequest.getParameter("orderType");
			String orderTypeStr = orderTypeParam == null ? "" : orderTypeParam.trim();
			String getOrderType = orderTypeStr.isEmpty() ? pagingParam.orderType() : orderTypeStr;

			String keywordParam = webRequest.getParameter("keyword");
			String getKeyword = null;
			if (keywordParam != null) {
				getKeyword = keywordParam.trim();
				if ("" == getKeyword) {
					throw new BadRequestException(null);
				}
			}

			return new ResponseDataTableDTO(getPath, getPage, getPageSize, getKeyword, getOrderBy, getOrderType);
		} catch (Exception ex) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

}

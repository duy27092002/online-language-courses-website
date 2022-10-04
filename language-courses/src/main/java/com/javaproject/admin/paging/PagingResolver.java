package com.javaproject.admin.paging;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.javaproject.admin.dto.ResponseDataTableDTO;

public class PagingResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(PagingParam.class) != null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		PagingParam pagingParam = parameter.getParameterAnnotation(PagingParam.class);
		String getPath = pagingParam.path();

		String pageStr = webRequest.getParameter("page");
		int getPage = StringUtils.isEmpty(pageStr) ? pagingParam.page() : Integer.parseInt(pageStr);
		getPage = getPage <= 0 ? pagingParam.page() : getPage;

		String pageSizeStr = webRequest.getParameter("pageSize");
		int getPageSize = StringUtils.isEmpty(pageSizeStr) ? pagingParam.pageSize() : Integer.parseInt(pageSizeStr);
		getPageSize = getPageSize <= 0 ? pagingParam.pageSize() : getPageSize;

		String keyword = webRequest.getParameter("keyword");
		String orderBy = webRequest.getParameter("orderBy");
		String orderType = webRequest.getParameter("orderType");

		return new ResponseDataTableDTO(getPath, getPage, getPageSize, keyword, orderBy, orderType);
	}

}

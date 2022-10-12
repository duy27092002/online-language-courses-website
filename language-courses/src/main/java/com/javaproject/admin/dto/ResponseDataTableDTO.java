package com.javaproject.admin.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.javaproject.admin.repository.SearchingRepository;
import com.javaproject.admin.util.SortUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataTableDTO {
	private String path;

	private Integer page;

	private Integer pageSize;

	private Integer totalOfPage;

	private Integer totalOfItem;

	private String keyword;

	private String orderBy;

	private String orderType;

	private List<?> data;

	public ResponseDataTableDTO(String path, Integer page, Integer pageSize, String keyword, String orderBy,
			String orderType) {
		this.path = path;
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.orderBy = orderBy;
		this.orderType = orderType;
	}

	@SuppressWarnings("deprecation")
	public <T> ResponseDataTableDTO getList(SearchingRepository<?, ?> repository, Class<T> dtoObj, String keyword)
			throws Exception {
		SortUtil sortUtil = new SortUtil();
		Pageable pageable = PageRequest.of(page - 1, pageSize, sortUtil.handleSort(orderBy, orderType));
		List<T> resultList = new ArrayList<>();
		//List<?> getListByPage = null;
		Page<?> getListByPage = null;
		long total = 0;
		if (keyword == null) {
//			getListByPage = repository.findAll(pageable).getContent();
//			total = repository.count();
			getListByPage = repository.getAllList(pageable);
		} else {
			getListByPage = repository.getSearchList(keyword, pageable);
//			total = repository.getSearchList(keyword, null).size() ;
		}
		// test
		total = getListByPage.getTotalElements();
		for (Object object : getListByPage) {
			T dto = dtoObj.newInstance();
			BeanUtils.copyProperties(object, dto);
			resultList.add(dto);
		}
		this.setData(resultList);
		this.setTotalOfItem((int) total);
		this.setTotalOfPage((int) Math.ceil((double) total / pageSize));
		return this;
	}
}

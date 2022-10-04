package com.javaproject.admin.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.admin.util.SortUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataTableDTO {
	private String path;

	private int page;

	private int pageSize;

	private int totalOfPage;

	private int totalOfItem;

	private String keyword;

	private String orderBy;

	private String orderType;

	private List<?> data;

	public ResponseDataTableDTO(String path, int page, int pageSize, String keyword, String orderBy,
			String orderType) {
		this.path = path;
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.orderBy = orderBy;
		this.orderType = orderType;
	}

	public ResponseDataTableDTO list(JpaRepository<?, ?> repository) throws Exception {
		SortUtil sortUtil = new SortUtil();
		Pageable pageable = PageRequest.of(this.getPage() - 1, this.getPageSize(),
				sortUtil.handleSort(this.getOrderBy(), this.getOrderType()));
		this.setData(repository.findAll(pageable).getContent());
		long total = repository.count();
		this.setTotalOfItem((int) total);
		this.setTotalOfPage((int) Math.ceil((double) total / this.getPageSize()));
		return this;
	}
}

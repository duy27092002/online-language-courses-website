package com.javaproject.admin.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.javaproject.admin.entity.Video;
import com.javaproject.admin.repository.SearchingRepository;
import com.javaproject.admin.repository.VideoRepository;
import com.javaproject.admin.util.SortUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataTableDTO {
	private Long id;

	private String path;

	private Integer page;

	private Integer pageSize;

	private Integer totalOfPage;

	private Integer totalOfItem;

	private String keyword;

	private String orderBy;

	private String orderType;

	private List<?> data;

	public ResponseDataTableDTO(String path, Long id, Integer page, Integer pageSize, String keyword, String orderBy,
			String orderType) {
		this.path = path;
		this.id = id;
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
		Page<?> getListByPage = null;
		long total = 0;
		if (keyword == null) {
			getListByPage = repository.getAllList(pageable);
		} else {
			getListByPage = repository.getSearchList(keyword, pageable);
		}
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

	public ResponseDataTableDTO getList(VideoRepository repository, Long id, String keyword) throws Exception {
		SortUtil sortUtil = new SortUtil();
		Pageable pageable = PageRequest.of(page - 1, pageSize, sortUtil.handleSort(orderBy, orderType));
		List<VideoDTO> resultList = new ArrayList<>();
		Page<Video> getListByPage = null;
		long total = 0;
		if (keyword == null) {
			try {
				getListByPage = repository.getAllList(id, pageable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			getListByPage = repository.getSearchList(id, keyword, pageable);
		}
		total = getListByPage.getTotalElements();
		for (Video videoEntity : getListByPage) {
			VideoDTO dto = new VideoDTO();
			BeanUtils.copyProperties(videoEntity, dto);
			resultList.add(dto);
		}
		this.setData(resultList);
		this.setTotalOfItem((int) total);
		this.setTotalOfPage((int) Math.ceil((double) total / pageSize));
		return this;
	}
}

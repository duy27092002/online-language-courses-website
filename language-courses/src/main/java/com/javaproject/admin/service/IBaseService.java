package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface IBaseService<T> {
	// lấy toàn bộ danh sách + lấy danh sách tìm kiếm + phân trang
	List<T> getList(String keyword, Pageable pageable);

	// thêm mới + cập nhật dữ liệu
	T save(T dto);

	// tính tổng số trang
	int getTotalPage(int pageSize);

	// lấy chi tiết bản ghi theo id
	List<T> getDetails(Long id);
}

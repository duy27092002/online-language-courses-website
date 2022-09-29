package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.VideoDTO;

public interface IVideoService {
	List<VideoDTO> getList(String keyword, Pageable pageable);
	
	List<VideoDTO> getDetails(Long id, String videoName);
	
	VideoDTO save(VideoDTO dto);
	
	int getTotalPage(int pageSize);
}

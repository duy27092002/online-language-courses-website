package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.VideoDTO;

public interface IVideoService extends IBaseService<VideoDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;

	List<VideoDTO> getVideoByIdOrName(Long id, String videoName);
	
	VideoDTO update(VideoDTO dto);
	
	List<VideoDTO> getListByCourseId(Long courseId);
	
	List<VideoDTO> getListByCourseIdAndUserId(Long courseId, Long userId);
	
	List<Long> getVideoIdListByCourse(List<Long> courseIdList);
}

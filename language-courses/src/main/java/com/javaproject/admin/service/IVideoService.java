package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.VideoDTO;

public interface IVideoService extends IBaseService<VideoDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;

	List<VideoDTO> getVideoByIdOrName(Long id, String videoName);
	
	VideoDTO update(VideoDTO dto);
}

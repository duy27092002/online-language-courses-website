package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.VideoDTO;

public interface IVideoService extends IBaseService<VideoDTO> {
	List<VideoDTO> getVideoByIdOrName(Long id, String videoName);
}

package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface IFeedbackService extends IBaseService<FeedbackDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	List<FeedbackDTO> getListByStatus(int status);
}

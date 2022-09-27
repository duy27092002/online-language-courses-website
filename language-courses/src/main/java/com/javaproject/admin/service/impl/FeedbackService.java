package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.entity.Feedback;
import com.javaproject.admin.repository.FeedbackRepository;
import com.javaproject.admin.service.IFeedbackService;

@Service
@Transactional
public class FeedbackService implements IFeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepo;

	@Override
	public List<FeedbackDTO> getList(String keyword, Pageable pageable) {
		List<Feedback> getList = null;
		if (keyword == null) {
			getList = feedbackRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			getList = feedbackRepo.getSearchListByKeyword(keyword, pageable);
		}
		List<FeedbackDTO> resultList = new ArrayList<>();
		for (Feedback item : getList) {
			FeedbackDTO dto = new FeedbackDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public FeedbackDTO save(FeedbackDTO dto) {
		Long getFeedbackId = dto.getId();
		Feedback feedbackEntity = null;
		if (getFeedbackId == null) {
			feedbackEntity = new Feedback();
		} else {
			feedbackEntity = feedbackRepo.findById(getFeedbackId).get();
		}
		BeanUtils.copyProperties(dto, feedbackEntity);
		BeanUtils.copyProperties(feedbackRepo.save(feedbackEntity), dto);
		return dto;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) feedbackRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public List<FeedbackDTO> getDetails(Long id) {
		Feedback getFeedbackById = feedbackRepo.findById(id).get();
		List<FeedbackDTO> getInfo = new ArrayList<>();
		FeedbackDTO dto = new FeedbackDTO();
		BeanUtils.copyProperties(getFeedbackById, dto);
		getInfo.add(dto);
		return getInfo;
	}

}

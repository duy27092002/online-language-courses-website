package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.FAQsDTO;
import com.javaproject.admin.entity.FAQs;
import com.javaproject.admin.repository.FAQsRepository;
import com.javaproject.admin.service.IFAQsService;

@Service
@Transactional
public class FAQsService implements IFAQsService {
	@Autowired
	private FAQsRepository faqsRepo;

	@Override
	public List<FAQsDTO> getList(String keyword, Pageable pageable) {
		List<FAQs> getList = null;
		if (keyword == null) {
			getList = faqsRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			getList = faqsRepo.getSearchListByQuestion(keyword, pageable);
		}
		List<FAQsDTO> resultList = new ArrayList<>();
		for (FAQs item : getList) {
			FAQsDTO faqsDTO = new FAQsDTO();
			BeanUtils.copyProperties(item, faqsDTO);
			resultList.add(faqsDTO);
		}
		return resultList;
	}

	@Override
	public FAQsDTO save(FAQsDTO dto) {
		Long getFAQsId = dto.getId();
		FAQs faqsEntity = null;
		if (getFAQsId == null) {
			faqsEntity = new FAQs();
		} else {
			faqsEntity = faqsRepo.findById(getFAQsId).get();
		}
		BeanUtils.copyProperties(dto, faqsEntity);
		BeanUtils.copyProperties(faqsRepo.save(faqsEntity), dto);
		return dto;
	}

	@Override
	public List<FAQsDTO> getDetails(Long id) {
		FAQs getFAQsEntityById = faqsRepo.findById(id).get();
		List<FAQsDTO> faqsDTO = new ArrayList<>();
		FAQsDTO dto = new FAQsDTO();
		BeanUtils.copyProperties(getFAQsEntityById, dto);
		faqsDTO.add(dto);
		return faqsDTO;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) faqsRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public FAQsDTO getQuestion(String question) {
		FAQsDTO dto = new FAQsDTO();
		FAQs getQuestion = faqsRepo.findByQuestion(question);
		if (getQuestion != null) {
			BeanUtils.copyProperties(getQuestion, dto);
			return dto;
		}
		return null;
	}

}

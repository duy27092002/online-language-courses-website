package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.LanguageDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.entity.Language;
import com.javaproject.admin.repository.LanguageRepository;
import com.javaproject.admin.service.ILanguageService;

@Service
@Transactional
public class LanguageService implements ILanguageService {
	@Autowired
	private LanguageRepository languageRepo;
	
	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(languageRepo, new LanguageDTO().getClass(),
				responseDataTableDTO.getKeyword());
	}

	@Override
	public LanguageDTO save(LanguageDTO dto) {
		Long getLanguageId = dto.getId();
		Language languageEntity = null;
		if (getLanguageId == null) {
			languageEntity = new Language();
		} else {
			languageEntity = languageRepo.findById(getLanguageId).get();
		}
		BeanUtils.copyProperties(dto, languageEntity);
		BeanUtils.copyProperties(languageRepo.save(languageEntity), dto);
		return dto;
	}

	@Override
	public List<LanguageDTO> getDetails(Long id) {
		Language getLanguageById = languageRepo.findById(id).get();
		List<LanguageDTO> getInfo = new ArrayList<>();
		LanguageDTO dto = new LanguageDTO();
		BeanUtils.copyProperties(getLanguageById, dto);
		getInfo.add(dto);
		return getInfo;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) languageRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public LanguageDTO getLanguageByName(String name) {
		Language getLanguageByName = languageRepo.findByName(name);
		if (getLanguageByName != null) {
			LanguageDTO dto = new LanguageDTO();
			BeanUtils.copyProperties(getLanguageByName, dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<LanguageDTO> getList(String keyword, Pageable pageable) {
		return null;
	}

}

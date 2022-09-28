package com.javaproject.admin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.Language;
import com.javaproject.admin.entity.SkillLevel;
import com.javaproject.admin.repository.CourseRepository;
import com.javaproject.admin.repository.LanguageRepository;
import com.javaproject.admin.repository.SkillLevelRepository;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.service.IImageService;

@Service
@Transactional
public class CourseService implements ICourseService {
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private LanguageRepository languagerepo;
	
	@Autowired
	private SkillLevelRepository sklRepo;
	
	@Autowired
	private IImageService imageService;

	@Override
	public List<CourseDTO> getList(String keyword, Pageable pageable) {
		List<CourseDTO> resultList = new ArrayList<>();
		List<Course> entityList = null;
		if (keyword == null) {
			entityList = courseRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			entityList = courseRepo.getSearchList(keyword, pageable);
		}
		for (Course item : entityList) {
			CourseDTO dto = new CourseDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public CourseDTO save(CourseDTO dto) {
		Long getCourseId = dto.getId();
		Course courseEntity = null;
		if (getCourseId == null) {
			courseEntity = new Course();
		} else {
			courseEntity = courseRepo.findById(getCourseId).get();
			courseEntity.getSkillLevelList().clear();
		}
		
		try {
			dto.setThumbnail(getImageURL(dto.getThumbnailFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Language getLanguageById = languagerepo.findById(dto.getLanguageId()).get();
		courseEntity.setLanguage(getLanguageById);
		
		Long[] getSkillLevelIds = dto.getSkillLevelIds();
		for (Long sklId : getSkillLevelIds) {
			SkillLevel getSklById = sklRepo.findById(sklId).get();
			courseEntity.addSkillLevel(getSklById);
		}
		
		BeanUtils.copyProperties(dto, courseEntity);
		BeanUtils.copyProperties(courseRepo.save(courseEntity), dto);
		return dto;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getCountOfRecord = (int) courseRepo.count();
		return getCountOfRecord % pageSize == 0 ? getCountOfRecord / pageSize : ((getCountOfRecord / pageSize) + 1);
	}

	@Override
	public List<CourseDTO> getDetails(Long id) {
		Course getCourseById = courseRepo.findById(id).get();
		CourseDTO courseDTO = new CourseDTO();
		BeanUtils.copyProperties(getCourseById, courseDTO);
		List<CourseDTO> resultList = new ArrayList<>();
		resultList.add(courseDTO);
		return resultList;
	}

	@Override
	public CourseDTO getCourseByName(String name) {
		Course getCourseByName = courseRepo.findByName(name);
		if (getCourseByName != null) {
			CourseDTO courseDTO = new CourseDTO();
			BeanUtils.copyProperties(getCourseByName, courseDTO);
			return courseDTO;
		}
		return null;
	}
	
	private String getImageURL(MultipartFile files) throws IOException {
		String fileName = imageService.save(files);
        String imageUrl = imageService.getImageUrl(fileName);

        return imageUrl;
	}

}

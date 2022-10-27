package com.javaproject.admin.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.Language;
import com.javaproject.admin.entity.SkillLevel;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.repository.CourseRepository;
import com.javaproject.admin.repository.LanguageRepository;
import com.javaproject.admin.repository.SkillLevelRepository;
import com.javaproject.admin.repository.UserRepository;
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
	private UserRepository userRepo;

	@Autowired
	private IImageService imageService;

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(courseRepo, new CourseDTO().getClass(), responseDataTableDTO.getKeyword());
	}

	@Override
	public List<CourseDTO> getList(String keyword, Pageable pageable) {
		return null;
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
			if (dto.getThumbnailFile().getOriginalFilename().isEmpty()) {
				dto.setThumbnail(
						"https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513");
			} else {
				dto.setThumbnail(getImageURL(dto.getThumbnailFile()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start convert dob string to dob date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDisTimeDate = null;
		Date endDisTimeDate = null;
		Date releaseTimeDate = null;
		try {
			String releaseTimeStr = null;
			if (!dto.getReleaseTimeStr().isEmpty()) {
				releaseTimeStr = dto.getReleaseTimeStr();
				releaseTimeDate = df.parse(releaseTimeStr);
			}

			String startDisTimeStr = null;
			if (!dto.getStartDisTimeStr().isEmpty()) {
				startDisTimeStr = dto.getStartDisTimeStr();
				startDisTimeDate = df.parse(startDisTimeStr);
			}

			String endDisTimeStr = null;
			if (!dto.getEndDisTimeStr().isEmpty()) {
				endDisTimeStr = dto.getEndDisTimeStr();
				endDisTimeDate = df.parse(endDisTimeStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// end convert

		BeanUtils.copyProperties(dto, courseEntity);

		Language getLanguageById = languagerepo.findById(dto.getLanguageId()).get();
		courseEntity.setLanguage(getLanguageById);

		Long[] getSkillLevelIds = dto.getSkillLevelIds();
		for (Long sklId : getSkillLevelIds) {
			SkillLevel getSklById = sklRepo.findById(sklId).get();
			courseEntity.addSkillLevel(getSklById);
		}

		Long[] getInstructorIds = dto.getInstructorIds();
		for (Long instructorId : getInstructorIds) {
			User getInstructorById = userRepo.findById(instructorId).get();
			courseEntity.addInstructor(getInstructorById);
		}

		courseEntity.setReleaseTime(releaseTimeDate);
		courseEntity.setStartDiscountTime(startDisTimeDate);
		courseEntity.setEndDiscountTime(endDisTimeDate);

		BeanUtils.copyProperties(courseRepo.save(courseEntity), dto);
		return dto;
	}

	@Override
	public CourseDTO update(CourseDTO dto) {
		Long getCourseId = dto.getId();
		Course courseEntity = courseRepo.findById(getCourseId).get();
		courseEntity.getSkillLevelList().clear();

		try {
			if (dto.getThumbnailFile().getOriginalFilename().isEmpty()) {
				dto.setThumbnail(courseEntity.getThumbnail());
			} else {
				dto.setThumbnail(getImageURL(dto.getThumbnailFile()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start convert dob string to dob date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDisTimeDate = null;
		Date endDisTimeDate = null;
		Date releaseTimeDate = null;
		try {
			String releaseTimeStr = null;
			if (!dto.getReleaseTimeStr().isEmpty()) {
				releaseTimeStr = dto.getReleaseTimeStr();
				releaseTimeDate = df.parse(releaseTimeStr);
			}

			String startDisTimeStr = null;
			if (!dto.getStartDisTimeStr().isEmpty()) {
				startDisTimeStr = dto.getStartDisTimeStr();
				startDisTimeDate = df.parse(startDisTimeStr);
			}

			String endDisTimeStr = null;
			if (!dto.getEndDisTimeStr().isEmpty()) {
				endDisTimeStr = dto.getEndDisTimeStr();
				endDisTimeDate = df.parse(endDisTimeStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// end convert

		BeanUtils.copyProperties(dto, courseEntity);

		Language getLanguageById = languagerepo.findById(dto.getLanguageId()).get();
		courseEntity.setLanguage(getLanguageById);

		Long[] getSkillLevelIds = dto.getSkillLevelIds();
		for (Long sklId : getSkillLevelIds) {
			SkillLevel getSklById = sklRepo.findById(sklId).get();
			courseEntity.addSkillLevel(getSklById);
		}

		Long[] getInstructorIds = dto.getInstructorIds();
		for (Long instructorId : getInstructorIds) {
			User getInstructorById = userRepo.findById(instructorId).get();
			courseEntity.addInstructor(getInstructorById);
		}

		courseEntity.setReleaseTime(releaseTimeDate);
		courseEntity.setStartDiscountTime(startDisTimeDate);
		courseEntity.setEndDiscountTime(endDisTimeDate);

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

	@Override
	public List<CourseDTO> getListByStatus(int status) {
		List<Course> activeCourseEntityList = courseRepo.findByStatus(status);
		List<CourseDTO> resultList = new ArrayList<>();
		for (Course course : activeCourseEntityList) {
			CourseDTO dto = new CourseDTO();
			BeanUtils.copyProperties(course, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	private String getImageURL(MultipartFile files) throws IOException {
		String fileName = imageService.save(files);
		String imageUrl = imageService.getImageUrl(fileName);

		return imageUrl;
	}

	@Override
	public List<CourseDTO> getListByLanguageIdAndStatus(Long languageId, int status) {
		List<Course> courseListByLanguageId = courseRepo.findByLanguageIdAndStatus(languageId, status);
		List<CourseDTO> resultList = new ArrayList<>();
		for (Course entity : courseListByLanguageId) {
			CourseDTO dto = new CourseDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<CourseDTO> getListByCourseId(List<Long> courseIdList) {
		List<Course> courseListByUserId = new ArrayList<>();
		for (Long courseId : courseIdList) {
			Course getCourseById = courseRepo.findById(courseId).get();
			courseListByUserId.add(getCourseById);
		}
		List<CourseDTO> resultList = new ArrayList<>();
		for (Course entity : courseListByUserId) {
			CourseDTO dto = new CourseDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

}

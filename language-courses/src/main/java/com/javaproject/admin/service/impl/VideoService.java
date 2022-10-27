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

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.VideoDTO;
import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.entity.Video;
import com.javaproject.admin.repository.CourseRepository;
import com.javaproject.admin.repository.UserRepository;
import com.javaproject.admin.repository.VideoRepository;
import com.javaproject.admin.service.IVideoService;
import com.javaproject.service.IImageService;

@Service
@Transactional
public class VideoService implements IVideoService {
	@Autowired
	private VideoRepository videoRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private IImageService imageService;

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(videoRepo, responseDataTableDTO.getId(), responseDataTableDTO.getKeyword());
	}

	@Override
	public List<VideoDTO> getList(String keyword, Pageable pageable) {
		return null;
	}

	@Override
	public List<VideoDTO> getVideoByIdOrName(Long id, String videoName) {
		Video videoEntity = null;
		if (id != null && videoName == null) {
			videoEntity = videoRepo.findById(id).get();
		} else {
			videoEntity = videoRepo.findByName(videoName);
		}

		if (videoEntity != null) {
			List<VideoDTO> resultList = new ArrayList<>();
			VideoDTO dto = new VideoDTO();
			BeanUtils.copyProperties(videoEntity, dto);
			resultList.add(dto);
			return resultList;
		}
		return null;
	}

	@Override
	public VideoDTO save(VideoDTO dto) {
		Long getVideoId = dto.getId();
		Video videoEntity = null;
		if (getVideoId == null) {
			videoEntity = new Video();
		} else {
			videoEntity = videoRepo.findById(getVideoId).get();
		}

		try {
			dto.setVideoFile(getFileURL(dto.getVideoFileName()));
			if (dto.getThumbnailImg().getOriginalFilename().isEmpty()) {
				dto.setThumbnail(
						"https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b");
			} else {
				dto.setThumbnail(getFileURL(dto.getThumbnailImg()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		BeanUtils.copyProperties(dto, videoEntity);
		Course getCourseById = courseRepo.findById(dto.getCourseId()).get();
		videoEntity.setCourse(getCourseById);
		User getUserById = userRepo.findById(dto.getUserId()).get();
		videoEntity.setUser(getUserById);
		try {
			videoEntity = videoRepo.save(videoEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		BeanUtils.copyProperties(videoEntity, dto);
		return dto;
	}

	@Override
	public VideoDTO update(VideoDTO dto) {
		Long getVideoId = dto.getId();
		Video videoEntity = videoRepo.findById(getVideoId).get();
		try {
			if (dto.getThumbnailImg().getOriginalFilename().isEmpty()) {
				dto.setThumbnail(videoEntity.getThumbnail());
			} else {
				dto.setThumbnail(getFileURL(dto.getThumbnailImg()));
			}

			if (dto.getVideoFileName().getOriginalFilename().isEmpty()) {
				dto.setVideoFile(videoEntity.getVideoFile());
			} else {
				dto.setVideoFile(getFileURL(dto.getVideoFileName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		BeanUtils.copyProperties(dto, videoEntity);
		Course getCourseById = courseRepo.findById(dto.getCourseId()).get();
		videoEntity.setCourse(getCourseById);
		User getUserById = userRepo.findById(dto.getUserId()).get();
		videoEntity.setUser(getUserById);
		BeanUtils.copyProperties(videoRepo.save(videoEntity), dto);
		return dto;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getCountOfRecord = (int) videoRepo.count();
		return getCountOfRecord % pageSize == 0 ? getCountOfRecord / pageSize : ((getCountOfRecord / pageSize) + 1);
	}

	private String getFileURL(MultipartFile files) throws IOException {
		String fileName = imageService.save(files);
		String fileUrl = imageService.getImageUrl(fileName);
		return fileUrl;
	}

	@Override
	public List<VideoDTO> getDetails(Long id) {
		return null;
	}

	@Override
	public List<VideoDTO> getListByCourseId(Long courseId) {
		List<Video> videoListByCourseId = videoRepo.findByCourseId(courseId);
		List<VideoDTO> resultList = new ArrayList<>();
		for (Video entity : videoListByCourseId) {
			VideoDTO dto = new VideoDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<VideoDTO> getListByCourseIdAndUserId(Long courseId, Long userId) {
		return null;
	}
}

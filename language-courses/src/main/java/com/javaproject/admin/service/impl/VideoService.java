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
	public List<VideoDTO> getList(String keyword, Pageable pageable) {
		List<VideoDTO> resultList = new ArrayList<>();
		List<Video> entityList = null;
		if (keyword == null) {
			entityList = videoRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			entityList = videoRepo.getSearchList(keyword, pageable);
		}
		for (Video video : entityList) {
			VideoDTO dto = new VideoDTO();
			BeanUtils.copyProperties(video, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<VideoDTO> getDetails(Long id, String videoName) {
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
			dto.setVideoFile(getFileURL(dto.getVideoName()));
			dto.setThumbnail(getFileURL(dto.getThumbnailImg()));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		dto.setVideoFile("https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/videoplayback.mp4?alt=media&token=775baef3-bc53-406c-af09-7e297445d7b0");
//		dto.setThumbnail("https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/tieng-anh-nha-hang.jpg?alt=media&token=bef8480a-40d4-4c62-a27d-9eb92fd23c22");

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
}

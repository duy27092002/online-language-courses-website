package com.javaproject.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.VideoDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.IVideoService;
import com.javaproject.util.SecurityUtil;

@Controller(value = "VideoControllerOfAdmin")
@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_giang-vien')")
public class VideoController extends BaseController {
	@Autowired
	private IVideoService videoService;

	@Autowired
	private ICourseService courseService;

	@GetMapping(value = "/quan-tri/khoa-hoc/danh-sach-video")
	public String viewListPage(@PagingParam(path = "/quan-tri/khoa-hoc/danh-sach-video") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách video", model);
		try {
			ResponseDataTableDTO resultList = videoService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
			model.addAttribute("courseId", resDTDTO.getId());
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/video/list";
	}

	@GetMapping(value = "/quan-tri/video/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới video bài giảng", model);
		model.addAttribute("videoDTO", new VideoDTO());
		model.addAttribute("instructorId", SecurityUtil.getPrincipal().getUserId());
		return "/admin/video/create-or-edit";
	}

	@PostMapping(value = "/quan-tri/video/them-moi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String create(@Valid @ModelAttribute("videoDTO") VideoDTO videoDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", videoDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/quan-tri/video/chi-tiet")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/quan-tri/video/chinh-sua")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "create-or-edit", redirectModel, model);
	}

	@PostMapping(value = "/quan-tri/video/chinh-sua", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_giang-vien')")
	public String update(@Valid @ModelAttribute("videoDTO") VideoDTO videoDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", videoDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết video bài giảng", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa video bài giảng", model);
		}

		try {
			Long getVideoId = Long.parseLong(id);

			// kiểm tra xem id của video có thuộc danh sách video do giảng viên phụ trách
			// hay không?
			// Chỉ áp dụng cho vai trò giảng viên
			if (SecurityUtil.getAuthorities().contains("giang-vien")) {
				List<Long> getCourseIdListByInstructor = courseService
						.getCourseIdListByInstructorId(SecurityUtil.getPrincipal().getUserId());
				List<Long> getVidIdListByCourseOfInstructor = videoService
						.getVideoIdListByCourse(getCourseIdListByInstructor);
				if (!getVidIdListByCourseOfInstructor.contains(getVideoId)) {
					return viewErrorPage(redirectModel);
				}
			}

			VideoDTO videoDetails = videoService.getVideoByIdOrName(getVideoId, null).get(0);
			model.addAttribute("videoDetails", videoDetails);
			model.addAttribute("role", SecurityUtil.getAuthorities());
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/video/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("videoDTO") VideoDTO videoDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		VideoDTO getOldVideoById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới video bài giảng", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa video bài giảng", model);
			getOldVideoById = videoService.getVideoByIdOrName(videoDTO.getId(), null).get(0);
			model.addAttribute("role", SecurityUtil.getAuthorities());
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("create")) {
				model.addAttribute("courseIdAfterErr", videoDTO.getCourseId());
			} else {
				model.addAttribute("courseIdAfterErr", videoDTO.getCourseId());
				if (videoDTO.getVideoFileName().getOriginalFilename().isEmpty()) {
					model.addAttribute("oldVideo", getOldVideoById.getVideoFile());
				}
				model.addAttribute("oldThumbnail", getOldVideoById.getThumbnail());
				model.addAttribute("videoDetails", videoDTO);
				model.addAttribute("role", SecurityUtil.getAuthorities());
			}
			return "/admin/video/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			String getVideoName = null;
			List<VideoDTO> getVideoByName = new ArrayList<>();

			if (formAction.equalsIgnoreCase("create")) {
				getVideoName = videoDTO.getVideoFileName().getOriginalFilename();
				if (getVideoName.isEmpty()) {
					model.addAttribute("courseIdAfterErr", videoDTO.getCourseId());
					return doesNotVideo(model);
				}

				getVideoByName = videoService.getVideoByIdOrName(null, videoDTO.getName());
				if (getVideoByName != null) {
					model.addAttribute("courseIdAfterErr", videoDTO.getCourseId());
					return isExitName(model);
				}

				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldVideoById.getName().equalsIgnoreCase(videoDTO.getName())) {
					getVideoByName = videoService.getVideoByIdOrName(null, videoDTO.getName());
					if (getVideoByName != null) {
						model.addAttribute("courseIdAfterErr", videoDTO.getCourseId());
						if (videoDTO.getVideoFileName().getOriginalFilename().isEmpty()) {
							model.addAttribute("oldVideo", getOldVideoById.getVideoFile());
						}
						model.addAttribute("oldThumbnail", getOldVideoById.getThumbnail());
						model.addAttribute("videoDetails", videoDTO);
						model.addAttribute("role", SecurityUtil.getAuthorities());
						return isExitName(model);
					}
				}

				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			VideoDTO getVideoAfterSave = null;
			if (formAction.equalsIgnoreCase("create")) {
				getVideoAfterSave = videoService.save(videoDTO);
			} else {
				getVideoAfterSave = videoService.update(videoDTO);
			}

			if (getVideoAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/quan-tri/khoa-hoc/danh-sach-video?id=" + videoDTO.getCourseId();
	}

	// bắt lỗi không chọn video
	private String doesNotVideo(Model model) {
		model.addAttribute("doesNotVideo", "Vui lòng chọn video");
		return "/admin/video/create-or-edit";
	}

	// hiển thị lỗi trùng tên video bài giảng
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên video bài giảng này đã tồn tại");
		return "/admin/video/create-or-edit";
	}
}

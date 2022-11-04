package com.javaproject.admin.controller;

import java.util.Objects;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.ChangePasswordDTO;
import com.javaproject.admin.dto.NotificationResponseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IRoleService;
import com.javaproject.admin.service.IUserService;
import com.javaproject.util.SecurityUtil;

@Controller(value = "UserControllerOfAdmin")
@RequestMapping(value = "/quan-tri")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@GetMapping(value = "/nguoi-dung/danh-sach")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String viewEmployeeListPage(@PagingParam(path = "nguoi-dung") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách người dùng", model);
		try {
			ResponseDataTableDTO resultList = userService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/user/list";
	}

	@GetMapping(value = "/nguoi-dung/them-moi")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới người dùng", model);
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("enableSelectElement", true);
		model.addAttribute("roleList", roleService.activeRoleList());
		return "/admin/user/create-or-edit";
	}

	@PostMapping(value = "/nguoi-dung/them-moi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String create(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", userDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = { "/ho-so-cua-toi", "/nguoi-dung/chi-tiet" })
	public String viewProfilePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		model.addAttribute("enableEditAction", true);
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua-ho-so")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "create-or-edit", redirectModel, model);
	}

	@PostMapping(value = "/chinh-sua-ho-so", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", userDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/nguoi-dung/cap-nhat-trang-thai")
	public String updateStatus(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		try {
			Long getUserId = Long.parseLong(id);
			UserDTO getUserById = userService.getDetails(getUserId).get(0);
			if (getUserById.getStatus() == 0) {
				getUserById.setStatus(1);
			} else if (getUserById.getStatus() == 1) {
				getUserById.setStatus(0);
			}
			getUserById.setFileImage(null);
			return save("update-status", getUserById, null, redirectModel, model);
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
	}

	@GetMapping(value = "/doi-mat-khau")
	public String viewChangePassPage(Model model) {
		setViewTitleOrFaviconAttribute("Đổi mật khẩu", model);
		model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
		return "/admin/user/change-password";
	}

	@PostMapping(value = "/doi-mat-khau")
	public String changePassword(@Valid @ModelAttribute("changePasswordDTO") ChangePasswordDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (!Objects.equals(dto.getNewPass(), dto.getReNewPass())) {
			bindingResult.rejectValue("reNewPass", "error.dto", "Mật khẩu mới không trùng khớp");
		}

		if (bindingResult.hasErrors()) {
			return "/admin/user/change-password";
		}

		NotificationResponseDTO notiResDTO = userService.changePassword(dto);
		redirectModel.addFlashAttribute("message", notiResDTO.getMess());
		return "redirect:/quan-tri/doi-mat-khau";
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Hồ sơ chi tiết", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa thông tin hồ sơ", model);
			model.addAttribute("roleList", roleService.activeRoleList());
			model.addAttribute("userDTO", new UserDTO());
			model.addAttribute("role", SecurityUtil.getAuthorities());
		}

		try {
			Long getUserId = Long.parseLong(id);

			// Nếu id đăng nhập bằng với id trên url thì hiển thị dữ liệu, ngược lại thì báo
			// lỗi
			// Admin có quyền xem chi tiết thông tin của tất cả người dùng
			if (getUserId == SecurityUtil.getPrincipal().getUserId()
					|| SecurityUtil.getAuthorities().contains("admin")) {
				model.addAttribute("userDetails", userService.getDetails(getUserId).get(0));
			} else {
				return viewErrorPage(redirectModel);
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/user/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("userDTO") UserDTO userDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		UserDTO getOldUserById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới người dùng", model);
		} else if (formAction.equalsIgnoreCase("update")) {
			setViewTitleOrFaviconAttribute("Chỉnh sửa thông tin hồ sơ", model);
			getOldUserById = userService.getDetails(userDTO.getId()).get(0);
		}

		if (bindingResult != null && bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				userDTO.setAvatar(getOldUserById.getAvatar());
				model.addAttribute("userDetails", userDTO);
				model.addAttribute("role", SecurityUtil.getAuthorities());
			} else if (formAction.equalsIgnoreCase("create")) {
				model.addAttribute("enableSelectElement", true);
			}
			model.addAttribute("roleList", roleService.activeRoleList());
			return "/admin/user/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			if (!formAction.equalsIgnoreCase("update-status")) {
				UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(userDTO.getEmail(), null);

				UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null,
						userDTO.getPhoneNumber());

				model.addAttribute("roleList", roleService.activeRoleList());
				if (formAction.equalsIgnoreCase("create")) {
					if (getUserByEmail != null) {
						model.addAttribute("enableSelectElement", true);
						return checkDuplicateField(null, null, model, "email");
					}
					if (getUserByPhoneNumber != null) {
						model.addAttribute("enableSelectElement", true);
						return checkDuplicateField(null, null, model, "phoneNumber");
					}
					successMess = "Thêm mới thành công";
					errorMess = "Thêm mới thất bại";
				} else if (formAction.equalsIgnoreCase("update")) {
					if (!getOldUserById.getPhoneNumber().equalsIgnoreCase(userDTO.getPhoneNumber())) {
						if (getUserByPhoneNumber != null) {
							return checkDuplicateField(userDTO, getOldUserById, model, "phoneNumber");
						}
					}
					successMess = "Chỉnh sửa thành công";
					errorMess = "Chỉnh sửa thất bại";
				}
			}

			UserDTO getUserAfterSave = null;
			if (formAction.equalsIgnoreCase("create")) {
				getUserAfterSave = userService.save(userDTO);
				redirectPageAfterSave(getUserAfterSave, redirectModel, successMess);
				return "redirect:/quan-tri/nguoi-dung/danh-sach";
			} else if (formAction.contains("update")) {
				getUserAfterSave = userService.update(userDTO);
				redirectPageAfterSave(getUserAfterSave, redirectModel, successMess);
				if (formAction.contains("status")) {
					return "redirect:/quan-tri/nguoi-dung/danh-sach";
				} else {
					return "redirect:/quan-tri/ho-so-cua-toi?id=" + userDTO.getId();
				}
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
			if (formAction.equalsIgnoreCase("create")) {
				return "redirect:/quan-tri/nguoi-dung/danh-sach";
			} else {
				return "redirect:/quan-tri/ho-so-cua-toi?id=" + userDTO.getId();
			}
		}
		return null;
	}

	// hiển thị lỗi trùng tên người dùng
	private String checkDuplicateField(UserDTO userDTO, UserDTO oldUserById, Model model, String field) {
		if (userDTO != null && oldUserById != null) {
			userDTO.setAvatar(oldUserById.getAvatar());
			model.addAttribute("userDetails", userDTO);
			model.addAttribute("role", SecurityUtil.getAuthorities());
		}
		if (field.equalsIgnoreCase("email")) {
			model.addAttribute("duplicateEmailErr", "Email này đã tồn tại");
		} else if (field.equalsIgnoreCase("phoneNumber")) {
			model.addAttribute("duplicatePhoneNumberErr", "Số điện thoại này đã tồn tại");
		}
		return "/admin/user/create-or-edit";
	}

	private void redirectPageAfterSave(UserDTO userAfterSave, RedirectAttributes redirectModel, String successMess) {
		if (userAfterSave != null) {
			redirectNotification(redirectModel, successMess, "success");
		}
	}
}

package com.javaproject.web.controller;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.ChangePasswordDTO;
import com.javaproject.admin.dto.NotificationResponseDTO;
import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IUserService;
import com.javaproject.util.SecurityUtil;

@Controller(value = "UserControllerOfWeb")
public class UserController extends BaseController {
	@Autowired
	private ILanguageService languageService;

	@Autowired
	private IUserService userService;

	@GetMapping(value = { "/dang-nhap" })
	public String viewSignInPage(Model model) {
		setViewTitleOrGetWebDetails("Đăng nhập", model);
		return "/sign-in";
	}

	@GetMapping(value = { "/dang-ky", "/dang-ki" })
	public String viewSignUpPage(Model model) {
		setViewTitleOrGetWebDetails("Đăng ký", model);
		model.addAttribute("userDTO", new UserDTO());
		return "/sign-up";
	}

	@PostMapping(value = "/dang-ky", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			Model model) {
		setViewTitleOrGetWebDetails("Đăng ký", model);
		if (bindingResult.hasErrors()) {
			return "/sign-up";
		}

		UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(userDTO.getEmail(), null);
		if (getUserByEmail != null) {
			model.addAttribute("emailErrorMess", "Email đã tồn tại");
			return "/sign-up";
		}

		UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null, userDTO.getPhoneNumber());
		if (getUserByPhoneNumber != null) {
			model.addAttribute("phoneErrorMess", "Số điện thoại đã tồn tại");
			return "/sign-up";
		}

		try {
			UserDTO getNewUser = userService.save(userDTO);
			if (getNewUser != null) {
				return "redirect:/sign-in";
			} else {
				model.addAttribute("errorMess", "Đăng ký thất bại. Vui lòng thử lại!");
				return "/sign-up";
			}
		} catch (Exception ex) {
			model.addAttribute("errorMess", "Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
			return "/sign-up";
		}
	}

	@GetMapping(value = { "/danh-sach-giang-vien" })
	public String instructorList(Model model) {
		setViewTitleOrGetWebDetails("Giảng viên", model);
		model.addAttribute("instructorList", userService.getListByRoleIdAndStatus(2, 1));
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/instructor/index";
	}

	@GetMapping(value = "/ho-so-cua-toi")
	public String viewProfilePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "profile-details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua-ho-so")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "edit-profile", redirectModel, model);
	}

	@PostMapping(value = "/chinh-sua-ho-so", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(userDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("profile-details")) {
			setViewTitleOrGetWebDetails("Hồ sơ của tôi", model);
		} else {
			setViewTitleOrGetWebDetails("Chỉnh sửa hồ sơ", model);
			model.addAttribute("userDTO", new UserDTO());
			model.addAttribute("role", SecurityUtil.getAuthorities());
		}
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));

		try {
			Long getUserId = Long.parseLong(id);
			model.addAttribute("userDetails", userService.getDetails(getUserId).get(0));
		} catch (Exception e) {
			return viewErrorPage();
		}
		return "/web/user/" + action;
	}

	private String save(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrGetWebDetails("Chỉnh sửa hồ sơ", model);
		UserDTO getOldUserById = userService.getDetails(userDTO.getId()).get(0);

		if (bindingResult != null && bindingResult.hasErrors()) {
			userDTO.setAvatar(getOldUserById.getAvatar());
			model.addAttribute("userDetails", userDTO);
			model.addAttribute("role", SecurityUtil.getAuthorities());
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
			return "/web/user/edit-profile";
		}

		String successMess = null;
		String errorMess = null;
		try {
			UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(userDTO.getEmail(), null);

			if (!getOldUserById.getEmail().equalsIgnoreCase(userDTO.getEmail())) {
				if (getUserByEmail != null) {
					userDTO.setAvatar(getOldUserById.getAvatar());
					model.addAttribute("userDetails", userDTO);
					return checkDuplicateField(model, "email");
				}
			}

			UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null, userDTO.getPhoneNumber());

			if (!getOldUserById.getPhoneNumber().equalsIgnoreCase(userDTO.getPhoneNumber())) {
				if (getUserByPhoneNumber != null) {
					userDTO.setAvatar(getOldUserById.getAvatar());
					model.addAttribute("userDetails", userDTO);
					return checkDuplicateField(model, "phoneNumber");
				}
			}

			successMess = "Chỉnh sửa thành công";
			errorMess = "Chỉnh sửa thất bại";

			UserDTO getUserAfterSave = userService.update(userDTO);
			if (getUserAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
			return "redirect:/ho-so-cua-toi?id=" + userDTO.getId();
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
			return "redirect:/ho-so-cua-toi?id=" + userDTO.getId();
		}
	}

	private String checkDuplicateField(Model model, String field) {
		if (field.equalsIgnoreCase("email")) {
			model.addAttribute("duplicateEmailErr", "Email này đã tồn tại");
		} else if (field.equalsIgnoreCase("phoneNumber")) {
			model.addAttribute("duplicatePhoneNumberErr", "Số điện thoại này đã tồn tại");
		}
		model.addAttribute("role", SecurityUtil.getAuthorities());
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/user/edit-profile";
	}

	@GetMapping(value = "/doi-mat-khau")
	public String viewChangePassPage(Model model) {
		setViewTitleOrGetWebDetails("Đổi mật khẩu", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
		return "/web/user/change-password";
	}

	@PostMapping(value = "/doi-mat-khau")
	public String changePassword(@Valid @ModelAttribute("changePasswordDTO") ChangePasswordDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (!Objects.equals(dto.getNewPass(), dto.getReNewPass())) {
			bindingResult.rejectValue("reNewPass", "error.changePasswordDTO", "Mật khẩu mới không trùng khớp");
		}

		if (bindingResult.hasErrors()) {
			setViewTitleOrGetWebDetails("Đổi mật khẩu", model);
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
			return "/web/user/change-password";
		}

		NotificationResponseDTO notiResDTO = userService.changePassword(dto);
		redirectModel.addFlashAttribute("message", notiResDTO.getMess());
		return "redirect:/doi-mat-khau";
	}
}

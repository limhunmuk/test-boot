package com.test.boot.domain.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.boot.domain.notice.dto.NoticeCreateRequestDTO;
import com.test.boot.domain.notice.dto.NoticeDetailResponseDTO;
import com.test.boot.domain.notice.dto.NoticePageResponseDTO;
import com.test.boot.domain.notice.dto.NoticeUpdateRequestDTO;
import com.test.boot.domain.notice.service.NoticeService;
import com.test.boot.domain.uploadfile.service.UploadFileService;

import jakarta.validation.Valid;

@Controller
public class NoticeController {

	private static final int PAGE_SIZE = 10;
	private static final String TARGET_TYPE = "NOTICE";

	private final NoticeService noticeService;
	private final UploadFileService uploadFileService;

	public NoticeController(NoticeService noticeService, UploadFileService uploadFileService) {
		this.noticeService = noticeService;
		this.uploadFileService = uploadFileService;
	}

	@GetMapping("/notices")
	public String notices(@RequestParam(defaultValue = "1") int page, Model model) {
		NoticePageResponseDTO noticePage = noticeService.getNoticePage(page, PAGE_SIZE);
		model.addAttribute("noticePage", noticePage);
		return "notice/list";
	}

	@GetMapping("/notices/new")
	public String newNoticeForm(Model model) {
		model.addAttribute("noticeCreateRequest", new NoticeCreateRequestDTO());
		return "notice/form";
	}

	@PostMapping("/notices")
	public String createNotice(@Valid @ModelAttribute("noticeCreateRequest") NoticeCreateRequestDTO request,
			BindingResult bindingResult,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {
		if (bindingResult.hasErrors()) {
			return "notice/form";
		}
		Long noticeId = noticeService.createNotice(request);
		uploadFileService.saveFiles(TARGET_TYPE, noticeId, files);
		return "redirect:/notices/" + noticeId;
	}

	@GetMapping("/notices/{noticeId}")
	public String notice(@PathVariable Long noticeId, Model model) {
		noticeService.increaseViewCount(noticeId);
		model.addAttribute("notice", noticeService.getNotice(noticeId));
		model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, noticeId));
		return "notice/detail";
	}

	@GetMapping("/notices/{noticeId}/edit")
	public String editNoticeForm(@PathVariable Long noticeId, Model model) {
		NoticeDetailResponseDTO notice = noticeService.getNotice(noticeId);
		NoticeUpdateRequestDTO request = new NoticeUpdateRequestDTO();
		request.setTitle(notice.getTitle());
		request.setContent(notice.getContent());
		request.setImportantYn("Y".equals(notice.getImportantYn()));
		model.addAttribute("noticeId", noticeId);
		model.addAttribute("noticeUpdateRequest", request);
		model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, noticeId));
		return "notice/edit";
	}

	@PostMapping("/notices/{noticeId}/edit")
	public String updateNotice(@PathVariable Long noticeId,
			@Valid @ModelAttribute("noticeUpdateRequest") NoticeUpdateRequestDTO request,
			BindingResult bindingResult,
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("noticeId", noticeId);
			model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, noticeId));
			return "notice/edit";
		}
		noticeService.updateNotice(noticeId, request);
		uploadFileService.saveFiles(TARGET_TYPE, noticeId, files);
		return "redirect:/notices/" + noticeId;
	}

}

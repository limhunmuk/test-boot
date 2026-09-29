package kr.go.egov.pungdong.domain.postcomment.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentCreateRequestDTO;
import kr.go.egov.pungdong.domain.postcomment.service.PostCommentService;

import jakarta.validation.Valid;

@Controller
public class PostCommentController {

	private final PostCommentService postCommentService;

	public PostCommentController(PostCommentService postCommentService) {
		this.postCommentService = postCommentService;
	}

	@PostMapping("/posts/{articleId}/comments")
	public String createComment(@PathVariable Long articleId,
			@Valid @ModelAttribute("postCommentCreateRequest") PostCommentCreateRequestDTO request,
			BindingResult bindingResult,
			Principal principal,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("commentError", "댓글 내용을 입력해주세요.");
			return "redirect:/posts/" + articleId;
		}
		try {
			String regId = principal != null ? principal.getName() : "guest";
			postCommentService.createComment(articleId, request, regId);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("commentError", e.getMessage());
		}
		return "redirect:/posts/" + articleId;
	}

	@PostMapping("/posts/{articleId}/comments/{articleCommentId}/delete")
	public String deleteComment(@PathVariable Long articleId,
			@PathVariable Long articleCommentId,
			Principal principal,
			RedirectAttributes redirectAttributes) {
		try {
			String requesterId = principal != null ? principal.getName() : null;
			postCommentService.deleteComment(articleCommentId, requesterId);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("commentError", e.getMessage());
		}
		return "redirect:/posts/" + articleId;
	}

}
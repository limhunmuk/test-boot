package com.test.boot.domain.post.controller;

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

import com.test.boot.domain.post.dto.PostCreateRequestDTO;
import com.test.boot.domain.post.dto.PostDetailResponseDTO;
import com.test.boot.domain.post.dto.PostPageResponseDTO;
import com.test.boot.domain.post.dto.PostUpdateRequestDTO;
import com.test.boot.domain.post.service.PostService;
import com.test.boot.domain.uploadfile.service.UploadFileService;

import jakarta.validation.Valid;

@Controller
public class PostController {

	private static final int PAGE_SIZE = 10;
	private static final String TARGET_TYPE = "POST";

	private final PostService postService;
	private final UploadFileService uploadFileService;

	public PostController(PostService postService, UploadFileService uploadFileService) {
		this.postService = postService;
		this.uploadFileService = uploadFileService;
	}

	@GetMapping("/posts")
	public String posts(@RequestParam(defaultValue = "1") int page, Model model) {
		PostPageResponseDTO postPage = postService.getPostPage(page, PAGE_SIZE);
		model.addAttribute("postPage", postPage);
		return "post/list";
	}

	@GetMapping("/posts/new")
	public String newPostForm(Model model) {
		model.addAttribute("postCreateRequest", new PostCreateRequestDTO());
		return "post/form";
	}

	@PostMapping("/posts")
	public String createPost(@Valid @ModelAttribute("postCreateRequest") PostCreateRequestDTO request,
			BindingResult bindingResult,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) {
		if (bindingResult.hasErrors()) {
			return "post/form";
		}
		Long articleId = postService.createPost(request);
		uploadFileService.saveFiles(TARGET_TYPE, articleId, files);
		return "redirect:/posts/" + articleId;
	}

	@GetMapping("/posts/{articleId}")
	public String post(@PathVariable Long articleId, Model model) {
		postService.increaseViewCount(articleId);
		model.addAttribute("post", postService.getPost(articleId));
		model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, articleId));
		return "post/detail";
	}

	@GetMapping("/posts/{articleId}/edit")
	public String editPostForm(@PathVariable Long articleId, Model model) {
		PostDetailResponseDTO post = postService.getPost(articleId);
		PostUpdateRequestDTO request = new PostUpdateRequestDTO();
		request.setTitle(post.getTitle());
		request.setContent(post.getContent());
		model.addAttribute("articleId", articleId);
		model.addAttribute("postUpdateRequest", request);
		model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, articleId));
		return "post/edit";
	}

	@PostMapping("/posts/{articleId}/edit")
	public String updatePost(@PathVariable Long articleId,
			@Valid @ModelAttribute("postUpdateRequest") PostUpdateRequestDTO request,
			BindingResult bindingResult,
			@RequestParam(value = "files", required = false) List<MultipartFile> files,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("articleId", articleId);
			model.addAttribute("files", uploadFileService.getUploadFileListByTarget(TARGET_TYPE, articleId));
			return "post/edit";
		}
		postService.updatePost(articleId, request);
		uploadFileService.saveFiles(TARGET_TYPE, articleId, files);
		return "redirect:/posts/" + articleId;
	}

}

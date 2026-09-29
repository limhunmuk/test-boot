package kr.go.egov.pungdong.domain.post.controller;

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

import kr.go.egov.pungdong.domain.post.dto.PostCreateRequestDTO;
import kr.go.egov.pungdong.domain.post.dto.PostDetailResponseDTO;
import kr.go.egov.pungdong.domain.post.dto.PostPageResponseDTO;
import kr.go.egov.pungdong.domain.post.dto.PostSearchConditionDTO;
import kr.go.egov.pungdong.domain.post.dto.PostUpdateRequestDTO;
import kr.go.egov.pungdong.domain.post.service.PostService;
import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentCreateRequestDTO;
import kr.go.egov.pungdong.domain.postcomment.service.PostCommentService;
import kr.go.egov.pungdong.domain.uploadfile.service.UploadFileService;
import kr.go.egov.pungdong.web.support.PaginationInfoFactory;

import jakarta.validation.Valid;

@Controller
public class PostController {

	private static final int PAGE_SIZE = 10;
	private static final String TARGET_TYPE = "POST";

	private final PostService postService;
	private final UploadFileService uploadFileService;
	private final PostCommentService postCommentService;

	public PostController(PostService postService, UploadFileService uploadFileService,
			PostCommentService postCommentService) {
		this.postService = postService;
		this.uploadFileService = uploadFileService;
		this.postCommentService = postCommentService;
	}

	@GetMapping("/posts")
	public String posts(@RequestParam(defaultValue = "1") int page,
			@ModelAttribute("condition") PostSearchConditionDTO condition, Model model) {
		PostPageResponseDTO postPage = postService.getPostPage(page, PAGE_SIZE, condition);
		model.addAttribute("postPage", postPage);
		model.addAttribute("paginationInfo",
				PaginationInfoFactory.create(postPage.getCurrentPage(), PAGE_SIZE, postPage.getTotalCount()));
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
		model.addAttribute("commentThreads", postCommentService.getCommentThreadsByArticleId(articleId));
		model.addAttribute("commentCount", postCommentService.getPostCommentListByArticleId(articleId).size());
		if (!model.containsAttribute("postCommentCreateRequest")) {
			model.addAttribute("postCommentCreateRequest", new PostCommentCreateRequestDTO());
		}
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

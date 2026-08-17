package com.test.boot.domain.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.boot.domain.post.dto.PostDetailResponseDTO;
import com.test.boot.domain.post.service.PostService;
import com.test.boot.domain.post.vo.PostVO;

@RestController
public class PostApiController {

	private final PostService postService;

	public PostApiController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/api/posts")
	public List<PostVO> posts() {
		return postService.getPostList();
	}

	@GetMapping("/api/posts/{articleId}")
	public PostDetailResponseDTO post(@PathVariable Long articleId) {
		return postService.getPost(articleId);
	}

}

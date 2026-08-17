package com.test.boot.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.boot.post.dto.PostDetailResponseDTO;

@RestController
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
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

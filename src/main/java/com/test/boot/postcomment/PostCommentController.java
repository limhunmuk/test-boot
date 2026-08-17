package com.test.boot.postcomment;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostCommentController {

	private final PostCommentService postCommentService;

	public PostCommentController(PostCommentService postCommentService) {
		this.postCommentService = postCommentService;
	}

	@GetMapping("/api/post-comments")
	public List<PostCommentVO> postComments() {
		return postCommentService.getPostCommentList();
	}

	@GetMapping("/api/posts/{articleId}/comments")
	public List<PostCommentVO> postComments(@PathVariable Long articleId) {
		return postCommentService.getPostCommentListByArticleId(articleId);
	}

}

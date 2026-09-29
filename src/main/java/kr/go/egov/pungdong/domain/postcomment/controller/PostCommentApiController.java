package kr.go.egov.pungdong.domain.postcomment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.go.egov.pungdong.domain.postcomment.service.PostCommentService;
import kr.go.egov.pungdong.domain.postcomment.vo.PostCommentVO;

@RestController
public class PostCommentApiController {

	private final PostCommentService postCommentService;

	public PostCommentApiController(PostCommentService postCommentService) {
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

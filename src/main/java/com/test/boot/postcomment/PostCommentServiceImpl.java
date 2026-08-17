package com.test.boot.postcomment;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PostCommentServiceImpl implements PostCommentService {

	private final PostCommentMapper postCommentMapper;

	public PostCommentServiceImpl(PostCommentMapper postCommentMapper) {
		this.postCommentMapper = postCommentMapper;
	}

	@Override
	public List<PostCommentVO> getPostCommentList() {
		return postCommentMapper.selectPostCommentList();
	}

	@Override
	public List<PostCommentVO> getPostCommentListByArticleId(Long articleId) {
		return postCommentMapper.selectPostCommentListByArticleId(articleId);
	}

}

package com.test.boot.domain.postcomment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.boot.domain.postcomment.mapper.PostCommentMapper;
import com.test.boot.domain.postcomment.vo.PostCommentVO;

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

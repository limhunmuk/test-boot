package com.test.boot.domain.postcomment.service;

import java.util.List;

import com.test.boot.domain.postcomment.vo.PostCommentVO;

public interface PostCommentService {

	List<PostCommentVO> getPostCommentList();

	List<PostCommentVO> getPostCommentListByArticleId(Long articleId);

}

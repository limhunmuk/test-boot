package com.test.boot.postcomment;

import java.util.List;

public interface PostCommentService {

	List<PostCommentVO> getPostCommentList();

	List<PostCommentVO> getPostCommentListByArticleId(Long articleId);

}

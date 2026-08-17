package com.test.boot.postcomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCommentMapper {

	List<PostCommentVO> selectPostCommentList();

	List<PostCommentVO> selectPostCommentListByArticleId(Long articleId);

}

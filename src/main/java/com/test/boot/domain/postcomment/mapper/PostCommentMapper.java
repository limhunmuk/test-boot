package com.test.boot.domain.postcomment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.boot.domain.postcomment.vo.PostCommentVO;

@Mapper
public interface PostCommentMapper {

	List<PostCommentVO> selectPostCommentList();

	List<PostCommentVO> selectPostCommentListByArticleId(Long articleId);

}

package com.test.boot.domain.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.boot.domain.post.vo.PostVO;

@Mapper
public interface PostMapper {

	List<PostVO> selectPostList();

	PostVO selectPost(Long articleId);

	List<PostVO> selectPostListPaged(@Param("offset") int offset, @Param("limit") int limit);

	long selectPostCount();

	void insertPost(PostVO post);

	void updatePost(PostVO post);

	void increaseViewCount(Long articleId);

}

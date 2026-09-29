package kr.go.egov.pungdong.domain.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.egov.pungdong.domain.post.dto.PostSearchConditionDTO;
import kr.go.egov.pungdong.domain.post.vo.PostVO;

@Mapper
public interface PostMapper {

	List<PostVO> selectPostList();

	PostVO selectPost(Long articleId);

	List<PostVO> selectPostListPaged(@Param("condition") PostSearchConditionDTO condition,
			@Param("offset") int offset, @Param("limit") int limit);

	long selectPostCount(@Param("condition") PostSearchConditionDTO condition);

	void insertPost(PostVO post);

	void updatePost(PostVO post);

	void increaseViewCount(Long articleId);

}

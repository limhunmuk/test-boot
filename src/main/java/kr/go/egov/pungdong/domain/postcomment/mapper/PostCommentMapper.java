package kr.go.egov.pungdong.domain.postcomment.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.go.egov.pungdong.domain.postcomment.vo.PostCommentVO;

@Mapper
public interface PostCommentMapper {

	List<PostCommentVO> selectPostCommentList();

	List<PostCommentVO> selectPostCommentListByArticleId(Long articleId);

	Optional<PostCommentVO> selectPostComment(Long articleCommentId);

	void insertPostComment(PostCommentVO comment);

	void deletePostComment(Long articleCommentId);

	int countReplies(Long parentCommentId);

}

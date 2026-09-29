package kr.go.egov.pungdong.domain.postcomment.service;

import java.util.List;

import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentCreateRequestDTO;
import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentThreadDTO;
import kr.go.egov.pungdong.domain.postcomment.vo.PostCommentVO;

public interface PostCommentService {

	List<PostCommentVO> getPostCommentList();

	List<PostCommentVO> getPostCommentListByArticleId(Long articleId);

	List<PostCommentThreadDTO> getCommentThreadsByArticleId(Long articleId);

	Long createComment(Long articleId, PostCommentCreateRequestDTO request, String regId);

	void deleteComment(Long articleCommentId, String requesterId);

}

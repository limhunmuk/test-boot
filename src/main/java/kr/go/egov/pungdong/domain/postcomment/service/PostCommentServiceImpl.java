package kr.go.egov.pungdong.domain.postcomment.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentCreateRequestDTO;
import kr.go.egov.pungdong.domain.postcomment.dto.PostCommentThreadDTO;
import kr.go.egov.pungdong.domain.postcomment.mapper.PostCommentMapper;
import kr.go.egov.pungdong.domain.postcomment.vo.PostCommentVO;

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

	@Override
	public List<PostCommentThreadDTO> getCommentThreadsByArticleId(Long articleId) {
		List<PostCommentVO> flatComments = postCommentMapper.selectPostCommentListByArticleId(articleId);

		Map<Long, PostCommentThreadDTO> threadsByCommentId = new LinkedHashMap<>();
		for (PostCommentVO comment : flatComments) {
			if (comment.getParentCommentId() == null) {
				threadsByCommentId.put(comment.getArticleCommentId(), new PostCommentThreadDTO(comment, new ArrayList<>()));
			} else {
				PostCommentThreadDTO thread = threadsByCommentId.get(comment.getParentCommentId());
				if (thread != null) {
					thread.getReplies().add(comment);
				}
			}
		}

		return new ArrayList<>(threadsByCommentId.values());
	}

	@Override
	public Long createComment(Long articleId, PostCommentCreateRequestDTO request, String regId) {
		Long parentCommentId = request.getParentCommentId();
		if (parentCommentId != null) {
			PostCommentVO parent = postCommentMapper.selectPostComment(parentCommentId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
			if (parent.getParentCommentId() != null) {
				throw new IllegalArgumentException("답글에는 답글을 달 수 없습니다.");
			}
		}

		PostCommentVO comment = new PostCommentVO();
		comment.setArticleId(articleId);
		comment.setParentCommentId(parentCommentId);
		comment.setContent(request.getContent());
		comment.setRegId(regId);
		postCommentMapper.insertPostComment(comment);
		return comment.getArticleCommentId();
	}

	@Override
	public void deleteComment(Long articleCommentId, String requesterId) {
		PostCommentVO comment = postCommentMapper.selectPostComment(articleCommentId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

		if (!comment.getRegId().equals(requesterId)) {
			throw new IllegalArgumentException("본인이 작성한 댓글만 삭제할 수 있습니다.");
		}

		if (comment.getParentCommentId() == null && postCommentMapper.countReplies(articleCommentId) > 0) {
			throw new IllegalArgumentException("답글이 있는 댓글은 삭제할 수 없습니다.");
		}

		postCommentMapper.deletePostComment(articleCommentId);
	}

}

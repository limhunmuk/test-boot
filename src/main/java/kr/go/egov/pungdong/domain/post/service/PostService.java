package kr.go.egov.pungdong.domain.post.service;

import java.util.List;

import kr.go.egov.pungdong.domain.post.dto.PostCreateRequestDTO;
import kr.go.egov.pungdong.domain.post.dto.PostDetailResponseDTO;
import kr.go.egov.pungdong.domain.post.dto.PostPageResponseDTO;
import kr.go.egov.pungdong.domain.post.dto.PostSearchConditionDTO;
import kr.go.egov.pungdong.domain.post.dto.PostUpdateRequestDTO;
import kr.go.egov.pungdong.domain.post.vo.PostVO;

public interface PostService {

	List<PostVO> getPostList();

	PostDetailResponseDTO getPost(Long articleId);

	PostPageResponseDTO getPostPage(int page, int size, PostSearchConditionDTO condition);

	Long createPost(PostCreateRequestDTO request);

	void updatePost(Long articleId, PostUpdateRequestDTO request);

	void increaseViewCount(Long articleId);

}

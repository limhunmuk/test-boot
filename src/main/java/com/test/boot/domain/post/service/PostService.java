package com.test.boot.domain.post.service;

import java.util.List;

import com.test.boot.domain.post.dto.PostCreateRequestDTO;
import com.test.boot.domain.post.dto.PostDetailResponseDTO;
import com.test.boot.domain.post.dto.PostPageResponseDTO;
import com.test.boot.domain.post.dto.PostUpdateRequestDTO;
import com.test.boot.domain.post.vo.PostVO;

public interface PostService {

	List<PostVO> getPostList();

	PostDetailResponseDTO getPost(Long articleId);

	PostPageResponseDTO getPostPage(int page, int size);

	Long createPost(PostCreateRequestDTO request);

	void updatePost(Long articleId, PostUpdateRequestDTO request);

	void increaseViewCount(Long articleId);

}

package com.test.boot.post;

import java.util.List;

import com.test.boot.post.dto.PostDetailResponseDTO;

public interface PostService {

	List<PostVO> getPostList();

	PostDetailResponseDTO getPost(Long articleId);

}

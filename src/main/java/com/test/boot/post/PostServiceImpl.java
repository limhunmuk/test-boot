package com.test.boot.post;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.boot.post.dto.PostDetailResponseDTO;

@Service
public class PostServiceImpl implements PostService {

	private final PostMapper postMapper;

	public PostServiceImpl(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	@Override
	public List<PostVO> getPostList() {
		return postMapper.selectPostList();
	}

	@Override
	public PostDetailResponseDTO getPost(Long articleId) {
		return PostDetailResponseDTO.from(postMapper.selectPost(articleId));
	}

}

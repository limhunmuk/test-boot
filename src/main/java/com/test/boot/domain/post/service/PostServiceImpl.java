package com.test.boot.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.boot.domain.post.dto.PostCreateRequestDTO;
import com.test.boot.domain.post.dto.PostDetailResponseDTO;
import com.test.boot.domain.post.dto.PostPageResponseDTO;
import com.test.boot.domain.post.dto.PostUpdateRequestDTO;
import com.test.boot.domain.post.mapper.PostMapper;
import com.test.boot.domain.post.vo.PostVO;

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

	@Override
	public PostPageResponseDTO getPostPage(int page, int size) {
		int currentPage = Math.max(1, page);
		int offset = (currentPage - 1) * size;
		List<PostVO> posts = postMapper.selectPostListPaged(offset, size);
		long totalCount = postMapper.selectPostCount();
		return PostPageResponseDTO.of(posts, currentPage, size, totalCount);
	}

	@Override
	public Long createPost(PostCreateRequestDTO request) {
		PostVO post = new PostVO();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setRegId("guest");
		postMapper.insertPost(post);
		return post.getArticleId();
	}

	@Override
	public void updatePost(Long articleId, PostUpdateRequestDTO request) {
		PostVO post = new PostVO();
		post.setArticleId(articleId);
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setModId("guest");
		postMapper.updatePost(post);
	}

	@Override
	public void increaseViewCount(Long articleId) {
		postMapper.increaseViewCount(articleId);
	}

}

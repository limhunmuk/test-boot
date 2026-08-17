package com.test.boot.domain.post.dto;

import java.util.List;

import com.test.boot.domain.post.vo.PostVO;

import lombok.Data;

@Data
public class PostPageResponseDTO {

	private List<PostVO> posts;
	private int currentPage;
	private int totalPages;
	private long totalCount;

	public static PostPageResponseDTO of(List<PostVO> posts, int currentPage, int size, long totalCount) {
		PostPageResponseDTO dto = new PostPageResponseDTO();
		dto.setPosts(posts);
		dto.setCurrentPage(currentPage);
		dto.setTotalCount(totalCount);
		dto.setTotalPages(Math.max(1, (int) Math.ceil((double) totalCount / size)));
		return dto;
	}

}

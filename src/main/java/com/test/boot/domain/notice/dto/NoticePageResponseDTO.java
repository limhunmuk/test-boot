package com.test.boot.domain.notice.dto;

import java.util.List;

import com.test.boot.domain.notice.vo.NoticeVO;

import lombok.Data;

@Data
public class NoticePageResponseDTO {

	private List<NoticeVO> notices;
	private int currentPage;
	private int totalPages;
	private long totalCount;

	public static NoticePageResponseDTO of(List<NoticeVO> notices, int currentPage, int size, long totalCount) {
		NoticePageResponseDTO dto = new NoticePageResponseDTO();
		dto.setNotices(notices);
		dto.setCurrentPage(currentPage);
		dto.setTotalCount(totalCount);
		dto.setTotalPages(Math.max(1, (int) Math.ceil((double) totalCount / size)));
		return dto;
	}

}

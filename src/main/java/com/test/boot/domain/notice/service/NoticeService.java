package com.test.boot.domain.notice.service;

import java.util.List;

import com.test.boot.domain.notice.dto.NoticeCreateRequestDTO;
import com.test.boot.domain.notice.dto.NoticeDetailResponseDTO;
import com.test.boot.domain.notice.dto.NoticePageResponseDTO;
import com.test.boot.domain.notice.dto.NoticeUpdateRequestDTO;
import com.test.boot.domain.notice.vo.NoticeVO;

public interface NoticeService {

	List<NoticeVO> getNoticeList();

	NoticeDetailResponseDTO getNotice(Long noticeId);

	NoticePageResponseDTO getNoticePage(int page, int size);

	Long createNotice(NoticeCreateRequestDTO request);

	void updateNotice(Long noticeId, NoticeUpdateRequestDTO request);

	void increaseViewCount(Long noticeId);

}

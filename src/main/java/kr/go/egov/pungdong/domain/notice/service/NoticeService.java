package kr.go.egov.pungdong.domain.notice.service;

import java.util.List;

import kr.go.egov.pungdong.domain.notice.dto.NoticeCreateRequestDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeDetailResponseDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticePageResponseDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeSearchConditionDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeUpdateRequestDTO;
import kr.go.egov.pungdong.domain.notice.vo.NoticeVO;

public interface NoticeService {

	List<NoticeVO> getNoticeList();

	NoticeDetailResponseDTO getNotice(Long noticeId);

	NoticePageResponseDTO getNoticePage(int page, int size, NoticeSearchConditionDTO condition);

	Long createNotice(NoticeCreateRequestDTO request);

	void updateNotice(Long noticeId, NoticeUpdateRequestDTO request);

	void increaseViewCount(Long noticeId);

}

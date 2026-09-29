package kr.go.egov.pungdong.domain.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.notice.dto.NoticeCreateRequestDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeDetailResponseDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticePageResponseDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeSearchConditionDTO;
import kr.go.egov.pungdong.domain.notice.dto.NoticeUpdateRequestDTO;
import kr.go.egov.pungdong.domain.notice.mapper.NoticeMapper;
import kr.go.egov.pungdong.domain.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<NoticeVO> getNoticeList() {
		return noticeMapper.selectNoticeList();
	}

	@Override
	public NoticeDetailResponseDTO getNotice(Long noticeId) {
		return NoticeDetailResponseDTO.from(noticeMapper.selectNotice(noticeId));
	}

	@Override
	public NoticePageResponseDTO getNoticePage(int page, int size, NoticeSearchConditionDTO condition) {
		int currentPage = Math.max(1, page);
		int offset = (currentPage - 1) * size;
		List<NoticeVO> notices = noticeMapper.selectNoticeListPaged(condition, offset, size);
		long totalCount = noticeMapper.selectNoticeCount(condition);
		return NoticePageResponseDTO.of(notices, currentPage, size, totalCount);
	}

	@Override
	public Long createNotice(NoticeCreateRequestDTO request) {
		NoticeVO notice = new NoticeVO();
		notice.setTitle(request.getTitle());
		notice.setContent(request.getContent());
		notice.setImportantYn(request.isImportantYn() ? "Y" : "N");
		notice.setRegId("guest");
		noticeMapper.insertNotice(notice);
		return notice.getNoticeId();
	}

	@Override
	public void updateNotice(Long noticeId, NoticeUpdateRequestDTO request) {
		NoticeVO notice = new NoticeVO();
		notice.setNoticeId(noticeId);
		notice.setTitle(request.getTitle());
		notice.setContent(request.getContent());
		notice.setImportantYn(request.isImportantYn() ? "Y" : "N");
		notice.setModId("guest");
		noticeMapper.updateNotice(notice);
	}

	@Override
	public void increaseViewCount(Long noticeId) {
		noticeMapper.increaseViewCount(noticeId);
	}

}

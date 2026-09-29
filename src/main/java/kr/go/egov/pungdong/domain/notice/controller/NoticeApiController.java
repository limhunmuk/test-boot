package kr.go.egov.pungdong.domain.notice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.go.egov.pungdong.domain.notice.dto.NoticeDetailResponseDTO;
import kr.go.egov.pungdong.domain.notice.service.NoticeService;
import kr.go.egov.pungdong.domain.notice.vo.NoticeVO;

@RestController
public class NoticeApiController {

	private final NoticeService noticeService;

	public NoticeApiController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@GetMapping("/api/notices")
	public List<NoticeVO> notices() {
		return noticeService.getNoticeList();
	}

	@GetMapping("/api/notices/{noticeId}")
	public NoticeDetailResponseDTO notice(@PathVariable Long noticeId) {
		return noticeService.getNotice(noticeId);
	}

}

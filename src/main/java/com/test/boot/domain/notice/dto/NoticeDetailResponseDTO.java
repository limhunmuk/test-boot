package com.test.boot.domain.notice.dto;

import java.time.LocalDateTime;

import com.test.boot.domain.notice.vo.NoticeVO;

import lombok.Data;

@Data
public class NoticeDetailResponseDTO {

	private Long noticeId;
	private String title;
	private String content;
	private Integer viewCnt;
	private String importantYn;
	private LocalDateTime regDt;
	private String regId;
	private LocalDateTime modDt;
	private String modId;

	public static NoticeDetailResponseDTO from(NoticeVO vo) {
		if (vo == null) {
			return null;
		}
		NoticeDetailResponseDTO dto = new NoticeDetailResponseDTO();
		dto.setNoticeId(vo.getNoticeId());
		dto.setTitle(vo.getTitle());
		dto.setContent(vo.getContent());
		dto.setViewCnt(vo.getViewCnt());
		dto.setImportantYn(vo.getImportantYn());
		dto.setRegDt(vo.getRegDt());
		dto.setRegId(vo.getRegId());
		dto.setModDt(vo.getModDt());
		dto.setModId(vo.getModId());
		return dto;
	}

}

package com.test.boot.domain.post.dto;

import java.time.LocalDateTime;

import com.test.boot.domain.post.vo.PostVO;

import lombok.Data;

@Data
public class PostDetailResponseDTO {

	private Long articleId;
	private String groupCd;
	private String detailCd;
	private String title;
	private String content;
	private String statusCd;
	private Integer viewCnt;
	private Long uploadFileId;
	private LocalDateTime regDt;
	private String regId;
	private LocalDateTime modDt;
	private String modId;

	public static PostDetailResponseDTO from(PostVO vo) {
		if (vo == null) {
			return null;
		}
		PostDetailResponseDTO dto = new PostDetailResponseDTO();
		dto.setArticleId(vo.getArticleId());
		dto.setGroupCd(vo.getGroupCd());
		dto.setDetailCd(vo.getDetailCd());
		dto.setTitle(vo.getTitle());
		dto.setContent(vo.getContent());
		dto.setStatusCd(vo.getStatusCd());
		dto.setViewCnt(vo.getViewCnt());
		dto.setUploadFileId(vo.getUploadFileId());
		dto.setRegDt(vo.getRegDt());
		dto.setRegId(vo.getRegId());
		dto.setModDt(vo.getModDt());
		dto.setModId(vo.getModId());
		return dto;
	}

}

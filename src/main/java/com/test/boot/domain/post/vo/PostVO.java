package com.test.boot.domain.post.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostVO {

	private Long articleId;
	private String groupCd;
	private String detailCd;
	private String title;
	private String content;
	private String statusCd;
	private Integer viewCnt;
	private Long uploadFileId;
	private String delYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;
	private LocalDateTime modDt;
	private String modId;
	private String modIp;

}

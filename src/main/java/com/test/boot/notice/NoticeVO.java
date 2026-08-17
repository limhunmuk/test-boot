package com.test.boot.notice;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeVO {

	private Long noticeId;
	private Long uploadFileId;
	private String title;
	private String content;
	private String statusCd;
	private Integer viewCnt;
	private Integer likeCnt;
	private String importantYn;
	private String delYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;
	private LocalDateTime modDt;
	private String modId;
	private String modIp;

}

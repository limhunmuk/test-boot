package com.test.boot.postcomment;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostCommentVO {

	private Long articleCommentId;
	private Long articleId;
	private String title;
	private String content;
	private String statusCd;
	private String delYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;
	private LocalDateTime modDt;
	private String modId;
	private String modIp;

}

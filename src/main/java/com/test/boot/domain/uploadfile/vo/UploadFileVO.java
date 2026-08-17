package com.test.boot.domain.uploadfile.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UploadFileVO {

	private Long uploadFileId;
	private String targetType;
	private Long targetId;
	private String orgFileNm;
	private String realFileNm;
	private Long fileSize;
	private String delYn;
	private LocalDateTime regDt;

}

package com.test.boot.uploadfile;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UploadFileVO {

	private Long uploadFileId;
	private String category;
	private String saveFileNm;
	private String realFileNm;
	private String extension;
	private Integer size;
	private String delYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;

}

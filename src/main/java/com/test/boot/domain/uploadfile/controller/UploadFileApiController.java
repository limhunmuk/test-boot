package com.test.boot.domain.uploadfile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.boot.domain.uploadfile.service.UploadFileService;
import com.test.boot.domain.uploadfile.vo.UploadFileVO;

@RestController
public class UploadFileApiController {

	private final UploadFileService uploadFileService;

	public UploadFileApiController(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	@GetMapping("/api/upload-files")
	public List<UploadFileVO> uploadFiles() {
		return uploadFileService.getUploadFileList();
	}

}

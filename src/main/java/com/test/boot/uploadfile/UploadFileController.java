package com.test.boot.uploadfile;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadFileController {

	private final UploadFileService uploadFileService;

	public UploadFileController(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	@GetMapping("/api/upload-files")
	public List<UploadFileVO> uploadFiles() {
		return uploadFileService.getUploadFileList();
	}

}

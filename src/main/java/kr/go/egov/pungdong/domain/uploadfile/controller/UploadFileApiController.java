package kr.go.egov.pungdong.domain.uploadfile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.egov.pungdong.domain.uploadfile.service.UploadFileService;
import kr.go.egov.pungdong.domain.uploadfile.vo.UploadFileVO;

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

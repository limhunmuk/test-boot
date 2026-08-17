package com.test.boot.domain.uploadfile.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.boot.domain.uploadfile.service.FileStorageService;
import com.test.boot.domain.uploadfile.service.UploadFileService;
import com.test.boot.domain.uploadfile.vo.UploadFileVO;

@Controller
public class UploadFileController {

	private final UploadFileService uploadFileService;
	private final FileStorageService fileStorageService;

	public UploadFileController(UploadFileService uploadFileService, FileStorageService fileStorageService) {
		this.uploadFileService = uploadFileService;
		this.fileStorageService = fileStorageService;
	}

	@GetMapping("/files/{uploadFileId}/download")
	public ResponseEntity<Resource> download(@PathVariable Long uploadFileId) throws IOException {
		UploadFileVO file = uploadFileService.getUploadFile(uploadFileId);
		if (file == null) {
			return ResponseEntity.notFound().build();
		}

		Path path = fileStorageService.resolve(file.getRealFileNm());
		Resource resource = new UrlResource(path.toUri());
		if (!resource.exists()) {
			return ResponseEntity.notFound().build();
		}

		String encodedName = URLEncoder.encode(file.getOrgFileNm(), StandardCharsets.UTF_8).replace("+", "%20");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedName)
				.body(resource);
	}

	@PostMapping("/files/{uploadFileId}/delete")
	public String delete(@PathVariable Long uploadFileId, @RequestParam String redirectUrl) {
		uploadFileService.deleteFile(uploadFileId);
		String safeRedirect = redirectUrl.startsWith("/") ? redirectUrl : "/";
		return "redirect:" + safeRedirect;
	}

}

package com.test.boot.domain.uploadfile.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageService {

	private static final DateTimeFormatter DATE_PATH_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	private final Path uploadDir;

	public FileStorageService(@Value("${file.upload-dir:uploads}") String uploadDir) {
		this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
	}

	/**
	 * uploads/{targetType}/{yyyy}/{MM}/{dd}/{저장파일명} 경로에 파일을 저장하고,
	 * uploadDir 기준 상대경로를 반환한다.
	 */
	public String store(String targetType, MultipartFile file) {
		String datePath = LocalDate.now().format(DATE_PATH_FORMAT);
		String typeSegment = targetType.toLowerCase(Locale.ROOT);
		Path dir = uploadDir.resolve(typeSegment).resolve(datePath);
		try {
			Files.createDirectories(dir);
		} catch (IOException e) {
			throw new IllegalStateException("업로드 디렉터리를 생성할 수 없습니다: " + dir, e);
		}

		String originalName = file.getOriginalFilename();
		String extension = extractExtension(originalName);
		String savedFileName = UUID.randomUUID().toString().replace("-", "")
				+ (extension.isEmpty() ? "" : "." + extension);

		try {
			Files.copy(file.getInputStream(), dir.resolve(savedFileName));
		} catch (IOException e) {
			throw new IllegalStateException("파일 저장에 실패했습니다: " + originalName, e);
		}

		return typeSegment + "/" + datePath + "/" + savedFileName;
	}

	public Path resolve(String relativePath) {
		return uploadDir.resolve(relativePath);
	}

	private String extractExtension(String fileName) {
		if (fileName == null) {
			return "";
		}
		int dotIdx = fileName.lastIndexOf('.');
		return dotIdx >= 0 ? fileName.substring(dotIdx + 1) : "";
	}

}

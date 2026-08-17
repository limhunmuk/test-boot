package com.test.boot.domain.uploadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.test.boot.domain.uploadfile.vo.UploadFileVO;

public interface UploadFileService {

	List<UploadFileVO> getUploadFileList();

	List<UploadFileVO> getUploadFileListByTarget(String targetType, Long targetId);

	UploadFileVO getUploadFile(Long uploadFileId);

	void saveFiles(String targetType, Long targetId, List<MultipartFile> files);

	void deleteFile(Long uploadFileId);

}

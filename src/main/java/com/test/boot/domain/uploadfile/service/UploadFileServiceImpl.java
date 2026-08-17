package com.test.boot.domain.uploadfile.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.boot.domain.uploadfile.mapper.UploadFileMapper;
import com.test.boot.domain.uploadfile.vo.UploadFileVO;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	private final UploadFileMapper uploadFileMapper;
	private final FileStorageService fileStorageService;

	public UploadFileServiceImpl(UploadFileMapper uploadFileMapper, FileStorageService fileStorageService) {
		this.uploadFileMapper = uploadFileMapper;
		this.fileStorageService = fileStorageService;
	}

	@Override
	public List<UploadFileVO> getUploadFileList() {
		return uploadFileMapper.selectUploadFileList();
	}

	@Override
	public List<UploadFileVO> getUploadFileListByTarget(String targetType, Long targetId) {
		return uploadFileMapper.selectUploadFileListByTarget(targetType, targetId);
	}

	@Override
	public UploadFileVO getUploadFile(Long uploadFileId) {
		return uploadFileMapper.selectUploadFile(uploadFileId);
	}

	@Override
	public void saveFiles(String targetType, Long targetId, List<MultipartFile> files) {
		if (files == null) {
			return;
		}
		for (MultipartFile file : files) {
			if (file == null || file.isEmpty()) {
				continue;
			}
			String relativePath = fileStorageService.store(targetType, file);
			UploadFileVO uploadFile = new UploadFileVO();
			uploadFile.setTargetType(targetType);
			uploadFile.setTargetId(targetId);
			uploadFile.setOrgFileNm(file.getOriginalFilename());
			uploadFile.setRealFileNm(relativePath);
			uploadFile.setFileSize(file.getSize());
			uploadFileMapper.insertUploadFile(uploadFile);
		}
	}

	@Override
	public void deleteFile(Long uploadFileId) {
		uploadFileMapper.deleteUploadFile(uploadFileId);
	}

}

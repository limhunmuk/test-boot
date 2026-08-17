package com.test.boot.uploadfile;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	private final UploadFileMapper uploadFileMapper;

	public UploadFileServiceImpl(UploadFileMapper uploadFileMapper) {
		this.uploadFileMapper = uploadFileMapper;
	}

	@Override
	public List<UploadFileVO> getUploadFileList() {
		return uploadFileMapper.selectUploadFileList();
	}

}

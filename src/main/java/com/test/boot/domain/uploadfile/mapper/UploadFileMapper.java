package com.test.boot.domain.uploadfile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.boot.domain.uploadfile.vo.UploadFileVO;

@Mapper
public interface UploadFileMapper {

	List<UploadFileVO> selectUploadFileList();

	List<UploadFileVO> selectUploadFileListByTarget(@Param("targetType") String targetType, @Param("targetId") Long targetId);

	UploadFileVO selectUploadFile(Long uploadFileId);

	void insertUploadFile(UploadFileVO uploadFile);

	void deleteUploadFile(Long uploadFileId);

}

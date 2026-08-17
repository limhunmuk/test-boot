package com.test.boot.uploadfile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadFileMapper {

	List<UploadFileVO> selectUploadFileList();

}

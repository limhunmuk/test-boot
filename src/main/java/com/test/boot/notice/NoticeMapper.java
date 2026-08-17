package com.test.boot.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

	List<NoticeVO> selectNoticeList();

}

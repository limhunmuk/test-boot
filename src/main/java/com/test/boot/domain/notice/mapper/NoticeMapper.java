package com.test.boot.domain.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.boot.domain.notice.vo.NoticeVO;

@Mapper
public interface NoticeMapper {

	List<NoticeVO> selectNoticeList();

	NoticeVO selectNotice(Long noticeId);

	List<NoticeVO> selectNoticeListPaged(@Param("offset") int offset, @Param("limit") int limit);

	long selectNoticeCount();

	void insertNotice(NoticeVO notice);

	void updateNotice(NoticeVO notice);

	void increaseViewCount(Long noticeId);

}

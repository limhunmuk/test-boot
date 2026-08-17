package com.test.boot.notice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<NoticeVO> getNoticeList() {
		return noticeMapper.selectNoticeList();
	}

}

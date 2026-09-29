package kr.go.egov.pungdong.domain.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.egov.pungdong.domain.notice.dto.NoticeSearchConditionDTO;
import kr.go.egov.pungdong.domain.notice.vo.NoticeVO;

@Mapper
public interface NoticeMapper {

	List<NoticeVO> selectNoticeList();

	NoticeVO selectNotice(Long noticeId);

	List<NoticeVO> selectNoticeListPaged(@Param("condition") NoticeSearchConditionDTO condition,
			@Param("offset") int offset, @Param("limit") int limit);

	long selectNoticeCount(@Param("condition") NoticeSearchConditionDTO condition);

	void insertNotice(NoticeVO notice);

	void updateNotice(NoticeVO notice);

	void increaseViewCount(Long noticeId);

}

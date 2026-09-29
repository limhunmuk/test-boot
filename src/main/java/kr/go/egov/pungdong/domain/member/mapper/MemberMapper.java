package kr.go.egov.pungdong.domain.member.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.egov.pungdong.domain.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	List<MemberVO> selectMemberList();

	Optional<MemberVO> selectMemberByLoginId(String loginId);

	void updateMemberInfo(MemberVO member);

	void updatePassword(@Param("loginId") String loginId, @Param("password") String password);

	void updateLastLogin(@Param("loginId") String loginId, @Param("ip") String ip);

}

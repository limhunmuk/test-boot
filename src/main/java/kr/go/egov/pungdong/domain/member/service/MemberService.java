package kr.go.egov.pungdong.domain.member.service;

import java.util.List;
import java.util.Optional;

import kr.go.egov.pungdong.domain.member.dto.MemberPasswordChangeRequestDTO;
import kr.go.egov.pungdong.domain.member.dto.MemberUpdateRequestDTO;
import kr.go.egov.pungdong.domain.member.vo.MemberVO;

public interface MemberService {

	List<MemberVO> getMemberList();

	Optional<MemberVO> getMemberByLoginId(String loginId);

	void updateMemberInfo(String loginId, MemberUpdateRequestDTO request);

	void changePassword(String loginId, MemberPasswordChangeRequestDTO request);

	void updateLastLogin(String loginId, String ip);

}

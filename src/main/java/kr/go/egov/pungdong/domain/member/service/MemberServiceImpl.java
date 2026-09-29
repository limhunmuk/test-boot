package kr.go.egov.pungdong.domain.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.member.dto.MemberPasswordChangeRequestDTO;
import kr.go.egov.pungdong.domain.member.dto.MemberUpdateRequestDTO;
import kr.go.egov.pungdong.domain.member.mapper.MemberMapper;
import kr.go.egov.pungdong.domain.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberMapper.selectMemberList();
	}

	@Override
	public Optional<MemberVO> getMemberByLoginId(String loginId) {
		return memberMapper.selectMemberByLoginId(loginId);
	}

	@Override
	public void updateMemberInfo(String loginId, MemberUpdateRequestDTO request) {
		MemberVO member = new MemberVO();
		member.setLoginId(loginId);
		member.setMemNm(request.getMemNm());
		member.setNickNm(request.getNickNm());
		member.setPhoneNo(request.getPhoneNo());
		member.setAddr(request.getAddr());
		member.setAddrDetail(request.getAddrDetail());
		memberMapper.updateMemberInfo(member);
	}

	@Override
	public void changePassword(String loginId, MemberPasswordChangeRequestDTO request) {
		if (!request.getNewPassword().equals(request.getConfirmPassword())) {
			throw new IllegalArgumentException("새 비밀번호가 일치하지 않습니다.");
		}

		MemberVO member = memberMapper.selectMemberByLoginId(loginId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

		if (!passwordEncoder.matches(request.getCurrentPassword(), member.getPassword())) {
			throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
		}

		memberMapper.updatePassword(loginId, passwordEncoder.encode(request.getNewPassword()));
	}

	@Override
	public void updateLastLogin(String loginId, String ip) {
		memberMapper.updateLastLogin(loginId, ip);
	}

}

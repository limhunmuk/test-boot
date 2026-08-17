package com.test.boot.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberMapper.selectMemberList();
	}

}

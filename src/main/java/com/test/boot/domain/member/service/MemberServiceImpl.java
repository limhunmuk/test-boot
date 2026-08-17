package com.test.boot.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.boot.domain.member.mapper.MemberMapper;
import com.test.boot.domain.member.vo.MemberVO;

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

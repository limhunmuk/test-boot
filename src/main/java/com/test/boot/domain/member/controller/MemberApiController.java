package com.test.boot.domain.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.boot.domain.member.service.MemberService;
import com.test.boot.domain.member.vo.MemberVO;

@RestController
public class MemberApiController {

	private final MemberService memberService;

	public MemberApiController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/api/members")
	public List<MemberVO> members() {
		return memberService.getMemberList();
	}

}

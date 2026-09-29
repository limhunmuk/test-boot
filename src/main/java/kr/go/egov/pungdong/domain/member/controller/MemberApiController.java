package kr.go.egov.pungdong.domain.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.domain.member.vo.MemberVO;

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

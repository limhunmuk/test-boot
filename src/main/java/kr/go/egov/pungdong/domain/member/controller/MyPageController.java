package kr.go.egov.pungdong.domain.member.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.go.egov.pungdong.domain.member.dto.MemberPasswordChangeRequestDTO;
import kr.go.egov.pungdong.domain.member.dto.MemberUpdateRequestDTO;
import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.domain.member.vo.MemberVO;

import jakarta.validation.Valid;

@Controller
public class MyPageController {

	private final MemberService memberService;

	public MyPageController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/mypage")
	public String mypage(Principal principal, Model model) {
		model.addAttribute("member", getMember(principal));
		return "mypage/view";
	}

	@GetMapping("/mypage/edit")
	public String editForm(Principal principal, Model model) {
		MemberVO member = getMember(principal);

		MemberUpdateRequestDTO request = new MemberUpdateRequestDTO();
		request.setMemNm(member.getMemNm());
		request.setNickNm(member.getNickNm());
		request.setPhoneNo(member.getPhoneNo());
		request.setAddr(member.getAddr());
		request.setAddrDetail(member.getAddrDetail());

		model.addAttribute("member", member);
		model.addAttribute("memberUpdateRequest", request);
		return "mypage/edit";
	}

	@PostMapping("/mypage/edit")
	public String update(Principal principal,
			@Valid @ModelAttribute("memberUpdateRequest") MemberUpdateRequestDTO request,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("member", getMember(principal));
			return "mypage/edit";
		}
		memberService.updateMemberInfo(principal.getName(), request);
		return "redirect:/mypage";
	}

	@PostMapping("/mypage/password")
	public String changePassword(Principal principal,
			@ModelAttribute MemberPasswordChangeRequestDTO passwordChangeRequest,
			RedirectAttributes redirectAttributes) {
		try {
			memberService.changePassword(principal.getName(), passwordChangeRequest);
			redirectAttributes.addFlashAttribute("pwSuccess", "비밀번호가 변경되었습니다.");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("pwError", e.getMessage());
		}
		return "redirect:/mypage";
	}

	private MemberVO getMember(Principal principal) {
		return memberService.getMemberByLoginId(principal.getName())
			.orElseThrow(() -> new IllegalStateException("존재하지 않는 계정입니다."));
	}

}
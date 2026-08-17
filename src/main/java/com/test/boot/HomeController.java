package com.test.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("message", "로그인 기능은 아직 준비 중입니다.");
		return "placeholder";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("message", "로그아웃 기능은 아직 준비 중입니다.");
		return "placeholder";
	}

	@GetMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("message", "내 정보 기능은 아직 준비 중입니다.");
		return "placeholder";
	}

}

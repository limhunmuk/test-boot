package kr.go.egov.pungdong.domain.ipacl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.go.egov.pungdong.domain.ipacl.dto.IpAclRequestDTO;
import kr.go.egov.pungdong.domain.ipacl.service.IpAclService;
import kr.go.egov.pungdong.domain.ipacl.vo.IpAclVO;
import kr.go.egov.pungdong.web.util.ClientIpUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AdminIpAclController {

	private final IpAclService ipAclService;

	public AdminIpAclController(IpAclService ipAclService) {
		this.ipAclService = ipAclService;
	}

	@GetMapping("/admin/ip-acl")
	public String list(Model model) {
		model.addAttribute("ipAclList", ipAclService.getIpAclList());
		model.addAttribute("policyMode", ipAclService.getPolicyMode());
		return "admin/ipacl/list";
	}

	@GetMapping("/admin/ip-acl/new")
	public String newForm(Model model) {
		model.addAttribute("ipAclRequest", new IpAclRequestDTO());
		return "admin/ipacl/form";
	}

	@PostMapping("/admin/ip-acl")
	public String create(@Valid @ModelAttribute("ipAclRequest") IpAclRequestDTO request, BindingResult bindingResult,
			HttpServletRequest servletRequest, Authentication authentication) {
		if (bindingResult.hasErrors()) {
			return "admin/ipacl/form";
		}
		ipAclService.createIpAcl(request, authentication.getName(), ClientIpUtils.resolveClientIp(servletRequest));
		return "redirect:/admin/ip-acl";
	}

	@GetMapping("/admin/ip-acl/{aclId}/edit")
	public String editForm(@PathVariable Long aclId, Model model) {
		IpAclVO ipAcl = ipAclService.getIpAcl(aclId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 IP 등록 정보입니다."));

		IpAclRequestDTO request = new IpAclRequestDTO();
		request.setIpAddr(ipAcl.getIpAddr());
		request.setListType(ipAcl.getListType());
		request.setDescription(ipAcl.getDescription());
		request.setUseYn(ipAcl.getUseYn());

		model.addAttribute("aclId", aclId);
		model.addAttribute("ipAclRequest", request);
		return "admin/ipacl/form";
	}

	@PostMapping("/admin/ip-acl/{aclId}/edit")
	public String update(@PathVariable Long aclId, @Valid @ModelAttribute("ipAclRequest") IpAclRequestDTO request,
			BindingResult bindingResult, Model model, HttpServletRequest servletRequest,
			Authentication authentication) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("aclId", aclId);
			return "admin/ipacl/form";
		}
		ipAclService.updateIpAcl(aclId, request, authentication.getName(),
				ClientIpUtils.resolveClientIp(servletRequest));
		return "redirect:/admin/ip-acl";
	}

	@PostMapping("/admin/ip-acl/{aclId}/delete")
	public String delete(@PathVariable Long aclId) {
		ipAclService.deleteIpAcl(aclId);
		return "redirect:/admin/ip-acl";
	}

	@PostMapping("/admin/ip-acl/policy")
	public String updatePolicy(@RequestParam String policyMode, Authentication authentication) {
		ipAclService.updatePolicyMode(policyMode, authentication.getName());
		return "redirect:/admin/ip-acl";
	}

}

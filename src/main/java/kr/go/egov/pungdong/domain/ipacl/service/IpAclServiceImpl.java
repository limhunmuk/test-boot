package kr.go.egov.pungdong.domain.ipacl.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.ipacl.dto.IpAclRequestDTO;
import kr.go.egov.pungdong.domain.ipacl.mapper.IpAclMapper;
import kr.go.egov.pungdong.domain.ipacl.vo.IpAclVO;

@Service
public class IpAclServiceImpl implements IpAclService {

	private static final String WHITELIST = "WHITELIST";
	private static final String BLACKLIST = "BLACKLIST";

	private final IpAclMapper ipAclMapper;

	public IpAclServiceImpl(IpAclMapper ipAclMapper) {
		this.ipAclMapper = ipAclMapper;
	}

	@Override
	public List<IpAclVO> getIpAclList() {
		return ipAclMapper.selectIpAclList();
	}

	@Override
	public Optional<IpAclVO> getIpAcl(Long aclId) {
		return ipAclMapper.selectIpAcl(aclId);
	}

	@Override
	public void createIpAcl(IpAclRequestDTO request, String regId, String regIp) {
		IpAclVO ipAcl = new IpAclVO();
		ipAcl.setIpAddr(request.getIpAddr());
		ipAcl.setListType(request.getListType());
		ipAcl.setDescription(request.getDescription());
		ipAcl.setUseYn(request.getUseYn());
		ipAcl.setRegDt(LocalDateTime.now());
		ipAcl.setRegId(regId);
		ipAcl.setRegIp(regIp);
		ipAclMapper.insertIpAcl(ipAcl);
	}

	@Override
	public void updateIpAcl(Long aclId, IpAclRequestDTO request, String modId, String modIp) {
		IpAclVO ipAcl = new IpAclVO();
		ipAcl.setAclId(aclId);
		ipAcl.setIpAddr(request.getIpAddr());
		ipAcl.setListType(request.getListType());
		ipAcl.setDescription(request.getDescription());
		ipAcl.setUseYn(request.getUseYn());
		ipAcl.setModDt(LocalDateTime.now());
		ipAcl.setModId(modId);
		ipAcl.setModIp(modIp);
		ipAclMapper.updateIpAcl(ipAcl);
	}

	@Override
	public void deleteIpAcl(Long aclId) {
		ipAclMapper.deleteIpAcl(aclId);
	}

	@Override
	public String getPolicyMode() {
		String mode = ipAclMapper.selectPolicyMode();
		return mode != null ? mode : BLACKLIST;
	}

	@Override
	public void updatePolicyMode(String policyMode, String modId) {
		ipAclMapper.updatePolicyMode(policyMode, modId);
	}

	@Override
	public boolean isAllowed(String clientIp) {
		String mode = getPolicyMode();
		List<String> activeIps = ipAclMapper.selectActiveIpAddrsByType(mode);
		boolean matched = activeIps.contains(clientIp);
		return WHITELIST.equals(mode) ? matched : !matched;
	}

}

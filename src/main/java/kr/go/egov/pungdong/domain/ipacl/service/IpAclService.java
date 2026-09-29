package kr.go.egov.pungdong.domain.ipacl.service;

import java.util.List;
import java.util.Optional;

import kr.go.egov.pungdong.domain.ipacl.dto.IpAclRequestDTO;
import kr.go.egov.pungdong.domain.ipacl.vo.IpAclVO;

public interface IpAclService {

	List<IpAclVO> getIpAclList();

	Optional<IpAclVO> getIpAcl(Long aclId);

	void createIpAcl(IpAclRequestDTO request, String regId, String regIp);

	void updateIpAcl(Long aclId, IpAclRequestDTO request, String modId, String modIp);

	void deleteIpAcl(Long aclId);

	String getPolicyMode();

	void updatePolicyMode(String policyMode, String modId);

	boolean isAllowed(String clientIp);

}

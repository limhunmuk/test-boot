package kr.go.egov.pungdong.domain.ipacl.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.egov.pungdong.domain.ipacl.vo.IpAclVO;

@Mapper
public interface IpAclMapper {

	List<IpAclVO> selectIpAclList();

	Optional<IpAclVO> selectIpAcl(Long aclId);

	List<String> selectActiveIpAddrsByType(String listType);

	void insertIpAcl(IpAclVO ipAcl);

	void updateIpAcl(IpAclVO ipAcl);

	void deleteIpAcl(Long aclId);

	String selectPolicyMode();

	void updatePolicyMode(@Param("policyMode") String policyMode, @Param("modId") String modId);

}

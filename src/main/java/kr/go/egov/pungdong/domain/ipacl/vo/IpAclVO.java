package kr.go.egov.pungdong.domain.ipacl.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IpAclVO {

	private Long aclId;
	private String ipAddr;
	private String listType;
	private String description;
	private String useYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;
	private LocalDateTime modDt;
	private String modId;
	private String modIp;

}

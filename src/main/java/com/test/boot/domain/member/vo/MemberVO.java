package com.test.boot.domain.member.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MemberVO {

	private Long memberId;
	private String loginId;

	@JsonIgnore
	private String password;

	private String memNm;
	private String nickNm;
	private String memType;
	private LocalDateTime joinDt;
	private String phoneNo;
	private String addr;
	private String addrDetail;
	private String delYn;
	private LocalDateTime regDt;
	private String regId;
	private String regIp;
	private LocalDateTime modDt;
	private String modId;
	private String modIp;

}

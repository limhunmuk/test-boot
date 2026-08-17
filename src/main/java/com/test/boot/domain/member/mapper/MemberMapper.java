package com.test.boot.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.boot.domain.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	List<MemberVO> selectMemberList();

}

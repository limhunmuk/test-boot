package kr.go.egov.pungdong.domain.postcomment.dto;

import java.util.List;

import kr.go.egov.pungdong.domain.postcomment.vo.PostCommentVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCommentThreadDTO {

	private PostCommentVO comment;
	private List<PostCommentVO> replies;

}
package kr.go.egov.pungdong.domain.post.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostSearchConditionDTO {

	private String title;

	private String regId;

	private LocalDate startDate;

	private LocalDate endDate;

}

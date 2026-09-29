package kr.go.egov.pungdong.domain.notice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NoticeSearchConditionDTO {

	private String title;

	private String regId;

	private LocalDate startDate;

	private LocalDate endDate;

}

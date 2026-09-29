package kr.go.egov.pungdong.web.support;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public final class PaginationInfoFactory {

	private static final int PAGE_LIST_SIZE = 10;

	private PaginationInfoFactory() {
	}

	public static PaginationInfo create(int currentPageNo, int recordCountPerPage, long totalRecordCount) {
		PaginationInfo info = new PaginationInfo();
		info.setCurrentPageNo(currentPageNo);
		info.setRecordCountPerPage(recordCountPerPage);
		info.setPageSize(PAGE_LIST_SIZE);
		info.setTotalRecordCount((int) totalRecordCount);
		return info;
	}

}

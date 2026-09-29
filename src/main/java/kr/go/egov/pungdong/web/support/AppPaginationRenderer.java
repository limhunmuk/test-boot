package kr.go.egov.pungdong.web.support;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

public class AppPaginationRenderer implements PaginationRenderer {

	@Override
	public String renderPagination(PaginationInfo info, String jsFunction) {
		StringBuilder html = new StringBuilder();
		html.append("<div class=\"pagination\">");

		boolean atFirst = info.getCurrentPageNo() <= info.getFirstPageNo();
		appendArrow(html, jsFunction, info.getFirstPageNo(), "&laquo;", atFirst);
		appendArrow(html, jsFunction, Math.max(info.getFirstPageNo(), info.getCurrentPageNo() - 1), "&lsaquo;", atFirst);

		for (int pageNo = info.getFirstPageNoOnPageList(); pageNo <= info.getLastPageNoOnPageList(); pageNo++) {
			appendPage(html, jsFunction, pageNo, pageNo == info.getCurrentPageNo());
		}

		boolean atLast = info.getCurrentPageNo() >= info.getLastPageNo();
		appendArrow(html, jsFunction, Math.min(info.getLastPageNo(), info.getCurrentPageNo() + 1), "&rsaquo;", atLast);
		appendArrow(html, jsFunction, info.getLastPageNo(), "&raquo;", atLast);

		html.append("</div>");
		return html.toString();
	}

	private void appendArrow(StringBuilder html, String jsFunction, int pageNo, String label, boolean disabled) {
		if (disabled) {
			html.append("<a class=\"disabled\">").append(label).append("</a>");
			return;
		}
		html.append("<a href=\"javascript:").append(jsFunction).append('(').append(pageNo).append(");\">")
			.append(label).append("</a>");
	}

	private void appendPage(StringBuilder html, String jsFunction, int pageNo, boolean active) {
		String cssClass = active ? " class=\"active\"" : "";
		html.append("<a href=\"javascript:").append(jsFunction).append('(').append(pageNo).append(");\"")
			.append(cssClass).append('>').append(pageNo).append("</a>");
	}

}

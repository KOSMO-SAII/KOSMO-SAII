package utils;

public class PagingUtil {
	public static String pagingCenter(int totalCount, int pageSize, int pageBlock, int pageNum, String reqUri) {
		String pagingRes = "";

		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		int div = pageBlock / 2;
		int pageTemp = pageNum - div; //5개의 페이지번호를 pageBlock이라 하고, pageBlock에서 첫번째에 해당하는 페이지번호를 pageTemp라고 함.
		if (pageTemp <= 0)
			pageTemp = 1;

		if (pageTemp != 1) {
			pagingRes += "<a href='" + reqUri + "?pageNum=1'>[first]<a/>";
		}
		int count = 1;
		while (count <= pageBlock && pageTemp <= totalPage) {
			if (pageTemp == pageNum) {
				pagingRes += "&nbsp;" + pageTemp + "&nbsp;";
			} else {
				pagingRes += "&nbsp;<a href='" + reqUri + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			count++;
		}
		if (pageTemp <= totalPage) {
			pagingRes += "<a href='" + reqUri + "?pageNum=" + totalPage + "'>[last]</a>";
		}

		return pagingRes;
	}
}
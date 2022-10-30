<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트용</title>
</head>
<body>
	<form method="post" action="../test">

		<input type="hidden" name="c1"
			value="CE7|1974266862|서울 종로구 재동 86|서울 종로구 북촌로 6-3|0507-1337-9973|카페노티드 안국|http://place.map.kakao.com/1974266862|126.98603029791168|37.57745704823158|장소1번" />
		<input type="hidden" name="c2"
			value="CE7|87323834|서울 중구 명동2가 105|서울 중구 명동2길 22|02-779-1981|더스팟 패뷸러스|http://place.map.kakao.com/87323834|126.982601390472|37.5623324834706|장소2번" />
		<input type="hidden" name="c3"
			value="AD2|253560122|서울 중구 정동 17-4|서울 중구 정동길 17|02-772-9935|커피루소 정동점|http://place.map.kakao.com/253560122|126.97060772065376|37.56703961398295|장소3번" />

		${c1.category }<br /> ${c2.address_id }<br /> ${c3.memo}<br />
		<button type="submit">submmmmmmit</button>
	</form>
</body>
</html>
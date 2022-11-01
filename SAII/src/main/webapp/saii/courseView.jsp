<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 뷰</title>
<link href="http://localhost:8081/SAII/saii/CSS/courseView.css" rel="stylesheet" type="text/css">

<!-- 카카오 맵 api 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9298b59fe5fe509e6414c98642407c29&libraries=services"></script>
</head>
<!-- 제이쿼리 적용 -->
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<body>
	 <div class="body_wrap">
		<div class="my">
			<form action="../t" method="post" >
			<ul id="My_List">
			
			</ul>
			<div id="btnbox">
			${requestScope.num}
			<a href="http://localhost:8081/SAII/saii/startPage.jsp">메인 메뉴로 돌아가기</a>
			<input type="submit" value="코스 수정"></input>
			</form>
			</div>
		</div>
		<div class="map_wrap">
			<div id="map"style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

			
			
		</div>
		<div id="memobox" class="none">
		
			<div class="title">
			</div>
				<div class="close memo"  title="닫기"></div>
			<textarea class="memo"></textarea>
			<button class="savememobtn" type="button">저장</button>
		
		</div>
		
	</div>
	<!-- js연동 -->
	<script src="http://localhost:8081/SAII/saii/JS/courseView.js"></script>
</body>
</html>
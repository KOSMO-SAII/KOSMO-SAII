<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키워드로 장소검색하고 목록으로 표출하기</title>
<!-- css연동 -->
<link href="CSS/courseWritePage.css" rel="stylesheet" type="text/css">

<!-- 카카오 맵 api 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9298b59fe5fe509e6414c98642407c29&libraries=services"></script>
</head>
<!-- 제이쿼리 적용 -->
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<body>
	 <div class="body_wrap">
		<div class="my">
			<form >
			<ul id="My_List">
							
			</ul>
			</form>
			<button onclick="check();">확인</button>
			<button onclick="hide();">숨기기</button>
			<a href="http://localhost:8081/SAII/home">돌아가기</a>
		</div>
		<div class="map_wrap">
			<div id="map"style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

			<!-- 검색 사이드바 -->
			<div id="menu_wrap" class="bg_white">
				<div class="option">
					<div>
						<form onsubmit="searchPlaces(); return false;">
							키워드 : <input type="text" value="" id="keyword" size="15" placeholder="입력하세요">
							<button type="submit">검색하기</button>
						</form>
					</div>
				</div>
				<hr>
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
			<!-- 카테고리 목록 위치  -->
			<ul id="category">
				<li id="BK9" data-order="0"><span class="category_bg bank"></span>
				은행</li>
				<li id="MT1" data-order="1"><span class="category_bg mart"></span>
				마트</li>
				<li id="PM9" data-order="2"><span class="category_bg pharmacy"></span>
				약국</li>
				<li id="OL7" data-order="3"><span class="category_bg oil"></span>
				주유소</li>
				<li id="CE7" data-order="4"><span class="category_bg cafe"></span>
				카페</li>
				<li id="CS2" data-order="5"><span class="category_bg store"></span>
				편의점</li>
				<li id="PK6" data-order="6"><span class="category_bg park"></span>
				주차장</li>
				<li id="SW8" data-order="7"><span class="category_bg subway"></span>
				지하철역</li>
				<li id="CT1" data-order="8"><span class="category_bg culture"></span>
				문화시설</li>
				<li id="AT4" data-order="9"><span class="category_bg tourism"></span>
				관광명소</li>
				<li id="AD5" data-order="10"><span class="category_bg lodgment"></span>
				숙박</li>
				<li id="FD6" data-order="11"><span class="category_bg food"></span>
				음식점</li>
				<li id="HP8" data-order="12"><span class="category_bg hospital"></span>
				병원</li>

			</ul>
		</div>


	</div>
	<!-- js연동 -->
	<script src="JS/courseWritePage.js"></script>
</body>
</html>
	
	
	
	
	
	
	
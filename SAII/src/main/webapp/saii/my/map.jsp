<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SAII</title>
<link href="../CSS/map.css" rel="stylesheet" type="text/css">
</head>
<body>
<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=70ac00c8df7aff6f198c097d67066de1&libraries=services"></script>
	<div class="wrap">
		<div class="info_wrap">
		<ul id="info_list">
			
		</ul>
		</div>
		<div class="map_wrap">
			<div id="map"
				style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
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
			</ul>
			<button id="markerBtn" onclick="showMarker()">마커 숨기기</button>
			<button id="resetBtn" onclick="reset()">초기화</button>
			
		</div>
	</div>
	
		
<script src="../JS/map.js"></script>	
</body>
</html>
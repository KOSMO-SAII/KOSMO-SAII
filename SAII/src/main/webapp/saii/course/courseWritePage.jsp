<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 작성</title>
<link href="http://localhost:8081/SAII/saii/CSS/courseWritePage.css" rel="stylesheet" type="text/css">

<!-- 카카오 맵 api 불러오기 ㅇㅇ -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9298b59fe5fe509e6414c98642407c29&libraries=services"></script>
</head>
<!-- 제이쿼리 적용 -->
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<body>	
	 <div class="body_wrap">
		<div class="my">			
			<form action="http://localhost:8081/SAII/course_view?mode=${param.mode}&c_id=${param.c_id}" method="post" onsubmit="return coursecheck()" >
				제목<br/><input type="text" name="title"/><br/>

				지역<br/><input type="text" name="region"/>
				<input type="hidden" name="nickname" value="${sessionScope.nickname}" />
				<ul id="My_List">
				
				</ul>
				<div id="btnbox">
				<button type="button" onclick="check();">확인</button>
				<button type="button" onclick="hide();">숨기기</button>
				<a href="http://localhost:8081/SAII/home">돌아가기</a>
				<c:choose>
					<c:when test="${param.mode eq 'edit'}">
						<input type="submit" value="코스수정"></input>
					</c:when>
					<c:otherwise>
						<input type="submit" value="코스저장"></input>
					</c:otherwise>
				</c:choose>
				</div>
			</form>
		</div>
		<div class="map_wrap">
			<div id="map"style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

			<!-- 검색 사이드바 -->
			<div id="menu_wrap_box">
				<div id="menu_wrap" class="bg_white">
					<div class="option">
						<div>
							<form onsubmit="searchPlaces(); return false;">
								키워드 : <input type="text" value="" id="keyword" size="15" placeholder="입력하세요">
								<button class="searchbtn" type="submit">검색하기</button>
							</form>
						</div>
					</div>
					<hr>
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>
				<div id="arrow">▼</div>
			</div>
			<!-- 카테고리 목록 위치  -->
			<div id="box">
				<div id="arrow2">▶</div>
				<div id="category-box">
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
				</ul>
				<ul id="category2">	
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
		</div>
		<div id="memobox" class="none">
		
			<div class="title">
			</div>
				<div class="close memo"  title="닫기"></div>
			<textarea class="memo"></textarea>
			<button class="savememobtn" type="button">저장</button>
		
		</div>
		
	</div>
	
	<script type="text/javascript">
		var paramObjs=[]; //장소 정보 객체 담는 배열
		var mode="${param.mode}"; //현재 작성 모드인지 수정 모드이지 구분
		//console.log("${param.mode}");
	</script>
	<c:forEach items="${requestScope.List}" var="List">
	<script type="text/javascript">
		//장소 정브를 반복문을 돌려서 객체에 담음
		var paramObj={
			address_id:"${List["address_id"]}",
			address_name:"${List["address_name"]}",
			category:"${List["category"]}",
			Memo:"${List["Memo"]}",
			Phone_number:"${List["Phone_number"]}",
			Place_name:"${List["Place_name"]}",
			Place_url:"${List["Place_url"]}",
			Road_address_name:"${List["Road_address_name"]}",
			X:"${List["X"]}",
			Y:"${List["Y"]}",
		}
		paramObjs.push(paramObj)
	</script>
	</c:forEach>
	<!-- js연동 -->
	<script src="http://localhost:8081/SAII/saii/JS/courseWritePage.js"></script>
</body>
</html>
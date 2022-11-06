<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%@ include file="../top.jsp" %>
	 <div class="body_wrap">
		<div class="my">
			<form action="http://localhost:8081/SAII/course_write?mode=edit&c_id=${c_id}" method="post" >
				<div class="sidebar">제목:
					<div id="sidebartitle">${requestScope.title }</div>
				</div>

				<div class="sidebar">지역:
					<div id="sidebarregion">${requestScope.region }</div>
				</div>
				<ul id="My_List">
				
				</ul>
				<div id="btnbox">
				
				<!-- <a class="submitbtna" href="http://localhost:8081/SAII/home">메인 메뉴로 돌아가기</a> -->
				<c:if test="${requestScope.nickname==sessionScope.nickname || param.mode == 'edit'}">
				<input type="submit" class="submitbtn" value="코스 수정"></input>
				</c:if>
				
				</div>
			</form>
		</div>
		<div class="map_wrap">
			<div id="map"style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

			
			
		</div>
		<div id="memobox" class="none">
		
			<div class="title">
			</div>
				<div class="close memo"  title="닫기"></div>
			<textarea class="memo" readonly="readonly"></textarea>
			<!-- <button class="savememobtn" type="button">저장</button>  -->
		
		</div>
		
	</div>
	
	<script type="text/javascript">
		var paramObjs=[]; //장소 정보 객체 담는 배열
		 //console.log("이건")
		 //console.log("뭐지")
		 console.log("${requestScope.title}")
		 console.log("${requestScope.region}")
		//console.log(${param.mode == "edit"}) 
		//console.log(${requestScope.nickname==sessionScope.nickname})
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
	<script src="http://localhost:8081/SAII/saii/JS/courseView.js"></script>


<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>
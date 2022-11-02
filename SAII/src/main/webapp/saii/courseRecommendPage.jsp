<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 추천</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script>

</script>


<style>
.course-list{
    width: 735px;
    margin-left: auto;
    margin-right: auto;
}
.course h2{
    font-size: 34px;
    color: #545454;
    margin-top:60px;
    margin-bottom: 60px;
    text-align: center;
}

.coursese{
    display: block;
    width: 225px;
    text-align: center;
    text-decoration:none;
    color: black;
    float:left;
    margin-left: 10px;
    margin-right: 10px;
    margin-bottom: 30px;
}

.course-name{
    margin-top: 20px;
    margin-bottom: 4px;
}

</style>
<body>

	<div class="coco"></div>

    <div class="course">
        <h2>코스 추천</h2>

        <div class="course-list">
        	
            <a href="http://localhost:8081/SAII/course_recommend" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
               	${list[0].title }
                </div>
                <div class="course-co">
                ${list[0].c_id}
                ${list[0].region}
				<c:forTokens items="${list[0].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" value="글읽기" onclick="location.href='./course_view?num=${list[0].c_id}' "/> <br/>
                </div>
            </a>
            
             <a href="#" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
               	${list[1].title }
                </div>
                <div class="course-co">
                ${list[1].c_id}
                ${list[1].region}
                
                <c:forTokens items="${list[1].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" value="글읽기" onclick="location.href='./course_view?num=${list[1].c_id}' "/> <br/>
                </div>
            </a>
            
             <a href="#" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
               	${list[2].title }
                </div>
                <div class="course-co">
                ${list[2].c_id}
                ${list[2].region}
                <c:forTokens items="${list[2].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" value="글읽기" onclick="location.href='./course_view?num=${list[2].c_id}' "/> <br/>
                </div>
            </a>
 		</div>
 	</div>	
	
	
	
	
	
	
	
	<a href="http://localhost:8081/SAII/home">돌아가기</a>
	<a href="http://localhost:8081/SAII/mainboard?page=1">추천게시판</a>
	<a href="http://localhost:8081/SAII/review">리뷰</a>
</body>
</html>
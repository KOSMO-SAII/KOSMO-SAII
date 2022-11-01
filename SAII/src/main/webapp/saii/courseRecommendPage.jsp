<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 추천</title>
</head>
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
            <a href="#" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
                    코스 제목
                </div>
                <div class="course-co">
                    기타 등등
                </div>
            </a>
             <a href="#" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
                    코스 제목
                </div>
                <div class="course-co">
                    기타 등등
                </div>
            </a>
             <a href="#" class="coursese">
                <img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg" width="225">
                <div class="course-name">
                    코스 제목
                </div>
                <div class="course-co">
                    기타 등등
                </div>
            </a>
 		</div>
 	</div>	
	
	
	
	
	
	
	
	<a href="http://localhost:8081/SAII/home">돌아가기</a>
	<a href="http://localhost:8081/SAII/mainboard?page=1">추천게시판</a>
	<a href="http://localhost:8081/SAII/review">리뷰</a>
</body>
</html>
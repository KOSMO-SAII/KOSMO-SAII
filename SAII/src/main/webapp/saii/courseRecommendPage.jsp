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
}
	


.img{
    position: relative;
    background-image: url(http://localhost:8081/SAII/saii/img/her.png);                                                               
    height: 225px;
    width: 225px;
    background-size: cover;
  }

  .img-cover{
     position: absolute;
     height: 100%;
     width: 100%;
     background-color: rgba(255, 0, 0, 0.05);                                                                 
     z-index:1;
  }

  .img .content{
     position: absolute;
     top:50%;
     left:50%;
     transform: translate(-50%, -50%);                                                                   
     font-size:10px;
     color: white;
     z-index: 2;
     text-align: center;
     width:100%;
  }
  
  h1 {display: block;
    color: #545454;
    word-break: break-all;
    font-size: 24px;
    font-weight: 400;
    line-height: 13px;
    text-align: center;}

input.look {margin-top: 24px; height: 25px; width: 30%; background: none; border: none;
    		color: #fff; font-size: 10px; font-weight: 200; cursor: pointer;
    		border-radius: 4px; background-color: #d0d0d0;}
    			

</style>
<body>

	<div class="coco"></div>

    <div class="course">
        <h2>코스 추천</h2>

        <div class="course-list">
            <a href="http://localhost:8081/SAII/course_recommend" class="coursese">
             <div class="img">
        		<div class="content">
            		<h1>< ${list[0].title} ></h1>
            		<h1>${list[0].region}</h1>
        			</div> 
        		<div class="img-cover"></div></div>
				<c:forTokens items="${list[0].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" value="글읽기" onclick="location.href='./course_view?num=${list[0].c_id}' "/> <br/>
            </a>
            
            
             <a href="#" class="coursese">
                <div class="img">
        		<div class="content">
            		<h1>< ${list[1].title} ></h1>
            		<h1>${list[1].region}</h1>
        			</div> 
        		<div class="img-cover"></div></div>
				<c:forTokens items="${list[1].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" value="글읽기" onclick="location.href='./course_view?num=${list[1].c_id}' "/> <br/>
            </a>
            
             <a href="#" class="coursese">
                <div class="img">
        		<div class="content">
            		<h1>< ${list[2].title} ></h1>
            		<h1>${list[2].region}</h1>
        			</div> 
        		<div class="img-cover"></div></div>
				<c:forTokens items="${list[2].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
                <input type="button" class="look"  value="자세히보기" onclick="location.href='./course_view?num=${list[2].c_id}' "/> <br/>
            </a>
            </a>
 		</div>
 	</div>	
	
	
	
	
	
	
	
	<a href="http://localhost:8081/SAII/home">돌아가기</a>
	<a href="http://localhost:8081/SAII/mainboard?page=1">추천게시판</a>
	<a href="http://localhost:8081/SAII/review">리뷰</a>
	
</body>
</html>
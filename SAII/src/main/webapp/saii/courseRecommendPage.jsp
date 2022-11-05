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
h3 {font-size: 40px;
    font-weight: 400;
    line-height: 13px;
    margin-top:80px;
    margin-bottom: 100px;
    text-align: center;
    font-weight: bold;}

.course-list{
    width: 1100px;
    margin: auto;
}
.course h2{   
    font-size: 24px;
    font-weight: 400;
    line-height: 13px;
    margin-top:80px;
    margin-bottom: 80px;
    text-align: center;
}

.coursese{
    display: inline-block;
    width: 300px;
    text-align: center;
    text-decoration:none;
    color: black;
    float:left;
    box-shadow: rgb(31 38 135 / 20%) 0px 8px 32px 0px;
    margin:3%;
    
	
}


	
.coursese:hover{
   transform:scale(1.1);
	}

.img{
    position: relative;
    background-image: url(http://localhost:8081/SAII/saii/img/loc/경기.png);                                                               
    height: 225px;
    width: 300px;
    background-size: cover;
    
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
    font-size: 22px;
    font-weight: 400;
    line-height: 13px;
    text-align: center;
       font-weight: bolder;
    margin-bottom: 40px;
    }

input.look {margin-top: 24px; height: 25px; width: 30%; background: none; border: none;
    		color: #fff; font-size: 10px; font-weight: 200; cursor: pointer;
    		border-radius: 4px; background-color: #d0d0d0;}
    		
#abc {
    position: absolute;
    display: inline-block;
    right: 2%;
   	top:10%
}
    			
	
</style>
<body>

	<div class="coco"></div>

        <h3>코스 추천</h3>
    <div class="course">

        <div class="course-list">
            <a href="#" class="coursese" onclick="location.href='http://localhost:8081/SAII/course_view?num=${list[0].c_id}' ">
                <div class="img">
        		<div class="content">
            		<h2 style="text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;">[ ${list[0].title} ]</h2>
        			</div> 
        		</div>
            		<h1>${list[0].region}</h1>
				<c:forTokens items="${list[0].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
            </a>
            
            
             <a href="#" class="coursese" onclick="location.href='./course_view?num=${list[1].c_id}' ">
                <div class="img">
        		<div class="content">
            		<h2 style="text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;">[ ${list[1].title} ]</h2>
        			</div> 
        		<div class="img-cover"></div></div>
            		<h1>${list[1].region}</h1>
				<c:forTokens items="${list[1].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
            </a>
            
            
            
             <a href="#" class="coursese" onclick="location.href='./course_view?num=${list[2].c_id}' ">
                <div class="img">
        		<div class="content">
            		<h2 style="text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;">[ ${list[2].title} ]</h2>
        			</div> 
        		<div class="img-cover"></div></div>
            		<h1>${list[2].region}</h1>
				<c:forTokens items="${list[2].p_name}" delims="," var="name">
                	<p><c:out value=" ${name}" /></p>
                </c:forTokens>
            </a>
 		</div>
 	</div>	
	
<div id="abc">
	<a href="http://localhost:8081/SAII/home">돌아가기</a>
	<a href="http://localhost:8081/SAII/mainboard?page=1">추천게시판</a>
	<a href="http://localhost:8081/SAII/review_list">리뷰게시판</a>
</div>

<!--마우스 커서-->	
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>
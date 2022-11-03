<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>초기화면</title>
<style>
h1 {
 text-align: center;
 text-shadow: gray;
 color:black;
 bold;
}

#header {
  float: right;
  display: flex;
  justify-content: flex-end;
  align-items:center;
  text-align: center;
  margin-bottom : 20px;
  text-decoration:blink;
  font-weight: bold;
  color: white;
  width:700px;
  position:relative; 
  right:-120px;
  padding:0;

} 
.container {
	width : 100%;
}
@media ( min-width : 768px) .justify-content-md-between {
	justify-content
	:
	space-between
	!
	important
	;
	
	
}

.py-3 {
	padding-top: 1rem !important;
	padding-bottom: 1rem !important;
}

.mb-4 {
	margin-bottom: 1.5rem !important;
}

.align-items-center {
	align-items: center !important;
}

.justify-content-center {
	justify-content: center !important;
}

.flex-wrap {
	flex-wrap: wrap !important;
}

.border-bottom {
	border-bottom: var(- -bs-border-width) var(- -bs-border-style)
		var(- -bs-border-color) !important;
}

.d-flex {
	display: flex;
}

*, ::after, ::before {
	box-sizing: border-box;
}

body {
	background-color: #EEEEEE;
	color: white;
	margin: 0;
	padding: 0;
	font-size: 1.2em;
	font-family: 'Hack';
}

section {
	background-color:rgb(207, 220, 243);
	padding: 30px;
	color: white;
	display: flex;
	flex-direction: row-reverse;
	width: 100%;
	height: 700px;
}

.image-box {
	position: relative;
	font-size: 10px;
	color: white;
	z-index: 2;
	text-align: center;
	width: 100%;
}

/* div.image-box {
    width:50%;
    height:50%;
    overflow:hidden;
    margin:0 auto;
    justify-content: center;
    align-items: center;
}
 */
footer {
	background-color: lightgrey;
	padding: 30px;
	color:black;
}

ul {
	margin: 0;
	padding: 0;
	list-style-type: none;
	float: right;
}

li {
	display: inline;
	margin: 0 20px 0 0;
}

#search {
	border-style: dotted;
	border-width: 2px;
	padding: 5px;
	margin: 0;
	display: flex;
	flex-direction: row-reverse;
}

.imgbox {
	width: 700px;
	height: 100%;
}

.col-md-3 text-end{
	width:200px
}
</style>
</head>
<body>
	<h1>SAII</h1>
	<div class="container">
		<header
			id="header">
			<a href="/">
				<svg class="bi me-2" width="40" height="32" role="img"
					aria-label="Bootstrap">
					<use xlink:href="#bootstrap"></use></svg>
			</a>

			<ul
				class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
				<li><c:if test="${UserId!=null}">
						<a href="http://localhost:8081/SAII/mypage?id=${UserId }"
							class="nav-link px-2 link-dark">MyPage</a>
					</c:if></li>
				<li><a href="http://localhost:8081/SAII/saii/startPage.jsp"
					class="nav-link px-2 link-dark">Home</a></li>
				<li test="${UserId==null}"><a href="<!--링크입력하기 -->"
					class="nav-link px-2 link-dark">FAQs</a></li>
				<li test="${UserId==null}"><a href="<!--링크입력하기 -->"
					class="nav-link px-2 link-dark">About</a></li>
				
			</ul>

			<div class="col-md-3 text-end">
				<c:if test="${UserId==null}">
					<a
						href="http://localhost:8081/SAII/saii/loginPage.jsp?id=${UserId }"><button
							type="button" class="btn btn-outline-primary me-2">Login</button></a>
				</c:if>
				<c:if test="${UserId!=null}">
					<a href="http://localhost:8081/SAII/logout"><button
							type="button" class="btn btn-outline-primary me-2">LogOut</button></a>
				</c:if>
				<c:if test="${UserId==null}">
					<a href="http://localhost:8081/SAII/signup"><button
							type="button" class="btn btn-primary">Sign-up</button></a>
				</c:if>
			</div>
		</header>
	</div>
	<section id="tripple">
		<div class="image-box">
			<a href="http://localhost:8081/SAII/course_write"><img
				class="imgbox" alt="코스 작성 화면" src="../img/내가 만드는 데이트 코스.png"></a>
		</div>
		<div class="image-box">
			<a href="http://localhost:8081/SAII/course_recommend"><img
				class="imgbox" alt="코스 추천화면" src="../img/추천코스로 빠른 데이트 코스짜기.png"></a>
		</div>
	</section>


	<footer>
		<ul>
			<li>대표전화:070-000-0000 /</li>
			<li>대표이메일:saiIcustomer@saii.co.kr</li>
			</br>
		</ul>
	</footer>

</body>
</html>
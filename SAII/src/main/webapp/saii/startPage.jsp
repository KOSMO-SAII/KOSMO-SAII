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
<link rel="stylesheet" href="http://localhost:8081/SAII/saii/CSS/main.css">
</head>
<body>
	<h1><img id=himg src="http://localhost:8081/SAII/saii/img/saii.png"></h1>
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
					<a href="http://localhost:8081/SAII/saii/loginPage.jsp?id=${UserId }"><button
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
				class="imgbox" alt="코스 작성 화면" src="http://localhost:8081/SAII/saii/img/fast.png"></a><br/>코스작성
		</div>
		<div class="image-box">
			<a href="http://localhost:8081/SAII/course_recommend"><img
				class="imgbox" alt="코스 추천화면" src="http://localhost:8081/SAII/saii/img/her.png"></a><br/>코스추천
		</div>
	</section>


	<footer>
		<ul>
			<li>대표전화:070-000-0000 /</li>
			<li>대표이메일:saiIcustomer@saii.co.kr</li>
			</br>
		</ul>
	</footer>
	
<!--마우스커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>
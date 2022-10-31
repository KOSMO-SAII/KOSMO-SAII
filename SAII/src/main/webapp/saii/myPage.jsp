<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script>
function passCk(){     
		window.name = "parentForm";            
		nickWin = window.open("http://localhost:8081/SAII/saii/passcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");
	}
</script>
</head>
<body>
	<h4>회원정보</h4>
	<div>
		<form method="get" onsubmit="passCk()">
			<input type="submit" value="회원정보 수정하기"><br> 
			아이디 : <input	type="text" id="userId" name="id" value="${dto.id}" readonly><br>
		</form>
		
			비밀번호 : <input type="password" name="pw" value="${dto.pw }" readonly><br>
			닉네임 : <input type="text" name="nickname" value="${dto.nickname }"readonly><br>
			이름 : <input type="text" name="name"	value="${dto.name }" readonly><br> 
			생일 : <input type="Date" name="birthday" value="${dto.birthday }" readonly><br>
			성별 : <input type="text" name="sex" value="${dto.sex }" readonly><br>
			전화 : <input type="text" name="phone" value="${dto.phone }" readonly><br>
			이메일 : <input type="email" name="email" value="${dto.email }" readonly><br>
			주소 : <input type="text" name="address" value="${dto.address }"	readonly>
			
			
		<form action="http://localhost:8081/SAII/upload?id=${dto.id }" name="img" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${dto.id}">
			<input type="file" name="o_profile_img" required>
			<input type="submit" value="프로필변경">
		</form>
		
	</div>
	<img src="/SAII/Storage/${dto.n_profile_img}" width="100px" height="100px" style="border-radius: 50px">
	
		<h2>내 코스</h2>
	<form method="get">
		<table align="center" border="1" width="100%">
			<tr>
				<td align="center">
					<select name="searchType">
						<option value="m_title" <c:if test="${map.searchType == 'm_title'}">selected</c:if>>제목</option>
						<option value="content" <c:if test="${map.searchType == 'content'}">selected</c:if>>내용</option>
					</select>
					<input type="text" name="searchStr" value="${map.searchStr}" />
					<input type="submit" value="검색" />
				</td>
			</tr>
		</table>
	</form>
	
	<table align="center" border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>지역</td>
			<td>제목</td>
			<td>코스명</td>
			<td>게시날짜</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		<c:choose>
			<c:when test="${not empty boardLists}">
				<c:forEach items="${boardLists}" var="list" varStatus="stat">
					<tr>
						<td>${list.m_id}</td>
						<td>${list.region}</td>
						<td><a href="http://localhost:8081/SAII/view?m_id=${list.m_id}">${list.m_title}</a></td>
						<td>${list.course_name}</td>
						<td>${list.m_postdate}</td>
						<td>${list.visitcount}</td>
						<td>${list.goodcount}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
		<%-- 페이징 --%>
		<tr>
			<td colspan="8" id="paging">
				<c:url var="action" value="http://localhost:8081/SAII/mainboard" />
				<c:if test="${paging.prev}">
					<a href="${action}?page=${paging.beginPage-1}">prev</a>
					&nbsp;&nbsp;
				</c:if>
				<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
					<c:choose>
						<c:when test="${paging.page==index}">
							${index}
						</c:when>
						<c:otherwise>
							<a href="${action}?page=${index}">${index}</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;
				</c:forEach>
				<c:if test="${paging.next}">
					<a href="${action}?page=${paging.endPage+1}">next</a>
				</c:if>
			</td>
		</tr>
	</table>

	<a href="http://localhost:8081/SAII/home">돌아가기</a>
</body>
</html>
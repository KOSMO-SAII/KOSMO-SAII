<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>리뷰게시판</title>
</head>
<body>
	<h2>게시판 상세보기</h2>

	<table border="1" width="90%">
		<tr>
			<td>카테고리</td>
			<td><c:if test="${dto.r_category eq 'course'}">코스</c:if> <c:if
					test="${dto.r_category eq 'place'}">장소</c:if></td>

		</tr>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<td>제목</td>
			<td>${dto.r_title}</td>
			<td>작성자</td>
			<td>${dto.nickname}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${dto.r_postdate}</td>
			<td>조회수</td>
			<td>${dto.visitcount}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3" height="100">${dto.content}</td>
		</tr>

		<tr>
			<td>첨부파일</td>
			<td><c:if test="${not empty dto.o_file }">
					<a
						href="http://localhost:8081/SAII/review_download.do?o_file=${dto.o_file}&n_file=${dto.n_file}&r_id=${dto.r_id}">
						${dto.o_file} </a>
					<img src="/SAII/Storage/${dto.n_file}">
				</c:if></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<!-- 회원이면, 작성자와 회원 닉네임이 같을 때만, 버튼 보임. -->
				<c:if test="${!(empty sessionScope.UserId)}">
					<c:if test="${dto.nickname == nickname}">
						<button class="chkWriter" type="button"
							onclick="location.href='http://localhost:8081/SAII/review_mode?mode=edit&r_id=${param.r_id}';">
							수정하기</button>
						<button class="chkWriter" type="button"
							onclick="location.href='http://localhost:8081/SAII/review_mode?mode=delete&r_id=${param.r_id}';">
							삭제하기</button>
					</c:if>
				</c:if>
				<button type="button"
					onclick="location.href='http://localhost:8081/SAII/review_list';">
					목록 바로가기</button>
			</td>
		</tr>
	</table>

	<!-- 댓글 목록 -->
<div>
	<table border="1" width="90%">
		<c:if test="${ commentLists.size() == 0 }">
			<tr>
				<td colspan="2">댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${ commentLists }" var="clist">
			<tr>
				<td>
					<span>${ clist.nickname }</span>
					<span>${ clist.cmt_regdate }</span>
					${ clist.cmt_content }
				</td>
				<td>
					<input type="button" value="삭제하기"
						onclick="location.href='http://localhost:8081/SAII/delComment?cmt_no=${ clist.cmt_no }&board_no=${ dto.r_id }';"/>
				</td>
			</tr>
		</c:forEach>	
	</table>
	
	<form method="POST" action="http://localhost:8081/SAII/addComment">
		<table border="1" width="90%">
			<tr>
				<td>
					<input type="text" name="cmt_content" required placeholder="댓글을 작성하세요. "/>
				</td>
				<td>
					<input type="submit" value="댓글쓰기" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="board_no" value="${ dto.r_id }" />
	</form>
</div>








	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
		integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
		crossorigin="anonymous"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 게시판</title>
</head>
<script type="text/javascript">
	function validateForm(form) {	//필수 항목 입력 확인

		if(form.r_title.value ==""){
			alert("제목을 입력하세요.");
			form.r_title.focus();
			return false;
		}
		if(form.content.value ==""){
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
	}
</script>
<body>
<h2>수정하기(Edit)</h2>
<form name="writeFrm" method="post"
	enctype="multipart/form-data"
	action="../rboard/edit.do" onsubmit="return validateForm(this):">
<input type="hidden" name="r_id" value="${dto.r_id }"/>
<input type="hidden" name="prevO_file" value="${dto.o_file }"/>
<input type="hidden" name="prevN_file" value="${dto.n_file }"/>
<table border="1" width="90%">
	<tr>
		<td>카테고리</td>
		<td>
			<c:if test="${dto.r_category eq 'course'}">코스</c:if>
			<c:if test="${dto.r_category eq 'place'}">장소</c:if>
		</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>
			${dto.nickname}
		</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="r_title" style="width:90%;" value="${ dto.r_title }" />
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea name="content" style="width:90%;height:100px;">${ dto.content }</textarea>
		</td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>
			<input type="file" name="o_file" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="submit" onclick="okEdit()">작성 완료</button>
			<button type="reset">RESET</button>
			<button type="button" onclick="location.href='../rboard/list.do';">
				목록 바로가기
			</button>
		</td>
	</tr>
</table>
</form>

</body>
</html>
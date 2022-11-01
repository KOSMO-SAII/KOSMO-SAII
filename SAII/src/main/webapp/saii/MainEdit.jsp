<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style>
	table{
		width:90%;
		margin:auto;
		border:1px solid black;
	}
	tr,td{
		margin:auto;
		border:1px solid black;
	}
	textarea{
		width:99%;
		height:99%;
	}
	#content_td{
		height:500px;
	}
	#submit_button{
		text-align:right;
	}
</style>
</head>
<body>
	<form name="editForm" method="post" action="http://localhost:8081/SAII/edit">
		<table>
			<tr>
				<td>제목</td> <td colspan="3"><input type="text" name="m_title" />${dto.m_title}</td>
			</tr>
			<tr>
				<td>지역</td> <td><input type="text" name="region" />${dto.region}</td>
				<td>코스명</td> <td><input type="text" name="course_name" />${dto.course_name}</td>
			</tr>
			<tr>
				<td id="content_td" colspan="4"><textarea name="content">${dto.content}</textarea></td>
			</tr>
			<td id="submit_button" colspan="4">
				<input type="submit" value="작성 완료" />
				<input type="reset" value="초기화" />
				<input type="button" value="목록 보기" onclick="location.href='http://localhost:8081/SAII/mainboard';" />
			</td>
		</table>
	</form>
</body>
</html>
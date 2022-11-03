<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
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
	#content{
		height:500px;
	}
	#heart{
		/*border:0px solid white;
		background-color:white;*/
	}
	#delete{
		text-align:right;
	}
	#comment{
		width:90%;
		border:1px solid black;
		margin:auto;
	}
	#good{
		float: left;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#good').click(function(){
		jQuery.ajax({
			type: "GET",
			url: "http://localhost:8081/SAII/good",
			cache: false,
			data: {
				text_condition: $('#good').text(),
				m_id: $('#m_id').text(),
				nickname: $('#nickname').text()
			},
			datatype: "JSON",
			success: function(obj){
				if(obj.heart == "noheart"){
					$('#good').text('이게바로하트지');				
				}else{
					$('#good').text('하트아님');					
				}
				
				$('#goodcount').text(obj.goodcount);
			}
		});
	});
});
</script>
</head>
<body>
	<table>
		<tr>
			<td>글번호 <span id="m_id">${dto.m_id}</span></td>
			<td colspan="5">제목 : ${dto.m_title}</td>
		</tr>
		<tr>
			<td>지역</td> <td>${dto.region}</td>
			<td>코스넘버</td> <td>${dto.course_id}</td>
			<td>글쓴이</td> <td><span id="nickname">${dto.nickname}</span></td>
		</tr>
		<tr>
			<td>작성일</td> <td>${dto.m_postdate}</td>
			<td>좋아요</td>
			<td>
				<c:if test="${memdto.nickname ne null}">
					<span id="good">${goodWhether}</span>
				</c:if>&nbsp;&nbsp;
				<span id="goodcount">${dto.goodcount}</span>
			</td>
			<td>조회수</td> <td>${dto.visitcount}</td>
		</tr>
		<tr>
			<td id="delete" colspan="6">
				<c:choose>
					<c:when test="${memdto.nickname eq dto.nickname}">
						<input type="button" value="수정하기" onclick="location.href='http://localhost:8081/SAII/edit?m_id=${dto.m_id}';" />
						<input type="button" value="삭제하기" onclick="location.href='http://localhost:8081/SAII/delete?m_id=${dto.m_id}';" />
					</c:when>
				</c:choose>
				<input type="button" value="목록보기" onclick="location.href='http://localhost:8081/SAII/mainboard';" />
			</td>
		</tr>
	</table>
	<div id="comment">댓글작성 <input type="text"></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	h2{
		text-align: center;
		width: 80%;
		margin: auto;
    	margin-bottom: 50px;
    	margin-top: 40px;
	}
	table{
		width:90%;
		margin:50px auto;
	}
	tr,td{
		margin:auto;
	}
	#paging{
		text-align:center;
	}
	#writeButton{
		text-align:right;
	}
	.mainboard{
		margin:auto;
		width:80%;
	}
	input[name="search_Str"]{
    vertical-align: bottom;
    width: 20%;
    height: 40px;
    display: inline-block;
    border: none;
    border-radius: 5px;
    background-color: #F7F7F7;
 	}
 	input[type="submit"]{
    height: 40px;
    width: 70px;
    background: none;
    border: none;
    color: #fff;
    font-size: 16px;
    font-weight: 500;
    border-radius: 5px;
    background-color: #98dde3;
    position: relative;
    vertical-align: bottom;
    }
    input[type="button"]{
    height: 35px;
    width: 80px;
    background: none;
    border: none;
    color: #fff;
    font-size: 16px;
    font-weight: 500;
    border-radius: 5px;
    background-color: #98dde3;
    position: relative;
    vertical-align: bottom;
    }
    input[type="text"]{
    width: 20%;
    height: 40px;
    display: inline-block;
    border: none;
    border-radius: 5px;
    background-color: #F7F7F7;
    }
    select{
    border-radius: 5px;
    height: 40px;
    }
	#search_table{
	border:none;
	margin:auto;	
	}
	#search_table.children(){
		margin:auto 50px;
	}
	#board_table tr:nth-child(2n){
		background-color: none;
	}
	#board_table tr:nth-child(2n-1){
		background-color: #f7f7f7;
	}
	#view_table tr td:nth-child(2n-1){
		background-color: #f7f7f7;
		text-align: center;
		width: 100px;
	}
	a {
	text-decoration:none;
	color:darkblue;
	}
	#table_head{
	text-align:center;
	font-size:larger;
	}
	#delete{
		text-align:right;
	}
	#good{
		float: left;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="saii/JS/MainView.js"></script>

</head>
<body>
	<table id=view_table>
		<tr>
			<th colspan='6' style="text-align:left;">${dto.m_title}</th>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${dto.nickname}</td>
			<td>작성일</td>
			<td>${dto.m_postdate}</td>
			<td>조회수</td>
			<td>${dto.visitcount}
		</tr>
		<tr>
			<td>지역</td>
			<td colspan=''>${dto.region}</td>
			<td>좋아요</td>
			<td>
				<c:if test="${memdto.nickname ne null}">
					<span id="good">
						<c:choose>
							<c:when test="${goodWhether eq true}">
								<img src="saii/img/yesGood.png" alt="좋아" width="20px" height="20px">
							</c:when>
							<c:otherwise>
								<img src="saii/img/notGood.png" alt="싫어" width="20px" height="20px">
							</c:otherwise>
						</c:choose>
					</span>
				</c:if>
				<span id="goodcount">${dto.goodcount}</span>
			</td>
		</tr>
	
		<tr>
			<td id="delete" colspan="5">
			</td>
			<td>
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

	<table>
	<ul style="width:70%; margin:auto">
		<c:forTokens items="${p_name}" delims="," var="name">
          	<li><c:out value=" ${name}" /></li>
        </c:forTokens>	
    </ul>
	</table>

	<table>
		<c:if test="${memdto.nickname ne null}">
			<tr>
				<form method="POST" action="http://localhost:8081/SAII/addMainComments">
				<input type="hidden" name="m_id" value="${dto.m_id}" />
					<td colspan="5">
						댓글작성 <textarea name="comments" cols="150" rows="3" style="resize: none;"></textarea>
					</td>
					<td>
						<input type="submit" value="댓글쓰기" />
					</td>
				</form>
			</tr>
		</c:if>
		<tr>
			<td>프사</td>
			<td>작성자</td>
			<td colspan="3">내용</td>
			<td>게시날짜</td>
		</tr>
		<c:choose>
			<c:when test="${not empty mainCommentsLists}">
				<c:forEach items="${mainCommentsLists}" var="c_list" varStatus="stat">
					<tr>
						<td><img src="/SAII/Storage/${c_list.n_profile_img}" alt="왜안나옴?" width="50px" height="50px" style="border-radius: 50px"></td>
						<td>${c_list.nickname}</td>
						<td colspan="3">${c_list.comments}</td>
						<td>${c_list.c_postdate}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" align="center">등록된 댓글이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
<!--마우스 커서-->
<%--마우스 커서--%>
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>
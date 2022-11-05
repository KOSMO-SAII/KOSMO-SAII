$(function(){
	$('#good').click(function(){
		jQuery.ajax({
			type: "GET",
			url: "http://localhost:8081/SAII/good",
			cache: false,
			data: {
				m_id: $('#m_id').text()
			},
			datatype: "JSON",
			success: function(obj){
				if(obj.heart == "cancel"){
					$('#good').html('<img src="saii/img/notGood.png" alt="싫어" width="20px" height="20px">');					
				}else{
					$('#good').html('<img src="saii/img/yesGood.png" alt="좋아" width="20px" height="20px">');
				}
				
				$('#goodcount').text(obj.goodcount);
			}
		});
	});
});
function selectCommentsList(){
	Jquery.ajax({
		type: "GET",
		url: "http://localhost:8081/SAII/maincomments",
		cache: false,
		data: {
			m_id: $('#m_id').text()
		},
		datatype: "JSON",
		success: function(list){
			var result = "";
			
			for(var i in list){
				result +=
					"<tr>" +
					"	<td>번호</td>"
					"	<td>프사</td>"
					"	<td>작성자</td>"
					"	<td>내용</td>"
					"	<td>게시날짜</td>"
					"	<td>좋아요</td>"
					"<tr>";
			}
			$("#comments").html(result);
		}
	});
}
$(function(){
	selectCommentsList();
	setInterval(selectComments,1000);
});
$(function(){
	$('#comments_submit').click(function(){
		jQuery.ajax({
			type: "GET",
			url: "http://localhost:8081/SAII/maincomments",
			cache: false,
			data: {
				m_id: $('#m_id').text(),
				comments: $('#comments').text()
			},
			datatype: "JSON",
			success: function(obj){
				
			}
		});
	});
});
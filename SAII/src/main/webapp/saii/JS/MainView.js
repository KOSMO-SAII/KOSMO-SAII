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

function delCmt(){
	if(!confirm('삭제 시, 되돌릴 수 없습니다. \n 정말 삭제하시겠습니까?'))
		return false;
	else{
		location.href="http://localhost:8081/SAII/deleteMainComments?c_id=${c_list.c_id}&m_id=${dto.m_id}";
	}
}
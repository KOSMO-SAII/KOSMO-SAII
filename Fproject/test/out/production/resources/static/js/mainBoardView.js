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
//생년월일 받기
	$(document).ready(function(){
	    var now = new Date();
	    var year = now.getFullYear();
	    var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);
	    var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());
	    //년도 selectbox만들기
	    for(var i = 1960 ; i <= year ; i++) {
	        $('#year').append('<option value="' + i + '">' + i + '</option> ');
	    }

	    // 월별 selectbox 만들기
	    for(var i=1; i <= 12; i++) {
	        var mm = i > 9 ? i : "0"+i ;
	        $('#month').append('<option value="' + mm + '">' + mm + '</option> ');
	    }

	    // 일별 selectbox 만들기
	    for(var i=1; i <= 31; i++) {
	        var dd = i > 9 ? i : "0"+i ;
	        $('#day').append('<option value="' + dd + '">' + dd+ '</option> ');
	    }
	    $("#year  > option[value="+year+"]").attr("selected", "true");
	    $("#month  > option[value="+mon+"]").attr("selected", "true");
	    $("#day  > option[value="+day+"]").attr("selected", "true");

	})


	}
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색RL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("http://localhost:8081/SAII/saii/juso.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	  //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
	}


	function jusoCallBack(roadFullAddr){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			document.getElementById("roadFullAddr").value = roadFullAddr;
	}

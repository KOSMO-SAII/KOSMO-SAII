
//빈칸일시 경고창 출력
function check() {
	console.log("체크들어오나요?");
	var form = document.getElementById("form");

	if (form.pw.value == "") {
		alert("비밀번호를 입력하세요");
		form.pw.focus();
		return false;
	}
	if (form.pw2.value == "") {
		alert("비밀번호 재확인을 입력하세요");
		form.pw2.focus();
		return false;
	}
	if (form.nickname.value == "") {
		alert("닉네임을 입력하세요");
		form.nickname.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하세요");
		form.name.focus();
		return false;
	}



	//아이디 유효성 검사 (영문소문자, 숫자만 허용)
	for (var i = 0; i < form.id.value.length; i++) {
		ch = form.id.value.charAt(i)
		if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
			alert("아이디는 영문 대소문자, 숫자만 입력가능합니다.")
			form.id.focus();
			form.id.select();
			return false;
		}
	}
	//아이디에 공백 사용하지 않기
	if (form.id.value.indexOf(" ") >= 0) {
		alert("아이디에 공백을 사용할 수 없습니다.")
		form.id.focus();
		form.id.select()
		return false;
	}
	//아이디 길이 체크 (6~15자)
	if (form.id.value.length < 6 || form.id.value.length > 15) {
		alert("아이디를 6~15자까지 입력해주세요.")
		form.id.focus();
		form.id.select();
		return false;
	}

	//비밀번호 길이 체크(8~15자 까지 허용)
	if (form.pw.value.length < 8 || form.pw.value.length > 15) {
		alert("비밀번호를 8~15자까지 입력해주세요.")
		form.pw.focus();
		form.pw.select();
		return false;
	}

	//비민번호 유효성 검사
	for (var j = 0; j < form.pw.value.length; j++) {
		ch = form.pw.value.charAt(j)
		if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
			alert("비밀번호는 영문 대소문자, 숫자만 입력가능합니다.")
			form.pw.focus();
			form.pw.select();
			return false;
		}
	}



	//비밀번호 재확인
	if (!(form.pw.value == form.pw2.value)) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}


	//이름을 한글로만 받기
	for (var k = 0; k < form.name.value.length; k++) {
		ck = form.name.value.charAt(k);
		if (((ck < "ㅏ") || (ck > "히")) && ((ck < "ㄱ") || (ck > "ㅎ"))) {
			alert("한글만 입력 가능합니다.");
			form.name.focus();
			form.name.select();
			return false;
		}
	}


	//이름 2글자 이상
	if (form.name.value.length < 2) {
		alert("이름을 2자 이상 입력해주십시오.");
		form.name.focus();
		form.name.select();
		return false;
	}
	
		
}

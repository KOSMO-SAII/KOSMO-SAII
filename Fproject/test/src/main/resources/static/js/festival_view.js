src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";

  fetch(url=https://apis.data.go.kr/B551011/KorService/areaBasedList?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      let authors = data;

      authors.map(function(author) {

      });
    })
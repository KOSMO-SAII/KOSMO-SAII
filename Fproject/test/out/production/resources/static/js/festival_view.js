src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";

  function getInfo(cotid){
      var info;
      var infoitem=[];
      console.log(cotid);
      fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=100000000000000&pageNo=10&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&eventStartDate=20220101&eventEndDate=20221231')
      .then((response) => response.json())
      .then((data) => {
          info = data;
          infoitem=data.response.body.items.item.contentid
          console.log(infoitem)
     if(true){
          for(var j=0;j<infoitem.length;j++){
              printInfo(infoitem[j])
          }
        }
      })

  }
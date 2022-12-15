src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";
console.log("안녕")
getInfo();
  function getInfo(){
      var info;
      var infoitem=[];
      var contentTypeId=15;
      var contentId=id
      fetch('https://apis.data.go.kr/B551011/KorService/detailCommon?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&contentId='+contentId+'&contentTypeId='+contentTypeId+'')
      .then((response) => response.json())
      .then((data) => {
            infoitem=data.response.body.items.item[0]
            console.log(infoitem)
            printInfo(infoitem)
         })
//   if(true){
//          for(var j=0;j<infoitem.length;j++){
//              printInfo(infoitem[j])
//          }
//        }
//      })
  }

  function printInfo(infoitem){
  console.log(infoitem.firstimage)
  console.log(infoitem.title)

    var img = document.createElement("IMG");
    img.setAttribute("src", infoitem.firstimage);
      img.setAttribute("width", "400");
      img.setAttribute("height", "600");
    $('.swiper-container')[0].prepend(img)
  }
src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";
console.log("안녕")
galleryGo();
  function galleryGo(){
      var info;
      var infoitem=[];
      var contentTypeId=15;
      var contentId=id
      fetch('https://apis.data.go.kr/B551011/KorService/detailCommon?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&contentId='+contentId+'&contentTypeId='+contentTypeId+'')
      .then((response) => response.json())
      .then((data) => {
            infoitem=data.response.body.items.item[0]
            console.log(infoitem)
            printImg(infoitem)
            printInfo(infoitem)
         })
//   if(true){
//          for(var j=0;j<infoitem.length;j++){
//              printInfo(infoitem[j])
//          }
//        }
//      })
  }

  function printImg(infoitem){
  console.log(infoitem.firstimage)
  console.log(infoitem.title)

    var img = document.createElement("IMG");
    img.setAttribute("src", infoitem.firstimage);
      img.setAttribute("width", "400");
      img.setAttribute("height", "600");
    $('.swiper-container')[0].prepend(img)
  }

  function printInfo(infoitem){
    console.log(infoitem.overview)
    console.log(infoitem.title)

      var detail = document.createElement("DIV");
      detail.setAttribute("class", infoitem.overview);
      detail.innerHTML='<p>'+infoitem.overview+'</p>'
      $('.inr_wrap')[0].append(detail)
    }

     function koreaservice(){
         var info;
         var infoitem;
         var contentId=id
         fetch('https://apis.data.go.kr/B551011/KorService/detailIntro?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId='+contentId+'&contentTypeId=15')
         .then((response) => response.json())
         .then((data) => {
               infoitem=data.response.body.items.item[0]
               printservice(infoitem)
               console.log(infoitem)
         })
     }
     function printservice(infoitem){
        var div=$('.inr')[0]
        var startDate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
        var eventenddate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
        var sponsor1tel=infoitem.sponsor1tel.substr(0,3)+'-'+ infoitem.sponsor1tel.substr(3,3)+'-'+ infoitem.sponsor1tel.substr(6,4)
        div.innerHTML='<ul><li><strong>시작일</strong><span>'+startDate+'</span></li><li><strong>종료일</strong><span>'+infoitem.eventenddate+'</span></li><li><strong>전화번호</strong><span>'+infoitem.sponsor1tel+'</span></li><li><strong>홈페이지</strong><span>'+infoitem.eventhomepage+'<a href="infoitem.eventhomepage" target="_blank" title="새창 : 코엑스 윈터 페스티벌 (Coex Winter Festival)</a></span></li><li><strong>주소</strong><span></span></li><li><strong>행사장소</strong><span>'+infoitem.eventplace+'</span></li><li><strong>주최</strong><span>'+infoitem.sponsor2+'</span></li><li><strong>주관</strong><span>'+infoitem.sponsor1+'</span></li><li><strong>이용요금</strong><span>'+infoitem.usetimefestival+'</span></li><li><strong>행사시간</strong><span>'+infoitem.playtime+'</span></li></ul>'
     }
   koreaservice();




src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";
//console.log("실행");
var id;
$(".btn").click(function(event){
var e = event.target
//console.log("클릭")
//console.dir(e)
//console.dir(e.parentElement.id)
//console.dir(e.parentElement.localName=='li')
 id = e.parentElement.id
 getInfo(id);
})


function getInfo(id){
    var info;
    var infoitem=[];
    fetch('https://apis.data.go.kr/B551011/KorService/areaBasedList?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&&pageNo=1&numOfRows=10&MobileApp=AppTest&MobileOS=ETC&arrange=A&areaCode='+id+'&_type=json')
    .then((response) => response.json())
    .then((data) => {
        info = data;
        infoitem=data.response.body.items.item
        //console.log(info);
       console.log(infoitem)
   if(){}
        for(var j=0;j<infoitem.length;j++){

              ...
                printInfo(infoitem[j])
                console.log("월")
        }
      }
    })

}
//function getminfo(class){
//    var info;
//    var infoitem=[];
//    fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=12&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&eventStartDate=20220101&eventEndDate=20221231')
//    .then((response) => response.json())
//    .then((data) => {
//        info = data;
//        infoitem=data.response.body.items.item
//        //console.log(info);
//       console.log(infoitem)
//    for(var j=0;j<infoitem.length;j++){
//            printInfo(infoitem[j])
//            console.log("반복")
//        }
//    })
//
//}
function printInfo(infoitem){
    console.log("반복2")
    var ul =$('.flnon');
    var li=document.createElement('li');
    li.innerHTML = '<image class="image" src="'+infoitem.firstimage+'"></image><p class="title">'+infoitem.title+'</p>';
    ul[0].appendChild(li);
}

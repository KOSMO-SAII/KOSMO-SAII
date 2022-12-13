src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";
console.log("실행");
var id = ""
var c = ""

$(".btn").click(function(event){
var e = event.target
//console.log("클릭")
//console.dir(e)
//console.dir(e.parentElement.id)
//console.dir(e.parentElement.localName=='li')
if(e.parentElement.id != ""){
 id = e.parentElement.id
 }
if(e.parentElement.className != ""){
 c = e.parentElement.className
 }

 console.log(e + '    ' +e.parentElement + '   ' + id + '   ' +  c)
 var ul =$('.flnon')
 ul.empty()
 getInfo(id, c);
})

$("btn_all_active").click(function(event){
    id = ""
    c = ""
})

$("#monthlist").children().click(function(event){
    var e = event.target
    id = e.id
    getInfo(id,'')
    })

function getInfo(id, c){
    var info;
    var infoitem=[];
    var param = ('&areaCode='+id+'&eventStartDate=2022'+c+'01&eventEndDate=2022'+c+'31')
    console.log(param);
    fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=Q'+param)
    .then((response) => response.json())
    .then((data) => {
        info = data;
        infoitem=data.response.body.items.item
        console.log(infoitem)
   if(true){
        for(var j=0;j<infoitem.length;j++){
            printInfo(infoitem[j])
        }
      }
    })

}

function printInfo(infoitem){
    var ul =$('.flnon');
    var li=document.createElement('li');
    li.innerHTML = '<image class="image" src="'+infoitem.firstimage+'" alt="이미지 준비중입니다."><a href="/festivalview"></a></image><p class="title">'+infoitem.title+'</p>';
    ul[0].appendChild(li);
}

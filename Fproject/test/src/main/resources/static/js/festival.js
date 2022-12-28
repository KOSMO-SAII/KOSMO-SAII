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
if(e.parentElement.parentElement.id == "monthlist"){
if(e.parentElement.id != ""){
 c = e.parentElement.id
 }
//  else(e.parentElement.id=""){
//    c=""
//  }

}else if(e.parentElement.parentElement.id == "arealist"){
if(e.parentElement.id != ""){
 id = e.parentElement.id
 }
//  else(e.parentElement.id=""){
//     for(var i=0;i<id.length;i++){
//     printInfo(id[i]
//     }
//   }
}


console.log(e.parentElement.parentElement.id)

 console.log(e + '    ' +e.parentElement + '   ' + id + '   ' +  c)
 var ul =$('.flnon')
 ul.empty()
 getInfo(id, c);
})

$("btn_all_active").click(function(event){
    id = ""
    c = ""
})

//$("#monthlist").child().click(function(event){
//    var e = event.target
//    id = e.id
//    getInfo(id,'')
//    })

function getInfo(id, c){
    var info;
    var infoitem=[];
    var param = ('&areaCode='+id+'&eventStartDate=2022'+c+'01&eventEndDate=2022'+c+'31')
    //var date = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
    console.log(param);
    fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=O'+param)
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
     var eventstartdate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
     var eventenddate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
    li.innerHTML = '<div class="imgandtext"><a href="/festivalview/'+infoitem.contentid+'"><image class="image" src="'+infoitem.firstimage+'" alt="이미지 준비중입니다."></image></a><div class="titleanddate"><a href="/festivalview/'+infoitem.contentid+'"><p class="title">'+infoitem.title+'</p><p class="date">['+''+eventstartdate+'~'+eventenddate+']</p></div></a></div>';
    ul[0].appendChild(li);
}

function allInfo(){
  var infoitem=[];
fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=10&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&eventStartDate=20220101&eventEndDate=20221231')
    .then((response) => response.json())
    .then((data) => {
        info = data;
        infoitem=data.response.body.items.item
        console.log(infoitem)
        for(var j=0;j<infoitem.length;j++){
             printAll(infoitem[j])
             }
    })
}

function printAll(infoitem){
    var ul =$('.tag_list js_multi');
    var li=document.createElement('li');
     var eventstartdate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
     var eventenddate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
    li.innerHTML = '<div class="imgandtext"><a href="/festivalview/'+infoitem.contentid+'"><image class="image" src="'+infoitem.firstimage+'" alt="이미지 준비중입니다."></image></a><div class="titleanddate"><a href="/festivalview/'+infoitem.contentid+'"><p class="title">'+infoitem.title+'</p><p class="date">['+''+eventstartdate+'~'+eventenddate+']</p></div></a></div>';
    ul[0].appendChild(li);
}

//var All=false;
//function SelectAll(){
//var selection=document.getElementById("infoitem[]");
//if(all==false){
//    all=true;
//    for(var i=0;i<)
//}
//}



src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";

var id = ""
var c = ""
var on=""
var maxPage = 5
var totalCount
var totalPages
var start
var end

$(".btn").click(function(event){
var e = event.target
if(e.parentElement.parentElement.id == "monthlist"){
if(e.parentElement.id != ""){
 c = e.parentElement.id
 }

}else if(e.parentElement.parentElement.id == "arealist"){
if(e.parentElement.id != ""){
 id = e.parentElement.id
 }
}

console.log("c: "+c+" id: "+id)

//console.log(e.parentElement.parentElement.id)
//
// console.log(e + '    ' +e.parentElement + '   ' + id + '   ' +  c)
 var ul =$('.flnon')
 ul.empty()
 getInfo();
})


$("btn_all_active").click(function(event){
    id = ""
    c = ""
    on= "r"
})
function getInfo(){
    var info;
    var infoitem=[];
    var param = ('&areaCode='+id+'&eventStartDate=2022'+c+'01&eventEndDate=2022'+c+'31')
    var listparam=('&arrange='+on)
    console.log(param);
    console.log('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&'
                            +'numOfRows=10&pageNo='+pageNo+'&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y'+param+listparam)
    fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&'
            +'numOfRows=10&pageNo='+pageNo+'&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y'+param+listparam)
    .then((response) => response.json())
    .then((data) => {
        info = data
        totalCount = data.response.body.totalCount
        infoitem=data.response.body.items.item
        console.log(infoitem)
        $('#totalCount')[0].innerText=totalCount

   if(true){

        for(var j=0; j<infoitem.length; j++){
            printInfo(infoitem[j])
        }
      }
    })
    totalPages = totalCount/maxPage
    start = (pageNo/maxPage) * maxPage + 1
    end = (totalPages == 0) ? 1 : (start + (maxPage - 1) < totalPages ? start + (maxPage - 1) : totalPages)
    paging()
}

function printInfo(infoitem){
    var ul =$('.flnon');
    var li=document.createElement('li');
     var eventstartdate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
     var eventenddate = infoitem.eventenddate.substr(0,4) + '-' + infoitem.eventenddate.substr(4,2) + '-' + infoitem.eventenddate.substr(6,2)
    li.innerHTML = '<div class="imgandtext"><a href="/festivalview/'+infoitem.contentid+'"><image class="image" src="'+infoitem.firstimage+'" alt="이미지 준비중입니다."></image></a><div class="titleanddate"><a href="/festivalview/'+infoitem.contentid+'"><p class="title">'+infoitem.title+'</p><p class="date">['+''+eventstartdate+'~'+eventenddate+']</p></div></a></div>';
    ul[0].appendChild(li);
}
function allInfo(){
  var infoitem=[];
fetch('https://apis.data.go.kr/B551011/KorService/searchFestival?serviceKey=%2Bj0evNiGTyeurclaWudJiAx8TTZR7CIDuaVb7eKSqMRM8cgCFe%2BRjhZUNBZubBIRZhlHxVvK63mnQwy53w%2Bqxg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&eventStartDate=20220101&eventEndDate=20221231')
    .then((response) => response.json())
    .then((data) => {
        info = data;
        infoitem=data.response.body.items.item
        pageNo = data.response.body.pageNo
        totalCount = data.response.body.totalCount
        $('#totalCount')[0].innerText=totalCount
        console.log(infoitem)
          var ul =$('.flnon');
                ul.empty()
        for(var j=0;j<infoitem.length;j++){
             printAll(infoitem[j])
             }
    })
    totalPages = totalCount/maxPage
    start = (pageNo/maxPage) * maxPage + 1
    end = (totalPages == 0) ? 1 : (start + (maxPage - 1) < totalPages ? start + (maxPage - 1) : totalPages)
    paging()
}

function printAll(infoitem){
    var ul =$('.flnon');

    var li=document.createElement('li');
     var eventstartdate = infoitem.eventstartdate.substr(0,4) + '-' + infoitem.eventstartdate.substr(4,2) + '-' + infoitem.eventstartdate.substr(6,2)
     var eventenddate = infoitem.eventenddate.substr(0,4) + '-' + infoitem.eventenddate.substr(4,2) + '-' + infoitem.eventenddate.substr(6,2)
    li.innerHTML = '<div class="imgandtext"><a href="/festivalview/'+infoitem.contentid+'"><image class="image" src="'+infoitem.firstimage+'" alt="이미지 준비중입니다."></image></a><div class="titleanddate"><a href="/festivalview/'+infoitem.contentid+'"><p class="title">'+infoitem.title+'</p><p class="date">['+''+eventstartdate+'~'+eventenddate+']</p></div></a></div>';
    ul[0].appendChild(li);
}

function paging(){
    var inner = '<li class="page-item" th:classappend="'
    if (pageNo == 0)
        inner += 'disabled'
    else
        inner += ''

    inner += '">'
    inner += '<a th:href="@{' + '/course/list?pageNo=' + (pageNo-1) + '}" aria-label="Previous" class="page-link">'
    inner += '   <span aria-hidden="true">Previous</span>'
    inner += '</a>'
    inner += '</li>'

    for(var i = start;i <= end;i++){
        inner += '<li class="page-item" th:classappend="'
        if(pageNo == i-1)
            inner += 'active'
        else
            inner += ''
        inner += '">'
              +  '  <a th:href="@{/course/list?pageNo='+(i-1)+'} th:inline="text" class="page-link">'+i+'</a>'
              +  '</li>'
    }
    inner += '<li class="page-item" th:classappend="'
    if (pageNo+1 >= totalCount)
        inner += 'disabled'
    else
        inner += ''
    inner += '">'
    inner += '  <a th:href="@{/course/list?pageNo='+(pageNo+1)+ '}" aria-label="Next" class="page-link">'
    inner += '  <span aria-hidden="true">Next</span>'
    inner += '  </a>'
    inner += '</li>'

    var li = document.getElementById("paging")
    li.innerHTML = inner
}

allInfo();
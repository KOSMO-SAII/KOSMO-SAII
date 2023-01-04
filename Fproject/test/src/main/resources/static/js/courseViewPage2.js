//제이쿼리 적용
src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";
var optVal=1;
var schedulNum=0;
var listNum=[];
var schedul=[];

//페이지 시작시 실행
     addDays()
     addList()
     hideList(optVal)
     setSchedul()



//날짜 select option 변경 이벤트
$('.days').on('change',function(){
    console.log($(this).val());
    schedulNum=($(this).val()-1);
    optVal=$(this).val();
    hideList(optVal)
    overlaySet();
    var move =new kakao.maps.LatLng(schedul[schedulNum][0].mymarker.mymarker.getPosition().Ma,schedul[schedulNum][0].mymarker.mymarker.getPosition().La)
            map.panTo(move)
})

//day select 생성
function addDays(){
    var sel=$('.days');
    for(var i=1;i<=days;i++){
        var opt=document.createElement("option");
            opt.text=i+"day"
            opt.value=i;
        sel[0].appendChild(opt);
    }

}

//마이 코스 리스트 생성
function addList(){
    for(var i = days; i>=2;i--){
        var ul = document.createElement("ul")
        $('.nickname').after(ul)
        $(ul).attr('class',i)
        $(ul).addClass("My_List")
    }
}

//마이 코스 리스트 숨기기
function hideList(optVal){
        $('.My_List').hide()
        $('.My_List.'+optVal+'').show()

}

//일자별 코스 배열 생성
function setSchedul(){
        for(var i=0;i<days;i++){
            var array=[]
            var num={num:0};
            schedul.push(array);
            listNum.push(num);
        }

}
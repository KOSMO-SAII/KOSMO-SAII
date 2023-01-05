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
    var move =new kakao.maps.LatLng(schedul[schedulNum][0].mymarker.mymarker.getPosition().Ma,schedul[schedulNum][0].mymarker.mymarker.getPosition().La)
            map.panTo(move)
    overlaySet();
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

function alertBoxClose2(){
//    $('#alertBox2').hide()
//    if($('#alertBoxp2')[0].innerText=="제목을 입력해주세요"){
//    $('input[name=ptitle]')[0].focus();
//    }
//    if($('#alertBoxp2')[0].innerText=="지역을 입력해주세요"){
//    $('select[name=pregion]')[0].focus();
//    }
//    if($('#alertBoxp2')[0].innerText=="저장하지 않고 나가시겠습니까?"){
//        var link = '/';
//        location.href=link;
//    }
//    if($('#alertBoxp2')[0].innerText="정말 삭제하시겠습니까??"){
//        return true;
//    }

    var form = document.getElementById("form");
      form.submit();
}

function alertBoxClose3(){
    $('#alertBox2').hide()
    var modalBg = $('.mbg');
    $(modalBg).removeClass('modal-bg');

}

function deleteCheck(){
    $('#alertBoxp2')[0].innerText="정말 삭제하시겠습니까??";
       $('#alertBox2').show()
       var modalBg = $('.mbg');
       $(modalBg).addClass('modal-bg');
}

function review(){
    var form = document.getElementById("reviewform");
          form.submit();
}
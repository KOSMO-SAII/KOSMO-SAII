var days ; //총 여행 일 수
var daycheck=false; //날짜를 선택했는가?
var st; //여행 시작 날짜
var ed; //여행 종료날짜
var optVal=1;
var schedulNum=0;
var listNum=[];
var schedul=[]; //코스 정보 객체 담는 배열
//마이핀 목록 활성화
var checkmode=0;
var polylineArr=[];//line 정보 객체 담는 배열
//라인 정보
var polyline = new kakao.maps.Polyline({
				    map: map,
				    path:[
						new kakao.maps.LatLng(0, 0)
						],
						endArrow:true,
				    strokeWeight: 6,
				    strokeColor: '#98dde3',
				    strokeOpacity: 1,
					});




//날짜 select option 변경 이벤트
$('.days').on('change',function(){
    //console.log($(this).val());
    schedulNum=($(this).val()-1);
    optVal=$(this).val();
    hideList(optVal)
    if(checkmode==1){
        check()
    }
})

//팝업 열기
 function popOpen() {
    //console.log("오픈시작");
    var modalPop = $('.modal-wrap');
    var modalBg = $('.modal-bg');

}

//팝업 닫기
 function popClose() {
    //제목,지역 빈칸 예외처리
    var title =$('input[name=ptitle]')[0].value;
    console.log(title)
    var region = $('select[name=pregion]')[0].value;
    if(title==''){
        alert("제목을 입력해주세요")
        $('.class').slick('slickGoTo', 0);
        return false;
    }else if(region=='없음'){
        alert("지역을 입력해주세요")
        $('.class').slick('slickGoTo', 1);
        return false;
    }



  var modalPop = $('.modal-wrap');
    var modalBg = $('.modal-bg');
    $(modalPop).hide();
    $(modalBg).removeClass('modal-bg');
    $('.inputbox').removeClass('hidden')
    $('input[name=title]')[0].value=$('input[name=ptitle]')[0].value
    $('input[name=region]')[0].value=$('select[name=pregion]')[0].value
    if(daycheck){
     $('input[name=days]')[0].value=days
        $('.start')[0].value=st
         $('.end')[0].value=ed
    }else{
       $('.start')[0].value=dateFormat(new Date());
        $('.end')[0].value=dateFormat(new Date());
     $('input[name=days]')[0].value=1;
    }

     addDays()
     addList()
     hideList(optVal)
     setSchedul()
}

//일자별 코스 배열 생성
function setSchedul(){
    if(days==undefined){
        var array=[]
        var num={num:0};
        schedul.push(array);
        listNum.push(num);
        polylineArr.push(polyline)

    }else{
        for(var i=0;i<days;i++){
            var array=[]
            var num={num:0};
            schedul.push(array);
            listNum.push(num);
            polylineArr.push(polyline)
        }
    }
}

//마이 코스 리스트 숨기기
function hideList(optVal){

       // console.log("값 있음");
        $('.My_List').hide()
        $('.My_List.'+optVal+'').show()

}

//마이 코스 리스트 생성
function addList(){
    if(!days){
    }else{
        for(var i = days; i>=2;i--){
            var ul = document.createElement("ul")
            $('.nickname').after(ul)
            $(ul).attr('class',i)
            $(ul).addClass("My_List")
        }
    }

}

//day select 생성
function addDays(){
   // console.log("데이 실행")
    var sel=$('.days');
     if(!days){
            var opt=document.createElement("option");
                        opt.text=1+"day"
                        opt.value=1;
                    sel[0].appendChild(opt);
            }
    for(var i=1;i<=days;i++){

            var opt=document.createElement("option");
                opt.text=i+"day"
                opt.value=i;
            sel[0].appendChild(opt);
    }

}
//

$(document).ready(function(){
      $('.class').slick({
        slidesToShow: 1, //한번에 보여줄 슬라이드 갯수
        slidesToScroll: 1, //한번에 넘겨지는 슬라이드 갯수
        arrows: true, //넘기기 버튼 화살표 여부
        dots:true, //네비게이션 버튼 dot유형
        asNavFor: '.terms-nav', //함께 움직이는 슬라이드 메뉴영역
        infinite: false, //무한반복 옵션
        adaptiveHeight: true, // 슬라이더 높이를 현재 슬라이드에 맞춤
        draggable:true ,//드래그 가능여부
        prevArrow: $('.prevbtn'),
        nextArrow: $('.nextbtn')
      });

      $('#demo').daterangepicker({
                      "locale": {
                          "format": "YYYY-MM-DD",
                          "separator": " ~ ",
                          "applyLabel": "확인",
                          "cancelLabel": "취소",
                          "fromLabel": "From",
                          "toLabel": "To",
                          "customRangeLabel": "Custom",
                          "weekLabel": "W",
                          "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
                          "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                          "firstDay": 1
                      },
//                      "startDate": new Date(),
//                      "endDate": new Date(),
                      "drops": "down"
                  }, function (start, end, label) {
//                      console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
//                      console.log('New date range selected: ' + start.format('DD') + ' to ' + end.format('DD') + ' (predefined range: ' + label + ')');
//                      console.log(end.format('YYYY-MM-DD')-start.format('YYYY-MM-DD'))
                       st=start.format('YYYY-MM-DD');
                       ed=end.format('YYYY-MM-DD');
                       // console.log(start);
                       // console.log(end);
                      days=(getDateDiff(ed,st)+1);
//                      console.log(days);
                      daycheck=true;

                  });
});

//날짜 계산 함수
const getDateDiff = (d1, d2) => {
  const date1 = new Date(d1);
  const date2 = new Date(d2);

  const diffDate = date1.getTime() - date2.getTime();

  return Math.abs(diffDate / (1000 * 60 * 60 * 24)); // 밀리세컨 * 초 * 분 * 시 = 일
}

//날짜 포맷 변경
function dateFormat(date){
    let dateFormat = date.getFullYear()+'-'+((date.getMonth()+1) <9?"0"+(date.getMonth()+1) : (date.getMonth()+1) )
    +'-'+((date.getDate()) < 9 ?"0"+(date.getDate()) : (date.getDate()) );
    return dateFormat;
}
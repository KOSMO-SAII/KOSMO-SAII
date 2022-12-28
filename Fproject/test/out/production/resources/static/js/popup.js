
  function popOpen() {
    console.log("오픈시작");
    var modalPop = $('.modal-wrap');
    var modalBg = $('.modal-bg');

    $(modalPop).show();
    $(modalBg).show();

}

 function popClose() {


   var modalPop = $('.modal-wrap');
   var modalBg = $('.modal-bg');

   $(modalPop).hide();
   $(modalBg).hide();
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
                      "startDate": "2020-10-21",
                      "endDate": "2020-10-23",
                      "drops": "down"
                  }, function (start, end, label) {
                      console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
                      console.log('New date range selected: ' + start.format('DD') + ' to ' + end.format('DD') + ' (predefined range: ' + label + ')');
                      var start=start.format('DD');
                      var end=end.format('DD');
                      var sum= Math.abs(start-end)+1;
                      console.log(sum);

                  });
});
//달력 스크립트



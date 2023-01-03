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
var pathArr=[];
//임시
var datas=[];
var editsSchedul=[];
var editmode=false;
var checkmode2=false;
var firstCheck=false

if(mode=="edit"){
    var modalPop = $('.modal-wrap');
        var modalBg = $('.mbg');
        $(modalBg).removeClass('modal-bg');
        $(modalPop).css("z-index",-1000)
        $('.inputbox').removeClass('hidden')
        days=editdays;
        addDays()
        setSchArr();
        addList()
        hideList(optVal)
        setEditsSchedul();
        editmode=true;

        firstCheck=true;
}

function setEditsSchedul(){
    for(var i=0;i<days;i++){
        var arr=[];
        editsSchedul.push(arr)
    }
}

//mode가 edit일때만 실행
if(mode=="edit"){

	$('.'+region+'').attr("selected","selected");


	//코스 day별로 나눠서 배열에 저장
    for(var k=0;k<paramObjs.length;k++){
        var cDay=(paramObjs[k].day-1);

    	//오버레이 내용
    	var mycontent='<div class="wrap">' +
    	            '    <div class="info overlay">' +
    	            '        <div class="title">' +
    	                        			paramObjs[k].Place_name +
    	            '            <div class="close r" onclick="overlayclose('+paramObjs[k].corder+')" title="닫기"><input type="hidden" class="hidden" value="'+paramObjs[k].address_id+'"></div>' +
    	            '        </div>' +
    	            '        <div class="body">' +
    	            '            <div class="desc">' +
    	            '                <div class="ellipsis">'+paramObjs[k].Road_address_name+'</div>' +
    	            '                <div class="jibun ellipsis">'+paramObjs[k].address_name+'</div>' +
    	            '                <div class="phone ellipsis">'+paramObjs[k].Phone_number+'</div>' +
    	            '                <div><a href="'+paramObjs[k].Place_url+'" target="_blank" class="link">상세보기</a></div>' +
    	            '            </div>' +
    	            '        </div>' +
    	            '    </div>' +
    	            '</div>';

    	//마이 핀 이미지 생성
    	var imageSrc = '/img/realpin.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
    	    imageSize = new kakao.maps.Size(45, 45),  // 마커 이미지의 크기
    	    imgOptions =  {
    	        spriteSize : new kakao.maps.Size(45, 45), // 스프라이트 이미지의 크기
    	        //spriteOrigin : new kakao.maps.Point(1409, 1033), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
    	        offset: new kakao.maps.Point(22, 35), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    	        shape:'poly',
    	        coords:'19,9,25,9,28,11,31,14,31,21,28,26,22,35,16,26,14,21,14,14,16,11'
    	    },
    	    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions)


    	//마이 핀 마커 생성
    	var mymarker = {
    		mymarker:new kakao.maps.Marker({
    				map: map,
    				position: new kakao.maps.LatLng(paramObjs[k].Y,paramObjs[k].X),
    				image:markerImage
    			}),
    		id:paramObjs[k].address_id
    		}
    	//마이 핀 오버레이 생성
    	var myoverlay={
    			myoverlay:	new kakao.maps.CustomOverlay({
    	    				content: mycontent,
    	    				map: map,
    	    				position: mymarker.mymarker.getPosition(),
    	    				clickable:true
    					}),
    	    	id:paramObjs[k].address_id
    			}
    	//마커,오버레이를 객체로 묶음
    	var mymarkerOb={
    				mymarker:mymarker,
    				myoverlay:myoverlay,
    				id:paramObjs[k].address_id,
    				Object:paramObjs[k]
    			}
    	mymarkerOb.myoverlay.myoverlay.setMap(null);
    	mymarkerOb.mymarker.mymarker.setMap(null);
    	//묶은 객체를 배열에 집어 넣음
    	editsSchedul[cDay].push(mymarkerOb)
    }

   for(var i=0;i<editsSchedul.length;i++){
       for(var j=0;j<editsSchedul[i].length;j++){

            var paramObj = {
                    placex:editsSchedul[i][j].Object.X,
                    placey:editsSchedul[i][j].Object.Y

                };
            var paramObjinfo = {
                    placeName:editsSchedul[i][j].Object.Place_name,
                    placeRaddress:editsSchedul[i][j].Object.Road_address_name  ,
                    placeAddress:editsSchedul[i][j].Object.address_name   ,
                    placePhone:editsSchedul[i][j].Object.Phone_number ,
                    placeUrl:editsSchedul[i][j].Object.Place_url ,
                    placeid:editsSchedul[i][j].Object.address_id,
                    placeCategoryCode:editsSchedul[i][j].Object.category,
                    placex:editsSchedul[i][j].Object.X,
                    placey:editsSchedul[i][j].Object.Y,
                    day:editsSchedul[i][j].Object.day ,
                    corder:editsSchedul[i][j].Object.corder

                };

            rsaveMyPin(paramObj,paramObjinfo,paramObjinfo.day);
       }
   }


    var x=editsSchedul[0][0].Object.X
    var y=editsSchedul[0][0].Object.Y
    var po =new kakao.maps.LatLng(y,x)
    map.panTo(po)

    editmode=false;
    schedulNum=0;
}

$('.popclose').on('click',function(){
    popX()
})

//날짜 select option 변경 이벤트
$('.days').on('change',function(){
    console.log("days 체인지")
    schedulNum=($(this).val()-1);
    optVal=$(this).val();
    hideList(optVal)
    if(checkmode==1){
        check()
    }
    if(checkmode2){
    console.log(schedulNum)
        var move =new kakao.maps.LatLng(schedul[schedulNum][0].mymarker.mymarker.getPosition().Ma,schedul[schedulNum][0].mymarker.mymarker.getPosition().La)
        map.panTo(move)
    }
})

//팝업 열기
 function popOpen() {
    //console.log("오픈시작");
    var modalPop = $('.modal-wrap');
    var modalBg = $('.mbg');
    $(modalPop).css("z-index",1000)
    $(modalBg).addClass('modal-bg');
    if(mode=="edit"){
       console.log("edit 팝업오픈")

      $('input[name=ptitle]')[0].value=$('input[name=title]')[0].value
//        $('input[name=prigion]')[0].value=$('input[name=region]')[0].value

    }

}
//팝업 x닫기
function popX(){
    console.log("x닫기")

    var title =$('input[name=ptitle]')[0].value;
    var region = $('select[name=pregion]')[0].value;

    if(title==''){
        $('input[name=title]')[0].value='제목을 입력해 주세요'
    }else{
        $('input[name=title]')[0].value=$('input[name=ptitle]')[0].value
    }
    if(region=='없음'){
        $('input[name=region]')[0].value='지역을 선택해 주세요'
    }else{
        var xPoint = 0;
            var yPoint = 0;
            switch(region){
                case '서울':
                    yPoint = 126.978652258823
                    xPoint = 37.56682420267543
                    break
                case '인천':
                    yPoint = 126.7052580700657
                    xPoint = 37.456004465652136
                    break
                case '대전':
                    yPoint = 127.384834846753
                    xPoint = 36.3505388993078
                    break
                case '대구':
                    yPoint = 128.601805491082
                    xPoint = 35.8713802646523
                    break
                case '부산':
                    yPoint = 129.07506783124393
                    xPoint = 35.17973748292069
                    break
                case '울산':
                    yPoint = 129.3116012687339
                    xPoint = 35.53959646757943
                    break
                case '세종':
                    yPoint = 127.28919531800284
                    xPoint = 36.48006310909889
                    break
                case '광주':
                    yPoint = 126.85162995901466
                    xPoint = 35.16010195999625
                    break
                case '경기':
                    yPoint = 127.05346120005058
                    xPoint = 37.28900614836743
                    break
                case '강원':
                    yPoint = 127.729829010358
                    xPoint = 37.8853257858225
                    break
                case '충북':
                    yPoint = 127.491457326504
                    xPoint = 36.6353581960153
                    break
                case '충남':
                    yPoint = 126.67277619382
                    xPoint = 36.6588292533059
                    break
                case '전북':
                    yPoint = 127.108976712012
                    xPoint = 35.8201963639598
                    break
                case '전남':
                    yPoint = 126.462788333373
                    xPoint = 34.8160821479338
                    break
                case '경북':
                    yPoint = 128.50580033730782
                    xPoint = 36.57599440982183
                    break
                case '경남':
                    yPoint = 128.69192185958292
                    xPoint = 35.23770717139541
                    break
                case '제주':
                    yPoint = 126.49822914119608
                    xPoint = 33.48891790333224
                    break
            }

            mapCenter = new kakao.maps.LatLng(xPoint, yPoint)

            $('input[name=region]')[0].value=$('select[name=pregion]')[0].value

            moveRegion();
    }
   if(daycheck){
        $('input[name=days]')[0].value=days
        $('.start')[0].value=st
        $('.end')[0].value=ed
   }else{
       $('.start')[0].value=dateFormat(new Date());
       $('.end')[0].value=dateFormat(new Date());
       $('input[name=days]')[0].value=1;
   }


     var modalPop = $('.modal-wrap');
    var modalBg = $('.mbg');
    $(modalBg).removeClass('modal-bg');
    $(modalPop).css("z-index",-1000)
    $('.inputbox').removeClass('hidden')

    setSchedul()
     addDays()
     addList()
     hideList(optVal)

     if(firstCheck){
     }else{
        firstCheck=true;
     }


}

//팝업 저장 후 닫기
 function popClose() {
    //제목,지역 빈칸 예외처리
    var title =$('input[name=ptitle]')[0].value;
    console.log(title)
    var region = $('select[name=pregion]')[0].value;

    var xPoint = 0;
    var yPoint = 0;
    switch(region){
        case '서울':
            yPoint = 126.978652258823
            xPoint = 37.56682420267543
            break
        case '인천':
            yPoint = 126.7052580700657
            xPoint = 37.456004465652136
            break
        case '대전':
            yPoint = 127.384834846753
            xPoint = 36.3505388993078
            break
        case '대구':
            yPoint = 128.601805491082
            xPoint = 35.8713802646523
            break
        case '부산':
            yPoint = 129.07506783124393
            xPoint = 35.17973748292069
            break
        case '울산':
            yPoint = 129.3116012687339
            xPoint = 35.53959646757943
            break
        case '세종':
            yPoint = 127.28919531800284
            xPoint = 36.48006310909889
            break
        case '광주':
            yPoint = 126.85162995901466
            xPoint = 35.16010195999625
            break
        case '경기':
            yPoint = 127.05346120005058
            xPoint = 37.28900614836743
            break
        case '강원':
            yPoint = 127.729829010358
            xPoint = 37.8853257858225
            break
        case '충북':
            yPoint = 127.491457326504
            xPoint = 36.6353581960153
            break
        case '충남':
            yPoint = 126.67277619382
            xPoint = 36.6588292533059
            break
        case '전북':
            yPoint = 127.108976712012
            xPoint = 35.8201963639598
            break
        case '전남':
            yPoint = 126.462788333373
            xPoint = 34.8160821479338
            break
        case '경북':
            yPoint = 128.50580033730782
            xPoint = 36.57599440982183
            break
        case '경남':
            yPoint = 128.69192185958292
            xPoint = 35.23770717139541
            break
        case '제주':
            yPoint = 126.49822914119608
            xPoint = 33.48891790333224
            break
    }

    mapCenter = new kakao.maps.LatLng(xPoint, yPoint)

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
    var modalBg = $('.mbg');
    $(modalBg).removeClass('modal-bg');
    $(modalPop).css("z-index",-1000)
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
     setSchedul()
     addDays()
     addList()
     hideList(optVal)

     if(firstCheck){
     }else{
        firstCheck=true;
     }

    moveRegion();
}

//일자별 코스 배열 생성
function setSchedul(){
   // console.log("days: "+days)
    var delul=$('ul.My_List')
    //console.log("delul.length: "+delul.length)
    if(firstCheck){
        if(days==delul.length){
            //console.log("스케쥴 배열 같음")
        }else if(days>delul.length){
          // console.log("스케쥴 배열 큼")
           var sum=days-delul.length
          // console.log("sum: "+sum)
           for(var i=0;i<sum;i++){
               var array=[]
               var num={num:0};
               var path=[];
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
               schedul.push(array);
               listNum.push(num);
               polylineArr.push(polyline)
               pathArr.push(path)
           }
        }else if(days<delul.length){
           //console.log("스케쥴 배열 작음")
           var sum=delul.length-days;
          for(var i=0;i<sum;i++){
              var array=[]
              var num={num:0};
              var path=[];
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
              schedul.pop();
              listNum.pop();
              polylineArr.pop();
              pathArr.pop();
          }
        }
    }else{
        if(days==undefined){
            var array=[]
            var num={num:0};
            var path=[];
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
            schedul.push(array);
            listNum.push(num);
            polylineArr.push(polyline)
            pathArr.push(path)

        }else{
            for(var i=0;i<days;i++){
                var array=[]
                var num={num:0};
                var path=[];
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
                schedul.push(array);
                listNum.push(num);
                polylineArr.push(polyline)
                pathArr.push(path)
            }
        }
    }
    console.log(schedul)
}

//임시 코드 길이서 메소드로 분리
function setSchArr(){
    if(days==undefined){
                var array=[]
                var num={num:0};
                var path=[];
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
                schedul.push(array);
                listNum.push(num);
                polylineArr.push(polyline)
                pathArr.push(path)

            }else{
                for(var i=0;i<days;i++){
                    var array=[]
                    var num={num:0};
                    var path=[];
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
                    schedul.push(array);
                    listNum.push(num);
                    polylineArr.push(polyline)
                    pathArr.push(path)
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
    var delul=$('ul.My_List')
    console.log("days: "+days)
    console.log("delullength: "+delul.length)
    if(firstCheck){
        if(days==delul.length){
        console.log("리스트 갯수 같음")
        }else if(days>delul.length){
            console.log("리스트 갯수 작음")
            var num=days-delul.length;
            var num2=delul.length+1;
            for(var i=0;i<num;i++){
                var ul = document.createElement("ul")
                $('.nickname').after(ul)
                $(ul).attr('class',num2++)
                $(ul).addClass("My_List")
            }
        }else if(days<delul.length){
        console.log("리스트 갯수 큼")
            var length=(delul.length-1);
            var num=(delul.length-days);
            var num2=days;
            for(var i=0;i<num;i++){
                delul[i].remove();
            }
        }
    }else{
        for(var i=0;i<delul.length;i++){
             delul[i].remove()
        }

        if(!days){
            var ul = document.createElement("ul")
            $('.nickname').after(ul)
            $(ul).attr('class',1)
            $(ul).addClass("My_List")
        }else{
            for(var i = days; i>=1;i--){
                var ul = document.createElement("ul")
                $('.nickname').after(ul)
                $(ul).attr('class',i)
                $(ul).addClass("My_List")
            }

    }
    }

}

//day select 생성
function addDays(){
   // console.log("데이 실행")
    var sel=$('.days');
    sel.empty();
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
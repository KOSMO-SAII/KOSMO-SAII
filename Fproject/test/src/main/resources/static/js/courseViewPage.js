//제이쿼리 적용
src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";

//==========이하 지도 api부분
// 마커를 담을 배열입니다
//var markers = [];

//임시 전송할 데이터 담는 배열
var datas=[];

//선그리기 경로 배열
var path=[];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(paramObjs[0].Y,paramObjs[0].X), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };


// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

//=============불러온 값들의 마커,오버레이 출력
//마커,오버레이 정보 담을 배열
var mymarkers=[];

//라인 정보
var polyline = new kakao.maps.Polyline({
				    map: map,
				    path:[
						new kakao.maps.LatLng(0, 0)
						],endArrow:true,
				    strokeWeight: 6,
				    strokeColor: '#98dde3',
				    strokeOpacity: 1,
					});
var path=[];

//메모 기능 인덱스 넘버 부여
var index;


//코스 day별로 나눠서 배열에 저장
for(var k=0;k<paramObjs.length;k++){
    var cDay=(paramObjs[k].day-1);

	//오버레이 내용
	var mycontent='<div class="wrap">' +
	            '    <div class="info overlay">' +
	            '        <div class="mapoverlay">' +
	                        			paramObjs[k].Place_name +
	            '            <div class="close r" onclick="overlayclose('+paramObjs[k].corder+')" title="닫기"><input type="hidden" class="hidden" value="'+paramObjs[k].address_id+'"></div>' +
	            '			 <input type="hidden" class="hidden" value="'+paramObjs[k].address_id+'">'+
	            '        </div>' +
	            '        <div class="mapoverlaybody">' +
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
	schedul[cDay].push(mymarkerOb)
}

for(var i=0;i<schedul.length;i++){
    for(var j=0;j<schedul[i].length;j++){

        	//=========================== 좌측 마이 스케쥴 출력
        	var li=document.createElement('li');
        	var div=document.createElement('div');
        	var	data={
        			data: schedul[i][j].Object.category+"|"+schedul[i][j].Object.address_id+"|"+schedul[i][j].Object.address_name+"|"+schedul[i][j].Object.Road_address_name+"|"
        				+schedul[i][j].Object.Phone_number+"|"+schedul[i][j].Object.Place_name+"|"+schedul[i][j].Object.Place_url+"|"+schedul[i][j].Object.X+"|"+schedul[i][j].Object.Y+"|"+
        						schedul[i][j].Object.Memo
        		}
        		li.innerHTML=
        				'	 <input type="hidden" class="hidden" value="'+schedul[i][j].Object.address_id+'"/>'+
        	            '    <div class="info">' +
        	            '       <div class="title">' +
        	                        			schedul[i][j].Object.Place_name +
        	            '        </div>' +
        	            '        <div class="body">' +
        	            '            <div class="desc">' +
        	            '                <div class="ellipsis">'+schedul[i][j].Object.Road_address_name+'</div>' +
        	            '                <div class="jibun ellipsis">'+schedul[i][j].Object.address_name+'</div>' +
        	            '                <div class="phone ellipsis">'+schedul[i][j].Object.Phone_number+'</div>' +
        	            '                <div><a href="'+schedul[i][j].Object.Place_url+'" target="_blank" class="link">상세보기</a></div>'  +
        				'            </div>' +
        	            '		 <input type="hidden" class="data" name="data" value="">'+
        	            '		 <button type="button" class="memobtn" >메모보기'+
        	            '		 </button>' +
        	            '    	</div>' +
        	            '	 </div>';

        		var ul =$('.My_List.'+schedul[i][j].Object.day+'');
        		if(j>0){
        			div.innerHTML +=
                  		'        <div class="url"><a href="https://map.kakao.com/link/to/' + schedul[i][j].Object.Place_name + ',' + schedul[i][j].Object.Y + ',' + schedul[i][j].Object.X +
                   		'        /from/' + schedul[i][j-1].Object.Place_name + ',' + schedul[i][j-1].Object.Y + ',' + schedul[i][j-1].Object.X + '" target="_blank" />경로보기</a></div>' ;
                   		ul[0].appendChild(div);
          				 }
        		ul[0].appendChild(li);


        		datas.push(data);
        		$('.data')[j].defaultValue=datas[j].data;

                //마커 클릭시 오버레이 열기
                (function(marker, i,j) {

                    //리스트 클릭스 오버레이 출력
                    kakao.maps.event.addListener(marker,'click',function(){
                        console.log(i);
                        console.log(j);
                        console.log(schedul[i][j].myoverlay.myoverlay);
                        //marker.setMap(map);
                        schedul[i][j].myoverlay.myoverlay.setMap(map);
                    });
                })(schedul[i][j].mymarker.mymarker, i,j);
    }
}

overlaySet()



//day에 따른  오버레이 변경
function overlaySet(){
    for(var i=0;i<schedul.length;i++){
        for(var j=0;j<schedul[i].length;j++){
                schedul[i][j].mymarker.mymarker.setMap(null);
                schedul[i][j].myoverlay.myoverlay.setMap(null);
            }
        }
    for(var k=0;k<schedul[schedulNum].length;k++){
        schedul[schedulNum][k].mymarker.mymarker.setMap(map);
        schedul[schedulNum][k].myoverlay.myoverlay.setMap(map);
    }

    //오버레이 z-inex변경
    $(".info.overlay").off();
    $(".info.overlay").click(function(){
        console.log($(this))
        console.log($(this)[0].firstElementChild.lastElementChild.value)
        var value=$(this)[0].firstElementChild.lastElementChild.value;
        for(var i=0;i<schedul[schedulNum].length;i++){
            schedul[schedulNum][i].myoverlay.myoverlay.setZIndex(0)
            if(schedul[schedulNum][i].id==value){
                schedul[schedulNum][i].myoverlay.myoverlay.setZIndex(1)

            }
        }
    });

    makeline()

}
//생성한 리스트에 마우스 오버시 화면 이동(임시)
$('.My_List li .title').mouseover(function(event){
    var li=event.target.parentElement.parentElement;

    var val=li.firstElementChild.defaultValue

    for(var i = 0 ;i<schedul[schedulNum].length;i++){
        if (schedul[schedulNum][i].id==val){
        var mapmove=new kakao.maps.LatLng(schedul[schedulNum][i].mymarker.mymarker.getPosition().Ma,schedul[schedulNum][i].mymarker.mymarker.getPosition().La)
        schedul[schedulNum][i].myoverlay.myoverlay.setMap(map);
        map.panTo(mapmove);
        break;
        }
    }
})

//오버레이 닫기
function overlayclose(k){
    //console.log(schedul[schedulNum][k])
	schedul[schedulNum][k].myoverlay.myoverlay.setMap(null);
}

//마이 핀 라인 생성
function makeline(){
	//console.log("선긋기 시작")
	polyline.setMap(null);
	if(polyline.getPath()[0].La== 0 && polyline.getPath()[0].Ma==0){
		//console.log("0,0값 실행")
		for(var i=0;i<schedul[schedulNum].length;i++){
			var ma= schedul[schedulNum][i].mymarker.mymarker.getPosition().Ma,
				la=schedul[schedulNum][i].mymarker.mymarker.getPosition().La;
			//넘겨 받은 좌표 값 설정
			var coord = new kakao.maps.LatLng(ma, la);

			path.push(coord);

		}
		polyline.setPath(path);
		//polyline.setMap(map);
	}else{
		path=[];

		for(var i=0;i<schedul[schedulNum].length;i++){
			var ma= schedul[schedulNum][i].mymarker.mymarker.getPosition().Ma;
			var	la=schedul[schedulNum][i].mymarker.mymarker.getPosition().La;

			var coord = new kakao.maps.LatLng(ma, la);

			path.push(coord);
		}
		polyline.setPath(path);
	}
	polyline.setMap(map);
}

// 메모버튼클릭시 메모저장 박스 출력
$('.memobtn').click(function(event){
   // console.log(event.target.parentElement.parentElement);
    memobox(event);
    //console.log("Dd")
})
//
//	//=========== 메모
//
	//메모 박스 출력
function memobox(event){

    //none 클래스 삭제
    var e = event.target.parentElement.parentElement.parentElement;
    console.log("========");
    var num=(schedulNum+1);
    var li= $('.'+num+" "+'li');
    console.log(li);
    var index;
    for(var i =0;i<li.length;i++){
        if(li[i]==e){
            console.log(li[i]);
            index=i;

            //console.log("index= "+index)
        }

    }


    $('#memobox').removeClass('none');


    //title 부여
    var text=$('.'+num+" "+'.info .title')[index].innerText;
    //console.log(text);
    $('#memobox .title').text(text);

    //memo content 부여
    if(schedul[schedulNum][index].Object.Memo!=undefined){
    $('#memobox textarea')[0].value=schedul[schedulNum][index].Object.Memo;
    }
}

//메모 박스 숨기기
$('#memobox .close').click(function(){
    $('#memobox').addClass('none');
});

    //임시======================
	//마커,오버레이 지도에 설정
//	schedul[cDay].mymarker.mymarker.setMap(map);
//	schedul[cDay].myoverlay.myoverlay.setMap(map);
//
//
//
//
//
//	//마커 클릭시 오버레이 열기
//	for(var i=0; i< schedul[cDay].length;i++){
//
//		(function(marker, index) {
//
//			//리스트 클릭스 오버레이 출력
//            kakao.maps.event.addListener(marker,'click',function(){
//				mymarkers[index].myoverlay.myoverlay.setMap(map);
//			});
//        })(schedul[i].mymarker.mymarker, i);
//
//
//
//	}
//
//
//	//=========================== 좌측 마이 스케쥴 출력
//	var li=document.createElement('li');
//	var div=document.createElement('div');
//
//
//
//
//	var	data={
//			data: paramObjs[k].category+"|"+paramObjs[k].address_id+"|"+paramObjs[k].address_name+"|"+paramObjs[k].Road_address_name+"|"
//				+paramObjs[k].Phone_number+"|"+paramObjs[k].Place_name+"|"+paramObjs[k].Place_url+"|"+paramObjs[k].X+"|"+paramObjs[k].Y+"|"+
//						paramObjs[k].Memo
//		}
//		li.innerHTML=
//				'	 <input type="hidden" class="hidden" value="'+paramObjs[k].address_id+'"/>'+
//	            '    <div class="info">' +
//	            '       <div class="title">' +
//	                        			paramObjs[k].Place_name +
//	            '        </div>' +
//	            '        <div class="body">' +
//	            '            <div class="desc">' +
//	            '                <div class="ellipsis">'+paramObjs[k].Road_address_name+'</div>' +
//	            '                <div class="jibun ellipsis">'+paramObjs[k].address_name+'</div>' +
//	            '                <div class="phone ellipsis">'+paramObjs[k].Phone_number+'</div>' +
//	            '                <div><a href="'+paramObjs[k].Place_url+'" target="_blank" class="link">상세보기</a></div>'  +
//				'            </div>' +
//	            '		 <input type="hidden" class="data" name="data" value="">'+
//	            '		 <button type="button" class="memobtn" >메모'+
//	            '		 </button>' +
//	            '    	</div>' +
//	            '	 </div>';
//
//		var ul =$('.My_List.'+cDay+'');
//		if(k>0){
//			div.innerHTML +=
//          		'        <div class="url"><a href="https://map.kakao.com/link/to/' + paramObjs[k].Place_name + ',' + paramObjs[k].Y + ',' + paramObjs[k].X +
//           		'        /from/' + paramObjs[k-1].Place_name + ',' + paramObjs[k-1].Y + ',' + paramObjs[k-1].X + '" target="_blank" />경로보기</a></div>' ;
//           		ul[0].appendChild(div);
//  				 }
//		ul[0].appendChild(li);
//
//
//		datas.push(data);
//		$('.data')[k].defaultValue=datas[k].data
//
//		//num+=1;
//
//		//생성한 리스트에 마우스 오버시 화면 이동(임시)
//		$('.My_List li .title').mouseover(function(event){
//			var li=event.target.parentElement.parentElement;
////			console.log($(li))
////			console.log(li.firstElementChild.defaultValue);
//			var val=li.firstElementChild.defaultValue
//			//console.log($('#My_List').children(li))
//			for(var i = 0 ;i<schedul[cDay].length;i++){
//				if (schedul[i].id==val){
//				var mapmove=new kakao.maps.LatLng(schedul[i].mymarker.mymarker.getPosition().Ma,schedul[i].mymarker.mymarker.getPosition().La)
//				//mymarkers[i].mymarker.setMap(map);
//				map.panTo(mapmove);
//				break;
//				}
//			}
//		})
//
//
//	//=========== 메모
//	//인덱스 넘버 부여
//	var index;
//
//	//메모 박스 출력
//	function memobox(event){
//
//		//none 클래스 삭제
//		var e = event.target.parentElement.parentElement.parentElement;
//		console.log("========");
//        var li= $('li');
//        var index;
//        for(var i =0;i<li.length;i++){
//            if(li[i]==e){
//                index=i;
//                console.log(index)
//            }
//
//        }
//
//
//		$('#memobox').removeClass('none');
//
//
//		//title 부여
//		var text=$('.info .title')[index].innerText;
//		//console.log(text);
//		$('#memobox .title').text(text);
//
//		//memo content 부여
//		if(paramObjs[index].Memo!=undefined){
//		$('#memobox textarea')[0].value=paramObjs[index].Memo;
//		}
//	}
//
//	//메모 박스 숨기기
//	$('#memobox .close').click(function(){
//		$('#memobox').addClass('none');
//	});
// //임시
//		//임시, 메모버튼클릭시 메모저장 박스 출력
//		$('.memobtn').click(function(event){
//		   // console.log(event.target.parentElement.parentElement);
//			memobox(event);
//		})
//
////오버레이 닫기
//function overlayclose(j){
//
//	schedul[j].myoverlay.myoverlay.setMap(null);
//}
//
////==================선 그리기(값 두개 넣어보고 하기)
////라인 정보
//var polyline = new kakao.maps.Polyline({
//				    map: map,
//				    path:[
//						new kakao.maps.LatLng(0, 0)
//						],endArrow:true,
//				    strokeWeight: 6,
//				    strokeColor: '#98dde3',
//				    strokeOpacity: 1,
//					});
//
////마이 핀 라인 생성
//for(var p=0;p<schedul[cDay].length;p++){
//    makeline();
//}
//
//function makeline(){
//	//console.log("선긋기 시작")
//	polyline.setMap(null);
//	if(polyline.getPath()[0].La== 0 && polyline.getPath()[0].Ma==0){
//		//console.log("0,0값 실행")
//		for(var i=0;i<schedul[cDay].length;i++){
//			var ma= schedul[cDay][i].mymarker.mymarker.getPosition().Ma,
//				la=schedul[cDay][i].mymarker.mymarker.getPosition().La
//			//넘겨 받은 좌표 값 설정
//			var coord = new kakao.maps.LatLng(ma, la);
//
//			path.push(coord);
//
//		}
//		polyline.setPath(path);
//		//polyline.setMap(map);
//	}else{
//		path=[];
//
//		for(var i=0;i<schedul[cDay].length;i++){
//			var ma= schedul[i].mymarker.mymarker.getPosition().Ma,
//				la=schedul[i].mymarker.mymarker.getPosition().La
//
//			var coord = new kakao.maps.LatLng(ma, la);
//
//			path.push(coord);
//		}
//		polyline.setPath(path);
//	}
//	polyline.setMap(map);
//}






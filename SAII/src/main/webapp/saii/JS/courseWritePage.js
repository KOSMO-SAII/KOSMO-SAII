//임시 주석
//제이쿼리 적용
src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";

//======수정 페이지에서 넘어온 데이터 출력
//내가 저장한 핀 리스트에 부여할 넘버 및 
var num=0;

//내가 저장한 핀 담을 배열
var mymarkers=[];

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
//라인 그리기 좌표 담는 배열					
var path=[];

//마이핀 목록 활성화
var checkmode=0;

//==========이하 지도 api부분
// 마커를 담을 배열입니다
var markers = [];

//임시 데이타
var data={};

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

//mode가 edit일때만 실행
if(mode=="edit"){
	
	$('.'+region+'').attr("selected","selected");
	
	var po =new kakao.maps.LatLng(paramObjs[0].Y,paramObjs[0].X)
	map.panTo(po)
	
 for(var j=0; j<paramObjs.length;j++){
	var paramObj = {
			placex:paramObjs[j].X,
			placey:paramObjs[j].Y
			
		};
	var paramObjinfo = {
			placeName:paramObjs[j].Place_name,
			placeRaddress:paramObjs[j].Road_address_name  ,
			placeAddress:paramObjs[j].address_name   ,
			placePhone:paramObjs[j].Phone_number ,
			placeUrl:paramObjs[j].Place_url ,
			placeid:paramObjs[j].address_id,
			
			placeCategoryCode:paramObjs[j].category,
			placex:paramObjs[j].X,
			placey:paramObjs[j].Y
		};
	
	rsaveMyPin(paramObj,paramObjinfo);
	
	mymarkers[j].data.memo=paramObjs[j].Memo;
	$('.info .data')[j].defaultValue+=mymarkers[j].data.memo
	}
	
	
}
//================
//코스저장 전 체크
function coursecheck(){
	//console.log(mymarkers[0]);
	if(mymarkers[0]==undefined){
		alert("코스를 저장해주세요");
		return false;
	}else{
		return true;
	}
}

//==검색 사이드 바 드롭다운
let subToggle=true;
	$('.searchbtn').click(function(){
		var menu=document.getElementById('menu_wrap_box');
		if(subToggle){
		//console.log(menu);
		menu.style.height='90%';
		//console.log($('#menu_wrap'));
		//console.log($('#menu_wrap').style);
		$('#menu_wrap').css({"overflow":"scroll", "overflow-x":"hidden"}); 
		$('#arrow').text("▲")
		//$('#arrow').attr("onclick","javascript:window.scrollTo(0,0)")
		}
		subToggle=!subToggle;
	})
	
	$('#arrow').click(function() {
		var menu=document.getElementById('menu_wrap_box');
		if(subToggle){
		menu.style.height='90%';
		$('#menu_wrap').css({"overflow":"scroll", "overflow-x":"hidden"});
		//$('#arrow').attr("onclick","javascript:window.scrollTo(0,0)")
		$('#arrow').text("▲")
		subToggle=!subToggle;
		}else{
			menu.style.height='10%';
			$('#menu_wrap').scrollTop(0);
			$('#menu_wrap').css({"overflow":"scroll", "overflow-x":"hidden"});
			//$('#arrow').removeAttr("onclick")
			$('#arrow').text("▼")
		subToggle=!subToggle;
		}
	})
//==검색 사이드 바 드롭다운
let subToggle2=true;
	$('#arrow2').click(function() {
		var box=document.getElementById('box');
		var ctbox=document.getElementById('category-box');
		if(subToggle2){
			box.style.width='10px';
			$('#category-box').css('display', 'none');
			$('#arrow2').text("◀")
		subToggle2=!subToggle2;
		}else{
		box.style.width='357px';
		$('#category-box').css('display', 'block');
		$('#arrow2').text("▶")
		subToggle2=!subToggle2;
		}
	})



//-------------------------------------------------위는 기본 필수 코드
//=================================================아래: 왼쪽 사이드 검색장소 핀 코드

//키워드 저장 변수
//var keyword ='';


//검색 결과 클릭시 마커,오버레이 담을 변수 생성(임시)
var soverlay ;
var smarker;

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

     keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
 
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);
        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
            marker.setMap(map);

		//console.log(marker);
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
			
			//리스트 클릭스 오버레이 출력
            kakao.maps.event.addListener(marker, 'click', function() {
				if(soverlay!=undefined){
					soverlay.setMap(null);
				}
               displayInfowindow(marker, title);
               
               var placePosition = new kakao.maps.LatLng(title.y, title.x)
               map.panTo(placePosition);
                //searchmarker=1;
                //console.log("click");
                //console.log(searchmarker);
            });

            /*itemEl.onmouseover =  function () {
			//console.log("마우스 오버")
             hovereventlist(marker,title);
               
            };

            itemEl.onmouseout =  function () {
				
				//removeSearchMarker(marker);
			
            }; */
        })(marker, places[i]);

        fragment.appendChild(itemEl);
        
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
    
    //검색 목록 결과 클릭시 마커,오버레이 표시 이벤트 등록
		addEventClick(places);
}

//리스트 마우스 오버시 화면 이동
function hovereventlist(marker,position){
	//console.log("호버이벤트 실행")
	//console.log(marker.getPosition().Ma);
	//console.log(marker.getPosition().La);

	var po =new kakao.maps.LatLng(marker.getPosition().Ma,marker.getPosition().La)
	map.panTo(po)
}

//검색 목록 결과 클릭시 마커,오버레이 표시
function addEventClick(place){
	//console.log("한번만 실행 되야함")
		$("#placesList li").click(function(){
			console.log("리스트 클릭")
			
			if(soverlay!=undefined){
			soverlay.setMap(null);
			}
			var n = $(this).index();
			var placePosition = new kakao.maps.LatLng(place[n].y, place[n].x),
				smarker = addMarker(placePosition);
			displayInfowindow(smarker, place[n]);
			
			map.panTo(placePosition);
			
		})
	
}


// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image:markerImage
		        });
        //
    //marker.setMap(map);
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    //marker.setMap(map);
    searcAddOverLay(marker,title);
}
function displayInfomarker(marker ) {
    marker.setMap(map);
    
}

var mysplace ;
var mysplaceinfo ;


//검색정보 오버레이 정보 생성 및 표시

function searcAddOverLay(marker,places){
	mysplace = {
		placex:places.x,
		placey:places.y,
		ma:marker.getPosition().Ma,
		la:marker.getPosition().La
	};
	mysplaceinfo = {
		placeName:places.place_name,
		placeRaddress:places.road_address_name  ,
		placeAddress:places.address_name   ,
		placePhone:places.phone  ,
		placeUrl:places.place_url ,
		placeid:places.id,
		
		placeCategoryCode:places.category_group_code,
		placex:places.x,
		placey:places.y
	};
	
	//오버레이에 표시할 정보 
	var content = '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class=" mapoverlay">' + 
                        			places.place_name + 
            '            <div class="close" onclick="scloseOverlay()" title="닫기"></div>' + 
            '        </div>' + 
            '        <div class="mapoverlaybody">' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">'+places.road_address_name+'</div>' + 
            '                <div class="jibun ellipsis">'+places.address_name+'</div>' + 
            '                <div class="phone ellipsis">'+places.phone+'</div>' + 
            '                <div><a href="'+places.place_url+'" target="_blank" class="link">상세보기</a></div>' + 
            '            </div>' + 
            '			 <div class="button">' +
            '				<button  onclick="saveMySearchPin()" >장소 선택</button>'+
            '			 </div>' +
            '        </div>' + 
            '    </div>' +    
            '</div>';
            
    // 마커 위에 커스텀오버레이를 표시합니다
     // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	 soverlay = new kakao.maps.CustomOverlay({
    	content: content,
    	map: map,
    	position: marker.getPosition() ,
    	clickable:true          
		});
		
    soverlay.setMap(map);
}

//검색 결과 핀 저장
function saveMySearchPin(){
	rsaveMyPin(mysplace,mysplaceinfo);

}



//검색 결과 오버레이 닫기 
function scloseOverlay() {
    soverlay.setMap(null);  
	//smarker.setMap(null);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

//마우스 아웃시 검색 마커,오버레이 삭제
function removeSearchMarker(marker){
	marker.setMap(null);
}


//====================우측 상단 카테고리 별 핀 추가 메서드
//검색 객체 생성
var psc = new kakao.maps.services.Places(map);

//오버레이 담을 변수 생성
var overlayOb ;

//마커들을 만들고 지우기 위한 배열
var markersc=[];
var markerscId=[];

//카테고리 요소 선택
var li = document.querySelectorAll('#category-box li');
	//console.log(li)
//현재 카테고리명 담는 변수
var currCategory= '';

for(var i=0;i<li.length;i++){

	//우측상단 카테고리에 클릭 이벤트 생성
	li[i].addEventListener('click',function(event){
		var e = event.target;
		
		for(var j=0;j<li.length;j++){
			li[j].style.background='#fff';
		}
		
		
		
		if(e.localName=='li'){
			if(e.id!=currCategory){
			currCategory = e.id;
			psc.categorySearch(currCategory, placesSearchCBCategory, {useMapBounds:true}); 
			e.style.background='#98dde3';
			}else if(e.id==currCategory){
				currCategory='';
				removeMarkerCategory();
				 closeOverlay();
			}
		}else if(e.localName=='span'){
			var p = e.parentElement;
			if(p.id!=currCategory){
			currCategory = p.id;
			psc.categorySearch(currCategory, placesSearchCBCategory, {useMapBounds:true}); 
			p.style.background='#98dde3';
			}else if(p.id==currCategory){
				currCategory='';
				removeMarkerCategory();
				if(overlayOb!=undefined){
				 closeOverlay();
				 }
			}

		}		
		addListenr();	
	})
}

//지도 확대,축소,이동에 따른 idle효과 등록
function addListenr(){
	console.log("아이들 이벤트 변경")
	console.log(currCategory)
		if(currCategory!=''){
			kakao.maps.event.addListener(map, 'idle',categorySearch);
		}else if(currCategory==''){
			kakao.maps.event.removeListener(map, 'idle',categorySearch);
		}
}

//카테고리검색 메서드
function categorySearch(){
	psc.categorySearch(currCategory, placesSearchCBCategory, {useMapBounds:true}); 
}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCBCategory (data, status) {
    if (status === kakao.maps.services.Status.OK) {
		removeMarkerCategory();
        for (var i=0; i<data.length; i++) {
            displayMarkerCategory(data[i]);    
        }       
    }else {
	removeMarkerCategory();
}

	//idle 이벤트 발생시 마커가 사라질때 오버레이도 삭제
	/*if(overlayOb!=null){
		var exist=0;
		for(var i=0;i<markerscId.length;i++){
			if(markerscId[i]==overlayOb.id){	
				exist=1;
				break;
			}
		}
		if(exist==0){
			closeOverlay();
		}
	}*/
}

// 지도에 마커를 표시하는 함수입니다
function displayMarkerCategory(place) {
    // 마커를 생성하고 지도에 표시합니다
    var markerc = addMarkerCategory(place);
   
    // 마커 클릭스 오버레이 이벤트 등록
    kakao.maps.event.addListener(markerc, 'click', function() {
		//다른 오버레이가 열려있을시 닫음
	if(overlayOb!=null){
		closeOverlay();
	}
	addOverLay(markerc,place);
	
	  var placePosition = new kakao.maps.LatLng(place.y, place.x)
               map.panTo(placePosition);
	});	
	
	
}

//키타고리 마커 생성 메서드
function addMarkerCategory(place){
	markerc = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
        zIndex: -1
    });
    
    markerc.setMap(map);
    markersc.push(markerc);
    
    //임시
    var markercId = place.id;
	markerscId.push(markercId) ;
	   
    return markerc;
}

//카테고리 마커 지우는 메소드
function removeMarkerCategory(){
	for ( var i = 0; i < markersc.length; i++ ) {
        markersc[i].setMap(null);
    }   
    markersc = [];
    markerscId=[];
}

//마이 핀 좌표 저장 변수
var myplace;
//임시
var myplaceinfo;

//오버레이 정보 생성 및 표시
function addOverLay(markerc,place){	
	 myplace = {
		placex:place.x,
		placey:place.y,
		ma:markerc.getPosition().Ma,
		la:markerc.getPosition().La
	};
	myplaceinfo = {
		placeName:place.place_name,
		placeRaddress:place.road_address_name  ,
		placeAddress:place.address_name   ,
		placePhone:place.phone  ,
		placeUrl:place.place_url ,
		placeid:place.id,
		
		placeCategoryCode:place.category_group_code,
		placex:place.x,
		placey:place.y
	};
	//console.log(place.phone)
	
	//오버레이에 표시할 정보 
	var content = '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class=" mapoverlay">' + 
                        			place.place_name + 
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
            '        </div>' + 
            '        <div class="mapoverlaybody">' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">'+place.road_address_name+'</div>' + 
            '                <div class="jibun ellipsis">'+place.address_name+'</div>' + 
            '                <div class="phone ellipsis">'+place.phone+'</div>' + 
            '                <div><a href="'+place.place_url+'" target="_blank" class="link">상세보기</a></div>' + 
            '            </div>' + 
            '			 <div class="button">' +
            '				<button  onclick="saveMyPin()" >장소 선택</button>'+
            '			 </div>' +
            '        </div>' + 
            '    </div>' +    
            '</div>';
            

            
    // 마커 위에 커스텀오버레이를 표시합니다
     // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	 overlayOb = {
			overlay:new kakao.maps.CustomOverlay({
    				content: content,
    				map: map,
    				position: markerc.getPosition(),
    				clickable:true    
					}),
			id:place.id
			};

}
//오버레이 닫기 
function closeOverlay() {
    overlayOb.overlay.setMap(null);   
    overlayOb=null;
      
}


//================================왼쪽 화면에 마이 핀 정보 보이기




//내가 저장한 핀 실행 메서드1
 function saveMyPin(){
	rsaveMyPin(myplace,myplaceinfo);
	
}

//내가 저장한 핀 실행 메서드2
function rsaveMyPin(myplace,myplaceinfo){
	//console.log("rsaveMyPin 실행")

	//중복체크 임시
	if(mymarkers[0]!=undefined){
		for(var i=0;i<mymarkers.length;i++){
			if(mymarkers[i].id==myplaceinfo.placeid){
				alert("같은 장소 중복 선택불가")
				//console.log("중복")
				return ;
			}
			
		}
	}
	
	//핀은 최대 7개까지 고정 가능
	if(num<7){
		//마커이미지 생성
		var imageSrc = 'http://localhost:8081/SAII/saii/img/realpin.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(45, 45),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(45, 45), // 스프라이트 이미지의 크기
            //spriteOrigin : new kakao.maps.Point(1409, 1033), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(22, 35), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            shape:'poly',
            coords:'19,9,25,9,28,11,31,14,31,21,28,26,22,35,16,26,14,21,14,14,16,11'
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions)
		
		
		//마서 생성
		var mymarker = {
					mymarker:new kakao.maps.Marker({
			    		map: map,
			    		position: new kakao.maps.LatLng(myplace.placey , myplace.placex),
			    		image:markerImage,
			    		id:myplaceinfo.placeid
								}),
					id:myplaceinfo.placeid
					}
		
		//오버레이 생성
		var mycontent='<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class="title ">' + 
                        			myplaceinfo.placeName + 
            '            <div class="close r"  title="닫기"></div>' + 
            '			 <input type="hidden" class="hidden" value="'+myplaceinfo.placeid+'">'+
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">'+myplaceinfo.placeRaddress+'</div>' + 
            '                <div class="jibun ellipsis">'+myplaceinfo.placeAddress+'</div>' + 
            '                <div class="phone ellipsis">'+myplaceinfo.placePhone+'</div>' + 
            '                <div><a href="'+myplaceinfo.placeUrl+'" target="_blank" class="link">상세보기</a></div>' + 
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';
		
		var myoverlay={
					myoverlay:new kakao.maps.CustomOverlay({
		    				content: mycontent,
		    				map: map,
		    				position: mymarker.mymarker.getPosition(),
		    				clickable:true ,  
		    				id: myplaceinfo.placeid
							}),
					id: myplaceinfo.placeid
					}
		
		//console.log(mymarker.mymarker.getPosition())
		//console.log(myoverlay.myoverlay.getPosition());
		//?왠진 모르지만 됨/마커와 오버레이를 객체로 마이 핀 배열에 저장
		mymarker.mymarker.setMap(null);
		myoverlay.myoverlay.setMap(null);
	
		 mymarkerOb={
			mymarker:mymarker,
			myoverlay:myoverlay,
			data:data,
			id:myplaceinfo.placeid
		}
		
		mymarkers.push(mymarkerOb);
		
		//console.log(mymarkers);
		
		//마이핀 라인 생성
		makeline();
		
		//좌측 마이스케줄 블럭에 내가 저장한 핀 정보 출력
		addMySchedule(myplaceinfo);
	}else{
		alert("핀은 최대 7개까지 저장 가능합니다");
	}
	//임시 목록보기 중이면 실시간 갱신
	if(checkmode==1){
		check();
	}
}
//임시 


//좌측 마이스케줄 블럭에 내가 저장한 핀 정보 출력
function addMySchedule(place){
	//console.log("addMySchedule실행")
	//console.log(num);
	//db에 전달할 정보
	mymarkers[num].data={data: place.placeCategoryCode+"|"+place.placeid+"|"+place.placeAddress+"|"+place.placeRaddress+"|"
				+place.placePhone+"|"+place.placeName+"|"+place.placeUrl+"|"+place.placex+"|"+place.placey+"|",
						memo:""}
	
	var li=document.createElement('li');	
	li.innerHTML=
	        '	 <input type="hidden" class="id" name="as" value="'+place.placeid+'">'+
            '    <div class="info">' + 
            '       <div class="title">' + 
                        			place.placeName + 
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">'+place.placeRaddress+'</div>' + 
            '                <div class="jibun ellipsis">'+place.placeAddress+'</div>' + 
            '                <div class="phone ellipsis">'+place.placePhone+'</div>' + 
            '                <div><a href="'+place.placeUrl+'" target="_blank" class="link">상세보기</a></div>' + 
            '            </div>' + 
            '            <div class="close"  title="닫기"></div>' + 
            '        </div>' + 
            '		 <input type="hidden" class="data" name="data" value="">'+
            '		 <button type="button" class="memobtn" >메모'+
            '		 </button>' 
            '    	</div>' + 
            '	 </div>';
  
	var ul =document.getElementById('My_List');
	ul.appendChild(li);
	
	li.setAttribute("draggable", "true");
	
	$('.info .data')[num].defaultValue=mymarkers[num].data.data
	
	num+=1;
	
	//생성한 리스트에 클릭시 삭제 이벤트 등록
	$("#My_List .close").click(function(event){
		removeMySchedule(event)	
	});
	
	//생성한 리스트에 마우스 오버시 화면 이동
	$('#My_List li .title').mouseover(function(event){
		var over="over"
		hoverevent(event,over);
	})
	
	$('#My_List li .title').mouseout(function(event){
		var out="out"
		hoverevent(event,out);
	})
	
	//임시, 메모버튼클릭시 메모저장 박스 출력
	$('.memobtn').click(function(event){
		memobox(event);
	})
}

//리스트 삭제 메서드
function removeMySchedule(event){
	var et= event.target;
	var div=et.parentNode.parentNode;
	var li=div.parentNode;
	console.log(li.firstElementChild);
	console.log(li.firstElementChild.defaultValue);
	//console.log(li.firstElementChild.defaultValue);
	//console.log($(li).firstElementChild.defaultValue);
	for(var i = 0 ;i<mymarkers.length;i++){
		if (i==$(li).index()){
			//console.log("삭제완료")
			//라인삭제
			path.splice(i,1);
			polyline.setPath(path);
			
			//마커,오버레이 삭제
			mymarkers[i].mymarker.mymarker.setMap(null);
			mymarkers[i].myoverlay.myoverlay.setMap(null);
			
			mymarkers.splice(i,1);

			//좌측 마이 스케쥴 삭제
			li.remove();
			num-=1;
			break;
		}
	}
}

function hoverevent(event,state){
		var li=event.target.parentElement.parentElement;
		var value=li.firstElementChild.defaultValue;
		for(var i = 0 ;i<mymarkers.length;i++){
			if(value==mymarkers[i].mymarker.id){
				var mapmove=new kakao.maps.LatLng(mymarkers[i].mymarker.mymarker.getPosition().Ma,mymarkers[i].mymarker.mymarker.getPosition().La)
				map.panTo(mapmove);
				if(state=="over"){				
				mymarkers[i].mymarker.mymarker.setMap(map);
				mymarkers[i].myoverlay.myoverlay.setMap(map);
				}else if(state=="out"){
				mymarkers[i].myoverlay.myoverlay.setMap(null);	
					if(checkmode!=1){
					mymarkers[i].mymarker.mymarker.setMap(null);
					}
				}

			break;
			}
			
			/*if (i==$(li).index()){
			var mapmove=new kakao.maps.LatLng(mymarkers[i].mymarker.mymarker.getPosition().Ma,mymarkers[i].mymarker.mymarker.getPosition().La)
			mymarkers[i].mymarker.mymarker.setMap(map);
			map.panTo(mapmove);
			break;
			}*/
		}
}
//=======


function check(){
	if(mymarkers[0]!= undefined){
		//마이핀 ,마이 오버레이 생성
		for(var i=0; i<mymarkers.length;i++){
			mymarkers[i].mymarker.mymarker.setMap(map);
			
				
				
			(function(marker, index) {
				console.log("이벤트 등록");
			
				//리스트 클릭스 오버레이 출력
	            kakao.maps.event.addListener(marker,'click',function(){
					console.log(marker);
					console.log(index);
					addmOverlay(marker,index);	
				});	
        	})(mymarkers[i].mymarker.mymarker, i);
		}
		//라인 생성
		polyline.setMap(map);
		
		checkmode=1;
		
		$('.check').attr("onclick","hide();");
		$('.check').text("마이 핀 숨기기")
	}else{
		alert("핀을 저장해주세요");
	}
}

//마이핀 오버레이 출력
function addmOverlay(marker,index){
	//console.log("애드 오버레이")
	for(var i = 0 ; i<mymarkers.length;i++){
		//console.log("===========시작")
		//console.log(marker.Rc.x+'/'+marker.Rc.y);
		//console.log(mymarkers[i].myoverlay.myoverlay.Rc.x+'/'+mymarkers[i].myoverlay.myoverlay.Rc.y);
		//console.log(marker.getPosition().La+'/'+marker.getPosition().Ma);
		//console.log(mymarkers[i].myoverlay.myoverlay.getPosition().La+'/'+mymarkers[i].myoverlay.myoverlay.getPosition().Ma);
		//console.log("===========")
		if(mymarkers[index].mymarker.id==mymarkers[i].myoverlay.id){
			//console.log("일치")
			mymarkers[i].myoverlay.myoverlay.setMap(map);
			$(".info .r").click(function(event){
			closeMyOverlay(event)	
			});
			break;		
		}
	}
}

//오버레이 닫기 
function closeMyOverlay(event) {
	var et= event.target;
	var value=et.nextElementSibling.value;
	for(var i=0; i<mymarkers.length;i++){
		if(mymarkers[i].id==value){
			mymarkers[i].myoverlay.myoverlay.setMap(null);
		}
	}    
}

//마이핀 숨기기
function hide(){
	for(var i=0; i<mymarkers.length;i++){
		
		
		/*(function(marker, index) {
			console.log("이벤트 삭제");
			//리스트 클릭스 오버레이 출력
            kakao.maps.event.removeListener(marker,'click',function(){
				console.log(marker);
				console.log(index);
				addmOverlay(marker,index);	
			});	
        })(mymarkers[i].mymarker.mymarker, i);*/
		
		mymarkers[i].mymarker.mymarker.setMap(null);
		mymarkers[i].myoverlay.myoverlay.setMap(null);
		polyline.setMap(null);
		
	}
	checkmode=0;
	
	$('.check').attr("onclick","check();");
		$('.check').text("마이 핀 보이기")
	
	
}

//==================선 그리기


//마이 핀 라인 생성
function makeline(){
	polyline.setMap(null);
	if( polyline.getPath()[0]==undefined || polyline.getPath()[0].La== 0 && polyline.getPath()[0].Ma==0 ){
		for(var i=0;i<mymarkers.length;i++){
			var ma= mymarkers[i].mymarker.mymarker.getPosition().Ma,
				la=mymarkers[i].mymarker.mymarker.getPosition().La
			//넘겨 받은 좌표 값 설정
			var coord = new kakao.maps.LatLng(ma, la);
			
			path.push(coord);
		}
		polyline.setPath(path);
		//polyline.setMap(map);
	}else{
		var length=path.length;
		
		for(var i=0;i<mymarkers.length;i++){
			if(i<length){
				continue
			}else{
			var ma= mymarkers[i].mymarker.mymarker.getPosition().Ma,
				la=mymarkers[i].mymarker.mymarker.getPosition().La
				
			var coord = new kakao.maps.LatLng(ma, la);
			
			path.push(coord);
			}	
		}
		polyline.setPath(path);
	}
}

//===========임시 메모
//메모 내용 담는 배열
var memos=[];

//임시
var index;
//메모 박스 출력
function memobox(event){
	
	//none 클래스 삭제
	var e = event.target.parentElement.parentElement;
	//console.log(e)
	$('#memobox').removeClass('none');
	
	 index=$(e).index()
	 
	//console.log(index);
	//console.log($(e).index());
	
	//title 부여
	var text=$('.info .title')[index].innerText;
	$('#memobox .title').text(text);
	
	$('#memobox textarea')[0].value=mymarkers[index].data.memo;
}

//메모 박스 숨기기
$('#memobox .close').click(function(){
	$('#memobox').addClass('none');
})

//메모 저장 수정필요
	$('.savememobtn').click(function(){
		//메모 내용 있을시 저장 누르면 창 닫기
	
		//초기화
		$('.info .data')[index].defaultValue="";
		//console.log($('.info .data')[index]);
		//console.log($('.info .data')[index].defaultValu);
		//리스트 input value값 불러오기
		var value=mymarkers[index].data.data;

		//텍스트 에어이라 입력 값
		var memo=$('#memobox textarea')[0].value
		
		mymarkers[index].data.memo=memo;
		
		//input value에 메모내용 값 추가
		$('.info .data')[index].defaultValue = value+memo;
		//console.log($('.info .data')[index].defaultValue)
		
		$('#memobox').addClass('none');
	})
//========드래그 앤 드랍
const list = document.querySelector('#My_List');
let currentItemIndex = null;
let currentItem = null;

list.addEventListener('dragstart', (e) => {
  currentItem = e.target;
 //console.log("시작");
  const listArr = [...currentItem.parentElement.children];
  currentItemIndex = listArr.indexOf(currentItem);
});

list.addEventListener('dragover', (e) => {
  e.preventDefault();
});

list.addEventListener('drop', (e) => {
  e.preventDefault();
//console.log("끝");
  var currentDropItem=e.target;
  
  for(currentDropItem;currentDropItem.localName!="li";currentDropItem=currentDropItem.parentElement ){
	}

  const listArr = [...currentItem.parentElement.children];

  const dropItemIndex = listArr.indexOf(currentDropItem);

  if (currentItemIndex < dropItemIndex) {
   currentDropItem.after(currentItem);
  } else {
    currentDropItem.before(currentItem);
  }
  
  //내가 저장한 장소 배열 재설정
   if(currentItemIndex==dropItemIndex){
	//console.log("제자리 실행")
	}else{
  arraychange(currentItemIndex,dropItemIndex);	
	}
});

//내가 저장한 장소 배열 재설정
function arraychange(currentItemIndex,dropItemIndex){
	var num= path.length
	var temp=[];
		
	//console.log(mymarkers);
	for(var i=0;i<num;i++){
	
		var my = path.shift();
		//console.log(my)
		temp.push(my)
	}
		//console.log(temp)
		//console.log(mymarkers)
	if (currentItemIndex < dropItemIndex){
		for(var j=0; j<num;j++){
			if(j==currentItemIndex){
				continue;
			}else if(j==dropItemIndex){
				path.push(temp[j]);
				path.push(temp[currentItemIndex]);
			}else{
				path.push(temp[j]);
			}
		}
	}else{
		for(var j=0; j<num;j++){
			if(j==currentItemIndex){
				continue;
			}else if(j==dropItemIndex){
				path.push(temp[currentItemIndex]);
				path.push(temp[j]);
			}else{
				path.push(temp[j]);
			}
		}
	}
	//console.log(mymarkers);
	makeline(path);
	polyline.setMap(map);
}
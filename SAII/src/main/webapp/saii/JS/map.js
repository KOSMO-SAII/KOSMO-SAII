
// 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
var placeOverlay = new kakao.maps.CustomOverlay({
	zIndex: 1
}), contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
	markers = [], // 마커를 담을 배열입니다
	currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		level: 5
		// 지도의 확대 레벨
	};
// 지도를 생성합니다


    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(map);

// 지도에 idle 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', searchPlaces);

// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
contentNode.className = 'placeinfo_wrap';

// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

// 커스텀 오버레이 컨텐츠를 설정합니다
placeOverlay.setContent(contentNode);

// 각 카테고리에 클릭 이벤트를 등록합니다
addCategoryClickEvent();

// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
function addEventHandle(target, type, callback) {
	if (target.addEventListener) {
		target.addEventListener(type, callback);
	} else {
		target.attachEvent('on' + type, callback);
	}
}

// 카테고리 검색을 요청하는 함수입니다
function searchPlaces() {
	if (!currCategory) {
		return;
	}

	// 커스텀 오버레이를 숨깁니다 
	placeOverlay.setMap(null);

	// 지도에 표시되고 있는 마커를 제거합니다
	removeMarker();

	ps.categorySearch(currCategory, placesSearchCB, {
		useMapBounds: true
	});
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	if (status === kakao.maps.services.Status.OK) {

		// 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
		displayPlaces(data);
	} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
		// 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

	} else if (status === kakao.maps.services.Status.ERROR) {
		// 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

	}
}

// 지도에 마커를 표출하는 함수입니다
function displayPlaces(places) {
	
	// 몇번째 카테고리가 선택되어 있는지 얻어옵니다
	// 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
	var order = document.getElementById(currCategory).getAttribute(
		'data-order');

	for (var i = 0; i < places.length; i++) {

		// 마커를 생성하고 지도에 표시합니다
		var marker = addMarker(new kakao.maps.LatLng(places[i].y,
			places[i].x), order);

		// 마커와 검색결과 항목을 클릭 했을 때
		// 장소정보를 표출하도록 클릭 이벤트를 등록합니다
		(function(marker, place, index) {
			myPlaces.push(new myPlace(place.category, place.address_name, place.road_address_name, place.id, place.phone, place.place_name, place.place_url, place.x, place.y))
			kakao.maps.event.addListener(marker, 'click', function() {
				displayPlaceInfo(place, index);
			});
		})(marker, places[i], i);
	}
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, order) {
	var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
		imgOptions = {
			spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
			spriteOrigin: new kakao.maps.Point(46, (order * 36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset: new kakao.maps.Point(11, 28)
			// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
			imgOptions), marker = new kakao.maps.Marker({
				position: position, // 마커의 위치
				image: markerImage
			});

	marker.setMap(map); // 지도 위에 마커를 표출합니다
	markers.push(marker); // 배열에 생성된 마커를 추가합니다

	return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
	myPlaces = [];
}

var myPlaces = [];
var chosenPlace = [];
// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
function displayPlaceInfo(place, index) {
	var content = '<div class="placeinfo">'
		+ '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">'
		+ place.place_name + '</a>';

	if (place.road_address_name) {
		content += '    <span title="' + place.road_address_name + '">'
			+ place.road_address_name
			+ '</span>'
			+ '  <span class="jibun" title="' + place.address_name + '">(지번 : '
			+ place.address_name + ')</span>';
	} else {
		content += '    <span title="' + place.address_name + '">'
			+ place.address_name + '</span>';
	}
	content += '    <span class="tel">' + place.phone;
	if (index != 0) 
		content	+= '&nbsp;&nbsp;<button onclick="pin(' + index + ')">핀 꽂기</button></span>' + '</div>';
	content	+= '<div class="after"></div>';
	contentNode.innerHTML = content;
	placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
	placeOverlay.setMap(map);
}

// 각 카테고리에 클릭 이벤트를 등록합니다
function addCategoryClickEvent() {
	var category = document.getElementById('category'), children = category.children;

	for (var i = 0; i < children.length; i++) {
		children[i].onclick = onClickCategory;
	}
}

// 카테고리를 클릭했을 때 호출되는 함수입니다
function onClickCategory() {
	var id = this.id, className = this.className;

	placeOverlay.setMap(null);

	if (className === 'on') {
		currCategory = '';
		changeCategoryClass();
		removeMarker();
	} else {
		currCategory = id;
		changeCategoryClass(this);
		searchPlaces();
	}
}

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
function changeCategoryClass(el) {
	var category = document.getElementById('category'), children = category.children, i;

	for (i = 0; i < children.length; i++) {
		children[i].className = '';
	}

	if (el) {
		el.className = 'on';
	}
}
var chosenPos = [];
var marker = new kakao.maps.Marker;
var pathLine = new kakao.maps.Polyline({
	map: map, // 선을 표시할 지도입니다 
	path: [], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
	strokeWeight: 3, // 선의 두께입니다 
	strokeColor: '#db4040', // 선의 색깔입니다
	strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	strokeStyle: 'solid' // 선의 스타일입니다
});
var isExist = false;

function pin(index) {
	var myPlace = myPlaces[index]
	placeOverlay.setMap(null);
	currCategory = '';
	changeCategoryClass();
	removeMarker()
	chosenPos.push(new kakao.maps.LatLng(myPlace.y, myPlace.x));
	for (var i = 0; i < chosenPlace.length; i++) {
		if (chosenPlace[i].id == myPlace.id) {
			console.log("already exist err");
			isExist = true;
			break;
		}
	}
	if (!isExist) {		
		addMyMarker(chosenPos[chosenPos.length-1]);
		chosenPlace.push(myPlace);
		pathLine.setPath(chosenPos);
		if (isEmpty)
			isEmpty = false;
		makeInfo();
	}
}

function addMyMarker(position) {
	chosenMarker = new kakao.maps.Marker({
		position: position
	});
	chosenMarkers.push(chosenMarker)
	chosenMarker.setMap(map);		
	kakao.maps.event.addListener(chosenMarker, 'click', function(){
		moveCenter(chosenMarkers.length-1);
	});
}

var isHide = false, isEmpty = true;
var markerBtn = document.getElementById("markerBtn");

function showMarker() {
	if (!isEmpty) {
		if (isHide) {
			for (var i = 0; i < chosenPos.length; i++) {
				chosenMarker = new kakao.maps.Marker({
					position: chosenPos[i]
				});
				chosenMarkers.push(chosenMarker);
				chosenMarker.setMap(map);
			}
			pathLine.setPath(chosenPos);
			isHide = false;
			markerBtn.innerHTML = "마커 숨기기";
		} else {
			for (var i = 0; i < chosenPos.length; i++) {
				chosenMarkers[i].setMap(null);
			}
			chosenMarkers = [];
			pathLine.setPath(null);
			isHide = true;
			markerBtn.innerHTML = "마커 보이기";
		}
	}
}

function reset() {
	if (!isHide){
		showMarker();
	}
	if (chosenPos) {
		chosenPos = [];
		chosenPlace = [];
		info_list.innerHTML = "";
		isEmpty = true;
	}
}

function test(place){
	console.log(place);
}

class myPlace {
	constructor(category, address_name,road_address_name,id,phone,place_name,place_url,x,y){
		this.category = category;
		this.address_name = address_name;
		this.road_address_name = road_address_name;
		this.id = id;
		this.phone = phone;
		this.place_name = place_name;
		this.place_url = place_url;
		this.x = x;
		this.y = y;
	}
	
	isEqual(id){
		if(this.id == id)
			return true;
		else
			return false;
	}
}

var info_list = document.getElementById("info_list")
info_list.innerHTML = "";

function makeInfo(){
	var index = chosenPlace.length-1;
	var endPoint = chosenPlace[index];
	var place = endPoint;
	var content = "";
	if(index == 0){
		content += "<li>출발 지점</li>";				
	}
	content += '<div class="cards"> '
		//+ '   		<div class="tab">'
		//+ '				<label for="info_tab">정보 보기</label> <label for="memo_tab">메모</label>'
		//+ '			</div>'
		+ '	  		<div class="content">'
		+ ' 			<div class="info"> <button class="moveCenter" onclick="moveCenter(' + index  + ')">위치 보기</button>'		
	if (place.road_address_name) {
	content += '    		<span title="' + place.road_address_name + '">' + place.road_address_name + '</span>'
			+ '  			<span class="jibun" title="' + place.address_name + '">(지번 : '	+ place.address_name + ')</span>';
	} else {
	content += '   			<span title="' + place.address_name + '">' + place.address_name + '</span>';
	}
	content += '   			<span class="tel">' + place.phone + '</span>'
	if(index != 0){
		var startPoint = chosenPlace[index-1];
		var link = "'https://map.kakao.com/link/to/" + endPoint.address_name + "," + endPoint.y + "," + endPoint.x + 
				"/from/" + startPoint.address_name + "," + startPoint.y + "," + startPoint.x + "'"; 
		content += '		&nbsp;<li><button onclick="window.open(' + link + ')">경로 보기</button></li><div class="after">';
	}
	content += '		<li><button onclick="window.open'+"('" + place.place_url + "')"+'">상세 보기</button></li>'
			+  '		</div>';
	// 다른탭
	content += '		<div class="memo">'
			+ '				<p>메모 작성</p>'
			+ '				<textarea cols="20" rows="3"></textarea>'
			+ '			</div>'
			+ '		</div>';			
			+ '</div>';
			
			
	content += '<form>'
			+'		<input type="hidden" name="category" value="' + place.category + '"/>'
			+'		<input type="hidden" name="address_id" value="' + place.address_id + '"/>'
			+'		<input type="hidden" name="address_name" value="' + place.address_name + '"/>'
			+'		<input type="hidden" name="road_address_name" value="' + place.road_address_name + '"/>'
			+'		<input type="hidden" name="phonenumber" value="' + place.phonenumber + '"/>'
			+'		<input type="hidden" name="place_name" value="' + place.place_name + '"/>'
			+'		<input type="hidden" name="place_url" value="' + place.place_url + '"/>'
			+'		<input type="hidden" name="x" value="' + place.x + '"/>'
			+'		<input type="hidden" name="y" value="' + place.y + '"/>'
			+'		<input type="hidden" name="memo" value="' + place.memo + '"/>'	
			+'		<input type="submit" value="제출하기"/>'
			+' 		<textarea>'
			+'	</form>'	
					
	info_list.innerHTML += content;
							
}

function moveCenter(index){
	var mp = chosenPlace[index];
	var pos = new kakao.maps.LatLng(mp.y, mp.x);
	map.setCenter(pos);
	displayPlaceInfo(mp,0);
	
}


function saveData(order){
	var place = chosenPlace[order];
	var data = order + "/"  + place.category + "/" 
			+ place.address_id + "/" 
			+ place.address_name + "/" 
			+ place.road_address_name + "/" 
			+ place.phonenumber + "/" 
			+ place.place_name + "/"
			+ place.place_url + "/"
			+ place.x + "/"
			+ place.y + "/"
			+ place.memo + "/"
	return data;
}



var chosenMarker;
var chosenMarkers = [];

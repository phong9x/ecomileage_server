<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<%@ page import = "org.ecomileage.web.common.utils.ConstantUtils" %>
<style>
	#map {
	  height: 700px;
	  width: 100%;
	  z-index: 9999;
	  
	}
	html, body {
	  height: 100%;
	  margin: 0;
	  padding: 0;
	}
</style>
<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar"></div>

              <form class="form_sample_3">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">신고 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신고자 ID :</div>
                      <div class="col-md-3 line-30">${item.user.username }</div>
                      <div class="col-md-2 text-right line-30">신고자 연락처 :</div>
                      <div class="col-md-3 line-30">${item.category.name }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">카테고리 :</div>
                      <div class="col-md-10 line-30">시설명/건물명 수정</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신고 일시 :</div>
                      <div class="col-md-10 line-30"><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신고 내용 :</div>
                      <div class="col-md-10 line-30">${item.content }</div>
                    </div>
                    <c:if test="${item.latitude == null ||  item.longtitude == null }">
                    	<div class="full mrb-10">
	                      <div class="col-md-2 text-right">위치정보 :</div>
	                      <div class="col-md-10">없음 </div>
	                    </div>
                    </c:if>
                    <c:if test="${item.latitude != null &&  item.longtitude != null }">
                    	<div class="full mrb-10">
	                      <div style="line-height: 250px;" class="col-md-2 text-right">위치정보 :</div>
	                      <div class="col-md-10">
	                      		<div id="map"></div>
	                      </div>
	                    </div>
                    </c:if>
                      		
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">첨부파일 :</div>
                      <div class="col-md-4"><img src="${item.profileUrl }" alt="index"/></div>
                    </div>
                    <div class="full mrb-30">
                      <div class="col-md-2 text-right line-30">상태 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-3">
                          <select name="status" id="statusReport" data-id="${item.id }" title="선택" class="form-control selectpickermember text-left">
                            <option data-hidden="true"></option>
                            <option value="1" ${item.status == 1? 'selected':'' }>검토중</option>
                      		<option value="2" ${item.status == 2? 'selected':'' }>수정완료</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="full text-center">
                      <button type="button" class="btn green pad-8-50 link-back" >목록</button>
                    </div>
                  </div>
                </div>
              </form>
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=<%= ConstantUtils.getConfig("googleMapKey") %>&callback=initMap"></script>
<div id="popupConfirmChangeStatus" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">신고 상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>신고 상태를 변경하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
          	<button type="button" data-id="${item.id}" class="btn red btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" data-status="${item.status}" class="btn btn-default btn-cancel" >취소</button>
          </div>
        </div>
      </div>
</div>

<script type="text/javascript">
   	// This example creates an interactive map which constructs a polyline based on
  	// user clicks. Note that the polyline only appears once its path property
  	// contains two LatLng coordinates.
  	function initMap() {
  		var jsonLat = '${jsonLat}';
  		var jsonLong = '${jsonLong}';
  		/******************************************************
        *
        * Create map
        *
        ******************************************************/
		function create(center) {
            map = new google.maps.Map(document.getElementById('map'), {
                center  : center,
                zoom    : 17,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });
        }
  		
		/******************************************************
        *
        * Initialize map with center
        *
        ******************************************************/
		function init() {
            create(new google.maps.LatLng(jsonLat, jsonLong));
        }
		init();
  		
		var contentString = 'fgfdg';


  		
    	var geocoder = new google.maps.Geocoder;
        var infowindow = new google.maps.InfoWindow;
        var flightPlanCoordinatesMark = new google.maps.LatLng(jsonLat,jsonLong);
        /******************************************************
        *
        * Create marker
        *
        ******************************************************/
        var markerLat = new google.maps.Marker({
            position: flightPlanCoordinatesMark
        });
        markerLat.setMap(map);
         
        markerLat.addListener('click', function() {
        	geocodeLatLng(geocoder, map, infowindow);
        });
        
        var contentString = '';
        
        function geocodeLatLng(geocoder, map, infowindow) {
            var latlng = {lat: parseFloat(jsonLat), lng: parseFloat(jsonLong)};
            geocoder.geocode({'location': latlng}, function(results, status) {
              if (status === 'OK') {
                if (results[0]) {
                  map.setZoom(11);
                  var marker = new google.maps.Marker({
                    position: latlng,
                    map: map
                  });
                  var accuracy = '';
                  if(results[0].geometry.location_type == "ROOFTOP"){
                  	accuracy = 9;
                  }else if(results[0].geometry.location_type == "RANGE_INTERPOLATED"){
                  	accuracy = 8;
                  }else if(results[0].geometry.location_type == "GEOMETRIC_CENTER"){
                  	accuracy = 7;
                  }else if(results[0].geometry.location_type == "APPROXIMATE"){
                  	accuracy = 6;
                  }else{
                  	accuracy = 5;
                  }
                  
                  var countryCode = '';
                  for(var i=0; i < results[0].address_components.length; i++)
                  {
                      var component = results[0].address_components[i];
                      if(component.types[0] == "country") {
                          countryCode = component.short_name;
                      }
                  }
                  
                contentString = 'Latitude: ' + jsonLat + '<br>'+
				      	    	'Longitude: ' + jsonLong+'<br>'+
				    	    	'StatusCode: 200<br>'+
				    	    	'StatusCode: geocode<br>'+
				    	    	'Address: ' +results[0].formatted_address+'<br>'+
				    	    	'Accuracy: ' +accuracy+'<br>'+
				    	    	'Country code: ' +countryCode+'<br>';
                  
                  infowindow.setContent(contentString);
                  infowindow.open(map, marker);
                } else {
                  window.alert('No results found');
                }
              } else {
                window.alert('Geocoder failed due to: ' + status);
              }
          });
		}
   	}
   </script>
   
  	<script type="text/javascript">
    $('.link-back').click(function(){
     	window.location = "/auth/report/list";
    })
     	
    $('#statusReport').on('change', function() {
		var status = $(this).val();
		$('#popupConfirmChangeStatus').find('.btn-confirm').attr('status',status);
		$('#popupConfirmChangeStatus').modal('show');
	});
			  
	$('.btn-cancel').on('click', function() {
		var current_status = $(this).attr("data-status");
		$('select[name=status]').val(current_status);
		$('select[name=status]').selectpicker('refresh');
	});
			
	$('.btn-confirm').on('click', function() {
		var id = $(this).attr("data-id");
		var status = $(this).attr('status');
		$.ajax({
			url         : '/auth/report/changeStatus',
			method      : 'post',
			data:{
				"id":id,
				"status" : status
			},
			success:function(rs) {
				if(rs.status == 200){
					window .location.href = "/auth/report/edit?id=" + id;
				} else {
					alert("Can not change status !!!");
				}
			}
		});
	});
</script>
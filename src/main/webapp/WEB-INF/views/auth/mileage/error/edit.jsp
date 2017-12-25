<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<%@ page import = "org.ecomileage.web.common.utils.ConstantUtils" %>
<style>
	#map {
	  height: 700px;
	  width: 100%;
	  z-index: 9999;
	  
	}
</style>
<div class="page-content-wrapper">
  <div class="page-content">
    <div class="container">
      <div class="main-body-content member-page">
        <div class="page-bar">
          <ul id="page-breadcrumb" class="page-breadcrumb"></ul>
        </div>

        <form id="form_error_mileage" method="post">
          <div class="member-title-table">
            <div class="title-page full border-bottom mrb-30">
              <h3 class="bold pull-left">오류 데이터 상세</h3>
            </div>
            <div class="full border-full pad-20-30 mrb-40">
              <div class="full mrb-10">
                <div class="full"><div id="map"></div></div>
                <div class="full mrb-10" style="border: 1px solid #dddddd; margin-top: 24px;">
               	<div class="col-md-2 text-right line-30">상태 :</div>
               	 <div class="col-md-3 line-30">재신청</div>
               	 <div class="col-md-2 text-right line-30">사유 :</div>
               	 <div class="col-md-3 line-30"></div>
              	</div>
                <div class="full" style="border: 1px solid #dddddd;">
                 	<input type="hidden" name="id" value="${item.id}"> 
                  	<textarea name="memo" id="memo" rows="6" placeholder="메모란" class="rw-ckeditor form-control">${item.memo }</textarea>
                </div>
              </div>
              <div class="full text-center">
                <button type="button" class="btn red pad-8-50 error-link-back">목록</button>
                <button type="button" class="btn green pad-8-50 error-button-confirm">저장</button>
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
<script type="text/javascript" src="http://www.jqchart.com/jquery/chart/Scripts/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript" src="http://www.jqchart.com/jquery/chart/Scripts/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="http://www.jqchart.com/jquery/chart/Scripts/jquery.mousewheel.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=<%= ConstantUtils.getConfig("googleMapKey") %>&callback=initMap"></script>
<script>
	// This example creates an interactive map which constructs a polyline based on
	// user clicks. Note that the polyline only appears once its path property
	// contains two LatLng coordinates.
	function initMap() {
		var data = '${data}';
		var jsonLat1 = '${jsonLat1}';
		var jsonLong1 = '${jsonLong1}';
		var jsonLat2 = '${jsonLat2}';
		var jsonLong2 = '${jsonLong2}';
		var flightPlanCoordinatesLatMark;
		
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
            create(new google.maps.LatLng(jsonLat1, jsonLong1));
            flightPlanCoordinatesLatMark = new google.maps.LatLng(jsonLat1,jsonLong1);
        }
		init();
		
    	var flightPlanCoordinates = JSON.parse(data);
        var flightPlanCoordinatesLongMark = new google.maps.LatLng(jsonLat2,jsonLong2);
        
        /******************************************************
        *
        * Draw map polyline
        *
        ******************************************************/
        var flightPath = new google.maps.Polyline({
          path: flightPlanCoordinates,
          geodesic: true,
          strokeColor: '#0000FF',
          strokeOpacity: 1.0,
          strokeWeight: 2
        });
        flightPath.setMap(map); 
        
        /******************************************************
        *
        * Create lat marker
        *
        ******************************************************/
        var markerLat = new google.maps.Marker({
            position: flightPlanCoordinatesLatMark
        });
        markerLat.setMap(map);
        
        /******************************************************
        *
        * Create long marker
        *
        ******************************************************/
        var markerLong = new google.maps.Marker({
            position: flightPlanCoordinatesLongMark
        });
        markerLong.setMap(map);
      }
	
	
	$('.error-link-back').click(function(){
  		window.location = '/auth/mileage/error/list';
  	});
	$('.error-button-confirm').click(function(){
		var memo = $('#memo').val();
		console.log(memo);
		if (memo.leng == 0) {
			alert("Please input information.")
		} else {
			$('#form_error_mileage').submit();
		}
  		
  	});
</script>



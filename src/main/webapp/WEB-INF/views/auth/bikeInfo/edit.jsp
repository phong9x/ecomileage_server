<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<style>
	#map {
	  height: 500px;
	  width: 100%;
	  z-index: 9999;
	}
    .datepicker.datepicker-dropdown{
       z-index: 99999;
    }
</style>
      <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar"></div>
              <form method="post" action="/auth/bikeInfo/edit?id=${item.id }" enctype="multipart/form-data">
              	<input type="hidden" name="id" value="${item.id }" class="form-control"/>
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">자전거 시설 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full border-bottom pdb-10 mrb-20">
                      <div class="col-md-2 line-30"><span class="font-18">위치 정보</span></div>
                      <div class="col-md-1 pull-right">
                        <button type="submit" name="delete" value="${item.id }" class="btn red pad-8-50 trams-need-confirm" data-toggle="modal" data-trams-confirm-popup="#popup-delete-bike-info">삭제</button>
                      </div>
                    </div>
                    <div class="full mrb-20">
                      <div class="col-md-5">
                        <div class="row mrb-20">
                          <div class="col-md-2 text-right line-30">lat :</div>
                          <div class="col-md-10 ">
                            <input type="text" name="latitude" value="${item.latitude }" readonly class="form-control"/>
                          </div>
                        </div>
                        <div class="row mrb-20">
                          <div class="col-md-2 text-right line-30">lng :</div>
                          <div class="col-md-10">
                            <input type="text" name="longitude" value="${item.longitude }" readonly class="form-control"/>
                          </div>
                        </div>
                        <div class="row mrb-20">
                          <div class="col-md-2 text-right line-30">시/도 :</div>
                          <div class="col-md-10">
		                      <select name="city" id="city" title="시/도" class="form-control selectpickermember text-left">
		                        <option value="">시/도</option>
		                        <c:forEach items="${citys}" var="city">
		                        	<option data-cityid="${city.id }"  value="${city.name}">${city.name}</option>
		                        </c:forEach>
		                      </select>
                          </div>
                        </div>
                        <div class="row mrb-20">
                          <div class="col-md-2 text-right line-30">시/군/구 :</div>
                          <div class="col-md-10">
		                      <select name="district" id="district" title="시/군/구" class="form-control selectpickermember text-left">
		                        <option value="">시/군/구</option>
		                      	<c:forEach items="${districts}" var="dis">
		                      		<option data-cityid="${dis.id }" value="${dis.name}">${dis.name}</option>
		                        </c:forEach>
		                      </select>
                          </div>
                        </div>
                        <div class="row mrb-50">
                          <div class="col-md-2 text-right line-30">zipcode :</div>
                          <div class="col-md-10">
                            <input type="text" name="postCode" value="${item.postCode }" readonly class="form-control"/>
                          </div>
                        </div>
                        <div class="row mrb-10">
                          <div class="col-md-2 color-333 font-16 text-right">시설 정보 :</div>
                        </div>
                        <div class="row mrb-10">
                          <div class="col-md-2 text-right line-30">시설 구분 :</div>
                          <div class="col-md-6">
                            <select name="type" title="선택..." class="form-control selectpickermember text-left">
                              <option value="2">자전거 보관소</option>
                              <option value="1">자전거 대여소</option>
                              <option value="3">자전거 수리/판매점</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-7">
                      <div id="map"></div>
                      </div>
                    </div>
                    
                    <div class="full mrb-20 mrt-30 div-data" id="div-type-2" >
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">주소 :</div>
                        <div class="col-md-10">
                          <input type="text" name="address" value="${item.address }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">역명 :</div>
                        <div class="col-md-4">
                          <input type="text" name="name" value="${item.name }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">보관대수 / 실제 주차대수 :</div>
                        <div class="col-md-1">
                          <input type="number" name="totalBikeParking" value="${item.totalBikeParking }" class="form-control"/>
                        </div>
                        <div class="text-right line-30" style="float:left">/</div>
                        <div class="col-md-1">
                          <input type="number" name="totalBikeParkingFact" value="${item.totalBikeParkingFact }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">출구번호 :</div>
                        <div class="col-md-4">
                          <input type="number" name="totalDoors" value="${item.totalDoors }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">정보출처 :</div>
                        <div class="col-md-4">
                          <input type="text" name="sourceInformation" value="${item.sourceInformation }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">공기주입 :</div>
                        <div class="col-md-4 pdt-5">
                          <div data-error-container="#form_2_membership_error" class="radio-list">
                            <label class="pull-left mrr-30">
                              <input type="radio" name="pumpBike" ${item.pumpBike == 0 ? 'checked': '' } value="0"/>없음
                            </label>
                            <label class="pull-left">
                              <input type="radio" name="pumpBike" ${item.pumpBike == 1 ? 'checked': '' } value="1"/>있음
                            </label>
                          </div>
                        </div>
                        <div class="col-md-2 text-right line-30">정보제공일 :</div>
                        <div class="col-md-4">
                          <fmt:formatDate value="${item.provideInformationDate }" pattern="yyyy-MM-dd" var="provideInformationDate"/>
                          <input name="provideDate" type="text" data-date-format="yyyy-mm-dd" value="${provideInformationDate }" class="form-control date-picker"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">사진 첨부 :</div>
                        <div class="col-md-4">
                          <div class="inputfile cusomer-file">
                            <div class="tr-file-upload image-upload">
                              <label class="btn green btn-select-file pull-right">찾아보기
                                <input type="file" name="file" class="hidden input-file"/>
                              </label>
                              <div class="input-icon input-large input-inline"><span class="btn-delete"><i class="fa fa-times"></i></span><span class="show-file-info">${item.imageName}</span></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="full mrb-20 mrt-30 div-data" id="div-type-1" style="display: none">
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">주소 :</div>
                        <div class="col-md-10">
                          <input type="text" name="address" value="${item.address }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">명칭 :</div>
                        <div class="col-md-4">
                          <input type="text" name="name" value="${item.name }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">이용요금 :</div>
                        <div class="col-md-4">
                          <input type="text" name="feeRental" value="${item.feeRental }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">보관대수 :</div>
                        <div class="col-md-4">
                          <input type="text" name="totalBikeParking" value="${item.totalBikeParking }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">전화번호 :</div>
                        <div class="col-md-4">
                          <input type="text" name="phone" value="${item.phone }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">홈페이지 :</div>
                        <div class="col-md-4">
                          <input type="text" name="homepage" value="${item.homepage }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">정보출처 :</div>
                        <div class="col-md-4">
                          <input type="text" name="sourceInformation" value="${item.sourceInformation }" class="form-control"/>
                        </div>
                      </div>
                       <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">정보 제공일 :</div>
                        <div class="col-md-4">
                          <fmt:formatDate value="${item.provideInformationDate }" pattern="yyyy-MM-dd" var="provideInformationDate"/>
                          <input name="provideDate" type="text" data-date-format="yyyy-mm-dd" value="${provideInformationDate }" class="form-control date-picker"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">사진 첨부 :</div>
                        <div class="col-md-4">
                          <div class="inputfile cusomer-file">
                            <div class="tr-file-upload image-upload">
                              <label class="btn green btn-select-file pull-right">찾아보기
                                <input type="file" name="file" class="hidden input-file"/>
                              </label>
                              <div class="input-icon input-large input-inline"><span class="btn-delete"><i class="fa fa-times"></i></span><span class="show-file-info">${item.imageName}</span></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="full mrb-20 mrt-30 div-data" id="div-type-3" style="display: none">
                     <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">주소 :</div>
                        <div class="col-md-10">
                          <input type="text" name="address" value="${item.address }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">상호 :</div>
                        <div class="col-md-4">
                          <input type="text" name="name" value="${item.name }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">영업시간 :</div>
                        <div class="col-md-1">
                          <div class="input-group">
                              <input type="text" name="openTime" alue="${item.openTime }" class="form-control timepicker timepicker-24">
                              <span class="input-group-btn">
                                  <button class="btn default" type="button">
                                      <i class="fa fa-clock-o"></i>
                                  </button>
                              </span>
                          </div>
                        </div>
                        <div class="text-right line-30" style="float:left">~</div>
                        <div class="col-md-1">
                          <div class="input-group">
                              <input type="text" name="closeTime" value="${item.closeTime }" class="form-control timepicker timepicker-24">
                              <span class="input-group-btn">
                                  <button class="btn default" type="button">
                                      <i class="fa fa-clock-o"></i>
                                  </button>
                              </span>
                          </div>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">전화번호 :</div>
                        <div class="col-md-4">
                          <input type="number" name="phone" value="${item.phone }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">정보출처 :</div>
                        <div class="col-md-4">
                          <input type="text" name="sourceInformation" value="${item.sourceInformation }" class="form-control"/>
                        </div>
                      </div>
                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">홈페이지 :</div>
                        <div class="col-md-4">
                          <input type="text" name="homepage" value="${item.homepage }" class="form-control"/>
                        </div>
                        <div class="col-md-2 text-right line-30">정보 제공일 :</div>
                        <div class="col-md-4">
                          <fmt:formatDate value="${item.provideInformationDate }" pattern="yyyy-MM-dd" var="provideInformationDate"/>
                          <input name="provideDate" type="text" data-date-format="yyyy-mm-dd" value="${provideInformationDate }" class="form-control date-picker"/>
                        </div>
                      </div>

                      <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">사진 첨부 :</div>
                        <div class="col-md-4">
                          <div class="inputfile cusomer-file">
                            <div class="tr-file-upload image-upload">
                              <label class="btn green btn-select-file pull-right">찾아보기
                                <input type="file" name="file" class="hidden input-file"/>
                              </label>
                              <div class="input-icon input-large input-inline"><span class="btn-delete"><i class="fa fa-times"></i></span><span class="show-file-info">${item.imageName}</span></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="full text-center">
                      <a href="/auth/bikeInfo/list" class="btn grey pad-8-50">목록</a>
                  	  <button type="submit" class="btn green pad-8-50 " >수정</button>
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
      
      
	    <div id="popup-delete-bike-info" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">삭제 안내</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          
	          <div class="modal-body">
	            <p>시설을 삭제하면 복구되지 않습니다. 삭제하시겠습니까?</p>
	          </div>
	          
	          <div class="modal-footer" style="text-align: center">
	          	<button type="button" onclick="tramsNC.confirm()" class="btn red" class="btn green">확인</button>
	          	<button type="button" data-dismiss="modal" class="btn btn-default">취소</button>
	          </div>
	        </div>
	      </div>
	    </div> 
      
  <script type="text/javascript">
  	$(document).ready(function(){
  		$('select[name=type]').val(${item.type});
		$('select[name=type]').selectpicker('refresh');
		
		$('select[name=city]').val('${item.city}');
		$('select[name=city]').selectpicker('refresh');
		
		$('select[name=district]').val('${item.district}');
		$('select[name=district]').selectpicker('refresh');
		
		
		var id =$('select[name=type]').val();
		console.log("id:"+id);
		for(var i = 1; i<=3;i++){
			if(id == i){
	  			$('#div-type-'+i).show();
	  			$("#div-type-"+i+" :input").attr("disabled", false);
	  			$('#div-type-'+i).find('input, textarea, button, select').removeAttr('disabled');
			}else{
	  			$('#div-type-'+i).hide();
	  			$("#div-type-"+i+" :input").attr("disabled", true);
	  			$('#div-type-'+i).find('input, textarea, button, select').attr('disabled','disabled');
			}
		}
		
  		$('select[name=type]').change(function(){
  			var id =$(this).val();
  			for(var i = 1; i<=3;i++){
  				if(id == i){
  		  			$('#div-type-'+i).show();
  		  			$("#div-type-"+i+" :input").attr("disabled", false);
  		  			$('#div-type-'+i).find('input, textarea, button, select').removeAttr('disabled');
  				}else{
  		  			$('#div-type-'+i).hide();
  		  			$("#div-type-"+i+" :input").attr("disabled", true);
  		  			$('#div-type-'+i).find('input, textarea, button, select').attr('disabled','disabled');
  				}
  			}
  		})
  		
  		$('#city').on('change', function() {
			  var cityId = $(this).find(':selected').attr('data-cityid');
			  $.ajax({
				url         : '/ajax/get_districts',
				method      : 'post',
				data:{
					"cityId":cityId
				},
				success:function(rs) {
					if(rs.code == 200){
						if (rs.data != null) {
							var html = "";
							html += "<option data-hidden='true'></option>";
							rs.data.forEach(function(element) {
								html += "<option value='"+element.name+"'>"+element.name+"</option>";
								
							});
							$('#district').html(html);
							$('select[name=district]').selectpicker('refresh');
						}
					} else {
						alert("Can not change status !!!");
					}
				}
			});
		});
  	})
  	
  </script>
  
  <script>

      // In the following example, markers appear when the user clicks on the map.
      // The markers are stored in an array.
      // The user can then click an option to hide, show or delete the markers.
      var map;
      var markers = [];

      function initMap() {
        var haightAshbury =new google.maps.LatLng(${item.latitude},${item.longitude}); 
        var geocoder = new google.maps.Geocoder;
        
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 13,
          center: haightAshbury,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        // This event listener will call addMarker() when the map is clicked.
         map.addListener('click', function(event) {
           clearMarkers();
           markers = [];
           
           geocodeLatLng(geocoder, map,event.latLng );
         });
         geocodeLatLng(geocoder, map,haightAshbury );
        // Adds a marker at the center of the map.
      }	

      // Adds a marker to the map and push to the array.

      // Sets the map on all markers in the array.
      function setMapOnAll(map) {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(map);
        }
      }

      // Removes the markers from the map, but keeps them in the array.
      function clearMarkers() {
        setMapOnAll(null);
      }

      function geocodeLatLng(geocoder, map, location) {
          geocoder.geocode({'location': location}, function(results, status) {
            if (status === 'OK') {
              if (results[0]) {
               
                var marker = new google.maps.Marker({
                  position: location,
                  map: map
                });
                markers.push(marker);
                
                var postcode = '';
                var countryCode = '';
                var accuracy = '';
                var city = '';
                var district = '';
                for(var i=0; i < results[0].address_components.length; i++)
                {
                    var component = results[0].address_components[i];
                    if(component.types[0] == "postal_code")
                    {
                    	postcode = component.long_name;
                    }else if (component.types[0] == "country") {
                        countryCode = component.short_name;
                    }else if (component.types[1] == "sublocality") {
                    	district = component.short_name;
                    	
                    }else if (component.types[0] == "locality") {
                    	city = component.short_name;
                    	
                    }
                    
                }
                
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
                
                var infowindow = new google.maps.InfoWindow({
            	    content: 
            	    	'Latitude: ' + location.lat() + '<br>'+
            	    	'Longitude: ' + location.lng()+'<br>'+
            	    	'StatusCode: 200<br>'+
            	    	'StatusRequest: geocode<br>'+
            	    	'Address: ' +results[0].formatted_address+'<br>'+
            	    	'Accuracy: ' +accuracy+'<br>'+
            	    	'Country code: ' +countryCode+'<br>'
            	  });
                infowindow.open(map, marker);
                
                $('input[name=latitude]').val(location.lat());
                $('input[name=longitude]').val(location.lng());
        		
                $('input[name=postCode]').val(postcode);
                $('input[name=coordinatesCity]').val(location.lat()+','+location.lng());
                $('input[name=coordinatesDistrict]').val(location.lat()+','+location.lng());
              } else {
                window.alert('No results found');
              }
            } else {
              window.alert('Geocoder failed due to: ' + status);
            }
          });
        }
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=${googleMapkey}&callback=initMap&language=ko">
    </script>
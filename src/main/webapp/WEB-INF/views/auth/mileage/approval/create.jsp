	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul id="page-breadcrumb" class="page-breadcrumb"></ul>
              </div>

              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">에코 설정값 상세</h3>
                </div>
                <form id="approval_create" method="post" action="/auth/mileage/approval/create">
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">제목 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="title" id="title" class="form-control"/>
                    </div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">적용 기간 설정<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-4 datepicker-form">
                      <div class="item icon-next datepicker-item"><span>
                          <input name="startTime" id="startTime" type="text" data-date-format="yyyy/mm/dd" class="form-control date-picker"/></span><span class="text">달력</span></div>
                      <div class="item datepicker-item last"><span>
                          <input name="endTime" id="endTime" type="text" data-date-format="yyyy/mm/dd" class="form-control date-picker"/></span><span class="text">달력</span></div>
                    </div>
                  </div>
                  <div class="full mrb-20">
                  	<div class="col-md-3 line-30">
                  		<div class="row">
                  			<div class="col-md-6 text-left">주행 승인 기준</div>
                  			<div class="col-md-6 text-right">거리당 마일리지 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                  		</div>
                  	</div>
                    <div class="col-md-3">
                      <input type="text" name="bikePointPerKm" id="bikePointPerKm" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">Point/km </span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">일일 최대 승인 거리<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxMileagePerDay" id="maxMileagePerDay" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">km/인</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">1회 최대 승인 거리<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxMileagePerOnce" id="maxMileagePerOnce" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">km/인</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">일일 최대 승인 횟수<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxApprovalPerDay" id="maxApprovalPerDay" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">회</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">승인 기준일<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="approvalDateNumber" id="approvalDateNumber" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">일</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">평균 속도 최고값<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxAverageSpeed" id="maxAverageSpeed" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">Km/h 이하</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">평균 속도 최저값<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="minAverageSpeed" id="minAverageSpeed" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">Km/h 이상</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">최고 속도<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxSpeed" id="maxSpeed" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">km/h</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">이상 지점<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="problemPoint" id="problemPoint" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30"> </span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">가속도 최대값<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxAcceleration" id="maxAcceleration" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30"></span></div>
                  </div>
                  
                  <div class="full mrb-20">
                  	<div class="col-md-3 line-30">
                  		<div class="row">
                  			<div class="col-md-6 text-left">환승 승인 기준</div>
                  			<div class="col-md-6 text-right">1회 지급 마일리지<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                  		</div>
                  	</div>
                    
                    <div class="col-md-3">
                      <input type="text" name="tranferPointPerKm" id="tranferPointPerKm" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">1회</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">일일 최대 승인 횟수<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="maxApprovalTranferPerDay" id="maxApprovalTranferPerDay" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">회</span></div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 line-30">
                  		<div class="row">
                  			<div class="col-md-6 text-left">즐겨찾기 포인트 기준</div>
                  			<div class="col-md-6 text-right">1회 지급 마일리지<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                  		</div>
                  	</div>
                    <div class="col-md-3">
                      <input type="text" name="favoritePointPerKm" id="favoritePointPerKm" class="form-control text-right"/>
                    </div>
                    <div class="col-md-1"><span class="line-30">Point/km</span></div>
                  </div>
                  
                  <div class="full text-center">
                    <button type="button" class="btn green pad-8-50 btn-linklist">목록</button>
                    <button type="submit" class="btn green pad-8-50 btn-">저장</button>
                  </div>
                  
                </div>
                </form>
              </div>
              <!--.table-member-mng-->
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
      <!-- .member-->
      
<div id="popupDuplicateDate" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">적용 기간 안내</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>적용 기간이 겹치는 기준이 있습니다. 적용 기간을 재설정해주세요.</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
            <button type="button" data-dismiss="modal" class="btn green pad-8-50">확인</button>
          </div>
        </div>
      </div>
</div>

<!-- .popupConfirmDelete-->
<script type="text/javascript">
  	$(document).ready(function(){
  		$('.btn-linklist').click(function(){
      		window.location = '/auth/mileage/approval/list';
      	});
  		
  		$('#approval_create').on('submit', function(evt) {
  			evt.preventDefault();
  			var form        = $(this)[0];
  			var formData    = new FormData(form);
  			$
            .ajax({
                url         : $(this).attr('action'),
                method      : $(this).attr('method'),
                processData : false,
                contentType : false,
                data        : formData
            })
            .then(function(res) {
            		console.log(res);
            		if (res.status == 201) {
            			alert("Please check date information again.");
				} else if (res.status == 202) {
					$('#popupDuplicateDate').modal('show');
				} else if (res.status == 100) {
					alert("Can not request.");
				} else {
					window.location = '/auth/mileage/approval/list';
				}
            		
            }, function(err) {
            		alert("not ok....");
            });
  		});
  	});
      	
</script>
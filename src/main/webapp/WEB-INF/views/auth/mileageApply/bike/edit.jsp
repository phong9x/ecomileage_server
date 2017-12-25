<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<div class="page-content-wrapper">
    <div class="page-content">
      <div class="container">
        <div class="main-body-content member-page">
          <div class="page-bar">
            <ul class="page-breadcrumb">
            </ul>
          </div>

          <form id="form_sponsor_edit1" method="post">
            <div class="member-title-table">
              <div class="title-page full border-bottom mrb-30">
                <h3 class="bold pull-left">물품 신청 상세</h3>
              </div>
              <div class="full">
              	<div class="full mrb-10">
                  <div class="col-md-2 text-left line-30" style="font-weight: bold;font-size: medium;">신청 내용</div>
                </div>
              </div>
              <div class="full border-full pad-20-30 mrb-40">
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">ID :</div>
                  <div class="col-md-8 line-30">${item.user.username }</div>
                 
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">이름 :</div>
                  <div class="col-md-10 line-30">${item.user.fullname }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
                  <div class="col-md-10 line-30">${item.user.phone }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-110">주소 :</div>
                  <div class="col-md-10 line-30">
                    <p>${item.postCode }</p>
                    <p>${item.address }</p>
                    <p>${item.addressDetail }</p>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">신청 물품 / 개수 :</div>
                  <div class="col-md-10 line-30">${item.registerItem }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">사용 마일리지 :</div>
                  <div class="col-md-10 line-30">${item.pointUse } p</div>
                </div>
              </div>
              
              <div class="full">
              	<div class="full mrb-10">
                  <div class="col-md-2 text-left line-30" style="font-weight: bold;font-size: medium;">신청자 정보 (선택)</div>
                </div>
              </div>
              
              <div class="full border-full pad-20-30 mrb-40">
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">성별 :</div>
                  <div class="col-md-8 line-30">
                  	<c:if test="${item.gender == false }">여자</c:if>
                  	<c:if test="${item.gender == true }">남자</c:if>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">연령대 :</div>
                  <div class="col-md-10 line-30">${item.age }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">직업 :</div>
                  <div class="col-md-10 line-30">${item.job }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">상태 :</div>
                  <div class="col-md-2 line-30">
                  	<select name="status" data-status="${item.status}" id="statusItem" title="상태" class="form-control selectpickermember text-left">
                        <option value="">상태</option>
                        <option value="0" ${item.status == 0? 'selected':'' }>검토중</option>
                        <option value="1" ${item.status == 1? 'selected':'' }>승인</option>
                        <option value="2" ${item.status == 2? 'selected':'' }>불인</option>
                      </select>
                  </div>
                </div>
              </div>
              <div class="full text-center">
                  <button type="button" class="btn red pad-8-50 bike_mileage-back">목록</button>
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
  <div id="popupConfirmStatus1" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">신청 상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>신청 상태를 변경하면 다시 변경할 수 없습니다. 승인하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
          	<button type="button" data-id="${item.id}" class="btn red btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" data-status="${item.status}" class="btn btn-default btn-cancel">취소</button>
          </div>
        </div>
      </div>
</div>

<div id="popupConfirmStatus2" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">신청 상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>신청 상태를 변경하면 마일리지가복구되며 다시 변경할 수 없습니다. 불인하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
          	<button type="button" data-id="${item.id}" class="btn red btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" data-status="${item.status}" class="btn btn-default btn-cancel">취소</button>
          </div>
        </div>
      </div>
</div>
  <script type="text/javascript">
	  $('.bike_mileage-back').on('click', function() {
		  window.location.href = "/auth/mileageApply/bike/list";
      });
	  
	  $('#statusItem').on('change', function() {
		  var statusNew = this.value;
		  var statusOld = $(this).attr("data-status");
		  
		  if (statusOld == 0) {
			if (statusNew == 1) {
				$('#popupConfirmStatus1').find('.btn-confirm').attr('statusNew',statusNew);
				$('#popupConfirmStatus1').modal('show');
			} else if (statusNew == 2) {
				$('#popupConfirmStatus2').find('.btn-confirm').attr('statusNew',statusNew);
				$('#popupConfirmStatus2').modal('show');
			}
		  }
	  });
	  
	  $('.btn-cancel').on('click', function() {
			var current_status = $(this).attr("data-status");
			$('select[name=status]').val(current_status);
			$('select[name=status]').selectpicker('refresh');
	    });
	  
	  $('.btn-confirm').on('click', function() {
		  var statusNew = $(this).attr('statusNew');
		  var id = $(this).attr("data-id");
		  console.log("123: " + statusNew);
		  $.ajax({
				url         : '/ajax/mileageApply/bike/change_status',
				method      : 'post',
				data:{
					"id":id,
					"status" : statusNew
				},
				success:function(rs) {
					if(rs.code == 200){
						window .location.href = "/auth/mileageApply/bike/edit?id=" + id;
					} else {
						alert("Can not change status !!!");
					}
				}
			});
	  });
  
  </script>
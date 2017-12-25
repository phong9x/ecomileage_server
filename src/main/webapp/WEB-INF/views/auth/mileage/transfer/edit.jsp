<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
            <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
              </div>

              <form method="post" action="/auth/mileage/transfer/edit" id="form-vehicle-code">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">환승 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                  
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">ID :</div>
                      <div class="col-md-3 line-30">
                        <div class="col-md-6">${item.user.username }</div>
                      </div>
                      <div class="col-md-2 text-right line-30">이름 :</div>
                      <div class="col-md-3 line-30">
                        <div class="col-md-6">${item.user.fullname }</div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">교통수단 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6">
                        		<c:if test="${item.type == 2 }">버스</c:if>
	                      	<c:if test="${item.type == 3 }">지하철</c:if>
                        </div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">인증방식 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6">
                        		<c:if test="${item.typeCertificate == 0 }">NFC 태그</c:if>
                        		<c:if test="${item.typeCertificate == 1 }">QR code 태그</c:if>
                        		<c:if test="${item.typeCertificate == 2 }">WIFI 태그</c:if>
                        </div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">탑승 일시 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6"><fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd hh:mm:ss"/></div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">하차 일시 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6"><fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd hh:mm:ss"/></div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신청 마일리지 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6">${item.point }</div>
                      </div>
                    </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신청 날짜 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6"><fmt:formatDate value="${item.vehicleCode.registerDate }" pattern="yyyy-MM-dd"/></div>
                      </div>
                    </div>
                    
                    <div class="full mrb-30">
                      <div class="col-md-2 text-right line-30">승인 상태 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-3">
                          <select name="status" id="click-status" data-status="${item.status}" title="사용여부" class="form-control selectpickermember text-left">
                            <option ${item.status == 1 ? 'selected': '' } value="1" >승인</option>
	                      	<option ${item.status == 4 ? 'selected': '' } value="4">거부 </option>
	                      	<option ${item.status == 0 ? 'selected': '' } value="0">신청전</option>
	                      	<option ${item.status == 2 ? 'selected': '' } value="2">미승인</option>
                          </select>
                        </div>
                        <div class="col-md-7">
                              ${item.registerAgainReason }
                        </div>
                      </div>
                      
	                    <div class="full mrb-10" style="    margin-top: 25px;">
	                      <div class="col-md-2 text-right line-30">메모 :</div>
	                      <div class="col-md-10 line-30">
	                        <textarea name="memo" rows="10" class="rw-ckeditor form-control">${item.memo }</textarea>
	                      </div>
	                    </div>
                    </div>
                    <input type="hidden" name="id" value="${item.id }" class="form-control"/>
                    <div class="full text-center">
                     
                      <a href="/auth/mileage/transfer/list" class="btn grey pad-8-50">수정</a>
                      <button type="button" class="btn green pad-8-50 " onclick="validateFormVehicleCode();">목록</button>
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
        
        <div id="popupNotCheckSerialNumber" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">중복 확인</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <p>고유번호 중복확인을 해주세요.</p>
	          </div>
	          <div class="modal-footer">
	            <button type="button" data-dismiss="modal" class="btn btn-default">확인</button>
	          </div>
	        </div>
	      </div>
	    </div>
        
         <div id="popupExistsSerialNumber" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">중복 확인</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <p>이미 등록된 고유번호입니다.</p>
	          </div>
	          <div class="modal-footer">
	            <button type="button" data-dismiss="modal" class="btn btn-default">확인</button>
	          </div>
	        </div>
	      </div>
	    </div>

<div id="popupConfirmChangeStatus1" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">승인 상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>상태를 변경하면 사용자 마일리지가 적립됩니다. 변경하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
          	<button type="button" data-id="${item.id}" class="btn red btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" data-status="${item.status}" class="btn btn-default btn-cancel" >취소</button>
          </div>
        </div>
      </div>
</div>

<div id="popupConfirmChangeStatus2" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">승인 상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>상태를 변경하면 사용자 마일리지가 차감됩니다. 변경하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
          	<button type="button" data-id="${item.id}" class="btn red btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" data-status="${item.status}" class="btn btn-default btn-cancel">취소</button>
          </div>
        </div>
      </div>
</div>
        <script type="text/javascript">
        $(document).ready(function(){
    			$('select[name=status]').val('${item.status}');
    			$('select[name=status]').selectpicker('refresh');
    			
    			$('#form-vehicle-code').on('submit', function(evt) {
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
    	            		if (res.status == 200) {
    	            			window.location = '/auth/mileage/transfer/list';
    					} else {
    						alert("Can not request...");
    					}
    	            		
    	            }, function(err) {
    	            		alert("Can not request....");
    	            });
    			});
    			
    			$('#click-status').on('change', function() {
    				  var new_value = this.value;
    				  var current_status = $(this).attr("data-status");
    				  if(current_status == 3 && new_value == 1) {
    					  $('#popupConfirmChangeStatus1').find('.btn-confirm').attr('new_value',new_value);
    					  $('#popupConfirmChangeStatus1').modal('show');
    				  } else if (current_status == 1 && new_value == 2) {
    					  $('#popupConfirmChangeStatus2').find('.btn-confirm').attr('new_value',new_value);
    					  $('#popupConfirmChangeStatus2').modal('show');
    				  }
    			});
    			
    			$('.btn-cancel').on('click', function() {
    				var current_status = $(this).attr("data-status");
    				$('select[name=status]').val(current_status);
    				$('select[name=status]').selectpicker('refresh');
  			});
    			
    			$('.btn-confirm').on('click', function() {
    				var id = $(this).attr("data-id");
    				var new_value = $(this).attr('new_value');
    				$.ajax({
    					url         : '/auth/mileage/transfer/change_status',
    					method      : 'post',
    					data:{
    						"id":id,
    						"status" : new_value
    					},
    					success:function(rs) {
    						if(rs.status == 200){
    							window .location.href = "/auth/mileage/transfer/edit?id=" + id;
    						} else {
    							alert("Can not change status !!!");
    						}
    					}
    				});
  			});
    		});
        </script>
      </div>

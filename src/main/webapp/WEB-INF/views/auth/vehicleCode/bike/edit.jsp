<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
      <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                	<li class="menu-lever3"><a href="">자전거 NFC/QR코드 상세 </a></li>
                </ul>
              </div>

              <form  id="form-vehicle-code" method="post">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">자전거 NFC/QR코드 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">번호 :</div>
                      <div class="col-md-10 line-30">${param.no }</div>
                    </div>
                   <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">고유번호 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-4">
                          <input type="text" name="serialNumber" value="${item.serialNumber }" class="form-control"/>
                          <input type="hidden" name="typeVehicle" value="${item.typeVehicle }" class="form-control"/>
                        </div>
                        <button type="button" class="btn green" id="button-check-serial-number" disabled="disabled">중복확인</button>
                        <span class="red mrl-10 " id="serial-message-error" style="display: none">이미 등록된 고유번호입니다.</span>
                        <span class="green mrl-10 " id="serial-message-success" style="display: none">등록할 수 있는 고유번호입니다.</span>
                        <input type="hidden" name="existsSerialNumber" value="false" class="form-control"/>
                        <input type="hidden" name="checkSerialNumber" value="true" class="form-control"/>
                        <input type="hidden" name="serialNumberOld" value="${item.serialNumber }" class="form-control"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">아이디 :</div>
                      <div class="col-md-10 line-30">${item.user.username }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">이름 :</div>
                      <div class="col-md-10 line-30">${item.user.fullname }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
                      <div class="col-md-3 line-30">
                        <input type="text" name="phone" value="${item.phone }" class="form-control"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">우편번호 :</div>
                      <div class="col-md-3 line-30">
                        <input type="text" name="postCode" value="${item.postCode }" class="form-control"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">주소 :</div>
                      <div class="col-md-5 line-30">
                        <input type="text" name="location" value="${item.location }" class="form-control"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">신청날짜 :</div>
                      <div class="col-md-10 line-30"><fmt:formatDate value="${item.registerDate }" pattern="yyyy-MM-dd" /></div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">발송상태 :</div>
                      <div class="col-md-2">
                        <select name="typeSend" title="선택..." class="form-control selectpickermember text-left">
                          <option data-hidden="true"></option>
                          <option value="1" ${item.typeSend == 1? 'selected':'' }>발송전</option>
                          <option value="2" ${item.typeSend == 2? 'selected':'' }>발송완료</option>
                        </select>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">승인상태 :</div>
                      <div class="col-md-2">
                        <select name="status" id="status" title="선택..." class="form-control selectpickermember text-left">
                          <option data-hidden="true"></option>
                          <option value="0" ${item.status == 0? 'selected':'' }>미승인</option>
                          <option value="1" ${item.status == 1? 'selected':'' }>승인</option>
                          <option value="2" ${item.status == 2? 'selected':'' }>승인취소</option>
                        </select>
                      </div>
                       <div class="col-md-2 status-info">
                       		<c:if test="${item.status == 1}">
                       			<input type="text" disabled="disabled" value="<fmt:formatDate value="${item.updateDate }" pattern="yyyy-MM-dd HH:mm:ss" />" class="form-control"/>
                       			<input type="hidden" name="reason" value="${item.reason }"/>
                       		</c:if>
                      		<c:if test="${item.status == 2}"><input type="text" name="reason" value="${item.reason }" class="form-control"/></c:if>
                       </div>
                      
                    </div>
                    <div class="full mrb-40">
                      <div class="col-md-2 text-right line-30">등록 이미지 :</div>
                      <div class="col-md-4"><img src="${item.imageUrl}" /></div>
                    </div>
                     <input type="hidden" name="id" value="${item.id }" class="form-control"/>
                    <div class="full text-center">
                      <a href="/auth/vehicleCode/bike/list" class="btn grey pad-8-50">목록</a>
                      <button type="button" class="btn green pad-8-50 " onclick="validateFormVehicleCode();">수정</button>
                    </div>
                  </div>
                </div>
              </form>
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        
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
        <!-- .page-content-->
      </div>
      <script type="text/javascript">
      $('#status').on('change', function() {
      	console.log("sdfhd");
      	var status = $(this).val();
      	console.log("status: " + status);
      	var html ="";
      	if (status == 2) {
      		html += "<input type='text' name='reason' value='' class='form-control'/>";
		} else {
			html += "<input type='hidden' disabled='disabled' value='' class='form-control'/>";
		}
      	$('.status-info').html(html);
      });
      </script>
      
      
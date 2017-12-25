<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
            <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul id="page-breadcrumb" class="page-breadcrumb">
                	<li class="menu-lever3"><a href="">버스 NFC/QR코드 등록  </a></li>
                </ul>
              </div>

              <form  method="post" id="form-vehicle-code">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">버스 NFC/QR코드 등록</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">번호 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6">${id }</div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">고유번호 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-4">
                          <input type="text" name="serialNumber" value="${item.serialNumber }" class="form-control"/>
                          <input type="hidden" name="typeVehicle" value="2" class="form-control"/>
                        </div>
                        <button type="button" class="btn green" id="button-check-serial-number" disabled="disabled">중복확인</button>
                        <span class="red mrl-10 " id="serial-message-error" style="display: none">이미 등록된 고유번호입니다.</span>
                        <span class="green mrl-10 " id="serial-message-success" style="display: none">등록할 수 있는 고유번호입니다.</span>
                        <input type="hidden" name="existsSerialNumber" value="false" class="form-control"/>
                        <input type="hidden" name="checkSerialNumber" value="false" class="form-control"/>
                        <input type="hidden" name="serialNumberOld" value="" class="form-control"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">위치 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-3">
                          <input type="text" name="busCompanyName" placeholder="회사명" value="${item.busCompanyName}" class="form-control"/>
                        </div>
                        <div class="col-md-3">
                          <input type="text" name="busNo" placeholder="버스번호" value="${item.busNo}" class="form-control"/>
                        </div>
                        <div class="col-md-3">
                          <input type="text" name="busNumber" placeholder="차량번호" value="${item.busNumber}" class="form-control"/>
                        </div>
                        <div class="col-md-3">
                          <input type="text" name="location" placeholder="위치" value="${item.location}" class="form-control"/>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">부여날짜 :</div>
                      <div class="col-md-10">
                        <div class="col-md-6">
                          <div class="full datepicker-form">
                            <div class="item datepicker-item"><span>
                                <input name="registerDate" type="text" data-date-format="yyyy/mm/dd" value="" class="form-control date-picker"/></span><span class="text"> <i aria-hidden="true" class="fa fa-calendar"></i></span></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30">등록일 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-6"><fmt:formatDate value="${now }" pattern="yyyy/MM/dd"/></div>
                      </div>
                    </div>
                    <div class="full mrb-30">
                      <div class="col-md-2 text-right line-30">사용여부 :</div>
                      <div class="col-md-10 line-30">
                        <div class="col-md-3">
                          <select name="status" title="사용여부" class="form-control selectpickermember text-left">
                            <option data-hidden="true"></option>
                            <option value="0">미사용</option>
                            <option value="1">사용 </option>
                            <option value="2">폐기</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <input type="hidden" name="id" value="${item.id }" class="form-control"/>
                    <div class="full text-center">
                     
                      <a href="/auth/vehicleCode/bus/list" class="btn grey pad-8-50">목록</a>
                      <button type="button" class="btn green pad-8-50 " onclick="validateFormVehicleCode();">등록</button>
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
	    
        <script type="text/javascript">
        $(document).ready(function(){
    		$('select[name=status]').val('${item.status}');
    		$('select[name=status]').selectpicker('refresh');
    	})
        </script>
      </div>

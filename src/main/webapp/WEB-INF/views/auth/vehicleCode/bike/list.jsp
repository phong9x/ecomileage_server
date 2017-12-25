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
			  <form method="get">
              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">자전거 NFC/QR코드 관리</h3><span>자전거 NFC/QR 코드를 관리합니다.</span>
                </div>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">부여날짜로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-8">
                      <div class="full mrb-20">
                        <button type="button" class="btn green" id="time-all">전체</button>
                        <button type="button" class="btn green" id="time-1day">1일전</button>
                        <button type="button" class="btn green" id="time-1week">1주일</button>
                        <button type="button" class="btn green" id="time-1month">1개월</button>
                        <button type="button" class="btn green" id="time-3month">3개월</button>
                        <button type="button" class="btn green" id="time-6month">6개월</button>
                        <button type="button" class="btn green" id="time-1year">1년</button>
                      </div>
                      <div class="full datepicker-form">
                        <div class="item icon-next datepicker-item"><span>
                            <input name="registerTimeStart" type="text" data-date-format="yyyy/mm/dd" value="${param.registerTimeStart}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                        <div class="item datepicker-item last"><span>
                            <input name="registerTimeEnd" type="text" data-date-format="yyyy/mm/dd" value="${param.registerTimeEnd}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                      </div>
                    </div>
                  </div>
                 <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">아이디/이름 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="fullnameOrUsername" value="${param.fullnameOrUsername }" class="form-control"/>
                    </div>
                    <div class="col-md-3 text-right line-30">등록 여부로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <select name="typeRegister" title="선택..." class="form-control selectpickermember text-left">
                        <option value="">선택</option>
                        <option  value="1">등록</option>
                        <option  value="0">미등록</option>
                      </select>
                    </div>
                  </div>
                 <div class="full mrb-30">
                    <div class="col-md-3 text-right line-30">발송 상태로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <select name="typeSend" title="선택..." class="form-control selectpickermember text-left">
                        <option value="">선택</option>
                        <option value="1">발송전</option>
                        <option value="2">발송완료</option>
                      </select>
                    </div>
                    <div class="col-md-3 text-right line-30">승인 상태로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <select name="status" title="선택" class="form-control selectpickermember text-left">
                        <option value="">선택</option>
                        <option value="0">미승인</option>
                        <option value="1">승인</option>
                      </select>
                    </div>
                  </div>
                  <div class="full text-center">
                    <button class="btn green pad-8-50">검색</button>
                  </div>
                </div>
                
                <div class="md-ct row">
                  <util:downloadExcel/>
                </div>
              </div>
              </form>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>고유번호</th>
                      <th>아이디</th>
                      <th>이름</th>
                      <th>신청날짜</th>
                      <th>발송 상태</th>
                      <th>승인 상태</th>
                      <th>등록 여부</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list }" varStatus="loop" var="i">
	                  <c:set value="${totalCount - loop.index  - (currentIndex-1)*size}" var="index"></c:set>
	                    <tr onclick="window.location.href='/auth/vehicleCode/bike/edit?id=${i.id }&no=${index}'">
	                      <td>${index} </td>
	                      <td>${i.serialNumber}</td>
	                      <td><a href="/auth/vehicleCode/bike/edit?id=${i.id }&no=${index}">${i.user.username}</a></td>
	                      <td>${i.user.fullname }</td>
	                      <td><fmt:formatDate value="${i.registerDate }" pattern="yyyy.MM.dd"/></td>
	                      <td>
	                      	<c:if test="${i.typeSend == 1 }">발송전</c:if>
	                      	<c:if test="${i.typeSend == 2 }">발송완료</c:if>
	                      </td>
	                      <td>
	                      	<c:if test="${i.status == 0 }">미승인</c:if>
	                      	<c:if test="${i.status == 1 }">승인</c:if>
	                      	<c:if test="${i.status == 2 }">승인취소</c:if>
	                      </td>
	                      <td>
	                      	<c:if test="${i.typeRegister == 0 }">미등록</c:if>
	                      	<c:if test="${i.typeRegister == 1 }">등록</c:if>
	                      </td>
	                    </tr>
	                  </c:forEach>
	                  
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form" style="bottom: auto" onclick="location.href='/auth/vehicleCode/bike/create'">NFC/QR코드 등록    </button>
              <!--.table-member-mng-->

              <util:paging />
              <!-- .rw-pagination-->
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <iframe id="txtArea1" style="display:none"></iframe>
        <!-- .page-content-->
        <script type="text/javascript">
        
        	$('#time-all').click(function(){
        		$('input[name=registerTimeStart]').val('');
        		$('input[name=registerTimeEnd]').val('');
        	})
			
        	$('#time-1day').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setDate(d.getDate()-1)
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$('#time-1week').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setDate(d.getDate()-7)
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$('#time-1month').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setMonth(d.getMonth()-1);
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$('#time-3month').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setMonth(d.getMonth()-3);
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$('#time-6month').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setMonth(d.getMonth()-6);
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$('#time-1year').click(function(){
        		var d = new Date();
        		$('input[name=registerTimeEnd]').val(formatDate(d));
        		d.setFullYear(d.getFullYear()-1);
        		$('input[name=registerTimeStart]').val(formatDate(d));
        	})
        	
        	$(document).ready(function(){
        		$('select[name=status]').val('${param.status}');
        		$('select[name=status]').selectpicker('refresh');
        		
        		$('select[name=typeRegister]').val('${param.typeRegister}');
        		$('select[name=status]').selectpicker('refresh');
        		
        		$('select[name=typeSend]').val('${param.typeSend}');
        		$('select[name=typeSend]').selectpicker('refresh');
        		
        	})
        	
        </script>
      </div>

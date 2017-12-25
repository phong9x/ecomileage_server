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
                  <h3 class="bold pull-left">환승 데이터</h3><span>환승 마일리지 데이터를 관리합니다.</span>
                </div>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">기간으로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
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
                            <input name="registerTimeStart" type="text" data-date-format="yyyy/mm/dd" value="${registerTimeStart}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                        <div class="item datepicker-item last"><span>
                            <input name="registerTimeEnd" type="text" data-date-format="yyyy/mm/dd" value="${registerTimeEnd}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                      </div>
                    </div>
                  </div>
                 <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">아이디/이름 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="fullnameOrUsername" value="${param.fullnameOrUsername }" class="form-control"/>
                    </div>
                    
                  </div>
                 
                  <div class="full text-center">
                    <button class="btn green pad-8-50">검색</button>
                  </div>
                </div>
                
                <div class="md-ct row">
                	<div class="col-md-3 pull-right">
                      <select name="status" title="선택..." class="form-control selectpickermember text-left" onchange="this.form.submit()">
                        <option value="">상태</option>
                        <option value="1">승인</option>
                        <option value="4">거부</option>
                        <option value="0">신청전</option>
                        <option value="2">미승인</option>
                      </select>
                    </div>
                </div>
              </div>
              
              </form>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>아이디</th>
                      <th>이름</th>
                      <th>교통수단</th>
                      <th>신청 마일리지</th>
                      <th>탑승 일시</th>
                      <th>신청 일시</th>
                      <th>상태</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:if test="${list.size() > 0}">
	                  <c:forEach begin="0" end="${list.size() -1 }" var="i">
	                    <tr>
	                      <td>${totalCount - ((currentIndex -1) * 10 +i) }</td>
	                      <td><a href="/auth/mileage/transfer/edit?id=${list[i].id }">${list[i].user.username}</a></td>
	                      <td><a href="/auth/mileage/transfer/edit?id=${list[i].id }">${list[i].user.fullname}</a></td>
	                      <td>
	                      	<c:if test="${list[i].type == 2 }">버스</c:if>
	                      	<c:if test="${list[i].type == 3 }">지하철</c:if>
	                      </td>
	                      <td>${list[i].point } p</td>
	                      <td><fmt:formatDate value="${list[i].startTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	                      <td><fmt:formatDate value="${list[i].vehicleCode.registerDate }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	                      <td>
	                      	<c:if test="${list[i].status == 1 }">승인</c:if>
	                      	<c:if test="${list[i].status == 4 }">거부</c:if>
	                      	<c:if test="${list[i].status == 0 }">신청전</c:if>
	                      	<c:if test="${list[i].status == 2 }">미승인</c:if>
	                      </td>
	                    </tr>
	                  </c:forEach>
	                  </c:if>
                  </tbody>
                </table>
              </div>

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

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
                  <h3 class="bold pull-left">일시후원</h3><span>일시후원 내역을 관리합니다.</span>
                </div>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">후원날짜로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
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
                            <input name="startTime" type="text" data-date-format="yyyy/mm/dd" value="${param.startTime}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                        <div class="item datepicker-item last"><span>
                            <input name="endTime" type="text" data-date-format="yyyy/mm/dd" value="${param.endTime}" class="form-control date-picker"/></span><span class="text">달력</span></div>
                      </div>
                    </div>
                  </div>
                  
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">아이디/이름 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <input type="text" name="fullnameOrUsername" value="${param.fullnameOrUsername }" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                      <button class="btn green pad-8-50">검색</button>
                    </div>
                  </div>
                  
                 <div class="full mrb-30">
                    <div class="col-md-3 text-right line-30">발송 상태로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <select name="paymentMethod" title="결제 수단" class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option  value="" >결제 수단</option>
                      <option ${param.paymentMethod == 'wcard' ? 'selected': '' }  value="wcard">카드 결제</option>
                      <option ${param.paymentMethod == 'bank' ? 'selected': '' } value="bank">실시간 계좌이체</option>
                      <option ${param.paymentMethod == 'kakaoPay' ? 'selected': '' } value="kakaoPay">카카오 페이 </option>
                    </select>
                    </div>
                  </div>
                </div>
                
                </div>
                <div class="md-ct row">
                  <div class="col-md-3 pull-left bold">총 포인트후원 금액 : ${totalMoney } 원</div>
                </div>
              </form>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>아이디</th>
                      <th>이름</th>
                      <th>결제 수단</th>
                      <th>후원 금액</th>
                      <th>후원 날짜</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list}" varStatus="loop" var="i">
	                    <tr>
	                      <td>${totalCount - loop.index  - (currentIndex-1)*size}</td>
	                      <td><a href="/auth/sponsor/tem/edit?id=${i.id}">${i.user.username == null ? i.user.fullname : i.user.username }</a></td>
	                      <td>${i.user.fullname }</td>
	                      <td>
	                      	<c:if test="${i.paymentMethod == 'wcard'}">카드 결제</c:if>
	                      	<c:if test="${i.paymentMethod == 'bank'}">실시간 계좌이체</c:if>
	                      	<c:if test="${i.paymentMethod == 'kakaoPay'}">카카오 페이 </c:if>
	                      </td>
	                      <td>${i.money }</td>
	                      <td><fmt:formatDate value="${i.createDate }"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
	                    </tr>
	                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form" style="bottom: auto" onclick="location.href='/auth/sponsor/tem/create'">후원 내역 추가</button>
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
	    		$('input[name=startTime]').val('');
	    		$('input[name=endTime]').val('');
	    	})
			
	    	$('#time-1day').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setDate(d.getDate()-1)
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
	    	
	    	$('#time-1week').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setDate(d.getDate()-7)
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
	    	
	    	$('#time-1month').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setMonth(d.getMonth()-1);
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
	    	
	    	$('#time-3month').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setMonth(d.getMonth()-3);
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
	    	
	    	$('#time-6month').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setMonth(d.getMonth()-6);
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
	    	
	    	$('#time-1year').click(function(){
	    		var d = new Date();
	    		$('input[name=endTime]').val(formatDate(d));
	    		d.setFullYear(d.getFullYear()-1);
	    		$('input[name=startTime]').val(formatDate(d));
	    	})
        	
        	
        </script>
      </div>

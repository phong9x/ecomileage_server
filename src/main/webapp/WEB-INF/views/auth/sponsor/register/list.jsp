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
                  <h3 class="bold pull-left">정기후원</h3><span>정기 후원 내역을 관리합니다.</span>
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
                    
                  </div>
                 <div class="full mrb-30">
                    <div class="col-md-3 text-right line-30">발송 상태로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-3">
                      <select name="membershipFeeTypeId" title="선택..." class="form-control selectpickermember text-left">
                        <option value="">회비 유형</option>
                        <c:forEach items="${listMembershipFeeType}" var="i">
                        	<option value="${i.id}">${i.name }</option>
                        </c:forEach>
                      </select>
                    </div>
                  </div>
                  <div class="full text-center">
                    <button class="btn green pad-8-50">검색</button>
                  </div>
                </div>
                <div class="md-ct row">
                  <div class="col-md-3 pull-left bold">총 정기후원 금액   : ${totalMoneySponsor } 원</div>
                  <div class="col-md-2 pull-right">
                    <select name="isDelete"  class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option  value="" >후원 상태</option>
                      <option  value="true">중단 </option>
                      <option  value="false">진행중 </option>
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
                      <th>회비 유형</th>
                      <th>회비 금액</th>
                      <th>후원 시작 날짜</th>
                      <th>후원 상태</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list}"  var="i" varStatus="loop">
	                    <tr>
	                      <c:set value="${totalCount - loop.index  - (currentIndex-1)*size}" var="index"></c:set>
	                      <td>${index }</td>
	                      <td><a href="/auth/sponsor/register/edit${i.isDelete == true ? 2: 1}?id=${i.id }">${i.user.username }</a></td>
	                      <td>${i.user.fullname }</td>
	                      <td>${i.category.name }</td>
	                      <td>
	                      	<fmt:formatNumber value="${i.membershipFee}" maxFractionDigits="3"></fmt:formatNumber>
	                      </td>
	                      <td><fmt:formatDate value="${i.sponsorDate }"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
	                      <td>
	                      	${i.isDelete ? '중단': '진행중'}
	                      </td>
	                    </tr>
	                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form" style="bottom: auto" onclick="location.href='/auth/sponsor/register/create'">후원 내역 추가</button>
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

        	$(document).ready(function(){
        		$('select[name=status]').val('${param.status}');
        		$('select[name=status]').selectpicker('refresh');
        		
        		$('select[name=membershipFeeTypeId]').val('${param.membershipFeeTypeId}');
        		$('select[name=membershipFeeTypeId]').selectpicker('refresh');

        		$('select[name=isDelete]').val('${param.isDelete}');
        		$('select[name=isDelete]').selectpicker('refresh');
        	})
        	
        </script>
      </div>

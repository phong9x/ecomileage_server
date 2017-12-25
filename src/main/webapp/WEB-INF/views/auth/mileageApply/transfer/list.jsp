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
                  <h3 class="bold pull-left">환승 마일리지 물품 신청</h3><span>환승 마일리지 물품 신청 리스트를 관리합니다.</span>
                </div>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">신청기간으로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
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
                    <div class="col-md-2">
                      <input type="text" name="fullnameOrUsername" value="${param.fullnameOrUsername }" class="form-control"/>
                    </div>
                    <div class="col-md-3 text-right line-30"><button class="btn green pad-8-50">검색</button></div>
                  </div>
                 <div class="full mrb-30">
                    <div class="col-md-3 text-right line-30">상태로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-1">
                      <select name="typeSend" title="상태" class="form-control selectpickermember text-left">
                        <option value="">상태</option>
                        <option value="0">발송전</option>
                        <option value="1">발송완료</option>
                      </select>
                    </div>
                    <div class="col-md-2 text-right line-30">상품권으로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-2">
                      <select name="giftId" title="상태" class="form-control selectpickermember text-left">
                        <option value="">상태</option>
                        <c:forEach items="${giftCategories}" var="i">
                      		<option ${param.giftId == i.id ? 'selected': '' } value="${i.id }">${i.name }</option>
                      	</c:forEach>
                      </select>
                    </div>
                  </div>
                  
                </div>
                
                <div class="md-ct row">
                	<div class="col-md-2 text-left" style="line-height: 36px;">총 신청 건수  : ${totalCount}</div>
                  	<util:downloadExcel/>
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
                      <th>신청 상품권</th>
                      <th>사용 마일리지</th>
                      <th>신청 날짜</th>
                      <th>발송 상태</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list }" varStatus="loop" var="i">
	                    <tr>
	                      <td>${totalCount - loop.index  - (currentIndex-1)*size}</td>
	                      <td><a href="/auth/mileageApply/transfer/edit${i.typeSend == 0 ? 1: 2}?id=${i.id}">${i.user.username}</a></td>
	                      <td>${i.user.fullname}</td>
	                      <td>${i.category.name }</td>
	                      <td>${i.pointUse}</td>
	                      <td><fmt:formatDate value="${i.createDate }" pattern="yyyy-MM-dd"/></td>
	                      <td>
	                      	<c:if test="${i.typeSend == 0}">발송전</c:if>
	                      	<c:if test="${i.typeSend == 1}">발송완료</c:if>
	                      	</td>
	                    </tr>
	                  </c:forEach>
	                  
                  </tbody>
                </table>
              </div>
              
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

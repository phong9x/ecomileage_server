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
                  <h3 class="bold pull-left">지하철 NFC/QR코드 관리</h3><span>지하철 NFC/QR코드 관리 페이지입니다.</span>
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
                  <div class="full">
                    <div class="col-md-3 text-right line-30">위치명으로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-4">
                      <input type="text" name="locationSubway" value="${param.locationSubway}" class="form-control"/>
                    </div>
                    <div class="col-md-2">
                      <button type="submit" class="btn green">검색</button>
                    </div>
                  </div>
                </div>
                <div class="md-ct row">
                  <div class="col-md-2 pull-right">
                    <select name="status" title="선택..." class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option  value="" >사용여부</option>
                      <option  value="1">사용</option>
                      <option  value="0">미사용</option>
                      <option  value="2">폐기 </option>
                    </select>
                  </div>
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
                      <th>위치</th>
                      <th>부여날짜</th>
                      <th>사용여부</th>
                      <th>등록일</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list}" varStatus="loop" var="i">
	                    <tr>
	                      <c:set value="${totalCount - loop.index  - (currentIndex-1)*size}" var="index"></c:set>
	                      <td>${index }</td>
	                      <td><a href="/auth/vehicleCode/subway/edit?id=${i.id}&no=${index}">${i.serialNumber}</a></td>
	                      <td>${i.subwayLineNo} ${i.subwayStationName} ${i.subwayDirection} ${i.subwayDoorNo }</td>
	                      <td><fmt:formatDate value="${i.registerDate }" pattern="yyyy.MM.dd"/></td>
	                      <td>
	                      	<c:if test="${i.status == 0 }">미사용</c:if>
	                      	<c:if test="${i.status == 1 }">사용</c:if>
	                      	<c:if test="${i.status == 2 }">폐기</c:if>
	                      </td>
	                       <td><fmt:formatDate value="${i.createDate }" pattern="yyyy.MM.dd"/></td>
	                    </tr>
	                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form" style="bottom: auto" onclick="location.href='/auth/vehicleCode/subway/create'">NFC/QR코드 등록    </button>
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
        	})
        	
        </script>
      </div>

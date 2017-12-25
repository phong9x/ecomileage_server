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
                  <h3 class="bold pull-left">오류 데이터 검토</h3><span>오류 데이터를 검토합니다</span>
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
                    <div class="col-md-1">
                      <button class="btn green">검색</button>
                    </div>
                  </div>
                  
                  <div class="full mrb-30">
                    <div class="col-md-3 text-right line-30">주행지역으로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-2 cityss" data-districtName="${districtM}">
                      <select name="city-m" id="city-m" title="시/도" class="form-control selectpickermember text-left">
                        <option data-hidden="true"></option>
                        <c:forEach items="${citys}" var="city">
                        	<c:if test="${cityM == city.name }"><option data-cityid="${city.id }" selected="selected" value="${city.name}">${city.name}</option></c:if>
                        	<c:if test="${cityM != city.name }"><option data-cityid="${city.id }" value="${city.name}">${city.name}</option></c:if>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="col-md-2">
                      <select name="district-m" id="district-m" title="시/군/구" class="form-control selectpickermember text-left">
                      	<c:forEach items="${districts}" var="dis">
                        	<c:if test="${districtM == dis.name }"><option data-cityid="${dis.id }" selected="selected" value="${dis.name}">${dis.name}</option></c:if>
                        	<c:if test="${districtM != dis.name }"><option data-cityid="${dis.id }" value="${dis.name}">${dis.name}</option></c:if>
                        </c:forEach>
                      </select>
                    </div>
                    
                    <div class="col-md-5">
                    	<div class="col-md-3 text-right line-30">상태로 검색 <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                		<div class="col-md-3">
                      		<select name="status" title="상태 구분" class="form-control selectpickermember text-left">
		                        <option value="">상태 구분</option>
		                        <!-- <option value="0">미승인</option>
		                        <option value="1">승인</option> -->
		                        <option value="2">거부</option>
		                        <option value="3">재신청</option>
		                      </select>
                    	</div>
                	</div>
                  </div>
                </div>
              </div>
              </form>
              
              <div class="table-member-mng table-responsive">
              	<c:forEach items="${list}" var="i">
              	<table class="table table-striped table-bordered table-hover text-center" onclick="window.location.href='/auth/mileage/error/edit?id=${i.id}'">
                  <tbody>
                    <tr>
                      <td rowspan="7">${i.id}</td>
                      <td colspan="6">1500438363795.kml</td>
                    </tr>
                    <tr>
                      <td>출발지</td>
                      <td>${i.latStart}</td>
                      <td>이동중 평균속도</td>
                      <td>${i.averageSpeed}km/h</td>
                      <td>[${i.user.fullname}](${i.user.username})</td>
                      <td><fmt:formatDate pattern = "yyyy-MM-dd hh:mm" value = "${i.createDate }" /></td>
                    </tr>
                    <tr>
                      <td>도착지</td>
                      <td>${i.longStart}</td>
                      <td>최고 속도</td>
                      <td>${i.maxSpeed}</td>
                      <td colspan="2"></td>
                    </tr>
                    <tr>
                      <td>시작 시간</td>
                      <td><fmt:formatDate pattern = "yyyy-MM-dd hh:mm:ss" value = "${i.startTime }" /></td>
                      <td>이상지점</td>
                      <td></td>
                      <td colspan="2"></td>
                    </tr>
                    <tr>
                      <td>종료 시간</td>
                      <td><fmt:formatDate pattern = "yyyy-MM-dd hh:mm:ss" value = "${i.endTime }" /></td>
                      <td>총 이동 거리</td>
                      <td>${i.totalMileage}km/h</td>
                      <td colspan="2"></td>
                    </tr>
                    <tr>
                      <td>승인 타입</td>
                      <td></td>
                      <td>포인트</td>
                      <td>${i.point}</td>
                      <td colspan="3">
<!--                       	<span>주행 포인트 : 25p &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
<!--                       	<span>즐겨찾기 포인트 : 25p &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
						<span></span>
						<span></span>
                        <c:if test="${i.isAdminApproval == true }">
                        	<button class="btn green btn-process-approval" data-id="${i.id}">승인</button>
                        </c:if>
                      </td>
                    </tr>
                    
                    <tr>
                      <td>상태</td>
<%--                       <td>${i.status}</td> --%>
					  <td></td>
                      <td>사유</td>
<%--                       <td colspan="4">${i.registerAgainReason}</td> --%>
					  <td colspan="3"></td>
                    </tr>
                  </tbody>
                </table>
              	</c:forEach>
              </div>
            </div>
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
        	
        	var districtName = $(".cityss").attr('data-districtName');
        	$('select[name=district-m]').val(districtName);
        	
        	$(document).ready(function(){
        		$('select[name=status]').val('${param.status}');
        		$('select[name=status]').selectpicker('refresh');
        		
        		$('select[name=typeRegister]').val('${param.typeRegister}');
        		$('select[name=status]').selectpicker('refresh');
        		
        		$('select[name=typeSend]').val('${param.typeSend}');
        		$('select[name=typeSend]').selectpicker('refresh');
        		
        		$('#city-m').on('change', function() {
  				  var cityId = $(this).find(':selected').attr('data-cityid');
  				  $.ajax({
					url         : '/auth/mileage/bike/get_districts',
					method      : 'post',
					data:{
						"cityId":cityId
					},
					success:function(rs) {
						if(rs.status == 200){
							if (rs.data != null) {
								var html = "";
								html += "<option data-hidden='true'></option>";
								rs.data.forEach(function(element) {
									html += "<option value='"+element.name+"'>"+element.name+"</option>";
									
								});
								$('#district-m').html(html);
								$('select[name=district-m]').selectpicker('refresh');
							}
						} else {
							alert("Can not change status !!!");
						}
					}
				});
  				});
        		
        		$('.btn-process-approval').click(function(){
              		var id = $(this).attr("data-id");
              		$.ajax({
    					url         : '/auth/mileage/bike/process_approval',
    					method      : 'post',
    					data:{
    						"id":id
    					},
    					success:function(rs) {
    						if(rs.status == 200){
    							window .location.href = "/auth/mileage/error/list";
    						} else {
    							alert("Can not change status !!!");
    						}
    					}
    				});
              	});
        		
        	})
        	
        </script>
      </div>

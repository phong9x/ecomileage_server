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
                  <h3 class="bold pull-left">회원 관리</h3><span>에코 마일리지의 회원 정보를 관리합니다.</span>
                </div>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">가입기간으로 검색  <i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
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
                    <div class="col-md-3 text-right line-30">지역으로 검색<i aria-hidden="true" class="fa fa-angle-right font-18 mrl-10"></i></div>
                    <div class="col-md-1">
                      <select name="city" id="city" title="시/도" class="form-control selectpickermember text-left">
                        <option data-hidden="true"></option>
                        <c:forEach items="${citys}" var="city">
                        	<c:if test="${cityM == city.name }"><option data-cityid="${city.id }" selected="selected" value="${city.name}">${city.name}</option></c:if>
                        	<c:if test="${cityM != city.name }"><option data-cityid="${city.id }" value="${city.name}">${city.name}</option></c:if>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="col-md-1 text-right line-30"></div>
                    <div class="col-md-1">
                      <select name="district" id="district" title="시/군/구" class="form-control selectpickermember text-left">
                      	<c:forEach items="${districts}" var="dis">
                        	<c:if test="${districtM == dis.name }"><option data-cityid="${dis.id }" selected="selected" value="${dis.name}">${dis.name}</option></c:if>
                        	<c:if test="${districtM != dis.name }"><option data-cityid="${dis.id }" value="${dis.name}">${dis.name}</option></c:if>
                        </c:forEach>
                      </select>
                    </div>
                  </div>
                  <div class="full text-center">
                    <button class="btn green pad-8-50">검색</button>
                  </div>
                </div>
                
                <div class="md-ct row">
                  <div class="col-md-3 pull-left bold">총 회원수 : ${totalCount }</div>
                  <div class="col-md-2 pull-right">
                    <select name="roleId" title="선택..." class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option  value="" >회원구분</option>
                      <option  value="1">준회원</option>
                      <option  value="2">정회원</option>
                      <option  value="3">후원회원 </option>
                      <option  value="4">탈퇴회원 </option>
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
                      <th>아이디</th>
                      <th>이름</th>
                      <th>휴대폰 번호</th>
                      <th>가입날짜</th>
                      <th>회원구분</th>
                    </tr>
                  </thead>
                  <tbody>
                  	 <c:if test="${list.size() >0 }">
                  	 	<c:forEach begin="0" end="${list.size() -1 }" var="i">
	                    <tr>
	                      <td>${totalCount - ((currentIndex -1) * 10 +i) }</td>
	                      <td><a href="/auth/user/member/edit?id=${list[i].id}">${list[i].username}</a></td>
	                      <td><a href="/auth/user/member/edit?id=${list[i].id}">${list[i].fullname }</a></td>
	                      <td>${list[i].phone }</td>
	                      <td><fmt:formatDate value="${list[i].createDate }" pattern="yyyy.MM.dd"/></td>
	                      <td>
	                      	<c:choose>
	                      		<c:when test="${list[i].isDelete }">
	                      			<span style="color: red">탈퇴회원</span>
	                      		</c:when>
	                      		<c:otherwise>
	                      			<c:if test="${list[i].userRole.id == 1 }">준회원 </c:if>
	                      			<c:if test="${list[i].userRole.id == 2 }">정회원</c:if>
	                      			<c:if test="${list[i].userRole.id == 3 }">후원회원</c:if>
	                      		</c:otherwise>
	                      	</c:choose>
	                      	
	                      </td>
	                    </tr>
	                  </c:forEach>
                  	 </c:if>
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form" style="bottom: auto" onclick="location.href='/auth/user/member/create'">후원회원 등록    </button>
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
        		$.ajax({
        			url         : '/auth/user/member/getMinStartTime',
					method      : 'get',
					data:{},
					success:function(rs) {
						if(rs.status == 200){
							if (rs.data != null) {
								$('input[name=startTime]').val(rs.data.minStartTime);
				        		$('input[name=endTime]').val(rs.data.currentDate);
							}
						} else {
							alert("Can not change status !!!");
						}
					}
				});
        		
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
        		$('select[name=roleId]').val('${param.roleId}');
        		$('select[name=roleId]').selectpicker('refresh');
        		
        		$('#city').on('change', function() {
    				  var cityId = $(this).find(':selected').attr('data-cityid');
    				  $.ajax({
	  					url         : '/ajax/get_districts',
	  					method      : 'post',
	  					data:{
	  						"cityId":cityId
	  					},
	  					success:function(rs) {
	  						if(rs.code == 200){
	  							if (rs.data != null) {
	  								var html = "";
	  								html += "<option data-hidden='true'></option>";
	  								rs.data.forEach(function(element) {
	  									html += "<option value='"+element.name+"'>"+element.name+"</option>";
	  									
	  								});
	  								$('#district').html(html);
	  								$('select[name=district]').selectpicker('refresh');
	  							}
	  						} else {
	  							alert("Can not change status !!!");
	  						}
	  					}
	  				});
    			});
        		
        	})
        	
        </script>
      </div>

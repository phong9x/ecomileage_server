<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

   	<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul id="page-breadcrumb" class="page-breadcrumb"></ul>
              </div>

              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">에코 설정값</h3><span>에코 마일리지 정책을 설정합니다.</span>
                </div>
                <div class="md-ct row">
                  <div class="col-md-4 pull-right">
                  	<form>
                    <div class="input-search">
                      <input type="text" name="title" value="${title}" placeholder="제목으로 검색" class="form-control"/>
                      <button style ="position: absolute;width: 34px;height: 34px;top: 0;
    right: 0;display: inline-block;line-height: 32px;padding-top: 10px;text-align: center;border: none;background: none;"><i aria-hidden="true" class="fa fa-search"></i></button>
                    </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>제목</th>
                      <th>시작날짜</th>
                      <th>종료날짜</th>
                      <th>등록날짜</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${approval}" var="a">
                  		<tr>
	                      <td>${a.id}</td>
	                      <td><a href="/auth/mileage/approval/edit?id=${a.id}">${a.title }</a></td>
	                      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.startTime }" /></td>
	                      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.endTime }" /></td>
	                      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.createDate }" /></td>
	                    </tr>
                  	</c:forEach>
                  </tbody>
                </table>
              </div>
              <button class="btn green dowload-form btn-create" style="z-index: 999; position: absolute;">승인 기준 추가</button>
              <!--.table-member-mng-->

              <util:paging />
              <!-- .rw-pagination-->
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
      <!-- .member-->
      
<script type="text/javascript">
  	$(document).ready(function(){
  		$('.btn-create').click(function(){
      		window.location = '/auth/mileage/approval/create';
      	});
  	});
      	
</script>
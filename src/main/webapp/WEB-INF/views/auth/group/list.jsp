<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

   	<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul id="page-breadcrumb" class="page-breadcrumb">
                
                
                </ul>
              </div>

              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">그룹 관리</h3><span>그룹 정보를 관리합니다.</span>
                </div>
                <form method="get">
                <div class="md-ct row">
                  <div class="col-md-3 pull-left bold">총 그룹 수 : ${totalCount }</div>
                  <div class="col-md-2 pull-right">
                    <select name="isDelete" title="상태 " class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      	<option value="">상태 </option>
                      	<option ${param.isDelete == 0 ? 'selected': '' } value="0">운영중 </option>
                      	<option ${param.isDelete == 1 ? 'selected': '' } value="1">삭제 </option>
                    </select>
                  </div>
                  <div class="col-md-4 pull-right">
                    <div class="input-search">
                      <input type="text" name="name" value="${param.name}" placeholder="그룹명으로 검색" class="form-control"/>
                      <button type="submit" style ="position: absolute;width: 34px;height: 34px;top: 0;
    							right: 0;display: inline-block;line-height: 32px;padding-top: 10px;
    							text-align: center;border: none;background: none;">
    							<i aria-hidden="true" class="fa fa-search"></i>
    				  </button>
                    </div>
                  </div>
                </div>
                </form>
              </div>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>그룹 이름</th>
                      <th>그룹장 이름</th>
                      <th>참여자수</th>
                      <th>그룹 생성일</th>
                      <th>상태</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${list}" var="i" varStatus="loop">
                  		<tr >
                  		<c:set value="${totalCount - loop.index  - (currentIndex-1)*size}" var="index"></c:set>
	                      <td>${index }</td>
	                      <td><a href="/auth/group/edit?id=${i.id }">${i.name }</a></td>
	                      <td>${i.fullname }</td>
	                      <td>${i.totalMember }</td>
	                      <td><fmt:formatDate value="${i.createDate }" pattern="yyyy-MM-dd"/></td>
	                      ${i.isDelete == false? '<td>운영중</td>':'<td style="color:red">삭제</td>' }
	                    </tr>
                  	</c:forEach>
                  	
                  </tbody>
                </table>
              </div>
              <util:paging />
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
      <!-- .member-->
      
<div id="popupConfirmApproval" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>데이터 이전 승인 시 구 에코 마일리지 앱의 총 이동거리, 총 활동수, 마일리지 정보가 현재 아이디에 업데이트 됩니다. 승인하시겠습니까?</p>
          </div>
          <div class="modal-footer" style="text-align: center;">
            <button type="button" data-dismiss="modal" class="btn green pad-8-50 btn-confirm">확인</button>
            <button type="button" data-dismiss="modal" class="btn green pad-8-50 btn-cancel">취소</button>
          </div>
        </div>
      </div>
</div>

<div id="popupConfirmNotApproval" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">상태 변경</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <p>데이터 이전 요청을 불인 하시겠습니까? </p>
          </div>
          <div class="modal-footer" style="text-align: center;">
            <button type="button" data-dismiss="modal"  class="btn green pad-8-50 btn-confirm">확인</button>
            <button type="button" data-dismiss="modal"  class="btn green pad-8-50 btn-cancel">취소</button>
          </div>
        </div>
      </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#codeStatus #status-sub').change(function() {
		var id = $(this).attr("data-id");
		var oldStatus = $(this).attr("data-id");
		var newStatus = $(this).val();
		if (newStatus == 1) {
			$('#popupConfirmApproval').modal('show');
			$('#popupConfirmApproval').find('.btn-confirm').attr('id',id);
			$('#popupConfirmApproval').find('.btn-confirm').attr('status',newStatus);
			$('#popupConfirmApproval').find('.btn-cancel').attr('status',oldStatus);
		} else if (newStatus == 2) {
			$('#popupConfirmNotApproval').modal('show');
			$('#popupConfirmNotApproval').find('.btn-confirm').attr('id',id);
			$('#popupConfirmNotApproval').find('.btn-confirm').attr('status',newStatus);
			$('#popupConfirmNotApproval').find('.btn-cancel').attr('status',oldStatus);
		} else {
			$.ajax({
				url         : '/auth/user/code/change_status',
				method      : 'post',
				data:{
					"id":id,
					"status" : newStatus
				},
				success:function(rs) {
					if(rs.status == 200){
						window .location.href = "/auth/user/code/list";
					} else {
						alert("Can not change status !!!");
					}
				}
			});
		}
    });
	$('.btn-cancel').on('click', function() {
		var status = $(this).attr("status");
		$('select[name=status-sub]').val(status);
		$('select[name=status-sub]').selectpicker('refresh');
	});
	
	$('.btn-confirm').on('click', function() {
		var id = $(this).attr("id");
		var status = $(this).attr('status');
		$.ajax({
			url         : '/auth/user/code/change_status',
			method      : 'post',
			data:{
				"id":id,
				"status" : status
			},
			success:function(rs) {
				if(rs.status == 200){
					window .location.href = "/auth/user/code/list";
				} else {
					alert("Can not change status !!!");
				}
			}
		});
	});		
});		
      	
</script>
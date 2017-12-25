<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
       <div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul class="page-breadcrumb">
                	<li class="menu-lever3"><a href="">그룹 상세 (${item.isDelete ? '삭제':'운영중' }) </a></li>
                </ul>
              </div>

              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">그룹 상세</h3>
                </div>
                <form method="post" action="/auth/group/edit?id=${item.id }">
                 <div class="md-ct pull-right">
                 <c:if test="${item.isDelete == false }">
                  <button type="submit" name="deleteGroup" value="true" class="btn red pull-left trams-need-confirm" style="margin-left: 200px;width:100px " data-toggle="modal" data-trams-confirm-popup="#popup-delete-group">그룹 삭제</button>
                 </c:if>
                </div>
                </form>
                <input type="hidden" name="groupId" value="${item.id }"/>
                <div class="full border-full pad-20-30 mrb-40">
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹명 :</div>
                    <div class="col-md-3" id="div-show">
                      <span class="line-30" id="group-name">${item.name } &nbsp;</span>
                      <c:if test="${item.isDelete == false }">
                      	<button type="button" id="button-edit-show" class="btn green line-20 ">수정</button>
                      </c:if>
                    </div>
                    
                    <div class="col-md-3" id="div-edit" style="display: none">
                      <input type="text" name="groupName" value="${item.name }" style="font-size: 16px; padding-bottom: 4px; width: 120px"/>
                      <button type="button" class="btn red pad-5-10" id="button-edit-delete">x</button>
                      <button type="button" id="button-edit-group-name" class="btn green line-20 ">수정</button>
                    </div>
                    
                    <c:if test="${item.isDelete}">
	                    <div class="col-md-1 text-right line-30">그룹 삭제일 :</div>
	                     <div class="col-md-3" id="div-show">
	                      <span class="line-30" style="color:red"><fmt:formatDate value="${item.deleteDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span>
	                    </div>
                    </c:if>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹장 이름 :</div>
                    <div class="col-md-3 line-30" id="groupManagerFullname">${groupManagerName }</div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹장 아이디 :</div>
                    <div class="col-md-3"><span class="line-30" id="groupManagerUsername">${groupManagerId } &nbsp;</span>
                    <c:if test="${item.isDelete == false }">
                      	<button type="button" onclick="callApiFindMemberGroup(${item.id}, '')"  class="btn green line-20 ">그룹장 변경</button>
                    </c:if>
                     <input type="hidden" name="groupManagerId" value=""></input>
                    </div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹 생성일 :</div>
                    <div class="col-md-3 line-30"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">참여자 수 :</div>
                    <div class="col-md-3 line-30">${totalMember}</div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹 총 주행거리 :</div>
                    <div class="col-md-3 line-30"><fmt:formatNumber maxFractionDigits="3"  value="${totalMileage }"></fmt:formatNumber> km</div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹 총 CO2 감소량 :</div>
                    <div class="col-md-3 line-30"><fmt:formatNumber maxFractionDigits="3" value="${totalReduceCO2 }"></fmt:formatNumber> kg</div>
                  </div>
                  <div class="full mrb-20">
                    <div class="col-md-3 text-right line-30">그룹 소나무 수 :</div>
                    <div class="col-md-3 line-30"><fmt:formatNumber maxFractionDigits="3" value="${totalTree }"></fmt:formatNumber> 그루</div>
                  </div>
                </div>
                <div class="md-ct">
                  <div class="line-30 mrr-10">회원 리스트</div>
                  <c:if test="${item.isDelete == false }">
                  	<button type="button" class="btn green line-30 pad-0-10" onclick="callApiFindGroupMember('')">회원 추가</button>
                  </c:if>
                </div>
              </div>
              <div class="table-member-mng table-responsive">
              <form method="post" action="/auth/group/edit?id=${item.id }">
                <table class="table table-striped table-bordered table-hover text-center" id="table-member">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>이름</th>
                      <th>아이디</th>
                      <th>가입일</th>
                      <th>주행거리</th>
                      <th>CO2 감소량</th>
                      <th>소나무 수</th>
                      <c:if test="${item.isDelete == false }">
                       <th></th>
                      </c:if>
                    </tr>
                  </thead>
                  <tbody >
                  <c:forEach items="${list}" var="i" varStatus="loop">
                  	 <tr>
                      <td>${list.size() - loop.index }</td>
                      <td>${i.fullname }</td>
                      <td>${i.username == null ?i.fullname:i.username  }</td>
                      <td><fmt:formatDate value="${i.createDate }" pattern="yyyy-MM-dd"/></td>
                      <td><fmt:formatNumber value="${i.totalMileage }" maxIntegerDigits="3"></fmt:formatNumber></td>
                      <td><fmt:formatNumber value="${i.totalReduceCo2 }" maxIntegerDigits="3"></fmt:formatNumber></td>
                      <td><fmt:formatNumber value="${i.totalTree }" maxIntegerDigits="3"></fmt:formatNumber></td>
                      <c:if test="${item.isDelete == false }">
                       <td> 
                        <button type="submit" name="deleteMemberId" value="${i.id}" class="btn red trams-need-confirm pad-5-10"  data-toggle="modal" data-trams-confirm-popup="#popup-delete-member">삭제</button>
                      </td>
                      </c:if>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
               </form>
              </div>
              <div class="full text-center">
                <a href="/auth/group/list" class="btn grey pad-10-50">목록    </a>
              </div>
              <!--.table-member-mng-->
              
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
    
    <div id="popup-delete-group" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">그룹 삭제</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          
	          <div class="modal-body">
	            <p>그룹을 삭제할 경우, 그룹 회원리스트, 주행거리, CO2 감소량, 소나무 수 데이터가 삭제 됩니다. 삭제 하시겠습니까?</p>
	          </div>
	          
	          <div class="modal-footer" style="text-align: center">
	          	<button type="button" onclick="tramsNC.confirm()" class="btn red" class="btn green">확인</button>
	          	<button type="button" data-dismiss="modal" class="btn btn-default">취소</button>
	          </div>
	        </div>
	      </div>
	    </div>  
	
	<div id="popup-delete-member" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">회원 삭제</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          
	          <div class="modal-body">
	            <p>회원을 삭제할 경우 해당 회원은 그룹에서 탈퇴된 상태로 변경됩니다. 삭제 하시겠습니까?</p>
	          </div>
	          
	          <div class="modal-footer" style="text-align: center">
	          	<button type="button" onclick="tramsNC.confirm()" class="btn red" class="btn green">확인</button>
	          	<button type="button" data-dismiss="modal" class="btn btn-default">취소</button>
	          </div>
	        </div>
	      </div>
	    </div>  
    
    <div id="popup-find-member-group" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">아이디 찾기</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <div class="full">
              <div class="col-md-10">
                <input type="text" id="popup-username" value="" class="form-control pull-left"/>
              </div>
              <div class="col-md-2">
                <button class="btn green width-100" onclick="callApiFindMemberGroup(${item.id}, $('#popup-find-member-group #popup-username').val())">검색</button>
              </div>
            </div>
            <div class="full">
              <div class="col-md-6"><span class="btn grey width-100">아이디</span></div>
              <div class="col-md-6"><span class="btn grey width-100">이름</span></div>
            </div>
            <div class="full">
              <div class="col-md-12">
                <table class="table table-striped table-bordered table-hover align-middle text-center">
                  <tbody >
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div id="popup-add-member-group" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">아이디 찾기</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <div class="full">
              <div class="col-md-10">
                <input type="text" id="popup-username-group" value="" class="form-control pull-left"/>
              </div>
              <div class="col-md-2">
                <button class="btn green width-100" onclick="callApiFindGroupMember($('#popup-username-group').val())">검색</button>
              </div>
            </div>
            <div class="full">
              <div class="col-md-6"><span class="btn grey width-100">아이디</span></div>
              <div class="col-md-6"><span class="btn grey width-100">이름</span></div>
            </div>
            <div class="full">
              <div class="col-md-12">
              <form method="post" action="/auth/group/edit?id=${item.id }" id="form-add-member">
                <table class="table table-striped table-bordered table-hover align-middle text-center">
                  <tbody >
                    
                  </tbody>
                </table>
                <input type="hidden" name="addMemberId" value=""></input>
              </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
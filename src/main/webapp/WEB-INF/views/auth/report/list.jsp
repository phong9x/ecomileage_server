<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
      
<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
                <ul id="page-breadcrumb" class="page-breadcrumb"></ul>
              </div>
              <form method="get">
              <div class="member-title-table">
                <div class="title-page full border-bottom mrb-30">
                  <h3 class="bold pull-left">신고 관리</h3><span>신고 내용을 관리합니다.</span>
                </div>
                <div class="md-ct row">
				  <div class="col-md-2 pull-right">
                    <select name="status" title="상태" class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option data-hidden="true"></option>
                      <option value="1" ${param.status == 1? 'selected':'' }>검토중</option>
                      <option value="2" ${param.status == 2? 'selected':'' }>수정완료</option>
                    </select>
                  </div>
				  <div class="col-md-2 pull-right">
                    <select name="categoryId" title="카테고리" class="form-control selectpickermember text-left" onchange="this.form.submit()">
                      <option data-hidden="true"></option>
                      <option value="22" ${param.categoryId == 22? 'selected':'' }>그룹 관련 오류</option>
                      <option value="23" ${param.categoryId == 23? 'selected':'' }>시설명/건물명 수정</option>
                      <option value="24" ${param.categoryId == 24? 'selected':'' }>주행 관련 오류</option>
                      <option value="25" ${param.categoryId == 25? 'selected':'' }>대중교통 관련 오류</option>
                      <option value="26" ${param.categoryId == 26? 'selected':'' }>기타 오류</option>
                    </select>
                  </div>
				  <div class="col-md-2 pull-right">
                    <div class="input-search">
                      <input type="text" name="reporterName" value="${param.reporterName}" placeholder="신고자 ID 검색" class="form-control"/>
                      <button style ="position: absolute;width: 34px;height: 34px;top: 0;
    right: 0;display: inline-block;line-height: 32px;padding-top: 10px;text-align: center;border: none;background: none;"><i aria-hidden="true" class="fa fa-search"></i></button>
                    </div>
                  </div>
                </div>
              </div>
              </form>
              <div class="table-member-mng table-responsive">
                <table class="table table-striped table-bordered table-hover text-center">
                  <thead>
                    <tr>
                      <th>번호</th>
                      <th>카테고리</th>
                      <th>신고자 이름</th>
                      <th>신고자 연락처</th>
                      <th>신고 일시</th>
					  <th>상태</th>
                    </tr>
                  </thead>
                  <tbody>
                  	 <c:forEach items="${list }" varStatus="loop" var="i">
                  	 	<tr>
	                      <td>${totalCount - loop.index  - (currentIndex-1)*size}</td>
	                      <td>${i.category.name }</td>
	                      <td><a href="/auth/report/edit?id=${i.id }">${i.user.username }</a></td>
	                      <td>${i.user.phone }</td>
	                      <td><fmt:formatDate value="${i.createDate }" pattern="yyyy-MM-dd"/></td>
						  <td>
						  	<c:if test="${i.status == 1}">검토중</c:if>
						  	<c:if test="${i.status == 2}">수정완료</c:if>
						  </td>
	                    </tr>
                  	 </c:forEach>
                    
                  </tbody>
                </table>
              </div>
             
              <util:paging />
            </div>
          </div>
        </div>
      </div>
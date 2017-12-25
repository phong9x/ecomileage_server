<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWUUfAbTD7UFu9Fagk6CLHX6zAR62ebPQ&callback=initMap"></script>
<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
              	<ul class="page-breadcrumb">
                	<li class="menu-lever3"><a href="">일시후원 상세</a></li>
                </ul>
              </div>
              <form class="form_sample_3">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">일시후원 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">ID :</div>
                      <div class="col-md-3 line-30">${item.user.username }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">이름 :</div>
                      <div class="col-md-10 line-30">${item.fullname }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">휴대폰 번호 :</div>
                      <div class="col-md-10 line-30">${item.phone }</div>
                    </div>
                    
                    <div class="full mrb-10">
	                  <div class="col-md-2 text-right line-110">주소 :</div>
	                  <div class="col-md-10 line-30">
	                    <p>${item.postcode }</p>
	                    <p>${item.address }</p>
	                    <p>${item.addressDetail }</p>
	                  </div>
	                </div>
                    
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">이메일 주소:</div>
                      <div class="col-md-10 line-30">${item.email }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">후원 날짜:</div>
                      <div class="col-md-10 line-30"><fmt:formatDate value="${item.createDate }"  pattern="yyyy-MM-dd"></fmt:formatDate></div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">후원 금액:</div>
                      <div class="col-md-10 line-30">${item.money }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">결제 수단:</div>
                      <div class="col-md-10 line-30">
                      		<c:if test="${item.paymentMethod == 'wcard'}">카드 결제</c:if>
	                      	<c:if test="${item.paymentMethod == 'bank'}">실시간 계좌이체</c:if>
	                      	<c:if test="${item.paymentMethod == 'kakaoPay'}">카카오 페이 </c:if>
                      </div>
                    </div>
                  </div>
                  
                   <div class="full text-center">
                      <a href="/auth/sponsor/tem/list" type="button" class="btn green pad-8-50 link-back" >목록</a>
                    </div>
                </div>
              </form>
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>

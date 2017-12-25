<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWUUfAbTD7UFu9Fagk6CLHX6zAR62ebPQ&callback=initMap"></script>
<div class="page-content-wrapper">
        <div class="page-content">
          <div class="container">
            <div class="main-body-content member-page">
              <div class="page-bar">
              	<ul class="page-breadcrumb">
                	<li class="menu-lever3"><a href="">포인트후원 상세</a></li>
                </ul>
              </div>
              <form class="form_sample_3">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">포인트후원 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">ID :</div>
                      <div class="col-md-3 line-30">${item.user.username }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">이름 :</div>
                      <div class="col-md-10 line-30">${item.user.fullname }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">휴대폰 번호 :</div>
                      <div class="col-md-10 line-30">${item.user.phone }</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">이메일 주소:</div>
                      <div class="col-md-10 line-30">${item.user.email }</div>
                    </div>
                  </div>
                  
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">후원 날짜 :</div>
                      <div class="col-md-3 line-30"><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/></div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">총 후원 마일리지 :</div>
                      <div class="col-md-3 line-30"> <fmt:formatNumber maxFractionDigits="3" value="${item.money }"></fmt:formatNumber> P</div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-right line-30 bold">이전 마일리지 :</div>
                      <div class="col-md-1 line-30"> <fmt:formatNumber maxFractionDigits="3" value="${item.previousPoint }"></fmt:formatNumber> P</div>
                      <div class="col-md-1 text-right line-30 bold">주행 마일리지 :</div>
                      <div class="col-md-1 line-30"> <fmt:formatNumber maxFractionDigits="3" value="${item.bikePoint }"></fmt:formatNumber> P</div>
                      <div class="col-md-1 text-right line-30 bold">환승 마일리지 :</div>
                      <div class="col-md-1 line-30"> <fmt:formatNumber maxFractionDigits="3" value="${item.tranferPoint }"></fmt:formatNumber> P</div>
                    </div>
                  </div>
                  
                   <div class="full text-center">
                      <a href="/auth/sponsor/point/list" type="button" class="btn green pad-8-50 link-back" >목록</a>
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

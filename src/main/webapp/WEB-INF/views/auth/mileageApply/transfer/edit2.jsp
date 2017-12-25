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

          <form id="form_sponsor_edit1" method="post">
            <div class="member-title-table">
              <div class="title-page full border-bottom mrb-30">
                <h3 class="bold pull-left">물품 신청 상세</h3>
              </div>
              
              <div class="full border-full pad-20-30 mrb-40">
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">ID :</div>
                  <div class="col-md-8 line-30">${item.user.username }</div>
                 
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">이름 :</div>
                  <div class="col-md-10 line-30">${item.user.fullname }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
                  <div class="col-md-10 line-30">${item.user.phone }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">쿠폰 받을 휴대폰 번호 :</div>
                  <div class="col-md-10 line-30">${item.phone }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-110">주소 :</div>
                  <div class="col-md-10 line-30">
                    <p>${item.postCode }</p>
                    <p>${item.address }</p>
                    <p>${item.addressDetail }</p>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">신청 상품권 :</div>
                  <div class="col-md-10 line-30">${item.category.name }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">사용 마일리지 :</div>
                  <div class="col-md-10 line-30">${item.pointUse } p</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">요청사항  :</div>
                  <div class="col-md-10 line-30">${item.memo }</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">발송 상태 :</div>
                  <div class="col-md-10 line-30">${i.typeSend == 0 ? '발송전' : '발송완료'}</div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">상품권 정보 :</div>
                  <div class="col-md-10 text-right line-30">
                  		<div class="full">
                  			<div class="col-md-2 text-left line-30">쿠폰번호  :</div>
                  			<div class="col-md-2 line-30">${item.couponNo}</div>
                  		</div>
                  		<div class="full">
                  			<div class="col-md-2 text-left line-30">유효기간  :</div>
                  			<div class="col-md-2 line-30"><fmt:formatDate value="${item.expiredCouponTime }" pattern="yyyy-MM-dd"/></div>
                  		</div>
                  		<div class="full">
                  			<div class="col-md-2 text-left line-30">발송날짜  :</div>
                  			<div class="col-md-2 line-30"><fmt:formatDate value="${item.sendCouponTime }" pattern="yyyy-MM-dd"/></div>
                  		</div>
                  </div>
                </div>
              </div>
              <div class="full text-center">
                  <button type="button" class="btn pad-8-50 transfer_mileage-back">목록</button>
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
  
  <script type="text/javascript">
	  $('.transfer_mileage-back').on('click', function() {
		  window.location.href = "/auth/mileageApply/transfer/list";
      });
	  
	  $('.transfer_mileage-confirm').on('click', function() {
		  
	  });
  </script>
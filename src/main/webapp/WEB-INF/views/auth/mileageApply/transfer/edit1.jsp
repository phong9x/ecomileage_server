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
                  <div class="col-md-10 line-30 couponPhones">${item.phone }</div>
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
                  <div class="col-md-10 line-30 categoryName">${item.category.name }</div>
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
                  			<div class="col-md-2 text-right line-30">상품권 번호  :</div>
                  			<div class="col-md-2 line-30"><input type="text" id="couponNo" name="couponNo" class="form-control"/></div>
                  		</div>
                  		<div class="full">
                  			<div class="col-md-2 text-right line-30">유효기간  :</div>
                  			<div class="col-md-2 line-30">
                  				<input name="expiredCouponTime" type="text" id="expiredCouponTimes" data-date-format="yyyy-mm-dd" class="form-control date-picker"/>
                  			</div>
                  		</div>
                  </div>
                </div>
              </div>
              <div class="full text-center">
                  <button type="button" class="btn pad-8-50 transfer_mileage-back">목록</button>
                  <button type="button" class="btn green pad-8-50 send_coupon">발송하기</button>
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
  
  <div id="popupsend_coupon" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
     <div class="modal-dialog">
       <div class="modal-content">
         <div class="modal-header"><strong class="modal-title">상품권 발송</strong>
           <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
         </div>
         <div class="modal-body">
           <div class="form-search-wrapper">
             <form action="#" class="form-search form-horizontal sb-container-style-1">
               <div class="form-body">
                 <div class="form-group mrb-30">
                   <div class="col-md-3 line-30"><span>핸드폰 번호  :</span></div>

                   <div class="col-md-9">
                     <input id="couponPhone" type="search" name="" class="form-control"/>
                   </div>
                 </div>
                 <div class="full border-full pad-10-30 mrb-30">
                   <p>에코마일리지 [<span id="categoryName"></span>] 발송드립니다.</p>
                   <p>상품권 번호는 [<span id="couponNumber"></span>]<span>입니다.</span></p>
                   <p>유효기간은 [<span id="expiredCouponTime"></span>] 까지 입니다.</p>
                 </div>

                 <div class="form-group text-center">
                   <div class="col-md-12">
                   	<button type="button" class="btn green pad-8-50 btn-send-coupon">발송하기</button>
                   </div>
                 </div>
               </div>
             </form>
             <!-- .form-search-->
           </div>
           <!-- .form-search-wrapper-->
           
           
           <!--.table-wrapper-->
           
         </div>
       </div>
     </div>
   </div>
<script type="text/javascript">
  $('.transfer_mileage-back').on('click', function() {
	  window.location.href = "/auth/mileageApply/transfer/list";
     });
  
  $('.send_coupon').on('click', function() {
	  	var couponnumber   = $('#couponNo').val();
	    var expiredCouponTime  = $('#expiredCouponTimes').val();
	    var categoryName = $('.categoryName').text();
	    var couponPhone = $('.couponPhones').text();
	    $("#couponNumber").text(couponnumber);
	    $("#expiredCouponTime").text(expiredCouponTime);
	    $("#categoryName").text(categoryName);
	    $("#couponPhone").val(couponPhone);
	    $('#popupsend_coupon').find('.btn-send-coupon').attr('data-couponnumber',couponnumber);
	    $('#popupsend_coupon').find('.btn-send-coupon').attr('data-expiredCouponTime',expiredCouponTime);
	    $('#popupsend_coupon').modal('show');
     });
  
  $('.btn-send-coupon').on('click', function() {
	  	var id   = ${item.id};
	  	var couponNumber = $(this).attr('data-couponnumber');
	  	var expiredCouponTime = $(this).attr('data-expiredCouponTime');
	  	
	  	$.ajax({
			url         : '/ajax/mileageApply/transfer/change_typeSend',
			method      : 'post',
			data:{
				"id":id,
				"couponNumber":couponNumber,
				"expiredCouponTime":expiredCouponTime
			},
			success:function(rs) {
				if(rs.code == 200){
					window.location.href = "/auth/mileageApply/transfer/list";
				}else{
					alert("Can not request!")
				}
			}
		});
	});
</script>
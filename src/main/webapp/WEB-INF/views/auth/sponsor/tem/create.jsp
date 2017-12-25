<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<div class="page-content-wrapper">
    <div class="page-content">
      <div class="container">
        <div class="main-body-content member-page">
          <div class="page-bar">
            <ul class="page-breadcrumb">
             	<li class="menu-lever3"><a href="">후원 내역 추가</a></li>
             </ul>
          </div>

          <form id="form_sponsor_tem_create" method="post">
            <div class="member-title-table">
              <div class="title-page full border-bottom mrb-30">
                <h3 class="bold pull-left">후원 내역 추가</h3>
              </div>
              <div class="full border-full pad-20-30 mrb-40">
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">ID :</div>
                  <div class="col-md-2 line-30"><input type="text" name="username" id="usernameSearch" class="form-control"/></div>
                  <div class="col-md-1">
                  	<button type="button" class="btn green" id="button-search-user" >찾기</button>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">이름 :</div>
                  <div class="col-md-2 line-30"><input type="text" name="fullname" class="form-control"/></div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
                  <div class="col-md-2 line-30"><input type="text" name="phone" class="form-control"/></div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">주소 :</div>
                  <div class="col-md-1 line-30"><input type="text" name="postcode" class="form-control"/></div>
                  <div class="col-md-2"><button type="button" data-toggle="modal" data-target="#popup-postcode-member" class="btn green pull-left btn-open-popup">우편번호 검색</button></div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30"></div>
                  <div class="col-md-3 line-30"><input type="text" name="address" class="form-control"/></div>
                  <div class="col-md-3 line-30"><input type="text" name="addressDetail" class="form-control"/></div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">이메일 주소 :</div>
                  <div class="col-md-2 line-30"><input type="text" name="email" class="form-control"/></div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">후원 날짜 :</div>
                  <div class="col-md-2 line-30">
                  <input name="createDates" type="text" data-date-format="yyyy-mm-dd" class="form-control date-picker"/></div>
                </div>
                
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">후원 금액 :</div>
                  <div class="col-md-2 line-30"><input type="number" name="money" class="form-control"/></div>
                </div>

                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">결제 수단 :</div>
                  <div class="col-md-2">
                    <select name="paymentMethod" title="결제 수단" class="form-control selectpickermember text-left">
                      <option  value="" >결제 수단</option>
                      <option value="wcard">카드 결제</option>
                      <option value="bank">실시간 계좌이체</option>
                      <option value="kakaoPay">카카오 페이 </option>
                    </select>
                  </div>
                </div>
                
                <div class="full text-center">
                  <a href="/auth/sponsor/tem/list" type="button" class="btn red pad-8-50 link-back" >목록</a>
                  <button type="submit" class="btn green pad-8-50 sponsor-create-confirm">등록</button>
                </div>
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
	  $('#membershipFeeId').on('change', function() {
		  var membershipFeeIdNEW = this.value;
		  
		  if(membershipFeeIdNEW == 7) {
			  $('#membershipFeeOther').prop('disabled', false);
			  $('#membershipFeeOther').val(membershipFeeOther);
		  } else {
			  $('#membershipFeeOther').prop('disabled', true);
			  $('#membershipFeeOther').val("");
		  }
	});
	  
	  $('#isReviceReceipt').on('change', function() {
		  var isReviceReceipt = this.value;
		  if(isReviceReceipt == false) {
			  if(!$('.full-user-name').hasClass("hidden")){
				  $('.full-user-name').addClass("hidden");
			  }
		  } else {
			  $('.full-user-name').removeClass("hidden");
		  }
	  });
	  
	  $('#form_sponsor_tem_create').on('submit', function(evt) {
			evt.preventDefault();
			var form        = $(this)[0];
			var formData    = new FormData(form);
			
          $.ajax({
              url         : '/ajax/sponsor/tem/create',
              method      : 'post',
              processData : false,
              contentType : false,
              data        : formData
          })
          .then(function(res) {
          		console.log(res);
          		if (res.code == 200) {
          			window.location = '/auth/sponsor/tem/list';
				} else {
					alert(res.message);
				}
          		
          }, function(err) {
          		alert("not ok....");
          });
		});
	  
  </script>
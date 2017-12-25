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

          <form id="form_sponsor_create" method="post">
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
                  <div class="col-md-2 text-right line-30">회비유형 :</div>
                  <div class="col-md-2">
                    <select name="membershipFeeTypeId" title="월회비" class="form-control selectpickermember text-left">
                      <option data-hidden="true"></option>
                      <c:forEach items="${listMembershipFeeType}" var="i">
                      	<option value="${i.id }">${i.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-2 text-right line-30">후원 시작 날짜 :</div>
                  <div class="col-md-2">
                        <input name="sponsorDate" type="text" data-date-format="yyyy-mm-dd" class="form-control date-picker"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">회비금액 :</div>
                  <div class="col-md-2">
                    <select name="membershipFeeId" id="membershipFeeId"  title="선택..." class="form-control selectpickermember text-left">
                      <option data-hidden="true"></option>
                      <c:forEach items="${listMembershipFee}" var="j">
                      	<option value="${j.id }">${j.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-2 line-30">
                    <input type="text" disabled="disabled" name="membershipFee" id="membershipFee" class="form-control width-90"/><span class="pull-right">원</span>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">은행명 :</div>
                  <div class="col-md-2">
                    <select name="bankId" class="form-control selectpickermember text-left">
                      <c:forEach items="${listBank}" var="k">
                      	<option value="${k.id }">${k.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">계좌번호 :</div>
                  <div class="col-md-2">
                    <input type="text" name="accountNumber" class="form-control"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">예금주 :</div>
                  <div class="col-md-2">
                    <input type="text" name="accountName" class="form-control"/>
                  </div>
                  <div class="col-md-2">  </div>
                  <div class="col-md-2 text-right line-30">예금주 생년월일(8자) :</div>
                  <div class="col-md-2">
                    <input name="dayOfBirth" type="text" data-date-format="yyyy-mm-dd" class="form-control date-picker"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">기부금 영수증 발행여부 :</div>
                  <div class="col-md-2">
                    <select name="isReviceReceipt" id="isReviceReceipt" class="form-control selectpickermember text-left">
                      <option value="1">발행</option>
                      <option value="0">미발행</option>
                    </select>
                  </div>
                </div>
                <div class="full mrb-10 full-user-name">
                  <div class="col-md-2 text-right line-30">수령인 이름 :</div>
                  <div class="col-md-2">
                  	 <input type="text" name="reviceUserName" class="form-control"/>
                  </div>
                  <div class="col-md-2">  </div>
                  <div class="col-md-2 text-right line-30">수령인 주민등록번호 :</div>
                  <div class="col-md-1">
                    <input  type="text" name="identityCardNumber1" class="form-control width-80"/>
                    <span class="line-30 pull-right">-</span>
                  </div>
                  <div class="col-md-1">
                    <input type="text" name="identityCardNumber2" class="form-control width-80"/>
                  </div>
                </div>
                <div class="full text-center">
                  <button type="button" class="btn red pad-8-50 sponsor-create-back">목록</button>
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
			  $('#membershipFee').prop('disabled', false);
		  } else {
			  $('#membershipFee').prop('disabled', true);
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
	  
	  
	  $('.sponsor-create-back').on('click', function() {
		  window.location.href = "/auth/sponsor/register/list";
      });
	  
	  
	  $('#form_sponsor_create').on('submit', function(evt) {
			evt.preventDefault();
			var form        = $(this)[0];
			var formData    = new FormData(form);
			
          $.ajax({
              url         : '/ajax/sponsor/create',
              method      : 'post',
              processData : false,
              contentType : false,
              data        : formData
          })
          .then(function(res) {
          		console.log(res);
          		if (res.code == 200) {
          			window.location = '/auth/sponsor/register/list';
				} else {
					alert("Please check information again.");
				}
          		
          }, function(err) {
          		alert("not ok....");
          });
		});
	  
  </script>
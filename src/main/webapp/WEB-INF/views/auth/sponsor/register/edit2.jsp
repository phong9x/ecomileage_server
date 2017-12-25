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
                <h3 class="bold pull-left">정기후원 상세</h3>
              </div>
              <div class="full border-full pad-20-30 mrb-40">
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">ID :</div>
                  <div class="col-md-6 line-30">${item.user.username }</div>
                  <div class="col-md-2 text-right line-30" style="color: red;"><fmt:formatDate value="${item.deleteDate}" pattern="yyyy-MM-dd"/></div>
                  <div class="col-md-2 line-30" style="color: red;">후원중단</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">이름 :</div>
                  <div class="col-md-10 line-30">${item.fullname }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
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
                  <div class="col-md-2 text-right line-30">이메일 주소 :</div>
                  <div class="col-md-10 line-30">${item.email }</div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">회비유형 :</div>
                  <div class="col-md-2">
                    <select name="membershipFeeTypeId" title="월회비" class="form-control selectpickermember text-left">
                      <option data-hidden="true"></option>
                      <c:forEach items="${listMembershipFeeType}" var="i">
                      	<option ${item.category.id == i.id ? 'selected': '' } value="${i.id }">${i.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-2 text-right line-30">후원 시작 날짜 :</div>
                  <div class="col-md-2">
                        <input name="sponsorDate" type="text" data-date-format="yyyy-mm-dd" value="<fmt:formatDate value="${item.sponsorDate}" pattern="yyyy-MM-dd"/>" class="form-control date-picker"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">회비금액 :</div>
                  <div class="col-md-2">
                    <select name="membershipFeeId" id="membershipFeeId" data-membershipFeeId="${item.category2.id}" data-membershipFee="${item.membershipFee}" title="선택..." class="form-control selectpickermember text-left">
                      <option data-hidden="true"></option>
                      <c:forEach items="${listMembershipFee}" var="j">
                      	<option ${item.category2.id == j.id ? 'selected': '' } value="${j.id }">${j.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-2 line-30">
                    <input type="text" ${item.category2.id != 7 ? 'disabled="disabled"': '' } name="membershipFee" id="membershipFee" value="${item.membershipFee}" class="form-control width-90"/><span class="pull-right">원</span>
                  	<input type="hidden" name="id" value="${item.id }" class="form-control"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">은행명 :</div>
                  <div class="col-md-2">
                    <select name="bankId" class="form-control selectpickermember text-left">
                      <c:forEach items="${listBank}" var="k">
                      	<option ${item.category3.id == k.id ? 'selected': '' } value="${k.id }">${k.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">계좌번호 :</div>
                  <div class="col-md-2">
                    <input type="text" name="accountNumber" value="${item.accountNumber }" class="form-control"/>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">예금주 :</div>
                  <div class="col-md-2">
                    <input type="text" name="accountName" value="${item.accountName }" class="form-control"/>
                  </div>
                  <div class="col-md-2">  </div>
                  <div class="col-md-2 text-right line-30">예금주 생년월일(8자) :</div>
                  <div class="col-md-2">
                    <p class="form-control">${item.dayOfBirth }</p>
                  </div>
                </div>
                <div class="full mrb-10">
                  <div class="col-md-2 text-right line-30">기부금 영수증 발행여부 :</div>
                  <div class="col-md-2">
                    <select name="isReviceReceipt" id="isReviceReceipt" class="form-control selectpickermember text-left">
                      <option ${item.isReviceReceipt == true ? 'selected': '' } value="1">발행</option>
                      <option ${item.isReviceReceipt == false ? 'selected': '' } value="0">미발행</option>
                    </select>
                  </div>
                </div>
                <div class="full mrb-10 full-user-name ${item.isReviceReceipt == false ? 'hidden': '' }">
                  <div class="col-md-2 text-right line-30">수령인 이름 :</div>
                  <div class="col-md-2">
                  	 <input type="text" disabled="disabled" name="reviceUserName" value="${item.reviceUserName }" class="form-control"/>
                  </div>
                  <div class="col-md-2">  </div>
                  <div class="col-md-2 text-right line-30">수령인 주민등록번호 :</div>
                  <div class="col-md-1">
                    <input disabled="disabled" type="text" value="${fn:split(item.identityCardNumber, '-')[0]}" class="form-control width-80"/>
                    <span class="line-30 pull-right">-</span>
                  </div>
                  <div class="col-md-1">
                    <input disabled="disabled" type="text" value="${fn:split(item.identityCardNumber, '-')[1]}" class="form-control width-80"/>
                  </div>
                </div>
                <div class="full text-center">
                  <button type="button" class="btn red pad-8-50 sponsor-detail1-back">목록</button>
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
		  var membershipFeeIdOLD = $(this).attr("data-membershipFeeId");
		  var membershipFee = $(this).attr("data-membershipFee");
		  
		  if(membershipFeeIdNEW == 7) {
			  $('#membershipFee').prop('disabled', false);
			  $('#membershipFee').val(membershipFee);
		  } else {
			  $('#membershipFee').prop('disabled', true);
			  $('#membershipFee').val("");
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
	  
	  
	  $('.sponsor-detail1-back').on('click', function() {
		  window.location.href = "/auth/sponsor/register/list";
      });
	  
	  
	  
	  
  
  </script>
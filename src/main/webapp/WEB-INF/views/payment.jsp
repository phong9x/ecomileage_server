<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
<script type="text/javascript">
	$( document ).ready(function() {
		var paymentMethod = '${paymethod }';
		if(paymentMethod == 'kakaoPay'){
			kakaoPay()
		}else{
			$('#iniForm').submit();
		}
	});
	
	 function kakaoPay() {
	      var url_post = "/ajax/kakaopay/txnid";

	      $.ajax({
	        type: "POST",
	        url: url_post,
	        data: $("#kakaoForm").serialize(),
	        success: function(response) {
	          if (response.code == 0) {
	            cnspay(response.data);
	          } else {
	            alert("Error: " + response.message);
	          }
	        },
	        error: function(err) {
	          toggleLoading();
	          alert("Error: " + err.statusText);
	        }
	      });
	    }
	 
	 /**
	    cnspay  를 통해 결제를 시작합니다.
	    */
	    // DLP 결제창 호출 함수
	 function cnspay(data) {
	      // TO-DO : 가맹점에서 해줘야할 부분(TXN_ID)과 KaKaoPay DLP 호출 API
	      // 결과코드가 00(정상처리되었습니다.)
	      //alert(data.RESULT_CODE);
	      if (data.RESULT_CODE == '00') {

	        // TO-DO : 가맹점에서 해줘야할 부분(TXN_ID)과 KaKaoPay DLP 호출 API
	        kakaopayDlp.setTxnId(data.TXN_ID);

	        //DLP에 휴대전화 번호를 미리 셋팅 할 수 있음. 사용 안할 시 사용자가 직접 입력.
	        //kakaopayDlp.setChannelType('WPM', 'TMS'); // PC결제
	        kakaopayDlp.setChannelType('MPM', 'WEB'); // 모바일 웹(브라우저)결제
	        //kakaopayDlp.addRequestParams({ MOBILE_NUM : '010-1234-5678'}); // 초기값 세팅

	        //크롬엔진 버그 회피를 위한 dummy page가 필요할때 설정
	        //kakaopayDlp.setDummyPageFlag(true);

	        //div 태그(kakaopay_layer)에 가맹점 자체 css를 적용옵션
	        //kakaopayDlp.setCustomTargetLayerCssFlag(true);     

	        //DLP 호출
	        kakaopayDlp.callDlp('kakaopay_layer', document.kakaoForm, submitFunc);

	      } else {
	        toggleLoading();
	        alert('[RESULT_CODE] : ' + data.RESULT_CODE + '\n[RESULT_MSG] : ' + data.RESULT_MSG);
	      }

	    }

	
	    var submitFunc = function cnspaySubmit(data) {
	      // 결과코드가 00(정상처리되었습니다.) 정상일 경우에는 TargetForm을 Submit
	      // DLP창 결과코드에 대한 부분은 별첨의 9.5의 DLP창 결과코드표를 참조
	      if (data.RESULT_CODE === '00') {
	        // 매뉴얼 참조하여 부인방지코드값 관리
	        document.payForm.submit();
	      } else if (data.RESULT_CODE === 'KKP_SER_002') {
	        // X버튼 눌렀을때의 이벤트 처리 코드 등록
	        console.log('[RESULT_CODE] : ' + data.RESULT_CODE + '\n[RESULT_MSG] : ' + data.RESULT_MSG);
	        $('#popupCancelPayment').modal("show");
	      } else {
	        // 결과코드가 00이 아니면 비정상처리이므로 Alert을 수행 
	        //비정상일 경우에는 RESULT_CODE와 RESULT_MSG를 받음
	        alert('[RESULT_CODE] : ' + data.RESULT_CODE + '\n[RESULT_MSG] : ' + data.RESULT_MSG);
	      }
	      toggleLoading();
	    };
	 
	    function toggleLoading() {
	        if ($('body').find('#ajax_loading').length) {
	          $('#ajax_loading').css('display') == 'none' ?
	            $('#ajax_loading').css('display', '') :
	            $('#ajax_loading').css('display', 'none');
	        } else {
	          var html = '<div id="ajax_loading" class="loading-page">';
	          html += '<div class="img-loading">';
	          html += '<img src="/theme/admin/assets/images/loading.svg" alt="loading"/>';
	          html += '</div>';
	          html += '</div>';

	          $('body').append(html);
	        }
	      }
</script>
<body>

<form id="iniForm"  method="post" action="https://mobile.inicis.com/smart/${paymethod }/" accept-charset="euc-kr">
            <input type="hidden" name="P_GOODS" value="후원 금액" />
            <input type="hidden" name="P_AMT" value="${P_AMT }" />
            <input type="hidden" name="P_UNAME" value="${P_UNAME }" />
            <input type="hidden" name="P_MOBILE" value="${P_MOBILE }" />
            <input type="hidden" name="P_EMAIL" value="${P_EMAIL}" />
            <input type="hidden" name="P_MID" value="${P_MID }" />
            <input type="hidden" name="P_OID" value="${P_OID }" />
            <input type="hidden" name="P_NOTI" value="${P_NOTI }" />
            
            <c:if test="${paymethod == 'bank' }">
            	<input type="hidden" name="P_RETURN_URL" value="${siteDomain}/payment/return_bank?P_OID=${P_OID}"/>
            	<input type="hidden" name="P_NOTI_URL" value="${siteDomain}/payment/noti_bank"/>
            </c:if>
            
            <c:if test="${paymethod == 'wcard' }">
            	<input type="hidden" name="P_NEXT_URL" value="${siteDomain}/payment/next_card"/>
            	<input type="hidden" name="P_RESERVED" value="twotrs_isp=Y&amp;block_isp=Y&amp;twotrs_isp_noti=N&amp;apprun_check=Y" />
            </c:if>
            
            <input type="hidden" name="P_HPP_METHOD" value="1" />
</form>

<form name="kakaoForm" id="kakaoForm" action="/payment/kakaopay/result" method="post" accept-charset="">
            <input type="hidden" name="PayMethod" value="KAKAOPAY" />
            <fmt:formatNumber pattern="#,##0" value="${kakaoPay.Amt}" var="GoodsPrice" />
            <input type="hidden" name="GoodsName" value="헬로코인 ${GoodsPrice}원" />
            <input type="hidden" name="productName" value="헬로코인 ${GoodsPrice}원" />
            <input type="hidden" name="Amt" value="${kakaoPay.Amt}" />
            <input type="hidden" name="amount" value="${kakaoPay.Amt}" />
            <input type="hidden" name="GoodsCnt" value="1" />
            <input type="hidden" name="MID" value="${kakaoPay.MID}" />
            <input type="hidden" name="merchantID" value="${kakaoPay.MID}" />
            <input type="hidden" name="AuthFlg" value="10" />
            <input type="hidden" name="EdiDate" value="${kakaoPay.EdiDate}" />
            <input type="hidden" name="EncryptData" value="${kakaoPay.EncryptData}" />
            <input type="hidden" name="BuyerEmail" value="${kakaoPay.BuyerEmail}" />
            <input type="hidden" name="BuyerName" value="${kakaoPay.BuyerName}" />
            <input type="hidden" name="certifiedFlag" value="CN" />
            <input type="hidden" name="currency" value="KRW" />
            <input type="hidden" name="merchantEncKey" value="${kakaoPay.merchantEncKey}" />
            <input type="hidden" name="merchantHashKey" value="${kakaoPay.merchantHashKey}" />
            <input type="hidden" name="requestDealApproveUrl" value="${kakaoPay.requestDealApproveUrl}" />
            <input type="hidden" name="prType" value="${kakaoPay.prType}" />
            <input type="hidden" name="channelType" value="${kakaoPay.channelType}" />
            <input type="hidden" name="merchantTxnNum" value="${kakaoPay.merchantTxnNum}" />
            <input type="hidden" name="OrderCheckYn" value="N" />
            <input type="hidden" name="OrderBirthDay" value="${kakaoPay.OrderBirthDay}" />
            <input type="hidden" name="OrderName" value="${kakaoPay.BuyerName}" />
            <input type="hidden" name="OrderTel" value="" />

            <!-- DLP호출에 대한 응답 -->
            <input type="hidden" name="SPU" value="" />
            <input type="hidden" name="SPU_SIGN_TOKEN" value="" />
            <input type="hidden" name="MPAY_PUB" value="" />
            <input type="hidden" name="NON_REP_TOKEN" value="" />
            <input type="hidden" name="FnNo" value="" />
</form>
</body>
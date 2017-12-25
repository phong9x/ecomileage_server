<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<s:message code='username' var="placeholder_username"/>
<s:message code='password' var="placeholder_password"/>

 <div class="login">
      <div class="form-login-wrapper">
        <div class="container">
          <form method="POST"  class="form-login">
            <div class="form-header clearfix" style=" font-size: 15px;">Ecomileage</div>
            <div class="form-group ">
              <div class="inputbox-wrapper">
                <input id="username" type="text" name="username" value="" placeholder="아이디 입력"  class="inputbox required"/>
              </div>
            </div>

            <div class="form-group clearfix">
              <div class="inputbox-wrapper">
                <input id="password" type="password" name="password" value="" placeholder="비밀번호 입력"  class="inputbox required"/><i class="inputbox-icon fa fa-eye"></i>
              </div>
            </div>

            <div class="form-group clearfix">
              <button type="submit" class="btn-login"><s:message code="button.login"></s:message></button>
            </div>
			<c:if test="${login_success==0 }">
            	<div class="form-group clearfix"><span class="error-message">일치하는 계정이 없습니다 </span></div>
            </c:if>
          </form>
        </div>
        <!-- form-login-->
      </div>
      <!-- .form-login-wrapper-->
    </div>
    <!-- .login-->
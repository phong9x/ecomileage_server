<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<html>
	<head>
	    <meta charset="utf-8">
	    <title><tiles:insertAttribute name="title" /></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <!-- Web Fonts-->
	
	    <!-- Vendor CSS (GLOBAL MANDATORY STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/font-awesome/css/font-awesome.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/simple-line-icons/simple-line-icons.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/uniform/css/uniform.default.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
	
	    <!-- Vendor CSS (GLOBAL PAGE LEVEL STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/select2/select2.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.min.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/plugins/owl.carousel/assets/owl.carousel.css">
	
	    <!-- Vendor CSS (THEME STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/css/components.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/global/css/plugins.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/admin/layout/css/layout.css">
	    <link rel="stylesheet" type="text/css" href="/theme/libs/metronic-admin/admin/layout/css/themes/darkblue.css">
	
	    <!-- Theme CSS-->
	    <link rel="stylesheet" type="text/css" href="/theme/assets/css/bootstrap-select.min.css">
		<link rel="stylesheet" type="text/css" href="/theme/assets/css/custom.css">
		<link rel="stylesheet" type="text/css" href="/theme/assets/css/admin.css">
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file://-->
	    <!--if lt IE 9
	    script(src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js')
	    script(src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js')
	    -->
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery-migrate.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery-ui/jquery-ui.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery.blockui.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery.cokie.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/uniform/jquery.uniform.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	</head>
	<body class="page-header-fixed page-quick-sidebar-over-content" onload="startTime()">
		
		<tiles:insertAttribute name="menu" />
	    <tiles:insertAttribute name="heading" />
	    <div class="clearfix"></div>
		<div class="page-container">
            <tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />

	<!-- #popupInputAllRequiredThings-->
	     <div id="popupInputAllRequiredThings" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <h4><strong><s:message code="message.required" /></strong>
	            </h4>
	          </div>
	          <div class="modal-footer">
	            <button type="button" data-dismiss="modal" class="btn red"><s:message code="button.confirm" /></button>
	          </div>
	        </div>
	      </div>
	    </div>
	    <!-- #popupConfirmDelete-->
	    <div id="popupConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <h4><strong><s:message code="button.delete" /></strong>
	              <!-- Are you sure you want to delete?-->
	            </h4>
	          </div>
	          <div class="modal-footer">
	          	<button type="button" onclick="tramsNC.confirm()" class="btn red"><s:message code="button.confirm" /></button>
	            <button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="button.cancel" /></button>
	          </div>
	        </div>
	      </div>
	    </div>
	    <!-- #popupConfirmEdit-->
	    <div id="popupConfirmEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <h4><strong><s:message code="message.edit" /></strong>
	              <!-- Are you sure you want to delete?-->
	            </h4>
	          </div>
	          <div class="modal-footer">
	          	<button type="button" onclick="tramsNC.confirm()" class="btn red"><s:message code="button.confirm" /></button>
	            <button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="button.cancel" /></button>
	          </div>
	        </div>
	      </div>
	    </div>
	    
	    <div id="popupLogout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <h4><strong>로그아웃 되었습니다</strong>
	            </h4>
	          </div>
	          <div class="modal-footer">
	            <button type="button" data-dismiss="modal" class="btn red"><s:message code="button.confirm" /></button>
	          </div>
	        </div>
	      </div>
	    </div>
		
		
	<div id="popup-find-user" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="bold">아이디 찾기</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <div class="full">
              <div class="col-md-10">
                <input type="text" id="popup-username" value="" class="form-control pull-left"/>
              </div>
              <div class="col-md-2">
                <button class="btn green width-100" id="button-popup-search-user">검색</button>
              </div>
            </div>
            <div class="full">
              <div class="col-md-6"><span class="btn grey width-100">아이디</span></div>
              <div class="col-md-6"><span class="btn grey width-100">이름</span></div>
            </div>
            <div class="full">
              <div class="col-md-12">
                <table class="table table-striped table-bordered table-hover align-middle text-center">
                  <tbody >
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
	
	<div id="popup-postcode-member" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">우편번호 찾기</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          <div class="modal-body">
	            <p class="pdl-10">주소를 검색해주세요.</p>
	            <div class="full">
	              <div class="col-md-10">
	                <input type="text" name="key" value="" class="form-control pull-left"/>
	              </div>
	              <div class="col-md-2">
	                <button id="button-search-address" class="btn green width-100">검색</button>
	              </div>
	            </div>
	            <div class="col-md-12">
	                <div class="alert">검색어 예 : 도로명(반포대로 58), 건물명(독립기념관), 지번(삼성동 25)</div>
	            </div>
	            <div class="full">
	              <div class="col-md-9"><span class="btn green width-100">주소</span></div>
	              <div class="col-md-3"><span class="btn green width-100">우편번호</span></div>
	            </div>
	            <div class="full">
	              <div class="col-md-12">
	                <table class="table table-striped table-bordered table-hover align-middle text-center">
	                  <tbody>
	                    
	                  </tbody>
	                </table>
	              </div>
	              
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	    
	    <!-- Vendor jQuery (PAGE LEVEL PLUGINS - METRONIC)-->
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/select2/select2.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.kr.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/ckeditor/ckeditor.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/ckeditor/adapters/jquery.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/ckeditor/lang/ko.js"></script>
	    <!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support, and moment.min.js-->
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/moment.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/fullcalendar/lang-all.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/plugins/owl.carousel/owl.carousel.min.js"></script>
	    <script type="text/javascript" src="/theme/libs/starwars.js"></script>
	    <script type="text/javascript" src="/theme/libs/bootstrap-rating.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL STYLES - METRONIC)-->
	    <script type="text/javascript" src="/theme/libs/metronic-admin/global/scripts/metronic.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/admin/layout/scripts/layout.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/admin/layout/scripts/quick-sidebar.js"></script>
	    <script type="text/javascript" src="/theme/libs/metronic-admin/admin/layout/scripts/demo.js"></script>
	
	    <!-- Theme Script-->
	    <script type="text/javascript" src="/theme/assets/js/bootstrap-select.min.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/jquery.jqChart.min.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/sidebar-menu.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/admin.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/admin-custom.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/customer.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/required.js"></script>
	    <script type="text/javascript" src="/theme/libs/custom.js"></script>
	    <script type="text/javascript" src="/theme/assets/js/ajax.js"></script>
	    
  </body>
</html>
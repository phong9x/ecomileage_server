<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<header>

<SCRIPT type="text/javascript">
	
	function showToast(){
		   var success = ${success};
		   var state = ${state};
		   
		   var ua = navigator.userAgent;
		   if(ua.match(/(iPhone|iPod|iPad)/)){
		    	window.location = "com.TramVN.EcoMileage://" + success + "&amp;" + state;
		   }else if(ua.match(/Android/)){
		          Android.payment(success, state);
		   }
		         
		   return false;
	}
</SCRIPT>

</header>
<body onload="showToast()">

	<div>
		<c:choose>
	       <c:when test="${success}">
	    		<P>Payment success</P>
	       </c:when>
	       <c:otherwise>
			    <P>Payment fail</P>
				<P>P_STATUS: ${P_STATUS }</P>
				<P>message: ${message}</P>       
	       </c:otherwise>
	   </c:choose>
	</div>	
	
</body>


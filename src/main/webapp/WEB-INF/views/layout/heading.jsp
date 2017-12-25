<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
<div class="page-header">
      <div class="page-header-inner container">
        <!-- BEGIN LOGO-->
        <div class="logo"><a href="index.html"></a></div>
		<a href="javascript:;" class="icon-menu">menu</a>
        <!-- .responsive-toggler-->
        
<script>
function startTime() {
    var today = new Date();	
    var yy = today.getFullYear();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    } 
    if(mm<10){
        mm='0'+mm;
    } 
    var h = today.getHours();
    if(h<10){
        h='0'+h;
    } 
    var m = today.getMinutes();
    m = checkTime(m);
    var ampm = h >= 12 ? 'PM' : 'AM';
    document.getElementById('clock-time').innerHTML ="Today "+yy+"."+mm+"."+dd+" "+ampm+" "+
    h + ":" + m ;
    var t = setTimeout(startTime, 500);
    
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}
</script>
        <div class="top-menu">
          <ul class="nav navbar-nav pull-right">
            <li class="dropdown dropdown-user">
            <a href="/logout" class="dropdown-toggle"><span class="username username-hide-on-mobile">로그아웃</span></a>
            </li>
          </ul>
        </div><span class="time-menu"  id="clock-time"> 12:04
          <!-- .dropdown-user--></span>
        <!-- .top-menu-->
      </div>
</div>

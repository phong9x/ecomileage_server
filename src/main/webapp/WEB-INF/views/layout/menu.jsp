<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>
     <div class="sidebar-left">
      <div class="left_col">
        <div class="left_col scroll-view">
          <div style="border: 0;width: 69px;margin-left: 29%;" class="navbar nav_title"><a href="/dist/html/admin/" class="site_title"><img src="/theme/assets/images/logo/logo.png" alt="logo"/></a></div>
          <div class="clearfix"></div>
          <!-- menu profile quick info-->
          <!-- /menu profile quick info--><br/>
          <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
              <ul class="nav side-menu">
                <li ><a href="/auth/campaign"><i class="fa fa-user-o"></i><span>캠페인 관리</span></a></li>

                <li><a ><i class="fa fa-desktop"></i> NFC/QR코드 관리 <span class="fa fa-chevron-down"></span></a>
                  <ul class="nav child_menu">
                    <li><a href="/auth/vehicleCode/bus/list">버스 NFC/QR 코드 관리</a></li>
                    <li><a href="/auth/vehicleCode/subway/list">지하철 NFC/QR코드 관리</a></li>
                    <li><a href="/auth/vehicleCode/bike/list">자전거 NFC/QR코드 관리</a></li>
                  </ul>
                </li>
                <li><a><i class="fa fa-table"></i> 회원 관리 <span class="fa fa-chevron-down"></span></a>
                  <ul class="nav child_menu">
                    <li><a href="/auth/user/member/list">회원 정보 관리</a></li>
                    <li><a href="/auth/user/code/list">데이터 이전 관리</a></li>
                  </ul>
                </li>
                <li><a><i class="fa fa-bar-chart-o"></i> 데이터 관리 <span class="fa fa-chevron-down"></span></a>
                  <ul class="nav child_menu">
                    <li><a href="/auth/mileage/bike/list">트랙킹 데이터 </a></li>
                    <li><a href="/auth/mileage/error/list">오류 데이터 검토</a></li>
                    <li><a href="/auth/mileage/transfer/list">환승 데이터</a></li>
                    <li><a href="/auth/mileage/approval/list">에코 설정값</a></li>
                  </ul>
                </li>
                
                <li><a><i class="fa fa-clone"></i>물품 신청 관리 <span class="fa fa-chevron-down"></span></a>
                  <ul class="nav child_menu">
                    <li><a href="/auth/mileageApply/bike/list">주행 마일리지 물품 신청</a></li>
                    <li><a href="/auth/mileageApply/transfer/list">환승 마일리지 물품 신청</a></li>
                  </ul>
                </li>
                
                
                <li><a><i class="fa fa-windows"></i>후원 관리 <span class="fa fa-chevron-down"></span></a>
                  <ul class="nav child_menu">
                    <li><a href="/auth/sponsor/register/list">정기후원</a></li>
                    <li><a href="/auth/sponsor/tem/list">일시후원</a></li>
                    <li><a href="/auth/sponsor/point/list">포인트후원</a></li>
                  </ul>
                </li>
                <li><a href="/auth/group/list"><i class="fa fa-sitemap"></i>그룹 관리 </a></li>
                <li><a href="/auth/bikeInfo/list"><i class="fa fa-bug"></i>자전거 시설관리</a></li>
                <li><a href="/auth/report/list"><i class="fa fa-sitemap"></i>신고 관리 </a>
                <li><a href="/auth/notice/list"><i class="fa fa-sitemap"></i>공지사항 </a>
                <li><a href="/auth/news/list"><i class="fa fa-sitemap"></i>자전거 뉴스 </a>
                </li>
              </ul>
            </div>

          </div>
          <!-- sidebar menu-->
          <!-- /sidebar menu-->
          <!-- /menu footer buttons-->
          <!-- /menu footer buttons-->
        </div>
      </div>
      <!-- .left_col-->
    </div>
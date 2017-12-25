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
              <!-- .page-bar-->
			  <form method="post">
              <div class="main-content full">
                <div class="title-page full border-bottom mrb-20">
                  <h3 class="bold pull-left">캠페인 관리</h3><span>에코 마일리지 캠페인 정보를 관리합니다.</span>
                </div>
                <div class="item-main full">
                  <h4>MEMBER (명)</h4>
                  <div class="full border-full text-center pdt-20">
                    <div class="col-md-4">
                      <p class="mrb-20">올해의 캠페인 참여자 목표</p>
                      <p id="goal-user-show" > 
                        <span class="font-16"><fmt:formatNumber value="${c.totalGoalUser }" maxFractionDigits="3"></fmt:formatNumber> </span>
                        <button type="button" class="btn green pad-5-10" id="button-goal-user-show">수정</button>
                      </p>
                      <p id="goal-user-edit" style="display: none"> 
                      	<input type="number" name="totalGoalUser" value="${c.totalGoalUser }" style="font-size: 16px; padding-bottom: 4px; width: 120px"/>
                        <button type="button" class="btn red pad-5-10" id="button-goal-user-delete">x</button>
                        <button class="btn green pad-5-10" id="button-goal-user-edit" name="editUserGoal" value="1" >저장</button>
                      </p>
                    </div>
                    <div class="col-md-3">
                      <p class="mrb-20">올해의 참여자수</p>
                      <p> <span class="font-16">${totalFactUserInYear }</span></p>
                    </div>
                    <div class="col-md-2">
                      <p class="mrb-20">달성률</p>
                      <p> <span class="font-16">${ratioUser }%</span></p>
                    </div>
                    <div class="col-md-3">
                      <p class="mrb-20">지금까지의 참여자수</p>
                      <p> <span class="font-16">${totalFactUser }</span></p>
                    </div>
                  </div>
                </div>
                <div class="item-main full">
                  <h4>CO2 감축량 (kg)</h4>
                  <div class="full border-full text-center pdt-20">
                    <div class="col-md-4">
                      <p class="mrb-20">올해의  <span style="color: black;font-weight: bold;font-size: 18px;">CO</span><span style="color: black;font-weight: bold;">2</span> 감축량 목표</p>
                      
                      <p id="goal-co2-show"> <span class="font-16"><fmt:formatNumber value="${totalGoalCo2 }" maxFractionDigits="3"></fmt:formatNumber> </span>
                        <button type="button" class="btn green pad-5-10" id="button-goal-co2-show">수정</button>
                      </p>
                      
                      <p id="goal-co2-edit" style="display: none"> 
                      	<input type="number" name="totalGoalCo2" value="${totalGoalCo2 }" style="font-size: 16px; padding-bottom: 4px; width: 120px"/>
                        <button type="button" class="btn red pad-5-10" id="button-goal-co2-delete">x</button>
                        <button class="btn green pad-5-10" id="button-goal-co2-edit" name="editCo2Goal" value="1">저장</button>
                      </p>
                      
                    </div>
                    <div class="col-md-3">
                      <p class="mrb-20">올해의 <span style="color: black;font-weight: bold;font-size: 18px;">CO</span><span style="color: black;font-weight: bold;">2</span>감축량</p>
                      <p> <span class="font-16">${totalFactReduceCO2InYear }</span></p>
                    </div>
                    <div class="col-md-2">
                      <p class="mrb-20">달성률</p>
                      <p> <span class="font-16">${ratioCO2 }%</span></p>
                    </div>
                    <div class="col-md-3">
                      <p class="mrb-20">지금까지의 <span style="color: black;font-weight: bold;font-size: 18px;">CO</span><span style="color: black;font-weight: bold;">2</span> 감축량</p>
                      <p> <span class="font-16">${totalFactReduceCO2 }</span></p>
                    </div>
                  </div>
                </div>
              </div>
              </form>
              <!--.table-member-mng-->
              
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        <!-- .page-content-->
      </div>
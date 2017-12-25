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

              <form method="post" enctype="multipart/form-data">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">회원 상세</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-40">
                      <div class="col-md-2">
                        <p class="text-center">프로필 사진</p>
                        <div class="member-avatar">
                          <div class="inputfile">
                            <div class="tr-file-upload image-upload">
                              <div class="preview-image"><img src="${item.avatarUrl }" alt=""/></div>
                              <c:if test="${!item.isDelete }">
	                              <label class="btn green btn-select-file">수정
	                                <input type="file" name="avatar" class="hidden input-file" accept="image/*"/>
	                              </label>
	                              <div class="preview-image" style="margin-left: 40%;">
	                              	<div class="input-icon"><span class="btn red btn-delete">삭제 </span></div>
	                              </div>
                              </c:if>
                            </div>
                          </div>
                        </div>
                      </div>
                      
                      <div class="col-md-10">
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-34 bg-a9">ID :</div>
                          <div class="col-md-3 line-34"> 
                            <input type="text" value="${item.username }" class="form-control pull-left" disabled="disabled"/>
                          </div>
                          <div class="col-md-4 line-34"> 
	                          <c:if test="${!item.isDelete }">
	                           	<button type="button" class="btn red pull-left" style="margin-left: 200px;width:100px " data-toggle="modal" data-target="#popup-delete-user">탈퇴</button>
	                          </c:if>
                          </div>
                        </div>
                        
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-34 bg-a9">이름 :</div>
                          <div class="col-md-3 line-34"> 
                            <input type="text" value="${item.fullname }" name="fullname" class="form-control pull-left"/>
                          </div>
                        </div>
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-34 bg-a9">회원구분 :</div>
                          
                          <c:choose>
	                    	<c:when test="${item.isDelete}">
	                    		<div class="col-md-3">
                        			<input type="text" value="탈퇴회원" style="color: red" class="form-control pull-left" disabled="disabled"/>
                      			</div>
	                    	</c:when>
	                    	<c:otherwise>
	                    	  <div class="col-md-3">
	                            <select name="roleId" title="선택..." class="form-control selectpickermember text-left">
	                              <option data-hidden="true"></option>
	                              <option ${item.roleId == 1 ? 'selected' : '' } value="1">준회원 </option>
	                              <option ${item.roleId == 2 ? 'selected' : '' } value="2">정회원</option>
	                              <option ${item.roleId == 3 ? 'selected' : '' } value="3">후원회원</option>
	                            </select>
	                          </div>
	                    	</c:otherwise>
	                    </c:choose>
                          
                        </div>
                        <div class="full mrb-10">
                        <div class="col-md-2 text-right line-34 bg-a9">휴대폰 번호 :</div>
                        <div class="col-md-3 line-34"> 
                          <input type="text" value="${item.phone}" name="phone" class="form-control pull-left"/>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-34 bg-a9">이메일 :</div>
                      <div class="col-md-3">
                        <input type="text" value="${item.email}" name="email" class="form-control width-90"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-60 bg-a9">주소 :</div>
                      <div class="col-md-10">
                        <div class="row">
                          <div class="col-md-2">
                            <input type="text" value="${item.postcode}" name="postcode" class="form-control"/>
                          </div>
                          <c:if test="${!item.isDelete }">
                          	<div class="col-md-2"><button type="button" data-toggle="modal" data-target="#popup-postcode-member" class="btn green pull-left btn-open-popup">우편번호 검색</button></div>
                          </c:if>
                        </div>
                        <div class="row">
                          <div class="col-md-3">
                            <input type="text" value="${item.address}" name="address" class="form-control"/>
                            <input type="hidden" value="${item.city}" name="city" class="form-control"/>
                            <input type="hidden" value="${item.district}" name="district" class="form-control"/>
                          </div>
                          <div class="col-md-3">
                            <input type="text" value="${item.addressDetail}" name="addressDetail" class="form-control"/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-34 bg-a9">가입날짜 :</div>
                      <div class="col-md-2 line-34"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/> </div>
                      <div class="col-md-2 text-center line-34 bg-a9">가입경로 :</div>
                      <div class="col-md-2 line-34">
						<c:choose>
							<c:when test="${item.snsType eq 'normal' }">
								직접가입
							</c:when>
							<c:otherwise>
								${item.snsType}
							</c:otherwise>
						</c:choose>
					  </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-34 bg-a9">보유 마일리지 :</div>
                      <div class="col-md-10 line-34">
                        <div class="row">
                          <div class="col-md-1 bold bg-a9">이전 ECO :</div>
                          <div class="col-md-1">${item.previousPoint } P</div>
                          <div class="col-md-1"></div>
                          <div class="col-md-1 bold bg-a9">주행 :</div>
                          <div class="col-md-1">${totalBikePoint } P</div>
                          <div class="col-md-1"></div>
                          <div class="col-md-1 bold bg-a9">즐겨찾기 :</div>
                          <div class="col-md-1">${totalFavouritePoint } P</div>
                          <div class="col-md-1"></div>
                          <div class="col-md-1 bold bg-a9">환승 :</div>
                          <div class="col-md-1">${totalTranferPoint } P</div>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10 line-34">
                      <div class="col-md-2 text-center bg-a9">자전거 NFC/QR 코드 :</div>
                      <div class="col-md-2">${v.serialNumber }</div>
                      <div class="col-md-2"> </div>
                      <div class="col-md-2 text-center bg-a9 ">승인날짜  :</div>
                      <div class="col-md-2"><fmt:formatDate value="${v.registerDate }" pattern="yyyy-MM-dd"/></div>
                    </div>
                    <div class="full mrb-10 line-34">
                      <div class="col-md-2 text-center bg-a9">총 이동거리(km) :</div>
                      <div class="col-md-4"> 
                        <div class="row">
                          <div class="col-md-3 bold bg-a9 bg-a9">이전 eco :</div>
                          <div class="col-md-3">${item.totalMileageOld }</div>
                          <div class="col-md-3 bold bg-a9 bg-a9">현재 :</div>
                          <div class="col-md-3"><fmt:formatNumber value="${totalMileage }" maxFractionDigits="3"></fmt:formatNumber> </div>
                        </div>
                      </div>
                      <div class="col-md-2 text-center bg-a9">총 활동수  :</div>
<%--                       <div class="col-md-2">${totalActive }</div> --%>
						<div class="col-md-4"> 
                        <div class="row">
                          <div class="col-md-3 bold bg-a9">이전 eco :</div>
                          <div class="col-md-3">${item.totalActiveOld }</div>
                          <div class="col-md-3 bold bg-a9">현재 :</div>
                          <div class="col-md-3"><fmt:formatNumber value="${totalActive }" maxFractionDigits="2"></fmt:formatNumber> </div>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10 line-34">
                      <div class="col-md-2 text-center bg-a9">총 CO2 감축량 :</div>
                      <div class="col-md-2 text-center bold bg-a9">이전 eco :</div>
                      <div class="col-md-1"> ${item.totalReduceCo2Old }</div>
                      <div class="col-md-1"> </div>
                      <div class="col-md-2 text-center bg-a9">현재  :</div>
                      <div class="col-md-2">${totalReduceCO2}</div>
                    </div>
                    <c:choose>
                    	<c:when test="${item.isDelete}">
                    		<div class="full mrb-10 line-34">
		                      <div class="col-md-2 text-center bg-a9">탈퇴날짜</div>
		                      <div class="col-md-2"><fmt:formatDate value="${item.leavingDate }" pattern="yyyy-MM-dd"/></div>
		                      <div class="col-md-2"> </div>
		                      <div class="col-md-2 text-center bg-a9">탈퇴사유  :</div>
		                      <div class="col-md-2">${item.leavingReason }</div>
		                    </div>
                    	</c:when>
                    	<c:otherwise>
                    		<div class="full mrb-10 line-34">
		                      <div class="col-md-2 text-center bg-a9">메모 :</div>
		                      <div class="col-md-8">
		                        <textarea name="memo" rows="10" class="rw-ckeditor form-control">${item.memo }</textarea>
		                      </div>
		                    </div>
                    	</c:otherwise>
                    </c:choose>
                    
                    <input type="hidden" value="${item.id}" name="id" class="form-control"/>
                    <div class="full text-center">
                       <a href="/auth/user/member/list" class="btn grey pad-8-50">목록</a>
                       <c:if test="${!item.isDelete}">
                       		<button type="submit" class="btn green pad-8-50 ">수정</button>
					   </c:if>
                    </div>
                  </div>
                </div>
                </div>
              </form>
              <!-- .block-1-->
            </div>
            <!-- .member-main-->
          </div>
        </div>
        
        <div id="popup-delete-user" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
	      <div class="modal-dialog">
	      <form method="post" enctype="multipart/form-data">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h4 class="bold">회원 탈퇴</h4>
	            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
	          </div>
	          
	          <div class="modal-body">
	            <div class="full">
	            	<input type="text" name="reason" value=""  placeholder="탈퇴 사유를 입력해주세요." class="form-control pull-left"/>
	            </div>
	          </div>
	          
	          <div class="modal-footer" style="text-align: center">
	          	<button type="button" data-dismiss="modal" class="btn btn-default">취소</button>
	            <button type="submit" name="delete" value="true" class="btn green">확인</button>
	          </div>
	        </div>
	       </form>
	      </div>
	    </div>
        
        
	    <c:if test="${item.isDelete}">
	    	<script type="text/javascript">
	    	$( document ).ready(function() {
	    		$('input').prop('disabled', true);
	    		$('input').prop('disabled', true);
	    	})
	    		
	    	</script>
	    </c:if>
	    
        <!-- .page-content-->
      </div>
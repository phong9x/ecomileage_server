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

              <form method="post" id="form-member-creat" enctype="multipart/form-data">
                <div class="member-title-table">
                  <div class="title-page full border-bottom mrb-30">
                    <h3 class="bold pull-left">후원회원 등록</h3>
                  </div>
                  <div class="full border-full pad-20-30 mrb-40">
                    <div class="full mrb-40">
                      <div class="col-md-2">
                        <p class="text-center">프로필 사진</p>
                        <div class="member-avatar">
                          <div class="inputfile">
                            <div class="tr-file-upload image-upload">
                              <div class="preview-image"><img src="/theme/assets/images/avatar_men.png" alt="thumbnail"/></div>
                              <label class="btn green btn-select-file">수정
                                <input type="file" name="avatar" class="hidden input-file" accept="image/*"/>
                              </label>
                            </div>
                          </div>
                        </div>
                      </div>
                      
                      <div class="col-md-10">
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-30">ID :</div>
                          <div class="col-md-3 line-30"> 
                            <input type="text" name="username" class="form-control pull-left"/>
                          </div>
                        </div>
                        
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-30">PW :</div>
                          <div class="col-md-3 line-30"> 
                            <input type="password" name="password" class="form-control pull-left"/>
                          </div>
                        </div>
                        
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-30">이름 :</div>
                          <div class="col-md-3 line-30"> 
                            <input type="text" name="fullname" class="form-control pull-left"/>
                          </div>
                        </div>
                        
                        <div class="full mrb-10">
                          <div class="col-md-2 text-right line-30">회원구분 :</div>
                          
                    	  <div class="col-md-3 line-30">
                            <select name="roleId" class="form-control selectpickermember text-left">
                              <option value="1">준회원 </option>
                              <option value="2">정회원</option>
                              <option value="3" selected="selected">후원회원</option>
                            </select>
                          </div>
                          
                        </div>
                        <div class="full mrb-10">
                        <div class="col-md-2 text-right line-30">휴대폰 번호 :</div>
                        <div class="col-md-3 line-30"> 
                          <input type="text" name="phone" class="form-control pull-left"/>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-30">이메일 :</div>
                      <div class="col-md-3">
                        <input type="text" name="email" class="form-control width-90"/>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-60">주소 :</div>
                      <div class="col-md-10">
                        <div class="row">
                          <div class="col-md-2">
                            <input type="text" name="postcode" class="form-control"/>
                          </div>
                          <div class="col-md-2"><button type="button" data-toggle="modal" data-target="#popup-postcode-member" class="btn green pull-left btn-open-popup">우편번호 검색</button></div>
                        </div>
                        <div class="row">
                          <div class="col-md-3">
                            <input type="text" name="address" class="form-control"/>
                            <input type="hidden" name="city" class="form-control"/>
                            <input type="hidden" name="district" class="form-control"/>
                          </div>
                          <div class="col-md-3">
                            <input type="text" name="addressDetail" class="form-control"/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="full mrb-10">
                      <div class="col-md-2 text-center line-30">가입날짜 :</div>
                      <div class="col-md-2 line-30">
                      		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
                      		<input type="hidden" name="createDate" value="${now}" class="form-control">
                      </div>
                    </div>
                    
                  	<div class="full mrb-10 line-30">
                      <div class="col-md-2 text-center">메모 :</div>
                      <div class="col-md-8">
                        <textarea name="memo" rows="10" class="rw-ckeditor form-control"></textarea>
                      </div>
                    </div>
                    	
                    
                    <div class="full text-center">
                       <a href="/auth/user/member/list" class="btn grey pad-8-50">목록</a>
                       <button type="button" class="btn green pad-8-50 " onclick="validateFormMemberCreated();">등록</button>
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
        <!-- .page-content-->
      </div>
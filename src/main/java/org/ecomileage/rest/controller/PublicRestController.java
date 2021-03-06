/*
 * Created on 2 thg 10 2017 ( Time 11:58:51 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.rest.controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import kr.co.tongkni.webservice.sendsms;

import org.ecomileage.bean.UserItem;
import org.ecomileage.bean.VehicleCode;
import org.apache.commons.lang.RandomStringUtils;
import org.ecomileage.bean.Address;
import org.ecomileage.bean.User;
import org.ecomileage.bean.jpa.AddressEntity;
import org.ecomileage.bean.jpa.UserEntity;
import org.ecomileage.business.service.AddressService;
import org.ecomileage.business.service.UserService;
import org.ecomileage.business.service.VehicleCodeService;
import org.ecomileage.common.mail.Mail;
import org.ecomileage.rest.common.AbstractRestController;
import org.ecomileage.rest.common.AuthorizationToken;
import org.ecomileage.web.common.utils.DataUtils;
import org.ecomileage.web.common.utils.EncryptionUtils;
import org.ecomileage.web.common.utils.FileUtils;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;

@RequestMapping("/public")
@Controller
public class PublicRestController extends AbstractRestController{

	@Resource
	private UserService userService;
	@Resource
	private VehicleCodeService vehicleCodeService;
	@Resource
	private AddressService addressService;
	// login screen
	@RequestMapping(value = "/login", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> login(
			@ModelAttribute User user, HttpSession session
			) {
		try {
			HashMap<String, Object> data = new HashMap<>();
			String password = EncryptionUtils.jwtBuild(user.getPassword());
			Integer roleIds[] = new Integer[3];
			roleIds[0] = ApplicationDefine.UserRole.StandardMember.getCode();
			roleIds[1] = ApplicationDefine.UserRole.OfficalMember.getCode();
			roleIds[2] = ApplicationDefine.UserRole.SponsorMember.getCode();
			User u = new User();
			if(user.getSnsType().equals("normal")) {
				u = userService.login(user.getUsername(), password, roleIds);
			}else {
				if(user.getSocialId().isEmpty()) {
					return FAIL("SNS Token is null");
				}
				u = userService.loginSNS(user.getSnsType(), user.getSocialId(), roleIds);
			}
			
			if (u != null) {

				String token = AuthorizationToken.generateTokenMobile(u);
				data.put("token", token);
				
				HashMap<String, Object> ret = new HashMap<>();
				ret.put("avatarUrl", u.getAvatarUrl());
				ret.put("fullname", u.getFullname());
				ret.put("roleId", u.getRoleId());
				ret.put("totalPoint", u.getPreviousPoint()+ u.getBikePoint() +u.getFavouritePoint()+ u.getTransferPoint());
				data.put("user", ret);
				data.put("loginFirst", u.getLoginFirst());
				if(u.getLoginFirst()) {
					u.setLoginFirst(false);
					userService.update(u);
				}
				
				return SUCCESS(data);
			} else {
				return FAIL("User is not exists");
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// find_id screen
	@RequestMapping(value = "/findId", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findId(
			@ModelAttribute User user, HttpSession session
			) {
		try {
			
			List<UserEntity> list = userService.findByEmailAndPhoneAndIsDelete(user.getEmail(), user.getPhone());
			
			if (list.size() > 0 ) {
				UserEntity u = list.get(0);
				String username =u.getUsername();
				if(username.length() >4) {
					username = username.substring(0, username.length()-2)+"**";
				}else {
					username = username.substring(0, username.length()-1)+"*";
				}
				return SUCCESS(username);
			} else {
				return FAIL("입력하신 정보와 일치하는 \n" + 
						"회원정보가 없습니다.\n" + 
						"다시 확인해 주세요.\n" );
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// find_pw screen
	@RequestMapping(value = "/findPw", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findPw(
			@ModelAttribute User user, HttpSession session) {
		try {
			User u = userService.findByUsernameAndPhoneAndIsDelete(user.getUsername(), user.getPhone());
			
			if (u != null ) {
				String passwordTemp = RandomStringUtils.randomNumeric(6);
				u.setPassword(EncryptionUtils.jwtBuild(passwordTemp));
				u = userService.update(u);
				
				Map<String, Object> mailModel = new HashMap<>();
				mailModel.put("passwordTemp", passwordTemp);
				mailModel.put("email", u.getEmail());
				Mail.sendEmailTemplate(u.getEmail(), mailModel, Mail.EmailTemplate.VERYFY_EMAIL, "스마트교통마일리지");
				
				return SUCCESS();
			} else {
				return FAIL();
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// join_id_1 screen
	@RequestMapping(value = "/checkId", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> checkId(
			@ModelAttribute User user, 
			
			HttpSession session
			) {
		try {
			UserEntity u = userService.findByUsername(user.getUsername());
			if (u == null ) {
				return SUCCESS();
			} else {
				return FAIL("Username is exists");
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// join_id_2 screen
	@RequestMapping(value = "/sendVerifyCode", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> sendVerifyCode(
			@ModelAttribute User user, 
			@RequestParam(required=true) String phone
			) {
		try {
			List<UserEntity> list = userService.findByPhoneAndIsDelete(phone);
			if (list != null && list.size()> 0) {
				return FAIL("Phone is exists");
			} else {
				String auth = DataUtils.getAuthPhoneCode(phone, "");
				sendsms sendsms = new sendsms();
				sendsms.send_SMS(user.getPhone(), "스마트교통마일리지]본인확인 인증번호는 ["+auth+"]입니다.");
				return SUCCESS(auth);
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// join_id_2 screen
	@RequestMapping(value = "/verifyPhone", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> verifyCode(
			@ModelAttribute User user, 
			@RequestParam(required=true) String phone,
			@RequestParam(required=true) String SMScode
			) {
		try {
			List<UserEntity> list = userService.findByPhoneAndIsDelete(phone);
			HashMap<String, Object> ret = new HashMap<>();
			ret.put("phoneOk", true);
			if (list != null && list.size()> 0) {
				ret.put("phoneOk", false);
			}
			String auth = DataUtils.getAuthPhoneCode(phone, "");
			if(auth.equals(SMScode)) {
				return SUCCESS(ret);
			}else {
				return FAIL(ret);
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	// join_id_3 screen
	@RequestMapping(value = "/join", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> join(
			@ModelAttribute User user, 
			@RequestParam(required=false) MultipartFile avatar
			
			) {
		try {
			
			UserEntity ue = new UserEntity();
			if(user.getSnsType().equals("normal")) {
				if(user.getPassword()== null || user.getUsername()== null){
					return FAIL("Username or password is not null");
				}
				ue = userService.findByUsername(user.getUsername());
				if (ue != null ) {
					return FAIL("Username is exists");
				}
				user.setRoleId(ApplicationDefine.UserRole.OfficalMember.getCode());
				user.setPassword(EncryptionUtils.jwtBuild(user.getPassword()));
			}else {
				if(user.getSocialId() == null){
					return FAIL("SocialId is not null");
				}
				ue = userService.findBySnsTypeAndSnsTokenAndIsDelete(user.getSnsType(), user.getSocialId());
				if (ue != null ) {
					return FAIL("Username is exists");
				}
				user.setRoleId(ApplicationDefine.UserRole.StandardMember.getCode());
				user.setPassword(null);
			}
				if(avatar != null && avatar.getSize() >0) {
					try {
						user.setAvatarName(avatar.getOriginalFilename());
						user.setAvatarUrl(FileUtils.saveFile(avatar));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				user.setLoginFirst(false);
				user.setUpdateDate(new Date());
				user.setCreateDate(new Date());
				user.setRequestDate(new Date());
				User userCreate = userService.create(user);
				
				HashMap<String, Object> data = new HashMap<>();
				HashMap<String, Object> ret = new HashMap<>();
				ret.put("avatarUrl", userCreate.getAvatarUrl());
				ret.put("fullname", userCreate.getFullname());
				ret.put("roleId", userCreate.getRoleId());
				ret.put("totalPoint", 0);
				data.put("user", ret);
				String token = AuthorizationToken.generateTokenMobile(userCreate);
				data.put("token", token);
				return SUCCESS(data);
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	
	// history_rank_2  screen
	@RequestMapping( value="/findCity",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findCity2(
	) {
		try {
			List<Address> list = addressService.findAll();
			List<Address> listCity= new ArrayList<>();
			List<Map<String, Object>> result= new ArrayList<>();
			int index =0;
			for (Address address : list) {
				if(address.getParentId() == 0) {
					listCity.add(address);
					index++;
				}
			}
			System.out.println("index: "+index);
			for (int i = 0; i < listCity.size(); i++) {
				HashMap<String, Object> city = new HashMap<>();
				List<Map<String, Object>> listDistrict = new ArrayList<>();
				Address a = listCity.get(i);
				city.put("id", a.getId());
				city.put("name", a.getName());
				for (int j = index; j < list.size(); j++) {
						Address district = list.get(j);
						if(district.getParentId() == a.getId() ) {
							HashMap<String, Object> map = new HashMap<>();
							map.put("id", district.getId());
							map.put("name", district.getName());
							listDistrict.add(map);
							if(j == list.size()-1) {
								city.put("listDistrict", listDistrict);
							}
						}else {
							city.put("listDistrict", listDistrict);
							continue;
						}
						index++;
				}
				result.add(city);
			}
			
			
			
			return SUCCESS(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
	} 
}




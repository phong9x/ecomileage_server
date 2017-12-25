/*
 * Created on 27 thg 9 2017 ( Time 11:37:23 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.rest.controller;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.ecomileage.bean.UserItem;
import org.ecomileage.bean.User;
import org.ecomileage.bean.jpa.CampaignEntity;
import org.ecomileage.bean.jpa.TeamMemberEntity;
import org.ecomileage.bean.jpa.UserEntity;
import org.ecomileage.business.service.CampaignService;
import org.ecomileage.business.service.MileageHistoryService;
import org.ecomileage.business.service.TeamMemberService;
import org.ecomileage.business.service.UserService;
import org.ecomileage.rest.common.AbstractRestController;
import org.ecomileage.rest.common.AuthorizationToken;
import org.ecomileage.web.common.utils.DataUtils;
import org.ecomileage.web.common.utils.EncryptionUtils;
import org.ecomileage.web.common.utils.FileUtils;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;
/**
 * Spring MVC controller for 'User' management.
 */
@RequestMapping("/user")
@Controller
public class UserRestController extends AbstractRestController{

	@Resource
	private UserService userService;
	@Resource
	private MileageHistoryService mileageHistoryService;
	@Resource
	private CampaignService campaignService;
	@Resource
	private TeamMemberService teamMemberService;
	//join_id_pre screen
	@RequestMapping( value="/loginFirst",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findOne(
	HttpSession session,
	@RequestHeader(value="token",required=false) String token,
	@RequestHeader(value="avatarUrl",required=false) String avatarUrl,
	@RequestParam(value="avatar",required=false) MultipartFile avatar,
	@RequestParam String fullname
		) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			if(user !=null || (user.getUsername().isEmpty() && user.getSnsType().equals("normal") )){
				user.setAvatarUrl(avatarUrl);
				if(avatar != null && avatar.getSize() >0) {
					try {
						user.setAvatarName(avatar.getOriginalFilename());
						user.setAvatarUrl(FileUtils.saveFile(avatar));
						user.setPassword(EncryptionUtils.jwtBuild(user.getPassword()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				user.setFullname(fullname);
				User userupdate =userService.update(user);
				return SUCCESS(userupdate.getAvatarUrl());
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	
	//Info-modify, Info-modify_sns screen
	@RequestMapping( value="/infomation",
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> infomation(
	HttpSession session,
	@RequestHeader(value="token",required=false) String token,
	@ModelAttribute User item
		) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			if(user !=null ){
				HashMap<String, Object> data = new HashMap<>();
				data.put("avatarUrl", user.getAvatarUrl());
				data.put("fullname", user.getFullname());
				data.put("username", user.getUsername());
				data.put("role", user.getRoleId());
				data.put("phone", user.getPhone());
				data.put("email", user.getEmail());
				data.put("postcode", user.getPostcode() );
				data.put("address", user.getAddress());
				data.put("addressDetail", user.getAddressDetail());
				data.put("city", user.getCity() );
				data.put("district", user.getDistrict());
				data.put("snsType", user.getSnsType());
				data.put("totalMileage",mileageHistoryService.getTotalPointByUserId(u.getId()));
				if(user.getPassword() == null) {
					data.put("password", "");
				}else {
					data.put("password", EncryptionUtils.jwtParse(user.getPassword(), String.class));
				}
				return SUCCESS(data);
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	//Info-modify, Info-modify_sns screen
	@RequestMapping( value="/infomation",
			method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> infomation_post(
	HttpSession session,
	@RequestHeader(value="token",required=false) String token,
	@RequestParam(value="avatar",required=false) MultipartFile avatar,
	@ModelAttribute User item
		) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			if(user !=null ){	
				user.setFullname(item.getFullname());
				user.setPhone(item.getPhone());
				user.setEmail(item.getEmail());
				user.setPostcode(item.getPostcode());
				user.setAddress(item.getAddress());
				user.setAddressDetail(item.getAddressDetail());
				user.setCity(user.getCity());
				user.setDistrict(user.getDistrict());
				if(avatar != null && avatar.getSize() >0) {
					try {
						user.setAvatarName(avatar.getOriginalFilename());
						user.setAvatarUrl(FileUtils.saveFile(avatar));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				User userupdate =userService.update(user);
				HashMap<String, Object> data = new HashMap<>();
				data.put("avatarUrl", userupdate.getAvatarUrl());
				data.put("fullname", userupdate.getFullname());
				data.put("username", userupdate.getUsername());
				data.put("role", userupdate.getRoleId());
				data.put("phone", userupdate.getPhone());
				data.put("email", userupdate.getEmail());
				data.put("postcode", userupdate.getPostcode() );
				data.put("address", userupdate.getAddress());
				data.put("addressDetail", userupdate.getAddressDetail());
				data.put("city", user.getCity() );
				data.put("district", user.getDistrict());
				return SUCCESS(data);
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	//change_pw screen
	@RequestMapping( value="/changePw",
			method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> changePw(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam(value="password") String password,
	@RequestParam(value="newPassword") String newPassword
		) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			if(user !=null ){
				if(!EncryptionUtils.jwtBuild(password).equals(user.getPassword())) {
					return FAIL("Password is not match");
				}
				if(newPassword == null) {
					return FAIL("New password is not null");
				}
				user.setPassword(EncryptionUtils.jwtBuild(newPassword));
				User userupdate =userService.update(user);
				return SUCCESS();
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	//group_create  screen
	@RequestMapping( value="/findByName",
			method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findByName(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam(value="fullname",required=true) String fullname
		) {
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("fullname", fullname);
			Page<UserEntity> list = userService.filterAdmin(params, 1, 10);
			if(list !=null && list.getSize() >0 ){
				List<Map<String, Object>> listResult = new ArrayList<>();
				for (UserEntity i : list) {
					HashMap<String, Object> user = new HashMap<>();
					user.put("avatarUrl", i.getAvatarUrl());
					user.put("fullname", i.getFullname());
					user.put("id", i.getId());
					listResult.add(user);
				}
				return SUCCESS(listResult);
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	//info-modify_sms  screen
	@RequestMapping(value = "/verifyPhoneSNS", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> verifyCode(
			@RequestHeader(value="token",required=false) String token,
			@RequestParam(required=true) String phone,
			@RequestParam(required=true) String SMScode
			) {
		try {
			List<UserEntity> list = userService.findByPhoneAndIsDelete(phone);
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			HashMap<String, Object> ret = new HashMap<>();
			ret.put("phoneOk", true);
			if (list != null && list.size()> 0) {
				return FAIL("Phone is exsits");
			}
			String auth = DataUtils.getAuthPhoneCode(phone, "");
			if(auth.equals(SMScode)) {
				user.setRoleId(ApplicationDefine.UserRole.OfficalMember.getCode());
				user.setPhone(phone);
				userService.update(user);
				return SUCCESS(user.getRoleId());
			}else {
				return FAIL(ret);
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	//member  screen
	@RequestMapping(value = "/member", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> member(
			@RequestHeader(value="token",required=false) String token
			) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			Calendar cal = Calendar.getInstance();
			Integer year = cal.get(Calendar.YEAR);
			List<TeamMemberEntity> team = teamMemberService.findByUserId(u.getId());
			CampaignEntity c =  campaignService.findByYear(year);
			List<HashMap<String, Object>> group = new ArrayList<>();
			HashMap<String, Object> ret = new HashMap<>();
			Long totalUserInYear = userService.countUserJoinByYear(year);
			Long totalUser = userService.countUserActive();
			Integer totalGoalUser = c.getTotalGoalUser();
			Float ratioUser = (totalUserInYear/(float)totalGoalUser)*100;
			
			ret.put("totalFactUserInYear",  totalUserInYear);
			ret.put("totalUser", totalUser);
			ret.put("ratioUser",Math.round(ratioUser * 10d)/10d );
			ret.put("totalGoalUserInYear", totalGoalUser);
			ret.put("linkShare", "google.com");
			
			if(team !=null) {
				for (TeamMemberEntity i : team) {
					HashMap<String, Object> data = new HashMap<>();
					data.put("groupId", i.getTeam().getId());
					data.put("groupName", i.getTeam().getName());
					data.put("totalGroupUser", teamMemberService.countMemberByGroupId(i.getTeam().getId()));
					group.add(data);
				}
				ret.put("group", group);
			}else {
				ret.put("group", null);
			}
			
			return SUCCESS(ret);
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	//co2  screen
	@RequestMapping(value = "/co2", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> co2(
			@RequestHeader(value="token",required=false) String token
			) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			Calendar cal = Calendar.getInstance();
			Integer year = cal.get(Calendar.YEAR);
			List<TeamMemberEntity> team = teamMemberService.findByUserId(u.getId());
			CampaignEntity c =  campaignService.findByYear(year);
			List<HashMap<String, Object>> group = new ArrayList<>();
			HashMap<String, Object> ret = new HashMap<>();
			Short status[] = new Short[4];
			status[0] = ApplicationDefine.MileageHistory_Status.NotSendRequest.getCode();
			status[1] = ApplicationDefine.MileageHistory_Status.RecivePoint.getCode();
			status[2] = ApplicationDefine.MileageHistory_Status.SendRequestAndNotConfirmed.getCode();
			status[3] = ApplicationDefine.MileageHistory_Status.RegisterAgain.getCode();
			
			Long totalCO2InYear = mileageHistoryService.countReduceCo2_ByYear(year, status);
			Long totalCO2 = mileageHistoryService.countReduceCo2();
			Integer totalGoalCO2 = Math.round(c.getTotalGoalCo2());
			Float ratioCO2 = (totalCO2InYear/(float)totalGoalCO2)*100;
			ret.put("totalCO2InYear",  totalCO2InYear);
			ret.put("totalCO2", totalCO2);
			ret.put("ratioCO2",Math.round(ratioCO2 * 10d)/10d );
			ret.put("totalGoalCO2InYear", totalGoalCO2);
			ret.put("linkShare", "google.com");
			
			if(team !=null) {
				for (TeamMemberEntity i : team) {
					HashMap<String, Object> data = new HashMap<>();
					data.put("groupId", i.getTeam().getId());
					data.put("groupName", i.getTeam().getName());
					data.put("totalCO2", teamMemberService.countCO2ByGroupId(i.getTeam().getId()));
					group.add(data);
				}
				ret.put("group", group);
			}else {
				ret.put("group", null);
			}
			
			return SUCCESS(ret);
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	//tree  screen
	@RequestMapping(value = "/tree", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> tree(
			@RequestHeader(value="token",required=false) String token
			) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			Calendar cal = Calendar.getInstance();
			Integer year = cal.get(Calendar.YEAR);
			HashMap<String, Object> ret = new HashMap<>();
			Short status[] = new Short[1];
			status[0] = ApplicationDefine.MileageHistory_Status.RecivePoint.getCode();
			Short[] type = new Short[1];
			type[0] = ApplicationDefine.MileageHistory_Type.Bike.getCode();
			
			Float totalMilegeInYear = mileageHistoryService.countTotalMileage_ByYearAndUserId(year, u.getId(), type);
			Float totalMilege = mileageHistoryService.countTotalMileageByUserId(u.getId(),type);
			
			ret.put("totalTreeInYear",  DataUtils.calculateTree(totalMilegeInYear));
			ret.put("totalTree",  DataUtils.calculateTree(totalMilege));
			ret.put("username", user.getUsername());
			ret.put("yearNow", year);
			ret.put("totalGoalTreeInYear", user.getTotalGoalTree());
			ret.put("username",user.getUsername() );
			ret.put("fullname",user.getFullname() );
			return SUCCESS(ret);
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	//tree  screen
	@RequestMapping(value = "/update-tree", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> updatetree(
			@RequestHeader(value="token",required=false) String token,
			@RequestParam(value="treeNumber",required=true) Integer treeNumber
			) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			user.setTotalGoalTree(treeNumber);
			userService.update(user);
			return SUCCESS(user.getTotalGoalTree());
			
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	//withdraw screen
	@RequestMapping(value = "/deleteUser", 
			method = { RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> deleteUser(
			@RequestHeader(value="token",required=false) String token){
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			if (u == null) {
				return FAIL("User is not exists");
			}
			User user = userService.findById(u.getId());
			user.setIsDelete(true);
			user.setUpdateDate(new Date());
			user = userService.update(user);
			return SUCCESS();
		} catch (Exception e) {	
			e.printStackTrace();
			return ERROR("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping( value="/getName",
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> getName(
	HttpSession session,
	@RequestHeader(value="token",required=false) String token,
	@ModelAttribute User item
		) {
		try {
			UserItem u = EncryptionUtils.jwtParse(token, UserItem.class);
			User user = userService.findById(u.getId());
			String username = "";
			if(user.getUsername() == null) {
				username = user.getFullname();
			}else {
				username = user.getUsername();
			}
			return SUCCESS(username);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
}





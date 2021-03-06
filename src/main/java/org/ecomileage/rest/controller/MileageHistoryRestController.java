/*
 * Created on 15 thg 11 2017 ( Time 09:35:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.rest.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.ecomileage.web.common.utils.EncryptionUtils;

import org.ecomileage.bean.UserItem;
import org.ecomileage.bean.MileageHistory;
import org.ecomileage.bean.User;
import org.ecomileage.bean.jpa.MileageHistoryEntity;
import org.ecomileage.business.service.MileageHistoryService;
import org.ecomileage.business.service.UserService;
import org.ecomileage.rest.common.AbstractRestController;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;
/**
 * Spring MVC controller for 'MileageHistory' management.
 */
@RequestMapping("/mileageHistory")
@Controller
public class MileageHistoryRestController extends AbstractRestController{

	@Resource
	private MileageHistoryService mileageHistoryService;
	@Resource
	private UserService userService;
	
	//history_chart_bike screen
	@RequestMapping( value="/chart-bike",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAll(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam String typeTime ,
	@RequestParam String typeChart
	) {
		UserItem userItem = EncryptionUtils.jwtParse(token, UserItem.class);
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("typeVehicle", "(1)");
			params.put("typeTime ", typeTime );
			params.put("userId", userItem.getId());
			HashMap<String, Object> ret = new HashMap<>();
			List<Map<String,Object>> result = new ArrayList<>();
			
			if(typeChart.equals("mileage")) {
				result = mileageHistoryService.listMileageChart(params);
				ret.put("unit", "km");
			}else if (typeChart.equals("point")){
				result = mileageHistoryService.listPointChart(params);
				ret.put("unit", "P");
			}else {
				result = mileageHistoryService.listActiveNumberChart(params);
				ret.put("unit", "번");
			}
			ret.put("chart", result);
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
	} 
	
	//history_chart_transfer screen
	@RequestMapping( value="/chart-tranfer",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> chart(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam String typeTime ,
	@RequestParam String typeChart
	) {
		UserItem userItem = EncryptionUtils.jwtParse(token, UserItem.class);
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("typeVehicle", "(2,3)");
			params.put("typeTime", typeTime);
			params.put("userId", userItem.getId());
			HashMap<String, Object> ret = new HashMap<>();
			List<Map<String,Object>> result = new ArrayList<>();
			if(typeChart.equals("mileage")) {
				result = mileageHistoryService.listPointChart(params);
				ret.put("unit", "P");
			} else {
				result = mileageHistoryService.listActiveNumberChart(params);
				ret.put("unit", "번");
			}
			
			ret.put("chart", result);
			
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
	} 
	
	//history_rank screen
	@RequestMapping( value="/ranking",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> ranking(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam(required =false) String city ,
	@RequestParam(required =false) String district ,
	@RequestParam String typeArea ,
	@RequestParam String typeChart
	) {
		UserItem userItem = EncryptionUtils.jwtParse(token, UserItem.class);
		
		try {
			HashMap<String, Object> ret = new HashMap<>();
			
			List<Map<String,Object>> result = new ArrayList<>();
			HashMap<String, Object> params = new HashMap<>();
			if(typeArea.equals("city")) {
				params.put("city", city);
				params.put("district", district);
			}else if(typeArea.equals("hometown")) {
				User u = userService.findById(userItem.getId());
				if(u.getCity() != null) {
					params.put("city", u.getCity());
				}else {
					params.put("city", "");
				}
				if(u.getDistrict() != null) {
					params.put("district", u.getDistrict());
				}else {
					params.put("district", "");
				}
				
			}
			
			params.put("typeArea", typeArea);
			params.put("typeChart", typeChart);
			params.put("userId", userItem.getId());
			
			if(typeChart.equals("mileage")) {
				ret.put("unit", "km");
			} else if(typeChart.equals("point")){
				ret.put("unit", "P");
			}else {
				ret.put("unit", "번");
			}
			Short[] type = new Short[1];
			type[0] = ApplicationDefine.MileageHistory_Type.Bike.getCode();
			
			Float userMileage = mileageHistoryService.countTotalMileageByUserId(userItem.getId(),type);
			result = mileageHistoryService.listRanking(params);
			ret.put("userId", userItem.getId());
			ret.put("userMileage", userMileage);
			ret.put("listRank", result);
			
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
	} 
	
	//history_rank_detail screen
	@RequestMapping( value="/ranking-detail",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> ranking_detail(
	@RequestHeader(value="token",required=false) String token,
	@RequestParam Integer userId
	) {
		try {
			HashMap<String, Object> ret = new HashMap<>();
			User u = userService.findById(userId);
			Short[] type = new Short[1];
			type[0] = ApplicationDefine.MileageHistory_Type.Bike.getCode();
			ret.put("fullname", u.getFullname());
			ret.put("userId", u.getId());
			ret.put("avatarUrl", u.getAvatarUrl());
			if(u.getRoleId() == 1) {
				ret.put("roleId", 1);
			}else {
				ret.put("roleId", 2);
			}

			
			ret.put("totalMileage", mileageHistoryService.countTotalMileageByUserId(userId, type));
			ret.put("totalPoint", mileageHistoryService.countPointByUserId(userId, type));
			ret.put("activeNumber",mileageHistoryService.countActiveByUserId(userId,type));
			
			
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
	} 
	
	@RequestMapping( value="/paging",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAllPaging(
	@RequestParam(value="" , defaultValue ="1") Integer page,
	@RequestHeader(value="token",required=false) String token,
	HttpSession session
	) throws ParseException {
		UserItem userItem = EncryptionUtils.jwtParse(token, UserItem.class);
		HashMap<String, Object> ret =new HashMap<String, Object>();
		try {
			Page<MileageHistoryEntity> list = mileageHistoryService.findAll(page);
			if(list.getContent()!=null){
				ret.put("list", list.getContent());
				ret.put("page", page);
				ret.put("totalPage", list.getTotalPages());
				return SUCCESS(ret);
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	} 


	@RequestMapping( value="/item/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findOne(
	@PathVariable("id") Integer id,
	HttpSession session,
	@RequestHeader(value="token",required=false) String token
		) {
		UserItem userItem = EncryptionUtils.jwtParse(token, UserItem.class);
		try {
			MileageHistory item= mileageHistoryService.findById(id);
			if(item!=null){
				return SUCCESS(item);
			}else{
				return FAIL();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	

	
	

}




package org.ecomileage.rest.controller;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lgcns.kmpay.dto.DealApproveDto;
//import com.lgcns.kmpay.service.CallWebService4NS;

import org.ecomileage.bean.UserItem;
import org.ecomileage.bean.VehicleCode;
import org.ecomileage.bean.Campaign;
import org.ecomileage.bean.Category;
import org.ecomileage.bean.MileageApply;
import org.ecomileage.bean.MileageHistory;
import org.ecomileage.bean.Payment;
import org.ecomileage.bean.Sponsor;
import org.ecomileage.bean.Team;
import org.ecomileage.bean.TeamMember;
import org.ecomileage.bean.jpa.AddressEntity;
import org.ecomileage.bean.jpa.CampaignEntity;
import org.ecomileage.bean.jpa.CategoryEntity;
import org.ecomileage.bean.jpa.PaymentEntity;
import org.ecomileage.bean.jpa.TeamMemberEntity;
import org.ecomileage.bean.jpa.UserEntity;
import org.ecomileage.business.service.AddressService;
import org.ecomileage.business.service.CampaignService;
import org.ecomileage.business.service.CategoryService;
import org.ecomileage.business.service.MileageApplyService;
import org.ecomileage.business.service.PaymentService;
import org.ecomileage.business.service.SponsorService;
import org.ecomileage.business.service.TeamMemberService;
import org.ecomileage.business.service.TeamService;
import org.ecomileage.business.service.UserService;
import org.ecomileage.business.service.VehicleCodeService;
import org.ecomileage.rest.common.AbstractRestController;
import org.ecomileage.web.common.utils.ConstantCommon;
import org.ecomileage.web.common.utils.DataUtils;
import org.ecomileage.web.common.utils.EncryptionUtils;
import org.ecomileage.web.common.utils.KakaoPay;
import org.ecomileage.web.model.SponsorForm;
import org.ecomileage.web.model.SponsorTemForm;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;

//import net.sf.json.JSONObject;
/**
 * Spring MVC controller for 'Campaign' management.
 */
/**
 * @author Kenny Phong
 *
 */
@RequestMapping("/ajax")
@Controller
public class AjaxController extends AbstractRestController{

	@Resource
	private CampaignService campaignService;
	@Resource
	private VehicleCodeService vehicleCodeSerivce;
	@Resource
	private UserService userService;
	@Resource
	private TeamService teamService;
	@Resource
	private SponsorService sponsorService;
	@Resource
	private TeamMemberService teamMemberService;
	@Resource
	private AddressService addressService;
	@Resource
	private MileageApplyService mileageApplyService;
	@Resource
	private PaymentService paymentService;
	@Resource
	private CategoryService categoryService;
	
	//all function for call ajax
//	@SuppressWarnings("unchecked")
//	@RequestMapping( value="/kakaopay/txnid",
//			method = RequestMethod.POST,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//	@ResponseBody
//	public HashMap<String, Object> getTxnId(HttpServletRequest request,
//			HttpSession session,
//			@ModelAttribute DealApproveDto approveDto
//			) {
//		// 가맹점에서 MPay로 전문을 보내기 위한 객체 생성
//		// 타임아웃 설정
//		int timeOut = 20;
//		// 모듈이 설치되어 있는 경로 설정
//		CallWebService4NS webService = new CallWebService4NS(timeOut);
//
//		// 로그 디렉토리 생성
//		webService.setLogHome(KakaoPay.logHome);
//		// 프로퍼티 위치지정
//		webService.setKMPayHome(KakaoPay.cnsPayHome);
//		
//		// 서버로부터 받은 결과값 저장 JSONObject
//	    JSONObject  resultJSONObject =  new JSONObject();
//	    HashMap<String, Object> result = null;
//		try {
//		    resultJSONObject = webService.requestDealApprove(approveDto);
//			result = new ObjectMapper().readValue(resultJSONObject.toString(), HashMap.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	    
//		return SUCCESS(result);
//	}
	
	@RequestMapping(value = "/check-serial-number", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> serial(
			@RequestParam String serialNumber,
			@RequestParam Short typeVehicle
			) {
		try {
			VehicleCode v =vehicleCodeSerivce.findBySerialNumberAndTypeVehicle(serialNumber, typeVehicle);
			if(v != null) {
				return FAIL();
			}else {
				return SUCCESS();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping(value = "/get_districts",method = RequestMethod.POST)
	@ResponseBody
	public Object get_districts(
			@RequestParam(value = "cityId", defaultValue = "") Integer cityId,
			HttpSession session,
			ModelMap map) {
    	try {
    		List<AddressEntity> districts = addressService.findByParentId(cityId);
    		return SUCCESS(districts);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL();
		}
	}
	
	@RequestMapping(value = "/find-user", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> find(
			@RequestParam String username
			) {
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("type", "username");
			params.put("key", username);
			Page<UserEntity> list = userService.filterAdmin(params, 1, 30);
			return SUCCESS(list.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping(value = "/find-member-group", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> find_member(
			@RequestParam Integer groupId,
			@RequestParam(defaultValue="") String username
			) {
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("groupId", groupId);
			params.put("username", username);
			Page<TeamMemberEntity> list = teamMemberService.filterAdmin(params, 1, 30);
			List<UserEntity> listUser = new ArrayList<>();
			for (TeamMemberEntity i : list) {
				listUser.add(i.getUser());
			}
			return SUCCESS(listUser);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping(value = "/find-member-add-group", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> find_member_add(
			@RequestParam Integer groupId
			) {
		try {
			HashMap<String, Object> params = new HashMap<>();
			params.put("groupId", groupId);
			Page<TeamMemberEntity> list = teamMemberService.filterAdmin(params, 1, 30);
			List<UserEntity> listUser = new ArrayList<>();
			for (TeamMemberEntity i : list) {
				listUser.add(i.getUser());
			}
			return SUCCESS(listUser);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping( value="/update-group-manager",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> accpet(
	@RequestParam(value= "groupId", required = true) Integer groupId,
	@RequestParam(value= "groupManagerId", required = true) Integer groupManagerId,
	HttpSession session,
	@RequestHeader(value="token",required=false) String token
		) {
		try {
			HashMap<String, Object> ret = new HashMap<>();

			TeamMemberEntity tm = teamMemberService.findByUserIdAndGroupId(groupManagerId, groupId);
			tm.setStatus(ApplicationDefine.TeamMember_Status.Accept.getCode());
			tm.setUpdateDate(new Date());
			tm.setIsManager(true);
			teamMemberService.update(tm);
			teamMemberService.update_Manager(groupId, groupManagerId);
			
			return SUCCESS(ret);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	
	@RequestMapping( value="/add-group-member",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> add_group_member(
	@RequestParam(value= "groupId", required = true) Integer groupId,
	@RequestParam(value= "userId", required = true) Integer userId,
	HttpSession session,
	@RequestHeader(value="token",required=false) String token
		) {
		try {
			HashMap<String, Object> ret = new HashMap<>();

			TeamMemberEntity tm = teamMemberService.findByUserIdAndGroupId(userId, groupId);
			if(tm == null) {
				TeamMember t = new TeamMember();
				t.setCreateDate(new Date());
				t.setGroupId(groupId);
				t.setIsDelete(false);
				t.setIsManager(false);
				t.setMemberId(userId);
				t.setUpdateDate(new Date());
				teamMemberService.create(t);
				ret.put("fullGroup", false);
				return SUCCESS(ret);
			}else {
				return FAIL("This user joined this group");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR("HAVE ERROR: "+e);
		}
		
	}
	
	@RequestMapping(value = "/find-bike", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findbike(
			@RequestParam String serialNumber,
			@RequestParam Integer userId
			) {
		try {
			VehicleCode v = vehicleCodeSerivce.findBySerialNumberAndUserId(serialNumber, userId);
			if(v != null) {
				return SUCCESS();
			}else {
				return FAIL();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping(value = "/edit-group-name", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> edit_group_name(
			@RequestParam Integer groupId,
			@RequestParam String groupName
			) {
		try {
			Team t = teamService.findById(groupId);
			if(t != null) {
				t.setName(groupName);
				teamService.update(t);
				return SUCCESS(groupName);
			}else {
				return FAIL();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
	@RequestMapping(value = "/sponsor/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			HttpSession session,
			ModelMap map) {
    	try {
        	Sponsor sponsor = sponsorService.findById(id);
        	sponsor.setIsDelete(true);
        	sponsor.setUpdateDate(new Date());
        	sponsor.setDeleteDate(new Date());
        	sponsor = sponsorService.update(sponsor);
        	
        	return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL();
		}
	}
	
	@RequestMapping(value = "/sponsor/create",method = RequestMethod.POST)
	@ResponseBody
	public Object create(
			@ModelAttribute SponsorForm item,
			HttpSession session,
			ModelMap map) {
    	try {
    		UserEntity user = userService.findByUsername(item.getUsername());
    		if (user == null) {
    			return FAIL();
    		}
			Sponsor sponsor = new Sponsor();
			sponsor.setUserId(user.getId());
			sponsor.setMembershipFeeTypeId(item.getMembershipFeeTypeId());
			sponsor.setMembershipFeeId(item.getMembershipFeeId());
			if (item.getMembershipFeeId() == 7) {
				sponsor.setMembershipFee(item.getMembershipFee());
			} else {
				sponsor.setMembershipFee(Integer.valueOf(categoryService.findOne(item.getMembershipFeeId()).getDecription()));
			}
			
			sponsor.setBankId(item.getBankId());
			sponsor.setAccountNumber(item.getAccountNumber());
			sponsor.setFullname(item.getFullname());
			sponsor.setPhone(item.getPhone());
			sponsor.setEmail(item.getEmail());
			sponsor.setAddress(item.getAddress());
			sponsor.setAddressDetail(item.getAddressDetail());
			sponsor.setPostcode(item.getPostcode());
			sponsor.setAccountName(item.getAccountName());
			sponsor.setDayOfBirth(item.getDayOfBirth());
			sponsor.setIsReviceReceipt(item.getIsReviceReceipt());
			sponsor.setIdentityCardNumber(item.getIdentityCardNumber1() + "-" + item.getIdentityCardNumber2());
			if (item.getSponsorDate() != null) {
				sponsor.setSponsorDate(DataUtils.parseDate(item.getSponsorDate(), "yyyy-MM-dd"));
			} else {
				sponsor.setSponsorDate(new Date());
			}
			sponsor.setReviceUserName(item.getReviceUserName());
			sponsor.setUpdateDate(new Date());
			sponsor.setCreateDate(new Date());
			sponsor = sponsorService.create(sponsor);
        	return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL();
		}
	}
	
	
	@RequestMapping(value = "/mileageApply/bike/change_status",method = RequestMethod.POST)
	@ResponseBody
	public Object change_status(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "status", defaultValue = "") Short status,
			HttpSession session,
			ModelMap map) {
    		try {
    			MileageApply mApply = mileageApplyService.findById(id);
    			if (status == 2) {
    				PaymentEntity payment = paymentService.findByMileageApplyId(id);
    				if (payment != null) {
    					paymentService.delete(payment.getId());
					}
				}
    			
    			mApply.setStatus(status);
    			mApply.setUpdateDate(new Date());
    			mApply = mileageApplyService.update(mApply);
    			return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL();
		}
	}
	
	/*
	 * Page name: transfer_mileage_detail_1
	 * Method   : POST
	 * Function : change typeSend
	 * Purpose  : change 발송전 status to 발송완료 status
	 * */
	@RequestMapping(value = "/mileageApply/transfer/change_typeSend",method = RequestMethod.POST)
	@ResponseBody
	public Object change_typeSend(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "couponNumber", defaultValue = "") String couponNumber,
			@RequestParam(value = "expiredCouponTime", defaultValue = "") String expiredCouponTime,
			HttpSession session,
			ModelMap map) {
		try {
			MileageApply mApply = mileageApplyService.findById(id);
			mApply.setTypeSend(ApplicationDefine.MileageApply_TypeSend.Send.getCode());
			mApply.setCouponNo(couponNumber);
			mApply.setExpiredCouponTime(DataUtils.parseDate(expiredCouponTime, "yyyy-MM-dd"));
			mApply.setSendCouponTime(new Date());
			mApply.setUpdateDate(new Date());
			mApply = mileageApplyService.update(mApply);
			return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL();
		}
	}
	
	/*
	 * Page name: sponsor_tem_write
	 * Method   : POST
	 * Function : create new sponsor TEM
	 * */
	@RequestMapping(value = "/sponsor/tem/create",method = RequestMethod.POST)
	@ResponseBody
	public Object createSponsorTem(
			@ModelAttribute SponsorTemForm item,
			HttpSession session,
			ModelMap map) {
    	try {
    		UserEntity user = userService.findByUsername(item.getUsername());
    		if (user == null) {
    			return FAIL("User does not exist.");
    		}
    		Payment payment = new Payment();
    		payment.setUserId(user.getId());
    		payment.setMoney(item.getMoney());
    		payment.setTypeMoney(ApplicationDefine.Payment_TypeMoney.Money.getCode());
    		payment.setTypeSponsor(ApplicationDefine.Payment_TypeSponsor.Temporar.getCode());
    		payment.setTypeUse(ApplicationDefine.Payment_TypeUse.Donate.getCode());
    		payment.setStatus(ApplicationDefine.Payment_Status.Watting.getCode());
    		payment.setFullname(item.getFullname());
    		payment.setPhone(item.getPhone());
    		payment.setPostcode(item.getPostcode());
    		payment.setAddress(item.getAddress());
    		payment.setAddressDetail(item.getAddressDetail());
    		payment.setEmail(item.getEmail());
    		payment.setCreateDate(DataUtils.parseDate(item.getCreateDates(), "yyyy-MM-dd"));
    		payment.setPaymentMethod(item.getPaymentMethod());
    		payment.setUpdateDate(new Date());
    		
    		payment = paymentService.create(payment);
        	return SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("Please check information again.");
		}
	}
	
}




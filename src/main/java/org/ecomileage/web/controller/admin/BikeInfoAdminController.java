/*
 * Created on 13 thg 12 2017 ( Time 10:27:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.web.controller.admin;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ModelAttribute;
//--- Common classes
import org.ecomileage.web.common.AbstractController;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;
import org.ecomileage.web.common.Pager;
import org.ecomileage.web.common.utils.ConstantUtils;
import org.ecomileage.web.common.utils.DataUtils;
import org.ecomileage.web.common.utils.ExcelUtils;
import org.ecomileage.web.common.utils.FileUtils;
import org.ecomileage.bean.jpa.AddressEntity;
import org.ecomileage.bean.jpa.SupportBikeCenterEntity;
//--- Entities
import org.ecomileage.bean.SupportBikeCenter;
import org.ecomileage.bean.UserItem;
import org.ecomileage.business.service.AddressService;
//--- Services 
import org.ecomileage.business.service.SupportBikeCenterService;

/**
 * Spring MVC controller for 'SupportBikeCenter' management.
 */
@Controller
@RequestMapping("/auth/bikeInfo")
public class BikeInfoAdminController extends AbstractController {
	//--- Variables names ( to be used in JSP with Expression Language )
	private static final Integer PAGE_SIZE   = 10;
	private static String nav = "supportBikeCenter";
	private static final String JSP_LIST   = "admin/supportBikeCenter/list";
	//--- Main entity service
	@Resource
    private SupportBikeCenterService supportBikeCenterService; // Injected by Spring
	@Resource
    private AddressService addressService;
	//--- Other service(s)

	/*
	 * Page name: bike-info_list
	 * Method   : GET
	 * Purpose  : get bike-info list
	 * */
	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam Map<String, Object> params,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		
		List<AddressEntity> citys = addressService.findByParentId(0);
		if (params.get("city") != null && params.get("city") != "") {
			AddressEntity city = addressService.findOneByParentName(String.valueOf(params.get("city")));
			List<AddressEntity> districts = addressService.findByParentId(city.getId());
			model.addAttribute("districts", districts);
		}
		model.addAttribute("citys", citys);
		
		Page<SupportBikeCenterEntity> listPage = supportBikeCenterService.filterAdmin(params, page, PAGE_SIZE);
		model.addAttribute("activePage", nav);
		new Pager<SupportBikeCenterEntity>(listPage).setSetting(model, request);		
		return getView();
	}
	
	/*
	 * Page name: bike-info_list
	 * Method   : POST
	 * Function : download data by searching
	 * Purpose  : export to CSV or XLS file
	 * */
	@RequestMapping("/list/download")
	public void bus_download(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam Map<String, Object> params,
			HttpSession session,
			HttpServletResponse response,
			Model model) {
		try {
			params.put("typeVehicle", ApplicationDefine.VehicleCode_TypeVehicle.Bus.getCode());
			Page<SupportBikeCenterEntity> listPage = supportBikeCenterService.filterAdmin(params, page, Integer.MAX_VALUE);
			List<Object> header = new ArrayList<>();
			header.add("시설 구분");
			header.add("주소");
			header.add("상호/명칭/역명");
			header.add("전화번호");
			header.add("홈페이지");
			header.add("보관대수 / 실제 주차대수");
			header.add("이용요금");
			header.add("영업시간");
			header.add("정보출처");
			header.add("정보제공일");
			List<List<Object>> listData = null;
			if (listPage.getContent() != null) {
				listData = new ArrayList<>();
				int index =0;
				for (SupportBikeCenterEntity i : listPage.getContent()) {
					List<Object> listCell = new ArrayList<Object>();
					if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.RentalBicycle.getCode()) {
						listCell.add("자전거 대여소");
					}else if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.BikePark.getCode()){
						listCell.add("자전거 보관소");
					}else {
						listCell.add("자전거 수리/판매점");
					}
					listCell.add(i.getAddress());
					listCell.add(i.getName());
					listCell.add(i.getPhone());
					listCell.add(i.getHomepage());
					if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.RentalBicycle.getCode()) {
						listCell.add(i.getTotalBikeParking());
					}else if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.BikePark.getCode()){
						listCell.add(i.getTotalBikeParking()+"/"+i.getTotalBikeParkingFact());
					}else {
						listCell.add("");
					}
					if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.RentalBicycle.getCode()) {
						listCell.add(i.getFeeRental());
					}else {
						listCell.add("");
					}
					if(i.getType() == ApplicationDefine.SupportBikeCenter_Type.BuyRepairBicycle.getCode()) {
						listCell.add(i.getOpenTime()+"~"+i.getCloseTime());
					}else {
						listCell.add("");
					}
					listCell.add(i.getSourceInformation());
					listCell.add(DataUtils.parseStringFromDate(i.getProvideInformationDate(), "yyyy-MM-dd"));
					listData.add(listCell);
					index+=1;
				}
			}
			
			ExcelUtils.exportDocumentFile(response, String.valueOf(params.get("typeExcel")), header, listData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Page name: bike-info_register
	 * Method   : GET
	 * Purpose  : only get page
	 * */
	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		List<AddressEntity> citys = addressService.findByParentId(0);
		List<AddressEntity> districts = addressService.getDistricts();
		model.addAttribute("districts", districts);
		model.addAttribute("citys", citys);
		model.addAttribute("activePage", nav);
		model.addAttribute("googleMapkey", ConstantUtils.getConfig("googleMapKey"));
		return getView();
	}

	/*
	 * Page name: bike-info_register
	 * Method   : POST
	 * Purpose  : save information to database that user enter 
	 * */
	@RequestMapping(value = "/create", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute SupportBikeCenter supportBikeCenter,
			@RequestParam String provideDate,
			@RequestParam MultipartFile file,
			Model model) {
		try {
			UserItem user = Login.getUserLogin(session);
			supportBikeCenter.setUserId(user.getId());
			if (provideDate != null) {
				supportBikeCenter.setProvideInformationDate( DataUtils.parseDate(provideDate, "yyyy-MM-dd") );
			}
			
			try {
				if(file.getSize() > 0) {
					supportBikeCenter.setImageUrl( FileUtils.saveFile(file) );
					supportBikeCenter.setImageName( file.getOriginalFilename() );
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			supportBikeCenter.setCreateDate( new Date() );
			supportBikeCenter.setUpdateDate( new Date() );
			
			supportBikeCenterService.create(supportBikeCenter);
			return redirectList();
		} catch(Exception e) {
			e.printStackTrace();
			return getView();
		}
	}

	/*
	 * Page name: bike-info_detail
	 * Method   : GET
	 * Purpose  : only get page with param is "id" field from jsp page
	 * */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@RequestParam Integer id,
	Model model  ) {
		SupportBikeCenter supportBikeCenter = supportBikeCenterService.findById(id);
		try {
			List<AddressEntity> citys = addressService.findByParentId(0);
			List<AddressEntity> districts = addressService.getDistricts();
			model.addAttribute("districts", districts);
			model.addAttribute("citys", citys);
			model.addAttribute("item", supportBikeCenter);
			model.addAttribute("googleMapkey", ConstantUtils.getConfig("googleMapKey"));
			return getView();
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	/*
	 * Page name: bike-info_detail
	 * Method   : POST
	 * Purpose  : save information to database that user enter and want to change
	 * */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(
	@ModelAttribute SupportBikeCenter item,
	HttpSession session,
	@RequestParam Integer id,
	@RequestParam(defaultValue="0") Integer delete,
	@RequestParam String provideDate,
	@RequestParam MultipartFile file,
	Model model  ) {
		if(delete > 0) {
			supportBikeCenterService.delete(delete);
			return redirectList();
		}
		UserItem user = Login.getUserLogin(session);
		SupportBikeCenter supportBikeCenter = supportBikeCenterService.findById(id);
		try {
			supportBikeCenter.setUserId(user.getId());
			supportBikeCenter.setLatitude( item.getLatitude() );
			supportBikeCenter.setLongitude( item.getLongitude() );
			supportBikeCenter.setName( item.getName() );
			supportBikeCenter.setType( item.getType() );
			supportBikeCenter.setPostCode( item.getPostCode() );
			supportBikeCenter.setFeeRental( item.getFeeRental() );
			supportBikeCenter.setTrademark( item.getTrademark() );
			supportBikeCenter.setHomepage( item.getHomepage() );
			supportBikeCenter.setAddress( item.getAddress() );
			supportBikeCenter.setPhone( item.getPhone() );
			supportBikeCenter.setOpenTime( item.getOpenTime() );
			supportBikeCenter.setTotalBikeParking( item.getTotalBikeParking() );
			supportBikeCenter.setCloseTime( item.getCloseTime() );
			supportBikeCenter.setTotalBikeParkingFact( item.getTotalBikeParkingFact() );
			supportBikeCenter.setTotalDoors( item.getTotalDoors() );
			supportBikeCenter.setSourceInformation( item.getSourceInformation() );
			supportBikeCenter.setPumpBike(item.getPumpBike() );
			supportBikeCenter.setCity(item.getCity());
			supportBikeCenter.setDistrict(item.getDistrict());
			supportBikeCenter.setSubname( item.getSubname() );
			supportBikeCenter.setProvideInformationDate( DataUtils.parseDate(provideDate, "yyyy-MM-dd") );
			try {
				if(file.getSize() > 0) {
					supportBikeCenter.setImageUrl( FileUtils.saveFile(file) );
					supportBikeCenter.setImageName( file.getOriginalFilename() );
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			supportBikeCenter.setUpdateDate( new Date() );
			supportBikeCenter = supportBikeCenterService.update(supportBikeCenter);
			model.addAttribute("item", supportBikeCenter);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return redirectList();
	}
	
	

}

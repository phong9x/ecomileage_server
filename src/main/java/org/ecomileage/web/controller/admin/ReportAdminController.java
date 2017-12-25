/*
 * Created on 7 thg 12 2017 ( Time 15:57:28 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.web.controller.admin;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.ecomileage.web.common.AbstractController;
import org.ecomileage.web.common.Pager;
import org.ecomileage.web.common.utils.ConstantCommon;
import org.ecomileage.bean.jpa.ReportEntity;
import org.ecomileage.bean.Report;
import org.ecomileage.business.service.ReportService;
import org.ecomileage.business.service.UserService;

@Controller
@RequestMapping("/auth/report")
public class ReportAdminController extends AbstractController {
	private static final Integer PAGE_SIZE   = 10;
	private static String nav = "report";
	private static final String JSP_LIST   = "admin/report/list";
	
	@Resource
    private ReportService reportService;
	@Resource
    private UserService userService;

	/*
	 * Page name: report_list
	 * Method   : GET
	 * Purpose  : get report list
	 * */
	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam Map<String, Object> params,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		Page<ReportEntity> listPage = reportService.filterAdmin(params, page, PAGE_SIZE);
		model.addAttribute("activePage", nav);
		model.addAttribute("list", listPage.getContent());
		model.addAttribute("param", params);
		new Pager<ReportEntity>(listPage).setSetting(model, request);		
		return getView();
	}

	/*
	 * Page name: report_detail
	 * Method   : GET
	 * Purpose  : only get page with param is "id" field from jsp page
	 * */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String update(HttpSession session, @RequestParam Map<String, Object> params, Model model) {
		Integer    id = Integer.valueOf(params.get("id").toString());
		ReportEntity report = reportService.findOne(id);
		try {
			Double jsonLat = 37.5652894;
			Double jsonLong = 126.8494632;
			if (report.getLatitude() != null && report.getLongtitude() != null) {
				jsonLat = report.getLatitude();
				jsonLong = report.getLongtitude();
			}
			
			model.addAttribute("item", report);
			model.addAttribute("jsonLat" , jsonLat);
			model.addAttribute("jsonLong" , jsonLong);
			model.addAttribute("activePage", nav);
			return getView();
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	/*
	 * Page name: report_detail
	 * Method   : POST
	 * Function : change the approval status
	 * Purpose  : save status that user enter and want to change
	 * */
	@RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
	@ResponseBody
	public Object process_approval(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "status", defaultValue = "") Short status,
			HttpSession session,
			ModelMap map) {
    		try {
    			Report report = reportService.findById(id);
    			report.setStatus(status);
    			report.setUpdateDate(new Date());
    			report = reportService.update(report);
    			return SUCCESS_(ConstantCommon.RESPONSE_STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL_(ConstantCommon.RESPONSE_STATUS_FAIL);
		}
	}
	
	/*
	 * Page name: report_detail
	 * Method   : POST
	 * Purpose  : save information to database that user enter and want to change
	 * */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@ModelAttribute Report item, HttpSession session, @PathVariable("id") Integer id,Model model) {
		Report report = reportService.findById(id);
		try {
			report.setLatitude( item.getLatitude() );
			report.setLongtitude( item.getLongtitude() );
			report.setTitle( item.getTitle() );
			report.setContent( item.getContent() );
			report.setProfileImage( item.getProfileImage() );
			report.setProfileUrl( item.getProfileUrl() );
			report.setUpdateDate( new Date() );
			report.setStatus( item.getStatus() );
			report = reportService.update(report);
			model.addAttribute("item", report);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return redirectView();
	}
}

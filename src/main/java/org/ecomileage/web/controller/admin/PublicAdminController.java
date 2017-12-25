/*
 * Created on 2 thg 10 2017 ( Time 14:49:22 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.web.controller.admin;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//--- Common classes
import org.ecomileage.web.common.AbstractController;
import org.ecomileage.web.common.ApplicationDefine;
import org.ecomileage.web.common.Login;
import org.ecomileage.web.common.utils.EncryptionUtils;
import org.ecomileage.bean.User;
import org.ecomileage.bean.UserItem;
//--- Entities
//--- Services 
import org.ecomileage.business.service.UserService;

@Controller
@RequestMapping("")
public class PublicAdminController extends AbstractController {
	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_INDEX   = "/auth/campaign";
	private static final String JSP_LOGIN   = "/login";
	//Resource
	@Resource
	private UserService userService;
	
	/*
	 * Page name: login
	 * Method   : POST
	 * Purpose  : login to ADMIN page
	 * */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			@RequestParam(value="username",defaultValue="") String username,
			@RequestParam(value="password",defaultValue="") String password,
			@RequestParam(value = "remember", defaultValue = "") String remember,
			Model model, HttpSession session, HttpServletResponse response) {
		if(username == null || password == null){
			model.addAttribute("username", username);
			model.addAttribute("login_success", 0);
			return getView();
		}
		Integer roleIds[] = new Integer[1];
		roleIds[0] = ApplicationDefine.UserRole.Admin.getCode();
		User user = userService.login(username,  EncryptionUtils.jwtBuild(password), roleIds);
		if(user!=null){
			UserItem u = new UserItem();
			u.setId(user.getId());
			Login.saveUser(u, session);
			if (remember != null && remember.equalsIgnoreCase("on")) {
				Cookie name = new Cookie("username", username);
				Cookie pass = new Cookie("password", password);
				// Set expiry date after 24 Hrs for both the cookies.
				name.setMaxAge(60 * 60 * 24);
				pass.setMaxAge(60 * 60 * 24);
				// Add both the cookies in the response header.
				response.addCookie(name);
				response.addCookie(pass);
				model.addAttribute("user", user);
				model.addAttribute("login_success", 1);
			}
			return "redirect:"+JSP_INDEX;
		} else{
			model.addAttribute("login_success", 0);
			return getView();
		}
	}
	
	/*
	 * Page name: login
	 * Method   : GET
	 * Purpose  : only get page
	 * */
	@RequestMapping(value= {"/login"},method=RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		return JSP_LOGIN;
	}
	
	/*
	 * Page name: logout
	 * Method   : GET
	 * Purpose  : logout ADMIN page
	 * */
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		Login.removeUserLogin(session);
		return "redirect:/login";	
	}
	
	/*
	 * Page name: 
	 * Method   : GET
	 * Purpose  : redirect page, if the user has logged, redirect to main page, if not login, redirect to login page
	 * */
	@RequestMapping(value= {"/"},method=RequestMethod.GET)
	public String blank(Model model, HttpSession session) {
		UserItem user = Login.getUserLogin(session);
		if(user == null) {
			return "redirect:"+JSP_LOGIN;
		}else {
			return "redirect:"+JSP_INDEX;
		}
	}
}

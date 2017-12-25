package org.ecomileage.web.common;

import javax.servlet.http.HttpSession;

import org.ecomileage.bean.UserItem;


public class Login {
	
	public static void  saveUser(UserItem user,HttpSession session) {
		session.setAttribute("user", user);
	}
	
	public static boolean checkUserLogin(HttpSession session) {
		if (session.getAttribute("user") != null && session.getAttribute("user")!="" ) {
			return true;
		} 
			return false;
	}
	
	public static UserItem  getUserLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("user");
		return user;
	}

	public  static void   removeUserLogin(HttpSession session) {
		session.setAttribute("user",null);
		
	}
	
	public static boolean checkAdminLogin(HttpSession session) {
		if (session.getAttribute("admin") != null && session.getAttribute("admin")!="" ) {
			return true;
		} 
			return false;
	}
	
	public static void  saveAdmin(UserItem user,HttpSession session) {
		session.setAttribute("admin", user);
	}
	
	public static UserItem  getAdminLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("admin");
		return user;
	}
	
	public  static void   removeAdminLogin(HttpSession session) {
		session.setAttribute("admin",null);
		
	}
}
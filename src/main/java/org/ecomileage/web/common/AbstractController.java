/*
 * Created on 27 thg 9 2017 ( Time 11:37:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.web.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.ecomileage.bean.UserItem;
import org.ecomileage.web.common.Login;

public abstract class AbstractController {
	

	@Autowired
	private HttpServletRequest request;

	protected String getView() {
		return request.getRequestURI();
	}

	protected String redirectView() {
		return "redirect:"+request.getRequestURI();
	}

	protected String redirectList() {
		String uri = request.getRequestURI();
		Integer index = uri.lastIndexOf("/");
		return "redirect:"+uri.substring(0, index)+"/list";
	}

	@ModelAttribute(value = "user")
	protected UserItem getUser() {
		return Login.getUserLogin(request.getSession(true));
	}
	
	@ModelAttribute(value = "admin")
	protected UserItem getAdmin() {
		return Login.getAdminLogin(request.getSession(true));
	}

	protected ResponseEntity<?> SUCCESS() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	protected ResponseEntity<?> SUCCESS(Object data) {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	protected ResponseEntity<?> FAIL(Object data) {
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<?> ERROR() {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<?> ERROR_NULL() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	public static HashMap<String, Object> SUCCESS_(Integer status)
	{
		HashMap<String, Object> ret = new HashMap<String, Object> ();
		ret.put("status", status);
		return ret;
	}
	
	public static HashMap<String, Object> FAIL_(Integer status)
	{
		HashMap<String, Object> ret = new HashMap<String, Object> ();
		ret.put("status", status);
		return ret;
	}
	
	public static HashMap<String, Object> SUCCESS_DATA(Integer status, Object data)
	{
		HashMap<String, Object> ret = new HashMap<String, Object> ();
		ret.put("status", status);
		if (data == null) {
			ret.put("data", null);
		}
		ret.put("data", data);
		return ret;
	}

}
package org.ecomileage.rest.common;
import java.util.Calendar;

import org.ecomileage.bean.User;
import org.ecomileage.bean.UserItem;
import org.ecomileage.web.common.utils.EncryptionUtils;


public class AuthorizationToken {
	public static void main(String[] args){
	}
	public static String generateTokenMobile(User user){
		UserItem u =new UserItem();
		u.setId(user.getId());
		u.setRoleId(user.getRoleId());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		u.setExpiration(cal.getTime().getTime());
		return EncryptionUtils.jwtBuild(u);
	}
	
	public static UserItem convertToObject(String token){
		try {
			return EncryptionUtils.jwtParse(token, UserItem.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean checkLogin(String token){
		try {
			UserItem u= EncryptionUtils.jwtParse(token, UserItem.class);
			if(u!=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	

	
}

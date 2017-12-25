package org.ecomileage.web.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;


public class KakaoPay {

//	public static final String MID = "cnstest25m"; // test MID
//	public static final String encodeKey = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="; // test encodeKey
//	public static final String logHome = "/Users/trams_mac/Desktop/logs/kakaopay";
//	public static final String cnsPayHome = "/data/opt/was/hello/webapps/ROOT/WEB-INF/config";
//	public static final String merchantEncKey = "10a3189211e1dfc6";
//	public static final String merchantHashKey = "10a3189211e1dfc6";
	
	public static final String MID = "KCAIM0001m";
	public static final String encodeKey = "X9I1wExILbxiJdw6q7Yoc5be7vyIEjRsD69s9ImZW6mikPcgcuTzG/IM19hk1k2y+6Cb3YkVsBqpA2Sx7NYqUQ==";
	public static final String logHome = "/opt/was/hello/admin/logs/kakaopay";
	public static final String cnsPayHome = "/opt/was/hello/admin/webapps/ROOT/WEB-INF/config";
	public static final String merchantEncKey = "f4e2f500007b73ce";
	public static final String merchantHashKey = "a3bd22116d4711e4";
		
	//KaKaoPay의 INBOUND 전문 URL SETTING
	public static final String msgName = "merchant/requestDealApprove.dev";
	//PG사의 인증 서버 주소
	public static final String webPath = "https://kmpay.lgcns.com:8443/"; 
	
	public static final String TYPE_MOBILE = "MPM";
	public static final String TYPE_PC = "WPM";
	
	public static final String CHANEL_MOBILE = "2";
	public static final String CHANEL_PC = "4";
	
	
	/**
	 * 기준날짜에서 몇일 전,후의 날짜를 구한다.
	 * @param	sourceTS	기준날짜
	 * @param	day			변경할 일수
	 * @return	기준날짜에서 입력한 일수를 계산한 날짜
	 */
	public static Timestamp getTimestampWithSpan(Timestamp sourceTS, long day) throws Exception {
		Timestamp targetTS = null;

		if (sourceTS != null) {
			targetTS = new Timestamp(sourceTS.getTime() + (day * 1000 * 60 * 60 * 24));
		}

		return targetTS;
	}

	/**
	 * 현재날짜를 YYYYMMDDHHMMSS로 리턴
	 */
	public final synchronized static String getyyyyMMddHHmmss(){
		/** yyyyMMddHHmmss Date Format */
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

		return yyyyMMddHHmmss.format(new Date());
	}


	public class DataEncrypt {
	 MessageDigest md;
	 String strSRCData = "";
	 String strENCData = "";
	 String strOUTData = "";


	 public DataEncrypt() {}



	public String encrypt(String strData) { // 암호화 시킬 데이터
	  try {
	   MessageDigest md = MessageDigest.getInstance("MD5"); // "MD5 형식으로 암호화"
	   md.reset();
	   //byte[] bytData = strData.getBytes();
	   //md.update(bytData);
	   md.update(strData.getBytes());
	    byte[] digest = md.digest();

	  StringBuffer hashedpasswd = new StringBuffer();
	    String hx;

	    for (int i=0;i<digest.length;i++){
	    	hx =  Integer.toHexString(0xFF & digest[i]);
	    	//0x03 is equal to 0x3, but we need 0x03 for our md5sum
	    	if(hx.length() == 1){hx = "0" + hx;}
	    	hashedpasswd.append(hx);

	    }
	    strOUTData = hashedpasswd.toString();
	    byte[] raw = strOUTData.getBytes();
	    byte[] encodedBytes = Base64.encodeBase64(raw);
	    strOUTData = new String(encodedBytes);
	   }
	   catch (NoSuchAlgorithmException e) {
	   System.out.print("암호화 에러" + e.toString());
	  }


	  return strOUTData;  // 암호화된 데이터를 리턴...
	 }

	 public String SHA256(String strData) { // 암호화 시킬 데이터
	  String SHA = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(strData.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

			}
			SHA = sb.toString();
			byte[] raw = SHA.getBytes();
	    byte[] encodedBytes = Base64.encodeBase64(raw);
	    SHA = new String(encodedBytes);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
		}


	} // end class

	public static String SHA256Salt(String strData, String salt) { 
		  String SHA = "";
		  
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.reset();
			sh.update(salt.getBytes());
			byte byteData[] = sh.digest(strData.getBytes());
			
			//Hardening against the attacker's attack
			sh.reset();
			byteData = sh.digest(byteData);
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

			}
			
			SHA = sb.toString();
			byte[] raw = SHA.getBytes();
			byte[] encodedBytes = Base64.encodeBase64(raw);
			SHA = new String(encodedBytes);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			SHA = null;
		}
		
		return SHA;
	}
	
}

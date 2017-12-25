package kr.co.tongkni.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sendsms {
	private  static final String SMSId = "kngt";
	private  static final String SMSPassword = "green4855";
	/**
	 * @param args
	 */
	public void main(String[] args) {

		String smsID= SMSId;			//sms id
		String hashValue=SMSPassword;		//sms password
	
		ServiceSMSSoapProxy sendsms = new ServiceSMSSoapProxy();
		try{
		String senderPhone= "01087017889";
		String receivePhone= "01030184349";
		String smsContent= "Test";
		String test1 = (smsID+hashValue+receivePhone);
		CEncrypt encrypt = new CEncrypt("MD5",test1);
		java.lang.String send=sendsms.sendSMS(smsID,encrypt.getEncryptData(), senderPhone, receivePhone, smsContent);
 			System.out.println("Result Code:"+send);
		}catch(Exception e){
			System.out.println("Exception in main:" +e);
		}
	}
	
	
	public   void send_SMS(String receivePhone, String smsContent){
		String smsID= SMSId;			//sms id
		String hashValue=SMSPassword;		//sms password
		String senderPhone= "027444855";
		ServiceSMSSoapProxy sendsms = new ServiceSMSSoapProxy();
		try{
			
			String test1 = (smsID+hashValue+receivePhone);
			CEncrypt encrypt = new CEncrypt("MD5",test1);
			
			java.lang.String send=sendsms.sendSMS(smsID,encrypt.getEncryptData(), senderPhone, receivePhone, smsContent);
	 		System.out.println("Result Code:"+send);
		}catch(Exception e){
			System.out.println("Exception in main:" +e);
		}
}
  
class CEncrypt
{
    MessageDigest md;
    String strSRCData = "";
    String strENCData = "";

    public CEncrypt(){}
    //When you create duplicate instances brought the constructor to be able to handle at one time.
    public CEncrypt(String EncMthd, String strData)
    {
        this.encrypt(EncMthd, strData);
    }

    //A method for performing an encryption process.
    public void encrypt(String EncMthd, String strData)
   {
       try
      {
          MessageDigest md = MessageDigest.getInstance(EncMthd); // "MD5" or "SHA1"
         byte[] bytData = strData.getBytes();
         md.update(bytData);

         byte[] digest = md.digest();
         for(int i =0;i<digest.length;i++)
         {
          	 strENCData = strENCData + String.format("%02x",digest[i] & 0xFF).toLowerCase();
         }
       }catch(NoSuchAlgorithmException e)
      {
         System.out.print("There is no encryption algorithm.");
      };
    
      //Save the original data.
      strSRCData = strData;
    }

    //The inline accessor functions.
    public String getEncryptData(){return strENCData;}
    public String getSourceData(){return strSRCData;}

    //The method that the data is compared for equality.
    public boolean equal(String strData)
    {
      //Even if the encrypted data is compared ditch , even if the source being compared....
      if(strData == strENCData) return true;
      return false;
    }
	
}
}
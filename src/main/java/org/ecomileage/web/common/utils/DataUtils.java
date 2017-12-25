package org.ecomileage.web.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataUtils {
	
    public static void main(String[] args) throws MalformedURLException, IOException, org.json.simple.parser.ParseException {
    	Float totalCO2InYear = 618.15f;
    	Integer totalGoalCO2 = 20000;
		Float ratioCO2 = (totalCO2InYear/totalGoalCO2)*100;
		System.out.println(ratioCO2);
		System.out.println(Math.round(ratioCO2 * 100d)/100d);
    }
    public static Double calculateTree(Float mileage) {
    	Double tree = mileage/54d;
        return  Math.round(tree * 10.0) / 10.0;
    }
    
    
    public static double distance(double lat1, double lat2, double lon1,
            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

	public enum FormatDate {
        YYYYMMDD("yyyy/MM/dd"), YYYYMM("yyyy/MM"), YYYYMMDDHHMMSS("yyyy/MM/dd HH:mm:ss");

        String pattern;

        FormatDate(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }
	
	public static String getAuthPhoneCode(String srt, String str2) {

        String str = srt + "ML^#09X#" + str2;
        try {
            String hexStr = getMD5(str);
            return hexStr.substring(3, 9).toUpperCase();
        } catch (Exception e) {
        }
        return "";
    }
	
	 public static String getMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            // Now we need to zero pad it if you actually want the full 32 chars.
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
    public static Integer roundInteger(double number, int numberOfRound) {
        double round = Math.pow(10, numberOfRound);
        Double result = (Math.ceil(number / round)*round);
        Integer intValue = result.intValue();
        return intValue;
    }
    
    
    public static long getDaysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
    
    public enum DatePattern {
        YYYYMMDD("yyyy/MM/dd"), YYYYMM("yyyy/MM"), YYYYMMDDHHMMSS("yyyy/MM/dd HH:mm:ss");

        String pattern;

        DatePattern(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

	public static Date parseDate(String dateStr, String formatDate ) {
        DateFormat df = new SimpleDateFormat(formatDate);
        Date startDate;
        try {
            startDate = df.parse(dateStr);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public static Date parseStringToDate(String dateStr, String formatDate ) {
		SimpleDateFormat df = new SimpleDateFormat(formatDate);
        Date startDate;
        try {
            startDate = df.parse(dateStr);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static Integer parseInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }
    
    public static Double parseDouble(Object obj) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0d;
    }

    public static Long parseLong(Object obj) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return (long) 0;
    }

    
    public static Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        final int expiryTime = 60 * 60 * 24;  // 24h in seconds
        cookie.setPath("/");
        cookie.setMaxAge(expiryTime);
        cookie.setSecure(true);
        return cookie;
    }

    public static String formatPhoneNumber(String str) {
        String temp = "";
        Integer a = (Integer) str.length() / 4;
        Integer b = str.length() - (a) * 4;
        int index = 0;
        for (int i = a; i >= 0; i--) {
            if (i == a) {
                temp = str.substring(str.length() - (index * 4 + 4), str.length() - index * 4) + temp;
            } else if (i == 0) {
                temp = str.substring(0, b) + "-" + temp;
            } else {
                temp = str.substring(str.length() - (index * 4 + 4), str.length() - index * 4) + "-" + temp;
            }
            index++;

        }

        return temp;
    }
    
    public static String parseStringFromDate(Date date, String formatDate) {
        try {
            DateFormat df = new SimpleDateFormat(formatDate);
            return df.format(date);
        } catch (Exception e) {
            return null;
        }

    }

	public static Integer getOffset(Integer page, Integer size) {
		int offset = 0;
		if (page > 1) {
			offset = ((page - 1) * size);
		}
		return offset;
	}

}

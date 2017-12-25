package org.ecomileage.web.common.utils;

import javax.annotation.Resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.ecomileage.bean.Tracking;
import org.ecomileage.bean.TrackingList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
public class ServletUtils {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws MalformedURLException, IOException, org.json.simple.parser.ParseException {
		Date now = new Date();
		String content ="7486,21.0303709617419,105.834388348378,0.0,0.0,23.107\n" + 
				"7486,21.0303709617419,105.834388348378,0.0,0.0,23.107\n" + 
				"7486,21.0304367076801,105.834347410226,0.0,0.0,23.107\n" + 
				"7486,21.0304367076801,105.834347410226,0.0,0.0,23.107\n" + 
				"7486,21.0304367076801,105.834347410226,0.0,0.0,23.107\n" + 
				"7486,21.0304367076801,105.834347410226,0.0,0.0,23.107\n" + 
				"7486,21.0303929960551,105.834278175706,0.0,0.0,23.107\n" + 
				"7486,21.0303929960551,105.834278175706,0.0,0.0,23.107\n" + 
				"";
		sendTrackingFileToClientServer(1, 13, now, content);
		//createFileZipByString(content);
    }
	
	public static boolean sendTrackingFileToClientServer(Integer userId, Integer trackId, Date startTime, MultipartFile file) throws ClientProtocolException, IOException, ParseException {
		String url = "http://211.110.44.73/greenbike/index.php/greenbike/do_upload_json";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);
		
		String filename = DataUtils.parseStringFromDate(startTime, "yyyyMMddHHmmss")+"_"+userId+"_"+trackId+"_txt.zip";
		System.out.println(filename);
	    
		MultipartEntityBuilder builder = MultipartEntityBuilder.create(); 
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody
		  ("userfile", file.getInputStream(), ContentType.create("application/zip"), filename);
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
		String str = String.valueOf(jsonObject.get("result"));
		System.out.println(result.toString());
		if(str.equals("ok")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean sendTrackingFileToClientServer(Integer userId, Integer trackId, Date startTime, String contentFile) throws ClientProtocolException, IOException, ParseException {
		try {
			String url = "http://211.110.44.73/greenbike/index.php/greenbike/do_upload_json";

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			// add header
			post.setHeader("User-Agent", USER_AGENT);
			
			String filename = DataUtils.parseStringFromDate(startTime, "yyyyMMddHHmmss")+"_"+userId+"_"+trackId+"_txt.zip";
			System.out.println(filename);
		    
			MultipartEntityBuilder builder = MultipartEntityBuilder.create(); 
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			InputStream stream = compress(contentFile, filename);
			builder.addBinaryBody
			  ("userfile", stream, ContentType.create("application/zip"), filename);
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + post.getEntity());
			System.out.println("Response Code : " +
	                                    response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
	                        new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
			String str = String.valueOf(jsonObject.get("result"));
			System.out.println(result.toString());
			if(str.equals("ok")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static InputStream compress(String content,String entryName) throws IOException {
		InputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8.name()));
        final int BUFFER = 2048;
        byte buffer[] = new byte[BUFFER];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(out);
        zos.putNextEntry(new ZipEntry(entryName));
        int length;
        while ((length = in.read(buffer)) >= 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        zos.close();
        return new ByteArrayInputStream(out.toByteArray());
}

    private static void zip(String[] _files, String zipFileName) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipFileName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte data[] = new byte[1024];

            for (int i = 0; i < _files.length; i++) {
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, 1024);

                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;

                while ((count = origin.read(data, 0, 1024)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public static List<Tracking> getListTracking(String key, Integer userId, String username) throws ClientProtocolException, IOException, ParseException {
		//String url = "http://211.110.44.73/greenbike/index.php/json/gbikeservice/bikelist/key/"+key+"/"+userId+"/"+username;

		String url = "http://211.110.44.73/greenbike/index.php/json/gbikeservice/bikelist/key/1234/userid/trams/2";
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);

		// add header
		get.setHeader("User-Agent", USER_AGENT);
		
		HttpResponse response = client.execute(get);
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		TrackingList tl= new TrackingList();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(result.toString());
		JSONObject jsonObject = (JSONObject) obj;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			tl = mapper.readValue(result.toString(), new TypeReference<TrackingList>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(tl.getList());
		return tl.getList();
	}
}

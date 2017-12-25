package org.ecomileage.web.common.utils;


import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.ResourceUtils;

public class ExcelUtils {
	
	private static HSSFWorkbook template_workbook;
	
	public static void main(String[] args) throws IOException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		OutputStream out;
		try {
			FileInputStream fileInput = new FileInputStream(ResourceUtils.getFile("classpath:excel-template/MemberStatistics.xls"));
			template_workbook = new HSSFWorkbook(fileInput);
			HSSFSheet sheet = template_workbook.getSheetAt(0);
			int columnIndex = 1;
			int rowIndex = 0;
			if (today != null && !today.equals("")) {
				rowIndex++;
			}
			
			for (int i = 0; i < 5; i++) {
				System.out.println("row: "+i);
				if(sheet.getRow(i)== null){
					rowIndex = i;
					System.out.println("row begin: "+ rowIndex);
					break;
				}
			}
			
			//set header
			HSSFRow rowhead = sheet.createRow(rowIndex);
			columnIndex = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void exportDocumentFile(HttpServletResponse response,String typeExcel,
			List<Object> listHeader, List<List<Object>> listData) {
		
		
		if(typeExcel.equals("cvs")) {
			exportCSVFile(response, listHeader, listData);
		}else {
			exportExcelFile(response, listHeader, listData);
		}
		
	}
	
	public static void exportCSVFile(HttpServletResponse response,
			List<Object> listHeader, List<List<Object>> listData) {
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
			String today = formatter.format(new Date());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".csv" + "\";");
			response.setContentType("text/csv");
			
			OutputStream out;
			StringBuilder str = null;
			try {
				out = response.getOutputStream();
				str = new StringBuilder();
				for (int i = 0; i < listHeader.size(); i++) {
					String header = String.valueOf("     "+listHeader.get(i)+"     ");
					if(i > 0) {
						str.append(",");
					}
					str.append(header);
				}
				str.append("\n");
				
				
				//set data
				for (List<Object> listStr : listData) {
					for (int i = 0; i < listStr.size(); i++) {
						if(i > 0) {
							str.append(",");
						}
						str.append(String.valueOf(listStr.get(i)));
					}
					str.append("\n");
				}
				
				System.out.println("CSV file was created successfully !!!");
				try {
					out.write(String.valueOf(str).getBytes());
					out.flush();
					out.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	
	public static void exportExcelFile(HttpServletResponse response,
			List<Object> listHeader, List<List<Object>> listData) {
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
			String today = formatter.format(new Date());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".xls" + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream out;
			try {
				out = response.getOutputStream();
				HSSFWorkbook template_workbook = new HSSFWorkbook();
				HSSFSheet sheet = template_workbook.createSheet("Sheet 1");
				
				final HSSFCellStyle styleHeader = template_workbook.createCellStyle();
				styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
				styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);            
				styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);              
				styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleHeader.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				styleHeader.setTopBorderColor(IndexedColors.BLACK.getIndex());
				styleHeader.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				styleHeader.setRightBorderColor(IndexedColors.BLACK.getIndex());
				styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleHeader.setWrapText(false);
				HSSFFont font = template_workbook.createFont();
				font.setFontHeightInPoints((short)12);
				font.setBold(true);
				styleHeader.setFont(font);
				
				final HSSFCellStyle styleContent = template_workbook.createCellStyle();
				styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
				styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);            
				styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);              
				styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleContent.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				styleContent.setTopBorderColor(IndexedColors.BLACK.getIndex());
				styleContent.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				styleContent.setRightBorderColor(IndexedColors.BLACK.getIndex());
				styleContent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            
				int columnIndex = 1;
				int rowIndex = 0;
				
				//set header
				HSSFRow rowhead = sheet.createRow(rowIndex);
				columnIndex = 0;
				for (int i = 0; i < listHeader.size(); i++) {
					Cell c = rowhead.createCell(i);
					String header = String.valueOf("         "+listHeader.get(i)+"        ");
					c.setCellValue(header);
					c.setCellStyle(styleHeader);
					int width = ((int)(header.length() * 1.14388)) * 256;
					sheet.setColumnWidth(i, width);
				}
				rowIndex++;
				
				//set data
				for (List<Object> listStr : listData) {
					HSSFRow row = sheet.createRow(rowIndex);
					columnIndex = 0;
					for (Object rowItem : listStr) {
						Cell c = row.createCell(columnIndex);
						if(rowItem == null) {
							c.setCellValue("");
						}else {
							c.setCellValue(String.valueOf(rowItem));
						}
						
						c.setCellStyle(styleContent);
						columnIndex++;
					}
					rowIndex++;
				}
				
				try {
					template_workbook.write(out);
					out.flush();
					out.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}

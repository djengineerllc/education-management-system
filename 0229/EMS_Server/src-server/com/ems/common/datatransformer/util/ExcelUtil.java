package com.ems.common.datatransformer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author Chiknin
 *
 */
public class ExcelUtil {
	
	public static HSSFWorkbook getWorkbook(String filePath) {
		InputStream inputStream = null;
		try {
			if (StringUtils.isNotBlank(filePath)) {
				inputStream = new ClassPathResource(filePath).getInputStream();
			}
			
			return getWorkbook(inputStream);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
	public static HSSFWorkbook getWorkbook(InputStream inputStream) {
		if (inputStream != null) {
			try {
				return new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				throw new IllegalArgumentException(e.getMessage(), e);
			}
		} else {
			return new HSSFWorkbook();
		}
	}
	
	public static Object getCellValue(HSSFCell cell) {
		return getCellValue(cell, cell.getCellType());
	}
	public static Object getCellValue(HSSFCell cell, int cellType) {
		if (cell == null) { 
			return null;
		}
		
		Object valueObj = null;
		switch (cellType) {
			case HSSFCell.CELL_TYPE_BLANK: valueObj = null; break;
			case HSSFCell.CELL_TYPE_BOOLEAN: valueObj = cell.getBooleanCellValue(); break;
			case HSSFCell.CELL_TYPE_ERROR: valueObj = cell.getErrorCellValue(); break;
			case HSSFCell.CELL_TYPE_FORMULA: 
				valueObj = getCellValue(cell, cell.getCachedFormulaResultType()); 
			break;
			case HSSFCell.CELL_TYPE_NUMERIC: 
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					valueObj = cell.getDateCellValue();
				} else {
					valueObj = ObjectUtil.removeEndsWithAsString(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_STRING: valueObj = cell.getRichStringCellValue().getString(); break;
		}
		
		return valueObj;
	}
	
	public static HSSFRow getRow(HSSFSheet sheet, int rowIndex) {
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		
		return row;
	}
	
	public static HSSFCell getCell(HSSFRow row, int cellIndex) {
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			cell = row.createCell(cellIndex);
		}
		
		return cell;
	}
	
	public static void setCellValue(HSSFCell cell, Object cellValue) {
		if (cellValue == null) {
			return;
		}
		
		if (cellValue instanceof String) {
			cell.setCellValue(new HSSFRichTextString((String) cellValue));
		} else if (cellValue instanceof Double) {
			cell.setCellValue((Double) cellValue);
		} else if (cellValue instanceof Boolean) {
			cell.setCellValue((Boolean) cellValue);
		} else if (cellValue instanceof Date) {
			cell.setCellValue((Date) cellValue);
		} else if (cellValue instanceof Calendar) {
			cell.setCellValue((Calendar) cellValue);
		} else if (cellValue instanceof HSSFRichTextString) {
			cell.setCellValue((HSSFRichTextString) cellValue);
		} else {
			cell.setCellValue(new HSSFRichTextString(cellValue.toString()));
		}
	}
}
package com.ems.common.datatransformer.handler;

import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ems.common.datatransformer.api.IHandler;
import com.ems.common.datatransformer.definition.ClassMetadataDefinition;
import com.ems.common.datatransformer.util.ObjectUtil;

public class CellStyleHandler implements IHandler {
	public void execute(HandlerContext ctx) {
		HSSFCell cell = (HSSFCell) ctx.getValue();
		if (cell != null) {
			HSSFWorkbook workBook = (HSSFWorkbook) ctx.getAttribute("workBook");
			
			Properties props = new Properties();
			ClassMetadataDefinition refCmd = ctx.getHandlerDefinition().getRefClassMetadataDefinition();
			if (refCmd != null) {
				props.putAll(refCmd.getProps());
			}
			props.putAll(ctx.getHandlerDefinition().getProps());
			
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			ObjectUtil.setProperty(cellStyle, props);
			
//			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			
//			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
////			
//			cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			cell.setCellStyle(cellStyle);
		}
	}
}

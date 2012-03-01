package com.ems.common.datatransformer.factory.object;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ems.common.datatransformer.definition.CellFieldDefinition;
import com.ems.common.datatransformer.definition.CellFieldGroupDefinition;
import com.ems.common.datatransformer.definition.CellFieldSetDefinition;
import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.SheetDefinition;
import com.ems.common.datatransformer.definition.CellFieldDefinition.CellIndex;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.definition.parser.PojoToExcelParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.util.ExcelUtil;
import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 * TODO 导出 cellGroup 后 cell 
 */
public class PojoToExcelTransformer extends AbstractDataTransformer<Object, byte[]> {
	
	public static final String PROPERTY_EXCEL_TEMPLATE = "template";
	
	@Override
	protected byte[] transformImpl(String dtName, Object source,
		DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		PojoToExcelParserDefinition ptepd = (PojoToExcelParserDefinition) pd;
		List<SheetDefinition> sds = ptepd.getSheets();
		
		ValidateFailExceptions allValidateFailExceptions =  new ValidateFailExceptions();
		
		String excelTemplate = pd.getProps().getProperty(PROPERTY_EXCEL_TEMPLATE);
		HSSFWorkbook workBook = ExcelUtil.getWorkbook(excelTemplate);
		if (source instanceof Collection<?>) { // OTHER Map, Array
			Collection<?> sourceCollection = (Collection<?>) source;
			int sheetIndex = 0;
			for (Object sheetObj : sourceCollection) {
				if (sheetIndex >= sds.size()) {
					continue;
				}
				
				SheetDefinition sd = sds.get(sheetIndex);
				HSSFSheet sheet = this.getSheet(workBook, sheetIndex, sd, sheetObj);
				this.sheetProcess(workBook, sheet, sd, ptepd, sheetObj, allValidateFailExceptions);
				
				sheetIndex++;
			}
		} else {
			int sheetIndex = 0;
			SheetDefinition sd = sds.get(sheetIndex);
			HSSFSheet sheet = this.getSheet(workBook, sheetIndex, sd, source);
			this.sheetProcess(workBook, sheet, sd, ptepd, source, allValidateFailExceptions);
		}
		this.handle(workBook, source, pd, dtd.getHandlers(), allValidateFailExceptions);
		
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			workBook.write(baos);
			
			return baos.toByteArray();
		} catch (IOException e) {
			return null;
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	private void sheetProcess(HSSFWorkbook workBook, HSSFSheet sheet, 
			SheetDefinition sd, PojoToExcelParserDefinition pd,
			Object sheetObject, ValidateFailExceptions allValidateFailExceptions) 
	{
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		
		boolean validatePassed = this.validate(sheetObject, sd, sd.getValidator(), pd.getFailMode(), validateFailExceptions);
		if (validatePassed) {
			this.handle(sheetObject, sheetObject, sd, sd.getHandlers(), null);
			
			List<DataFieldDefinition> cfds = sd.getCells();
			for (DataFieldDefinition cfd : cfds) {
				this.cellProcess(workBook, sheet, null, cfd, sd, pd, sheetObject, validateFailExceptions);
			}
			
			this.handle(sheet, workBook, sd, sd.getHandlers(), validateFailExceptions);
		} else {
			this.handle(null, null, sd, sd.getHandlers(), validateFailExceptions);
		}
		allValidateFailExceptions.addException(validateFailExceptions);
	}
	
	private void cellProcess(HSSFWorkbook workBook, HSSFSheet sheet, CellIndex cellIndex,
		DataFieldDefinition cfd, SheetDefinition sd, PojoToExcelParserDefinition pd, 
		Object data, ValidateFailExceptions validateFailExceptions) 
	{
		if (cfd instanceof CellFieldSetDefinition) {
			List<DataFieldDefinition> rdfds = dataTransformerFactory.getDataFieldSet((CellFieldSetDefinition) cfd);
			for (DataFieldDefinition rdfd : rdfds) {
				this.cellProcess(workBook, sheet, ((CellFieldDefinition)rdfd).getCellIndex(), rdfd, sd, pd, data, validateFailExceptions);
			}
		}
		else if (cfd instanceof CellFieldGroupDefinition) {
			CellFieldGroupDefinition cfgd = (CellFieldGroupDefinition) cfd;
			
			int rowIndex = cfgd.getRow();
			Object groupValue = ObjectUtil.getProperty(data, cfgd.getName(), Object.class);
			if (groupValue != null) {
				if (groupValue instanceof Collection<?>) {
					Collection<?> gvs = (Collection<?>) groupValue;
					for (Object gv : gvs) {
						for (CellFieldDefinition gcfd : cfgd.getCells()) {
							this.cellProcess(workBook, sheet, new CellIndex(rowIndex, gcfd.getColumn()), gcfd, sd, pd, gv, validateFailExceptions);
						}
						rowIndex++;
					}
				} else if (groupValue instanceof Object[]) {
					Object[] gvs = (Object[]) groupValue;
					for (Object gv : gvs) {
						for (CellFieldDefinition gcfd : cfgd.getCells()) {
							this.cellProcess(workBook, sheet, new CellIndex(rowIndex, gcfd.getColumn()), gcfd, sd, pd, gv, validateFailExceptions);
						}
						rowIndex++;
					}
				} else {
					for (CellFieldDefinition gcfd : cfgd.getCells()) {
						this.cellProcess(workBook, sheet, new CellIndex(rowIndex, gcfd.getColumn()), gcfd, sd, pd, groupValue, validateFailExceptions);
					}
					rowIndex++;
				}
			}
		}
		else if (cfd instanceof CellFieldDefinition) {
			Object cellValue = null;
			if (StringUtils.isNotBlank(cfd.getName())) {
				cellValue = ObjectUtil.getProperty(data, cfd.getName(), Object.class);
			}
			if (cellValue == null) {
				cellValue = cfd.getValue();
			}
			
			cellValue = this.render(cellValue, cfd, cfd.getRenderers(), RendererDefinition.PHASE_BEFORE);
			
			boolean validatePassed = this.validate(cellValue, cfd, cfd.getValidator(), pd.getFailMode(), validateFailExceptions);
			if (validatePassed) {
				cellValue = this.render(cellValue, cfd, cfd.getRenderers(), RendererDefinition.PHASE_AFTER);
				
				if (cellIndex == null) {
					cellIndex = ((CellFieldDefinition) cfd).getCellIndex();
				}
				
				HSSFRow row = ExcelUtil.getRow(sheet, cellIndex.getRow());
				HSSFCell cell = ExcelUtil.getCell(row, cellIndex.getColumn());
				ExcelUtil.setCellValue(cell, cellValue);
				
				Map<String, Object> attrs = new HashMap<String, Object>();
				attrs.put("workBook", workBook);
				attrs.put("sheet", sheet);
				attrs.put("row", row);
				
				this.handle(cell, row, cfd, cfd.getHandlers(), attrs, null);
			} else {
				this.handle(null, data, cfd, cfd.getHandlers(), validateFailExceptions);
			}
		}
	}

	private HSSFSheet getSheet(HSSFWorkbook workBook, int sheetIndex, SheetDefinition sd, Object bean) {
		HSSFSheet sheet = null;
		try {
			sheet = workBook.getSheetAt(sheetIndex);
		} catch (IndexOutOfBoundsException e) {
			String sheetName = null;
			String propName = sd.getName();
			if (StringUtils.isNotEmpty(propName)) {
				sheetName = ObjectUtil.getProperty(bean, propName);
			}
			if (StringUtils.isEmpty(sheetName)) {
				sheetName = sd.getSheetName();
			}
			
			if (StringUtils.isEmpty(sheetName)) {
				sheet = workBook.createSheet();
			} else {
				sheet = workBook.createSheet(sheetName);
			}
		}
		
		return sheet;
	}
}
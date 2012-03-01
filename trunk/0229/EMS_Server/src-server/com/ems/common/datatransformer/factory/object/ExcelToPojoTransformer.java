package com.ems.common.datatransformer.factory.object;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanWrapper;

import com.ems.common.datatransformer.definition.CellFieldDefinition;
import com.ems.common.datatransformer.definition.CellFieldGroupDefinition;
import com.ems.common.datatransformer.definition.CellFieldSetDefinition;
import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.SheetDefinition;
import com.ems.common.datatransformer.definition.CellFieldDefinition.CellIndex;
import com.ems.common.datatransformer.definition.parser.ExcelToPojoParserDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.util.ExcelUtil;
import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 */
public class ExcelToPojoTransformer extends AbstractDataTransformer<InputStream, Object> {
	
	private static boolean closeInputStream = true;

	@Override
	protected Object transformImpl(String dtName, InputStream source,
		DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		ExcelToPojoParserDefinition etppd = (ExcelToPojoParserDefinition) pd;
		List<SheetDefinition> sds = etppd.getSheets();
		
		List<Object> sheetData = new ArrayList<Object>(sds.size());
		ValidateFailExceptions allValidateFailExceptions =  new ValidateFailExceptions();

		HSSFWorkbook workbook = this.createWorkbook(etppd, source);
		for (int i = 0; i < sds.size(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			this.sheetProcess(sheet, sds.get(i), etppd, sheetData, allValidateFailExceptions);
		}
		this.handle(sheetData, sheetData, dtd, dtd.getHandlers(), allValidateFailExceptions);
		
		if (sds != null && sds.size() == 1 
				&& sheetData != null && sheetData.size() == 1) {
			return sheetData.get(0);
		}
		
		return sheetData;
	}
	
	private void sheetProcess(HSSFSheet sheet, SheetDefinition sd, ExcelToPojoParserDefinition pd, 
		List<Object> sheetData, ValidateFailExceptions allValidateFailExceptions) 
	{
		Object sheetObject = ObjectUtil.instance(sd.getMappingClass());
		BeanWrapper sheetBean = ObjectUtil.buildBeanWrapperImpl(sheetObject);
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		for (DataFieldDefinition dfd : sd.getCells()) {
			this.cellProcess(sheet, null, dfd, sd, pd, sheetBean, validateFailExceptions);
		}
		this.validate(sheetObject, sd, sd.getValidator(), pd.getFailMode(), validateFailExceptions);
		this.handle(sheetObject, sheetData, sd, sd.getHandlers(), validateFailExceptions);
		
		sheetData.add(sheetObject);
	}
	
	private void cellProcess(HSSFSheet sheet, CellIndex cellIndex, DataFieldDefinition cfd, SheetDefinition sd, ExcelToPojoParserDefinition pd, 
		BeanWrapper sheetBean, ValidateFailExceptions validateFailExceptions) 
	{
		if (cfd instanceof CellFieldSetDefinition) {
			List<DataFieldDefinition> rdfds = dataTransformerFactory.getDataFieldSet((CellFieldSetDefinition) cfd);
			for (DataFieldDefinition rdfd : rdfds) {
				this.cellProcess(sheet, ((CellFieldDefinition)rdfd).getCellIndex(), rdfd, sd, pd, sheetBean, validateFailExceptions);
			}
		} else if (cfd instanceof CellFieldGroupDefinition) {
			CellFieldGroupDefinition cfgd = (CellFieldGroupDefinition) cfd;
			List<CellFieldDefinition> gcfds = cfgd.getCells();
			Object groupData = ObjectUtil.instance(cfgd.getMappingClass());
			BeanWrapper groupBean = ObjectUtil.buildBeanWrapperImpl(groupData);
			
			Integer maxRowIndex = (cfgd.getCount() == null) ? sheet.getLastRowNum() : Math.min(((Math.max((cfgd.getCount() - 1), 0)) + cfgd.getRow()), sheet.getLastRowNum());
			for (int rowIndex = cfgd.getRow(), i = 0; rowIndex <= maxRowIndex; rowIndex++, i++) {
				Object itemData = ObjectUtil.instance(cfgd.getItemMappingClass());
				BeanWrapper itemBean = ObjectUtil.buildBeanWrapperImpl(itemData);
				for (CellFieldDefinition gcfd : gcfds) {
					this.cellProcess(sheet, new CellIndex(rowIndex, gcfd.getColumn()), gcfd, sd, pd, itemBean, validateFailExceptions);
				}	
				groupBean.setPropertyValue(String.valueOf(i), itemBean.getWrappedInstance());
			}
			
			sheetBean.setPropertyValue(cfgd.getName(), groupBean.getWrappedInstance());
		} else if (cfd instanceof CellFieldDefinition) {
			if (cellIndex == null) {
				cellIndex = ((CellFieldDefinition)cfd).getCellIndex();
			}
			
			HSSFRow row = sheet.getRow(cellIndex.getRow());
			if (row == null) return;
			HSSFCell cell = row.getCell(cellIndex.getColumn());
			if (cell == null) return;
			
			Object cellValue = ExcelUtil.getCellValue(cell);
			if (cellValue == null) {
				cellValue = cfd.getValue();
			}
			
			cellValue = this.render(cellValue, cfd, cfd.getRenderers(), RendererDefinition.PHASE_BEFORE);
			
			boolean validatePassed = this.validate(cellValue, cfd, cfd.getValidator(), pd.getFailMode(), validateFailExceptions);
			if (validatePassed) {
				cellValue = this.render(cellValue, cfd, cfd.getRenderers(), RendererDefinition.PHASE_AFTER);
				sheetBean.setPropertyValue(cfd.getName(), cellValue);
				
				this.handle(cellValue, sheetBean.getWrappedInstance(), cfd, cfd.getHandlers(), null);
			} else {
				this.handle(null, sheetBean.getWrappedInstance(), cfd, cfd.getHandlers(), validateFailExceptions);
			}
		}
	}
	
	private HSSFWorkbook createWorkbook(ExcelToPojoParserDefinition etppd, InputStream inputStream) {
		try {
			return new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		} finally {
			if (closeInputStream) {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}
}
package com.ems.common.datatransformer.factory.object;

import java.io.ByteArrayOutputStream;

import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;

public class PojoToPdfTransformer extends AbstractDataTransformer<Object, byte[]> {

	@Override
	protected byte[] transformImpl(String dtName, Object source,
		DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		
//		Document doc = new Document();
//		PdfWriter.getInstance(doc, pdfOutputStream);
//		doc.open();
		
		return null;
	}
}
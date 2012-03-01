package com.ems.common.datatransformer.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ems.client.action.biz.samples.common.vo.UserInfoVO;
import com.ems.common.datatransformer.api.IDataTransformer;
import com.ems.common.datatransformer.factory.IDataTransformerFactory;

/**
 * 
 * @author Chiknin
 *
 */
public final class DataTransformerHelper {
	
	private static IDataTransformerFactory dataTransformerFactory;

	@SuppressWarnings("unchecked")
	public static Object transform(String dtName, Object source) {
		IDataTransformer dataTransformer = dataTransformerFactory.getDataTransformer(dtName);
		return dataTransformer.transform(dtName, source);
	}

	public void setDataTransformerFactory(IDataTransformerFactory dtf) {
		dataTransformerFactory = dtf;
	}
	
	public static void main(String[] args) throws IOException {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
		
		List gridList = new ArrayList();
		gridList.add(new UserInfoVO(1, "小萌", "1", "2011-10-10", "xm@163.com"));
		gridList.add(new UserInfoVO(2, "小乐", "2", "2011-11-11", "xl@163.com"));
		gridList.add(new UserInfoVO(3, "小傅", "2", "2011-11-11", "xf@163.com"));
	
		Map rootVO = new HashMap();
		rootVO.put("emailTitle", "电子邮件");
		rootVO.put("list", gridList);
		rootVO.put("sex", "1");
		
//		byte[] bs = (byte[]) DataTransformerHelper.transform("DT_test_excel_export", rootVO);
//		FileOutputStream fos = new FileOutputStream(new File("test_excel.xls"));
//		fos.write(bs);
//		fos.close();
//		
//		InputStream is = new FileInputStream("test_excel.xls");
//		Map l = (Map)DataTransformerHelper.transform("DT_test_excel_import", is);
//		for (Object o : (List)l.get("dataList")) {
////			System.out.println(ToStringBuilder.reflectionToString(o));
//			System.out.println(o);
//		}
		
		rootVO.put("stuName", "xxxxxxxxxxx");
		String data = (String) DataTransformerHelper.transform("DT_print_certificate_transcript", rootVO);
		System.out.println("-----1111112221--->" + data);
	}
}
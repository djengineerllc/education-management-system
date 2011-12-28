package com.ems.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Chiknin
 */
public class TestMain {
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		for (int l = 0; l < 10; l++) { // 测试循环 10 次
		
		File inFile = new File("a.day");
		InputStream is = new FileInputStream(inFile);
		InputStreamReader isr = new InputStreamReader(is, "iso8859-1");
		
		int fieldChars = 4; // 4个char为一个字段  ， TODO 使用byte类型 需要处理高位为1（负数）
		int cols = 8; // 每行有8列
		int fetchRows = 50; // 每次批量提取50行数据
		
		char[] cs = new char[fieldChars * cols * fetchRows]; // 4个char为一个字段，8个字段为一行, 一次读取50行
		int len = -1, // 如果每行长度不固定 需要判断此字段 
			colIndex = 0;
		Integer[] row = new Integer[cols];
		StringBuilder fieldHexStr = new StringBuilder();
		while ((len = isr.read(cs)) != -1) {
			for (int i = 0; i < cs.length; i+= 4) { // 4 == fieldChars.length
				for (int j = 3; j >= 0; j--) { // 3 == (fieldChars.length - 1)
					if (cs[i + j] < 16) { // 小于16的十六进制占1个字符，需在前面补0
						fieldHexStr.append("0").append(Integer.toString(cs[i + j], 16));
					} else {
						fieldHexStr.append(Integer.toString(cs[i + j], 16));
					}
				}
				row[colIndex++] = Integer.valueOf(fieldHexStr.toString(), 16); // 每行数据
				if (colIndex == cols) { // row.length
					// 行数据 row
					System.out.println(Arrays.asList(row)); // 测试时， 可以注释此行

					colIndex = 0; // 一行数据 读取结束， 行至下一行 列标识从 0 开始
				}
				
				fieldHexStr.delete(0, fieldHexStr.length());
			}
		}
		
		System.out.println("loop: " + l);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("use time(ms): " + (endTime - startTime));
	}
}
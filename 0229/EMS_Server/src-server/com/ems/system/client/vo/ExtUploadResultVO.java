package com.ems.system.client.vo;

import java.io.Serializable;

public class ExtUploadResultVO implements Serializable {
	
	private String fileName;
//	private String filePath;
	private Long fileSize;
	private String fileContentType;
	
	private ExtPagingVO successData;
	private ExtPagingVO failureData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public ExtPagingVO getSuccessData() {
		return successData;
	}
	public void setSuccessData(ExtPagingVO successData) {
		this.successData = successData;
	}
	public ExtPagingVO getFailureData() {
		return failureData;
	}
	public void setFailureData(ExtPagingVO failureData) {
		this.failureData = failureData;
	}
}
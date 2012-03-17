package com.ems.common.exception;

/**
 * 回滚业务异常，供事务框架识别。触发事务的回滚
 * @author zhuchaole
 *
 */
public class EMSRollbackableException extends EMSException {
	
	public EMSRollbackableException() {
		super();
	}

	public EMSRollbackableException(String message, Throwable cause) {
		super(message, cause);
	}

	public EMSRollbackableException(String message) {
		super(message);
	}

	public EMSRollbackableException(Throwable cause) {
		super(cause);
	}
}

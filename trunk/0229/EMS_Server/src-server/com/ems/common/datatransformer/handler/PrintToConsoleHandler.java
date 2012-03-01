package com.ems.common.datatransformer.handler;

import com.ems.common.datatransformer.api.IHandler;

/**
 * @author Chiknin
 */
public class PrintToConsoleHandler implements IHandler {

	public void execute(HandlerContext hCtx) {
		Object value = hCtx.getValue();
		
		String info = String.format("PrintToConsole: Value -> %n%s%nException -> %n%", value);
		System.out.println(info);
		if (hCtx.existsException()) {
			hCtx.getValidateFailExceptions().printStackTrace(System.out);
		}
	}
}
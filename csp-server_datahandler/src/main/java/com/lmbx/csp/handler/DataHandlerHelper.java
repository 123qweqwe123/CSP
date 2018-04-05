package com.lmbx.csp.handler;

import com.lmbx.csp.handler.amqp.AmqpHandler;
import com.lmbx.csp.handler.amqp.IAmqpHandler;
import com.lmbx.csp.handler.zip.IZipHandler;
import com.lmbx.csp.handler.zip.ZipHandler;

import java.io.File;

/**
 * 数据解析入口类
 * @author NCRC
 *
 */
public class DataHandlerHelper implements IZipHandler {

	IZipHandler zipHandler;
	IAmqpHandler amqpHandler;
	
	/**
	 * @param codeKey          数据解密key  
	 * @param type             数据类型  不同的类型，调用相应的数据解析逻辑
	 */
	public DataHandlerHelper(String projectId,String codeKey,String type){
		zipHandler = new ZipHandler(projectId,codeKey,type);
	}

	public DataHandlerHelper(String projectId, String dataType){
		amqpHandler = new AmqpHandler(projectId, dataType);
	}
	
	@Override
	public void handlerZipFile(File filename, String zipId) {
		zipHandler.handlerZipFile(filename, zipId);
	}

	@Override
	public void handlerZipFile(byte[] byteTubes, String fileId) {
		zipHandler.handlerZipFile(byteTubes, fileId);
	}

	public void handlerAmqpMsg(byte[] bytes, String dataType){
		amqpHandler.handlerAmqpMsg(bytes, dataType);
	}
}

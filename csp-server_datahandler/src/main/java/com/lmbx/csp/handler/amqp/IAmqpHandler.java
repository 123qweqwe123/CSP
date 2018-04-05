package com.lmbx.csp.handler.amqp;

public interface IAmqpHandler {
	/**
	 * 实时数据解析
	 * @param bytes  待解析的字节数组
	 * @param dataType    数据类型
	 */
	void handlerAmqpMsg(byte[] bytes, String dataType) ;
}

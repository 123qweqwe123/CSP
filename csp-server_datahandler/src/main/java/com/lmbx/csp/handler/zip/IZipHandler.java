package com.lmbx.csp.handler.zip;

import java.io.File;

public interface IZipHandler {
	/**
	 * 
	 * @param filename 待解析的文件
	 * @param zipId    文件在zip表中的id
	 */
	void handlerZipFile(File filename, String zipId) ;
	/**
	 * 
	 * @param byteTubes  待解析文件的字节数组
	 * @param zipId     文件在zip表中的id
	 */
	void handlerZipFile(byte[] byteTubes, String zipId) ;
}

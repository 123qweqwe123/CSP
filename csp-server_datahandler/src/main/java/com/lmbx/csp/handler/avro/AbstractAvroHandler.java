package com.lmbx.csp.handler.avro;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmbx.csp.core.utils.CryptoUtil;
import com.lmbx.csp.core.utils.EncodeUtils;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.data.sys.domain.SysDataParseInfo;
import com.lmbx.csp.data.sys.domain.SysDataParseInfoExample;
import com.lmbx.csp.data.sys.domain.SysDataParseQc;
import com.lmbx.csp.data.sys.mapper.SysDataParseInfoMapper;
import com.lmbx.csp.data.sys.mapper.SysDataParseQcMapper;

/**
 * 压缩包中文件文件的处理
 * 	- 成功 info表
 *  - 失败 qc表
 * @author NCRC
 *
 */
public abstract class AbstractAvroHandler implements IAvroHandler {

	static Logger log = LoggerFactory.getLogger(AbstractAvroHandler.class);

	private SysDataParseInfoMapper sysDataParseInfoMapper;
	private SysDataParseQcMapper sysDataParseQcMapper;

	protected String projectId;
	protected String codeKey;
	private String fileName;
	private String fileId;//文件表id
	
	private String infoId = null;
	private int sucCount;
	public AbstractAvroHandler(String projectId,String codeKey,String fileName,String fileId) {
		this.projectId = projectId;
		this.codeKey = codeKey;
		this.fileName = fileName;
		this.fileId = fileId;
		sysDataParseInfoMapper = SpringContextHolder.getBean(SysDataParseInfoMapper.class);
		sysDataParseQcMapper = SpringContextHolder.getBean(SysDataParseQcMapper.class);
	}

	@Override
	public void handlerAvroFile(File filename) {
		try {
			byte[] out = FileUtils.readFileToByteArray(filename);
			handlerAvroFile(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 若avro文件已经解析成功了，不再解析。
	 * 若需要重新解析，需要删除file表中的解析记录
	 */
	@Override
	public void handlerAvroFile(byte[] handler) {
		//查询是否已经成功解析过
		SysDataParseInfoExample example = new SysDataParseInfoExample();
		example.createCriteria().andFileNameEqualTo(this.fileName);
		int exCount = (int) sysDataParseInfoMapper.countByExample(example);
		if(exCount>0){
			throw new RuntimeException("08此文件已经解析过");
		}
		//解密
		byte[] output1 = decode(handler);
		if (output1 == null) {
			throw new RuntimeException("07解密失败");
		}
		try {
			//调用文件的业务处理方法
			sucCount = handlerAvroFileInner(output1);
			// 解析成功
			if (sucCount > 0) {
				handlerSuc();
				log.info("insert-info");
			}
		} catch (Exception e) {
			handlerFail(e);
			log.info("insert-qc");
			return;
		}
	}
	/**
	 * 正在的业务处理
	 * @param output1
	 * @return
	 */
	protected abstract int handlerAvroFileInner(byte[] output1) ;
	/**
	 * 冻存盒号，在解析的时候才能获取到
	 * @return
	 */
	protected abstract String getBoxCode() ;
	/**
	 * 插入或更新
	 * @return insert update
	 */
	protected abstract String getInsertWay() ;
	/**'
	 * info表的id
	 * @return
	 */
	protected String getInfoId(){
		if(infoId == null){
			infoId = UUID.randomUUID().toString().replaceAll("-", "");
		}
		return infoId;
	}

	/**
	 * 记录处理成功
	 */
	private void handlerSuc() {
		SysDataParseInfo scanInfo = new SysDataParseInfo();
		scanInfo.setId(getInfoId());
		scanInfo.setCreateBy("sys_"+getInsertWay());
		scanInfo.setCreateDate(new Date());
		scanInfo.setFileId(fileId);
		scanInfo.setFileName(this.fileName);
		sysDataParseInfoMapper.insertSelective(scanInfo);
	}

	/**
	 * 记录处理失败
	 * @param e
	 */
	private void handlerFail(Exception e) {
		// 解析失败
		String message = e.getMessage();
		SysDataParseQc scanQc = new SysDataParseQc();
		scanQc.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		String errorCode = message.substring(0, 2);
		scanQc.setFileId(fileId);
		scanQc.setCreateDate(new Date());
		scanQc.setCreateBy("sys");
		scanQc.setRemark("1");
		scanQc.setHandleSuc((short) 2);
		scanQc.setErrorCode(errorCode);
		scanQc.setErrorMsg(message);
		scanQc.setFileName(this.fileName);
		sysDataParseQcMapper.insertSelective(scanQc);
	}
	/**
	 * 解密
	 * 
	 * @param byteTubes
	 * @return
	 */
	private byte[] decode(byte[] byteTubes) {
		// 解码编码方式
		byte[] byteTubes1 = EncodeUtils.base64Decode(new String(byteTubes));

		// 解密
		byte[] output1;
		try {
			output1 = CryptoUtil.decrypt(encodeKey(), byteTubes1);
		} catch (Exception e) {
			throw new RuntimeException("01#解密失败");
		}
		return output1;
	}
	
	protected String encodeKey(){
		return this.codeKey;
	}
}

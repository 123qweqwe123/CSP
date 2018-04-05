package com.lmbx.csp.handler.zip;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import com.lmbx.csp.handler.file.MainPersonHandler;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmbx.csp.core.utils.DataUtils;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.data.sys.domain.SysDataParseFile;
import com.lmbx.csp.data.sys.domain.SysDataParseZip;
import com.lmbx.csp.data.sys.mapper.SysDataParseFileMapper;
import com.lmbx.csp.data.sys.mapper.SysDataParseZipMapper;
import com.lmbx.csp.handler.file.CheckinRecordHandler;
import com.lmbx.csp.handler.file.RegisterHandler;

public class ZipHandler implements IZipHandler {

	static Logger log = LoggerFactory.getLogger(ZipHandler.class);

	private SysDataParseFileMapper sysDataParseFileMapper;
	private SysDataParseZipMapper sysDataParseZipMapper;

	private String projectId;
	private String codeKey;
	private String type;//文件类型
	public ZipHandler(String projectId,String codeKey,String type){
		this.projectId = projectId;
		this.codeKey = codeKey;
		this.type = type;
		sysDataParseFileMapper = SpringContextHolder.getBean(SysDataParseFileMapper.class);
		sysDataParseZipMapper = SpringContextHolder.getBean(SysDataParseZipMapper.class);
	}
	@Override
	public void handlerZipFile(File filename, String zipId) {
		log.info("=====>begin:" + filename);
		byte[] data = null;
		try{
			data = FileUtils.readFileToByteArray(filename);
		}catch(Exception e){
			throw new RuntimeException("08#zip文件不存在");
		}
		handlerZipFile(data, zipId);
		log.info("=====>end:" + filename);
	}

	@Override
	public void handlerZipFile(byte[] bytes, String zipId) {
		SysDataParseFile scanFile = new SysDataParseFile();
		SysDataParseZip dataZip = sysDataParseZipMapper.selectByPrimaryKey(zipId);
		ZipInputStream zip = null;
		try {
			zip = new ZipInputStream(new ByteArrayInputStream(bytes));
			ZipEntry entry = zip.getNextEntry();
			while (entry != null) {
				//遍历每个文件
				String fileName = entry.getName();
				log.info("---->begin:" + fileName);
				byte[] byteData = DataUtils.input2byte(zip);
				try {
					//记录处理文件
					scanFile.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					scanFile.setZipId(zipId);
					scanFile.setFileName(fileName);
					scanFile.setCreateDate(new Date());
					scanFile.setCreateBy("sys");
					scanFile.setHandleState((short) 2);
					scanFile.setDataType(dataZip.getDataType());
					scanFile.setDataFormat(dataZip.getDataFormat());
					scanFile.setZipName(dataZip.getZipName());
					scanFile.setZipPath(dataZip.getZipPath());
					scanFile.setProjectId(dataZip.getProjectId());
					scanFile.setTerminalId(dataZip.getTerminalId());
					scanFile.setLccCode(dataZip.getLccCode());
					sysDataParseFileMapper.insertSelective(scanFile);
					log.info(fileName + ":file->insert-init-data " + this.type + " " + fileName);
					//处理单个文件
					handlerInner(byteData, fileName, scanFile.getId());
				} catch (Exception e) {
					String message = e.getMessage();
					scanFile.setErrorCode(message.substring(0, 2));
					scanFile.setErrorMsg(message);
				}finally{
					//更新文件处理结果
					scanFile.setHandleState(scanFile.getErrorMsg() == null ? (short) 1 : (short) 3);
					scanFile.setHandleDate(new Date());
					sysDataParseFileMapper.updateByPrimaryKey(scanFile);
					log.info(fileName + ":file->update-result");
				}
				log.info("-------------------------------------->end:");
				entry = zip.getNextEntry();
			}
		} catch (ZipException e) {
			throw new RuntimeException("07#解压失败");
		} catch (IOException e) {
			throw new RuntimeException("08#解压失败");
		} catch (Exception e) {
			throw new RuntimeException("09#zip处理失败");
		}finally{
			if(zip != null){
				try {
					zip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void handlerInner(byte[] byteData,String fileName, String fileId){
		if("checkin".equals(this.type)){		//签到
			CheckinRecordHandler checkinRecordHandler = new CheckinRecordHandler(projectId,codeKey, fileName, fileId);
			checkinRecordHandler.handlerFile(byteData);
		} else if ("register".equals(this.type)){//登记
			RegisterHandler registerHandler = new RegisterHandler(projectId, codeKey, fileName, fileId);
			registerHandler.handlerFile(byteData);
		} else if ("person".equals(this.type)){//Main_Person
			MainPersonHandler mainPersonHandler = new MainPersonHandler(projectId, codeKey, fileName, fileId);
			mainPersonHandler.handlerFile(byteData);
		}
	}

}

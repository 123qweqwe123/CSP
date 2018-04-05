package com.lmbx.csp.dispatch.service.download.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.lmbx.csp.core.utils.EncodeUtils;
import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.conf.mapper.*;
import com.lmbx.csp.dispatch.service.download.dto.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



/**
 * @author wanghongwei
 */
@Service
public class Dict5ataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private CspConferenceMapper        cspConferenceMapper;

    @Autowired
    private CspConfCertMapper          cspConfCertMapper;

    @Autowired
    private CspConfCertAttributeMapper cspConfCertAttributeMapper;

    @Value("${third-service.fastdfs.tracker-servers}")
    String                             trackerServers;

    @Override
    public List<EntityInZip> getEntitys(VersionDTO vervionDto) {
        List<EntityInZip> returnList = new ArrayList<EntityInZip>();
        String confNo = vervionDto.getConfNo();
        CspConferenceExample example = new CspConferenceExample();
        example.createCriteria().andConfNoEqualTo(confNo);
        List<CspConference> cspConferenceList = cspConferenceMapper.selectByExample(example);
        if (cspConferenceList.size() != 0) {
            CspConference cspConference = cspConferenceList.get(0);
            // 证件信息
            IdentityData identityData = getIdentitys(cspConference.getId(), cspConference.getConfNo(),
                                                     cspConference.getConfName());
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
                // 会议签到信息
                addEntity("IdentityData.json", mapper.writeValueAsString(identityData), returnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnList;

        }
        return null;
    }

    /**
     * 下载证件数据
     *
     * @param confId
     * @param confNo
     * @param confName
     * @return
     */
    private IdentityData getIdentitys(String confId, String confNo, String confName) {
        CspConfCertExample example = new CspConfCertExample();
        example.createCriteria().andConfIdEqualTo(confId);
        List<CspConfCert> cspConfCerts = cspConfCertMapper.selectByExample(example);
        IdentityData identity = new IdentityData();
        List<LayOutFile> layoutFileList = new ArrayList<>();
        List<BottomFile> bottomFileList = new ArrayList<>();
        try {
            if (cspConfCerts.size() > 0) {
                for (CspConfCert cert : cspConfCerts) {
                    LayOutFile layOutFile = new LayOutFile();
                    BottomFile bottomFile = new BottomFile();
                    String certId = cert.getId();
                    Short identityType = cert.getIdentityType();
                    String layOutfileId = cert.getLayoutfileId();
                    String bottomfileId = cert.getBottomfileId();
                    layOutFile.setId(certId);
                    layOutFile.setFileType(identityType);
                    layOutFile.setTemplateFileId(layOutfileId);
                    // 获取证件布局文件占位符的参数
                    List<ParamData> params = getIdentityParams(certId, (short) 0);
                    layOutFile.setDataMap(params);
                    //布局文件bate64位编码
                    TrackerClient tracker = new TrackerClient();
                    TrackerServer trackerServer = tracker.getConnection();
                    StorageClient1 client = new StorageClient1(trackerServer, null);
                    byte[] bytes = client.download_file1(layOutfileId);
                    String s1 = EncodeUtils.base64Encode(bytes);
                    layOutFile.setFileData(s1);
                    layoutFileList.add(layOutFile);
                    bottomFile.setId(certId);
                    bottomFile.setFileType(identityType);
                    bottomFile.setTemplateFileId(bottomfileId);
                    // 获取底板布局文件占位符的参数
                    List<ParamData> params1 = getIdentityParams(certId, (short) 1);
                    bottomFile.setDataMap(params1);
                    //底本文件bate64位编码
                    byte[] bytes1 = client.download_file1(bottomfileId);
                    String s = EncodeUtils.base64Encode(bytes1);
                    bottomFile.setFileData(s);
                    bottomFileList.add(bottomFile);
                }
                identity.setConferenceId(confId);
                identity.setConferenceNo(confNo);
                identity.setConferenceName(confName);
                identity.setLayoutFile(layoutFileList);
                identity.setBottomFile(bottomFileList);
                return identity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载证件参数数据
     *
     * @param certId
     * @param fileType
     * @return
     */

    private List<ParamData> getIdentityParams(String certId, Short fileType) {
        List<ParamData> dataMap = new ArrayList<>();
        CspConfCertAttributeExample example = new CspConfCertAttributeExample();
        example.createCriteria().andCertIdEqualTo(certId).andFileTypeEqualTo(fileType);
        List<CspConfCertAttribute> cspConfCertAttributeList = cspConfCertAttributeMapper.selectByExample(example);
        if (cspConfCertAttributeList.size() > 0) {
            for (CspConfCertAttribute attributes : cspConfCertAttributeList) {
                ParamData paramData = new ParamData();
                String attrName = attributes.getAttrName();
                String querySql = attributes.getQuerySql();
                String queryParameter = attributes.getQueryParameter();
                paramData.setAttrName(attrName);
                paramData.setQuerySql(querySql);
                paramData.setQueryParameter(queryParameter);
                dataMap.add(paramData);
            }
            return dataMap;
        }
        return null;
    }

    @Override
    public boolean needEncode() {
        return false;
    }

    @Override
    public boolean needZip() {
        return true;
    }

    /**
     * 服务器获得证件数据后base64位编解码测试
     * 
     * @param args
     */

    public static void main(String[] args) {
        try {
            byte[] bytes = EncodeUtils.base64Decode("sry+1g==");
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\zhengjian.txt"));
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.lmbx.csp.web.dataversion.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmbx.csp.core.utils.DictVersionUtils;
import com.lmbx.csp.core.utils.Utils;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersion;
import com.lmbx.csp.data.sys.domain.SysDataIssueVersionLog;
import com.lmbx.csp.data.sys.mapper.SysDataIssueVersionLogMapper;
import com.lmbx.csp.web.conf.service.impl.ConferenceServiceImpl;
import com.lmbx.csp.web.dataversion.filter.DataVersionFilter;
import com.lmbx.csp.web.dataversion.mapper.MySysDataIssueVersionLogMapper;
import com.lmbx.csp.web.dataversion.mapper.MySysDataIssueVersionMapper;
import com.lmbx.csp.web.dataversion.service.DictVersionLogService;

/**
 * TODO
 * 
 * @author qianyanan 2017年12月26日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictVersionLogServiceImpl implements DictVersionLogService {

    private static Logger                  logger = LoggerFactory.getLogger(ConferenceServiceImpl.class);

    @Autowired
    private MySysDataIssueVersionLogMapper mySysDataIssueVersionLogMapper;

    @Autowired
    private SysDataIssueVersionLogMapper   sysDataIssueVersionLogMapper;

    @Autowired
    private MySysDataIssueVersionMapper    mySysDataIssueVersionMapper;

    @Override
    public List<?> querySysDataIssueVersionLog() {
        List<SysDataIssueVersionLog> sysDataIssueVersionLogs1 = mySysDataIssueVersionLogMapper.selectCurrentDataVersion();
        if (sysDataIssueVersionLogs1.size() > 0) {
            for (SysDataIssueVersionLog versionLog : sysDataIssueVersionLogs1) {
                String dictVersion = versionLog.getDictVersion();
                String value1 = versionLog.getValue1();
                String value2 = versionLog.getValue2();
                String value3 = versionLog.getValue3();
                versionLog.setDictVersion(dictVersion + "(" + value2 + "-" + value3 + ")");
                // 计算文件大小
                long value = Long.parseLong(value1);
                BigDecimal filesize = new BigDecimal(value);
                BigDecimal megabyte = new BigDecimal(1024 * 1024);
                float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
                if (returnValue > 1) {
                    versionLog.setValue1(returnValue + "MB");
                } else {
                    BigDecimal kilobyte = new BigDecimal(1024);
                    returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
                    versionLog.setValue1(returnValue + "kB");
                }
            }
        }
        return sysDataIssueVersionLogs1;
    }

    @Override
    public List<?> querySysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog) {
        SysDataIssueVersionLog dataIssueVersionLog = new SysDataIssueVersionLog();
        dataIssueVersionLog.setId(sysDataIssueVersionLog.getId());
        dataIssueVersionLog.setDictType(sysDataIssueVersionLog.getDictType());
        dataIssueVersionLog.setValue3(sysDataIssueVersionLog.getValue3());
        List<SysDataIssueVersionLog> sysDataIssueVersionLogs1 = mySysDataIssueVersionLogMapper.selectDataVersionLog(dataIssueVersionLog);
        if (sysDataIssueVersionLogs1.size() > 0) {
            for (SysDataIssueVersionLog log : sysDataIssueVersionLogs1) {
                String dictVersion = log.getDictVersion();
                String value1 = log.getValue1();
                String value2 = log.getValue2();
                String value3 = log.getValue3();
                log.setDictVersion(dictVersion + "(" + value2 + "-" + value3 + ")");
                // 计算文件大小
                long value = Long.parseLong(value1);
                BigDecimal filesize = new BigDecimal(value);
                BigDecimal megabyte = new BigDecimal(1024 * 1024);

                float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
                if (returnValue > 1) {
                    log.setValue1(returnValue + "MB");
                } else {
                    BigDecimal kilobyte = new BigDecimal(1024);
                    returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
                    log.setValue1(returnValue + "kB");
                }
            }
        }
        return sysDataIssueVersionLogs1;
    }

    @Override
    @Transactional
    public void createSysDataIssueVersionLog(SysDataIssueVersionLog sysDataIssueVersionLog) {

        SysDataIssueVersionLog newsysDataIssueVersionLog = sysDataIssueVersionLogMapper.selectByPrimaryKey(sysDataIssueVersionLog.getId());
        newsysDataIssueVersionLog.setId(Utils.generateUUID());
        newsysDataIssueVersionLog.setRemark(sysDataIssueVersionLog.getRemark());
        newsysDataIssueVersionLog.setFileId(sysDataIssueVersionLog.getFileId());
        String dataVersion = DictVersionUtils.getDictVersion(newsysDataIssueVersionLog.getDictVersion());
        newsysDataIssueVersionLog.setValue1(sysDataIssueVersionLog.getValue1());
        newsysDataIssueVersionLog.setValue2(sysDataIssueVersionLog.getValue2());
        newsysDataIssueVersionLog.setValue3(sysDataIssueVersionLog.getValue3());
        newsysDataIssueVersionLog.setDictVersion(dataVersion);
        logger.debug("增加新版本，id:{}", newsysDataIssueVersionLog.getId());
        sysDataIssueVersionLogMapper.insert(newsysDataIssueVersionLog);
        DataVersionFilter dataVersionFilter = new DataVersionFilter();
        dataVersionFilter.setDictType(newsysDataIssueVersionLog.getDictType());
        List<SysDataIssueVersion> list = mySysDataIssueVersionMapper.selectByDataVersionFilter(dataVersionFilter);
        if (list.size() > 0) {
            if (Integer.parseInt(list.get(0).getValue3()) < Integer.parseInt(sysDataIssueVersionLog.getValue3())) {
                SysDataIssueVersion sysDataIssueVersion = new SysDataIssueVersion();
                sysDataIssueVersion.setId(null);
                sysDataIssueVersion.setFileId(sysDataIssueVersionLog.getFileId());
                sysDataIssueVersion.setDictVersion(dataVersion);
                sysDataIssueVersion.setDictType(newsysDataIssueVersionLog.getDictType());
                sysDataIssueVersion.setValue1(sysDataIssueVersionLog.getValue1());
                sysDataIssueVersion.setValue2(sysDataIssueVersionLog.getValue2());
                sysDataIssueVersion.setValue3(sysDataIssueVersionLog.getRemark());
                sysDataIssueVersion.setValue4(sysDataIssueVersionLog.getValue3());
                logger.debug("修改最新版本，描述：{}", sysDataIssueVersionLog.getRemark());
                mySysDataIssueVersionMapper.updateDictVersion(sysDataIssueVersion);
            }
        }

    }
}

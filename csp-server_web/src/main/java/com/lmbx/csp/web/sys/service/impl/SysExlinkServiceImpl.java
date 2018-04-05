package com.lmbx.csp.web.sys.service.impl;

import com.lmbx.csp.core.utils.Securitys;
import com.lmbx.csp.core.utils.Utils;
import com.lmbx.csp.data.sys.domain.SysExlink;
import com.lmbx.csp.data.sys.mapper.SysExlinkMapper;
import com.lmbx.csp.web.sys.filter.ExlinkFilter;
import com.lmbx.csp.web.sys.mapper.MySysMapper;
import com.lmbx.csp.web.sys.service.SysExlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 下午2:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysExlinkServiceImpl implements SysExlinkService {

    @Autowired
    private MySysMapper     mySysMapper;
    @Autowired
    private SysExlinkMapper sysExlinkMapper;

    @Override
    public List<?> queryExlinks(ExlinkFilter filter) {
        return mySysMapper.selectExlinkByFilter(filter);
    }

    @Override
    public void createExlink(SysExlink exlink) {
        exlink.setId(Utils.generateUUID());
        exlink.setCreateBy(Securitys.getAccountId());
        exlink.setCreateTime(new Date());
        sysExlinkMapper.insertSelective(exlink);
    }

    @Override
    public void updateExlink(SysExlink exlink) {
        exlink.setUpdateBy(Securitys.getAccountId());
        exlink.setUpdateTime(new Date());
        sysExlinkMapper.updateByPrimaryKeySelective(exlink);
    }

    @Override
    public void deleteExlink(String id) {
        sysExlinkMapper.deleteByPrimaryKey(id);
    }
}

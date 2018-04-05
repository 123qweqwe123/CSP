package com.lmbx.csp.web.sys.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbx.csp.core.utils.Securitys;
import com.lmbx.csp.core.utils.Utils;
import com.lmbx.csp.data.sys.domain.SysAccountDashboard;
import com.lmbx.csp.data.sys.domain.SysAccountDashboardExample;
import com.lmbx.csp.data.sys.domain.SysDashboardComponent;
import com.lmbx.csp.data.sys.domain.SysDashboardComponentExample;
import com.lmbx.csp.data.sys.mapper.SysAccountDashboardMapper;
import com.lmbx.csp.data.sys.mapper.SysDashboardComponentMapper;
import com.lmbx.csp.web.sys.service.DashboardService;
import com.lmbx.csp.web.sys.vo.DashboardComponentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * Author: javahuang Create: 2017/9/22 下午2:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SysDashboardComponentMapper sysDashboardComponentMapper;
    @Autowired
    private SysAccountDashboardMapper   sysAccountDashboardMapper;

    @Override
    public List<DashboardComponentVO> getDashboards() {
        return sysDashboardComponentMapper.selectByExample(null).stream().map(dashboard -> {
            DashboardComponentVO vo = new DashboardComponentVO();
            BeanUtils.copyProperties(dashboard, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DashboardComponentVO> getUserDashboards() {
        SysAccountDashboardExample exp = new SysAccountDashboardExample();
        exp.createCriteria().andAccountIdEqualTo(Securitys.getAccountId());
        return sysAccountDashboardMapper.selectByExample(exp).stream().map(userDashboard -> {
            SysDashboardComponentExample dExp = new SysDashboardComponentExample();
            dExp.createCriteria().andIEqualTo(userDashboard.getI());
            SysDashboardComponent sysDashboard = sysDashboardComponentMapper.selectByExample(dExp).get(0);
            DashboardComponentVO vo = new DashboardComponentVO();
            // 获取 maxW 等属性
            BeanUtils.copyProperties(sysDashboard, vo);
            BeanUtils.copyProperties(userDashboard, vo);
            // 设置 title
            vo.setTitle(sysDashboard.getTitle());
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public void saveUserDashboards(String dashboards) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SysAccountDashboardExample exp = new SysAccountDashboardExample();
        exp.createCriteria().andAccountIdEqualTo(Securitys.getAccountId());
        sysAccountDashboardMapper.deleteByExample(exp);
        try {
            List<DashboardComponentVO> vos = mapper.readValue(dashboards,
                                                              mapper.getTypeFactory().constructCollectionType(List.class,
                                                                                                              DashboardComponentVO.class));
            vos.forEach(vo -> {
                SysAccountDashboard dashboard = new SysAccountDashboard();
                BeanUtils.copyProperties(vo, dashboard);
                dashboard.setId(Utils.generateUUID());
                dashboard.setAccountId(Securitys.getAccountId());
                sysAccountDashboardMapper.insert(dashboard);
            });
            System.out.println(vos.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

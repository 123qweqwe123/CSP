package com.lmbx.csp.dispatch.service.download.impl;

import com.lmbx.csp.data.sys.domain.SysParameter;
import com.lmbx.csp.data.sys.domain.SysParameterExample;
import com.lmbx.csp.data.sys.domain.SysParatype;
import com.lmbx.csp.data.sys.domain.SysParatypeExample;
import com.lmbx.csp.data.sys.mapper.SysParameterMapper;
import com.lmbx.csp.data.sys.mapper.SysParatypeMapper;
import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wanghongwei
 */
@Service
public class Dict0DataDownLoadImpl extends AbstractDataDownLoad {

    @Autowired
    private SysParatypeMapper sysParatypeMapper;

    @Autowired
    private SysParameterMapper sysParameterMapper;

    @Override
    public List<EntityInZip> getEntitys(VersionDTO versionDTO) {
        List<EntityInZip> returnList = new ArrayList<>();
        // 参数类型
        List<SysParatype> sysParatypeList = getSysPareType();
        // 类型所对的详细信息
        List<SysParameter> sysParameters = getSysParameter(sysParatypeList);
        try {
            ObjectMapper mapper = new ObjectMapper();
            // 参数类型表
            addEntity("SYS_PARATYPE.json", mapper.writeValueAsString(sysParatypeList), returnList);
            // 类型所对的详细信息表
            addEntity("SYS_PARAMETER.json", mapper.writeValueAsString(sysParameters), returnList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    /**
     * 通用字典所对的详细信息
     *
     * @param sysParatypeList 参数类型
     * @return
     */
    private List<SysParameter> getSysParameter(List<SysParatype> sysParatypeList) {
        List<SysParameter> sysParameterList = new ArrayList<>();
        if (sysParatypeList.size() > 0) {
            List<String> codes = sysParatypeList.stream().map(SysParatype::getCode).collect(Collectors.toList());
            SysParameterExample sysParameterExample = new SysParameterExample();
            sysParameterExample.createCriteria().andTypeCodeIn(codes);
            sysParameterList = sysParameterMapper.selectByExample(sysParameterExample);
        }
        return sysParameterList;
    }

    /**
     * 通用字典版本信息
     *
     * @return
     */
    private List<SysParatype> getSysPareType() {
        SysParatypeExample sysParatypeExample = new SysParatypeExample();
        List<SysParatype> sysParatypeList = sysParatypeMapper.selectByExample(sysParatypeExample);
        return sysParatypeList;
    }

    @Override
    public boolean needEncode() {
        return false;
    }

    @Override
    public boolean needZip() {
        return true;
    }

}

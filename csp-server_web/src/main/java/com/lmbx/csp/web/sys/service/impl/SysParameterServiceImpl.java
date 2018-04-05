package com.lmbx.csp.web.sys.service.impl;

import com.lmbx.csp.core.exception.ValidatorException;
import com.lmbx.csp.core.utils.PinyingUtils;
import com.lmbx.csp.core.utils.Securitys;
import com.lmbx.csp.core.utils.Utils;
import com.lmbx.csp.data.sys.domain.SysParameter;
import com.lmbx.csp.data.sys.domain.SysParameterExample;
import com.lmbx.csp.data.sys.domain.SysParatype;
import com.lmbx.csp.data.sys.domain.SysParatypeExample;
import com.lmbx.csp.data.sys.mapper.SysParameterMapper;
import com.lmbx.csp.data.sys.mapper.SysParatypeMapper;
import com.lmbx.csp.web.sys.filter.ParameterFilter;
import com.lmbx.csp.web.sys.mapper.MySysMapper;
import com.lmbx.csp.web.sys.service.SysParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/28 下午7:03
 */
@Service
@EnableTransactionManagement
public class SysParameterServiceImpl implements SysParameterService {

    @Autowired
    private MySysMapper        mySysMapper;

    @Autowired
    private SysParameterMapper sysParameterMapper;

    @Autowired
    private SysParatypeMapper  sysParatypeMapper;

    @Override
    public List<SysParameter> queryParameters(ParameterFilter filter) {
        return mySysMapper.selectParameterByFilter(filter);
    }

    @Override
    public void deleteParameter(String id) {
        sysParameterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void createParameter(SysParameter parameter) {
        parameter.setId(Utils.generateUUID());
        validCode(parameter);
        validValue(parameter);
        parameter.setCreateBy(Securitys.getAccountId());
        parameter.setCreateTime(new Date());
        SysParameterExample exp = new SysParameterExample();
        exp.createCriteria().andTypeCodeEqualTo(parameter.getTypeCode());
        long total = sysParameterMapper.countByExample(exp);
        parameter.setSequence((short) (total + 1));
        sysParameterMapper.insertSelective(parameter);
    }

    private void validCode(SysParameter parameter) {
        SysParameterExample exp = new SysParameterExample();
        exp.createCriteria().andCodeEqualTo(parameter.getCode()).andTypeCodeEqualTo(parameter.getTypeCode()).andIdNotEqualTo(parameter.getId());
        long total = sysParameterMapper.countByExample(exp);
        if(total > 0) {
            throw new ValidatorException("参数编码不能重复");
        }
    }

    private void validValue(SysParameter parameter) {
        SysParameterExample exp = new SysParameterExample();
        exp.createCriteria().andValueEqualTo(parameter.getValue()).andTypeCodeEqualTo(parameter.getTypeCode()).andIdNotEqualTo(parameter.getId());
        long total = sysParameterMapper.countByExample(exp);
        if(total > 0) {
            throw new ValidatorException("参数值不能重复");
        }
    }

    @Override
    public void updateParameter(SysParameter parameter) {
        validCode(parameter);
        validValue(parameter);
        parameter.setUpdateBy(Securitys.getAccountId());
        parameter.setUpdateTime(new Date());
        sysParameterMapper.updateByPrimaryKey(parameter);
    }

    @Override
    public List<?> queryParaTypes(ParameterFilter filter) {
        return mySysMapper.selectParaTypeByFilter(filter);
    }

    @Override
    public void createParaType(SysParatype paraType) {
        paraType.setId(Utils.generateUUID());
        paraType.setCreateBy(Securitys.getAccountId());
        paraType.setCreateTime(new Date());
        SysParatypeExample exp = new SysParatypeExample();
        exp.setOrderByClause("to_number(substr(code,2)) desc");
        List<SysParatype> types = sysParatypeMapper.selectByExample(exp);
        String code = null;
        if (types.size() == 0) {
            code = "T001";
        } else {
            String maxCode = types.get(0).getCode();
            code = "T" + String.format("%03d", Integer.parseInt(maxCode.substring(1)) + 1);
        }
        paraType.setCode(code);
        paraType.setHelpCode(PinyingUtils.getJM(paraType.getValue()));
        sysParatypeMapper.insertSelective(paraType);
    }

    @Override
    public void updateParaType(SysParatype paraType) {
        paraType.setHelpCode(PinyingUtils.getJM(paraType.getValue()));
        paraType.setUpdateBy(Securitys.getAccountId());
        paraType.setUpdateTime(new Date());
        sysParatypeMapper.updateByPrimaryKeySelective(paraType);
    }

    @Override
    public void deleteParaType(String id) {
        SysParatype parType = sysParatypeMapper.selectByPrimaryKey(id);
        String typeCode = parType.getCode();
        sysParatypeMapper.deleteByPrimaryKey(id);
        SysParameterExample parameterExample = new SysParameterExample();
        parameterExample.createCriteria().andTypeCodeEqualTo(typeCode);
        sysParameterMapper.deleteByExample(parameterExample);
    }

    @Override
    public void sortParameter(String id, String siblingId) {
        SysParameter currentParameter = sysParameterMapper.selectByPrimaryKey(id);
        Short currentSequence = currentParameter.getSequence();
        SysParameter siblingParameter = sysParameterMapper.selectByPrimaryKey(siblingId);
        Short siblingSequence = siblingParameter.getSequence();
        currentParameter.setSequence(siblingSequence);
        siblingParameter.setSequence(currentSequence);
        sysParameterMapper.updateByPrimaryKeySelective(currentParameter);
        sysParameterMapper.updateByPrimaryKeySelective(siblingParameter);
    }
}

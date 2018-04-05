package com.lmbx.csp.web.sys.service;

import com.lmbx.csp.data.sys.domain.SysParameter;
import com.lmbx.csp.data.sys.domain.SysParatype;
import com.lmbx.csp.web.sys.filter.ParameterFilter;

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
public interface SysParameterService {

    List<SysParameter> queryParameters(ParameterFilter filter);

    void deleteParameter(String id);

    void createParameter(SysParameter parameter);

    void updateParameter(SysParameter parameter);

    List<?> queryParaTypes(ParameterFilter filter);

    void createParaType(SysParatype paraType);

    void updateParaType(SysParatype paraType);

    void deleteParaType(String id);

    void sortParameter(String id, String siblingId);
}

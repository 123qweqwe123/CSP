package com.lmbx.csp.web.common.mapper;

import com.lmbx.csp.web.common.qo.AutoCompleteQO;
import com.lmbx.csp.web.common.vo.AutoCompleteVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
@Mapper
public interface AutoCompleteMapper {

    List<AutoCompleteVO> selectComboxData(AutoCompleteQO qo);
}

package com.lmbx.csp.web.common.service;

import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 *     excel 导入、导出，使用 jxls2.x
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public interface JxlsService {

    /**
     * @param templatePath 模板路径，相对 resources/template 路径
     * @param vars 变量
     * @return 文件在 fastdfs 上面的 id
     */
    String createExcel(String templatePath, String fileName, Map<String, Object> vars);
}

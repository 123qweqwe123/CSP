package com.lmbx.csp.web.common.controller;

import com.lmbx.csp.web.common.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Description:
 * <p>
 * 
 * <pre>
 *     前端 Select/AutoComplete
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
@RestController
@RequestMapping("/common/combox")
public class ComboxController {

    private final CommonService commonService;

    @Autowired
    public ComboxController(CommonService commonService){
        this.commonService = commonService;
    }

    /**
     * @param q 搜索补全字符串
     * @param s 搜索框标识
     * @return 组装的缓存列表
     */
    @GetMapping("/autoComplete")
    public List<?> autoComplete(String q, String s, Integer limit) {
        return commonService.queryAutoComplete(q, s, limit);
    }

    /**
     * @param paraType 字典类型
     * @return 字典类型对应的参数列表
     */
    @GetMapping("/select")
    public List<?> autoComplete(String paraType) {
        if (StringUtils.isBlank(paraType)) {
            return null;
        }
        return commonService.querySelect(paraType);
    }

}

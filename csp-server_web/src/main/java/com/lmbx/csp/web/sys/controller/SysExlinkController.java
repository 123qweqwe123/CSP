package com.lmbx.csp.web.sys.controller;

import com.lmbx.csp.data.sys.domain.SysExlink;
import com.lmbx.csp.web.sys.filter.ExlinkFilter;
import com.lmbx.csp.web.sys.service.SysExlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 下午2:06
 */
@RestController
@RequestMapping("/sys/exlinks")
public class SysExlinkController {

    @Autowired
    private SysExlinkService sysExlinkService;

    @GetMapping
    public List<?> queryExlinks(ExlinkFilter filter) {
        return sysExlinkService.queryExlinks(filter);
    }

    @PostMapping
    public void createExlink(SysExlink exlink) {
        sysExlinkService.createExlink(exlink);
    }

    @PatchMapping
    public void updateExlink(SysExlink exlink) {
        sysExlinkService.updateExlink(exlink);
    }

    @DeleteMapping
    public void deleteExlink(String id) {
        sysExlinkService.deleteExlink(id);
    }
}

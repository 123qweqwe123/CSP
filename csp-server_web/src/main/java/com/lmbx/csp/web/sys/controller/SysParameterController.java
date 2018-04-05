package com.lmbx.csp.web.sys.controller;

import com.lmbx.csp.data.sys.domain.SysParameter;
import com.lmbx.csp.data.sys.domain.SysParatype;
import com.lmbx.csp.web.sys.filter.ParameterFilter;
import com.lmbx.csp.web.sys.service.SysParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/28 下午7:02
 */
@RestController
@RequestMapping("/sys/parameters")
public class SysParameterController {

    @Autowired
    private SysParameterService sysParameterService;

    @GetMapping
    public List<?> queryParaTypes(ParameterFilter filter) {
        return sysParameterService.queryParaTypes(filter);
    }

    @PostMapping
    public void createParaType(SysParatype sysParaType) {
        sysParameterService.createParaType(sysParaType);
    }

    @PatchMapping
    public void updateParaType(SysParatype sysParaType) {
        sysParameterService.updateParaType(sysParaType);
    }

    @DeleteMapping
    public void deleteParaType(String id) {
        sysParameterService.deleteParaType(id);
    }

    @GetMapping("/{typeCode}")
    public List<?> queryParameters(ParameterFilter filter) {
        return sysParameterService.queryParameters(filter);
    }

    @PostMapping("/{typeCode}")
    public void createParameter(SysParameter parameter) {
        sysParameterService.createParameter(parameter);
    }

    @PatchMapping("/{typeCode}")
    public void updateParameter(SysParameter parameter) {
        sysParameterService.updateParameter(parameter);
    }

    @DeleteMapping("/{typeCode}")
    public void deleteParameter(String id) {
        sysParameterService.deleteParameter(id);
    }

    @PatchMapping("/{typeCode}/sort")
    public void sortParameter(String id, String siblingId) {
        sysParameterService.sortParameter(id, siblingId);
    }
}

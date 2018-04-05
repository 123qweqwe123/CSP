package com.lmbx.csp.web.sys.controller;

import com.lmbx.csp.web.sys.service.DashboardService;
import com.lmbx.csp.web.sys.vo.DashboardComponentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardComponentVO> dashboards() {
        return dashboardService.getDashboards();
    }

    @GetMapping("/user")
    public List<DashboardComponentVO> userDashboards() {
        return dashboardService.getUserDashboards();
    }

    /**
     * TODO: springMVC 怎么直接接收前端传过来的数组对象?
     * 
     * @param dashboards
     */
    @PostMapping
    public void dashboards(String dashboards) {
        dashboardService.saveUserDashboards(dashboards);
    }

}

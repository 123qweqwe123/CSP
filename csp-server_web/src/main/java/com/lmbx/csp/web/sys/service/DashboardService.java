package com.lmbx.csp.web.sys.service;

import com.lmbx.csp.web.sys.vo.DashboardComponentVO;

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
public interface DashboardService {

    List<DashboardComponentVO> getDashboards();

    List<DashboardComponentVO> getUserDashboards();

    void saveUserDashboards(String dashboards);
}

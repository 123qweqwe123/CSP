package com.lmbx.csp.web.conf.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/27 上午9:22
 */
public class ConferenceFilter extends PageFilter {

    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

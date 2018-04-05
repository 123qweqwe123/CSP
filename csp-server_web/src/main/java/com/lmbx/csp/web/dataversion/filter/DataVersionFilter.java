package com.lmbx.csp.web.dataversion.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lmbx.csp.core.mybatis.domain.PageFilter;

/**
 * TODO
 * 
 * @author qianyanan 2017年12月26日
 */
public class DataVersionFilter extends PageFilter {

    private String dictType;
    private String lccCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   startDate;

    public String getLccCode() {
        return lccCode;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setLccCode(String lccCode) {
        this.lccCode = lccCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

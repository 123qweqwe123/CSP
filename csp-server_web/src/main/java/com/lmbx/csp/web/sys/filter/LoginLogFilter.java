package com.lmbx.csp.web.sys.filter;

import com.lmbx.csp.core.mybatis.domain.PageFilter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public class LoginLogFilter extends PageFilter {

    private String machineIp;

    private Short  isSuccess;

    private Short  isValid;

    private String accountId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   startLoginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   endLoginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   startLogoutTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   endLogoutTime;

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public Short getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Short isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Short getIsValid() {
        return isValid;
    }

    public void setIsValid(Short isValid) {
        this.isValid = isValid;
    }

    public Date getStartLoginTime() {
        return startLoginTime;
    }

    public void setStartLoginTime(Date startLoginTime) {
        this.startLoginTime = startLoginTime;
    }

    public Date getEndLoginTime() {
        return endLoginTime;
    }

    public void setEndLoginTime(Date endLoginTime) {
        this.endLoginTime = endLoginTime;
    }

    public Date getStartLogoutTime() {
        return startLogoutTime;
    }

    public void setStartLogoutTime(Date startLogoutTime) {
        this.startLogoutTime = startLogoutTime;
    }

    public Date getEndLogoutTime() {
        return endLogoutTime;
    }

    public void setEndLogoutTime(Date endLogoutTime) {
        this.endLogoutTime = endLogoutTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

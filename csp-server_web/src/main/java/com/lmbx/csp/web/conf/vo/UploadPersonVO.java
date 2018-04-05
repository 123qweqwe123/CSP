package com.lmbx.csp.web.conf.vo;

import com.lmbx.csp.core.validator.annotation.DecimalToString;
import com.lmbx.csp.core.validator.annotation.IsIn;
import com.lmbx.csp.core.validator.annotation.TrimSpace;
import com.lmbx.csp.web.conf.validator.ValidIdNumber;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Description:
 * <p>
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/4 下午3:30
 */
@ValidIdNumber
@TrimSpace
@DecimalToString(includeFields = {"idNumber", "tel"})
public class UploadPersonVO {

    @NotNull(message = "身份证号不能为空")
    private String idNumber;

    @IsIn(values = {"身份证", "护照", "其他"}, message = "证件类型不存在(可选:身份证,护照,其他)")
    @NotNull(message = "证件类型不能为空")
    private String idTypeStr;

    @IsIn(values = {"来宾", "工作人员", "讲师"}, message = "人员类型不存在(可选:来宾,工作人员,讲师)")
    private String typeStr;

    @NotNull(message = "名字不能为空")
    private String name;

    @IsIn(values = {"男", "女", "未知"}, message = "性别输入错误(可选:男,女,未知)")
    private String genderStr;

    private Date birthday;

    @Pattern(regexp = "^(1[3|4|5|7|8][0-9]{9})|([\\d\\.]+E\\d+)$", message = "手机号输入错误")
    private String tel;

    @Email
    private String email;

    private String workplace;

    private String department;

    private String major;

    private String degree;

    private String duty;

    private String province;

    private String city;

    private String county;

    private String address;

    private String sameQrcode;

    private String remark;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdTypeStr() {
        return idTypeStr;
    }

    public void setIdTypeStr(String idTypeStr) {
        this.idTypeStr = idTypeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSameQrcode() {
        return sameQrcode;
    }

    public void setSameQrcode(String sameQrcode) {
        this.sameQrcode = sameQrcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

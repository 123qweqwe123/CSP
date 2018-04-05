package com.lmbx.csp.web.conf.vo;

import com.google.common.collect.Lists;
import com.lmbx.csp.data.conf.domain.CspConfCheckin;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/6 下午2:53
 */
public class CspConfCheckinVO extends CspConfCheckin {

    private Integer v1;
    private Integer v2;
    private Integer v3;
    private String  checkinPersonType;

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        this.v1 = v1;
    }

    public Integer getV2() {
        return v2;
    }

    public void setV2(Integer v2) {
        this.v2 = v2;
    }

    public Integer getV3() {
        return v3;
    }

    public void setV3(Integer v3) {
        this.v3 = v3;
    }

    public String getCheckinPersonType() {
        List<String> result = Lists.newArrayList();
        if (v1 != null && v1 != 0) {
            result.add("1");
        }
        if (v2 != null && v2 != 0) {
            result.add("2");
        }
        if (v3 != null && v3 != 0) {
            result.add("3");
        }
        return StringUtils.join(result, ",");
    }

    public void setCheckinPersonType(String checkinPersonType) {
        this.checkinPersonType = checkinPersonType;
    }
}

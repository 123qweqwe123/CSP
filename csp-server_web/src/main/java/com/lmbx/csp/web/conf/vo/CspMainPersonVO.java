package com.lmbx.csp.web.conf.vo;

import com.lmbx.csp.data.main.domain.CspMainPerson;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/1 下午2:56
 */
public class CspMainPersonVO extends CspMainPerson{

    private Short personType;
    private String personId;

    public Short getPersonType() {
        return personType;
    }

    public void setPersonType(Short personType) {
        this.personType = personType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}

package com.lmbx.csp.web.conf.vo;

import com.lmbx.csp.data.conf.domain.CspConference;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/27 上午10:34
 */
public class CspConferenceVO extends CspConference{
    private String placeName;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}

package com.lmbx.csp.dispatch.service.download.impl;

import com.lmbx.csp.dispatch.service.download.dto.VersionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghongwei
 */
@Service
public class Dict98PcDataDownLoadImpl extends AbstractDataDownLoad {

    @Override
    public boolean needEncode() {
        return false;
    }

    @Override
    public boolean needZip() {
        return false;
    }
}

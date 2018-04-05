package com.lmbx.csp.web.common.service;

import com.lmbx.csp.web.common.vo.AutoCompleteVO;
import com.lmbx.csp.web.common.vo.SelectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 * 封装一些共通的操作
 * </pre>
 * 
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
public interface CommonService {

    List<AutoCompleteVO> queryAutoComplete(String q, String s, Integer limit);

    List<SelectVO> querySelect(String q);

    String upload(MultipartFile file);

    byte[] download(String fileId);

    Map<String, Object> downloadWithMetaData(String fileId);

}

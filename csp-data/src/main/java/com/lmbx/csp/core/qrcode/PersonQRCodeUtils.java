package com.lmbx.csp.core.qrcode;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import com.lmbx.csp.data.main.domain.CspMainPerson;

/**
 * 
 * @author 韩晓伟
 *
 */
public class PersonQRCodeUtils {
    
    /**
     * 生成二维码的值
     * @param confNo
     * @param person
     * @return
     */
    public static String createPersonQRCode(String confNo,CspMainPerson person){
        String returnStr = confNo+":"+System.currentTimeMillis();
        try {
            String codeStr = confNo + ":" + person.getIdNumber();
            /*
            String idNumber = person.getIdNumber();
            if ("M_00002_2_20171210_1".equals(confNo)) {
                codeStr = "M00002_03_20171213_1" + idNumber;
            }
            */
            //每次会议使用相同的二维码
            if("1".equals(person.getSameQrcode())){
                codeStr = person.getIdType()+":"+person.getIdNumber();
            }
            //加密 md5->hex
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(codeStr.getBytes("utf-8"));
            char[] chars = Hex.encodeHex(md5.digest());
            returnStr = new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }
}

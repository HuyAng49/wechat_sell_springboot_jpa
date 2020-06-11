package com.hxzy.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author: huYang
 * @description:
 * @date: 2020-05-25 21:28
 */
public class WeChatCheckUtil {
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
//        String token="33_BbkPOtVvXQSicNjervAhSgWfiwXftHqPHB3VUMJjVaKYX1kA1BWIcGb5LEQtJzVyde3cXBcUhjKivtY4yAnOW5WChk9DbXD89hvUK145SEC1-XqnWsLCtOqa_mSNF9Df96ht2yflM2QAPC0KXBXhAIANRN";
        String token = "yang";
        String[] arr = {token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer buffer = new StringBuffer();
        for (String str : arr) {
            buffer.append(str);
        }
        String temp = getSha1(buffer.toString());
        //5.将加密后的字符串与微信传来的加密签名比较，返回结果
        return temp.equalsIgnoreCase(signature);
    }

    //sha1加密
    private static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
